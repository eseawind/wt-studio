package com.wt.studio.plugin.querydesigner.gef.layout;

import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class HorizontalLayout extends XYLayout
{

	// protected Map constraints = new HashMap();

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

	public void layout(IFigure parent)
	{
		Iterator children = parent.getChildren().iterator();
		Point offset = getOrigin(parent);
		int x = offset.x + 5;
		int y = offset.y;
		IFigure f;
		while (children.hasNext()) {

			f = (IFigure) children.next();
			Rectangle bounds = (Rectangle) getConstraint(f);
			if (bounds != null) {
				bounds.setLocation(x, y);
				bounds.setHeight(parent.getClientArea().height - 5);
			}
			if (bounds == null)
				continue;

			if (bounds.width == -1 || bounds.height == -1) {
				Dimension preferredSize = f.getPreferredSize(bounds.width, bounds.height);
				bounds = bounds.getCopy();
				if (bounds.width == -1)
					bounds.width = preferredSize.width;
				if (bounds.height == -1)
					bounds.height = preferredSize.height;
			}
			// bounds = bounds.getTranslated(offset);
			f.setBounds(bounds);
			x = x + bounds.width + 2;
			// x=x;
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