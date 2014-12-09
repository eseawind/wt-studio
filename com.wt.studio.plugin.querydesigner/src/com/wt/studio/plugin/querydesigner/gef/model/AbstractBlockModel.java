package com.wt.studio.plugin.querydesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class AbstractBlockModel extends Element
{
	/**
	 * 
	 */

	public static final String PROP_BLOCKMODELS = "blockModels";
	private static final long serialVersionUID = 3968565016457018410L;
	public static final String PROP_NAME = "name";
	public static final String PROP_PARAMS = "blocks";
	public String id;
	public String blockName;

	public List<Element> result = new ArrayList<Element>();

	// public String name = "模块";
	// private List<ChartModel> charts = new ArrayList<ChartModel>();

	public List<Element> getResult()
	{
		return result;
	}

	public void setResult(List<Element> result)
	{
		this.result = result;
	}

	public String getBlockName()
	{
		return blockName;
	}

	public void setBlockName(String name)
	{
		this.blockName = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
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
