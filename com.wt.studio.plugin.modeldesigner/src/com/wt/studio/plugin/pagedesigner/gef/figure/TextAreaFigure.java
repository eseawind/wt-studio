package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Rectangle;

public class TextAreaFigure extends Label{
 
  
  private String title;
    /**
     *
     */
    public TextAreaFigure(String title) {
        //LineBorder lineBorder = new LineBorder();
       // lineBorder.setColor(ColorConstants.gray);
        //setBorder(lineBorder);
       // setText("请输入文字");
    	this.title=title;
    }
    public void setBounds(Rectangle rect) {
 	   rect.height = this.getParent().getBounds().height;
 	   rect.width= this.getParent().getBounds().width-2;
 	   super.setBounds(rect);
    }
    
 
    /* (non-Javadoc)
     * @see org.eclipse.draw2d.Label#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(Graphics graphics) {
    	 super.paintFigure(graphics);
         Rectangle bound = getBounds();
         graphics.setBackgroundColor(ColorConstants.white);
         graphics.fillRectangle(bound.x,bound.y, bound.width, bound.height);
         graphics.drawText(title, bound.x + 5, bound.y+4);
         graphics.setForegroundColor(ColorConstants.gray);
         graphics.drawRectangle(bound.x + 52, bound.y, bound.width-56, bound.height-4);
         graphics.setForegroundColor(ColorConstants.black);
         graphics.drawText("请输入文字", bound.x + 54, bound.y+4);
    }
}

