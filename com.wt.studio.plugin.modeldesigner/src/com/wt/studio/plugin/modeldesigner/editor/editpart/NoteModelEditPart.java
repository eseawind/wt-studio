package com.wt.studio.plugin.modeldesigner.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.wt.studio.plugin.modeldesigner.editor.figure.NoteModelFigure;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;
import com.wt.studio.plugin.modeldesigner.editor.policy.MyNodeEditPolicy;
import com.wt.studio.plugin.modeldesigner.editor.policy.NoteDirectEditPolicy;
import com.wt.studio.plugin.modeldesigner.utils.NoteCellEditor;
import com.wt.studio.plugin.modeldesigner.utils.NoteEditManager;
import com.wt.studio.plugin.modeldesigner.utils.NoteEditorLocator;

public class NoteModelEditPart extends BONodeEditPart implements PropertyChangeListener, DeleteableEditPart
{

	private NoteModelFigure noteFigure;
	private NoteEditManager editManager = null;
	
	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((NoteModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((NoteModel) getModel()).removePropertyChangeListener(this);
	}
	

	@Override
	protected void refreshVisuals()
	{
		NoteModel node = (NoteModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((NoteModelFigure) this.getFigure()).setText(node.getText());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if(prop.equals(NoteModel.PROP_RECTANGLE)||prop.equals(NoteModel.PROP_NAME))
			refreshVisuals();
	    super.propertyChange(changeEvent);
	}
	
	public void performRequest(Request request) {
		if (request.getType().equals(RequestConstants.REQ_DIRECT_EDIT)
				|| request.getType().equals(RequestConstants.REQ_OPEN)) {
			performDirectEdit();
		}
	}

	private void performDirectEdit() {
		if (this.editManager == null) {
			this.editManager = new NoteEditManager(this, NoteCellEditor.class,
					new NoteEditorLocator(getFigure()));
		}

		this.editManager.show();
	}
	@Override
	protected IFigure createFigure()
	{
		if (noteFigure == null) {
			noteFigure = new NoteModelFigure();
		}
		return noteFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,new NoteDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new MyNodeEditPolicy());
		super.createEditPolicies();
	}

	@Override
	public boolean isDeleteable()
	{
		// TODO Auto-generated method stub
		return true;
	}

	
}
