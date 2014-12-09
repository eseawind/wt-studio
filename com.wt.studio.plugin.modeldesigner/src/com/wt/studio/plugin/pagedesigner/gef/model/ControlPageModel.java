package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;




public class ControlPageModel  extends BlockModel implements IPropertySource
{
	private static final long serialVersionUID = -8356627096473811653L;
	private String name="page";
	public static final String PROP_BLOCKMODELS = "blockmodels";
	public static final String PROP_NAME = "Name", PROP_TYPE = "Page Type";
	List<HorizonBlockModel> horizonModels = new ArrayList<HorizonBlockModel>();
	List<VerticalBlockModel> verticalModels= new ArrayList<VerticalBlockModel>();
	List<Element> elements= new ArrayList<Element>();
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_HEIGHT,PROP_HEIGHT),
				new TextPropertyDescriptor(PROP_WIDTH,PROP_WIDTH),
		};
	}
	public  void setName(String name)
	{
		this.name=name;
		firePropertyChange(PROP_NAME, null, name);
	}
    public String  getName()
    {	
    	return this.name;
    }
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
		}else if(PROP_WIDTH.equals(id)){
			this.setWidth(((String)value));
		}else if(PROP_HEIGHT.equals(id))
			this.setHeight(((String)value));
	}
	@Override
	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 400;
		}
		if (rectangle.height < 0) {
			rectangle.height = 500;
		}// TODO Auto-generated method stub
		return rectangle;
	}
}
