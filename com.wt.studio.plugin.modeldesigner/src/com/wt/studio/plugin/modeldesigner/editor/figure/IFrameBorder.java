package com.wt.studio.plugin.modeldesigner.editor.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.SchemeBorder;


public class IFrameBorder extends  FrameBorder 
{

	protected void createBorders() {
		inner = new ITitleBorder();
		outer = new LineBorder();
		((LineBorder)outer).setColor(ColorConstants.titleGradient);
	}
}
