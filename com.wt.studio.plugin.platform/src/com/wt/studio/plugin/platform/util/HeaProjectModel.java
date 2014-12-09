package com.wt.studio.plugin.platform.util;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * 
 * <pre>
 * 业务名:加载路径
 * 功能说明: 加载路径
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class HeaProjectModel {
	private String name;
	private String root;
	private String src;
	private String webapp;
	private String resources;
	private String build;
	private String lib;
	private String dataSource;
	private IProject project;
	private IJavaProject javaProject;
	private IClasspathEntry[] libPath;

	/**
	 * 构造方法
	 * 
	 * @param selection
	 *            selection
	 */
	public HeaProjectModel(ISelection selection) {
		// 取得项目根目录
		try {
			// 取得项目根目录
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			if (obj instanceof Project || obj instanceof IJavaProject) { 
				if (obj instanceof IJavaProject) {
					IJavaElement t = (IJavaElement) obj;
					javaProject = t.getJavaProject();
					project = t.getResource().getProject();
					libPath = javaProject.getRawClasspath();
				} else {
					project = (Project) obj;
				}

				// 项目名称
				name = project.getName();
				root = project.getLocation().toString();
				// 取得源目录
				src = root + "/src";
				dataSource = src
						+ "/main/resources/com/hirisun/hea/common/config/applicationContext-dataSource.xml";
				webapp = root + "/src/main/webapp";
				resources = root + "/src/main/resources";
				build = root + "/target";

			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	public IClasspathEntry[] getLibPath() {
		return libPath;
	}

	public void setLibPath(IClasspathEntry[] libPath) {
		this.libPath = libPath;
	}

	/**
	 * 刷新工程
	 */
	public void refresh() {
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getWebapp() {
		return webapp;
	}

	public void setWebapp(String webapp) {
		this.webapp = webapp;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getLib() {
		return lib;
	}

	public void setLib(String lib) {
		this.lib = lib;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IJavaProject getJavaProject() {
		return javaProject;
	}

	public void setJavaProject(IJavaProject javaProject) {
		this.javaProject = javaProject;
	}

	public String getWebRootName() {
		return "";
	}
}
