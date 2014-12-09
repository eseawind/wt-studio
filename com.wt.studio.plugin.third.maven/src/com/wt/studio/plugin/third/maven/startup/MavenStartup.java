package com.wt.studio.plugin.third.maven.startup;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenRuntime;
import org.eclipse.m2e.core.embedder.MavenRuntimeManager;
import org.eclipse.ui.IStartup;

import com.wt.studio.plugin.third.maven.Activator;

public class MavenStartup implements IStartup, IM2EConstants {

	public static final String FIRST_MAVEN_START_PREFERENCE_NAME = "FIRST_MAVEN_START";
	public static final String MAVEN_PLUGIN_ID = "org.eclipse.m2e.core.ui";
	public static final String MAVEN_INSTALL_PREFERENCE_NAME = "MavenInstallationsPreferencePage";

	public static final String MAVEN_FOLDER_NAME = "apache-maven-3";
	public static boolean NON_BLOCKING = false;

	public File location;

	public String mavenInstallPath;
	public MavenRuntime oldDefaultRuntime;

	public MavenStartup() {
	}

	public void earlyStartup() {
		Activator.getDefault().getPreferenceStore()
				.setDefault(DONT_AUTO_CHECK, false);

		MavenRuntimeManager runtimeManager = MavenPlugin
				.getMavenRuntimeManager();
		try {
			String path;
			String installPath = Activator.getStudioInstallLocation();
			mavenInstallPath =(new StringBuilder(
					String.valueOf(installPath))).append(File.separator)
					.append(MAVEN_FOLDER_NAME).toString();
			
			location = new File(mavenInstallPath);

			path = location.getCanonicalPath();

			MavenRuntime runtime = runtimeManager.getRuntime(path);

			oldDefaultRuntime = runtimeManager.getDefaultRuntime();

			// If a runtime at the given path already exists make it the default
			if (runtime != null && runtime.isAvailable()) {
				if (!runtime.equals(oldDefaultRuntime)) {
					runtimeManager.setDefaultRuntime(runtime);
				}

			} else {
				// Create a new runtime; install it and make the default
				runtime = MavenRuntimeManager.createExternalRuntime(location
						.getCanonicalPath());
				List<MavenRuntime> runtimes = runtimeManager.getMavenRuntimes();
				runtimes.add(runtime);
				runtimeManager.setRuntimes(runtimes);
				runtimeManager.setDefaultRuntime(runtime);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean shouldPerformCheck() {
		return Activator.IS_M2ECLIPSE_PRESENT
				&& !Activator.getDefault().getPreferenceStore()
						.getBoolean(DONT_AUTO_CHECK);
	}

}
