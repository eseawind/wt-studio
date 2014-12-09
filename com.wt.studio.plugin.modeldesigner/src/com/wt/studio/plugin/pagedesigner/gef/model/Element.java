package com.wt.studio.plugin.pagedesigner.gef.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.UUID;

import org.eclipse.draw2d.geometry.Rectangle;

abstract public class Element implements Serializable, Cloneable {

	private static final long serialVersionUID = 8915727344770752164L;
	public static final String PROP_WIDTH="Width",PROP_HEIGHT="Height";
	private String fontName;

	private int fontSize;

	private int[] color;
	private String height="";
	private String width="";

	// property change listeners
	protected Rectangle rectangle = new Rectangle(0, 0, -1, -1);
	public static final String PROP_RECTANGLE = "rectangle";

	public abstract Rectangle getRectangle();

	
	public String getHeight()
	{
		this.height=String.valueOf(this.rectangle.height);
		return height;
	}
	public void setHeight(String height)
	{
		this.height = height;
		this.rectangle.setHeight(Integer.parseInt(height));
		firePropertyChange(PROP_RECTANGLE, null, height);
	}
	public String getWidth()
	{
		this.width=String.valueOf(this.rectangle.width);
		return width;
	}
	public void setWidth(String width)
	{
		this.width = width;
		this.rectangle.setWidth(Integer.parseInt(width));
		firePropertyChange(PROP_RECTANGLE, null, width);
	}
	public void setRectangle(Rectangle rectangle)
	{
		Rectangle old = this.rectangle;
		this.rectangle = rectangle;
		//System.out.println("模型发送改变命令");
		firePropertyChange(PROP_RECTANGLE, old, rectangle);
	}
	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int[] getColor() {
		return color;
	}

	public void setColor(int[] color) {
		this.color = color;
	}

	   // property change listeners
	   transient protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	   public void addPropertyChangeListener(PropertyChangeListener listener) {
	      listeners.addPropertyChangeListener(listener);
	   }

	   protected void firePropertyChange(String prop, Object old, Object newValue) {
	      listeners.firePropertyChange(prop, old, newValue);
	   }

	   protected void fireStructureChange(String prop, Object child) {
	      listeners.firePropertyChange(prop, null, child);
	   }
	   protected void fireStructureChange(String prop) {
		      listeners.firePropertyChange(prop, null, null);
		   }

	   // implemented in order to create listeners field
	   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	      in.defaultReadObject();
	      listeners = new PropertyChangeSupport(this);
	   }

	   public void removePropertyChangeListener(PropertyChangeListener l) {
	      listeners.removePropertyChangeListener(l);
	   }

	
	   public static String uuid()
		{
			return UUID.randomUUID().toString().replace("-", "");
		}
	
}
