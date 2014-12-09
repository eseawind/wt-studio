package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

import com.wt.studio.plugin.pagedesigner.gef.Anchor.BorderAnchor;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.ConnectionBendpoint;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.ConnectionBendPointEditPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.ConnectionEditPolicy;


public class ColumnConnectionEditPart   extends AbstractConnectionEditPart implements PropertyChangeListener {
	
	

	private BorderAnchor sourceAnchor;
	private BorderAnchor targetAnchor;
	FunctionColumnModelEditPart source;
	FunctionColumnModelEditPart target;
	

	public BorderAnchor getSourceAnchor() {
		return sourceAnchor;
	}

	public void setSourceAnchor(BorderAnchor sourceAnchor) {
		this.sourceAnchor = sourceAnchor;
	}

	public BorderAnchor getTargetAnchor() {
		return targetAnchor;
	}

	public void setTargetAnchor(BorderAnchor targetAnchor) {
		this.targetAnchor = targetAnchor;
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((ColumnConnection) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((ColumnConnection) getModel()).removePropertyChangeListener(this);
	}
    protected IFigure createFigure() {
        PolylineConnection conn = new PolylineConnection();
        conn.setTargetDecoration(new PolygonDecoration());
        conn.setConnectionRouter(new BendpointConnectionRouter());
        final Label label = new Label("Conn");
        label.setOpaque(true);
        conn.add(label, new MidpointLocator(conn, 0));
        return conn;
    }

    protected void createEditPolicies() {
        this.installEditPolicy(EditPolicy.COMPONENT_ROLE, new ConnectionEditPolicy());
        this.installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
        this.installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new ConnectionBendPointEditPolicy());
    }

    @Override
  

    protected void refreshVisuals() {
    	refreshBendpoints() ;
    	
    }

    public void setSelected(int value) {
        super.setSelected(value);
        if (value != EditPart.SELECTED_NONE)  
        {
        	((PolylineConnection) getFigure()).setLineWidth(2);
        	FunctionColumnModelEditPart source=(FunctionColumnModelEditPart) this.getSource();
            source.getFigure().setBackgroundColor(ColorConstants.titleGradient);
            FunctionColumnModelEditPart target=(FunctionColumnModelEditPart) this.getTarget();
            target.getFigure().setBackgroundColor(ColorConstants.titleGradient);
        }
        else {
        	 ((PolylineConnection) getFigure()).setLineWidth(1);
        	 source=(FunctionColumnModelEditPart) this.getSource();
             target=(FunctionColumnModelEditPart) this.getTarget();
             if(source!=null&&target!=null)
             {
             target.getFigure().setBackgroundColor(ColorConstants.white);
             source.getFigure().setBackgroundColor(ColorConstants.white);
             }
        }
           
    }

	@Override
	public void propertyChange(PropertyChangeEvent event)
	{ 
		String property = event.getPropertyName();
        if(ColumnConnection.PROP_BENDPOINT.equals(property)){
             refreshBendpoints();
         }
	}

	protected void refreshBendpoints() {
		ColumnConnection conn = (ColumnConnection) getModel();
		List modelConstraint = conn.getBendpoints();
		List figureConstraint = new ArrayList();
		for (int i = 0; i < modelConstraint.size(); i++) {
			ConnectionBendpoint cbp = (ConnectionBendpoint) modelConstraint
					.get(i);
			RelativeBendpoint rbp = new RelativeBendpoint(getConnectionFigure());
			rbp.setRelativeDimensions(cbp.getFirstRelativeDimension(), cbp
					.getSecondRelativeDimension());
			rbp.setWeight((i + 1) / ((float) modelConstraint.size() + 1));
			figureConstraint.add(rbp);
		}
		getConnectionFigure().setRoutingConstraint(figureConstraint);
	}

}
