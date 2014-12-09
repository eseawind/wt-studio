package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.TitleBarBorder;
import org.eclipse.swt.graphics.Color;

import com.wt.studio.plugin.querydesigner.gef.layout.HorizontalFillLayout;

public class ColumnGroupFigure extends BlockModelFigure
{

	TitleBarBorder border;
	Color color = new Color(null, 0, 196, 0);

	public ColumnGroupFigure()
	{
		HorizontalFillLayout horizontal = new HorizontalFillLayout();
		horizontal.setSpacing(5);
		setLayoutManager(horizontal);
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new TitleBarBorder("默认列组");
		setBorder(border);
	}

	public TitleBarBorder getBorder()
	{
		return border;
	}
}
