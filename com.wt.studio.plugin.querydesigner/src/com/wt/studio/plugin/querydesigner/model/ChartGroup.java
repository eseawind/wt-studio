package com.wt.studio.plugin.querydesigner.model;

import java.util.ArrayList;
import java.util.List;

public class ChartGroup
{
	private String chartGroupName;
	private String chartGroupDisplayName;
	private List<ChartType> charts = new ArrayList<ChartType>();

	public String getChartGroupName()
	{
		return chartGroupName;
	}

	public void setChartGroupName(String chartGroupName)
	{
		this.chartGroupName = chartGroupName;
	}

	public String getChartGroupDisplayName()
	{
		return chartGroupDisplayName;
	}

	public void setChartGroupDisplayName(String chartGroupDisplayName)
	{
		this.chartGroupDisplayName = chartGroupDisplayName;
	}

	public List<ChartType> getCharts()
	{
		return charts;
	}

	public void setCharts(List<ChartType> charts)
	{
		this.charts = charts;
	}

}
