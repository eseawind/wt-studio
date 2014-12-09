package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class InputModel extends ControlModel
{
	private static final long serialVersionUID = -3820179531098967148L;
	
	public static final String PROP_VALUE = "VALUE", PROP_ISNECESSARY = "是否必须", PROP_DATACHECK = "数据校验";
	public InputModel()
	{
		id="Input";
		name="Title";
	}
	private String value="";
	private String dataCheck="";
	private int isNecessary=0;
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
		firePropertyChange(PROP, null,value);
	}
	public String getDataCheck()
	{
		return dataCheck;
	}
	public void setDataCheck(String dataCheck)
	{
		this.dataCheck = dataCheck;
		firePropertyChange(PROP, null,dataCheck);
	}
	public int getIsNecessary()
	{
		return isNecessary;
	}
	public void setIsNecessary(int isNecessary)
	{
		this.isNecessary = isNecessary;
		firePropertyChange(PROP, null,isNecessary);
	}
	public String getType()
	{
		return TYPE_INPUT;
	}

	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_NAME, PROP_NAME),
				new TextPropertyDescriptor(PROP_TYPE, PROP_TYPE),
				new TextPropertyDescriptor(PROP_VALUE, PROP_VALUE),
				new TextPropertyDescriptor(PROP_DATACHECK, PROP_DATACHECK),
				new ComboBoxPropertyDescriptor(PROP_ISNECESSARY, PROP_ISNECESSARY,
						new String[] { "否", "是" }),
						new TextPropertyDescriptor(PROP_HEIGHT,PROP_HEIGHT),
						new TextPropertyDescriptor(PROP_WIDTH,PROP_WIDTH),};
		        
		};
	
	
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
		}else if(PROP_ID.equals(id))
			return this.getUuid();
		else if(PROP_TYPE.equals(id))
			return this.getType();
		else if(PROP_VALUE.equals(id))
			return this.getValue();
		else if(PROP_DATACHECK.equals(id))
			return this.getDataCheck();
		else if(PROP_ISNECESSARY.equals(id))
			return this.getIsNecessary();
		else if(PROP_WIDTH.equals(id))
			return this.getWidth();
		else if(PROP_HEIGHT.equals(id))
			return this.getHeight();
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
		} else if(PROP_ID.equals(id))
			this.setUuid((String)value);
		else if(PROP_VALUE.equals(id))
			this.setValue((String)value);
		else if(PROP_DATACHECK.equals(id))
			this.setDataCheck((String)value);
		else if(PROP_ISNECESSARY.equals(id))
			this.setIsNecessary((Integer)value);
		else if(PROP_WIDTH.equals(id)){
			this.setWidth(((String)value));
		}else if(PROP_HEIGHT.equals(id))
			this.setHeight(((String)value));
	}

}
