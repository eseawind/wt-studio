package com.wt.studio.plugin.querydesigner.wizard.page;

import java.sql.SQLException;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.model.TableSqlAreaModel;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;
import com.wt.studio.plugin.querydesigner.wizard.CreateDataObjectWizard;

public class CreateTableSqlPageOne extends WizardPage
{
	private IConnectionProfile connectionProfile;
	private Text nameText;
	private Text sqlText;

	public CreateTableSqlPageOne(String pageName, String title, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
	}

	@Override
	public void createControl(Composite parent)
	{

		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		composite.setLayout(gridLayout);

		Group group = new Group(composite, SWT.NULL);
		FillLayout fillLayout = new FillLayout();
		fillLayout.spacing = 5;
		group.setLayout(new FillLayout());
		group.setText("名称");
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		group.setLayoutData(gridData);
		nameText = new Text(group, SWT.SINGLE);

		Composite sqlArea = new Composite(composite, SWT.NULL);
		GridLayout sqlLayout = new GridLayout();
		sqlLayout.marginLeft = 0;
		sqlLayout.numColumns = 2;
		sqlArea.setLayout(sqlLayout);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		sqlArea.setLayoutData(gridData);
		group = new Group(sqlArea, SWT.NULL);
		group.setLayout(new FillLayout());
		group.setText("SQL");

		group.setLayoutData(gridData);

		sqlText = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.H_SCROLL);

		group = new Group(sqlArea, SWT.NULL);
		group.setLayout(new GridLayout());
		group.setText("Option");
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		group.setLayoutData(gridData);
		GridData buttonData = new GridData();
		buttonData.widthHint = 120;
		final Button userId = new Button(group, SWT.NULL);
		userId.setText("loginUserId");
		userId.setLayoutData(buttonData);
		final Button uuid = new Button(group, SWT.NULL);
		uuid.setText("loginUserUuid");
		uuid.setLayoutData(buttonData);
		final Button deptId = new Button(group, SWT.NULL);
		deptId.setText("loginUserDeptId");
		deptId.setLayoutData(buttonData);
		final Button deptUuid = new Button(group, SWT.NULL);
		deptUuid.setText("loginUserDeptUuid");
		deptUuid.setLayoutData(buttonData);
		final Button orgId = new Button(group, SWT.NULL);
		orgId.setText("loginUserOrgId");
		orgId.setLayoutData(buttonData);
		final Button orgUuid = new Button(group, SWT.NULL);
		orgUuid.setText("loginUserOrgUuid");
		orgUuid.setLayoutData(buttonData);

		SelectionListener buttonListener = new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				// TODO Auto-generated method stub
				Widget button = e.widget;
				String sql = sqlText.getText();
				if (button == userId) {
					sqlText.setText(sql + " '$LOGINUSERID$'");
				} else if (button == uuid) {
					sqlText.setText(sql + " '$LOGINUSERUUID$'");
				} else if (button == deptId) {
					sqlText.setText(sql + " '$LOGINUSER_DEPTID$'");
				} else if (button == deptUuid) {
					sqlText.setText(sql + " '$LOGINUSER_DEPTUUID$'");
				} else if (button == orgId) {
					sqlText.setText(sql + " '$LOGINUSER_ORGID$'");
				} else if (button == orgUuid) {
					sqlText.setText(sql + " '$LOGINUSER_ORGUUID$'");
				}

			}

		};
		userId.addSelectionListener(buttonListener);
		uuid.addSelectionListener(buttonListener);
		deptId.addSelectionListener(buttonListener);
		deptUuid.addSelectionListener(buttonListener);
		orgId.addSelectionListener(buttonListener);
		orgUuid.addSelectionListener(buttonListener);
		Composite btnComposite = new Composite(composite, SWT.NULL);
		fillLayout = new FillLayout();
		btnComposite.setLayout(fillLayout);
		Button testBtn = new Button(btnComposite, SWT.NULL);
		testBtn.setText("测试语句");
		testBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String name = nameText.getText();
				if (name.equals("")) {
					CreateTableSqlPageOne.this
							.setErrorMessage(CreateTableSqlPageOne.this.SQL_NAME_EMPTY_INFO);
					return;
				}
				String sql = sqlText.getText();
				if (sql.equals("")) {
					CreateTableSqlPageOne.this
							.setErrorMessage(CreateTableSqlPageOne.this.SQL_EMPTY_INFO);
					return;
				}
				if (!"".equals(sql.trim())) {
					try {
						// 去掉sql语句里面的点位符
						String tmpSql = sql.replaceAll("#.*?#", "").replaceAll("\\$.*?\\$", "");
						if (CommonEclipseUtil.testSql(connectionProfile, tmpSql)) {
							CommonEclipseUtil.showInfomation("Ping successfully !");
							CreateTableSqlPageOne.this.setErrorMessage(null);
							TableSqlAreaModel sqlAreaModel = new TableSqlAreaModel();
							sqlAreaModel.setSqlName(name);
							sqlAreaModel.setSqlArea(sql);
							int i = 0;
							for (String s : CommonEclipseUtil.getColumnsFromSql(connectionProfile,
									tmpSql)) {
								ColumnModel2 cm2 = new ColumnModel2();
								cm2.setName(s);
								cm2.setDescription(s);
								if (i < 10) {
									cm2.setOrder("0" + i);
								} else {
									cm2.setOrder(i + "");
								}
								i++;
								sqlAreaModel.getCms().add(cm2);
							}
							refreshPageTwo(sqlAreaModel);
						} else {
							CommonEclipseUtil.showError("测试未通过[" + sql + "]");
						}
					} catch (SQLException e1) {
						CommonEclipseUtil.errorDialogWithStackTrace("执行sql时出错", e1);
					}
				} else {

				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{
			}
		});

		nameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e)
			{
				CreateTableSqlPageOne.this.verifyText();
			}
		});
		sqlText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e)
			{
				CreateTableSqlPageOne.this.verifyText();
			}
		});

		setErrorMessage(SQL_NAME_EMPTY_INFO);
		setControl(composite);
		setPageComplete(false);
	}

	public void verifyText()
	{
		String text = nameText.getText();
		if ("".equals(text.trim())) {
			this.setErrorMessage(SQL_NAME_EMPTY_INFO);
			return;
		}

		text = sqlText.getText();
		if ("".equals(text.trim())) {
			this.setErrorMessage(SQL_EMPTY_INFO);
			return;
		}
		this.setErrorMessage(null);
		this.setMessage(TEST_INFO);
	}

	public void setData(TableSqlAreaModel model)
	{
		nameText.setText(model.getSqlName().trim());
		sqlText.setText(model.getSqlArea().trim());
		this.setErrorMessage(null);
		this.setMessage(null);
		refreshPageTwo(model);
	}

	private void refreshPageTwo(TableSqlAreaModel model)
	{
		CreateTableSqlPageTwo createDataObjectPageTwo = (CreateTableSqlPageTwo) getWizard()
				.getPage(CreateDataObjectWizard.PAGE_TWO);
		createDataObjectPageTwo.refreshColums(model);
		setPageComplete(true);
	}

	@Override
	public boolean isPageComplete()
	{
		if (nameText != null && sqlText != null) {
			String name = nameText.getText().trim();
			String sql = sqlText.getText().trim();
			if (!"".equals(name) && !"".equals(sql)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IWizardPage getNextPage()
	{
		return super.getNextPage();
	}

	public void setConnectionProfile(IConnectionProfile connectionProfile)
	{
		this.connectionProfile = connectionProfile;
	}

	public IConnectionProfile getConnectionProfile()
	{
		return connectionProfile;
	}

	public final String SQL_EMPTY_INFO = "请输入SQL";
	public final String SQL_NAME_EMPTY_INFO = "请输入数据对象名称";
	public final String TEST_INFO = "输入完SQL请点击测试按钮";
}
