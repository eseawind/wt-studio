package com.wt.studio.plugin.querydesigner.gef.layout;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.querydesigner.gef.figures.PageModelFigure;

public class DiagramFreeFormLayout extends FreeformLayout
{

	public void layout(IFigure parent)
	{
		Iterator children = parent.getChildren().iterator();
		Point offset = getOrigin(parent);
		IFigure f;
		while (children.hasNext()) {

			f = (IFigure) children.next();
			if (f instanceof PageModelFigure) {
				Rectangle bounds = (Rectangle) getConstraint(f);
				if (bounds == null) {
					bounds = new Rectangle(0, 0, 0, 0);
				}
				Dimension preferredSize = calculatePreferredSize(f, bounds.width, bounds.height);
				bounds = bounds.getCopy();
				if (bounds.height < preferredSize.height) {
					bounds.height = preferredSize.height + 50;
					bounds.width = preferredSize.width + 2;
				}
				f.setBounds(bounds);
			}
		}
	}

	protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint)
	{
		List children = container.getChildren();
		Dimension prefSize = calculateChildrenSize(children, wHint, hHint);
		return prefSize;
	}

	private Dimension calculateChildrenSize(List children, int wHint, int hHint)
	{
		Dimension childSize;
		IFigure child;
		int height = 0, width = 0;
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure) children.get(i);
			Rectangle bounds = child.getBounds();
			if (bounds.height == 0)
				bounds.height = 150;
			height += bounds.height + 3;
			if (bounds.width > 0) {
				width = bounds.width;
			}
		}
		return new Dimension(width, height);
	}
}
