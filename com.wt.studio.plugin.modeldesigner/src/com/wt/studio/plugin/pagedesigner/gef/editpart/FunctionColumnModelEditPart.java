package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
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

import com.wt.studio.plugin.modeldesigner.editor.figure.ColumnModelFigure;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.Anchor.BorderAnchor;
import com.wt.studio.plugin.pagedesigner.gef.Anchor.RectangleBorderAnchor;
import com.wt.studio.plugin.pagedesigner.gef.figure.FunctionColumnModelFigure;
import com.wt.studio.plugin.pagedesigner.gef.figure.FunctionTableFigure;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.ColumnGraphicalNodeEditPolicy;

public class FunctionColumnModelEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener,NodeEditPart
{

	
	private FunctionColumnModelFigure figure;
	
	
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((FunctionColumnModel) getModel()).addPropertyChangeListener(this);
	}

	protected void refreshVisuals()
	{
		this.figure.setColumn((FunctionColumnModel)this.getModel());
	}
	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((FunctionColumnModel) getModel()).removePropertyChangeListener(this);
	}
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if ( prop.equals(FunctionColumnModel.PROP_RECTANGLE)||
				prop.equals(FunctionColumnModel.PROP_SELECT)) {
			refreshVisuals();
			FunctionTableModelEditPart table=(FunctionTableModelEditPart) this.getParent();
			table.refreshVisuals();
			}
		else if(prop.equals(FunctionColumnModel.PROP_INPUTS))
	            refreshTargetConnections();
	    else if(prop.equals(FunctionColumnModel.PROP_OUTPUTS))
	            refreshSourceConnections();
		
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
	public void setSelected(int value) {
		
		   super.setSelected(value);
	        if (value != EditPart.SELECTED_NONE)
	        {
	            ((FunctionColumnModelFigure) getFigure()).setBackgroundColor(ColorConstants.titleGradient);
	            ((FunctionColumnModel)this.getModel()).setSelected(true);
	        }
	        else
	        {
	            ((FunctionColumnModelFigure) getFigure()).setBackgroundColor(ColorConstants.white);
	            ((FunctionColumnModel)this.getModel()).setSelected(false);
	        }
	
	}
	@Override
	protected IFigure createFigure()
	{
		if(figure==null)
			figure=new FunctionColumnModelFigure();
		figure.setColumn((FunctionColumnModel)this.getModel());
		return figure;
	}

	@Override
	protected void createEditPolicies()
	{
		// TODO Auto-generated method stub
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new ColumnGraphicalNodeEditPolicy());
	}

	public ConnectionAnchor getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart connection) 
	{
	    	ColumnConnectionEditPart con =(ColumnConnectionEditPart)connection;
	    			BorderAnchor anchor =con.getSourceAnchor();
	    			if(anchor == null || anchor.getOwner() != getFigure()) {
	    				anchor = new RectangleBorderAnchor(getFigure());
	    				ColumnConnection conModel = (ColumnConnection)con.getModel();
	    				anchor.setAngle(conModel.getSourceAngle());
	    				con.setSourceAnchor(anchor);
	    			}
	    			return anchor;
	    }

	    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
	    	if(request instanceof ReconnectRequest) {
	    		ReconnectRequest r = (ReconnectRequest)request;
	    		ColumnConnectionEditPart con =(ColumnConnectionEditPart) r.getConnectionEditPart();
	    		ColumnConnection conModel = (ColumnConnection)con.getModel();
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
	    	ColumnConnectionEditPart con =(ColumnConnectionEditPart)connection;
			BorderAnchor anchor =con.getTargetAnchor();
			if(anchor == null || anchor.getOwner() != getFigure()) {
				anchor = new RectangleBorderAnchor(getFigure());
				ColumnConnection conModel = (ColumnConnection)con.getModel();
				anchor.setAngle(conModel.getTargetAngle());
				con.setTargetAnchor(anchor);
			}
			return anchor;
	    }

	    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
	    	if(request instanceof ReconnectRequest) {
	    		ReconnectRequest r = (ReconnectRequest)request;
	    		ColumnConnectionEditPart con =(ColumnConnectionEditPart) r.getConnectionEditPart();
	    		ColumnConnection conModel = (ColumnConnection)con.getModel();
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
	        return ((FunctionColumnModel) this.getModel()).getOutgoingConnections();
	    }

	    protected List getModelTargetConnections() {
	        return ((FunctionColumnModel) this.getModel()).getIncomingConnections();
	    }
}

