package com.wt.studio.plugin.modeldesigner.editor.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.draw2d.geometry.Rectangle;
abstract public class BONodeModel extends Element {

	private static final long serialVersionUID = 8915727344770752164L;
	
	private String fontName;
	private String uuid=uuid();
	private int fontSize;

	private int[] color;
	final public static String PROP_INPUTS = "INPUTS";
    final public static String PROP_OUTPUTS = "OUTPUTS";
	 

	

	protected List outputs = new ArrayList(5);

    protected List inputs = new ArrayList(5);

    
    public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}
    @SuppressWarnings("unchecked")
	public void addInput(NodeConnection connection) {
        this.inputs.add(connection);
        fireStructureChange(PROP_INPUTS, connection);
    }

    @SuppressWarnings("unchecked")
	public void addOutput(NodeConnection connection) {
        this.outputs.add(connection);
        fireStructureChange(PROP_OUTPUTS, connection);
    }

    public List getIncomingConnections() {
        return this.inputs;
    }
    public void setIncomingConnections(List inputs){
    	this.inputs=inputs;
    }
    public void setOutgoingConnections(List outputs){
    	this.outputs=outputs;
    }

    public List getOutgoingConnections() {
        return this.outputs;
    }

    public void removeInput(NodeConnection connection) {
        this.inputs.remove(connection);
        fireStructureChange(PROP_INPUTS, connection);
    }

    public void removeOutput(NodeConnection connection) {
        this.outputs.remove(connection);
        fireStructureChange(PROP_OUTPUTS, connection);
    }

	// property change listeners
	protected Rectangle rectangle = new Rectangle(0, 0, -1, -1);
	public static final String PROP_RECTANGLE = "rectangle";

	public abstract Rectangle getRectangle();

	public void setRectangle(Rectangle rectangle)
	{
		Rectangle old = this.rectangle;
		this.rectangle = rectangle;
		//System.out.println("发送消息");
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
	public int getX() {
		return this.rectangle.x;
	}

	public int getY() {
		return this.rectangle.y;
	}

	   // property change listeners
	private static String uuid()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
}