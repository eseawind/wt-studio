package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertySource;

public class TableControlModel  extends ControlModel implements IPropertySource
{
	private static final long serialVersionUID = -3820179531098967148L;
	private List<ColumnModel> children=new ArrayList<ColumnModel>();
	public static String PROP_CHILDREN="children";
	public List<ColumnModel> getChildren()
	{
		return children;
	}

	public void setChildren(List<ColumnModel> children)
	{
		this.children = children;
		firePropertyChange(PROP_CHILDREN, null, children);
	}

	public TableControlModel()
	{
		 name="Table";
		 id="Table";
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
	@Override
	public String getType()
	{
		// TODO Auto-generated method stub
		return TYPE_TABLE;
	}

	public void refresh()
	{
		// TODO Auto-generated method stub
		firePropertyChange(PROP_CHILDREN, null, null);
	}

}
