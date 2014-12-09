package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.pagedesigner.gef.tool.CommonEclipseUtil;

public class SelectModelFigure extends Figure
{

	private String title;
	public SelectModelFigure(String title)
	{
		this.title=title;
	}
	public void setName(String name)
	{
		this.title=name;
		repaint();
	}
	public String getName()
	{
		return this.title;
	}
	public void setBounds(Rectangle rect) {
	   rect.height = this.getParent().getBounds().height;
	   rect.width= this.getParent().getBounds().width;
	   super.setBounds(rect);
    }

	 protected void paintFigure(Graphics graphics) {
	        super.paintFigure(graphics);
	        Rectangle bound = getBounds();
	        graphics.setBackgroundColor(ColorConstants.white);
	        graphics.fillRectangle(bound.x,bound.y, bound.width, bound.height);
	        graphics.drawText(title, bound.x + 5, bound.y + bound.height/2-8);
	        graphics.setForegroundColor(ColorConstants.gray);
	        graphics.drawRectangle(bound.x + 52, bound.y, bound.width-56, bound.height-4);
	        graphics.drawImage(CommonEclipseUtil.getImage("icons/list.png").createImage(), bound.x+bound.width-30, bound.y + bound.height/2-14);
	    }
}

