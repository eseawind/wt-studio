package com.wt.studio.plugin.wizard.projects.framework;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.platform.Activator;
import com.wt.studio.plugin.platform.preferences.PreferenceConstants;
import com.wt.studio.plugin.wizard.projects.dbhelp.HelpDBInit;

/**
 * <pre>
 * 业务名:框架向导
 * 功能说明: 数据库信息页
 * 编写日期:	2012-12-17
 * 作者:	DongYibo
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class HeaConnectionPage extends WizardPage {

	/**
	 * container
	 */
	private Composite container;
	// 数据源
	private Combo comDb;
	// HEA 版本
	private Text combo;
	// 数据库类型
	private Text txtDbType;
	// 地址
	private Text txtLocal;
	// 端口
	private Text txtPort;
	// 数据库名
	private Text txtDbName;
	// 用户名
	private Text txtUser;
	// 密码
	private Text txtPwd;
	// 表命名空间
	private Text txtSpace;
	// 表空间Label
	private Label labSpace;

	// 复选框
	private Button btnC;
	
	

	public Button getBtnC() {
		return btnC;
	}

	public void setBtnC(Button btnC) {
		this.btnC = btnC;
	}

	/**
	 * Hea首选项
	 */
	private static IPreferenceStore preferenceStore = Activator.getDefault()
			.getPreferenceStore();

	/**
	 * 页面设置
	 */
	public HeaConnectionPage() {
		super("Other properties");
		setTitle("HEA 数据库设置");
		setDescription("基于 HEA FrameWork V2.3  初始化数据库环境");

	}

	/**
	 * 创建页面
	 * 
	 * @param parent
	 *            parent
	 */
	public void createControl(Composite parent) {

		container = new Composite(parent, SWT.None);
		container.setLayout(new GridLayout(1, false));

		Group dbConn = new Group(container, SWT.NONE);
		dbConn.setText("选择数据源");
		dbConn.setLayout(new RowLayout());
		GridData grd = new GridData();
		grd.horizontalAlignment = GridData.FILL;
		grd.grabExcessHorizontalSpace = true;
		dbConn.setLayoutData(grd);
		dbConn.setLayout(new GridLayout(2, false));

		new Label(dbConn, NONE).setText("  请选择:");
		comDb = new Combo(dbConn, SWT.READ_ONLY | SWT.NULL);
		comDb.setLayoutData(grd);

		Group gpConn = new Group(container, SWT.NONE);
		gpConn.setText("数据库信息");
		gpConn.setLayout(new RowLayout());
		GridData gdConn = new GridData();
		gdConn.horizontalAlignment = GridData.FILL;
		gdConn.grabExcessHorizontalSpace = true;

		gpConn.setLayoutData(gdConn);
		gpConn.setLayout(new GridLayout(2, false));

		Label labName = new Label(gpConn, SWT.LEFT);
		labName.setText("数据类型:");
		txtDbType = new Text(gpConn, SWT.READ_ONLY | SWT.BORDER);
		txtDbType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label labLocal = new Label(gpConn, SWT.NULL);
		labLocal.setText("主机地址:");
		txtLocal = new Text(gpConn, SWT.BORDER);
		txtLocal.setFocus();
		txtLocal.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtLocal.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				setPageComplete(false);
				btnC.setSelection(false);
				txtSpace.setText("");
				labSpace.setEnabled(false);
				txtSpace.setEnabled(false);
			}
		});

		Label labPort = new Label(gpConn, SWT.NONE);
		labPort.setText("      端口:");
		txtPort = new Text(gpConn, SWT.BORDER);
		txtPort.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtPort.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				setPageComplete(false);
				btnC.setSelection(false);
				txtSpace.setText("");
				labSpace.setEnabled(false);
				txtSpace.setEnabled(false);
			}
		});

		Label labDbName = new Label(gpConn, SWT.NULL);
		labDbName.setText("数据库名:");
		txtDbName = new Text(gpConn, SWT.BORDER);
		txtDbName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtDbName.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				setPageComplete(false);
				btnC.setSelection(false);
				txtSpace.setText("");
				labSpace.setEnabled(false);
				txtSpace.setEnabled(false);
			}
		});

		Label labUser = new Label(gpConn, SWT.NULL);
		labUser.setText("   用户名:");
		txtUser = new Text(gpConn, SWT.BORDER);
		txtUser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtUser.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				setPageComplete(false);
				btnC.setSelection(false);
				txtSpace.setText("");
				labSpace.setEnabled(false);
				txtSpace.setEnabled(false);
			}
		});

		Label labPwd = new Label(gpConn, SWT.NULL);
		labPwd.setText("      密码:");
		txtPwd = new Text(gpConn, SWT.PASSWORD | SWT.BORDER);
		txtPwd.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtPwd.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				setPageComplete(false);
				btnC.setSelection(false);
				txtSpace.setText("");
				labSpace.setEnabled(false);
				txtSpace.setEnabled(false);
			}
		});

		new Label(gpConn, SWT.NULL);
		Button btn = new Button(gpConn, SWT.NONE); // SWT.PUSH 表示按钮
		btn.setText("测试连接");
		// btn.setSelection(true);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HelpDBInit db = new HelpDBInit();
				try {
					if (db.doTestDBLink(getTxtDbType(), getTxtLocal(),
							getTxtPort(), getTxtDbName(), getTxtUser(),
							getTxtPwd())) {
						MessageDialog.openInformation(null, "消息框", "测试通过！");
						setPageComplete(true);
						btnC.setEnabled(true);
					} else {
						MessageDialog.openInformation(null, "消息框", "测试失败！");
						setPageComplete(false);
						btnC.setEnabled(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialog.openInformation(null, "消息框", "测试失败！");
					setPageComplete(false);
					btnC.setEnabled(false);
				}
			}
		});

		Group gpCreateDb = new Group(container, SWT.NONE);
		gpCreateDb.setText("数据库初始化");
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gpCreateDb.setLayoutData(gd);
		gpCreateDb.setLayout(new GridLayout(2, false));

		new Label(gpCreateDb, SWT.NULL).setText("创建数据库:");

		btnC = new Button(gpCreateDb, SWT.CHECK); // SWT.PUSH 表示按钮
		btnC.setText("(选中初始化！)");
		btnC.setEnabled(false);
		btnC.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				if (btnC.getSelection()) {
					txtSpace.setFocus();
					labSpace.setEnabled(true);
					if (txtDbType.getText().equals("Oracle")) {
						txtSpace.setEnabled(true);
						if ("".equals(txtSpace.getText())) {
							setPageComplete(false);
						}
					} else {
						txtSpace.setText("");
						txtSpace.setEnabled(false);
						setPageComplete(true);
					}

				} else {
					txtSpace.setText("");
					labSpace.setEnabled(false);
					txtSpace.setEnabled(false);
					setPageComplete(true);
				}
			}
		});

		labSpace = new Label(gpCreateDb, SWT.NONE);
		labSpace.setText("    表空间:");
		labSpace.setEnabled(false);
		txtSpace = new Text(gpCreateDb, SWT.BORDER);
		txtSpace.setEnabled(false);
		txtSpace.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtSpace.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				if (!"".equals(getTxtSpace())) {
					setPageComplete(true);
				} else {
					setPageComplete(false);
				}
			}
		});

		getCombData();
		comDb.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				if (comDb.getText() != "") {
					String name = comDb.getText();
					txtDbType.setText(HelpDBInit.getDBType(name));
					txtLocal.setText(HelpDBInit.getDBLocal(name));
					txtPort.setText(HelpDBInit.getDBPort(name));
					txtDbName.setText(HelpDBInit.getDBInstance(name));
					txtUser.setText(HelpDBInit.getDBUser(name));
					txtPwd.setText(HelpDBInit.getDBPass(name));
				}
			}
		});

		setControl(container);
		setPageComplete(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#getControl()
	 */
	public Control getControl() {
		return container;
	}

	public String getCombo() {
		return combo.getText();
	}

	public String getTxtDbType() {
		return txtDbType.getText();
	}

	public String getTxtLocal() {
		return txtLocal.getText();
	}

	public String getTxtPort() {
		return txtPort.getText();
	}

	public String getTxtDbName() {
		return txtDbName.getText();
	}

	public String getTxtUser() {
		return txtUser.getText();
	}

	public String getTxtPwd() {
		return txtPwd.getText();
	}

	public String getTxtSpace() {
		return txtSpace.getText();
	}

	protected void getCombData() {
		comDb.removeAll();
		for (String dbname : HelpDBInit.getDBNames()) {
			comDb.add(dbname);
		}
	}

}
