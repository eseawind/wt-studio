package com.wt.studio.plugin.platform.preferences;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.wt.studio.plugin.platform.Activator;
import com.wt.studio.plugin.platform.dbhelp.HelpDBInit;
import com.wt.studio.plugin.platform.util.DataBaseType;

public class DbPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private Composite container;

	private Combo comDb;
	private Text db_sourcename;
	private Text db_local;
	private Text db_port;
	private Text db_name;
	private Text db_user;
	private Text db_pwd;
	private Combo db_Type;

	public DbPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("DB Preference:");
	}

	@Override
	public void init(IWorkbench arg0) {

	}

	@Override
	protected void createFieldEditors() {

		container = new Composite(this.getControl().getParent(), SWT.None);
		container.setLayout(new GridLayout(2, false));

		new Label(container, NONE).setText("已有数据源:");
		comDb = new Combo(container, SWT.READ_ONLY | SWT.NULL);

		getCombData();
		comDb.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				if (comDb.getText() != "") {
					String name = comDb.getText();
					db_sourcename.setText(HelpDBInit.getDBProfile(name)
							.getName());
					db_local.setText(HelpDBInit.getDBLocal(name));
					db_port.setText(HelpDBInit.getDBPort(name));
					db_name.setText(HelpDBInit.getDBInstance(name));
					db_user.setText(HelpDBInit.getDBUser(name));
					db_pwd.setText(HelpDBInit.getDBPass(name));
					db_Type.setText(HelpDBInit.getDBType(name));

				}
			}
		});

		Label labSourcename = new Label(container, SWT.LEFT);
		labSourcename.setText("DataSource:");
		db_sourcename = new Text(container, SWT.BORDER);
		db_sourcename.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label labType = new Label(container, SWT.LEFT);
		labType.setText("    数据库:");
		db_Type = new Combo(container, SWT.READ_ONLY | SWT.NULL);
		db_Type.add("Oracle", 0);
		db_Type.add("Mysql", 1);
		db_Type.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label labLocal = new Label(container, SWT.LEFT);
		labLocal.setText("      地址:");
		db_local = new Text(container, SWT.BORDER);
		db_local.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label labPort = new Label(container, SWT.NONE);
		labPort.setText("      端口:");
		db_port = new Text(container, SWT.BORDER);
		db_port.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label labInstance = new Label(container, SWT.NONE);
		labInstance.setText("      实例名:");
		db_name = new Text(container, SWT.BORDER);
		db_name.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label labUser = new Label(container, SWT.NONE);
		labUser.setText("      用户:");
		db_user = new Text(container, SWT.BORDER);
		db_user.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label labPwd = new Label(container, SWT.NONE);
		labPwd.setText("      密码:");
		db_pwd = new Text(container, SWT.PASSWORD);
		db_pwd.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(container, SWT.NULL);
		Button btn = new Button(container, SWT.NONE);
		btn.setText("保存");
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HelpDBInit db = new HelpDBInit();
				boolean flag = true;
				try {
					flag = db.doTestDBLink(db_Type.getText(),
							db_local.getText(), db_port.getText(),
							db_name.getText(), db_user.getText(),
							db_pwd.getText());
				} catch (Exception e1) {
					flag = false;
				}
				if (flag) {
					if (db_Type.getText().equals("Oracle")) {
						StringBuffer dburl = new StringBuffer(
								"jdbc:oracle:thin:@")
								.append(db_local.getText()).append(":")
								.append(db_port.getText()).append(":")
								.append(db_name.getText());

						String jarpath = getOracleJarLocation();

						HelpDBInit.createConnectiontProfile(
								DataBaseType.ORACLE, db_sourcename.getText(),
								"", jarpath, HelpDBInit.oracleDriverClass,
								dburl.toString(), db_user.getText(),
								db_pwd.getText());
						getCombData();
					} else if(db_Type.getText().equals("Mysql")) {
						StringBuffer dburl = new StringBuffer("jdbc:mysql://").append(db_local.getText())
								.append(":").append(db_port.getText()).append("/").append(db_name.getText()).append(HelpDBInit.MYSQL_PARAMS);
						
						String jarpath = getMysqlJarLocation();

						HelpDBInit.createConnectiontProfile(
								DataBaseType.MYSQL, db_sourcename.getText(),
								"", jarpath, HelpDBInit.mysqlDriverClass,
								dburl.toString(), db_user.getText(),
								db_pwd.getText());
						getCombData();
					}
					MessageDialog.openWarning(getShell(), "测试", "数据库测试通过！");

				} else {
					MessageDialog.openWarning(getShell(), "测试", "数据库测试失败！");
				}
			}
		});
		setControl(container);
	}

	protected void getCombData() {
		comDb.removeAll();

		for (String dbname : HelpDBInit.getDBNames()) {
			comDb.add(dbname);
		}

	}

	protected String getOracleJarLocation() {

		String result = (new StringBuilder(String.valueOf(Activator
				.getStudioInstallLocation()))).append(File.separator)
				.append("apache-tomcat-6").append(File.separator).append("lib")
				.append(File.separator).append("ojdbc5.jar").toString();

		return result;
	}
	
	protected String getMysqlJarLocation() {

		String result = (new StringBuilder(String.valueOf(Activator
				.getStudioInstallLocation()))).append(File.separator)
				.append("apache-tomcat-6").append(File.separator).append("lib")
				.append(File.separator).append("mysql-connector-java-5.1.25-bin.jar").toString();

		return result;
	}	

}
