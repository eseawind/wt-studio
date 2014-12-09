package com.wt.studio.plugin.platform.startup;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.TasksUi;
import org.eclipse.ui.IStartup;

import com.wt.studio.plugin.platform.Activator;
import com.wt.studio.plugin.platform.preferences.PreferenceConstants;

public class StudioStartup implements IStartup {

	public static final String FIRST_START_PREFERENCE_NAME = "FIRST_STUDIO_START";
	public static final String JIRA_CONNECTOR_KIND = "jira";
	public static final String REPO_HIRISUM_NAME = "Hirisun PM";
	public static final String REPO_HIRISUM_PM_URL = "http://pm.hirisun.com";


	public StudioStartup() {

	}

	public void earlyStartup() {
		Activator.getDefault().getPreferenceStore()
				.setDefault(FIRST_START_PREFERENCE_NAME, true);
		// boolean firstStart = Activator.getDefault().getPreferenceStore()
		// .getBoolean(FIRST_START_PREFERENCE_NAME);
		// if (!firstStart)
		// return;

		String user = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.USER_NAME);
		String pass = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.USER_PWD);

		if (!user.equals("") && !pass.equals("")) {

			getTaskJiraRepositorySetting(JIRA_CONNECTOR_KIND,
					REPO_HIRISUM_PM_URL);
			getSvnRepositorySetting();
		}
		return;
	}

	public static TaskRepository getTaskJiraRepositorySetting(
			String connectorKind, String repositoryUrl) {
		TaskRepository taskRepository = TasksUi.getRepositoryManager()
				.getRepository(connectorKind, repositoryUrl);
		if (taskRepository == null) {
			taskRepository = new TaskRepository(connectorKind, repositoryUrl);
			taskRepository.setRepositoryLabel(REPO_HIRISUM_NAME);
			taskRepository.setBugRepository(true);
			taskRepository.setAuthenticationCredentials(
					Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.USER_NAME),
					Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.USER_PWD));

			TasksUi.getRepositoryManager().addRepository(taskRepository);
		}
		return taskRepository;
	}

	public static void getSvnRepositorySetting() {
	}

	private void disableSpellCheck() {
		IScopeContext context = new InstanceScope();
		IEclipsePreferences prefs = context.getNode("org.eclipse.ui.editors");
		prefs.putBoolean("spellingEnabled", false);
	}

}
