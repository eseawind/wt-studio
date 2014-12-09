package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;


public class Diagram extends Element implements IPropertySource
{
	private static final long serialVersionUID = -8356627096473811653L;
	private String name="page";
	private int type=0;
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	private int x;
	private int y;

	private int[] defaultColor;
	private int[] color;
	private List<ControlPageModel> pageModels = new ArrayList<ControlPageModel>();
	private List<Element> functionModels=new ArrayList<Element>();
	private VOFunctionTableModel func;
	private ControlPageModel cpage;
	public static final String PROP_PAGES = "pages";
	private String id = "";
	private String desc = "";
	private String status = "01";
	private Integer defaultShowData = 0;
	public static final String PROP_TABLES = "tables", PROP_ID = "ID*", PROP_NAME = "功能名称",
			PROP_DESC = "描述", PROP_STATUS = "状态", PROP_SHOW_DATA = "默认显示数据";
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_DESC, PROP_DESC),
				new ComboBoxPropertyDescriptor(PROP_STATUS, PROP_STATUS,
						new String[] { "启用", "停用" }),
				new ComboBoxPropertyDescriptor(PROP_SHOW_DATA, PROP_SHOW_DATA, new String[] { "否",
						"是" }) };
	}

	public VOFunctionTableModel getFunc()
	{
		return func;
	}

	public void setFunc(VOFunctionTableModel func)
	{
		this.func = func;
	}
	public void addPageModel(ControlPageModel pageModel)
	{
		pageModels.add(pageModel);
		fireStructureChange(PROP_PAGES,pageModels);
	}

	public void removePageModel(ControlPageModel pageModel)
	{
		pageModels.remove(pageModel);
		fireStructureChange(PROP_PAGES,pageModels);
	}
	public void addFunctionModel(FunctionModel funcModel)
	{
		functionModels.add(funcModel);
		fireStructureChange(PROP_PAGES,pageModels);
	}

	public void removeFunctionModel(FunctionModel funcModel)
	{
		functionModels.remove(funcModel);
		fireStructureChange(PROP_PAGES,pageModels);
	}
	public List<ControlPageModel> getPageModels()
	{
		return pageModels;
	}
	public List<Element> getFunctionTableModels()
	{
		return functionModels;
	}

	@Override
	public Rectangle getRectangle()
	{
		return null;
	}
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Integer getDefaultShowData()
	{
		return defaultShowData;
	}

	public void setDefaultShowData(Integer defaultShowData)
	{
		this.defaultShowData = defaultShowData;
	}

	public String getId()
	{
		return id.trim();
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public Object getEditableValue()
	{
		return this;
	}
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id)
	{
		if (PROP_NAME.equals(id)) {
			return this.getName();
		} else if (PROP_DESC.equals(id)) {
			return this.getDesc();
		} else if (PROP_STATUS.equals(id)) {
			return this.getStatus().equals("01") ? 0 : 1;
		} else if (PROP_SHOW_DATA.equals(id)) {
			return this.getDefaultShowData();
		} else if (PROP_ID.equals(id)) {
			return this.getId();
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
		} else if (PROP_DESC.equals(id)) {
			this.setDesc((String) value);
		} else if (PROP_STATUS.equals(id)) {
			int status = (Integer) value;
			this.setStatus(status == 0 ? "01" : "02");
		} else if (PROP_SHOW_DATA.equals(id)) {
			this.setDefaultShowData((Integer) value);
		} else if (PROP_ID.equals(id)) {
			this.setId((String) value);
		}
	}
	public void initFunctionModel()
	{
		func.getColumns().clear();
		ControlPageModel page=(ControlPageModel)this.getPageModels().get(0);
		//func.setTitle("VO");
		List<Element> elements=page.getAllElement();
		for(Element element:elements)
		{
			if(element instanceof  ControlModel)
			{
				FunctionColumnModel funcColumn=new FunctionColumnModel();
				funcColumn.setId(((ControlModel)element).getId());
				funcColumn.setTitle(((ControlModel)element).getName());
				func.getColumns().add(funcColumn);
			}
			else if(element instanceof BlockModel)
			{
				getColumnFromBlock((BlockModel)element,func);
			}
		}
		
		//func.setRectangle(new Rectangle(20,20,400,400));
	}
	private void getColumnFromBlock(BlockModel block, VOFunctionTableModel func)
	{
		// TODO Auto-generated method stub
		List<Element> elements=block.getAllElement();
		for(Element element:elements)
		{
			if(element instanceof  ControlModel)
			{
				FunctionColumnModel funcColumn=new FunctionColumnModel();
				funcColumn.setId(((ControlModel)element).getId());
				funcColumn.setTitle(((ControlModel)element).getName());
				func.getColumns().add(funcColumn);
			}
			else if(element instanceof BlockModel)
			{
				getColumnFromBlock((BlockModel)element,func);
			}
		}
	}

	public void refresh()
	{
		fireStructureChange(PROP_PAGES,pageModels);
	}
}
