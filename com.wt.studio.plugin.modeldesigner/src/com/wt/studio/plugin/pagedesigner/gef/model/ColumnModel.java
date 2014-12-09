package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class ColumnModel extends Element implements IPropertySource
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5957555244248408536L;
	private String title="Column";
	private List<ColumnModel>children=new ArrayList<ColumnModel>();
	public static final String PROP_TITLE="title",PROP_CHILDREN="children";
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
		firePropertyChange(PROP_TITLE, null, title);
	}
	public List<ColumnModel> getChildren()
	{
		return children;
	}
	public void setChildren(List<ColumnModel> children)
	{
		this.children = children;
		firePropertyChange(PROP_CHILDREN, null, children);
	}
	public void addColumn(ColumnModel column)
	{
		this.children.add(column);
		firePropertyChange(PROP_CHILDREN, null, column);
	}
	public void removeColumn(ColumnModel column)
	{
		this.children.remove(column);
		firePropertyChange(PROP_CHILDREN, null, column);
	}
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_TITLE, PROP_TITLE),
				new TextPropertyDescriptor(PROP_HEIGHT,PROP_HEIGHT),
				new TextPropertyDescriptor(PROP_WIDTH,PROP_WIDTH),
		};
	}
	@Override
	public Rectangle getRectangle()
	{
		if(this.rectangle.width<50)
			this.rectangle.width=50;
		if(this.rectangle.height<100)
			this.rectangle.height=100;
		// TODO Auto-generated method stub
		return this.rectangle;
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
		if (PROP_TITLE.equals(id)) {
			return this.getTitle();
		}else if(PROP_WIDTH.equals(id))
			return this.getWidth();
		else if(PROP_HEIGHT.equals(id))
			return this.getHeight();
		return null;
	}
	@Override
	public boolean isPropertySet(Object arg0)
	{
		// TODO Auto-generated method stub
		return true;
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
		if(PROP_TITLE.equals(id))
			this.setTitle((String)value);
	}
	public void refresh()
	{
		// TODO Auto-generated method stub
		firePropertyChange(PROP_CHILDREN, null, null);
	}

}
