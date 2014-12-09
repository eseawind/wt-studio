package com.wt.studio.plugin.querydesigner.utils;

public class Table
{
	private String tableName;
	private String tableType;

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getTableType()
	{
		return tableType;
	}

	public void setTableType(String tableType)
	{
		this.tableType = tableType;
	}

	public static void main(String[] args)
	{
		String s = "select * from p_s_func t where 1 = 1 ##abc#";
		System.out.println(s.replaceAll("#.*#", " "));
	}
}
