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

import com.wt.studio.plugin.querydesigner.gef.descriptor.HtmlDialog;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.AbstractBlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.HtmlAreaModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.HtmlAreaModel;

public class HtmlAreaModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	private HtmlAreaModelFigure figure;

	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((HtmlAreaModel) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((HtmlAreaModel) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals()
	{
		HtmlAreaModel node = (HtmlAreaModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((HtmlAreaModelFigure) this.getFigure()).setHtml((HtmlAreaModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		// TODO Auto-generated method stub
		String prop = evt.getPropertyName();
		if (prop.equals(HtmlAreaModel.PROP_NAME) || prop.equals(HtmlAreaModel.PROP_RECTANGLE)) {
			refreshVisuals();
		}
	}

	@Override
	public void performRequest(Request request)
	{
		HtmlAreaModel model = (HtmlAreaModel) getModel();
		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			HtmlDialog htmlDialog = new HtmlDialog(Display.getCurrent().getActiveShell(), model);
			htmlDialog.open();
			this.getRoot().getViewer().getEditDomain().getCommandStack().execute(new Command() {
			});
		}

	}

	@Override
	protected IFigure createFigure()
	{
		// TODO Auto-generated method stub
		if (figure == null) {
			figure = new HtmlAreaModelFigure();
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
