package com.wt.studio.plugin.platform.preferences;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.hirisun.uum.api.UUM;
import com.hirisun.uum.api.UUMFactory;
import com.wt.studio.plugin.platform.Activator;
import com.wt.studio.plugin.platform.startup.StudioStartup;

/**
 * User参数设置
 */

public class UserPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private Composite container;
	private Text tuser;
	private Text tpwd;

	/**
	 * 初始化
	 */
	public UserPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("公司账户设置");
	}

	/**
	 * preferenceStore
	 */
	private static IPreferenceStore preferenceStore = Activator.getDefault()
			.getPreferenceStore();

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		container = new Composite(this.getControl().getParent(), SWT.None);
		container.setLayout(new GridLayout(2, false));
		Label labUser = new Label(container, SWT.NONE);
		labUser.setText("    用户名:");
		tuser = new Text(container, SWT.BORDER);
		tuser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label labPwd = new Label(container, SWT.NONE);
		labPwd.setText("      密码:");
		tpwd = new Text(container, SWT.PASSWORD);
		tpwd.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tuser.setText(preferenceStore.getString(PreferenceConstants.USER_NAME));
		tpwd.setText(preferenceStore.getString(PreferenceConstants.USER_PWD));

		setControl(container);
	}

	/**
	 * 继承方法
	 * 
	 * @return boolean
	 */
	public boolean performOk() {
		super.performOk();
		if (!AcceptanceUUM(tuser.getText(), tpwd.getText())) {
			MessageDialog.openInformation(getShell(), "验证", "用户名或密码不正确");
			return false;
		} else {
			Activator.getDefault().getPreferenceStore()
					.setValue(PreferenceConstants.USER_NAME, tuser.getText());
			Activator.getDefault().getPreferenceStore()
					.setValue(PreferenceConstants.USER_PWD, tpwd.getText());

			StudioStartup
					.getTaskJiraRepositorySetting(
							StudioStartup.JIRA_CONNECTOR_KIND,
							StudioStartup.REPO_HIRISUM_PM_URL)
					.setAuthenticationCredentials(
							preferenceStore
									.getString(PreferenceConstants.USER_NAME),
							preferenceStore
									.getString(PreferenceConstants.USER_PWD));

			return true;
		}
	}

	/**
	 * 验证
	 * 
	 * @param username
	 *            用户
	 * @param password
	 *            密码
	 * @return boolean
	 */
	public static boolean AcceptanceUUM(String username, String password) {
//		boolean result = false;
//		if (username == null || password == null) {
//			return result;
//		} else {
//			UUM uum = UUMFactory.getUUM();
//			if (uum != null) {
//				result = uum.user().verifyPwdById(username, password);
//			}
//		}
		return true;
	}

	/**
	 * 继承方法
	 * 
	 * @param workbench
	 *            workbench
	 */
	public void init(IWorkbench workbench) {
	}

}