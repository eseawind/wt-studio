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

import com.wt.studio.plugin.querydesigner.model.DataObjectModel;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;
import com.wt.studio.plugin.querydesigner.wizard.CreateDataObjectWizard;

public class CreateDataObjectPageOne extends WizardPage
{
	private IConnectionProfile connectionProfile;
	private Text nameText;
	private Text sqlText;

	public CreateDataObjectPageOne(String pageName, String title, ImageDescriptor titleImage)
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
		group = new Group(composite, SWT.NULL);
		group.setLayout(new FillLayout());
		group.setText("SQL");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		group.setLayoutData(gridData);

		sqlText = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.H_SCROLL);

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
					CreateDataObjectPageOne.this
							.setErrorMessage(CreateDataObjectPageOne.this.SQL_NAME_EMPTY_INFO);
					return;
				}
				String sql = sqlText.getText();
				if (sql.equals("")) {
					CreateDataObjectPageOne.this
							.setErrorMessage(CreateDataObjectPageOne.this.SQL_EMPTY_INFO);
					return;
				}
				if (!"".equals(sql.trim())) {
					try {
						sql = sql.replaceAll("#.*?#", "").replaceAll("\\$.*?\\$", "");
						if (CommonEclipseUtil.testSql(connectionProfile, sql)) {
							CommonEclipseUtil.showInfomation("Ping successfully !");
							CreateDataObjectPageOne.this.setErrorMessage(null);
							DataObjectModel dataObjectModel = new DataObjectModel();
							dataObjectModel.setName(name);
							dataObjectModel.setSql(sql);
							dataObjectModel.setConnectionProfile(connectionProfile);
							dataObjectModel.setCms();
							refreshPageTwo(dataObjectModel);
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
		Button previewBtn = new Button(btnComposite, SWT.NULL);
		previewBtn.setText("预览数据");

		nameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e)
			{
				CreateDataObjectPageOne.this.verifyText();
			}
		});
		sqlText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e)
			{
				CreateDataObjectPageOne.this.verifyText();
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

	public void setData(DataObjectModel model)
	{
		nameText.setText(model.getName());
		sqlText.setText(model.getSql());
		this.setErrorMessage(null);
		this.setMessage(null);
		refreshPageTwo(model);
	}

	private void refreshPageTwo(DataObjectModel model)
	{
		CreateDataObjectPageTwo createDataObjectPageTwo = (CreateDataObjectPageTwo) getWizard()
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
