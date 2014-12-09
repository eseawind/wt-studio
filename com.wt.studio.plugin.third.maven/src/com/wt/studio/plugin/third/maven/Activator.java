package com.wt.studio.plugin.third.maven;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.wt.studio.plugin.third.maven.startup.MavenClasspathUpdateJob;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hirisun.ide.plugin.third.maven"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private static String STUDIO_INSTALL_LOCATION;
	
	private static final String M2ECLIPSE_CLASS = "org.eclipse.m2e.core.MavenPlugin";
	private static final String M2ECLIPSE_LEGACY_CLASS = "org.maven.ide.eclipse.MavenPlugin";

	public static final boolean IS_M2ECLIPSE_PRESENT = isPresent(M2ECLIPSE_CLASS);
	public static final boolean IS_LEGACY_M2ECLIPSE_PRESENT = isPresent(M2ECLIPSE_LEGACY_CLASS);
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	public static String getStudioInstallLocation() {
		if (STUDIO_INSTALL_LOCATION == null) {
			Location location = Platform.getInstallLocation();
			STUDIO_INSTALL_LOCATION = (new File(location.getURL().getPath()))
					.getParent();
		}
		return STUDIO_INSTALL_LOCATION;
	}	
	
	private static boolean isPresent(String className) {
		try {
			Class.forName(className);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public void scheduleClasspathUpdateJob(IProject project) {
		if (IS_M2ECLIPSE_PRESENT) {
			MavenClasspathUpdateJob.scheduleClasspathContainerUpdateJob(project);
		}
	}	

}
