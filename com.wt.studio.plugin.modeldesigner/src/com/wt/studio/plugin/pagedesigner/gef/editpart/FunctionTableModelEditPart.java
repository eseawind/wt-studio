package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.ReconnectRequest;



import com.wt.studio.plugin.pagedesigner.gef.Anchor.BorderAnchor;
import com.wt.studio.plugin.pagedesigner.gef.Anchor.RectangleBorderAnchor;
import com.wt.studio.plugin.pagedesigner.gef.figure.FunctionTableFigure;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.ColumnGraphicalNodeEditPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.FunctionModelEditPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.TableotoGraphicalNodeEditPolicy;

public class FunctionTableModelEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((FunctionModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((FunctionModel) getModel()).removePropertyChangeListener(this);
	}

	protected List<FunctionColumnModel> getModelChildren()
	{
		FunctionModel table = (FunctionModel) getModel();
		return table.getColumns();
	}
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if ( prop.equals(FunctionModel.PROP_RECTANGLE)||prop.equals(MOFunctionTableModel.PROP_DBURL)
				||prop.equals(MOFunctionTableModel.PROP_NAME)) {
			refreshVisuals();
		} else if(prop.equals(FunctionModel.PROP_COLUMN)){
			refreshChildren();
		}
	}
	protected void refreshVisuals()
	{
		FunctionModel node = (FunctionModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((FunctionTableFigure) this.getFigure()).setTableModel((FunctionModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
		refreshChildren();
	}
	protected void createEditPolicies()
	{
		// TODO Auto-generated method stub
		installEditPolicy("EditPolicy", new FunctionModelEditPolicy());
	}

	@Override
	protected IFigure createFigure()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
