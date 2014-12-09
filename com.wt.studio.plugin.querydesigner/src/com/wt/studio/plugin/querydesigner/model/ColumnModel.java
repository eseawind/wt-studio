package com.wt.studio.plugin.querydesigner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColumnModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7109505840418130764L;
	private String sql;
	private String name;
	private String description;
	private int type;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public static List<ColumnModel> getCols()
	{
		List<ColumnModel> result = new ArrayList<ColumnModel>();
		for (int i = 0; i < new Random().nextInt(10); i++) {
			ColumnModel col = new ColumnModel();
			col.setDescription("desc " + i);
			col.setName("name " + i);
			col.setType(0);
			result.add(col);
		}
		return result;
	}
}
