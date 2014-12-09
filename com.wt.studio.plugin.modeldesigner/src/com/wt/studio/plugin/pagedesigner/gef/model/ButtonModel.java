package com.wt.studio.plugin.pagedesigner.gef.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class ButtonModel extends ControlModel 
{
	private static final long serialVersionUID = -3820179531098967148L;
	 
	public ButtonModel()
	{
		name="按钮";
		id="Button";
	}
	
	public String getType()
	{
		return TYPE_BUTTON;
	}
}
