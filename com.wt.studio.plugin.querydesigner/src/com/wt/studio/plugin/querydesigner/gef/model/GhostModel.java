package com.wt.studio.plugin.querydesigner.gef.model;

import org.eclipse.draw2d.geometry.Rectangle;

public class GhostModel extends Element
{

	@Override
	public Rectangle getRectangle()
	{
		if (rectangle.width < 0) {
			rectangle.width = 100;
		}
		if (rectangle.height < 0) {
			rectangle.height = 30;
		}
		return rectangle;
	}

}
