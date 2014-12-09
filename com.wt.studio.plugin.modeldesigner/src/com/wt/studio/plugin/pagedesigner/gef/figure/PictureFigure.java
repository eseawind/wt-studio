package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.pagedesigner.gef.tool.CommonEclipseUtil;

public class PictureFigure extends Figure
{

	public PictureFigure(){
		LineBorder border=new LineBorder();
		this.setBorder(border);
	}
	public void setBounds(Rectangle rect) {
	   rect.height = this.getParent().getBounds().height;
	   rect.width= this.getParent().getBounds().width-2;
	   super.setBounds(rect);
    }

	 protected void paintFigure(Graphics graphics) {
	        super.paintFigure(graphics);
	        Rectangle bound = getBounds();
	        graphics.setBackgroundColor(ColorConstants.white);
	        graphics.fillRectangle(bound.x,bound.y, bound.width, bound.height);
	        graphics.drawImage(Activator.getImageDescriptor("icons/picture.png").createImage(), bound.x+bound.width/2-70, bound.y + bound.height/2-50);
	    }
}
