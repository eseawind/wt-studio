package com.wt.studio.plugin.querydesigner.gef.editors.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.wt.studio.plugin.querydesigner.gef.editors.policies.AbstractBlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.TitleModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.TitleModel;

public class TitleModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	private TitleModelFigure figure;

	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((TitleModel) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((TitleModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		// TODO Auto-generated method stub
		String prop = changeEvent.getPropertyName();
		if (prop.equals(TitleModel.PROP_NAME) || prop.equals(TitleModel.PROP_RECTANGLE)) {
			refreshVisuals();
		}
	}

	protected void refreshVisuals()
	{
		TitleModel node = (TitleModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((TitleModelFigure) this.getFigure()).setTitle((TitleModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	protected IFigure createFigure()
	{
		if (figure == null) {
			figure = new TitleModelFigure();
			figure.setTitle((TitleModel) this.getModel());
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
