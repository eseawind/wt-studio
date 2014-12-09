package com.wt.studio.plugin.querydesigner.wizard.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.ChartType;
import com.wt.studio.plugin.querydesigner.model.SqlSet;

public class CreateConfigChartPage extends WizardPage
{
	private SqlSet sqlSet;
	private Combo xz;
	private Text xdesc;
	private Text xunit;
	private Combo yaxis;
	private Text ydesc;
	private Text yunit;
	private Combo series;
	private Text serdes;
	private Combo stack;
	private Text stackdes;
	private ModifyListener modify;
	private Table table;
	private ArrayList<TableEditor> editors = new ArrayList();
	private String[] columns;
	private StringBuffer yz;
	private StringBuffer ydes;
	private StringBuffer yun;
	private ChartSqlAreaModel chart;
	private Composite sarea;
	private Composite yArea;
	private int ycount = 0;
	private int scount = 0;
	private List<String> y0 = new ArrayList<String>();
	private List<String> y1 = new ArrayList<String>();
	private List<String> y2 = new ArrayList<String>();
	private List<Widget> widgets = new ArrayList<Widget>();

	public SqlSet getSqlSet()
	{
		return sqlSet;
	}

	public void setSqlSet(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;
	}

	public CreateConfigChartPage(String pageName, String title, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
	}

	@Override
	public void createControl(Composite parent)
	{
		// TODO Auto-generated method stub
		final Composite composite = new Composite(parent, SWT.NULL);
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.verticalAlignment = GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;

		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;

		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		gridData.heightHint = 60;
		composite.setLayoutData(gridData1);
		composite.setLayout(new GridLayout());
		Composite group = new Composite(composite, SWT.NULL);
		// group.setText("图表配置");
		// group.setLayoutData(gridData);
		group.setLayout(new GridLayout(6, false));
		group.setLayoutData(gridData);
		final GridData data = new GridData();
		data.widthHint = 120;
		data.horizontalIndent = 10;
		data.verticalIndent = 15;

		final GridData data1 = new GridData();
		data1.widthHint = 70;
		data1.horizontalIndent = 10;
		data1.verticalIndent = 15;

		Label Xaxis = new Label(group, SWT.NULL);
		Xaxis.setText("X轴");
		xz = new Combo(group, SWT.READ_ONLY);
		xz.select(0);

		Xaxis.setLayoutData(data1);
		xz.setLayoutData(data);

		Label Xdesc = new Label(group, SWT.NULL);
		Xdesc.setText("X轴描述");
		xdesc = new Text(group, SWT.BORDER);
		xdesc.setLayoutData(data);
		Xdesc.setLayoutData(data1);

		Label Xunit = new Label(group, SWT.NULL);
		Xunit.setText("X轴单位");
		xunit = new Text(group, SWT.BORDER);
		xunit.setLayoutData(data);
		Xunit.setLayoutData(data1);
		yArea = new Composite(composite, SWT.NULL);
		yArea.setLayoutData(gridData2);
		yArea.setLayout(new GridLayout(7, false));
		Label Yaxis = new Label(yArea, SWT.NULL);
		Yaxis.setText("Y轴");
		yaxis = new Combo(yArea, SWT.READ_ONLY);
		Yaxis.setLayoutData(data1);
		yaxis.setLayoutData(data);

		Label Ydesc = new Label(yArea, SWT.NULL);
		Ydesc.setText("Y轴描述");
		ydesc = new Text(yArea, SWT.BORDER);
		ydesc.setLayoutData(data);
		Ydesc.setLayoutData(data1);

		Label Yunit = new Label(yArea, SWT.NULL);
		Yunit.setText("Y轴单位");
		yunit = new Text(yArea, SWT.BORDER);
		yunit.setLayoutData(data);
		Yunit.setLayoutData(data1);

		sarea = new Composite(composite, SWT.NULL);
		sarea.setLayoutData(gridData2);
		sarea.setLayout(new GridLayout(4, false));
		Label Series = new Label(sarea, SWT.NULL);
		Series.setText("Series");
		Series.setLayoutData(data1);
		series = new Combo(sarea, SWT.READ_ONLY);
		series.setLayoutData(data);

		Label Serdes = new Label(sarea, SWT.NULL);
		Serdes.setText("Series描述");
		Serdes.setLayoutData(data1);
		serdes = new Text(sarea, SWT.BORDER);
		serdes.setLayoutData(data);

		Composite tarea = new Composite(composite, SWT.NULL);
		tarea.setLayout(new GridLayout(4, false));
		tarea.setLayoutData(gridData);
		Label Stack = new Label(tarea, SWT.NULL);
		Stack.setText("Stack");
		Stack.setLayoutData(data1);
		stack = new Combo(tarea, SWT.READ_ONLY);
		stack.setLayoutData(data);

		Label Stackdes = new Label(tarea, SWT.NULL);
		Stackdes.setText("Stack描述");
		Stackdes.setLayoutData(data1);
		stackdes = new Text(tarea, SWT.BORDER);
		stackdes.setLayoutData(data);

		modify = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e)
			{
				// TODO Auto-generated method stub
				Widget w = e.widget;
				ChartSqlAreaModel chart = null;
				for (ChartSqlAreaModel ch : sqlSet.getSqls()) {
					chart = ch;
				}
				if (w == xz) {
					chart.setXaxis(chart.getColumnName().get(xz.getSelectionIndex()));
				} else if (w == xdesc) {
					chart.setXdes(xdesc.getText());
				} else if (w == xunit) {
					chart.setXunit(xunit.getText());
				} else if (w == yaxis) {
					chart.setYaxis(chart.getColumnName().get(yaxis.getSelectionIndex()));
				} else if (w == ydesc) {
					chart.setYdes(ydesc.getText());
				} else if (w == yunit) {
					chart.setYunit(yunit.getText());
				} else if (w == series) {
					chart.setSeries(chart.getColumnName().get(series.getSelectionIndex()));
				} else if (w == serdes) {
					chart.setSeriesDes(serdes.getText());
				} else if (w == stack) {
					chart.setStack(chart.getColumnName().get(stack.getSelectionIndex()));
				} else if (w == stackdes) {
					chart.setStackDes(stackdes.getText());
				}
			}

		};
		setControl(composite);

	}

	public void setData(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;
	}

	public void addTableColumn()
	{
		for (TableEditor editor : editors) {
			Control cont = editor.getEditor();
			cont.dispose();
		}
		TableItem item = new TableItem(table, SWT.NULL);
		CreateTableComb(table, item, 1);
		CreateTableText(table, item, 2);
		CreateTableText(table, item, 3);

	}

	public void refreshColums(SqlSet sqlSet, ChartType chartType)
	{
		// TODO Auto-generated method stub
		this.sqlSet = sqlSet;

		for (ChartSqlAreaModel ch : sqlSet.getSqls()) {
			chart = ch;
		}

		List<String> list = chart.getColumnName();
		columns = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			columns[i] = list.get(i);
		}
		xz.setItems(columns);
		xz.select(chart.getColumnName().indexOf(chart.getXaxis()));
		xdesc.setText(chart.getXdes());
		xunit.setText(chart.getXunit());
		yaxis.setItems(columns);
		yaxis.select(chart.getColumnName().indexOf(chart.getYaxis()));
		ydesc.setText(chart.getYdes());
		yunit.setText(chart.getYunit());
		series.setItems(columns);
		series.select(chart.getColumnName().indexOf(chart.getSeries()));
		serdes.setText(chart.getSeriesDes());
		stack.setItems(columns);
		stack.select(chart.getColumnName().indexOf(chart.getStack()));
		stackdes.setText(chart.getStackDes());

		xz.addModifyListener(modify);
		xdesc.addModifyListener(modify);
		xunit.addModifyListener(modify);
		yaxis.addModifyListener(modify);
		ydesc.addModifyListener(modify);
		yunit.addModifyListener(modify);
		series.addModifyListener(modify);
		serdes.addModifyListener(modify);
		stack.addModifyListener(modify);
		stackdes.addModifyListener(modify);

	}

	private void initialiseTable()
	{
		// TODO Auto-generated method stub
		String[] yz = chart.getYaxis().split(",");
		String[] ydes = chart.getYdes().split(",");
		String[] yun = chart.getYunit().split(",");
		if (yz.length == ydes.length && ydes.length == yun.length) {
			for (int i = 0; i < yz.length; i++) {
				TableItem item = new TableItem(table, SWT.NULL);
				item.setText(1, yz[i]);
				item.setText(2, ydes[i]);
				item.setText(3, yun[i]);
			}
		}
	}

	public void CreateTableText(Table table, TableItem item, final int i)
	{
		final TableEditor editor = new TableEditor(table);
		editors.add(editor);
		final Text text = new Text(table, SWT.NONE);
		text.setText(item.getText(i));
		editor.grabHorizontal = true;
		editor.setEditor(text, item, i);
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent evt)
			{
				editor.getItem().setText(i, text.getText());
				updateygroup();
			}

		});

	}

	public void CreateTableComb(Table table, final TableItem item, final int i)
	{
		final TableEditor editor = new TableEditor(table);
		editors.add(editor);
		final Combo combo = new Combo(table, SWT.NONE);
		combo.setItems(columns);
		combo.select(Arrays.asList(columns).indexOf(item.getText(i)));
		combo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0)
			{
				// TODO Auto-generated method stub
				item.setText(i, columns[combo.getSelectionIndex()]);
				updateygroup();
			}

		});
		editor.grabHorizontal = true;
		editor.setEditor(combo, item, i);

	}

	public void updateygroup()
	{
		yz = new StringBuffer();
		ydes = new StringBuffer();
		yun = new StringBuffer();
		TableItem[] items = table.getItems();
		int point = 0;
		for (TableItem item : items) {
			yz.append(item.getText(1));
			ydes.append(item.getText(2));
			yun.append(item.getText(3));
			point++;
			if (point < items.length) {
				yz.append(",");
				ydes.append(",");
				yun.append(",");
			}
		}
		chart.setYaxis(yz.toString());
		chart.setYdes(ydes.toString());
		chart.setYunit(yun.toString());

	}
}
