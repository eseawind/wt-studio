package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class HorizonBlockModel extends BlockModel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1843368406131981027L;
	public static String PROP_LAYOUTTYPE="布局是否充满"; 

	private int layoutType=0;//0代表布局充满平分；1代表布局可拖拽不平分
	public int getLayoutType()
	{
		return layoutType;
	}

	public void setLayoutType(int layoutType)
	{
		this.layoutType = layoutType;
		firePropertyChange(PROP_LAYOUTTYPE, null, layoutType);
	}

	public HorizonBlockModel()
	{
		type="水平块";
	}
	private static IPropertyDescriptor[] descriptors;
	static {
		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(PROP_ID, PROP_ID),
				new TextPropertyDescriptor(PROP_TYPE, PROP_TYPE),
				new ComboBoxPropertyDescriptor(PROP_LAYOUTTYPE, PROP_LAYOUTTYPE,
						new String[] { "是", "否" }),
				new TextPropertyDescriptor(PROP_HEIGHT,PROP_HEIGHT),
				new TextPropertyDescriptor(PROP_WIDTH,PROP_WIDTH),
		};
	}
	@Override
	public Object getEditableValue()
	{
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		// TODO Auto-generated method stub
		 return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id)
	{
		if (PROP_TYPE.equals(id)) {
			return this.getName();
		}else if (PROP_ID.equals(id)){
			return this.getUuid();
		}else if(PROP_LAYOUTTYPE.equals(id))
		{
			return this.getLayoutType();
		}else if(PROP_WIDTH.equals(id))
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

	public void setPropertyValue(Object id, Object value)
	{
		if (PROP_ID.equals(id)){
			this.setUuid((String)value);
		}else if(PROP_LAYOUTTYPE.equals(id))
		{
			this.setLayoutType((Integer)value);
			
		}else if(PROP_WIDTH.equals(id)){
			this.setWidth(((String)value));
		}else if(PROP_HEIGHT.equals(id))
			this.setHeight(((String)value));
	}
}
