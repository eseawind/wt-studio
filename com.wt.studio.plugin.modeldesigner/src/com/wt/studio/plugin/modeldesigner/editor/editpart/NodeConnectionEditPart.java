package com.wt.studio.plugin.modeldesigner.editor.editpart;

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
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;

import com.wt.studio.plugin.modeldesigner.dialog.ConnectionDialog;
import com.wt.studio.plugin.modeldesigner.dialog.TableDialog;
import com.wt.studio.plugin.modeldesigner.editor.Anchor.BorderAnchor;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.ConnectionBendpoint;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
import com.wt.studio.plugin.modeldesigner.editor.policy.ConnectionBendPointEditPolicy;
import com.wt.studio.plugin.modeldesigner.editor.policy.ConnectionEditPolicy;

public class NodeConnectionEditPart  extends AbstractConnectionEditPart implements PropertyChangeListener, DeleteableEditPart {
	
	private BorderAnchor sourceAnchor;
	private BorderAnchor targetAnchor;

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
		((NodeConnection) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((NodeConnection) getModel()).removePropertyChangeListener(this);
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
    public void performRequest(Request request)
	{
		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			try {
				NodeConnection conn=(NodeConnection)this.getModel();
				ConnectionDialog dialog = new ConnectionDialog(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(),conn);

				if (dialog.open() == IDialogConstants.OK_ID) {
			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.performRequest(request);
	}

    protected void refreshVisuals() {
    	refreshBendpoints() ;
    }

    public void setSelected(int value) {
        super.setSelected(value);
        if (value != EditPart.SELECTED_NONE)
        {
            ((PolylineConnection) getFigure()).setLineWidth(2);
            TableModelEditPart sourceTable=(TableModelEditPart) this.getSource();
            List<AbstractEditPart> scolumnEditpart=sourceTable.getChildren();
            TableModelEditPart targetTable=(TableModelEditPart) this.getTarget();
            List<AbstractEditPart> tcolumnEditpart=targetTable.getChildren();
            NodeConnection conn=(NodeConnection)this.getModel();
            for(String id:conn.getSourceColumnModels())
            {
            	for(AbstractEditPart column:scolumnEditpart)
            	{
            		HdbColumnModel columnModel=(HdbColumnModel)column.getModel();
            		if(columnModel.getId().equals(id))
            			((ColumnEditPart)column).getFigure().setBackgroundColor(ColorConstants.titleInactiveGradient);
            	}
            }
            for(String id:conn.getTargetColumnModels())
            {
            	for(AbstractEditPart column:tcolumnEditpart)
            	{
            		HdbColumnModel columnModel=(HdbColumnModel)column.getModel();
            		if(columnModel.getId().equals(id))
            			((ColumnEditPart)column).getFigure().setBackgroundColor(ColorConstants.titleInactiveGradient);
            	}
            }
        }
        else
        {
            ((PolylineConnection) getFigure()).setLineWidth(1);
            TableModelEditPart sourceTable=(TableModelEditPart) this.getSource();
            TableModelEditPart targetTable=(TableModelEditPart) this.getTarget();
            if(sourceTable!=null&&targetTable!=null)
            {
            List<AbstractEditPart> scolumnEditpart=sourceTable.getChildren();
            List<AbstractEditPart> tcolumnEditpart=targetTable.getChildren();
            NodeConnection conn=(NodeConnection)this.getModel();
            for(String id:conn.getSourceColumnModels())
            {
            	for(AbstractEditPart column:scolumnEditpart)
            	{
            		HdbColumnModel columnModel=(HdbColumnModel)column.getModel();
            		if(columnModel.getId().equals(id))
            			((ColumnEditPart)column).getFigure().setBackgroundColor(ColorConstants.white);
            	}
            }
            for(String id:conn.getTargetColumnModels())
            {
            	for(AbstractEditPart column:tcolumnEditpart)
            	{
            		HdbColumnModel columnModel=(HdbColumnModel)column.getModel();
            		if(columnModel.getId().equals(id))
            			((ColumnEditPart)column).getFigure().setBackgroundColor(ColorConstants.white);
            	}
            }
        }
        }
    }

	@Override
	public void propertyChange(PropertyChangeEvent event)
	{ 
		String property = event.getPropertyName();
        if(NodeConnection.PROP_BENDPOINT.equals(property)){
             refreshBendpoints();
         }
	}

	protected void refreshBendpoints() {
		NodeConnection conn = (NodeConnection) getModel();
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
	@Override
	public boolean isDeleteable()
	{
		// TODO Auto-generated method stub
		return true;
	}
}