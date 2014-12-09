/**
 * 
 */
package com.wt.studio.plugin.wizard.projects.business;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import com.wt.studio.plugin.wizard.projects.dbhelp.HelpDBInit;
import com.wt.studio.plugin.wizard.projects.utilpage.UumPreferencesPage;

/**
 * <pre>
 * 业务名:业务向导
 * 功能说明: 首页
 * 编写日期:	2012-12-17
 * 作者:DongYibo
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class BusinessPorjectWizard extends Wizard implements INewWizard,
		IExecutableExtension {
	/**
	 * 向导页
	 */
	private WizardNewProjectCreationPage wizardPage;

	/**
	 * config
	 */
	private IConfigurationElement config;

	/**
	 * workbench
	 */
	private IWorkbench workbench;

	/**
	 * java project
	 */
	private IProject project;

	/**
	 * uumPreferencesPage
	 */
	private UumPreferencesPage uumPreferencesPage;

	/**
	 * uumConnectionPage
	 */
	private BusinessConnectionPage businessConnectionPage;
	
	String domain;
	String mode;
	String accessKey;
	String expiredTime;	

	/**
	 * 构造函数
	 */
	public BusinessPorjectWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * 添加向导
	 */
	public void addPages() {
		this.setWindowTitle("New Hea Business Project");
		wizardPage = new WizardNewProjectCreationPage(
				"NewExampleComSiteProject");
		wizardPage.setTitle("Create a HEA Business Project");
		wizardPage.setDescription("基于 HEA Business V2.3 创建 HEA 业务项目");
		// wizardPage.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
		// "icons/banner_ChangeVision1.png"));
		this.addPage(wizardPage);

		businessConnectionPage = new BusinessConnectionPage();
		this.addPage(businessConnectionPage);
		uumPreferencesPage = new UumPreferencesPage("hirisun.com","hirisunpass","0","360000");
		this.addPage(uumPreferencesPage);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;

	}

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		this.config = config;
	}

	@Override
	public boolean performFinish() {
		if (project != null) {
			return true;
		}

		final String projectName = wizardPage.getProjectName();

		if (projectName.contains(".")) {
			MessageDialog.openWarning(getShell(), "提示", "项目名称含有特殊字符  . < >等");
			return false;
		}
		
		domain = this.uumPreferencesPage.getSdomain();
		mode = this.uumPreferencesPage.getSmode();
		accessKey = this.uumPreferencesPage.getSaccesskey();
		expiredTime = this.uumPreferencesPage.getSexpiredTime();		

		final IProject projectHandle = wizardPage.getProjectHandle();
		final String uumrest = uumPreferencesPage.getUumaddress();
		final String ip = businessConnectionPage.getTxtLocal();
		final String port = businessConnectionPage.getTxtPort();
		final String dbName = businessConnectionPage.getTxtDbName();
		final String userName = businessConnectionPage.getTxtUser();
		final String userPwd = businessConnectionPage.getTxtPwd();
		final String type = businessConnectionPage.getTxtDbType();

		URI projectURI = (!wizardPage.useDefaults()) ? wizardPage
				.getLocationURI() : null;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IProjectDescription desc = workspace
				.newProjectDescription(projectHandle.getName());
		desc.setLocationURI(projectURI);
		/*
		 * Just like the NewFileWizard, but this time with an operation object
		 * that modifies workspaces.
		 */
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			protected void execute(IProgressMonitor monitor)
					throws CoreException {
				createProject(desc, projectHandle, monitor, uumrest,
						projectName, ip, port, dbName, userName, userPwd, type);

			}
		};

		/*
		 * This isn't as robust as the code in the BasicNewProjectResourceWizard
		 * class. Consider beefing this up to improve error handling.
		 */
		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error",
					realException.getMessage());
			return false;
		}

		project = projectHandle;

		if (project == null) {
			return false;
		}

		BasicNewProjectResourceWizard.updatePerspective(config);
		BasicNewProjectResourceWizard.selectAndReveal(project,
				workbench.getActiveWorkbenchWindow());

		// 更新Jar
		com.wt.studio.plugin.platform.Activator.getDefault()
				.scheduleClasspathUpdateJob(project);

		return true;
	}

	/**
	 * createProject This creates the project in the workspace.
	 * 
	 * @param description
	 *            description
	 * @param proj
	 *            proj
	 * @param monitor
	 *            monitor
	 * @param uumrest
	 *            uum地址
	 * @param projectName
	 *            工程名字
	 * @param ip
	 *            ip地址
	 * @param port
	 *            端口
	 * @param dbName
	 *            数据库名
	 * @param userName
	 *            数据库用户名
	 * @param userPwd
	 *            数据库密码
	 * @throws CoreException
	 * @throws OperationCanceledException
	 */
	void createProject(IProjectDescription description, IProject proj,
			IProgressMonitor monitor, String uumrest, String projectName,
			String ip, String port, String dbName, String userName,
			String userPwd, String type) throws CoreException, OperationCanceledException {
		try {

			monitor.beginTask("", 2000);

			proj.create(description, new SubProgressMonitor(monitor, 1000));

			if (monitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			proj.open(IResource.BACKGROUND_REFRESH, new SubProgressMonitor(
					monitor, 1000));
			IContainer container = (IContainer) proj;
			// 创建src
			final IFolder src = container.getFolder(new Path("src"));
			src.create(true, true, monitor);

			// 添加文件
			InputStream input = this.getClass().getResourceAsStream(
					"templates/sources.properties");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			String line;
			InputStream resourceStreamfiles = null;
			try {
				while ((line = reader.readLine()) != null) {
					if (!line.isEmpty() && !line.trim().equals("")) {
						String filename = line.trim();
						// 源文件操作
						String sourceFileName = filename;
						String sourceName = sourceFileName.replaceAll(
								src.getName() + "/", "");
						String sourceFName = sourceName.trim();
						if (sourceFileName.endsWith(".java")) {// 如果是java文件则修改文件格式
							sourceFName = sourceFName + "-resource";
						}

						// 修改工程名
						if (filename.contains("rpt")) {
							filename = filename.replaceAll("rpt",
									projectName.toLowerCase());
						}
						String forder = filename.substring(0,
								filename.lastIndexOf("/") + 1);// 路径
						String name = filename.replaceAll(src.getName() + "/",
								"");// 文件名
						String fname = name.trim();
						if (!forder.equals("")) {// 创建路径
							createsubforder(container, monitor, forder);
						}
						if (!fname.equals("")) {// 创建文件
							resourceStreamfiles = this.getClass()
									.getResourceAsStream(
											"templates/" + sourceFName);
							if (resourceStreamfiles != null) {
								// 修改文件
								// uum-rest-client
								if (fname.indexOf("uum-rest-client.properties") > -1) {
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "serverurl",
											uumrest);
								}
								// 数据源
								if (fname.indexOf("applicationContext-dataSource.xml") > -1) {
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "userName",
											userName);
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "userPwd",
											userPwd);
									
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "driverClass", HelpDBInit.getDriverClass(type));
									
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "jdbcUrl", HelpDBInit.getDBURL(type, ip, port, dbName).toString());
								}
								if (fname.indexOf("AppApplication.java") > -1) {
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "projectName",
											projectName.toLowerCase());
								}
								if (fname.indexOf("AppRestService.java") > -1) {
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "projectName",
											projectName.toLowerCase());
								}
								if (fname.indexOf("applicationContext-hibernate.xml") > -1) {
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "projectName",
											projectName.toLowerCase());
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "hibernateDialect",
											HelpDBInit.getHiberNateDialect(type));
								}
								if (fname
										.indexOf("applicationContext-rest.xml") > -1) {
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "projectName",
											projectName.toLowerCase());
								}
								if (fname.indexOf("pom.xml") > -1) {
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "projectName",
											projectName.toLowerCase());
								}
								if (fname.indexOf("web.xml") > -1) {
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "projectName",
											projectName.toLowerCase());
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "domain",
											domain);
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "mode",
											mode);
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "accessKey",
											accessKey);
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "expiredTime",
											expiredTime);
								}
								if (fname.indexOf("weblogic.xml") > -1) {
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "projectName",
											projectName.toLowerCase());
									resourceStreamfiles = openContentStream(
											resourceStreamfiles, "cookName",
											projectName.toUpperCase());
								}
								addFileToProject(container, new Path(filename),
										resourceStreamfiles, monitor);
							}
						}
					}
				}

			} finally {
				reader.close();
			}
			if (resourceStreamfiles != null) {
				resourceStreamfiles.close();
			}

		} catch (Exception ioe) {
			IStatus status = new Status(IStatus.ERROR,
					"com.hirisun.ide.plugin.wizard.projects", IStatus.OK,
					ioe.getLocalizedMessage(), null);
			throw new CoreException(status);
		} finally {
			monitor.done();
		}
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
		if (path.toString().contains("settings")) {
			String s = path.toString().replaceAll("settings", ".settings");
			path = new Path(s);
		}
		final IFile file = container.getFile(path);
		if (file.exists()) {
			file.setContents(contentStream, true, true, monitor);
		} else {
			file.create(contentStream, true, monitor);
		}
	}

	/**
	 * create forders
	 * 
	 * @param container
	 *            container
	 * @param monitor
	 *            monitor
	 * @param path
	 *            path
	 */
	public void createsubforder(IContainer container, IProgressMonitor monitor,
			String path) {
		StringTokenizer st = new StringTokenizer(path, "/");
		String path1 = st.nextToken() + "/";
		String path2 = path1;
		while (st.hasMoreTokens()) {
			path1 = st.nextToken() + "/";
			path2 += path1;
			IFolder folder = container.getFolder(new Path(path2));
			if (!folder.exists()) {
				try {
					folder.create(true, true, monitor);
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
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

		/* We want to be truly OS-agnostic */
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

	@Override
	public boolean canFinish() {
		if (this.getContainer().getCurrentPage() != uumPreferencesPage)
			return false;
		return super.canFinish();
	}
}
