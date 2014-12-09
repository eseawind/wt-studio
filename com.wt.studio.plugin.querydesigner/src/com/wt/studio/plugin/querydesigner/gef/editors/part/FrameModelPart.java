package com.wt.studio.plugin.querydesigner.gef.editors.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.wt.studio.plugin.querydesigner.gef.editors.policies.AbstractBlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.FrameBlockModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.FrameBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;

public class FrameModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	private FrameBlockModelFigure figure;

	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((FrameBlockModel) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((FrameBlockModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		// TODO Auto-generated method stub
		String prop = changeEvent.getPropertyName();
		if (prop.equals(QueryBlockModel.PROP_NAME) || prop.equals(QueryBlockModel.PROP_RECTANGLE)) {
			refreshVisuals();
		}
	}

	protected void refreshVisuals()
	{
		FrameBlockModel node = (FrameBlockModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((FrameBlockModelFigure) this.getFigure()).setFrame((FrameBlockModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	protected IFigure createFigure()
	{
		if (figure == null) {
			figure = new FrameBlockModelFigure();
		}
		return figure;
	}

	@Override
	protected void createEditPolicies()
	{
		// TODO Auto-generated method stub
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AbstractBlockModelEditPolicy());
	}

}
