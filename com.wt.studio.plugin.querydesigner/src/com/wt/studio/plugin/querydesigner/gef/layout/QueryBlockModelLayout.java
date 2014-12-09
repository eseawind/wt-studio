package com.wt.studio.plugin.querydesigner.gef.layout;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.querydesigner.gef.figures.QueryHorizontalBlockModelFigure;

public class QueryBlockModelLayout extends ToolbarLayout
{

	protected Map constraints = new HashMap();

	protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint)
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

	public Object getConstraint(IFigure figure)
	{
		return constraints.get(figure);
	}

	public Point getOrigin(IFigure parent)
	{
		return parent.getClientArea().getLocation();
	}

	public QueryBlockModelLayout()
	{
		spacing = 1;
	}

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
			Dimension size = new Dimension(clientArea.width, -1);
			Rectangle newBounds = new Rectangle(loc, size);
			if (child instanceof QueryHorizontalBlockModelFigure) {
				newBounds.height = 50;
			} else
				newBounds.height = 40;
			child.setBounds(transposer.t(newBounds));

			y += newBounds.height + spacing;
		}
	}

	public void remove(IFigure figure)
	{
		super.remove(figure);
		constraints.remove(figure);
	}

	public void setConstraint(IFigure figure, Object newConstraint)
	{
		super.setConstraint(figure, newConstraint);
		if (newConstraint != null) {
			// Rectangle Constraint=(Rectangle)newConstraint;
			// Constraint.setWidth(100);
			constraints.put(figure, newConstraint);
		}
	}

}
