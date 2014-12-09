package com.wt.studio.plugin.wizard.projects.services;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;

import com.wt.studio.plugin.wizard.projects.model.util.ClassNameInfo;
import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs;
import com.wt.studio.plugin.wizard.projects.model.util.PackageNameInfo;
import com.wt.studio.plugin.wizard.projects.model.util.JavaTemplateArgs.JavaTemplateType;
import com.wt.studio.plugin.wizard.projects.services.templates.BO2ModelTemplate;
import com.wt.studio.plugin.wizard.projects.services.templates.BO2RestServiceTemplate;
import com.wt.studio.plugin.wizard.projects.services.templates.Mobile2HTML5Template;
import com.wt.studio.plugin.wizard.projects.services.util.BOModel;
import com.wt.studio.plugin.wizard.projects.services.util.HTML5Model;
import com.wt.studio.plugin.wizard.projects.services.util.ServiceModuleModel;
import com.wt.studio.plugin.wizard.projects.services.util.WSDLServiceModel;
import com.wt.studio.plugin.wizard.projects.uitl.Consistant;
import com.wt.studio.plugin.wizard.projects.uitl.HEABaseWizard;

public class ServiceWizard extends HEABaseWizard {
	/**
	 * workbench
	 */
	private IWorkbench workbench;

	/**
	 * config
	 */
	private IConfigurationElement config;

	private ModuleServicePage modulePage;
	private AssembleModelPage modelPage;
	private AssembleServicePage servicePage;
	
	private List<JavaTemplateArgs> javaTemplateArgsList;
	private ServiceModuleModel mobileModule = new ServiceModuleModel();

	@Override
	public void addPages() {
		this.setWindowTitle("New WebService Wizard");
		modulePage = new ModuleServicePage(); 
		modelPage = new AssembleModelPage();
		servicePage = new AssembleServicePage();
		addPage(modulePage);
		addPage(modelPage);
		addPage(servicePage);
	}
	
	

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		
		if(page instanceof AssembleModelPage) {
			AssembleServicePage nextpage = (AssembleServicePage)super.getNextPage(page);
			AssembleModelPage pg = (AssembleModelPage)page;
			Tree source =  pg.getModelTree();
			Tree target = nextpage.getModelTree();
			
			if( source.getItems().length == 0 ) {
				//do nothing
			} else {
				target.removeAll();
				assembleBoTree(source, target);
			};
			
			return nextpage;			
		}
		
		return super.getNextPage(page);
	}
	
	private void assembleBoTree(Tree source, Tree target) {
		TreeItem flag = null; 
		int i =0;
		for(TreeItem item :source.getItems()) {
			flag = new TreeItem(target,i);
			flag.setExpanded(true);
			flag.setText(0, item.getText(0));
			flag.setText(1, item.getText(1));
			getChileTreeItem(flag, item);
			i++;
		}
	}
	
	private void getChileTreeItem(TreeItem flag, TreeItem item) {
		TreeItem flagChild = null; 
		int i = 0;
		for(TreeItem child :item.getItems()) {
			flagChild = new TreeItem(flag,i);
			flag.setExpanded(true);
			flagChild.setText(0, child.getText(0));
			flagChild.setText(1, child.getText(1));
			i++;
			getChileTreeItem(flagChild, child);
		}
	}



	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		if(page instanceof AssembleServicePage) {
			
		}
		return super.getPreviousPage(page);
	}



	@Override
	public void setInitializationData(IConfigurationElement config,
			String arg1, Object arg2) throws CoreException {
		this.config = config;
	}

	@Override
	public boolean canFinish() {
		return true;
	}

	@Override
	public boolean performFinish() {
		final String moduleName = StringUtils.capitalize(modulePage.getMname().getText());
		final String pname = getHeaProjectModel().getName();
		final String mname = StringUtils.lowerCase(moduleName);
		final String psrc = this.getHeaProjectModel().getSrc();
		WSDLServiceModel wsdlModel = getWSDLModel(servicePage
				.getServiceTree());
		HTML5Model html5Model = null;
		BOModel boModel = null;
		
		html5Model = getHTML5Model(servicePage.getHtml5Tree(), servicePage.getHtml5Tree().getItem(0),0, pname, mname);
		boModel = getBOModel(servicePage.getModelTree(), servicePage.getModelTree().getItem(0),0, pname, mname);
		assembleMobileModule(mobileModule, moduleName, wsdlModel, html5Model, boModel);
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				monitor.beginTask("代码生成", 200);
				try {
					monitor.worked(100);
					monitor.subTask("Java代码生成");
					assembleBOModelTemplate(moduleName);
					assembleM2RestServiceTemplate(moduleName);
					monitor.worked(200);
					monitor.subTask("HTML5代码生成");
					assembleHtml5Template(moduleName);
					monitor.subTask("REST XML代码更新");
					assembleRestXMLDoc(moduleName);

					String key1 = "<!--INSERTREF -->";
					String key2 = "<!--INSERTBEAN -->";
					String name = StringUtils.lowerCase(moduleName);
					String className =  StringUtils.capitalize(moduleName);
					String xmlRefCode = "<ref bean=\"" + name +"Rest\"/>";
					String xmlBeancode = "<bean id=\"" + name + "Rest\" class=\"com.hirisun." + pname 
							+ ".rest." + className + "RestService\"></bean>";
					String keyCode1 = "<ref bean=\"" + name +"Rest\"";
					String keyCode2 = "<bean id=\"" + name + "Rest\"";

					StringBuilder path = new StringBuilder(psrc).append("/main/resources/com/hirisun/")
							.append(pname).append("/config").append("/applicationContext-rest.xml");
					
					synResours(path, name, className, key1, keyCode1, xmlRefCode, "\t\t\t\t");
					synResours(path, name, className, key2, keyCode2, xmlBeancode, "\t");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				monitor.done();
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

		/**
		 * 刷新工程
		 */
		this.getHeaProjectModel().refresh();
		return true;
	}
	


	protected void assembleBOModelTemplate(String moduleName) throws Exception {
		try {

			IPath path = getHeaProjectModel().getJavaProject().getPath()
					.append(Consistant.HEAPROJECT_JAVA + Consistant.COMP_PRIX_PACKAGE)
					.append(getHeaProjectModel().getName()).append("/bo");

			IPackageFragment packageFragment = getHeaProjectModel()
					.getJavaProject().findPackageFragment(path);
			// 获取包名对象
			PackageNameInfo packageNameInfo = new PackageNameInfo(
					packageFragment, moduleName);

			ICompilationUnit compilationUnit = packageFragment
					.getCompilationUnit(moduleName + Consistant.JAVA_POSTFIX);
			// 遍历实体类
			ClassNameInfo classNameInfo = new ClassNameInfo(compilationUnit, moduleName);

			// -------------------------------Model-----------------------------
			JavaTemplateArgs javaTemplateArgs = new JavaTemplateArgs();

			javaTemplateArgs.setType(JavaTemplateType.BOModel);
			javaTemplateArgs.setPackageNameInfo(packageNameInfo);
			javaTemplateArgs.setClassNameInfo(classNameInfo);

			// 创建包
			IPackageFragmentRoot packageFragmentRoot = getHeaProjectModel()
					.getJavaProject().findPackageFragmentRoot(
							new Path("/" + getHeaProjectModel().getName()
									+ Consistant.HEAPROJECT_JAVA));

			if (!packageFragment.exists()) {
				packageFragment = packageFragmentRoot.createPackageFragment(
						javaTemplateArgs.getPackageName(), true, null);
			}

			BO2ModelTemplate modelTemplate = new BO2ModelTemplate();

			String javaCode = modelTemplate.generate(new Object[] {
					javaTemplateArgs, mobileModule });

			packageFragment.createCompilationUnit(
					javaTemplateArgs.getClassName() + Consistant.JAVA_POSTFIX, javaCode, true,
					new NullProgressMonitor());
			
		} catch (Exception e) {
			throw e;
		}
		
	}



	private HTML5Model getHTML5Model(final Tree tree, TreeItem treeItem, int count, String pname, String mname) {
		HTML5Model model = new HTML5Model();
		model.setPname(pname);
		model.setMname(mname);
		model.setId(new Integer(count).toString());
		model.setLayout(treeItem.getText(0));
		model.setFielddesc(treeItem.getText(1));
		model.setFieldControl(treeItem.getText(2));
		model.setFieldDateSrouce(treeItem.getText(3));
		model.setType(treeItem.getText(0));
		List<HTML5Model> listChilds = new ArrayList<HTML5Model>();
		for(int i = 0; i< treeItem.getItemCount(); i++) {
			count++;
			HTML5Model child = getHTML5Model(tree, treeItem.getItem(i), count, pname, mname);
			listChilds.add(child);
		}
		model.setModels(listChilds);
		return model;
	}
	
	private BOModel getBOModel(final Tree tree, TreeItem treeItem, int count, String pname, String mname) {
		BOModel model = new BOModel();
		model.setBontype(treeItem.getText(0));
		model.setBoname(treeItem.getText(1));
		model.setType(treeItem.getText(0));
		List<BOModel> listChilds = new ArrayList<BOModel>();
		for(int i = 0; i< treeItem.getItemCount(); i++) {
			count++;
			BOModel child = getBOModel(tree, treeItem.getItem(i), count, pname, mname);
			listChilds.add(child);
		}
		model.setModels(listChilds);
		return model;
	}	
	
	

	private WSDLServiceModel getWSDLModel(Tree tree) {
		return null;
	}

	/**
	 * 组装MobileModule
	 * 
	 * @param mobileModule
	 * @param moduleName
	 * @param modulePage
	 * @param servicePage
	 */
	private void assembleMobileModule(ServiceModuleModel mobileModule,
			String moduleName, WSDLServiceModel wsdlModel, HTML5Model html5Model, BOModel boModel) {
		mobileModule.setMobileModuleName(moduleName);
		mobileModule.setWsdlServiceModel(wsdlModel);
		mobileModule.setHtml5model(html5Model);
		mobileModule.setBoModel(boModel);
	}

	/**
	 * 组装RestService
	 * 
	 * @param moduleName
	 * @throws Exception
	 */
	private void assembleM2RestServiceTemplate(String moduleName)
			throws Exception {
		try {

			IPath path = getHeaProjectModel().getJavaProject().getPath()
					.append(Consistant.HEAPROJECT_JAVA + Consistant.COMP_PRIX_PACKAGE)
					.append(getHeaProjectModel().getName()).append("/rest");

			IPackageFragment packageFragment = getHeaProjectModel()
					.getJavaProject().findPackageFragment(path);
			// 获取包名对象
			PackageNameInfo packageNameInfo = new PackageNameInfo(
					packageFragment, moduleName);

			ICompilationUnit compilationUnit = packageFragment
					.getCompilationUnit(moduleName + Consistant.JAVA_POSTFIX);
			// 遍历实体类
			ClassNameInfo classNameInfo = new ClassNameInfo(compilationUnit, moduleName);

			// -------------------------------Model-----------------------------
			JavaTemplateArgs javaTemplateArgs = new JavaTemplateArgs();

			javaTemplateArgs.setType(JavaTemplateType.M2RestService);
			javaTemplateArgs.setPackageNameInfo(packageNameInfo);
			javaTemplateArgs.setClassNameInfo(classNameInfo);

			// 创建包
			IPackageFragmentRoot packageFragmentRoot = getHeaProjectModel()
					.getJavaProject().findPackageFragmentRoot(
							new Path("/" + getHeaProjectModel().getName()
									+ Consistant.HEAPROJECT_JAVA));

			if (!packageFragment.exists()) {
				packageFragment = packageFragmentRoot.createPackageFragment(
						javaTemplateArgs.getPackageName(), true, null);
			}

			BO2RestServiceTemplate restServiceTemplate = new BO2RestServiceTemplate();

			String javaCode = restServiceTemplate.generate(new Object[] {
					javaTemplateArgs, mobileModule });

			packageFragment.createCompilationUnit(
					javaTemplateArgs.getClassName() + Consistant.JAVA_POSTFIX, javaCode, true,
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
	private void assembleHtml5Template(String moduleName) {
		try {
			String webappPath = "/src/main/webapp/mapp/";

			IPath path = getHeaProjectModel().getJavaProject().getPath()
					.append(webappPath);

			ArrayList<JavaTemplateArgs> m2html5Templatelist = new ArrayList<JavaTemplateArgs>();

			JavaTemplateArgs manageHtmlTemplateArgs = new JavaTemplateArgs();
			manageHtmlTemplateArgs.setType(JavaTemplateType.M2HTML5);
			m2html5Templatelist.add(manageHtmlTemplateArgs);

			Mobile2HTML5Template m2Html5Template = new Mobile2HTML5Template();

			String javaCode = null;
			for (JavaTemplateArgs javaTemplateArgs : m2html5Templatelist) {
				javaCode = "";
				switch (javaTemplateArgs.getType()) {
				case M2HTML5:
					javaCode = m2Html5Template.generate(new Object[] {
							manageHtmlTemplateArgs, mobileModule });
					break;
				}
			}

			String fileOutManage = this.getHeaProjectModel().getRoot()
					+ webappPath + "m/";
			String fileName = StringUtils.lowerCase(moduleName) + ".html";
			FileUtils.forceMkdir(new File(fileOutManage));
			File file = new File(fileOutManage + fileName);
			if (new File(fileOutManage).exists()) {
				new File(fileOutManage).deleteOnExit();
			}

			file.createNewFile();
			FileWriter os = new FileWriter(file);
			os.write(javaCode);
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void assembleRestXMLDoc(String moduleName) {
		IPath path = getHeaProjectModel().getJavaProject().getPath()
				.append("/src/main/resources/com/hirisun/")
				.append(getHeaProjectModel().getName()).append("/config");
		
	}
}
