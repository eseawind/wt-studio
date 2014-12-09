package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;


import com.wt.studio.plugin.pagedesigner.gef.figure.ColumnModelFigure;
import com.wt.studio.plugin.pagedesigner.gef.figure.XYBlockModelFigure;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.XYBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.ColumnModelEditPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.ControlEditPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.TableLayoutPolicy;

public class ColumnModelEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	private ColumnModelFigure figure;
	protected List<ColumnModel> getModelChildren()
	{
		ColumnModel column= (ColumnModel) getModel();
		return column.getChildren();
	}
	
	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((ColumnModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((ColumnModel) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals()
	{
		ColumnModel column = (ColumnModel) this.getModel();
		Rectangle rectangle = column.getRectangle();
		((ColumnModelFigure) this.getFigure()).setColumn((ColumnModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		// TODO Auto-generated method stub
		String prop = evt.getPropertyName();
		if(prop.equals(ColumnModel.PROP_TITLE)||prop.equals(ColumnModel.PROP_RECTANGLE))
			refreshVisuals();
		else if(prop.equals(ColumnModel.PROP_CHILDREN))
			refreshChildren();
				
	}

	@Override
	protected IFigure createFigure()
	{
		if(this.figure==null)
			figure=new ColumnModelFigure();
		return this.figure;
	}

	@Override
	protected void createEditPolicies()
	{
		// TODO Auto-generated method stub
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new TableLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ColumnModelEditPolicy());
	}

}
