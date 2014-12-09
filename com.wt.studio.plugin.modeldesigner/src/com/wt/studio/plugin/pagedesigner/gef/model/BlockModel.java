package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class BlockModel extends Element implements IPropertySource
{
	private static final long serialVersionUID = 430838171243301833L;
	public String type;
	public String uuid=uuid();
	List<Element> result = new ArrayList<Element>();
	public static final String PROP_TYPE = "Type",PROP_ID="ID";
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_TYPE, PROP_TYPE),
				new TextPropertyDescriptor(PROP_HEIGHT,PROP_HEIGHT),
				new TextPropertyDescriptor(PROP_WIDTH,PROP_WIDTH),
		};
	}

	public static String PROP_ELEMENTS="element";
	public void addElement(int i,Element element)
	{
		if(i==-1)
		    result.add(element);
		else
			result.add(i, element);
		fireStructureChange(PROP_ELEMENTS ,"element");
	}
	public void removeElement(Element element)
	{
		result.remove(element);
		fireStructureChange(PROP_ELEMENTS ,"element");
	}
	public List<Element> getAllElement()
	{
		return this.result;
	}

	 public String getUuid()
	{
		return uuid;
	}
    public void setUuid(String uuid)
	{
		this.uuid = uuid;
		firePropertyChange(PROP_ID, null, uuid);
	}
	public String getName()
	{
		return this.type;
	}
	public void setName(String name)
	{
		this.type=name;
		firePropertyChange(PROP_TYPE, null, name);
	}

	public void reRank()
	{
		fireStructureChange(PROP_ELEMENTS ,"element");
		
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
		if (PROP_TYPE.equals(id)) {
			return this.getName();
		}else if (PROP_ID.equals(id)){
			return this.getUuid();
		}else if(PROP_WIDTH.equals(id))
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

	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 200;
		}
		if (rectangle.height < 0) {
			rectangle.height = 40;
		}// TODO Auto-generated method stub
		return rectangle;
	}
	@Override
	public void setPropertyValue(Object id, Object value)
	{
		if (PROP_ID.equals(id)){
			this.setUuid((String)value);
		}else if(PROP_WIDTH.equals(id)){
			this.setWidth(((String)value));
		}else if(PROP_HEIGHT.equals(id))
			this.setHeight(((String)value));
	}


}
