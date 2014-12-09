package com.wt.studio.plugin.platform;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.wt.studio.plugin.platform.actions.MavenClasspathUpdateJob;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hirisun.ide.plugin.platform"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private static MessageConsole console;
	
	private static String STUDIO_INSTALL_LOCATION;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * 继承方法
	 * 
	 * @param context
	 *            context
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
	 * 继承方法
	 * 
	 * @param context
	 *            context
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

	/**
	 * 
	 * 方法说明：继承方法
	 * 
	 * @param msg
	 *            msg
	 */
	@SuppressWarnings("deprecation")
	static public void log(Object msg) {
		ILog log = Activator.getDefault().getLog();
		Status status = new Status(IStatus.INFO, Activator.getDefault()
				.getDescriptor().getUniqueIdentifier(), IStatus.ERROR, msg
				+ "\n", null);
		log.log(status);
	}

	/**
	 * 
	 * 方法说明：继承方法
	 * 
	 * @param ex
	 *            ex
	 */
	@SuppressWarnings("deprecation")
	static public void log(Throwable ex) {
		ILog log = Activator.getDefault().getLog();
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		String msg = stringWriter.getBuffer().toString();
		Status status = new Status(IStatus.ERROR, Activator.getDefault()
				.getDescriptor().getUniqueIdentifier(), IStatus.ERROR, msg,
				null);
		log.log(status);
	}

	private static final String M2ECLIPSE_CLASS = "org.eclipse.m2e.core.MavenPlugin";
	private static final String M2ECLIPSE_LEGACY_CLASS = "org.maven.ide.eclipse.MavenPlugin";

	public static final boolean IS_M2ECLIPSE_PRESENT = isPresent(M2ECLIPSE_CLASS);
	public static final boolean IS_LEGACY_M2ECLIPSE_PRESENT = isPresent(M2ECLIPSE_LEGACY_CLASS);
	
	public static final String THIRD_MAVEN_INSTALL_PLUGIN_ID = "com.hirisun.ide.plugin.third.maven";
	public static final String THIRD_TOMCAT_INSTALL_PLUGIN_ID = "com.hirisun.ide.plugin.third.tomcat";
	public static final String THIRD_JDBC_INSTALL_PLUGIN_ID = "com.hirisun.ide.plugin.third.jdbc";

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
			MavenClasspathUpdateJob
					.scheduleClasspathContainerUpdateJob(project);
		}
	}
	
	public static String getStudioInstallLocation() {
		if (STUDIO_INSTALL_LOCATION == null) {
			Location location = Platform.getInstallLocation();
			STUDIO_INSTALL_LOCATION = (new File(location.getURL().getPath()))
					.getParent();
		}
		return STUDIO_INSTALL_LOCATION;
	}		
	
	public static MessageConsole getConsole() {
		if (console == null) {
			console = new MessageConsole("HEA Studio Console", null);
			ConsolePlugin.getDefault().getConsoleManager()
					.addConsoles(new IConsole[] { console });
		}
		return console;
	}

}
