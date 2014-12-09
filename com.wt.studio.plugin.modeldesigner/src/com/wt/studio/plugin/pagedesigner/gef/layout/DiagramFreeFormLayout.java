package com.wt.studio.plugin.pagedesigner.gef.layout;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class DiagramFreeFormLayout extends FreeformLayout
{
	 
	public void layout(IFigure parent) {
		//System.out.println("Diagram 布局");
		Iterator children = parent.getChildren().iterator();
		Point offset = getOrigin(parent);
		IFigure f;
		while (children.hasNext()) {
			f = (IFigure) children.next();
			Rectangle bounds = (Rectangle) getConstraint(f);
			if (bounds == null) {
				continue;
			}
			Dimension preferredSize = calculatePreferredSize(f,bounds.width,
						bounds.height);
			bounds = bounds.getCopy();
			if (bounds.height<preferredSize.height)
			{
				bounds.height=preferredSize.height+50;
			}
			f.setBounds(bounds);
		}
	}
	protected Dimension calculatePreferredSize(IFigure container, int wHint,
			int hHint) {
		List children = container.getChildren();
		Dimension prefSize = calculateChildrenSize(children, wHint, hHint);
		return prefSize;
	}
	private Dimension calculateChildrenSize(List children, int wHint,
			int hHint) {
		Dimension childSize;
		IFigure child;
		int height = 0, width = 0;
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure) children.get(i);
			Rectangle bounds=child.getBounds();
			height += bounds.height+3;
			width = bounds.width;
		}
		return new Dimension(width, height);
	}
}
