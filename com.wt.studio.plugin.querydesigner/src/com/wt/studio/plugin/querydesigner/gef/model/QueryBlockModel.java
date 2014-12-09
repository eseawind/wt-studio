package com.wt.studio.plugin.querydesigner.gef.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class QueryBlockModel extends AbstractBlockModel implements IPropertySource
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3968565016457018409L;
	public static final String PROP_PARAMS = "params";
	private String name = "查询参数";
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] { new PropertyDescriptor(PROP_NAME, "标题") };
	}

	public Object getPropertyValue(Object id)
	{
		if (PROP_NAME.equals(id)) {
			return this.getName();
		}
		return null;
	}

	public Object getEditableValue()
	{
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		return descriptors;
	}

	@Override
	public boolean isPropertySet(Object id)
	{
		return false;
	}

	@Override
	public void resetPropertyValue(Object id)
	{
	}

	@Override
	public void setPropertyValue(Object id, Object value)
	{
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
		firePropertyChange(PROP_NAME, null, name);
	}

	public void reRank()
	{
		fireStructureChange(PROP_PARAMS, null);
	}

}
