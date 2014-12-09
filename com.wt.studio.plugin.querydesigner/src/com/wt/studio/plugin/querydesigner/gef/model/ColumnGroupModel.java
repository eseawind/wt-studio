package com.wt.studio.plugin.querydesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class ColumnGroupModel extends AbstractBlockModel implements IPropertySource
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909574304864368749L;
	public static final String PROP_COLUMNS = "columns", PROP_COLUMNGROUPS = "columnGroups";
	public static final String PROP_TITLE = "列组名称";
	public String title = "默认列组";
	private List<ColumnModel2> columns = new ArrayList<ColumnModel2>();
	private List<ColumnGroupModel> columnGroups = new ArrayList<ColumnGroupModel>();
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(PROP_TITLE, PROP_TITLE) };
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
		firePropertyChange(PROP_TITLE, null, title);
	}

	@Override
	public Object getEditableValue()
	{
		return this;
	}

	public void addColumn(ColumnModel2 col)
	{
		super.addElement(-1, col);
		this.columns.add(col);
		fireStructureChange(PROP_COLUMNS, this.columns);
	}

	public void removeColumn(ColumnModel2 col)
	{
		super.removeElement(col);
		this.columns.remove(col);
		fireStructureChange(PROP_COLUMNS, this.columns);
	}

	public List<ColumnModel2> getColumns()
	{
		return this.columns;
	}

	public void addColumnGroup(ColumnGroupModel columnGroup)
	{
		super.addElement(-1, columnGroup);
		this.columnGroups.add(columnGroup);
		fireStructureChange(PROP_COLUMNGROUPS, this.columnGroups);
	}

	public void removeColumnGroup(ColumnGroupModel columnGroup)
	{
		super.removeElement(columnGroup);
		this.columnGroups.remove(columnGroup);
		fireStructureChange(PROP_COLUMNGROUPS, this.columnGroups);
	}

	public List<ColumnGroupModel> getColumnGroups()
	{
		return this.columnGroups;
	}

	public void addAllColumn(List<ColumnModel2> columns)
	{
		super.addAllElement(columns);
		fireStructureChange(PROP_COLUMNS, this.columns);
	}

	public void removeAllColumn()
	{
		this.columnGroups.clear();
		this.columns.clear();
		super.removeAllElements();
		fireStructureChange(PROP_COLUMNS, this.columns);
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		return descriptors;
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
	public Object getPropertyValue(Object id)
	{
		if (PROP_TITLE.equals(id)) {
			return this.getTitle();
		}
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value)
	{
		if (PROP_TITLE.equals(id)) {
			this.setTitle((String) value);
		}
	}

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
