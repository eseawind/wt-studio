package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.TitleBarBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;

public class VOTitleBorder  extends TitleBarBorder
{
   
	
	
	private Insets padding = new Insets(1, 3, 2, 2);
	public void paint(IFigure figure, Graphics g, Insets insets) {
		tempRect.setBounds(getPaintRectangle(figure, insets));
		Rectangle rec = tempRect;
		rec.height = Math.min(rec.height, getTextExtents(figure).height
				+ padding.getHeight()); 
		g.clipRect(rec);
		g.setForegroundColor(ColorConstants.titleInactiveGradient); 
		g.setBackgroundColor(ColorConstants.white);
		g.fillGradient(rec, true);  

		
		
		int x = rec.x + padding.left;
		int y = rec.y + padding.top;
		Image image=Activator.getImageDescriptor(ImageResource.VO).createImage();
		g.drawImage(image, new Point(x,y));
        x=x+Activator.getImageDescriptor(ImageResource.TABLE).createImage().getBounds().width+5;
		int textWidth = getTextExtents(figure).width;
		int freeSpace = rec.width - padding.getWidth() - textWidth;

		if (getTextAlignment() == PositionConstants.CENTER)
			freeSpace /= 2;
		if (getTextAlignment() != PositionConstants.LEFT)
			x += freeSpace;

		g.setFont(getFont(figure));
		g.setForegroundColor(ColorConstants.darkBlue);
		g.drawString(getLabel(), x, y);
		
		g.setForegroundColor(ColorConstants.darkBlue);
		g.drawLine(rec.x, rec.y+rec.height-1, rec.width+rec.x+2, rec.y+rec.height-1);
	}
}

