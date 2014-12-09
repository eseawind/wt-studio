package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;

public class CheckBoxFigure extends Label {

	private String title;
	private String value;

	public CheckBoxFigure(String title,String value) {
		//super(text);
		//setDecrotorImage(Activator.getImageDescriptor(ImageResource.MO).createImage());
	     this.title=title;
	     this.value=value;
	}
	 public void setBounds(Rectangle rect) {
	    	
	     // Rectangle label=((Figure)this.getParent().getChildren().get(0)).getBounds();
		  rect.height = this.getParent().getBounds().height;
	   	   rect.width= this.getParent().getBounds().width-22;
	   	   super.setBounds(rect);
	    	    
	    }
	 protected void paintFigure(Graphics graphics) {
	        super.paintFigure(graphics);
	        Rectangle bound = getBounds();
	        graphics.setBackgroundColor(ColorConstants.white);
	        graphics.fillRectangle(bound.x,bound.y, bound.width, bound.height);
	        graphics.drawText(title, bound.x + 5, bound.y + bound.height/2-8);
	        int x=bound.x+52;
	        for(String cell:value.split(";"))
	        {
	        graphics.drawImage(Activator.getImageDescriptor(ImageResource.CHECK).createImage(), x, bound.y + bound.height/2-8);
	        graphics.setForegroundColor(ColorConstants.black);
	        graphics.drawText(cell, x+30, bound.y + bound.height/2-8);
	        x=x+55;
	        }
	    }
	
}