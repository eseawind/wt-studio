package com.wt.studio.plugin.querydesigner.gef.editors.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.wt.studio.plugin.querydesigner.gef.figures.ColumnModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;

public class ColumnModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	private ColumnModelFigure columnModelFigure;

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((ColumnModel2) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((ColumnModel2) getModel()).removePropertyChangeListener(this);
	}

	@Override
	public Object getAdapter(Class key)
	{
		return super.getAdapter(key);
	}

	@Override
	protected void refreshVisuals()
	{
		ColumnModel2 model = (ColumnModel2) this.getModel();
		((ColumnModelFigure) this.getFigure()).setColumnModel(model);
	}

	@Override
	protected IFigure createFigure()
	{
		if (columnModelFigure == null) {
			columnModelFigure = new ColumnModelFigure();
		}
		return columnModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		// 禁止删除
		// installEditPolicy(EditPolicy.COMPONENT_ROLE, new ColumnModelEditPolicy());
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		refreshVisuals();
		((GraphicalEditPart) getParent()).refresh();
	}
}