package com.wt.studio.plugin.querydesigner.gef.layout;

import java.util.Iterator;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class ScrollAreaLayout extends FlowLayout
{

	public Point getOrigin(IFigure parent)
	{
		return parent.getClientArea().getLocation();
	}

	public void layout(IFigure parent)
	{

		Iterator children = parent.getChildren().iterator();
		Point offset = getOrigin(parent);
		int x = offset.x;
		int y = offset.y;
		IFigure f;
		while (children.hasNext()) {
			f = (IFigure) children.next();
			Rectangle bounds = new Rectangle();
			bounds.setLocation(x, y);
			bounds.setHeight(parent.getParent().getParent().getParent().getClientArea().height - 25);
			Dimension pref = transposer.t(getChildSize(f, -1, -1));
			bounds = bounds.getCopy();
			bounds.width = pref.width;
			f.setBounds(bounds);
			x = x + bounds.width + 5;
		}
		parent.getParent().getLayoutManager().layout(parent.getParent());
	}
}
