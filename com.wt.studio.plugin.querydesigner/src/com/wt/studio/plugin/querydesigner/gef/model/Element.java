package com.wt.studio.plugin.querydesigner.gef.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.eclipse.draw2d.geometry.Rectangle;

// abstract base class of elements in the model

abstract public class Element implements Cloneable, Serializable
{

	// serialization version
	static final long serialVersionUID = 1;

	// property change listeners
	transient protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	public Rectangle rectangle = new Rectangle(0, 0, -1, -1);
	public static final String PROP_RECTANGLE = "rectangle";

	public abstract Rectangle getRectangle();

	public void setRectangle(Rectangle rectangle)
	{
		Rectangle old = this.rectangle;
		this.rectangle = rectangle;
		firePropertyChange(PROP_RECTANGLE, old, rectangle);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		listeners.addPropertyChangeListener(listener);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue)
	{
		listeners.firePropertyChange(prop, old, newValue);
	}

	protected void fireStructureChange(String prop, Object child)
	{
		listeners.firePropertyChange(prop, null, child);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		listeners = new PropertyChangeSupport(this);
	}

	public void removePropertyChangeListener(PropertyChangeListener l)
	{
		listeners.removePropertyChangeListener(l);
	}

}