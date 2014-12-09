package com.wt.studio.plugin.querydesigner.model;

import java.util.ArrayList;
import java.util.List;

public class SqlSet
{
	private String name;

	private List<ChartSqlAreaModel> sqlModelSet = new ArrayList<ChartSqlAreaModel>();

	private ChartType chartType;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ChartType getChartType()
	{
		return chartType;
	}

	public void setChartType(ChartType chartType)
	{
		this.chartType = chartType;
	}

	public List<ChartSqlAreaModel> getSqls()
	{
		return this.sqlModelSet;
	}

	public void setSqlModelSet(List<ChartSqlAreaModel> sqlModelSet)
	{
		this.sqlModelSet = sqlModelSet;
	}

	public void addSql(ChartSqlAreaModel sql)
	{
		this.sqlModelSet.add(sql);
	}

	public void removeSql(ChartSqlAreaModel sql)
	{
		this.sqlModelSet.remove(sql);
	}
}
