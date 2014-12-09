package com.wt.studio.plugin.modeldesigner.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.wt.studio.plugin.modeldesigner.editor.figure.ColumnModelFigure;
import com.wt.studio.plugin.modeldesigner.editor.figure.TableModelFigure;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;


public class ColumnEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	private ColumnModelFigure  columnModelFigure;
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((HdbColumnModel)getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((HdbColumnModel)getModel()).removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IFigure createFigure()
	{
		if(columnModelFigure==null)
		{
			columnModelFigure=new ColumnModelFigure();
		}
		columnModelFigure.setColumn((HdbColumnModel)this.getModel());
		return columnModelFigure;
	}

	public void setSelected(int value) {
	
		   super.setSelected(value);
	        if (value != EditPart.SELECTED_NONE)
	        {
	            ((ColumnModelFigure) getFigure()).setBackgroundColor(ColorConstants.titleGradient);
	            ((HdbColumnModel)this.getModel()).setSelected(true);
	        }
	        else
	        {
	            ((ColumnModelFigure) getFigure()).setBackgroundColor(ColorConstants.white);
	            ((HdbColumnModel)this.getModel()).setSelected(false);
	        }
	
	}
	@Override
	protected void createEditPolicies()
	{
		// TODO Auto-generated method stub
		
	}
	
	protected void refreshVisuals()
	{
		HdbColumnModel node = (HdbColumnModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((ColumnModelFigure) this.getFigure()).setColumn((HdbColumnModel) this.getModel());
		//((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
			
	}


	public void refreshTableColumns()
	{
		// TODO Auto-generated method stub
		TableModelEditPart parent = (TableModelEditPart) this.getParent();
		parent.getContentPane().add(columnModelFigure);
	}

}
