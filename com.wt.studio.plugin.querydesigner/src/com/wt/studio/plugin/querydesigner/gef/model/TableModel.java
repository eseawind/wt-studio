package com.wt.studio.plugin.querydesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.wt.studio.plugin.querydesigner.gef.descriptor.CTableInjectDialogDescriptor;
import com.wt.studio.plugin.querydesigner.gef.descriptor.TableModelDialogPropertyDescriptor;
import com.wt.studio.plugin.querydesigner.model.InjectModel;
import com.wt.studio.plugin.querydesigner.model.TableSqlAreaModel;
import com.wt.studio.plugin.querydesigner.utils.Utils;

public class TableModel extends AbstractBlockModel implements IPropertySource
{
	private static final long serialVersionUID = -2481435788455706065L;
	public static final String PROP_NAME = "标题", PROP_TYPE = "类别", PROP_COLUMNS = "columns",
			PROP_COLUMNGROUPS = "columnGroups", PROP_WIDTH = "宽度", PROP_HEIGHT = "高度",
			PROP_SQL = "列表配置", PROP_CTABLE = "个性化", PROP_CHECKBOX = "添加复选框";
	private String name = "列表", width = "100%", height = "300", sql = "", sqlName = "";
	private boolean hasCheckBox = false;
	private int type = 0;
	private InjectModel inject;
	private TableSqlAreaModel sqlAreaModel;

	public boolean isHasCheckBox()
	{
		return hasCheckBox;
	}

	public void setHasCheckBox(boolean hasCheckBox)
	{
		this.hasCheckBox = hasCheckBox;
	}

	public InjectModel getInject()
	{
		return inject;
	}

	public void setInject(InjectModel inject)
	{
		this.inject = inject;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getSqlName()
	{
		return sqlName;
	}

	public void setSqlName(String sqlName)
	{
		this.sqlName = sqlName;
	}

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_WIDTH, PROP_WIDTH),
				new TextPropertyDescriptor(PROP_HEIGHT, PROP_HEIGHT),
				new ComboBoxPropertyDescriptor(PROP_CHECKBOX, PROP_CHECKBOX, new String[] { "否",
						"是" }),
				new TableModelDialogPropertyDescriptor(PROP_SQL, PROP_SQL),
				new ComboBoxPropertyDescriptor(PROP_TYPE, PROP_TYPE, new String[] { "Table",
						"CTable" }), new CTableInjectDialogDescriptor(PROP_CTABLE, PROP_CTABLE) };

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
		firePropertyChange(PROP_NAME, null, name);
	}

	public void addColumn(ColumnModel2 col)
	{
		super.addElement(-1, col);
		// this.columns.add(col);
		fireStructureChange(PROP_COLUMNS, this.result);
	}

	public void removeColumn(ColumnModel2 col)
	{
		super.removeElement(col);
		// this.columns.remove(col);
		fireStructureChange(PROP_COLUMNS, this.result);
	}

	public void addColumnGroup(ColumnGroupModel columnGroup)
	{
		super.addElement(-1, columnGroup);
		fireStructureChange(PROP_COLUMNGROUPS, this.result);
	}

	public void removeColumnGroup(ColumnGroupModel columnGroup)
	{
		super.removeElement(columnGroup);
		fireStructureChange(PROP_COLUMNGROUPS, this.result);
	}

	public void addAllColumn(List<ColumnModel2> columns)
	{
		super.addAllElement(columns);
		fireStructureChange(PROP_COLUMNS, this.result);
	}

	public void removeAllColumn()
	{
		super.removeAllElements();
		fireStructureChange(PROP_COLUMNS, this.result);
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
			return this.sqlAreaModel;
		} else if (PROP_TYPE.equals(id)) {
			return this.getType();
		} else if (PROP_CHECKBOX.equals(id)) {
			if (isHasCheckBox())
				return 1;
			else
				return 0;
		} else if (PROP_CTABLE.equals(id)) {
			return this.inject;
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
			TableSqlAreaModel model = (TableSqlAreaModel) value;
			if (model != null) {
				this.sqlAreaModel = model;
				this.setSql(model.getSqlArea());
				this.setSqlName(model.getSqlName());
				this.removeAllColumn();
				this.addAllColumn(model.getCms());
				this.setBlockName(model.getSqlName());
			}
			firePropertyChange(PROP_SQL, null, this.sqlAreaModel);
		} else if (PROP_TYPE.equals(id)) {
			this.setType((Integer) value);
		} else if (PROP_CHECKBOX.equals(id)) {
			if ((Integer) value == 1)
				this.setHasCheckBox(true);
			else
				this.setHasCheckBox(false);
		} else if (PROP_CTABLE.equals(id)) {
			InjectModel model = (InjectModel) value;
			if (model != null) {
				this.inject = model;
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

	public String getSql()
	{
		if (this.sqlAreaModel != null)
			return this.sqlAreaModel.getSqlArea();
		else
			return null;
	}

	public void setSql(String sql)
	{
		if (!this.sql.equals(sql)) {
			this.sql = sql;
			firePropertyChange(PROP_SQL, null, sql);
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

	public TableSqlAreaModel getSqlAreaModel()
	{
		return sqlAreaModel;
	}

	public void setSqlAreaModel(TableSqlAreaModel sqlAreaModel)
	{
		this.sqlAreaModel = sqlAreaModel;
	}

	public List<ColumnModel2> getColumns()
	{
		int num = 0;
		List<ColumnModel2> columns = new ArrayList<ColumnModel2>();
		List<Element> elements = this.getElements();
		for (Element element : elements) {
			if (element instanceof ColumnModel2) {
				columns.add((ColumnModel2) element);
				((ColumnModel2) element).setColspanStyle("N");
				((ColumnModel2) element).setHierarchy("0");
				((ColumnModel2) element).setGroupTitle(((ColumnModel2) element).getDescription());
				((ColumnModel2) element).setColspanStyle("N");
			} else if (element instanceof ColumnGroupModel) {
				columns.addAll(Utils.getColumnsFromGroup((ColumnGroupModel) element, num));
			}
		}
		return columns;
	}
}
