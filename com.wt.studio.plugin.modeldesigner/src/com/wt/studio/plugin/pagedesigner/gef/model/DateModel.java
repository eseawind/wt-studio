package com.wt.studio.plugin.pagedesigner.gef.model;


import org.eclipse.ui.views.properties.IPropertySource;
public class DateModel extends ControlModel implements IPropertySource
{
	private static final long serialVersionUID = -3820179531098967148L;
	
	public DateModel()
	{
		 name="Title";
		 id="Date";
	}
	
	@Override
	public String getType()
	{
		// TODO Auto-generated method stub
		return TYPE_DATE;
	}

}
