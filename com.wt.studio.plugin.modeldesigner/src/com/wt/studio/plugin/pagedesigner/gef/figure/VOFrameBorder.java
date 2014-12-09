package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.LineBorder;


public class VOFrameBorder extends  FrameBorder 
{
 
	protected void createBorders() {
	
		
		inner = new VOTitleBorder();
		outer = new LineBorder();
		((LineBorder)outer).setColor(ColorConstants.titleInactiveGradient);
	}
}

