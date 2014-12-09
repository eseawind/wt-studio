package com.wt.studio.plugin.querydesigner.gef.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.wt.studio.plugin.querydesigner.gef.descriptor.TextDataPropertyDescriptor;

public abstract class ParamModel extends Element implements IPropertySource
{

	private static final long serialVersionUID = 1420404349187514050L;
	public static final String PROP_NAME = "Name", PROP_MORE_TYPE = "更多", PROP_DESCIPTION = "显示名称",
			PROP_SQL = "关联SQL", PROP_ROW_NUM = "行位置", PROP_COL_NUM = "列位置", PROP_OPTIONAL = "选填",
			PROP_CRITERIAAREA = "参数对应的条件", PROP_PARAMLOCATION = "条件追加位置", PROP_CODE_NAME = "日期格式",
			PROP_ORDER = "显示顺序", PROP_TREE_TYPE = "树类型", PROP_PARENT = "父级控件(ID)", PROP_ID = "ID";
	public static final String TYPE_INPUT = "INPUT", TYPE_DATE = "DATE", TYPE_HIDDEN = "HIDDEN",
			TYPE_TREE = "TREE", TYPE_USER_TREE = "USERTREE", TYPE_DEPTTREE = "DEPTTREE",
			TYPE_ORDER = "ORDER";
	public static List<ParamModel> params = new ArrayList<ParamModel>();
	private String id;
	private String name = "param1";
	private String description = "参数1";
	private String type;
	private String moreType = "01";
	private Integer treeType = 0;
	private String order = "";
	private String sql = "";
	private String rowNum = "1";
	private String colNum = "1";
	private Integer optional = 1;
	private String criteriaArea = "";
	private String paramLocation = "";
	private String codeName = "";
	private String parentId = "";

	private static IPropertyDescriptor[] descriptors;
	private static IPropertyDescriptor[] descriptors1;
	private static IPropertyDescriptor[] descriptors2;

	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_NAME, "参数名称"),
				new TextPropertyDescriptor(PROP_DESCIPTION, PROP_DESCIPTION),
				new TextDataPropertyDescriptor(PROP_CRITERIAAREA, PROP_CRITERIAAREA),
				new TextPropertyDescriptor(PROP_PARAMLOCATION, PROP_PARAMLOCATION),
				new TextPropertyDescriptor(PROP_ROW_NUM, PROP_ROW_NUM),
				new TextPropertyDescriptor(PROP_COL_NUM, PROP_COL_NUM),
				new ComboBoxPropertyDescriptor(PROP_OPTIONAL, PROP_OPTIONAL, new String[] { "否",
						"是" }),
				new ComboBoxPropertyDescriptor(PROP_MORE_TYPE, PROP_MORE_TYPE, new String[] { "否",
						"是" }), new TextPropertyDescriptor(PROP_PARENT, PROP_PARENT) };
		descriptors1 = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_NAME, "参数名称"),
				new TextPropertyDescriptor(PROP_DESCIPTION, PROP_DESCIPTION),
				new TextDataPropertyDescriptor(PROP_CRITERIAAREA, PROP_CRITERIAAREA),
				new TextPropertyDescriptor(PROP_PARAMLOCATION, PROP_PARAMLOCATION),
				new TextPropertyDescriptor(PROP_ROW_NUM, PROP_ROW_NUM),
				new TextPropertyDescriptor(PROP_COL_NUM, PROP_COL_NUM),
				new TextPropertyDescriptor(PROP_CODE_NAME, PROP_CODE_NAME),
				new ComboBoxPropertyDescriptor(PROP_OPTIONAL, PROP_OPTIONAL, new String[] { "否",
						"是" }),
				new ComboBoxPropertyDescriptor(PROP_MORE_TYPE, PROP_MORE_TYPE, new String[] { "否",
						"是" }), new TextPropertyDescriptor(PROP_PARENT, PROP_PARENT) };
		TextDataPropertyDescriptor text = new TextDataPropertyDescriptor(PROP_CRITERIAAREA,
				PROP_CRITERIAAREA);
		descriptors2 = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_NAME, "参数名称"),
				new TextPropertyDescriptor(PROP_DESCIPTION, PROP_DESCIPTION),
				new TextDataPropertyDescriptor(PROP_CRITERIAAREA, PROP_CRITERIAAREA),
				new TextPropertyDescriptor(PROP_PARAMLOCATION, PROP_PARAMLOCATION),
				new TextDataPropertyDescriptor(PROP_SQL, PROP_SQL),
				new TextPropertyDescriptor(PROP_ROW_NUM, PROP_ROW_NUM),
				new TextPropertyDescriptor(PROP_COL_NUM, PROP_COL_NUM),
				new ComboBoxPropertyDescriptor(PROP_TREE_TYPE, PROP_TREE_TYPE, new String[] {
						"TREE", "RADIO_TREE", "CHECKBOX_TREE", "SELECT" }),
				new ComboBoxPropertyDescriptor(PROP_OPTIONAL, PROP_OPTIONAL, new String[] { "否",
						"是" }),
				new ComboBoxPropertyDescriptor(PROP_MORE_TYPE, PROP_MORE_TYPE, new String[] { "否",
						"是" }), new TextPropertyDescriptor(PROP_PARENT, PROP_PARENT) };
	}

	public ParamModel()
	{
		this.setId(uuid());
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	@Override
	public Object getEditableValue()
	{
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		if (TYPE_TREE.equals(this.getType())) {
			return descriptors2;
		} else if (TYPE_DATE.equals(this.getType())) {
			return descriptors1;
		}
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id)
	{
		if (PROP_ID.equals(id)) {
			return this.getId();
		} else if (PROP_NAME.equals(id)) {
			return this.getName();
		} else if (PROP_DESCIPTION.equals(id)) {
			return this.getDescription();
		} else if (PROP_SQL.equals(id)) {
			return this.getSql().trim();
		} else if (PROP_ROW_NUM.equals(id)) {
			return this.getRowNum();
		} else if (PROP_COL_NUM.equals(id)) {
			return this.getColNum();
		} else if (PROP_OPTIONAL.equals(id)) {
			return this.getOptional();
		} else if (PROP_CRITERIAAREA.equals(id)) {
			return this.getCriteriaArea().trim();
		} else if (PROP_PARAMLOCATION.equals(id)) {
			return this.getParamLocation();
		} else if (PROP_CODE_NAME.equals(id)) {
			return this.getCodeName();
		} else if (PROP_MORE_TYPE.equals(id)) {
			if ("02".equals(this.getMoreType()))
				return 1;
			else
				return 0;
		} else if (PROP_TREE_TYPE.equals(id)) {
			return this.getTreeType();
		} else if (PROP_PARENT.equals(id)) {
			return this.getParentId();
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
			this.setName((String) value);
		} else if (PROP_DESCIPTION.equals(id)) {
			this.setDescription((String) value);
		} else if (PROP_SQL.equals(id)) {
			this.setSql(loadSql((String) value));
		} else if (PROP_ROW_NUM.equals(id)) {
			this.setRowNum((String) value);
		} else if (PROP_COL_NUM.equals(id)) {
			this.setColNum((String) value);
		} else if (PROP_OPTIONAL.equals(id)) {
			this.setOptional((Integer) value);
		} else if (PROP_CRITERIAAREA.equals(id)) {
			this.setCriteriaArea((String) value);
		} else if (PROP_PARAMLOCATION.equals(id)) {
			this.setParamLocation((String) value);
		} else if (PROP_CODE_NAME.equals(id)) {
			this.setCodeName((String) value);
		} else if (PROP_MORE_TYPE.equals(id)) {
			if ((Integer) value == 0)
				this.setMoreType("01");
			else
				this.setMoreType("02");
		} else if (PROP_TREE_TYPE.equals(id)) {
			this.setTreeType((Integer) value);
		} else if (PROP_PARENT.equals(id)) {
			this.setParentId((String) value);
		}

	}

	private String loadSql(String value)
	{
		// TODO Auto-generated method stub
		return value.replaceAll("\n", "\b");
	}

	public String getMoreType()
	{
		return moreType;
	}

	public void setMoreType(String moreType)
	{
		this.moreType = moreType;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (this.name.equals(name)) {
			return;
		}
		this.name = name;
		firePropertyChange(PROP_NAME, null, name);
	}

	public abstract String getType();

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		if (this.description.equals(description)) {
			return;
		}
		this.description = description;
		firePropertyChange(PROP_DESCIPTION, null, type);
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		if (!this.sql.equals(sql)) {
			this.sql = sql;
			firePropertyChange(PROP_SQL, null, sql);
		}
	}

	public Integer getTreeType()
	{
		return treeType;
	}

	public void setTreeType(Integer treeType)
	{
		this.treeType = treeType;
		firePropertyChange(PROP_DESCIPTION, null, type);
	}

	public String getRowNum()
	{
		return rowNum;
	}

	public void setRowNum(String rowNum)
	{
		if (!this.rowNum.equals(rowNum)) {
			this.rowNum = rowNum;
			firePropertyChange(PROP_ROW_NUM, null, rowNum);
		}
	}

	public String getColNum()
	{
		return colNum;
	}

	public void setColNum(String colNum)
	{
		if (!this.colNum.equals(colNum)) {
			this.colNum = colNum;
			firePropertyChange(PROP_COL_NUM, null, colNum);
		}
	}

	public Integer getOptional()
	{
		return optional;
	}

	public void setOptional(Integer optional)
	{
		if (!this.optional.equals(optional)) {
			this.optional = optional;
			firePropertyChange(PROP_OPTIONAL, null, optional);
		}
	}

	public String getCriteriaArea()
	{
		return criteriaArea;
	}

	public void setCriteriaArea(String criteriaArea)
	{
		if (!this.criteriaArea.equals(criteriaArea)) {
			this.criteriaArea = criteriaArea;
			firePropertyChange(PROP_CRITERIAAREA, null, criteriaArea);
		}
	}

	public String getParamLocation()
	{
		return paramLocation;
	}

	public void setParamLocation(String paramLocation)
	{
		if (!this.paramLocation.equals(paramLocation)) {
			this.paramLocation = paramLocation;
			firePropertyChange(PROP_PARAMLOCATION, null, paramLocation);
		}
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCodeName()
	{
		return codeName;
	}

	public void setCodeName(String codeName)
	{
		if (!this.getCodeName().equals(codeName)) {
			this.codeName = codeName;
			firePropertyChange(PROP_CODE_NAME, null, PROP_CODE_NAME);
		}
	}

	@Override
	public Rectangle getRectangle()
	{
		if (rectangle.width < 120) {
			rectangle.width = 120;
		}
		if (rectangle.height < 40) {
			rectangle.height = 40;
		}
		return rectangle;
	}

	private static String uuid()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}
}
