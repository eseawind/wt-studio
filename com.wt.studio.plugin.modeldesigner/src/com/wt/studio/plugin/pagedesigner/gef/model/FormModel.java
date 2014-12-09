package com.wt.studio.plugin.pagedesigner.gef.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class FormModel extends BlockModel implements IPropertySource
{
	String name="Title";
	String id="panel";
	public Integer title=0;
	public static final String PROP_NAME = "Title", PROP_TYPE = "Model Type",PROP_TITLE_STATUS="是否启用标题", PROP_ID="ID";
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new ComboBoxPropertyDescriptor(PROP_TITLE_STATUS, PROP_TITLE_STATUS,
						new String[] { "启用", "停用" }),
						new TextPropertyDescriptor(PROP_HEIGHT,PROP_HEIGHT),
						new TextPropertyDescriptor(PROP_WIDTH,PROP_WIDTH),
		};
	}

	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 200;
		}
		if (rectangle.height < 0) {
			rectangle.height = 200;
		}// TODO Auto-generated method stub
		return rectangle;
	}


	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
		 fireStructureChange(PROP_NAME ,"Name");
	}
	public String getId()
	{
		return this.id;
	}
	public void setId(String id)
	{
		this.id=id;
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
		} else if (PROP_TITLE_STATUS.equals(id)) {
			return this.getDefaultShowTitle();
		} else if (PROP_ID.equals(id)) {
			return this.getId();
		} else if(PROP_WIDTH.equals(id))
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
		} else if (PROP_TITLE_STATUS.equals(id)) {
			setDefaultShowTitle((Integer)value);
		} else if (PROP_ID.equals(id)) {
			setId((String)value);
		} else if(PROP_WIDTH.equals(id)){
			this.setWidth(((String)value));
		}else if(PROP_HEIGHT.equals(id))
			this.setHeight(((String)value));
	}
	public Integer getDefaultShowTitle()
	{
		return title;
	}

	public void setDefaultShowTitle(Integer defaultShowData)
	{
		this.title = defaultShowData;
		fireStructureChange(PROP_TITLE_STATUS);
	}


}
