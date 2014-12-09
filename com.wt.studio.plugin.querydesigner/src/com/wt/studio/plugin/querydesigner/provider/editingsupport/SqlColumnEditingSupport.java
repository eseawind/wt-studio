package com.wt.studio.plugin.querydesigner.provider.editingsupport;

import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.ChartType;

public class SqlColumnEditingSupport extends EditingSupport
{

	private CellEditor chartTypeEditor;
	int columnNumber = 0;
	private TableViewer viewer;
	private CellEditor textEditor;
	private List<String> columns;
	String chartTypes[] = { "column", "line", "pie", "bar", "area", "gauge" };
	private ChartType chart;

	public ChartType getChart()
	{
		return chart;
	}

	public void setChart(ChartType chart)
	{
		this.chart = chart;
	}

	public SqlColumnEditingSupport(TableViewer viewer, int columnNumber)
	{
		super(viewer);
		this.viewer = viewer;
		chartTypeEditor = new ComboBoxCellEditor(viewer.getTable(), chartTypes);
		textEditor = new TextCellEditor(viewer.getTable());
		this.columnNumber = columnNumber;
	}

	protected boolean canEdit(Object element)
	{
		return true;
	}

	protected CellEditor getCellEditor(Object element)
	{
		columns = ((ChartSqlAreaModel) element).getColumnName();
		String[] elements = new String[columns.size()];
		int i = 0;
		for (String s : columns) {
			elements[i] = s;
			i++;
		}
		ComboBoxCellEditor combo = new ComboBoxCellEditor(viewer.getTable(), elements);
		if ((columnNumber == 1 && "1".equals(chart.getProperties().get(1).getDefaultValue()))
				|| (columnNumber == 4 && "1".equals(chart.getProperties().get(4).getDefaultValue()))
				|| (columnNumber == 8 && "1".equals(chart.getProperties().get(8).getDefaultValue()))
				|| (columnNumber == 10 && "1".equals(chart.getProperties().get(10)
						.getDefaultValue()))) {
			return combo;
		} else if ((columnNumber == 2 && "1".equals(chart.getProperties().get(2).getDefaultValue()))
				|| (columnNumber == 5 && "1".equals(chart.getProperties().get(5).getDefaultValue()))
				|| (columnNumber == 7 && "1".equals(chart.getProperties().get(7).getDefaultValue()))
				|| (columnNumber == 9 && "1".equals(chart.getProperties().get(9).getDefaultValue()))
				|| (columnNumber == 11 && "1".equals(chart.getProperties().get(11)
						.getDefaultValue()))) {
			return textEditor;
		} else if ((columnNumber == 3 && "1".equals(chart.getProperties().get(3).getDefaultValue()))
				|| (columnNumber == 6 && "1".equals(chart.getProperties().get(6).getDefaultValue()))) {
			return textEditor;
		} else if (columnNumber == 0 && "false".equals(chart.getProperties().get(0).getIsBase())) {
			return chartTypeEditor;
		} else
			return null;
	}

	protected Object getValue(Object element)
	{
		if (columnNumber == 0) {
			List<String> list = java.util.Arrays.asList(chartTypes);
			return ((ChartSqlAreaModel) element).getChartType() == null ? list
					.indexOf((String) ((ChartSqlAreaModel) element).getChartType()) : 0;
		} else if (columnNumber == 1) {
			return ((ChartSqlAreaModel) element).getXaxis() == null ? (columns
					.indexOf((String) ((ChartSqlAreaModel) element).getXaxis())) : 0;
		} else if (columnNumber == 2) {
			return ((ChartSqlAreaModel) element).getXdes();
		} else if (columnNumber == 3) {
			return ((ChartSqlAreaModel) element).getXunit();
		} else if (columnNumber == 4) {
			return ((ChartSqlAreaModel) element).getYaxis() == null ? (columns
					.indexOf((String) ((ChartSqlAreaModel) element).getYaxis())) : 0;
		} else if (columnNumber == 5) {
			return ((ChartSqlAreaModel) element).getYdes();
		} else if (columnNumber == 6) {
			return ((ChartSqlAreaModel) element).getYunit();
		} else if (columnNumber == 7) {
			return ((ChartSqlAreaModel) element).getYaxisNum();
		} else if (columnNumber == 8) {
			return ((ChartSqlAreaModel) element).getSeries() == null ? (columns
					.indexOf((String) ((ChartSqlAreaModel) element).getSeries())) : 0;
		} else if (columnNumber == 9) {
			return ((ChartSqlAreaModel) element).getSeriesDes();
		} else if (columnNumber == 10) {
			return ((ChartSqlAreaModel) element).getStack() == null ? (columns
					.indexOf((String) ((ChartSqlAreaModel) element).getStack())) : 0;
		} else if (columnNumber == 11) {
			return ((ChartSqlAreaModel) element).getStackDes();
		} else {
			return null;
		}
	}

	protected void setValue(Object element, Object value)
	{
		if (columnNumber == 0) {
			if ((Integer) value > -1)
				((ChartSqlAreaModel) element).setChartType((String) chartTypes[(Integer) value]);
		} else if (columnNumber == 1) {
			if ((Integer) value > -1)
				((ChartSqlAreaModel) element).setXaxis((String) columns.get((Integer) value));

		} else if (columnNumber == 2) {
			((ChartSqlAreaModel) element).setXdes((String) value);

		} else if (columnNumber == 3) {
			((ChartSqlAreaModel) element).setXunit((String) value);

		} else if (columnNumber == 4) {
			if ((Integer) value > -1)
				((ChartSqlAreaModel) element).setYaxis((String) columns.get((Integer) value));

		} else if (columnNumber == 5) {
			((ChartSqlAreaModel) element).setYdes((String) value);

		} else if (columnNumber == 6) {
			((ChartSqlAreaModel) element).setYunit((String) value);
		} else if (columnNumber == 7) {
			((ChartSqlAreaModel) element).setYaxisNum((String) value);
		} else if (columnNumber == 8) {
			if ((Integer) value > -1)
				((ChartSqlAreaModel) element).setSeries((String) columns.get((Integer) value));
		} else if (columnNumber == 9) {
			((ChartSqlAreaModel) element).setSeriesDes((String) value);
		} else if (columnNumber == 10) {
			if ((Integer) value > -1)
				((ChartSqlAreaModel) element).setStack((String) columns.get((Integer) value));

		} else if (columnNumber == 11) {
			((ChartSqlAreaModel) element).setStackDes((String) value);
		}
		getViewer().update(element, null);
	}
}
