package com.wt.studio.plugin.modeldesigner.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gef.rulers.RulerProvider;

import com.wt.studio.plugin.modeldesigner.editor.Anchor.BorderAnchor;
import com.wt.studio.plugin.modeldesigner.editor.Anchor.RectangleBorderAnchor;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;
import com.wt.studio.plugin.modeldesigner.editor.policy.MyNodeEditPolicy;
import com.wt.studio.plugin.modeldesigner.editor.policy.NodeGraphicalNodeEditPolicy;

public  class BONodeEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener,NodeEditPart
{
	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((BONodeModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((BONodeModel) getModel()).removePropertyChangeListener(this);
	}
	public Object getAdapter(Class adapter)
	{
		if (adapter == SnapToHelper.class) {
			List snapStrategies = new ArrayList();
			Boolean val = (Boolean) getViewer()
					.getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGuides(this));
			val = (Boolean) getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGeometry(this));
			val = (Boolean) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGrid(this));

			if (snapStrategies.size() == 0)
				return null;
			if (snapStrategies.size() == 1)
				return snapStrategies.get(0);
			SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
			for (int i = 0; i < snapStrategies.size(); i++)
				ss[i] = (SnapToHelper) snapStrategies.get(i);
			return new CompoundSnapToHelper(ss);
		}
		return super.getAdapter(adapter);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String prop = evt.getPropertyName();
		if(prop.equals(NoteModel.PROP_INPUTS))
	            refreshTargetConnections();
	    else if(prop.equals(NoteModel.PROP_OUTPUTS))
	            refreshSourceConnections();
	}

	@Override
	protected IFigure createFigure()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new MyNodeEditPolicy());
	}

	
    public void reorder() {
	   IFigure parentFigure = this.figure.getParent();
	   parentFigure.remove(this.figure);
	   parentFigure.add(this.figure);
	   this.figure.repaint();
	}

    public ConnectionAnchor getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart connection) {
       // return new ChopboxAnchor(getFigure());
    	NodeConnectionEditPart con =(NodeConnectionEditPart)connection;
    			BorderAnchor anchor =con.getSourceAnchor();
    			if(anchor == null || anchor.getOwner() != getFigure()) {
    				anchor = new RectangleBorderAnchor(getFigure());
    				NodeConnection conModel = (NodeConnection)con.getModel();
    				anchor.setAngle(conModel.getSourceAngle());
    				con.setSourceAnchor(anchor);
    			}
    			return anchor;
    }

    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        //return new ChopboxAnchor(getFigure());
    	if(request instanceof ReconnectRequest) {
    		ReconnectRequest r = (ReconnectRequest)request;
    		NodeConnectionEditPart con =(NodeConnectionEditPart) r.getConnectionEditPart();
    		NodeConnection conModel = (NodeConnection)con.getModel();
    		BorderAnchor anchor = con.getSourceAnchor();
    		GraphicalEditPart part = (GraphicalEditPart)r.getTarget();
    		if(anchor == null || anchor.getOwner() != part.getFigure()) {
    		    anchor = new RectangleBorderAnchor(getFigure());
    			anchor.setAngle(conModel.getSourceAngle());
    			con.setSourceAnchor(anchor);
    		}
    		
    		Point loc = r.getLocation();
    		Rectangle rect = Rectangle.SINGLETON;
    		rect.setBounds(getFigure().getBounds());
    		getFigure().translateToAbsolute(rect);
    		Point ref = rect.getCenter();
    		double dx = loc.x - ref.x;
    		double dy = loc.y - ref.y;
    		anchor.setAngle(Math.atan2(dy, dx));
    		con.setSourceAnchor(anchor);
    		conModel.setSourceAngle(anchor.getAngle());
    		return anchor;		
    	} else {
    			return new RectangleBorderAnchor(getFigure());
    	}
    }

    public ConnectionAnchor getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart connection) {
        //return new ChopboxAnchor(getFigure());
    	NodeConnectionEditPart con =(NodeConnectionEditPart)connection;
		BorderAnchor anchor =con.getTargetAnchor();
		if(anchor == null || anchor.getOwner() != getFigure()) {
			anchor = new RectangleBorderAnchor(getFigure());
			NodeConnection conModel = (NodeConnection)con.getModel();
			anchor.setAngle(conModel.getTargetAngle());
			con.setTargetAnchor(anchor);
		}
		return anchor;
    }

    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        //return new ChopboxAnchor(getFigure());

        //return new ChopboxAnchor(getFigure());
    	if(request instanceof ReconnectRequest) {
    		ReconnectRequest r = (ReconnectRequest)request;
    		NodeConnectionEditPart con =(NodeConnectionEditPart) r.getConnectionEditPart();
    		NodeConnection conModel = (NodeConnection)con.getModel();
    		BorderAnchor anchor = con.getTargetAnchor();
    		GraphicalEditPart part = (GraphicalEditPart)r.getTarget();
    		if(anchor == null || anchor.getOwner() != part.getFigure()) {
    		    anchor = new RectangleBorderAnchor(getFigure());
    			anchor.setAngle(conModel.getTargetAngle());
    			con.setTargetAnchor(anchor);
    		}
    		
    		Point loc = r.getLocation();
    		Rectangle rect = Rectangle.SINGLETON;
    		rect.setBounds(getFigure().getBounds());
    		getFigure().translateToAbsolute(rect);
    		Point ref = rect.getCenter();
    		double dx = loc.x - ref.x;
    		double dy = loc.y - ref.y;
    		anchor.setAngle(Math.atan2(dy, dx));
    		con.setTargetAnchor(anchor);
    		conModel.setTargetAngle(anchor.getAngle());
    		return anchor;		
    	} else {
    			return new RectangleBorderAnchor(getFigure());
    	}
    
    }

    protected List getModelSourceConnections() {
        return ((BONodeModel) this.getModel()).getOutgoingConnections();
    }

    protected List getModelTargetConnections() {
        return ((BONodeModel) this.getModel()).getIncomingConnections();
    }



}