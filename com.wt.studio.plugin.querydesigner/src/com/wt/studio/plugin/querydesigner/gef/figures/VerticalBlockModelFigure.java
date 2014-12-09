package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.swt.graphics.Color;

import com.wt.studio.plugin.querydesigner.gef.layout.VerticalFillLayout;
import com.wt.studio.plugin.querydesigner.gef.layout.VerticalLayout;

public class VerticalBlockModelFigure extends BlockModelFigure
{
	Color orange = new Color(null, 0, 196, 0);

	public VerticalBlockModelFigure()
	{
		VerticalFillLayout vertical = new VerticalFillLayout();
		vertical.setSpacing(5);
		setLayoutManager(new VerticalLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new LineBorder();
		border.setColor(orange);
		border.setWidth(1);
		setBorder(border);
	}

}
