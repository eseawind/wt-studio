package com.wt.studio.plugin.querydesigner.gef.layout;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class VerticalFillLayout extends ToolbarLayout
{

	public VerticalFillLayout()
	{
		spacing = 1;
	}

	public void layout(IFigure parent)
	{
		List children = parent.getChildren();
		int numChildren = children.size();
		if (numChildren > 0) {
			org.eclipse.draw2d.geometry.Rectangle clientArea = transposer.t(parent.getClientArea());
			int x = clientArea.x;
			int y = clientArea.y;
			IFigure firstChild = (IFigure) children.get(0);
			Dimension first = new Dimension(-1, clientArea.height);
			Point firstloc = new Point(x, y);
			Rectangle firstNewBounds = new Rectangle(firstloc, first);
			firstChild.setBounds(transposer.t(firstNewBounds));
			for (int i = 1; i < numChildren; i++) {
				IFigure child = (IFigure) children.get(i);
				Point loc = new Point(x, y + 4);
				Dimension size = new Dimension(clientArea.width, -1);
				Rectangle newBounds = new Rectangle(loc, size);
				int divided = (clientArea.height - 10 - ((numChildren - 2) * spacing))
						/ (numChildren - 1);
				if (i == numChildren - 1)
					divided = clientArea.height - 10 - ((divided + spacing) * (numChildren - 2));
				newBounds.height = divided;
				child.setBounds(transposer.t(newBounds));
				y += newBounds.height + spacing;
			}
		}
	}
}
