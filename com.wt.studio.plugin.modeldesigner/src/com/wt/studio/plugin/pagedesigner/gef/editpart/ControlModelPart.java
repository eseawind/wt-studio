package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;

import com.wt.studio.plugin.modeldesigner.dialog.TableDialog;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.pagedesigner.gef.figure.ControlModelFigure;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.ControlEditPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.TableLayoutPolicy;


public class ControlModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	private ControlModelFigure controlModelFigure;

	protected List<ColumnModel> getModelChildren()
	{
		List <ColumnModel>children=new ArrayList<ColumnModel>(); 
		if(this.getModel() instanceof TableControlModel)
		{
		 TableControlModel table= (TableControlModel) getModel();
		 children.addAll(table.getChildren());
		}
		return  children;
	}
	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((ControlModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((ControlModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		ControlModel model = (ControlModel) this.getModel();
		Rectangle rectangle = model.getRectangle();
		((ControlModelFigure) this.getFigure()).setControlModel(model);
		((ControlModelFigure) this.getFigure()).setWidget(((ControlModel) getModel()).getType());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
		
		changeColumnByControl(model);
		
	}

	private void changeColumnByControl(ControlModel model)
	{
		// TODO Auto-generated method stub
		Diagram diagram=(Diagram) (this.getViewer().getContents().getModel());
		VOFunctionTableModel func=diagram.getFunc();
		FunctionColumnModel column=func.getColumnByUuid(model.getUuid());
		if(column!=null)
		{
	    column.setId(model.getId());
	    column.setTitle(model.getName());
		}
	}

	@Override
	public void performRequest(Request request)
	{
		
		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			try {
				if(this.getModel() instanceof TableControlModel)
				{
					performRequestOpen();
				}

			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}

	private void performRequestOpen()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IFigure createFigure()
	{
		if (controlModelFigure == null) {
			controlModelFigure = new ControlModelFigure();
		}
		ControlModel model = (ControlModel) this.getModel();
		controlModelFigure.setControlModel(model);
		controlModelFigure.setWidget(((ControlModel) getModel()).getType());
		return controlModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ControlEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new TableLayoutPolicy());
	}

	public Object getAdapter(Class adapter)
	{
		if (adapter == SnapToHelper.class) {
			List snapStrategies = new ArrayList();
			Boolean val = (Boolean) getViewer()
					.getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGuides(this));
			val = (Boolean) getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGeometry(this));
			val = (Boolean) getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
			if (val != null && val.booleanValue())
				snapStrategies.add(new SnapToGrid(this));

			if (snapStrategies.size() == 0)
				return null;
			if (snapStrategies.size() == 1)
				return snapStrategies.get(0);
			SnapToHelper ss[] = new SnapToHelper[snapStrategies.size()];
			for (int i = 0; i < snapStrategies.size(); i++)
				ss[i] = (SnapToHelper) snapStrategies.get(i);
			return new CompoundSnapToHelper(ss);
		}
		return super.getAdapter(adapter);
	}
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String name=changeEvent.getPropertyName();
		if(name.equals(TableControlModel.PROP_CHILDREN))
			refreshChildren();
		refreshVisuals();
	}
}