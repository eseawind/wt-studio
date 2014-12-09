package com.wt.studio.plugin.querydesigner.model;

public class Property
{
	private String name;// 图形类型编码
	private String defaultValue;// 图形类型名称
	private String isBase;

	public String getIsBase()
	{
		return isBase;
	}

	public void setIsBase(String isBase)
	{
		this.isBase = isBase;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

}
