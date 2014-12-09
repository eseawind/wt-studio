package com.wt.studio.plugin.querydesigner.model;

import java.util.ArrayList;
import java.util.List;

public class ChartType
{
	private String name;// 图形类型编码
	private String displayName;// 图形类型名称
	private String imgUrl;// 图形分类
	private String chartGroup;
	private List<Property> properties = new ArrayList<Property>();

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}

	public String getChartGroup()
	{
		return chartGroup;
	}

	public void setChartGroup(String chartGroup)
	{
		this.chartGroup = chartGroup;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	/*public String getChartType()
	{
		return chartType;
	}

	public void setChartType(String chartType)
	{
		this.chartType = chartType;
	}*/

	public List<Property> getProperties()
	{

		return this.properties;
	}

}
