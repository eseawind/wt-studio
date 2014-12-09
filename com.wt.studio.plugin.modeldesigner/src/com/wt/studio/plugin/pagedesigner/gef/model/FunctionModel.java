package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;

public  class FunctionModel extends Element implements IPropertySource
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -27169803682450905L;
	public static final String PROP_NAME = "name";
	public static final String  PROP_COLUMN="column";
	private String id;
	public String title;
    protected List<FunctionColumnModel> columns=new ArrayList<FunctionColumnModel>();
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
		firePropertyChange(PROP_NAME, null, title);
	}

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(PROP_NAME, "title") 
				};
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
			return this.getTitle();
		}
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
			this.setTitle((String) value);
			firePropertyChange(PROP_NAME, null, title);
		}
	}
	
	public List<FunctionColumnModel> getColumns()
	{
		return columns;
	}
	public void setColumns(List<FunctionColumnModel> columns)
	{
		this.columns = columns;
	}
	public void addColumn(FunctionColumnModel column)
	{
		this.columns.add(column);
		fireStructureChange(PROP_COLUMN,column);
	}

	public void removeColumn(FunctionColumnModel column)
	{
		this.columns.remove(column);
		fireStructureChange(PROP_COLUMN,column);
	}
	@Override
	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 200;
		}
		if (rectangle.height < 0) {
			rectangle.height = 220;
		}// TODO Auto-generated method stub
		return rectangle;
	}
}
