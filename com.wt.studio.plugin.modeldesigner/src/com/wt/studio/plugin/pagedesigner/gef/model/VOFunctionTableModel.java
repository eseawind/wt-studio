package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class VOFunctionTableModel  extends FunctionModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5354694306217488068L;
	
	
	
	
	public VOFunctionTableModel()
	{
		this.title="VO";
	}




	public FunctionColumnModel getColumnByUuid(String uuid)
	{
		for(FunctionColumnModel column:this.getColumns())
		{
			if(column.getUuid().equals(uuid))
				return column;
		}
		return null;
	}
	
	

}
