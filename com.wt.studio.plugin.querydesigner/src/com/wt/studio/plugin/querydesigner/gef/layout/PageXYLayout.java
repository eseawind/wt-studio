package com.wt.studio.plugin.querydesigner.gef.layout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.querydesigner.gef.figures.QueryBlockModelFigure;

public class PageXYLayout extends XYLayout
{

	/** The layout contraints */
	protected Map constraints = new HashMap();

	protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint)
	{
		int height = 40;
		Rectangle rect = new Rectangle();
		ListIterator children = f.getChildren().listIterator();

		while (children.hasNext()) {
			IFigure child = (IFigure) children.next();
			height = height + 40;
		}
		rect.setHeight(height);
		Dimension d = rect.getSize();
		Insets insets = f.getInsets();
		return new Dimension(d.width + insets.getWidth(), d.height + insets.getHeight())
				.union(getBorderPreferredSize(f));
	}

	/**
	 * @see LayoutManager#getConstraint(IFigure)
	 */
	public Object getConstraint(IFigure figure)
	{
		return constraints.get(figure);
	}

	/**
	 * Returns the origin for the given figure.
	 * 
	 * @param parent
	 *            the figure whose origin is requested
	 * @return the origin
	 */
	public Point getOrigin(IFigure parent)
	{
		return parent.getClientArea().getLocation();
	}

	/**
	 * Implements the algorithm to layout the components of the given container figure. Each
	 * component is laid out using its own layout constraint specifying its size and position.
	 * 
	 * @see LayoutManager#layout(IFigure)
	 */
	public void layout(IFigure parent)
	{
		Iterator children = parent.getChildren().iterator();
		Point offset = getOrigin(parent);
		int x = offset.x;
		int y = offset.y;
		IFigure f;
		while (children.hasNext()) {
			f = (IFigure) children.next();
			Rectangle bounds = (Rectangle) getConstraint(f);
			if (bounds != null) {
				bounds.setLocation(x, y);
				bounds.setWidth(parent.getClientArea().width);
			}
			if (bounds == null)
				continue;
			Dimension preferredSize1 = calculatePreferredSize(f, 0, 0);
			bounds = bounds.getCopy();
			if (f instanceof QueryBlockModelFigure) {
				bounds.height = preferredSize1.height + 30;
			}
			f.setBounds(bounds);
			y = y + bounds.height + 2;
		}
		parent.getParent().getLayoutManager().layout(parent.getParent());
	}

	/**
	 * @see LayoutManager#remove(IFigure)
	 */
	public void remove(IFigure figure)
	{
		super.remove(figure);
		constraints.remove(figure);
	}

	/**
	 * Sets the layout constraint of the given figure. The constraints can only be of type
	 * {@link Rectangle}.
	 * 
	 * @see LayoutManager#setConstraint(IFigure, Object)
	 * @since 2.0
	 */
	public void setConstraint(IFigure figure, Object newConstraint)
	{
		super.setConstraint(figure, newConstraint);
		if (newConstraint != null) {
			constraints.put(figure, newConstraint);
		}
	}

}
