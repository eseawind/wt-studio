package com.wt.studio.plugin.querydesigner.gef.editors.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.wt.studio.plugin.querydesigner.gef.figures.GhostModelFigure;

public class GhostModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	private GhostModelFigure ghostModelFigure;

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected IFigure createFigure()
	{
		if (ghostModelFigure == null) {
			ghostModelFigure = new GhostModelFigure();
		}
		return ghostModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		// TODO Auto-generated method stub

	}

	protected void refreshVisuals()
	{

	}

}
