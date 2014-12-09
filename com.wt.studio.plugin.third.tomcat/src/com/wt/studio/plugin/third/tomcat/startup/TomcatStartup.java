package com.wt.studio.plugin.third.tomcat.startup;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jst.server.tomcat.core.internal.TomcatServer;
import org.eclipse.ui.IStartup;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.IServerType;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.internal.RuntimeWorkingCopy;
import org.eclipse.wst.server.core.internal.ServerWorkingCopy;
import org.eclipse.wst.server.ui.internal.ServerUIPreferences;

import com.wt.studio.plugin.third.tomcat.Activator;

public class TomcatStartup implements IStartup {

	public static final String FIRST_TOMCAT_START_PREFERENCE_NAME = "FIRST_TOMCAT_START";
	public static final String AS_RUNTIME_TYPE_ID = "org.eclipse.jst.server.tomcat.runtime.60";
	public static final String AS_TYPE_ID = "org.eclipse.jst.server.tomcat.60";
	public static final String AS_HOST = "localhost";
	public static final String AS_NAME = "Apache Tomcat v6.0";
	public static final String AS_DEFAULT_CONFIGURATION_NAME = "default";
	private static final String AS_FOLDER_NAME = "apache-tomcat-6";

	public TomcatStartup() {
	}

	public void earlyStartup() {
		Activator.getDefault().getPreferenceStore()
				.setDefault(FIRST_TOMCAT_START_PREFERENCE_NAME, true);
		boolean firstStart = Activator.getDefault().getPreferenceStore()
				.getBoolean(FIRST_TOMCAT_START_PREFERENCE_NAME);
		if (!firstStart)
			return;
		// 更新Tomcat 初始化
		String tomcatInstallPath;
		disableSpellCheck();
		String installPath = Activator.getStudioInstallLocation();
		tomcatInstallPath = (new StringBuilder(
				String.valueOf(installPath))).append(File.separator)
				.append(AS_FOLDER_NAME).toString();
		if (!(new File(tomcatInstallPath)).exists()) {
			return;
		}
		try {
			IRuntime runtime = createRuntime(AS_NAME, tomcatInstallPath,
					new NullProgressMonitor());
			createServer(new NullProgressMonitor(), runtime, null);

			Activator.getDefault().getPreferenceStore()
					.setValue(FIRST_TOMCAT_START_PREFERENCE_NAME, false);
		} catch (Exception e) {
		}
		
		// 更新Maven 黄精初始化
		
		
		// 更新Jira 初始化
		
		return;
	}

	private void disableSpellCheck() {
		IScopeContext context = new InstanceScope();
		IEclipsePreferences prefs = context.getNode("org.eclipse.ui.editors");
		prefs.putBoolean("spellingEnabled", false);
	}

	private static IRuntime createRuntime(String runtimeName, String location,
			IProgressMonitor progressMonitor) throws CoreException {
		IRuntimeWorkingCopy runtime = null;
		String type = null;
		String version = null;
		String runtimeId = null;
		org.eclipse.core.runtime.IPath jbossAsLocationPath = new Path(location);
		IRuntimeType runtimeTypes[] = ServerUtil.getRuntimeTypes(type, version,
				"org.eclipse.jst.server.tomcat.runtime.60");
		if (runtimeTypes.length > 0) {
			runtime = runtimeTypes[0].createRuntime(runtimeId, progressMonitor);
			runtime.setLocation(jbossAsLocationPath);
			if (runtimeName != null)
				runtime.setName(runtimeName);
			IVMInstall defaultVM = JavaRuntime.getDefaultVMInstall();
			((RuntimeWorkingCopy) runtime).setAttribute("PROPERTY_VM_ID",
					defaultVM.getId());
			((RuntimeWorkingCopy) runtime).setAttribute("PROPERTY_VM_TYPE_ID",
					defaultVM.getVMInstallType().getId());
			return runtime.save(false, progressMonitor);
		} else {
			return runtime;
		}
	}

	private static IServerWorkingCopy createServer(
			IProgressMonitor progressMonitor, IRuntime runtime, String name)
			throws CoreException {
		IServerType serverType = ServerCore
				.findServerType("org.eclipse.jst.server.tomcat.60");
		ServerWorkingCopy server = (ServerWorkingCopy) serverType.createServer(
				null, null, runtime, progressMonitor);
		server.setStartTimeout(450);
		server.setStopTimeout(150);
		server.setAutoPublishSetting(2);
		TomcatServer tomcatServer = (TomcatServer) server
				.getWorkingCopyDelegate(null);
		tomcatServer.setServeModulesWithoutPublish(true);
		tomcatServer.setSaveSeparateContextFiles(true);
		server.setHost(AS_HOST);
		if (name != null) {
			server.setName(name);
		} else {
			server.setName(AS_NAME);
		}
		ServerUIPreferences serverUIPreferences = ServerUIPreferences
				.getInstance();
		serverUIPreferences.setShowOnActivity(false);
		server.save(false, progressMonitor);
		return server;
	}

}
