package com.wt.studio.plugin.platform.startup;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class LoginPage extends WizardPage {

	/**
	 * container
	 */
	private Composite container;
	// HEA
	private Text user;
	// 数据库类型
	private Text pass;

	public Text getUser() {
		return user;
	}

	public void setUser(Text user) {
		this.user = user;
	}

	public Text getPass() {
		return pass;
	}

	public void setPass(Text pass) {
		this.pass = pass;
	}

	/**
	 * 页面设置
	 */
	public LoginPage() {
		super("Other properties");
		setTitle("公司门户账户设置");
		this.setPageComplete(false);
	}

	/**
	 * 创建页面
	 * 
	 * @param parent
	 *            parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.None);
		container.setLayout(new GridLayout(2, false));

		Label label = new Label(container, SWT.LEFT);
		label.setText("用户:");

		user = new Text(container, SWT.BORDER);
		user.setFocus();
		user.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(container, SWT.LEFT).setText("密码:");
		pass = new Text(container, SWT.BORDER | SWT.PASSWORD);
		pass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		setControl(container);
		setPageComplete(true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#getControl()
	 */
	public Control getControl() {
		return container;
	}
}
