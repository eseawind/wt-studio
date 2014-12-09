package com.wt.studio.plugin.wizard.projects.model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.IWorkbench;

import com.wt.studio.plugin.platform.util.HeaProjectModel;
import com.wt.studio.plugin.wizard.projects.Activator;
import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel;
import com.wt.studio.plugin.wizard.projects.dbhelp.TableModel;
import com.wt.studio.plugin.wizard.projects.model.templates.DaoImplTemplate;
import com.wt.studio.plugin.wizard.projects.model.templates.DaoTemplate;
import com.wt.studio.plugin.wizard.projects.model.templates.ManageClassTemplate;
import com.wt.studio.plugin.wizard.projects.model.templates.ManageHtmlTemplate;
import com.wt.studio.plugin.wizard.projects.model.templates.ModelTemplate;
import com.wt.studio.plugin.wizard.projects.model.templates.ServiceImplTemplate;
import com.wt.studio.plugin.wizard.projects.model.templates.ServiceTemplate;
import com.wt.studio.plugin.wizard.projects.model.templates.UpdateClassTemplate;
import com.wt.studio.plugin.wizard.projects.model.templates.UpdateHtmlTemplate;
import com.wt.studio.plugin.wizard.projects.model.util.ClassNameInfo;
import com.wt.studio.plugin.wizard.projects.model.util.EntityFieldInfo;
import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;
import com.wt.studio.plugin.wizard.projects.model.util.PackageNameInfo;
import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs.JavaTemplateType;
import com.wt.studio.plugin.wizard.projects.uitl.Consistant;
import com.wt.studio.plugin.wizard.projects.uitl.DataUtil;
import com.wt.studio.plugin.wizard.projects.uitl.HEABaseWizard;

/**
 * <pre>
 * 业务名:功能向导
 * 功能说明: 首页
 * 编写日期:	2012-12-20
 * 作者:	DongYibo
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class MultiModelWizard extends HEABaseWizard {

	private MultiModelPage firstModelPage;

	@SuppressWarnings("unused")
	private ISelection selection;

	private HeaProjectModel heaProjectModel;

	private List<JavaTemplateArgs> javaTemplateArgsList = null;
	private ModuleModel module = null;
	private ModuleModel oldModule = null;

	/**
	 * 构造函数
	 */
	public MultiModelWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	public MultiModelWizard(HeaProjectModel heaProjectModel) {
		super();
		setNeedsProgressMonitor(true);
		this.heaProjectModel = heaProjectModel;
	}

	@Override
	public void addPages() {
		this.setWindowTitle("New Model Wizard");
		firstModelPage = new MultiModelPage(
				Activator.getProjectName(heaProjectModel.getName()));
		addPage(firstModelPage);
	}

	@Override
	public boolean canFinish() {
		boolean result = true;
		return result;
	}

	@Override
	public boolean performFinish() {
		final String pkgname = firstModelPage.getTxtPak().getText();
		try {
			Activator.validHEAProjectResource(heaProjectModel, null,
					pkgname);
		} catch (Exception e1) {

		}
		boolean result = false;

		List<TableModel> selectedTables = firstModelPage.getSeletedTable();

		String moduleName = "";
		String modelName = "";
		String tableName = "";
		TableModel tableModel = null;
		List<ColumeModel> columeModels = null;

		for (TableModel model : selectedTables) {
			moduleName = model.getClazzName();
			modelName = model.getClazzName();
			tableName = model.getTableName();
			columeModels = firstModelPage.getSeletedColumes(model.getTableName());
			try {
				module = settingModuleModel(moduleName, "", tableModel,
						columeModels, modelName, pkgname);
				assembleModelTemplate(pkgname, moduleName, tableName, module);
				boolean bdao = true;
				javaTemplateArgsList = createJavaTemplateArgsList(pkgname,
						heaProjectModel.getName(), moduleName, bdao, false,
						false, false);
			} catch (IndexOutOfBoundsException e) {
				MessageDialog.openWarning(getShell(), "提示",
						"Windows 文件大小写问题！请修改模块名称");
				result = false;
			} catch (Exception e) {
				MessageDialog.openWarning(getShell(), "提示",
						"Windows 文件大小写问题！请修改模块名称");
				result = false;
			}

			IRunnableWithProgress op = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException {
					monitor.beginTask("代码生成", 200);

					// 定义模板
					DaoTemplate daoTemplate = new DaoTemplate();
					DaoImplTemplate daoImplTemplate = new DaoImplTemplate();
					// -------------------------------------java代码生成-------------------------------------
					monitor.worked(1);
					monitor.subTask("java代码生成");

					for (JavaTemplateArgs javaTemplateArgs : javaTemplateArgsList) {
						try {
							// 生成java代码
							String javaCode = "";
							switch (javaTemplateArgs.getType()) {
							case DAO:
								javaCode = daoTemplate
										.generate(javaTemplateArgs);
								break;
							case DAOIMPL:
								javaCode = daoImplTemplate
										.generate(new Object[] {
												javaTemplateArgs, module,
												pkgname });
								break;
							}

							// 创建包
							IPackageFragmentRoot packageFragmentRoot = heaProjectModel
									.getJavaProject()
									.findPackageFragmentRoot(
											new Path(
													"/"
															+ heaProjectModel
																	.getName()
															+ Consistant.HEAPROJECT_JAVA));
							IPackageFragment packageFragment = packageFragmentRoot
									.getPackageFragment(javaTemplateArgs
											.getPackageName());
							if (!packageFragment.exists()) {
								packageFragment = packageFragmentRoot
										.createPackageFragment(javaTemplateArgs
												.getPackageName(), true, null);
							}

							packageFragment.createCompilationUnit(
									javaTemplateArgs.getClassName() + ".java",
									javaCode, true, new NullProgressMonitor());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			};

			try {
				getContainer().run(true, false, op);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return false;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * 刷新工程
		 */
		heaProjectModel.refresh();
		return true;
	}

	/**
	 * 保存Model 投 文件
	 * 
	 * @param model
	 * @return
	 */
	private boolean saveModelFile(ModuleModel model) {
		boolean result = false;
		IPath path = new Path(Consistant.DESIGNER_MODEL_PATH
				+ model.getModuleName() + Consistant.MODEL_POSTFIX);
		try {
			String data = DataUtil.java2xml(Consistant.MODEL_NAME,
					ModuleModel.class, model);
			InputStream contentStream = IOUtils.toInputStream(data);
			final IFile file = heaProjectModel.getProject().getFile(path);
			if (file.exists()) {
				file.setContents(contentStream, true, true, null);
			} else {
				file.create(contentStream, true, null);
			}
			result = true;
		} catch (CoreException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 从文件读数据到Model
	 */
	private boolean readModelFile(String name) {
		boolean result = false;
		IPath path = new Path(Consistant.DESIGNER_MODEL_PATH + name
				+ Consistant.MODEL_POSTFIX);
		IFile file = heaProjectModel.getProject().getFile(path);

		if (file.exists()) {
			try {
				oldModule = (ModuleModel) DataUtil.xml2java(
						Consistant.MODEL_NAME, ModuleModel.class,
						file.getContents());
			} catch (CoreException e) {
				e.printStackTrace();
			}
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * 方法说明：
	 * 
	 * @param moduleName
	 *            moduleName
	 * @param moduleDesc
	 *            moduleDesc
	 * @param tableModel
	 *            tableModel
	 * @param columeModels
	 *            columeModels
	 * @param modelName
	 *            modelName
	 * @return module
	 */
	private ModuleModel settingModuleModel(String moduleName,
			String moduleDesc, TableModel tableModel,
			List<ColumeModel> columeModels, String modelName, String packageName) {
		ModuleModel result = new ModuleModel();
		result.setProjectName(this.heaProjectModel.getName());
		result.setModuleName(moduleName);
		result.setModuleDesc(moduleDesc);
		result.setTableModel(tableModel);
		result.setColumeModels(columeModels);
		result.setModalName(modelName);
		result.setPackageName(packageName);
		return result;
	}

	/**
	 * 组装模版
	 * 
	 * @param moduleName
	 *            moduleName
	 */
	private void assembleModelTemplate(String pkgname, String moduleName,
			String tableName, ModuleModel module) throws Exception {
		try {

			IPath path = heaProjectModel.getProject().getFullPath()
					.append(Consistant.HEAPROJECT_JAVA)
					.append(StringUtils.replace("." + pkgname, ".", "/"))
					.append("/model");

			IPackageFragment packageFragment = heaProjectModel.getJavaProject()
					.findPackageFragment(path);
			// 获取包名对象
			PackageNameInfo packageNameInfo = new PackageNameInfo(
					packageFragment, moduleName);

			ICompilationUnit compilationUnit = packageFragment
					.getCompilationUnit(moduleName + ".java");
			// 遍历实体类
			ClassNameInfo classNameInfo = new ClassNameInfo(compilationUnit, moduleName);
			// 获取实体类属性

			// -------------------------------Model-----------------------------
			JavaTemplateArgs javaTemplateArgs = new JavaTemplateArgs();

			javaTemplateArgs.setType(JavaTemplateType.MODEL);
			javaTemplateArgs.setPackageNameInfo(packageNameInfo);
			javaTemplateArgs.setClassNameInfo(classNameInfo);

			// 创建包
			IPackageFragmentRoot packageFragmentRoot = heaProjectModel
					.getJavaProject().findPackageFragmentRoot(
							new Path("/" + heaProjectModel.getName()
									+ Consistant.HEAPROJECT_JAVA));

			if (!packageFragment.exists()) {
				packageFragment = packageFragmentRoot.createPackageFragment(
						javaTemplateArgs.getPackageName(), true, null);
			}

			ModelTemplate modelTemplate = new ModelTemplate();

			String javaCode = modelTemplate.generate(new Object[] { tableName,
					module, pkgname });

			packageFragment.createCompilationUnit(
					javaTemplateArgs.getClassName() + ".java", javaCode, true,
					new NullProgressMonitor());

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * update file
	 * 
	 * @param input
	 *            input
	 * @param key
	 *            源文件
	 * @param value
	 *            修改后的
	 * @return input
	 * @throws CoreException
	 */
	public InputStream openContentStream(InputStream input, String key,
			String value) throws CoreException {

		final String newline = System.getProperty("line.separator");
		String line;
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			try {
				while ((line = reader.readLine()) != null) {
					line = line.replaceAll("\\$\\{" + key + "\\}", value);
					sb.append(line);
					sb.append(newline);
				}
			} finally {
				reader.close();
			}
		} catch (IOException ioe) {
			IStatus status = new Status(IStatus.ERROR,
					"com.hirisun.ide.plugin.wizard.projects", IStatus.OK,
					ioe.getLocalizedMessage(), null);
			throw new CoreException(status);
		}
		return new ByteArrayInputStream(sb.toString().getBytes());
	}

	/**
	 * 创建 Service Dao 内容
	 * 
	 * @param projecName
	 *            projecName
	 * @param moduleName
	 *            moduleName
	 * @return list
	 * @throws Exception
	 */
	private List<JavaTemplateArgs> createJavaTemplateArgsList(String pkgname,
			String projecName, String moduleName, boolean isdao,
			boolean isservice, boolean ismain, boolean isupdate)
			throws Exception, IndexOutOfBoundsException {
		List<JavaTemplateArgs> javaTemplateArgsList = new ArrayList<JavaTemplateArgs>();

		IPath path = heaProjectModel.getJavaProject().getPath()
				.append(Consistant.HEAPROJECT_JAVA)
				.append(StringUtils.replace("." + pkgname, ".", "/"))
				.append("/model");

		IPackageFragment packageFragment = heaProjectModel.getJavaProject()
				.findPackageFragment(path);
		// 获取包名对象
		PackageNameInfo packageNameInfo = new PackageNameInfo(packageFragment,
				moduleName);

		ICompilationUnit compilationUnit = packageFragment
				.getCompilationUnit(moduleName + ".java");
		// 遍历实体类
		ClassNameInfo classNameInfo = new ClassNameInfo(compilationUnit, moduleName);
		// 获取实体类属性

		List<EntityFieldInfo> entityFieldInfoList = null;

		try {
			entityFieldInfoList = scanEntityField(compilationUnit);
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}

		// -------------------------------Dao-------------------------------
		if (isdao) {
			JavaTemplateArgs daoTemplateArgs = new JavaTemplateArgs();
			daoTemplateArgs.setType(JavaTemplateType.DAO);
			daoTemplateArgs.setPackageNameInfo(packageNameInfo);
			daoTemplateArgs.setClassNameInfo(classNameInfo);
			daoTemplateArgs.setEntityFieldInfoList(entityFieldInfoList);
			javaTemplateArgsList.add(daoTemplateArgs);

			JavaTemplateArgs daoImplTemplateArgs = new JavaTemplateArgs();
			daoImplTemplateArgs.setType(JavaTemplateType.DAOIMPL);
			daoImplTemplateArgs.setPackageNameInfo(packageNameInfo);
			daoImplTemplateArgs.setClassNameInfo(classNameInfo);
			daoImplTemplateArgs.setEntityFieldInfoList(entityFieldInfoList);
			javaTemplateArgsList.add(daoImplTemplateArgs);
		}

		return javaTemplateArgsList;
	}

	/**
	 * 
	 * 方法说明：继承父类
	 * 
	 * @param compilationUnit
	 *            compilationUnit
	 * @return list
	 */
	@SuppressWarnings("rawtypes")
	private List<EntityFieldInfo> scanEntityField(
			ICompilationUnit compilationUnit) throws IndexOutOfBoundsException {
		List<EntityFieldInfo> entityFieldVos = new ArrayList<EntityFieldInfo>();
		// 分析实体类的字段
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(compilationUnit);// 设置资源
		parser.setResolveBindings(true);// 后面需要绑定
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		List types = cu.types();
		try {
			TypeDeclaration typeDec = (TypeDeclaration) types.get(0);

			FieldDeclaration[] fieldDecs = typeDec.getFields();

			for (FieldDeclaration fieldDec : fieldDecs) {
				EntityFieldInfo entityFieldVo = new EntityFieldInfo();
				// 字段类型
				entityFieldVo.setFieldType(fieldDec.getType().toString());
				for (Object fragment : fieldDec.fragments()) {
					VariableDeclarationFragment frag = (VariableDeclarationFragment) fragment;
					// 字段名称
					entityFieldVo.addFieldName(frag.getName().toString());
				}
				entityFieldVos.add(entityFieldVo);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}
		return entityFieldVos;
	}

	@Override
	public void setInitializationData(IConfigurationElement arg0, String arg1,
			Object arg2) throws CoreException {
	}

}
