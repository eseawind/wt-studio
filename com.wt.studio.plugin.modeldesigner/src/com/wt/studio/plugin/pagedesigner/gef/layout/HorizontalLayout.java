package com.wt.studio.plugin.pagedesigner.gef.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class HorizontalLayout extends ToolbarLayout
{

	
	
	protected Rectangle rec;
	protected Rectangle newBounds;

	protected Map constraints = new HashMap();
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
			Rectangle bounds = (Rectangle) getConstraint(child);
			if (bounds == null)
			{
				bounds=child.getBounds();
				//bounds.setHeight(parent.getClientArea().height - 5);
			}
			int divided = (clientArea.width - 6 - ((numChildren - 2) * spacing))
					/ (numChildren - 1);
			if (i == numChildren - 1)
				divided = clientArea.width - 6 - ((divided + spacing) * (numChildren - 2));
			newBounds.width = bounds.width;
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
	

	/*protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint)
	{
		Rectangle rect = new Rectangle();
		ListIterator children = f.getChildren().listIterator();
		while (children.hasNext()) {
			IFigure child = (IFigure) children.next();
			Rectangle r = (Rectangle) constraints.get(child);
			if (r == null)
				continue;

			if (r.width == -1 || r.height == -1) {
				Dimension preferredSize = child.getPreferredSize(r.width, r.height);
				r = r.getCopy();
				if (r.width == -1)
					r.width = preferredSize.width;
				if (r.height == -1)
					r.height = preferredSize.height;
			}
			rect.union(r);
		}
		Dimension d = rect.getSize();
		Insets insets = f.getInsets();
		return new Dimension(d.width + insets.getWidth(), d.height + insets.getHeight())
				.union(getBorderPreferredSize(f));
	}

	

	/*public Point getOrigin(IFigure parent)
	{
		return parent.getClientArea().getLocation();
	}

	public void layout(IFigure parent)
	{
		Iterator children = parent.getChildren().iterator();
		Point offset = getOrigin(parent);
		int x = offset.x + 5;
		int y = offset.y;
		IFigure f;
		while (children.hasNext()) {

			f = (IFigure) children.next();
			// System.out.println(x);
			// System.out.println(y);
			Rectangle bounds = (Rectangle) getConstraint(f);
			if (bounds != null) {
				bounds.setLocation(x, y);
				bounds.setHeight(parent.getClientArea().height - 5);
			}
			if (bounds == null)
			{
				bounds=f.getBounds();
				bounds.setHeight(parent.getClientArea().height - 5);
			}

			if (bounds.width == -1 || bounds.height == -1) {
				Dimension preferredSize = f.getPreferredSize(bounds.width, bounds.height);
				bounds = bounds.getCopy();
				if (bounds.width == -1)
					bounds.width = preferredSize.width;
				if (bounds.height == -1)
					bounds.height = preferredSize.height;
			}
			// bounds = bounds.getTranslated(offset);
			f.setBounds(transposer.t(bounds));
			x = x + bounds.width + 2;
			// x=x;
		}
	}

	/*public void remove(IFigure figure)
	{
		super.remove(figure);
		constraints.remove(figure);
	}*/

	public void setConstraint(IFigure figure, Object newConstraint)
	{
		super.setConstraint(figure, newConstraint);
		if (newConstraint != null) {
			// Rectangle Constraint=(Rectangle)newConstraint;
			// Constraint.setWidth(100);
			constraints.put(figure, newConstraint);
		}
	}

	public Object getConstraint(IFigure figure)
	{
		return constraints.get(figure);
	}
}
