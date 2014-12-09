package com.wt.studio.plugin.querydesigner.wizard.page;

import java.sql.SQLException;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.ChartType;
import com.wt.studio.plugin.querydesigner.model.SqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.SqlSet;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;
import com.wt.studio.plugin.querydesigner.wizard.CreateChartSqlWizard;

public class CreateSqlChartPage extends WizardPage
{
	private IConnectionProfile connectionProfile;
	private SqlSet sqlSet;
	private Text nameText;
	private Text sqlText;
	private Button testBtn;

	public SqlSet getSqlSet()
	{
		return sqlSet;
	}

	public void setSqlSet(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;
	}

	public CreateSqlChartPage(String pageName, String title, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
	}

	@Override
	public void createControl(Composite parent)
	{
		final ChartSqlAreaModel sql = new ChartSqlAreaModel();
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.heightHint = 250;
		gridData.widthHint = 700;
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		gridData.heightHint = 270;
		final Composite sqlComposite = new Composite(composite, SWT.NULL);

		sqlComposite.setLayout(gridLayout);
		sqlComposite.setLayoutData(gridData);

		Group group = new Group(sqlComposite, SWT.NULL);
		FillLayout fillLayout = new FillLayout();
		fillLayout.spacing = 5;
		group.setLayout(new FillLayout());
		group.setText("名称");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		group.setLayoutData(gridData);
		nameText = new Text(group, SWT.SINGLE);
		group = new Group(sqlComposite, SWT.NULL);
		group.setLayout(new FillLayout());
		group.setText("SQL");
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		group.setLayoutData(gridData);

		sqlText = new Text(group, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.H_SCROLL);

		nameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e)
			{
				CreateSqlChartPage.this.verifyText();
			}
		});

		sqlText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e)
			{
				CreateSqlChartPage.this.verifyText();
			}
		});
		Composite btnComposite = new Composite(sqlComposite, SWT.NULL);
		btnComposite.setLayout(new RowLayout());
		testBtn = new Button(btnComposite, SWT.NULL);
		testBtn.setText("测试");
		final Button clear = new Button(btnComposite, SWT.NONE);
		clear.setText("重置");
		SelectionListener listener = new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e)
			{

			}

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Widget w = e.widget;
				if (w == testBtn) {
					String name = nameText.getText();
					String sqlArea = sqlText.getText();
					if (!"".equals(name) && !"".equals(sqlArea.trim())) {
						try {
							// 去掉sql语句里面的点位符
							String tmpSql = sqlArea.replaceAll("#.*?#", "").replaceAll("\\$.*?\\$",
									"");
							if (CommonEclipseUtil.testSql(connectionProfile, tmpSql)) {
								CommonEclipseUtil.showInfomation("Ping successfully !");
								sql.setSqlName(name);
								sql.setSqlArea(sqlArea);
								sql.getColumnName().clear();
								for (String s : CommonEclipseUtil.getColumnsFromSql(
										connectionProfile, tmpSql)) {
									sql.getColumnName().add(s);
								}
								sql.getColumnName().add(0, "  ");
								sqlSet.addSql(sql);
								refreshPageTwo(sqlSet, sqlSet.getChartType());
								setPageComplete(true);
							} else {
								CommonEclipseUtil.showError("测试未通过[" + tmpSql + "]");
							}
						} catch (SQLException e1) {
							CommonEclipseUtil.errorDialogWithStackTrace("执行sql时出错", e1);
						}
					} else {
						CommonEclipseUtil.showInfomation("数据错误 !");

					}

				} else if (w == clear) {
					if (nameText.getEditable()) {
						nameText.setText("");
					}
					sqlText.setText("");
				}

			}

		};
		testBtn.addSelectionListener(listener);
		clear.addSelectionListener(listener);
		setControl(composite);
		setPageComplete(false);
	}

	protected void verifyText()
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

	public void setConnectionProfile(IConnectionProfile connectionProfile)
	{
		this.connectionProfile = connectionProfile;
	}

	public IConnectionProfile getConnectionProfile()
	{
		return connectionProfile;
	}

	public void CreateSqlSet(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;

	}

	public void setData(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;
		for (SqlAreaModel sqlTemp : this.sqlSet.getSqls()) {
			nameText.setText(sqlTemp.getSqlName());
			sqlText.setText(sqlTemp.getSqlArea());
		}
		refreshPageTwo(this.sqlSet, this.sqlSet.getChartType());
		setPageComplete(true);
	}

	private void refreshPageTwo(SqlSet sqlSet, ChartType chartType)
	{
		CreateConfigChartPage pageConfig = (CreateConfigChartPage) getWizard().getPage(
				CreateChartSqlWizard.PAGE_CONFIG);
		pageConfig.refreshColums(sqlSet, chartType);
	}

	public final String SQL_EMPTY_INFO = "请输入SQL";
	public final String SQL_NAME_EMPTY_INFO = "请输入数据对象名称";
	public final String TEST_INFO = "输入完SQL请点击测试按钮";
	public final String SQL_NAME_DUPLICATE_INFO = "列表中存在此名称的SQL,保存会覆盖！";

}
