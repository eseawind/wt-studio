package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.pagedesigner.gef.tool.CommonEclipseUtil;


public class DateControlModelFigure extends Figure
{

	private IFigure container;
	//private GridData gridData;
	private String title;
	private Label imageFigure;

	public DateControlModelFigure(String title)
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
	        graphics.drawImage(Activator.getImageDescriptor("icons/control/date.png").createImage(), bound.x+bound.width-28, bound.y + bound.height/2-10);
	    }
}

