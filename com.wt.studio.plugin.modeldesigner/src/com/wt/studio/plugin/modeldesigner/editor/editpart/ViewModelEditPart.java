package com.wt.studio.plugin.modeldesigner.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;

import com.wt.studio.plugin.modeldesigner.dialog.TableDialog;
import com.wt.studio.plugin.modeldesigner.editor.figure.ViewModelFigure;
import com.wt.studio.plugin.modeldesigner.editor.model.ViewModel;
import com.wt.studio.plugin.modeldesigner.editor.policy.MyNodeEditPolicy;
import com.wt.studio.plugin.modeldesigner.editor.policy.ViewModelEditPolicy;

public class ViewModelEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	private ViewModelFigure rectangleModelFigure;

	//@Override
//	protected List<MyNodeModel> getModelChildren()
	//{
		ViewModel block = (ViewModel) getModel();
		//List<MyNodeModel> result = new ArrayList<MyNodeModel>();
		//return result;
	//}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((ViewModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((ViewModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		ViewModel node = (ViewModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((ViewModelFigure) this.getFigure()).setBlockModel((ViewModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	
	public void performRequest(Request request)
	{
		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			try {
				performRequestOpen();

			} catch (Exception e) {
				
			}
		}
	}

	public void performRequestOpen() {
		//TableModel table = (TableModel) this.getModel();
		//BOModelDiagram diagram = (BOModelDiagram)this.getParent().getModel();
		//TableModel copyTable = table.copyData();

		

		
	}
	protected IFigure createFigure()
	{
		if (rectangleModelFigure == null) {
			rectangleModelFigure = new ViewModelFigure();
		}
		return rectangleModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ViewModelEditPolicy());
	}
	public boolean isDeleteable() {
		return true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if (prop.equals(ViewModel.PROP_NAME) || prop.equals(ViewModel.PROP_RECTANGLE)
				|| prop.equals(ViewModel.PROP_TYPE)) {
			refreshVisuals();
		} else if (prop.equals(ViewModel.PROP_COLUMNS)) {
			refreshChildren();
		}
	}
}
