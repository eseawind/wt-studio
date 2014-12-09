package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class TextFieldFigure extends Label
{

	private String title;
	private int flag;
 
    public TextFieldFigure(String title,int flag) {
      
        //setMinimumSize(new Dimension(50,
        //        100));
       // LineBorder lineBorder = new LineBorder();
	    //lineBorder.setColor(ColorConstants.gray);
	    //setBorder(lineBorder);
        this.title=title;
        this.flag=flag;
    }
 
    /* (non-Javadoc)
     * @see org.eclipse.draw2d.Figure#setBounds(org.eclipse.draw2d.geometry.Rectangle)
     */
    @Override
   public void setBounds(Rectangle rect) {
    	
     // Rectangle label=((Figure)this.getParent().getChildren().get(0)).getBounds();
   	   rect.height = this.getParent().getBounds().height;
   	   rect.width= this.getParent().getBounds().width;
   	   super.setBounds(rect);
    	    
    }
 
    /* (non-Javadoc)
     * @see org.eclipse.draw2d.Label#paintFigure(org.eclipse.draw2d.Graphics)
     */
    //@Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        Rectangle bound = getBounds();
        graphics.setBackgroundColor(ColorConstants.white);
        graphics.fillRectangle(bound.x,bound.y, bound.width, bound.height);
        if(flag==1)
        {
        	graphics.setForegroundColor(ColorConstants.red);
        	graphics.drawText("*", bound.x, bound.y + bound.height/2-8);
        }
        graphics.setForegroundColor(ColorConstants.black);
        graphics.drawText(title, bound.x + 5, bound.y + bound.height/2-8);
        graphics.setForegroundColor(ColorConstants.gray);
        graphics.drawRectangle(bound.x + 52, bound.y, bound.width-56, bound.height-4);
    }
 
    /* (non-Javadoc)
     * @see org.eclipse.draw2d.Label#setText(java.lang.String)
     */
    //@Override
   /* public void setText(String s) {
        super.setText(s);
        repaint();
    }*/
	
}
