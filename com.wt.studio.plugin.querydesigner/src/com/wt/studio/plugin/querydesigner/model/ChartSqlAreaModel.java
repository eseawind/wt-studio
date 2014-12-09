package com.wt.studio.plugin.querydesigner.model;

import java.util.ArrayList;
import java.util.List;

public class ChartSqlAreaModel extends SqlAreaModel
{
	private List<String> columnName = new ArrayList<String>();

	private String chartType = "", Xaxis = "", Xdes = "", Xunit = "", Yaxis = "", Ydes = "",
			Yunit = "", series = "", seriesDes = "", stack = "", stackDes = "", YaxisNum = "0";

	public ChartSqlAreaModel()
	{
		this.columnName.add("  ");
	}

	public List<String> getColumnName()
	{
		return columnName;
	}

	public void setColumnName(List<String> columnName)
	{
		this.columnName = columnName;
		// this.columnName.add(0, " ");
	}

	public String getChartType()
	{
		return chartType;
	}

	public void setChartType(String chartType)
	{
		this.chartType = chartType;
	}

	public String getXaxis()
	{
		return Xaxis;
	}

	public void setXaxis(String xaxis)
	{
		Xaxis = xaxis;
	}

	public String getXdes()
	{
		return Xdes;
	}

	public void setXdes(String xdes)
	{
		Xdes = xdes;
	}

	public String getXunit()
	{
		return Xunit;
	}

	public void setXunit(String xunit)
	{
		Xunit = xunit;
	}

	public String getYaxis()
	{
		return Yaxis;
	}

	public void setYaxis(String yaxis)
	{
		Yaxis = yaxis;
	}

	public String getYdes()
	{
		return Ydes;
	}

	public void setYdes(String ydes)
	{
		Ydes = ydes;
	}

	public String getYunit()
	{
		return Yunit;
	}

	public void setYunit(String yunit)
	{
		Yunit = yunit;
	}

	public String getSeries()
	{
		return series;
	}

	public void setSeries(String series)
	{
		this.series = series;
	}

	public String getSeriesDes()
	{
		return seriesDes;
	}

	public void setSeriesDes(String seriesDes)
	{
		this.seriesDes = seriesDes;
	}

	public String getStack()
	{
		return stack;
	}

	public void setStack(String stack)
	{
		this.stack = stack;
	}

	public String getStackDes()
	{
		return stackDes;
	}

	public void setStackDes(String stackDes)
	{
		this.stackDes = stackDes;
	}

	public String getYaxisNum()
	{
		return YaxisNum;
	}

	public void setYaxisNum(String yaxisNum)
	{
		YaxisNum = yaxisNum;
	}
}
