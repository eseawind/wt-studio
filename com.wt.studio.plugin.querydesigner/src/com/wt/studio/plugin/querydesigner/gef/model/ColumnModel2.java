package com.wt.studio.plugin.querydesigner.gef.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.wt.studio.plugin.querydesigner.utils.Utils;

public class ColumnModel2 extends Element implements IPropertySource, Comparable<ColumnModel2>
{
	private static final long serialVersionUID = 3045174800695684767L;

	@Override
	public Object getEditableValue()
	{
		return this;
	}

	public static final String[] LABEL_OPERATIONS = { "", "sum", "avg" };
	public static final String[] LABEL_ALIGNS = { "left", "center", "right" };
	public static final String[] LABEL_YES_NO = { "Y", "N" };
	public static final String[] LABEL_TARGETS = { "_self", "_blank", "_iframe" };

	public static final String PROP_NAME = "列名", PROP_TYPE = "Param Type",
			PROP_DESCIPTION = "显示名称", PROP_ORDER = "顺序", PROP_IFSHOW = "显示与否", PROP_ALIGN = "对齐方式",
			PROP_CLICKURL = "链接", PROP_PARAMKEY = "参数key", PROP_CLICKURLTARGET = "链接target",
			PROP_OPERATION = "列操作", PROP_FORMATTER = "列格式", PROP_FORMATTEROPTIONS = "列格式参数",
			PROP_IFHIDE = "在显示结果中隐藏", PROP_PARAMVALUECOLUMNNAME = "参数value列", PROP_CORD = "列属组",
			PROP_WIDTH = "列宽", PROP_HIERARCHY = "层级", PROP_COLSPAN_STYLE = "是否列合并",
			PROP_TITLE = "分组列显示名称", PROP_GROUP_ORDER = "组内排序号", PROP_IS_FROZEN = "是否冻结",
			PROP_IS_GROUP = "是否分组";
	private String name = "column1", description = "列1", type = "", order = "01", ifshow = "Y",
			align = "left", clickurl = "", paramKey = "", clickUrlTarget = LABEL_TARGETS[0],
			options = "", formatter = "", formatterOptions = "", ifHide = "N",
			paramValueColumnName = "", cord = "", width = "", hierarchy = "", colspanStyle = "N",
			groupTitle = "", groupOrder = "", isFrozen = "N", isGroup = "N";

	public String getIsGroup()
	{
		return isGroup;
	}

	public void setIsGroup(String isGroup)
	{
		this.isGroup = isGroup;
		firePropertyChange(PROP_NAME, null, name);
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getHierarchy()
	{
		return hierarchy;
	}

	public void setHierarchy(String hierarchy)
	{
		this.hierarchy = hierarchy;
	}

	public String getColspanStyle()
	{
		return colspanStyle;
	}

	public void setColspanStyle(String colspanStyle)
	{
		this.colspanStyle = colspanStyle;
	}

	public String getGroupTitle()
	{
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle)
	{
		this.groupTitle = groupTitle;
	}

	public String getGroupOrder()
	{
		return groupOrder;
	}

	public void setGroupOrder(String groupOrder)
	{
		this.groupOrder = groupOrder;
	}

	public String getIsFrozen()
	{
		return isFrozen;
	}

	public void setIsFrozen(String isFrozen)
	{
		this.isFrozen = isFrozen;
	}

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new PropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_DESCIPTION, PROP_DESCIPTION),
				new ComboBoxPropertyDescriptor(PROP_IFSHOW, PROP_IFSHOW, LABEL_YES_NO),
				new TextPropertyDescriptor(PROP_WIDTH, PROP_WIDTH),
				new TextPropertyDescriptor(PROP_ORDER, PROP_ORDER),
				new ComboBoxPropertyDescriptor(PROP_ALIGN, PROP_ALIGN, LABEL_ALIGNS),
				new TextPropertyDescriptor(PROP_FORMATTER, PROP_FORMATTER),
				new TextPropertyDescriptor(PROP_FORMATTEROPTIONS, PROP_FORMATTEROPTIONS),
				new ComboBoxPropertyDescriptor(PROP_OPERATION, PROP_OPERATION, LABEL_OPERATIONS),
				new ComboBoxPropertyDescriptor(PROP_IFHIDE, PROP_IFHIDE, LABEL_YES_NO),
				new TextPropertyDescriptor(PROP_CLICKURL, PROP_CLICKURL),
				new ComboBoxPropertyDescriptor(PROP_CLICKURLTARGET, PROP_CLICKURLTARGET,
						LABEL_TARGETS), new TextPropertyDescriptor(PROP_PARAMKEY, PROP_PARAMKEY),
				new TextPropertyDescriptor(PROP_PARAMVALUECOLUMNNAME, PROP_PARAMVALUECOLUMNNAME),
				new TextPropertyDescriptor(PROP_CORD, PROP_CORD),
				new TextPropertyDescriptor(PROP_GROUP_ORDER, PROP_GROUP_ORDER),
				new ComboBoxPropertyDescriptor(PROP_IS_FROZEN, PROP_IS_FROZEN, LABEL_YES_NO),
				new ComboBoxPropertyDescriptor(PROP_IS_GROUP, PROP_IS_GROUP, LABEL_YES_NO) };
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id)
	{
		if (PROP_ALIGN.equals(id)) {
			return Utils.search(LABEL_ALIGNS, this.getAlign());
		} else if (PROP_CLICKURL.equals(id)) {
			return this.getClickurl();
		} else if (PROP_CLICKURLTARGET.equals(id)) {
			return Utils.search(LABEL_TARGETS, this.getClickUrlTarget());
		} else if (PROP_CORD.equals(id)) {
			return this.getCord();
		} else if (PROP_DESCIPTION.equals(id)) {
			return this.getDescription();
		} else if (PROP_FORMATTER.equals(id)) {
			return this.getFormatter();
		} else if (PROP_FORMATTEROPTIONS.equals(id)) {
			return this.getFormatterOptions();
		} else if (PROP_IFHIDE.equals(id)) {
			return Utils.search(LABEL_YES_NO, this.getIfHide());
		} else if (PROP_IFSHOW.equals(id)) {
			return Utils.search(LABEL_YES_NO, this.getIfshow());
		} else if (PROP_NAME.equals(id)) {
			return this.getName();
		} else if (PROP_OPERATION.equals(id)) {
			return Utils.search(LABEL_OPERATIONS, this.getOptions());
		} else if (PROP_ORDER.equals(id)) {
			return this.getOrder();
		} else if (PROP_PARAMKEY.equals(id)) {
			return this.getParamKey();
		} else if (PROP_PARAMVALUECOLUMNNAME.equals(id)) {
			return this.getParamValueColumnName();
		} else if (PROP_WIDTH.equals(id)) {
			return this.getWidth();
		} else if (PROP_GROUP_ORDER.equals(id)) {
			return this.getGroupOrder();
		} else if (PROP_IS_FROZEN.equals(id)) {
			return Utils.search(LABEL_YES_NO, this.getIsFrozen());
		} else if (PROP_IS_GROUP.equals(id)) {
			return Utils.search(LABEL_YES_NO, this.getIsGroup());
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
		if (PROP_ALIGN.equals(id)) {
			this.setAlign(LABEL_ALIGNS[(Integer) value]);
		} else if (PROP_CLICKURL.equals(id)) {
			this.setClickurl((String) value);
		} else if (PROP_CLICKURLTARGET.equals(id)) {
			this.setClickUrlTarget(LABEL_TARGETS[(Integer) value]);
		} else if (PROP_CORD.equals(id)) {
			this.setCord((String) value);
		} else if (PROP_DESCIPTION.equals(id)) {
			this.setDescription((String) value);
		} else if (PROP_FORMATTER.equals(id)) {
			this.setFormatter((String) value);
		} else if (PROP_FORMATTEROPTIONS.equals(id)) {
			this.setFormatterOptions((String) value);
		} else if (PROP_IFHIDE.equals(id)) {
			this.setIfHide(LABEL_YES_NO[(Integer) value]);
		} else if (PROP_IFSHOW.equals(id)) {
			this.setIfshow(LABEL_YES_NO[(Integer) value]);
		} else if (PROP_NAME.equals(id)) {
			this.setName((String) value);
		} else if (PROP_OPERATION.equals(id)) {
			this.setOptions(LABEL_OPERATIONS[(Integer) value]);
		} else if (PROP_ORDER.equals(id)) {
			this.setOrder((String) value);
		} else if (PROP_PARAMKEY.equals(id)) {
			this.setParamKey((String) value);
		} else if (PROP_PARAMVALUECOLUMNNAME.equals(id)) {
			this.setParamValueColumnName((String) value);
		} else if (PROP_WIDTH.equals(id)) {
			this.setWidth((String) value);
		} else if (PROP_GROUP_ORDER.equals(id)) {
			this.setGroupOrder((String) value);
		} else if (PROP_IS_FROZEN.equals(id)) {
			this.setIsFrozen(LABEL_YES_NO[(Integer) value]);
		} else if (PROP_IS_GROUP.equals(id)) {
			this.setIsGroup(LABEL_YES_NO[(Integer) value]);
		}
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
		firePropertyChange(PROP_DESCIPTION, null, type);
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
		firePropertyChange(PROP_ORDER, null, order);
	}

	public String getIfshow()
	{
		return ifshow;
	}

	public void setIfshow(String ifshow)
	{
		this.ifshow = ifshow;
		firePropertyChange(PROP_IFSHOW, null, ifshow);
	}

	public String getAlign()
	{
		return align;
	}

	public void setAlign(String align)
	{
		this.align = align;
		firePropertyChange(PROP_ALIGN, null, align);
	}

	public String getClickurl()
	{
		return clickurl;
	}

	public void setClickurl(String clickurl)
	{
		this.clickurl = clickurl;
		firePropertyChange(PROP_CLICKURL, null, clickurl);
	}

	public String getParamKey()
	{
		return paramKey;
	}

	public void setParamKey(String paramKey)
	{
		this.paramKey = paramKey;
		firePropertyChange(PROP_PARAMKEY, null, paramKey);
	}

	public String getClickUrlTarget()
	{
		return clickUrlTarget;
	}

	public void setClickUrlTarget(String clickUrlTarget)
	{
		this.clickUrlTarget = clickUrlTarget;
		firePropertyChange(PROP_CLICKURLTARGET, null, clickUrlTarget);
	}

	public String getOptions()
	{
		return options;
	}

	public void setOptions(String options)
	{
		this.options = options;
		firePropertyChange(PROP_OPERATION, null, options);
	}

	public String getFormatter()
	{
		return formatter;
	}

	public void setFormatter(String formatter)
	{
		this.formatter = formatter;
		firePropertyChange(PROP_FORMATTER, null, formatter);
	}

	public String getFormatterOptions()
	{
		return formatterOptions;
	}

	public void setFormatterOptions(String formatterOptions)
	{
		this.formatterOptions = formatterOptions;
		firePropertyChange(PROP_FORMATTEROPTIONS, null, formatterOptions);
	}

	public String getIfHide()
	{
		return ifHide;
	}

	public void setIfHide(String ifHide)
	{
		this.ifHide = ifHide;
		firePropertyChange(PROP_IFHIDE, null, ifHide);
	}

	public String getParamValueColumnName()
	{
		return paramValueColumnName;
	}

	public void setParamValueColumnName(String paramValueColumnName)
	{
		this.paramValueColumnName = paramValueColumnName;
		firePropertyChange(PROP_PARAMVALUECOLUMNNAME, null, paramValueColumnName);
	}

	public String getCord()
	{
		return cord;
	}

	public void setCord(String cord)
	{
		this.cord = cord;
		firePropertyChange(PROP_CORD, null, cord);
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
		firePropertyChange(PROP_WIDTH, null, width);
	}

	@Override
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

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof ColumnModel2) {
			ColumnModel2 tmp = (ColumnModel2) obj;
			return this.getName().equals(tmp.getName());
		}
		return false;
	}

	@Override
	public int compareTo(ColumnModel2 o)
	{
		return this.getOrder().compareTo(o.getOrder());
	}
}
