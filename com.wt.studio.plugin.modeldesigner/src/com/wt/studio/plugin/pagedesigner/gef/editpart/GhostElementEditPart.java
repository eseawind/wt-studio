package com.wt.studio.plugin.pagedesigner.gef.editpart;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class GhostElementEditPart extends AbstractGraphicalEditPart
{

	@Override
	protected IFigure createFigure()
	{
		Figure figure = new FreeformLayer();
		figure.setLayoutManager(new FreeformLayout());
		return figure;
	}

	@Override
	protected void createEditPolicies()
	{
		// TODO Auto-generated method stub
		
	}

}
