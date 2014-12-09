package com.wt.studio.plugin.querydesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public abstract class BlockModel extends Element implements IPropertySource
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3968565016457018409L;
	public static final String PROP_NAME = "name";
	public static final String PROP_PARAMS = "params";
	public static final String PROP_TYPE = "type";
	public static final String PROP_BLOCKMODELS = "blockModels";
	public static final String PROP_QUERYBLOCKMODELS = "queryBlockModels";
	public static final String PROP_CHARTBLOCKMODELS = "chartBlockModels";
	public static final String PROP_TABLES = "tables", PROP_ID = "ID*", PROP_TNAME = "功能名称",
			PROP_DESC = "描述", PROP_STATUS = "状态", PROP_SHOW_DATA = "默认显示数据";
	public String id;
	public String name = "block";
	List<Element> result = new ArrayList<Element>();
	public final static String TYPE_QUERY = "QUERY", TYPE_TABLE = "TABLE", TYPE_CHART = "CHART",
			TYPE_VERTICAL = "VERTICAL", TYPE_HORIZONTAL = "HORIZONTAL", TYPE_PAGE = "PAGE",
			TYPE_QUERY_HORIZONTAL = "QUERYHORIZONTAL";

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(PROP_NAME, "name") };
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
	public boolean isPropertySet(Object id)
	{
		return true;
	}

	@Override
	public void resetPropertyValue(Object id)
	{
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

	public void setChildName(String name)
	{
		this.name = name;
	}

	public void addElement(int index, Element element)
	{
		if (index == -1)
			result.add(element);
		else
			result.add(index, element);
		fireStructureChange(PROP_BLOCKMODELS, PROP_BLOCKMODELS);
	}

	public void removeElement(Element element)
	{
		result.remove(element);
		fireStructureChange(PROP_BLOCKMODELS, PROP_BLOCKMODELS);
	}

	public List<Element> getElements()
	{
		return result;
	}

	public void addAllElement(List<ColumnModel2> columns)
	{
		result.addAll(columns);
		fireStructureChange(PROP_BLOCKMODELS, PROP_BLOCKMODELS);

	}

	public void removeAllElements()
	{
		result.clear();
		fireStructureChange(PROP_BLOCKMODELS, PROP_BLOCKMODELS);
	}

	public void reRank()
	{
		fireStructureChange(PROP_BLOCKMODELS, PROP_BLOCKMODELS);
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public abstract String getType();

	@Override
	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 100;
		}
		if (rectangle.height < 0) {
			rectangle.height = 30;
		}
		return rectangle;
	}
}
