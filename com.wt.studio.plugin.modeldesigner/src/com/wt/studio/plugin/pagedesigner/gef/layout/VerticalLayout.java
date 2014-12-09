package com.wt.studio.plugin.pagedesigner.gef.layout;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.pagedesigner.gef.figure.HorizonBlockModelFigure;

public class VerticalLayout extends ToolbarLayout
{

	public VerticalLayout()
	{
		spacing = 5;
	}

	public void layout(IFigure parent)
	{
		List children = parent.getChildren();
		int numHorizontalChildren=0;
		int numChildren = children.size();
		if (numChildren > 0) {
			Rectangle clientArea = transposer.t(parent.getClientArea());
			int x = clientArea.x+2;
			int y = clientArea.y+3;
			IFigure firstChild = (IFigure) children.get(0);
			Dimension first = new Dimension(-1, clientArea.height);
			Point firstloc = new Point(x, y);
			Rectangle firstNewBounds = new Rectangle(firstloc, first);
			firstChild.setBounds(transposer.t(firstNewBounds));
			for (int i = 1; i < numChildren; i++) {
				IFigure child = (IFigure) children.get(i);
				if(child instanceof HorizonBlockModelFigure)
					numHorizontalChildren++;
				
			}
			for (int i = 1; i < numChildren; i++) {
				IFigure child = (IFigure) children.get(i);
				Point loc = new Point(x, y + 4);
				Dimension size = new Dimension(clientArea.width-4, -1);
				Rectangle newBounds = new Rectangle(loc, size);
				if(child instanceof HorizonBlockModelFigure)
				{
					newBounds.height = 40;
					//numHorizontalChildren++;
				}
				
				
				else{
					int divided = (clientArea.height - 5 -numHorizontalChildren*40- ((numChildren - 2) * spacing))
							/ (numChildren - 1-numHorizontalChildren);
					/*if (i == numChildren - 1)
						divided = clientArea.height - 5 -numHorizontalChildren*40- ((divided + spacing) * (numChildren - 2));*/
				    newBounds.height = divided;
				}
				child.setBounds(transposer.t(newBounds));
				y += newBounds.height + spacing;
			}
		}
	}
}
