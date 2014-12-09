package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.SWT;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotoConnection;
import com.wt.studio.plugin.pagedesigner.gef.policy.ConnectionBendPointEditPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.ConnectionEditPolicy;

public class FunctionTableConnectionEditPart   extends AbstractConnectionEditPart implements PropertyChangeListener {
	
		
	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((TableConnection) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((TableConnection) getModel()).removePropertyChangeListener(this);
	}
    protected IFigure createFigure() {
    	if(this.getModel() instanceof TableotoConnection)
        {
    	PolylineConnection conn = new PolylineConnection();
        conn.setLineStyle(SWT.LINE_DASHDOT);
        conn.setLineWidth(1);
        conn.setBackgroundColor(ColorConstants.titleGradient);
        conn.setTargetDecoration(new PolygonDecoration());
        conn.setConnectionRouter(new BendpointConnectionRouter());
        final Label label = new Label("1:1");
        label.setOpaque(true);
        conn.add(label, new MidpointLocator(conn, 0));
        return conn;
        }
    	else
    	{
    		PolylineConnection conn = new PolylineConnection();
            conn.setLineStyle(SWT.LINE_DASHDOT);
            conn.setLineWidth(1);
            conn.setBackgroundColor(ColorConstants.titleGradient);
            conn.setTargetDecoration(new PolygonDecoration());
            conn.setConnectionRouter(new BendpointConnectionRouter());
            final Label label = new Label("1:n");
            label.setOpaque(true);
            conn.add(label, new MidpointLocator(conn, 0));
            return conn;
    	}
    }

    protected void createEditPolicies() {
        this.installEditPolicy(EditPolicy.COMPONENT_ROLE, new ConnectionEditPolicy());
        this.installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
        //this.installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new ConnectionBendPointEditPolicy());
    }

    @Override
  

    protected void refreshVisuals() {
    	
    	
    }

    public void setSelected(int value) {
        super.setSelected(value);
        if (value != EditPart.SELECTED_NONE)  
        {
        	((PolylineConnection) getFigure()).setLineWidth(2);
        }
        else {
        	 ((PolylineConnection) getFigure()).setLineWidth(1);
        }
           
    }

	@Override
	public void propertyChange(PropertyChangeEvent event)
	{ 
		String property = event.getPropertyName();
        if(ColumnConnection.PROP_BENDPOINT.equals(property)){
            
         }
	}

}

