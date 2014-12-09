package com.wt.studio.plugin.querydesigner.gef.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class DashBoardModel extends AbstractBlockModel implements IPropertySource
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1148359657042212310L;
	private String type;
	private int minValue;
	private int maxValue;
	private int copies;
	private boolean isAnticlockwise;

	@Override
	public Object getEditableValue()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPropertyValue(Object arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPropertySet(Object arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setPropertyValue(Object arg0, Object arg1)
	{
		// TODO Auto-generated method stub

	}

}
