package com.wt.studio.plugin.querydesigner.wizard.page;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.ChartType;
import com.wt.studio.plugin.querydesigner.model.SqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.SqlSet;
import com.wt.studio.plugin.querydesigner.provider.content.TableColumnLabelProvider;
import com.wt.studio.plugin.querydesigner.provider.editingsupport.SqlColumnEditingSupport;

public class CreateChartSqlPageTwo extends WizardPage
{
	private SqlSet sqls;
	private TableViewer tableViewer;
	final String[] tableColumns = { "SQL名称", "ChartType", "X轴", "X轴描述", "X轴单位", "Y轴", "Y轴描述",
			"Y轴单位", "Y轴次序", "Series", "Series描述", "Stack", "Stack描述" };
	private Table table;

	private List<SqlColumnEditingSupport> columns = new ArrayList<SqlColumnEditingSupport>();

	public CreateChartSqlPageTwo(String pageName, String title, ImageDescriptor titleImage)
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
		group.setText("Columns");
		group.setLayout(new FillLayout());
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		group.setLayoutData(gridData);
		// group.setLayoutData(formData);
		tableViewer = new TableViewer(group, SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		TableViewerColumn nameColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element)
			{
				return ((SqlAreaModel) element).getSqlName();
			}
		});
		SqlColumnEditingSupport temp = null;
		for (int i = 0; i < 12; i++) {
			TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE);
			column.setLabelProvider(new TableColumnLabelProvider(i));
			temp = new SqlColumnEditingSupport(tableViewer, i);
			columns.add(temp);
			column.setEditingSupport(temp);
		}
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setColumnProperties(tableColumns);
		tableViewer.setInput(new ArrayList<SqlAreaModel>());
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		packTableViewer();
		setControl(composite);
	}

	public void packTableViewer()
	{
		int i = 0;
		Table table = tableViewer.getTable();
		for (TableColumn col : table.getColumns()) {
			col.setText(tableColumns[i]);
			col.setWidth(70);
			i++;
		}
	}

	public void setSqlSet(SqlSet sqls)
	{
		this.sqls = sqls;
	}

	public SqlSet getSqlSet()
	{
		return this.sqls;
	}

	public void refreshColums(SqlSet sqlSet, ChartType chartType)
	{
		setSqlSet(sqlSet);
		List<ChartSqlAreaModel> sqls = sqlSet.getSqls();
		for (ChartSqlAreaModel tempSql : sqls) {
			if ("".equals(tempSql.getChartType())
					|| "true".equals(chartType.getProperties().get(0).getIsBase()))
				tempSql.setChartType(chartType.getProperties().get(0).getDefaultValue());
		}
		for (SqlColumnEditingSupport temp : columns) {
			temp.setChart(chartType);
		}
		if (tableViewer != null) {
			tableViewer.setInput(sqls);
		}
	}
}
