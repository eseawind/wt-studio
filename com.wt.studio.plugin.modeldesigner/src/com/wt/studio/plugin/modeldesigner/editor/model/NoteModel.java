package com.wt.studio.plugin.modeldesigner.editor.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
public class NoteModel  extends BONodeModel implements IPropertySource
{
	private static final long serialVersionUID = 3968565016457018409L;
	public static final String PROP_NAME = "name";
	public static final String PROP_TYPE = "Note";
	//public static final String PROP_PARAMS = "params";
	private String id;
	private String name = "便签";
	private String type="Note";
	private String text;
	
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
	public void setText(String text)
	{
		this.text=text;
		firePropertyChange(PROP_NAME, null, name);
	}
	public String getText()
	{
		return this.text;
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
		return rectangle;
	}

}
