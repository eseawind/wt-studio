package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.LineBorder;
import org.eclipse.swt.graphics.Color;

import com.wt.studio.plugin.querydesigner.gef.layout.QueryHorizontalBlockModelLayout;

public class QueryHorizontalBlockModelFigure extends BlockModelFigure
{
	Color orange = new Color(null, 0, 0, 196);

	public QueryHorizontalBlockModelFigure()
	{
		QueryHorizontalBlockModelLayout horizontal = new QueryHorizontalBlockModelLayout();
		horizontal.setSpacing(5);
		setLayoutManager(horizontal);
		border = new LineBorder();
		border.setColor(orange);
		border.setWidth(1);
		setBorder(border);
	}

}