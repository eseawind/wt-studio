package com.wt.studio.plugin.modeldesigner.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.policy.BODiagramLayoutEditPolicy;



public class BOModelDiagramEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	protected List<BONodeModel> getModelChildren()
	{
		BOModelDiagram diagram = (BOModelDiagram)getModel();
		List<BONodeModel> result = new ArrayList<BONodeModel>();
		result.addAll(diagram.getTableModels());
		result.addAll(diagram.getNoteModels());
		result.addAll(diagram.getViewModels());
		return result;
	}
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((BOModelDiagram) getModel()).addPropertyChangeListener(this);
	}
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		String propName = event.getPropertyName();
		if (BOModelDiagram.PROP_RECTANGLES.equals(propName)
				) {
			refreshChildren();
		}
	}
		
	@Override
	protected IFigure createFigure()
	{
		Figure figure = new FreeformLayer();
		figure.setLayoutManager(new FreeformLayout());
		return figure;
	}
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new BODiagramLayoutEditPolicy());
	}
	

}
