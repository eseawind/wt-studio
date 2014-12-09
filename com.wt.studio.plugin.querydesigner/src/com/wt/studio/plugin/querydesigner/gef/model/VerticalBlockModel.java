package com.wt.studio.plugin.querydesigner.gef.model;

public class VerticalBlockModel extends BlockModel
{
	/**
	 * 
	 */
	private String name = "垂直块";
	private static final long serialVersionUID = 430838171243301833L;

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

	@Override
	public String getType()
	{
		return TYPE_VERTICAL;
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
}
