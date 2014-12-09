package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.wt.studio.plugin.pagedesigner.gef.figure.FunctionTableFigure;
import com.wt.studio.plugin.pagedesigner.gef.model.BOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.FunctionModelEditPolicy;

public class BOFunctionTableModelEditPart extends FunctionTableModelEditPart 
{

	private FunctionTableFigure figure;
	protected List<FunctionColumnModel> getModelChildren()
	{
		BOFunctionTableModel table = (BOFunctionTableModel) getModel();
		return table.getColumns();
	}
	
	protected IFigure createFigure()
	{
		if(figure==null)
			figure=new FunctionTableFigure();
		figure.setTableModel((BOFunctionTableModel)this.getModel());
		return figure;
	}
}
