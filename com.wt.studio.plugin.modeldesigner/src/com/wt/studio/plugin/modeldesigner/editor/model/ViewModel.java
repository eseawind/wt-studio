package com.wt.studio.plugin.modeldesigner.editor.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class ViewModel extends BONodeModel implements IPropertySource
{
	private static final long serialVersionUID = 3968565016457018409L;
	public static final String PROP_NAME = "name";
	public static final String PROP_TYPE = "View";
	public static final String PROP_COLUMNS = "Columns";
	//public static final String PROP_PARAMS = "params";
	private String id;
	private String name = "表视图";
	private String type="View";
	
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] { new PropertyDescriptor(PROP_NAME, "name"), 
				new PropertyDescriptor(PROP_TYPE, "type") };
	}

	@Override
	
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
	public Object getPropertyValue(Object id)
	{
		if (PROP_NAME.equals(id)) {
			return this.getName();
		}else if (PROP_TYPE.equals(id)) {
			return this.getType();
		}
		return null;
	}

	private String getType()
	{
		
		return type;
	}

	@Override
	public boolean isPropertySet(Object id)
	{
		return true;
	}

	@Override
	public void resetPropertyValue(Object id)
	{
	}

	@Override
	public void setPropertyValue(Object id, Object value)
	{
		if (PROP_NAME.equals(id)) {
			this.setName((String) value);
		}
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

	

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 200;
		}
		if (rectangle.height < 0) {
			rectangle.height = 200;
		}
		return rectangle;
	}

}
