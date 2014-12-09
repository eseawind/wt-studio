package com.wt.studio.plugin.pagedesigner.gef.Anchor;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

public abstract class BorderAnchor extends ChopboxAnchor {
	protected double angle;
	
	public BorderAnchor(IFigure figure) {
		super(figure);
		angle = Double.MAX_VALUE;
	}
	
	public abstract Point getBorderPoint(Point reference);
	
	public Point getLocation(Point reference) {
		/*if(angle == Double.MAX_VALUE)
			return super.getLocation(reference);
		else*/
			return getBorderPoint(reference);
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
}
