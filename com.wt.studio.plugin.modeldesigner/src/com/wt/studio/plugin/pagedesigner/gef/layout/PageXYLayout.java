package com.wt.studio.plugin.pagedesigner.gef.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class PageXYLayout  extends XYLayout {

	/** The layout contraints */
	protected Map constraints = new HashMap();

	/**
	 * Calculates and returns the preferred size of the input figure. Since in
	 * XYLayout the location of the child should be preserved, the preferred
	 * size would be a region which would hold all the children of the input
	 * figure. If no constraint is set, that child is ignored for calculation.
	 * If width and height are not positive, the preferred dimensions of the
	 * child are taken.
	 * 
	 * @see AbstractLayout#calculatePreferredSize(IFigure, int, int)
	 * @since 2.0
	 */
	protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint) {
		Rectangle rect = new Rectangle();
		ListIterator children = f.getChildren().listIterator();
		while (children.hasNext()) {
			IFigure child = (IFigure) children.next();
			Rectangle r = (Rectangle) constraints.get(child);
			if (r == null)
				continue;

			if (r.width == -1 || r.height == -1) {
				Dimension preferredSize = child.getPreferredSize(r.width,
						r.height);
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
		return new Dimension(d.width + insets.getWidth(), d.height
				+ insets.getHeight()).union(getBorderPreferredSize(f));
	}

	/**
	 * @see LayoutManager#getConstraint(IFigure)
	 */
	public Object getConstraint(IFigure figure) {
		return constraints.get(figure);
	}

	/**
	 * Returns the origin for the given figure.
	 * 
	 * @param parent
	 *            the figure whose origin is requested
	 * @return the origin
	 */
	public Point getOrigin(IFigure parent) {
		return parent.getClientArea().getLocation();
	}

	/**
	 * Implements the algorithm to layout the components of the given container
	 * figure. Each component is laid out using its own layout constraint
	 * specifying its size and position.
	 * 
	 * @see LayoutManager#layout(IFigure)
	 */
	public void layout(IFigure parent) {
		Iterator children = parent.getChildren().iterator();
		Point offset = getOrigin(parent);
		int x=offset.x+3;
		int y=offset.y+3;
		IFigure f;
		while (children.hasNext()) {
			f = (IFigure) children.next();
			//System.out.println(x);
			//System.out.println(y);
			Rectangle bounds = (Rectangle) getConstraint(f);
			if(bounds!=null)
			{
			bounds.setLocation(x,y);
			bounds.setWidth(parent.getClientArea().width-6);
			}
			if (bounds == null)
				continue;

			if (bounds.width == -1 || bounds.height == -1) {
				Dimension preferredSize = f.getPreferredSize(bounds.width,
						bounds.height);
				bounds = bounds.getCopy();
				if (bounds.width == -1)
					bounds.width = preferredSize.width;
				if (bounds.height == -1)
					bounds.height = preferredSize.height;
			}
			//bounds = bounds.getTranslated(offset);
			f.setBounds(bounds);
			y=y+bounds.height+2;
			//x=x;
		}
	}

	/**
	 * @see LayoutManager#remove(IFigure)
	 */
	public void remove(IFigure figure) {
		super.remove(figure);
		constraints.remove(figure);
	}

	/**
	 * Sets the layout constraint of the given figure. The constraints can only
	 * be of type {@link Rectangle}.
	 * 
	 * @see LayoutManager#setConstraint(IFigure, Object)
	 * @since 2.0
	 */
	public void setConstraint(IFigure figure, Object newConstraint) {
		super.setConstraint(figure, newConstraint);
		if (newConstraint != null)
		{
			//Rectangle Constraint=(Rectangle)newConstraint;
			//Constraint.setWidth(100);
			constraints.put(figure, newConstraint);
		}
	}

}