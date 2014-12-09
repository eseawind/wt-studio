package com.wt.studio.plugin.querydesigner.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;

import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;

public class DataObjectModel implements Serializable
{
	private static final long serialVersionUID = -8666067456752708310L;
	private IConnectionProfile connectionProfile;
	private String name;
	private String sql;
	private String id;
	private String desc;
	/**
	 * 01 对象 02 Folder
	 */
	private String type;
	private long order;
	private String parentId;
	private String dsId;
	private List<ColumnModel> cms;

	public IConnectionProfile getConnectionProfile()
	{
		return connectionProfile;
	}

	public void setConnectionProfile(IConnectionProfile connectionProfile)
	{
		this.connectionProfile = connectionProfile;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public List<ColumnModel> getCms()
	{
		return cms;
	}

	public void setCms(List<ColumnModel> cms)
	{
		this.cms = cms;
	}

	public void setCms()
	{
		List<ColumnModel> cms = new ArrayList<ColumnModel>();
		for (String s : CommonEclipseUtil.getColumnsFromSql(this.connectionProfile, this.sql)) {
			ColumnModel cm = new ColumnModel();
			cm.setName(s);
			cms.add(cm);
		}
		this.setCms(cms);
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
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

	public long getOrder()
	{
		return order;
	}

	public void setOrder(long order)
	{
		this.order = order;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getDsId()
	{
		return dsId;
	}

	public void setDsId(String dsId)
	{
		this.dsId = dsId;
	}
}
