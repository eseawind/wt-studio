package com.wt.studio.plugin.wizard.projects;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.wt.studio.plugin.platform.util.HeaProjectModel;
import com.wt.studio.plugin.wizard.projects.uitl.Consistant;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hirisun.ide.plugin.wizard.projects"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	public static ImageDescriptor getImage(String imagePath)
	{
		ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, imagePath);
		return descriptor;
	}	

	/**
	 * The constructor
	 */
	public Activator()
	{
	}

	/**
	 * 继承方法
	 * 
	 * @param context
	 *            context
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
	}

	/**
	 * 继承方法
	 * 
	 * @param context
	 *            context
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault()
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public static void validHEAProjectResource(HeaProjectModel heaProject,IProgressMonitor monitor, String pkgname)
			throws Exception {
		// 创建包
		IPackageFragmentRoot packageFragmentRoot;
		try {
			packageFragmentRoot = heaProject.getJavaProject()
					.findPackageFragmentRoot(
							heaProject.getJavaProject().getPath()
									.append(Consistant.HEAPROJECT_JAVA + "/"));

			packageFragmentRoot.createPackageFragment(pkgname + ".model", true,
					monitor);

			packageFragmentRoot.createPackageFragment(pkgname + ".dao", true,
					monitor);

			packageFragmentRoot.createPackageFragment(pkgname + ".dao.impl",
					true, monitor);

			packageFragmentRoot.createPackageFragment(pkgname + ".service",
					true, monitor);

			packageFragmentRoot.createPackageFragment(
					pkgname + ".service.impl", true, monitor);

			packageFragmentRoot.createPackageFragment(pkgname + ".web", true,
					monitor);

			packageFragmentRoot = heaProject.getJavaProject()
					.findPackageFragmentRoot(
							heaProject.getJavaProject().getPath()
									.append(Consistant.HEAPROJECT_RES + "/"));

			packageFragmentRoot.createPackageFragment(pkgname + ".web", true,
					monitor);

			IPath modelPath = new Path(Consistant.DESIGNER_MODEL_PATH);
			IFolder modelFolder = heaProject.getProject().getFolder(
					modelPath);

			IPath boPath = new Path(Consistant.DESIGNER_BO_PATH);
			IFolder boFolder = heaProject.getProject().getFolder(boPath);
			if (!modelFolder.exists()) {
				modelFolder.create(true, true, null);
			}
			if (!boFolder.exists()) {
				boFolder.create(true, true, null);
			}
		} catch (Exception e) {
			throw e;
		}
	}	
	
	public static String getProjectName(String name) {
		return StringUtils.replaceEach(name,
				new String[] { "-", "_", " " }, new String[] { "", "", "" });
	}
	
	/**
	 * 获取ModelName
	 * @param table
	 * @return
	 */
	public static String getTable2ModelName(String table) {
		String result = "";
		for (String s : StringUtils.replaceEach(table,
				new String[] { "-", " " }, new String[] { "", "" }).split("_")) {
			result += StringUtils.capitalize(StringUtils.lowerCase(s));

		}
		return result;
	}	
}
