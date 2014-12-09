package com.wt.studio.plugin.querydesigner.gef.editors.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.widgets.Display;

import com.wt.studio.plugin.querydesigner.gef.descriptor.TextDialog;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.ParamModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.ParamModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamTreeModel;

public class ParamModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	private ParamModelFigure paramModelFigure;

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((ParamModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((ParamModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		ParamModel model = (ParamModel) this.getModel();
		Rectangle rectangle = model.getRectangle();
		((ParamModelFigure) this.getFigure()).setParamModel(model);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	@Override
	public void performRequest(Request request)
	{
		if (getModel() instanceof ParamTreeModel) {
			ParamTreeModel model = (ParamTreeModel) getModel();
			if (request.getType().equals(RequestConstants.REQ_OPEN)) {
				TextDialog textDialog = new TextDialog(Display.getCurrent().getActiveShell(), model);
				textDialog.open();
				this.getRoot().getViewer().getEditDomain().getCommandStack().execute(new Command() {
				});
			}
		}
	}

	@Override
	protected IFigure createFigure()
	{
		if (paramModelFigure == null) {
			paramModelFigure = new ParamModelFigure();
		}
		paramModelFigure.setWidget(((ParamModel) getModel()).getType());
		return paramModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ParamModelEditPolicy());
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		refreshVisuals();
	}
}