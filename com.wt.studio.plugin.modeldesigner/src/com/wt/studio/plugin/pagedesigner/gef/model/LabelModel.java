package com.wt.studio.plugin.pagedesigner.gef.model;

import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;



public class LabelModel extends ControlModel 
{

	private static final long serialVersionUID = -3820179531098967148L;
	private Font font;
	
	
	public LabelModel()
	{
		id="Title";
		name="标题";
	}
    
	@Override
	public String getType()
	{
		// TODO Auto-generated method stub
		return TYPE_LABEL;
	}

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_TYPE, PROP_TYPE),
				new TextPropertyDescriptor(PROP_HEIGHT,PROP_HEIGHT),
				new TextPropertyDescriptor(PROP_WIDTH,PROP_WIDTH),
		};
	}
	
	@Override
	public Object getEditableValue()
	{
		return this;
	}
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		return descriptors;
	}


	
	@Override
	public Object getPropertyValue(Object id)
	{
		if (PROP_NAME.equals(id)) {
			return this.getName();
		}else if(PROP_ID.equals(id))
			return this.getUuid();
		else if(PROP_TYPE.equals(id))
			return this.getType();
		else if(PROP_WIDTH.equals(id))
			return this.getWidth();
		else if(PROP_HEIGHT.equals(id))
			return this.getHeight();
		return null;
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
		} else if(PROP_ID.equals(id))
			this.setUuid((String)value);
		else if(PROP_WIDTH.equals(id)){
			this.setWidth(((String)value));
		}else if(PROP_HEIGHT.equals(id))
			this.setHeight(((String)value));
	}
}
