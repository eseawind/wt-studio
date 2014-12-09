package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.LineBorder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import com.wt.studio.plugin.querydesigner.gef.layout.HorizontalFillLayout;

public class HorizontalBlockModelFigure extends BlockModelFigure
{
	Color orange = new Color(null, 0, 0, 196);

	public HorizontalBlockModelFigure()
	{
		HorizontalFillLayout horizontal = new HorizontalFillLayout();
		horizontal.setSpacing(5);
		setLayoutManager(horizontal);
		border = new LineBorder();
		border.setColor(orange);
		border.setWidth(1);
		border.setStyle(SWT.LINE_DASHDOT);
		setBorder(border);
	}

}