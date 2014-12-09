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
public class BusiModulelWizard extends HEABaseWizard {
	/**
	 * 向导页
	 */
	private BusiModelPage firstModelPage;

	private BusiDBModelPage secondModelPage;

	private BusiModelOptionPage thirdModelPage;

	@SuppressWarnings("unused")
	private ISelection selection;

	private HeaProjectModel heaProjectModel;

	private List<JavaTemplateArgs> javaTemplateArgsList;

	private ModuleModel module;

	private ModuleModel oldModule;
	private boolean bdao;
	private boolean bserver;
	private boolean bmain;
	private boolean bupdate;

	/**
	 * 构造函数
	 */
	public BusiModulelWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		this.setWindowTitle("New Busi Module Wizard");
		String pname = Activator.getProjectName(heaProjectModel.getName());
		firstModelPage = new BusiModelPage(pname);
		secondModelPage = new BusiDBModelPage(heaProjectModel);
		thirdModelPage = new BusiModelOptionPage();
		addPage(firstModelPage);
		addPage(secondModelPage);
		addPage(thirdModelPage);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.heaProjectModel = new HeaProjectModel(selection);
	}

	@Override
	public boolean canFinish() {
		boolean result = true;
		String moduleName = StringUtils.capitalize(firstModelPage.getTxtName()
				.getText());

		String tableName = secondModelPage.getComTable();
		if (moduleName == null || moduleName.equals("")) {
			result = false;
			return result;
		}

		if (tableName == null || tableName.equals("--请选择表--")) {
			result = false;
			return result;
		}

		return result;
	}

	@Override
	public boolean performFinish() {
		final String pkgname = firstModelPage.getTxtPak().getText();
		boolean result = true;
		try {
			Activator.validHEAProjectResource(heaProjectModel, null,
					pkgname);
		} catch (Exception e) {
			MessageDialog.openWarning(getShell(), "提示", "请检查项目名称存在大写或模块名称大小！");
			result = false;
		}

		if (!result) {
			return result;
		}

		String moduleName = StringUtils.capitalize(firstModelPage.getTxtName().getText());
		String moduleDesc = firstModelPage.getTxtCode().getText();
		String tableName = secondModelPage.getComTable();

		TableModel tableModel = secondModelPage.getSeletedTableModel();
		List<ColumeModel> columeModels = secondModelPage
				.getSeletedColumeModelList();

		if (columeModels == null) {
			result = true;
		}

		for (ColumeModel c : columeModels) {
			if (StringUtils.equals(c.getIsKey(), "1")) {
				result = true;
				break;
			} else {
				result = false;
			}
		}
		if (!result) {
			MessageDialog.openWarning(getShell(), "提示", "缺少主键！");
			return result;
		}

		String modelName = secondModelPage.getSeletedModelName();
		try {
			module = settingModuleModel(moduleName, moduleDesc, tableModel,
					columeModels, modelName, pkgname);
			assembleModelTemplate(pkgname, moduleName, modelName, tableName);

			boolean flag = saveModelFile(module);
			if (!flag) {
				MessageDialog.openWarning(getShell(), "提示", "Model文件保存失败");
				return flag;
			}
			bdao = thirdModelPage.getDaoBtn().getSelection();
			bserver = thirdModelPage.getServiceBtn().getSelection();
			bmain = thirdModelPage.getMainPageBtn().getSelection();
			bupdate = thirdModelPage.getUpdatePageBtn().getSelection();

			assembleHtmlTemplate(pkgname, moduleName, modelName, bmain, bupdate);
			javaTemplateArgsList = createJavaTemplateArgsList(pkgname,
					heaProjectModel.getName(), moduleName, modelName, bdao, bserver,
					bmain, bupdate);
		} catch (IndexOutOfBoundsException e) {
			MessageDialog.openWarning(getShell(), "提示",
					"Windows 文件大小写问题！请修改模块名称");
			result = false;
		} catch (Exception e) {
			MessageDialog.openWarning(getShell(), "提示",
					"Windows 文件大小写问题！请修改模块名称");
			result = false;
		}

		if (!result) {
			return result;
		}

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				monitor.beginTask("代码生成", 200);
				// 定义模板
				DaoTemplate daoTemplate = new DaoTemplate();
				DaoImplTemplate daoImplTemplate = new DaoImplTemplate();
				ServiceTemplate serviceTemplate = new ServiceTemplate();
				ServiceImplTemplate serviceImplTemplate = new ServiceImplTemplate();
				ManageClassTemplate manageClassTemplate = new ManageClassTemplate();
				UpdateClassTemplate updateClassTemplate = new UpdateClassTemplate();
				// -------------------------------------java代码生成-------------------------------------
				monitor.worked(1);
				monitor.subTask("java代码生成");
				for (JavaTemplateArgs javaTemplateArgs : javaTemplateArgsList) {
					try {

						// 生成java代码
						String javaCode = "";
						switch (javaTemplateArgs.getType()) {
						case DAO:
							javaCode = daoTemplate.generate(javaTemplateArgs);
							break;
						case DAOIMPL:
							javaCode = daoImplTemplate.generate(new Object[] {
									javaTemplateArgs, module, pkgname });
							break;
						case SERVICE:
							javaCode = serviceTemplate
									.generate(javaTemplateArgs);
							break;
						case SERVICEIMPL:
							javaCode = serviceImplTemplate
									.generate(new Object[] { javaTemplateArgs,
											module, pkgname });
							break;
						case MANAGECLASS:
							javaCode = manageClassTemplate
									.generate(new Object[] { javaTemplateArgs,
											module, pkgname });
							break;
						case UPDATECLASS:
							javaCode = updateClassTemplate
									.generate(new Object[] { javaTemplateArgs,
											module, pkgname });
							break;
						}

						// 创建包
						IPackageFragmentRoot packageFragmentRoot = heaProjectModel
								.getJavaProject().findPackageFragmentRoot(
										new Path("/"
												+ heaProjectModel.getName()
												+ Consistant.HEAPROJECT_JAVA));
						IPackageFragment packageFragment = packageFragmentRoot
								.getPackageFragment(javaTemplateArgs
										.getPackageName());
						if (!packageFragment.exists()) {
							packageFragment = packageFragmentRoot
									.createPackageFragment(
											javaTemplateArgs.getPackageName(),
											true, null);
						}

						packageFragment.createCompilationUnit(
								javaTemplateArgs.getClassName() + ".java",
								javaCode, true, new NullProgressMonitor());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (bmain) {
					// 同步资源
					String key1 = "/* INSERTSOURCE */";
					String key2 = "/*INSERTSOURCE*/";
					String moduleName = module.getModuleName();
					String className = StringUtils.capitalize(module
							.getModuleName());
					String name = StringUtils.lowerCase(moduleName);
					String keyCode = "mountPage(\"/" + name + "\"";
					String javacode = "mountPage(\"/" + name + "\", " + pkgname
							+ ".web." + name + "." + className
							+ "MainPage.class);";
					StringBuilder path = new StringBuilder(
							heaProjectModel.getSrc())
							.append("/main/java")
							.append(StringUtils
									.replace("." + pkgname, ".", "/"))
							.append("/web").append("/AppApplication.java");

					synResours(path, name, className, key1, keyCode, javacode,
							"\t\t");

					monitor.done();
				}
			}
		};

		try {
			getContainer().run(true, false, op);
			assembleModulePath(moduleName); // Call
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
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
	private void assembleModelTemplate(String pkgname, String moduleName, String modelName,
			String tableName) throws Exception {
		try {

			IPath path = heaProjectModel.getJavaProject().getPath()
					.append(Consistant.HEAPROJECT_JAVA)
					.append(StringUtils.replace("." + pkgname, ".", "/"))
					.append("/model");

			IPackageFragment packageFragment = heaProjectModel.getJavaProject()
					.findPackageFragment(path);
			// 获取包名对象
			PackageNameInfo packageNameInfo = new PackageNameInfo(
					packageFragment, moduleName);

			ICompilationUnit compilationUnit = packageFragment
					.getCompilationUnit(modelName + ".java");
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
	 * 组装模版
	 * 
	 * @param moduleName
	 *            moduleName
	 */
	private void assembleHtmlTemplate(String pkgname, String moduleName, String modelName,
			boolean ismain, boolean isupdate) {
		try {

			// 创建包
			IPackageFragmentRoot packageFragmentRoot = heaProjectModel
					.getJavaProject().findPackageFragmentRoot(
							new Path("/" + heaProjectModel.getName()
									+ Consistant.HEAPROJECT_RES));

			IPath path = heaProjectModel.getJavaProject().getPath()
					.append(Consistant.HEAPROJECT_RES)
					.append(StringUtils.replace("." + pkgname, ".", "/"))
					.append("/web");

			IPackageFragment packageFragment = heaProjectModel.getJavaProject()
					.findPackageFragment(path);

			ICompilationUnit compilationUnit = packageFragment
					.getCompilationUnit(moduleName + ".java");
			// 遍历实体类
			ClassNameInfo classNameInfo = new ClassNameInfo(compilationUnit, moduleName);

			// 获取包名对象
			PackageNameInfo packageNameInfo = new PackageNameInfo(
					packageFragment, moduleName);

			JavaTemplateArgs manageHtmlTemplateArgs = null;
			JavaTemplateArgs updateHtmlTemplateArgs = null;

			ArrayList<JavaTemplateArgs> htmlTemplatelist = new ArrayList<JavaTemplateArgs>();
			// -------------------------------manageHtml-----------------------------
			if (ismain) {
				manageHtmlTemplateArgs = new JavaTemplateArgs();
				manageHtmlTemplateArgs.setType(JavaTemplateType.MANAGEHTML);
				manageHtmlTemplateArgs.setPackageNameInfo(packageNameInfo);
				manageHtmlTemplateArgs.setClassNameInfo(classNameInfo);
				htmlTemplatelist.add(manageHtmlTemplateArgs);
			}

			// -------------------------------updateHtml-----------------------------
			if (isupdate) {
				updateHtmlTemplateArgs = new JavaTemplateArgs();
				updateHtmlTemplateArgs.setType(JavaTemplateType.UPDATEHTML);
				updateHtmlTemplateArgs.setPackageNameInfo(packageNameInfo);
				updateHtmlTemplateArgs.setClassNameInfo(classNameInfo);
				htmlTemplatelist.add(updateHtmlTemplateArgs);
			}
			ManageHtmlTemplate manageHtmlTemplate = new ManageHtmlTemplate();
			UpdateHtmlTemplate updateHtmlTemplate = new UpdateHtmlTemplate();

			String javaCode = null;
			for (JavaTemplateArgs javaTemplateArgs : htmlTemplatelist) {
				javaCode = "";
				switch (javaTemplateArgs.getType()) {
				case MANAGEHTML:
					javaCode = manageHtmlTemplate.generate(new Object[] {
							javaTemplateArgs, module });
					break;
				case UPDATEHTML:
					javaCode = updateHtmlTemplate.generate(new Object[] {
							javaTemplateArgs, module });
					break;
				}

				IPackageFragment paFragment = packageFragmentRoot
						.getPackageFragment(javaTemplateArgs.getPackageName());

				if (!paFragment.exists()) {
					paFragment = packageFragmentRoot.createPackageFragment(
							javaTemplateArgs.getPackageName(), true, null);
				}

				paFragment.createCompilationUnit(
						javaTemplateArgs.getClassName() + ".java", javaCode,
						true, new NullProgressMonitor());
			}

			// 将java文件类型，修改为html文件类型
			if (ismain) {
				String fileInManage = heaProjectModel.getResources()
						+ StringUtils.replace("." + pkgname, ".", "/")
						+ "/web/" + StringUtils.lowerCase(moduleName) + "/"
						+ manageHtmlTemplateArgs.getClassName() + ".java";
				String fileOutManage = heaProjectModel.getResources()
						+ StringUtils.replace("." + pkgname, ".", "/")
						+ "/web/" + StringUtils.lowerCase(moduleName) + "/"
						+ manageHtmlTemplateArgs.getClassName() + ".html";
				File file = new File(fileInManage);
				if (new File(fileOutManage).exists()) {
					new File(fileOutManage).delete();
				}
				file.renameTo(new File(fileOutManage));
				new File(fileInManage).delete();
			}
			if (isupdate) {
				String fileInUpdate = heaProjectModel.getResources()
						+ StringUtils.replace("." + pkgname, ".", "/")
						+ "/web/" + StringUtils.lowerCase(moduleName) + "/"
						+ updateHtmlTemplateArgs.getClassName() + ".java";
				String fileOutUpdate = heaProjectModel.getResources()
						+ StringUtils.replace("." + pkgname, ".", "/")
						+ "/web/" + StringUtils.lowerCase(moduleName) + "/"
						+ updateHtmlTemplateArgs.getClassName() + ".html";
				File file1 = new File(fileInUpdate);
				if (new File(fileInUpdate).exists()) {
					new File(fileOutUpdate).delete();
				}
				file1.renameTo(new File(fileOutUpdate));
				new File(fileInUpdate).delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理模板路径，同步Wicket路径
	 * 
	 * @param moduleName
	 */
	private void assembleModulePath(String moduleName) {

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
	 * Adds a new file to the project.
	 * 
	 * @param container
	 *            container
	 * @param path
	 *            路径
	 * @param contentStream
	 *            contentStream
	 * @param monitor
	 *            monitor
	 * @throws CoreException
	 *             CoreException
	 */
	private void addFileToProject(IContainer container, Path path,
			InputStream contentStream, IProgressMonitor monitor)
			throws CoreException {
		final IFile file = container.getFile(path);
		if (file.exists()) {
			file.setContents(contentStream, true, true, monitor);
		} else {
			file.create(contentStream, true, monitor);
		}
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
			String projecName, String moduleName, String modelName, boolean isdao,
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
				.getCompilationUnit(modelName + ".java");
		
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

		if (isservice) {
			// -------------------------------Service-------------------------------
			JavaTemplateArgs serviceTemplateArgs = new JavaTemplateArgs();
			serviceTemplateArgs.setPackageNameInfo(packageNameInfo);
			serviceTemplateArgs.setClassNameInfo(classNameInfo);
			serviceTemplateArgs.setEntityFieldInfoList(entityFieldInfoList);
			serviceTemplateArgs.setType(JavaTemplateType.SERVICE);
			javaTemplateArgsList.add(serviceTemplateArgs);

			JavaTemplateArgs serviceImplTemplateArgs = new JavaTemplateArgs();
			serviceImplTemplateArgs.setPackageNameInfo(packageNameInfo);
			serviceImplTemplateArgs.setClassNameInfo(classNameInfo);
			serviceImplTemplateArgs.setEntityFieldInfoList(entityFieldInfoList);
			serviceImplTemplateArgs.setType(JavaTemplateType.SERVICEIMPL);
			javaTemplateArgsList.add(serviceImplTemplateArgs);

		}

		if (ismain) {
			// -------------------------------WebClass-------------------------------
			JavaTemplateArgs manageClassTemplateArgs = new JavaTemplateArgs();
			manageClassTemplateArgs.setPackageNameInfo(packageNameInfo);
			manageClassTemplateArgs.setClassNameInfo(classNameInfo);
			manageClassTemplateArgs.setEntityFieldInfoList(entityFieldInfoList);
			manageClassTemplateArgs.setType(JavaTemplateType.MANAGECLASS);
			javaTemplateArgsList.add(manageClassTemplateArgs);
		}

		if (isupdate) {
			JavaTemplateArgs updateClassTemplateArgs = new JavaTemplateArgs();
			updateClassTemplateArgs.setPackageNameInfo(packageNameInfo);
			updateClassTemplateArgs.setClassNameInfo(classNameInfo);
			updateClassTemplateArgs.setEntityFieldInfoList(entityFieldInfoList);
			updateClassTemplateArgs.setType(JavaTemplateType.UPDATECLASS);
			javaTemplateArgsList.add(updateClassTemplateArgs);
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

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page instanceof BusiModelPage) {
			String mname = firstModelPage.getTxtName().getText();
			if (StringUtils.isNotEmpty(mname)) {
				if (!this.readModelFile(StringUtils.capitalize(mname))) {
					oldModule = null;
				} else {
					secondModelPage.setOldColumeModels(oldModule
							.getColumeModels());
					secondModelPage.setOldTable(oldModule.getTableModel()
							.getTableName());
				}
			}
		}

		if (page instanceof BusiDBModelPage) {
			String pkgname = firstModelPage.getTxtPak().getText();
			String mname = firstModelPage.getTxtName().getText();
			thirdModelPage
					.refresh(pkgname, heaProjectModel.getName(),
							StringUtils.lowerCase(mname),
							StringUtils.capitalize(mname));
		}
		return super.getNextPage(page);
	}

}
