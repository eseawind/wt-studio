package com.wt.studio.plugin.pagedesigner.gef.layout;

import java.util.List;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.pagedesigner.gef.figure.FormFigure;
import com.wt.studio.plugin.pagedesigner.gef.figure.FormTitleFigure;

public class FillLayout extends XYLayout
{
	public void layout(IFigure parent)
	{
		Dimension size;
		List children = parent.getChildren();
		int numChildren = children.size();
		int flag=0;
		Rectangle clientArea = parent.getClientArea();
		int x = clientArea.x+3;
		int y = clientArea.y;
		IFigure child = (IFigure) children.get(numChildren-1);
		Point loc = new Point(x, y );
		if(child instanceof FormTitleFigure )
		{
			flag=0;
			size = new Dimension(clientArea.width-6, 25);
			Rectangle newBounds = new Rectangle(loc, size);
			child.setBounds(newBounds);
		}
			if(child instanceof FormFigure)
		{
			flag=1;
			size = new Dimension(clientArea.width-6, 0);
			Rectangle newBounds = new Rectangle(loc, size);
			child.setBounds(newBounds);	
		}
		for(int i=0;i<numChildren-1;i++)
		{
			IFigure otherChild = (IFigure) children.get(i);
			Rectangle bounds = (Rectangle) getConstraint(otherChild);
			if(bounds!=null)
			{
			if(flag==0)
			{
			bounds.setLocation(x,y+25);
			bounds.setWidth(parent.getClientArea().width-6);
			}
			if(flag==1)
			{
			bounds.setLocation(x,y);
			bounds.setWidth(parent.getClientArea().width-6);
			}
			}
			if (bounds == null)
				continue;

			if (bounds.width == -1 || bounds.height == -1) {
				Dimension preferredSize = otherChild.getPreferredSize(bounds.width,
						bounds.height);
				bounds = bounds.getCopy();
				if (bounds.width == -1)
					bounds.width = preferredSize.width;
				if (bounds.height == -1)
					bounds.height = preferredSize.height;
			}
			//bounds = bounds.getTranslated(offset);
			otherChild.setBounds(bounds);
			y=y+bounds.height+2;
			
		}
	

}
}
