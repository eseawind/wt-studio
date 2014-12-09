package com.wt.studio.plugin.querydesigner.gef.layout;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class QueryHorizontalBlockModelLayout extends ToolbarLayout
{

	public void layout(IFigure parent)
	{
		List children = parent.getChildren();
		int numChildren = children.size();
		org.eclipse.draw2d.geometry.Rectangle clientArea = transposer.t(parent.getClientArea());
		int x = clientArea.x;
		int y = clientArea.y;
		for (int i = 0; i < numChildren; i++) {
			IFigure child = (IFigure) children.get(i);
			Point loc = new Point(x, y + 4);
			Dimension size = new Dimension(-1, clientArea.height - 8);
			Rectangle newBounds = new Rectangle(loc, size);
			int divided = (clientArea.width - 2 - ((numChildren - 1) * spacing)) / (numChildren);
			if (i == numChildren - 1)
				divided = clientArea.width - 2 - ((divided + spacing) * (numChildren - 1));
			newBounds.width = divided;
			child.setBounds(transposer.t(newBounds));
			x += newBounds.width + spacing;
		}
	}
}