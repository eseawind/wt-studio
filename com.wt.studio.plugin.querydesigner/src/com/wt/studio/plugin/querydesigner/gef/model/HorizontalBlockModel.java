package com.wt.studio.plugin.querydesigner.gef.model;

public class HorizontalBlockModel extends BlockModel
{
	private static final long serialVersionUID = 430838171243301833L;
	private String name = "水平块";

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
		firePropertyChange(PROP_NAME, null, name);
	}

	public void setChildName(String name)
	{
		this.name = name;
	}

	public Object getPropertyValue(Object id)
	{
		if (PROP_NAME.equals(id)) {
			return this.getName();
		}
		return null;
	}

	public void setPropertyValue(Object id, Object value)
	{
		if (PROP_NAME.equals(id)) {
			this.setName((String) value);
		}
	}

	@Override
	public String getType()
	{
		return TYPE_HORIZONTAL;
	}
}
