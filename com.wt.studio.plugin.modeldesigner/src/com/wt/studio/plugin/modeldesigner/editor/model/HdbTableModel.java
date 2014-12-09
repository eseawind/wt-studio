package com.wt.studio.plugin.modeldesigner.editor.model;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class HdbTableModel extends BONodeModel implements IPropertySource
{
	private static final long serialVersionUID = 3968565016457018409L;
	public static final String PROP_NAME = "name";
	public static final String PROP_TYPE = "Table";
	public static final String PROP_COLUMNS = "Columns";
	private String id;
	private String title = "表";
	private String type="Table";
	private String code="";
	private String codeString="";
	public int repeatNum=0;

	public int getRepeatNum()
	{
		return repeatNum;
	}

	public void setRepeatNum(int repeatNum)
	{
		this.repeatNum = repeatNum;
	}

	public String getCodeString()
	{
		return codeString;
	}

	public void setCodeString(String codeString)
	{
		this.codeString = codeString;
	}

	private String comment="";
	protected List<HdbColumnModel> columns=new ArrayList<HdbColumnModel>();
	
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(PROP_NAME, "title"), 
				new PropertyDescriptor(PROP_TYPE, "type") };
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
		}else if (PROP_TYPE.equals(id)) {
			return this.getType();
		}
		return null;
	}

	private String getType()
	{
		
		return type;
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


	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
		firePropertyChange(PROP_NAME, null, title);
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
		firePropertyChange(PROP_NAME, null, code);
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
		firePropertyChange(PROP_NAME, null, comment);
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public void addColumn(HdbColumnModel column)
	{
		this.columns.add(column);
		fireStructureChange(PROP_COLUMNS,columns);
	}
	public void removeColumn(HdbColumnModel column)
	{
		this.columns.remove(column);
		fireStructureChange(PROP_COLUMNS,columns);
	}
	public List<HdbColumnModel> getColumns()
	{
		return this.columns;
	}
	public void setColumns(List columns)
	{
		this.columns=columns;
	}
	public List getPColumns()
	{
		List<String>  PColumns=new ArrayList<String>();
		for(HdbColumnModel column:this.columns)
		{
			if(column.isPK())
				PColumns.add(column.getId());
		}
		return PColumns;
	}
	@Override
	public Rectangle getRectangle()
	{
		return rectangle;
	}
	public HdbTableModel CopyData() 
	{
		HdbTableModel newTable=new HdbTableModel();
		newTable.setTitle(this.getTitle());
		newTable.setCode(this.getCode());
		newTable.setComment(this.getComment());
		newTable.setColumns(this.getColumns());
		newTable.setCodeString(this.getCodeString());
		return newTable;
		
	}
	public void getCopydata(HdbTableModel table)
	{
		this.setCode(table.getCode());
		this.setTitle(table.getTitle());
		this.setComment(table.getComment());
		this.setColumns(table.getColumns());
		this.setCodeString(table.getCodeString());
		fireStructureChange(PROP_COLUMNS,columns);
	}

	public void refresh()
	{
		// TODO Auto-generated method stub
		fireStructureChange(PROP_COLUMNS,columns);
		firePropertyChange(PROP_NAME, null, title);
	}
	public String checkCodeName(String name)
	{
		for(HdbColumnModel column:this.getColumns())
		{
			if(name.equals(column.getCode()))
			{
				repeatNum++;
				return name+String.valueOf(repeatNum);
			}
			
		}
		return name;
	}
	public String checkName(String name)
	{
		for(HdbColumnModel column:this.getColumns())
		{
			if(name.equals(column.getName()))
			{
				return name+String.valueOf(repeatNum);
			}
			
		}
		return name;
	}

	public HdbColumnModel getColumnById(String id)
	{
		for(HdbColumnModel column:this.getColumns())
		{
			if(id.equals(column.getId()))
				return column;
		}
		return null;
	}

	public void removeColumnByCodeName(String codeName)
	{
		List columnModels=new ArrayList<HdbColumnModel>();
		for(HdbColumnModel column:this.getColumns())
		{
			if(column.getCode().equals(codeName))
				columnModels.add(column);
				
		}
		this.getColumns().removeAll(columnModels);
	}

	public HdbColumnModel getColumnByCode(String codeName)
	{
		// TODO Auto-generated method stub
		for(HdbColumnModel column:this.getColumns())
		{
			if(codeName.equals(column.getCode()))
				return column;
		}
		return null;
	}
}
