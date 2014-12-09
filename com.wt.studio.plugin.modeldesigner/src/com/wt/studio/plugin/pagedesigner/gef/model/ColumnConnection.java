package com.wt.studio.plugin.pagedesigner.gef.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;


public class ColumnConnection extends  Element
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1956278525224877305L;
    private FunctionColumnModel source;
    private FunctionColumnModel target;
    public static String PROP_BENDPOINT="bendpoint";
    List bendpoints=new ArrayList();
    private double sourceAngle=Double.MAX_VALUE;
    private double targetAngle=Double.MAX_VALUE;
    public FunctionColumnModel getSource()
	{
		return source;
	}

	public void setSource(FunctionColumnModel source)
	{
		this.source = source;
	}

	public FunctionColumnModel getTarget()
	{
		return target;
	}

	public void setTarget(FunctionColumnModel target)
	{
		this.target = target;
	}

	public double getSourceAngle()
	{
		return sourceAngle;
	}

	public void setSourceAngle(double sourceAngle)
	{
		this.sourceAngle = sourceAngle;
	}

	public double getTargetAngle()
	{
		return targetAngle;
	}

	public void setTargetAngle(double targetAngle)
	{
		this.targetAngle = targetAngle;
	}

	 public void addBendpoint(int index, ConnectionBendpoint point) {
	    	
	    	getBendpoints().add(index, point);
	        firePropertyChange(PROP_BENDPOINT, null, null);
	    }

	    public void setBendpointRelativeDimensions(int index, Dimension d1, Dimension d2){
	        ConnectionBendpoint cbp=(ConnectionBendpoint)getBendpoints().get(index);
	        cbp.setRelativeDimensions(d1,d2);
	        firePropertyChange(PROP_BENDPOINT, null, null);
	    }

	    public void removeBendpoint(int index) {
	        getBendpoints().remove(index);
	        firePropertyChange(PROP_BENDPOINT, null, null);
	    }
	    public List getBendpoints()
	    {
	    	return this.bendpoints;
	    }
	    public void setBendpoints(List bendpoints) {
			this.bendpoints = bendpoints;
		}
	    public ColumnConnection(FunctionColumnModel source, FunctionColumnModel target) {
	        super();
	        this.source = source;
	        this.target = target;
	        source.addOutput(this);
	        target.addInput(this);
	    }
	
	public ColumnConnection()
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
