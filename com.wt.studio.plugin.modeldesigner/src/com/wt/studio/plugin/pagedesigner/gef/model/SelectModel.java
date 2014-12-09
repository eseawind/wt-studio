package com.wt.studio.plugin.pagedesigner.gef.model;

import org.eclipse.ui.views.properties.IPropertySource;

public class SelectModel extends ControlModel implements IPropertySource
{
	private static final long serialVersionUID = -3820179531098967148L;
	
	public SelectModel()
	{
		 name="Select";
		 id="List";
	}
	
	@Override
	public String getType()
	{
		// TODO Auto-generated method stub
		return TYPE_LIST;
	}

}
