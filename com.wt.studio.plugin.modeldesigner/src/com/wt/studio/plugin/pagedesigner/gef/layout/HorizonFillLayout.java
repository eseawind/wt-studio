package com.wt.studio.plugin.pagedesigner.gef.layout;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class HorizonFillLayout extends ToolbarLayout
{
	protected Rectangle rec;
	protected Rectangle newBounds;

	public void setRectangle(Rectangle rec)
	{
		this.rec=rec;
	}
	public void layout(IFigure parent)
	{
		List children = parent.getChildren();
		int numChildren = children.size();
		if(numChildren>0)
		{
		Rectangle clientArea = transposer.t(parent.getClientArea());
		int x = clientArea.x+3;
		int y = clientArea.y;
		IFigure firstChild = (IFigure) children.get(0);
		Dimension first = new Dimension(-1, clientArea.height);
		Point firstloc = new Point(x, y);
		Rectangle firstNewBounds = new Rectangle(firstloc, first);
		firstChild.setBounds(transposer.t(firstNewBounds));

		for (int i = 1; i < numChildren; i++) {
			IFigure child = (IFigure) children.get(i);
			Point loc = new Point(x, y + 4);
			Dimension size = new Dimension(-1, clientArea.height-8);
			Rectangle newBounds = new Rectangle(loc, size);
			int divided = (clientArea.width - 6 - ((numChildren - 2) * spacing))
					/ (numChildren - 1);
			if (i == numChildren - 1)
				divided = clientArea.width - 6 - ((divided + spacing) * (numChildren - 2));
			newBounds.width = divided;
			child.setBounds(transposer.t(newBounds));
			x += newBounds.width + spacing;
		}
	   }
		/*IFigure topparent=parent.getParent();
		Rectangle clientArea = transposer.t(topparent.getClientArea());
		if(rec==null)
		{
		   newBounds = new Rectangle(clientArea.x,clientArea.y,clientArea.width,30);
		   parent.setBounds(newBounds);
		}
		else
		{
		   newBounds = new Rectangle(clientArea.x,clientArea.y,clientArea.width,rec.height);
		   parent.setBounds(newBounds);
		}*/
	}
}
