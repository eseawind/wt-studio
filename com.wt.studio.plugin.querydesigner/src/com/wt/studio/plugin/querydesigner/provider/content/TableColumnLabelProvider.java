package com.wt.studio.plugin.querydesigner.provider.content;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;

public class TableColumnLabelProvider extends ColumnLabelProvider
{

	int columnNumber;

	public TableColumnLabelProvider(int i)
	{
		this.columnNumber = i;
	}

	public String getText(Object element)
	{

		if (columnNumber == 0) {
			if (((ChartSqlAreaModel) element).getChartType() != null)
				return (((ChartSqlAreaModel) element).getChartType());
			else
				return null;
		} else if (columnNumber == 1) {
			if (((ChartSqlAreaModel) element).getXaxis() != null)
				return (((ChartSqlAreaModel) element).getXaxis());
			else
				return null;
		} else if (columnNumber == 2) {
			if (((ChartSqlAreaModel) element).getXdes() != null)
				return ((ChartSqlAreaModel) element).getXdes();
			else
				return null;
		} else if (columnNumber == 3) {
			if (((ChartSqlAreaModel) element).getXunit() != null)
				return (((ChartSqlAreaModel) element).getXunit());
			else
				return null;
		} else if (columnNumber == 4) {
			if (((ChartSqlAreaModel) element).getYaxis() != null)
				return (((ChartSqlAreaModel) element).getYaxis());
			else
				return null;
		} else if (columnNumber == 5) {
			if (((ChartSqlAreaModel) element).getYdes() != null)
				return ((ChartSqlAreaModel) element).getYdes();
			else
				return null;
		} else if (columnNumber == 6) {
			if (((ChartSqlAreaModel) element).getYunit() != null)
				return (((ChartSqlAreaModel) element).getYunit());
			else
				return null;
		} else if (columnNumber == 7) {
			if (((ChartSqlAreaModel) element).getYaxisNum() != null)
				return ((ChartSqlAreaModel) element).getYaxisNum();
			else
				return null;
		} else if (columnNumber == 8) {
			if (((ChartSqlAreaModel) element).getSeries() != null)
				return (((ChartSqlAreaModel) element).getSeries());
			else
				return null;
		} else if (columnNumber == 9) {
			if (((ChartSqlAreaModel) element).getSeriesDes() != null)
				return ((ChartSqlAreaModel) element).getSeriesDes();
			else
				return null;
		} else if (columnNumber == 10) {
			if (((ChartSqlAreaModel) element).getStack() != null)
				return (((ChartSqlAreaModel) element).getStack());
			else
				return null;
		} else if (columnNumber == 11) {
			if (((ChartSqlAreaModel) element).getStackDes() != null)
				return ((ChartSqlAreaModel) element).getStackDes();
			else
				return null;
		} else
			return null;

	}

}
