package com.wt.studio.plugin.modeldesigner.editor.Anchor;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

public class RectangleBorderAnchor extends BorderAnchor {

	public RectangleBorderAnchor(IFigure figure) {
		super(figure);
	}

	@Override
	public Point getBorderPoint(Point reference) {
		// 得到owner矩形，转换为绝对坐标
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getOwner().getBounds());
		getOwner().translateToAbsolute(r);
		
		// 根据角度，计算锚点相对于owner中心点的偏移
		double dx = 0.0, dy = 0.0;
		double tan = Math.atan2(r.height, r.width);
		if(angle >= -tan && angle <= tan) {
			dx = r.width >> 1;
			dy = dx * Math.tan(angle);
		} else if(angle >= tan && angle <= Math.PI - tan) {
			dy = r.height >> 1;
			dx = dy / Math.tan(angle);
		} else if(angle <= -tan && angle >= tan - Math.PI) {
			dy = -(r.height >> 1);
			dx = dy / Math.tan(angle);
		} else {
			dx = -(r.width >> 1);
			dy = dx * Math.tan(angle);
		}

		// 得到长方形中心点，加上偏移，得到最终锚点坐标
		PrecisionPoint pp = new PrecisionPoint(r.getCenter());	
		pp.translate((int)dx, (int)dy);
		return new Point(pp);	
	}
}
