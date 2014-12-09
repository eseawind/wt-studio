package com.wt.studio.plugin.pagedesigner.gef.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public abstract class ControlModel extends Element implements IPropertySource
{
	private static final long serialVersionUID = 1420404349187514051L;
	public static final String PROP_NAME = "Title", PROP_TYPE = "Type",PROP_ID="ID";
	public static final String TYPE_INPUT = "INPUT", TYPE_BUTTON = "BUTTON", TYPE_CHECK = "CHECK",TYPE_TEXTAREA = "TEXTAREA",
			TYPE_LABEL = "LABEL",TYPE_DATE="DATE",TYPE_TABLE="TABLE",TYPE_LIST="LIST",TYPE_PICTURE="PICTURE",
			TYPE_EDITOR="EDITOR",TYPE_RADIO="RADIO";
	public static final String PROP="changeProp";
	public String id;
	public String name;
	private String uuid=uuid();
	public String getUuid()
	{
		return uuid;
	}
	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
		fireStructureChange(PROP_NAME ,"Name");
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	//private String description = "图形";
	private String type;
	public abstract String getType();
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

	@Override
	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
		rectangle.width = 120;
	    }
	
		if (rectangle.height < 0) {
		rectangle.height = 38;
	    }
	    return rectangle;
	}
	
}
