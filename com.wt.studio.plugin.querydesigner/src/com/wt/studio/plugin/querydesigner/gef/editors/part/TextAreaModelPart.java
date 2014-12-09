package com.wt.studio.plugin.querydesigner.gef.editors.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.wt.studio.plugin.querydesigner.gef.editors.policies.AbstractBlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.TextAreaDirectEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.TextAreaModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.TextAreaModel;
import com.wt.studio.plugin.querydesigner.gef.utils.NoteEditorLocator;
import com.wt.studio.plugin.querydesigner.gef.utils.TextAreaEditManager;
import com.wt.studio.plugin.querydesigner.utils.NoteCellEditor;

public class TextAreaModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	private TextAreaModelFigure figure;
	private TextAreaEditManager editManager = null;

	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((TextAreaModel) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((TextAreaModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		// TODO Auto-generated method stub
		String prop = changeEvent.getPropertyName();
		if (prop.equals(TextAreaModel.PROP_NAME) || prop.equals(TextAreaModel.PROP_RECTANGLE)) {
			refreshVisuals();
		}
	}

	public void performRequest(Request request)
	{
		if (request.getType().equals(RequestConstants.REQ_DIRECT_EDIT)
				|| request.getType().equals(RequestConstants.REQ_OPEN)) {
			performDirectEdit();
		}
	}

	private void performDirectEdit()
	{
		if (this.editManager == null) {
			this.editManager = new TextAreaEditManager(this, NoteCellEditor.class,
					new NoteEditorLocator(getFigure()));
		}

		this.editManager.show();
	}

	protected void refreshVisuals()
	{
		TextAreaModel node = (TextAreaModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((TextAreaModelFigure) this.getFigure()).setTitle((TextAreaModel) this.getModel());
		((TextAreaModelFigure) this.getFigure()).setText(node.getTextArea());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	protected IFigure createFigure()
	{
		if (figure == null) {
			figure = new TextAreaModelFigure();
			// figure.setTitle((TitleModel) this.getModel());
		}
		return figure;
	}

	@Override
	protected void createEditPolicies()
	{
		// TODO Auto-generated method stub
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AbstractBlockModelEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new TextAreaDirectEditPolicy());
	}

}