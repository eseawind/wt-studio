package com.wt.studio.plugin.querydesigner.gef.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.wt.studio.plugin.querydesigner.gef.descriptor.ChartModelDialogPropertyDescriptor;
import com.wt.studio.plugin.querydesigner.model.ChartSqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.SqlSet;

public class ChartBlockModel extends AbstractBlockModel implements IPropertySource
{
	/**
	 * 
	 */

	private static final long serialVersionUID = 3968565016457018410L;
	// public static final String PROP_PARAMS = "charts";
	public static final String PROP_NAME = "标题", PROP_TYPE = "type", PROP_COLUMNS = "columns",
			PROP_WIDTH = "宽度", PROP_HEIGHT = "高度", PROP_SQL = "图表配置", PROP_CHART_TYPE = "图表类型";
	public String name = "图表", width = "100%", height = "300";
	private SqlSet sqlSet;
	private String chartType = "column2DSingle";
	private String chartTypeDisplayName = "单垂直柱状图-2D";

	public String getChartTypeDisplayName()
	{
		return chartTypeDisplayName;
	}

	public void setChartTypeDisplayName(String chartTypeDisplayName)
	{
		this.chartTypeDisplayName = chartTypeDisplayName;
	}

	public void setChartType(String chartType)
	{
		this.chartType = chartType;
		fireStructureChange(PROP_CHART_TYPE, PROP_CHART_TYPE);
	}

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_WIDTH, PROP_WIDTH),
				new TextPropertyDescriptor(PROP_HEIGHT, PROP_HEIGHT),
				new ChartModelDialogPropertyDescriptor(PROP_SQL, PROP_SQL),
				new TextPropertyDescriptor(PROP_CHART_TYPE, PROP_CHART_TYPE) };
	}

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
		return false;
	}

	@Override
	public void resetPropertyValue(Object id)
	{
	}

	@Override
	public Object getPropertyValue(Object id)
	{
		if (PROP_NAME.equals(id)) {
			return this.getName();
		} else if (PROP_WIDTH.equals(id)) {
			return this.getWidth();
		} else if (PROP_HEIGHT.equals(id)) {
			return this.getHeight();
		} else if (PROP_SQL.equals(id)) {
			return this.sqlSet;
		} else if (PROP_CHART_TYPE.equals(id)) {
			if (this.sqlSet != null) {
				this.setChartType(this.sqlSet.getChartType().getName());
				this.setChartTypeDisplayName(this.sqlSet.getChartType().getDisplayName());
			}
			return this.chartTypeDisplayName;
		}
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value)
	{
		if (PROP_NAME.equals(id)) {
			this.setName((String) value);
		} else if (PROP_WIDTH.equals(id)) {
			this.setWidth((String) value);
		} else if (PROP_HEIGHT.equals(id)) {
			this.setHeight((String) value);
		} else if (PROP_SQL.equals(id)) {
			SqlSet model = (SqlSet) value;
			if (model != null) {
				this.sqlSet = model;
				this.setBlockName(model.getName());
			}
		}
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		if (!this.width.equals(width)) {
			this.width = width;
			firePropertyChange(PROP_WIDTH, null, width);
		}
	}

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String height)
	{
		if (!this.height.equals(height)) {
			this.height = height;
			firePropertyChange(PROP_HEIGHT, null, height);
		}
	}

	public List<ChartSqlAreaModel> getSqls()
	{
		return this.sqlSet.getSqls();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public SqlSet getSqlSet()
	{
		return sqlSet;
	}

	public void setSqlSet(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;
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

	public String getChartType()
	{
		return this.chartType == null ? "column2DSingle" : this.chartType;
	}

	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 100;
		}
		if (rectangle.height < 0) {
			rectangle.height = 200;
		}
		return rectangle;
	}
}
