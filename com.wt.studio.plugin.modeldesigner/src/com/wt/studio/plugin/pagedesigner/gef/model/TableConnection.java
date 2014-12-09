package com.wt.studio.plugin.pagedesigner.gef.model;

import org.eclipse.draw2d.geometry.Rectangle;

public class TableConnection extends Element
{

	/**
	 * 
	 */
    private MOFunctionTableModel source;
	private MOFunctionTableModel target;
    public MOFunctionTableModel getSource()
	{
		return source;
	}

	public void setSource(MOFunctionTableModel source)
	{
		this.source = source;
	}

	public MOFunctionTableModel getTarget()
	{
		return target;
	}

	public void setTarget(MOFunctionTableModel target)
	{
		this.target = target;
	}

	 public TableConnection(MOFunctionTableModel source, MOFunctionTableModel target) {
	        super();
	        this.source = source;
	        this.target = target;
	        source.addOutput(this);
	        target.addInput(this);
	    }
	
	public TableConnection()
		{
			super();// TODO Auto-generated constructor stub
		}

	@Override
	public Rectangle getRectangle()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
