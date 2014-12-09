package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.SimpleRaisedBorder;
import org.eclipse.draw2d.geometry.Rectangle;

public class ButtonFigure extends Label{
	String name;
 
    public ButtonFigure(){
    	setText("按钮");
        SimpleRaisedBorder border = new SimpleRaisedBorder();
        setBorder(border);
    }
 
    public ButtonFigure(String text){
    	
        super(text);
        SimpleRaisedBorder border = new SimpleRaisedBorder();
        setBorder(border);
    }
    
    public void setBounds(Rectangle rect) {
    	//Rectangle rec=this.getParent().getBounds();
    	 rect.height = this.getParent().getBounds().height-2;
         rect.width= this.getParent().getBounds().width-2;
         super.setBounds(rect);
    }
    public void setName(String name)
    {
    	this.name=name;
    	setText(name);
    	repaint();
    }
}
