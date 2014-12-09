package com.wt.studio.plugin.querydesigner.gef.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class TextAreaModel extends AbstractBlockModel implements IPropertySource
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1020832608315397965L;
	/**
	 * 
	 */
	public static final String PROP_NAME = "标题", PROP_WIDTH = "宽度", PROP_HEIGHT = "高度";
	public String name = "段落", width = "100%", height = "300";
	public String textArea = "段落文字";

	public String getTextArea()
	{
		return textArea;
	}

	public void setTextArea(String textArea)
	{
		this.textArea = textArea;
		firePropertyChange(PROP_NAME, null, name);
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

	public String getWidth()
	{
		return width;

	}

	public void setWidth(String width)
	{
		this.width = width;
		firePropertyChange(PROP_WIDTH, null, width);
	}

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String height)
	{
		this.height = height;
		firePropertyChange(PROP_HEIGHT, null, height);
	}

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_WIDTH, PROP_WIDTH),
				new TextPropertyDescriptor(PROP_HEIGHT, PROP_HEIGHT), };
	}

	@Override
	public Object getEditableValue()
	{
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		// TODO Auto-generated method stub
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id)
	{
		if (PROP_NAME.equals(id)) {
			return this.getName();
		} else if (PROP_WIDTH.equals(id)) {
			return this.getWidth();
		} else if (PROP_HEIGHT.equals(id)) {
			return this.getHeight();
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setPropertyValue(Object id, Object value)
	{
		// TODO Auto-generated method stub
		if (PROP_NAME.equals(id)) {
			this.setName((String) value);
		} else if (PROP_WIDTH.equals(id)) {
			this.setWidth((String) value);
		} else if (PROP_HEIGHT.equals(id)) {
			this.setHeight((String) value);
		}
	}

}