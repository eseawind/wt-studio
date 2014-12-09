package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.LineBorder;

public class BOFrameBorder extends  FrameBorder 
{
 
	protected void createBorders() {
		
		inner = new BOTitleBorder();
		outer = new LineBorder();
		
	}
}

