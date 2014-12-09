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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.ChartType;
import com.wt.studio.plugin.querydesigner.model.SqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.SqlSet;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;
import com.wt.studio.plugin.querydesigner.wizard.CreateChartSqlWizard;

public class CreateChartSqlPageOne extends WizardPage
{
	private IConnectionProfile connectionProfile;
	private SqlSet sqlSet;
	private Table table;
	private Text nameText;
	private Text sqlText;
	private Text name;
	private int editFlag = -1;
	private ChartType chartType;
	private static String[] chartTypeDisplay;
	private static String[] chartTypeArray;
	private int sqlNum;
	private Combo chartTypeCombo;
	private Button testBtn;
	private Button add;

	public SqlSet getSqlSet()
	{
		return sqlSet;
	}

	public void setSqlSet(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;
	}

	public CreateChartSqlPageOne(String pageName, String title, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
	}

	@Override
	public void createControl(Composite parent)
	{
		final ChartSqlAreaModel sql = new ChartSqlAreaModel();
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		Composite chartTypeArea = new Composite(composite, SWT.NULL);
		chartTypeArea.setLayout(new GridLayout());
		// 图表name
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		chartTypeArea.setLayoutData(gridData);
		Group blockName = new Group(chartTypeArea, SWT.NULL);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		blockName.setLayoutData(gridData);
		blockName.setLayout(new FillLayout());
		blockName.setText("图表名称*");
		name = new Text(blockName, SWT.BORDER);
		name.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e)
			{
				// TODO Auto-generated method stub
				sqlSet.setName(name.getText());
			}

		});
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.heightHint = 250;
		gridData.widthHint = 700;
		final Composite top = new Composite(composite, SWT.NULL);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		top.setLayout(gridLayout);
		top.setLayoutData(gridData);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		Group group = new Group(top, SWT.NULL);
		group.setLayout(new FillLayout());
		group.setLayoutData(gridData);
		group.setText("图表SQL源");
		table = new Table(group, SWT.CHECK | SWT.MULTI | SWT.FULL_SELECTION);
		String[] tableHeader = { "Select", "Name", "SQL" };
		for (int i = 0; i < tableHeader.length; i++) {
			TableColumn tableColumn = new TableColumn(table, SWT.NONE);
			tableColumn.setText(tableHeader[i]);
			tableColumn.setMoveable(true);
		}
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.getColumn(0).setWidth(60);
		table.getColumn(1).setWidth(90);
		table.getColumn(2).setWidth(600);

		Composite buttonArea = new Composite(top, SWT.NONE);
		buttonArea.setLayout(new RowLayout());
		add = new Button(buttonArea, SWT.NONE);
		add.setText("添加");
		final Button edit = new Button(buttonArea, SWT.NONE);
		edit.setText("编辑");
		edit.setEnabled(false);
		final Button del = new Button(buttonArea, SWT.NONE);
		del.setText("删除");
		del.setEnabled(false);
		gridData.heightHint = 270;
		final Composite sqlComposite = new Composite(composite, SWT.NULL);

		sqlComposite.setLayout(gridLayout);
		sqlComposite.setLayoutData(gridData);

		group = new Group(sqlComposite, SWT.NULL);
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
				CreateChartSqlPageOne.this.verifyText();
			}
		});

		sqlText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e)
			{
				CreateChartSqlPageOne.this.verifyText();
			}
		});

		table.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				boolean haveSelected = false;
				TableItem[] items = table.getItems();
				if (items.length > 0) {
					for (int i = 0; i < items.length; i++) {
						if (!items[i].getChecked())
							continue;
						haveSelected = true;
						edit.setEnabled(true);
						del.setEnabled(true);
					}
					if (haveSelected == false) {
						edit.setEnabled(false);
						del.setEnabled(false);
					}
				} else {
					edit.setEnabled(false);
					del.setEnabled(false);
				}
			}

		});
		Composite btnComposite = new Composite(sqlComposite, SWT.NULL);
		btnComposite.setLayout(new RowLayout());
		testBtn = new Button(btnComposite, SWT.NULL);
		testBtn.setText("测试");
		final Button save = new Button(btnComposite, SWT.NONE);
		save.setText("保存");
		save.setEnabled(false);
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
					String blockName = name.getText();
					if (blockName == null || "".equals(blockName)) {
						setErrorMessage("图表名称为空");
						return;
					} else {
						setErrorMessage(null);
					}
					String name = nameText.getText();
					String sqlArea = sqlText.getText();
					if (!"".equals(name) && !"".equals(sqlArea.trim())) {
						try {
							// 去掉sql语句里面的点位符
							String tmpSql = sqlArea.replaceAll("#.*?#", "").replaceAll("\\$.*?\\$",
									"");
							if (CommonEclipseUtil.testSql(connectionProfile, tmpSql)) {
								CommonEclipseUtil.showInfomation("Ping successfully !");
								save.setEnabled(true);
								sql.setSqlName(name);
								sql.setSqlArea(sqlArea);
								sql.getColumnName().clear();
								for (String s : CommonEclipseUtil.getColumnsFromSql(
										connectionProfile, tmpSql)) {
									sql.getColumnName().add(s);
								}
							} else {
								CommonEclipseUtil.showError("测试未通过[" + tmpSql + "]");
							}
						} catch (SQLException e1) {
							CommonEclipseUtil.errorDialogWithStackTrace("执行sql时出错", e1);
						}
					} else {
						CommonEclipseUtil.showInfomation("数据错误 !");

					}

				} else if (w == save) {
					setPageComplete(true);
					nameText.setEditable(true);
					ChartSqlAreaModel sqlTemp = new ChartSqlAreaModel();
					sqlTemp.setSqlName(sql.getSqlName());
					sqlTemp.setSqlArea(sql.getSqlArea());
					sqlTemp.getColumnName().addAll(sql.getColumnName());
					TableItem[] items = table.getItems();
					for (int i = 0; i < items.length; i++) {
						if (sql.getSqlName().equals(items[i].getText(1))) {
							items[i].setText(2, sql.getSqlArea());
							editFlag = i;
							items[i].setText(2, sql.getSqlArea());
							sqlSet.getSqls().remove(editFlag);
							sqlSet.getSqls().add(editFlag, sqlTemp);
							break;
						}

					}
					if (editFlag == -1) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(1, sql.getSqlName());
						item.setText(2, sql.getSqlArea());
						sqlSet.addSql(sqlTemp);
					}
					if (sqlNum == 1) {
						testBtn.setEnabled(false);
					}
					editFlag = -1;
					save.setEnabled(false);
					setPageComplete(true);
					refreshPageTwo(sqlSet, chartType);
				} else if (w == del) {
					testBtn.setEnabled(true);
					edit.setEnabled(false);
					del.setEnabled(false);
					setErrorMessage(null);
					setMessage(TEST_INFO);
					nameText.setEditable(true);
					TableItem[] items = table.getItems();
					for (int i = 0; i < items.length; i++) {
						if (!items[i].getChecked())
							continue;
						int index = table.indexOf(items[i]);
						if (index < 0)
							continue;
						table.remove(index);
						sqlSet.getSqls().remove(index);
						refreshPageTwo(sqlSet, chartType);
					}
					if (table.getItems().length == 0) {
						setPageComplete(false);
					}
				} else if (w == edit) {
					testBtn.setEnabled(true);
					TableItem[] items = table.getItems();
					for (int i = 0; i < items.length; i++) {
						if (!items[i].getChecked())
							continue;
						int index = table.indexOf(items[i]);
						if (index < 0)
							continue;
						nameText.setText(items[i].getText(1));
						nameText.setEditable(false);
						sqlText.setText(items[i].getText(2));
					}
				} else if (w == clear) {
					if (nameText.getEditable()) {
						nameText.setText("");
					}
					sqlText.setText("");
				} else if (w == add) {
					TableItem[] items = table.getItems();
					for (int i = 0; i < items.length; i++) {
						items[i].setChecked(false);
					}
					nameText.setEditable(true);
					if (nameText.getEditable()) {
						nameText.setText("");
					}
					sqlText.setText("");
					del.setEnabled(false);
					edit.setEnabled(false);
				}

			}

		};
		testBtn.addSelectionListener(listener);
		save.addSelectionListener(listener);
		add.addSelectionListener(listener);
		del.addSelectionListener(listener);
		edit.addSelectionListener(listener);
		clear.addSelectionListener(listener);
		setControl(composite);
		setPageComplete(false);
	}

	public void verifyChartType(ChartType chart)
	{
		// TODO Auto-generated method stub
		chartType = chart;
		refreshPageTwo(sqlSet, chartType);
		if ("true".equals(chartType.getProperties().get(0).getIsBase())) {
			sqlNum = 1;
			if (table.getItems().length > 0) {
				testBtn.setEnabled(false);
				add.setEnabled(false);
			}

		} else {
			sqlNum = 0;
			testBtn.setEnabled(true);
			add.setEnabled(true);
		}
		if (table.getItems().length > 0) {
			setPageComplete(true);
		}
	}

	protected void verifyText()
	{
		String text = nameText.getText();
		if ("".equals(text.trim())) {
			this.setErrorMessage(SQL_NAME_EMPTY_INFO);
			return;
		}
		TableItem[] items = table.getItems();
		for (int i = 0; i < items.length; i++) {
			if (!text.equals(items[i].getText(1))) {
				continue;
			}
			this.setMessage(SQL_NAME_DUPLICATE_INFO);
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
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(1, sqlTemp.getSqlName());
			item.setText(2, sqlTemp.getSqlArea());
		}
		refreshPageTwo(this.sqlSet, this.sqlSet.getChartType());
		if (this.sqlSet.getName() != null) {
			name.setText(this.sqlSet.getName());
		}
		verifyChartType(sqlSet.getChartType());
		setPageComplete(true);

	}

	private void refreshPageTwo(SqlSet sqlSet, ChartType chartType)
	{
		CreateChartSqlPageTwo pageTwo = (CreateChartSqlPageTwo) getWizard().getPage(
				CreateChartSqlWizard.PAGE_TWO);
		pageTwo.refreshColums(sqlSet, chartType);
	}

	public final String SQL_EMPTY_INFO = "请输入SQL";
	public final String SQL_NAME_EMPTY_INFO = "请输入数据对象名称";
	public final String TEST_INFO = "输入完SQL请点击测试按钮";
	public final String SQL_NAME_DUPLICATE_INFO = "列表中存在此名称的SQL,保存会覆盖！";

}
