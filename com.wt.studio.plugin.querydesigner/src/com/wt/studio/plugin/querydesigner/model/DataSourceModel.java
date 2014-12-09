package com.wt.studio.plugin.querydesigner.model;

import java.util.ArrayList;
import java.util.List;

public class DataSourceModel
{
	private String id;
	private String name;
	private String desc;
	private String type;
	private String url;
	private String user;
	private String pass;
	private List<DataObjectModel> objectModels = new ArrayList<DataObjectModel>();

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPass()
	{
		return pass;
	}

	public void setPass(String pass)
	{
		this.pass = pass;
	}

	public List<DataObjectModel> getObjectModels()
	{
		return objectModels;
	}

	public void setObjectModels(List<DataObjectModel> objectModels)
	{
		this.objectModels = objectModels;
	}

}
