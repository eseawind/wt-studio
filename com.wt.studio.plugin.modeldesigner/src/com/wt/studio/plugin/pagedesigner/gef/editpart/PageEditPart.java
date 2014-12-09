package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.rulers.RulerProvider;

import com.wt.studio.plugin.pagedesigner.gef.figure.PageFigure;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.HorizonBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.VerticalBlockLayoutPolicy;

public class PageEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	private PageFigure pagefigure;

	protected List<Element> getModelChildren()
	{
		ControlPageModel page = (ControlPageModel) getModel();
		return page.getAllElement();
	}
	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((ControlPageModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((ControlPageModel) getModel()).removePropertyChangeListener(this);
	}
	protected void refreshVisuals()
	{
		ControlPageModel node = (ControlPageModel) this.getModel();
		((FrameBorder) getFigure().getBorder()).setLabel(node.getName());
		Rectangle rectangle = node.getRectangle();
		((PageFigure) this.getFigure()).setPageModel((ControlPageModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}
		
	
	@Override
	protected IFigure createFigure()
	{
		if(pagefigure==null)
		{
		   pagefigure = new PageFigure();
		}
		return pagefigure;
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
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new VerticalBlockLayoutPolicy());
	}
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if ( prop.equals(ControlPageModel.PROP_RECTANGLE)||prop.equals(ControlPageModel.PROP_NAME)) {
			refreshVisuals();
		} 
		else if( prop.equals(ControlPageModel.PROP_ELEMENTS)){
			refreshChildren();	
		}
		}
	

}

