/**
 * 
 */
package com.wt.studio.plugin.platform.startup;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import com.wt.studio.plugin.platform.Activator;
import com.wt.studio.plugin.platform.preferences.PreferenceConstants;
import com.wt.studio.plugin.platform.preferences.UserPreferencePage;

public class LoginWizard extends Wizard implements INewWizard,
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

	private LoginPage loginPage;

	private String user;
	private String pass;

	/**
	 * Constructor
	 */
	public LoginWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * 添加页面
	 */
	public void addPages() {
		this.setWindowTitle("公司门户账户设置");
		loginPage = new LoginPage();
		this.addPage(loginPage);
	}

	@Override
	public boolean canFinish() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		user = loginPage.getUser().getText();
		pass = loginPage.getPass().getText();

		if (!UserPreferencePage.AcceptanceUUM(user, pass)) {
			return false;
		}

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				monitor.beginTask("保存账户信息", 500);

				Activator.getDefault().getPreferenceStore()
						.setValue(PreferenceConstants.USER_NAME, user);
				Activator.getDefault().getPreferenceStore()
						.setValue(PreferenceConstants.USER_PWD, pass);

				IPreferenceStore ps = (IPreferenceStore) Activator.getDefault()
						.getPreferenceStore();

				ps.needsSaving();

				monitor.beginTask("同步账户信息", 500);

				StudioStartup.getTaskJiraRepositorySetting(
						StudioStartup.JIRA_CONNECTOR_KIND,
						StudioStartup.REPO_HIRISUM_PM_URL);

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

		return true;
	}

	@Override
	public boolean performCancel() {
		return false;
	}

	/**
	 * 继承方法
	 * 
	 * @param workbench
	 *            workbench
	 * @param selection
	 *            selection
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
	}

	/**
	 * 继承方法
	 * 
	 * @param config
	 *            config
	 * @param propertyName
	 *            propertyName
	 * @param data
	 *            data
	 */
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		this.config = config;
	}

}
