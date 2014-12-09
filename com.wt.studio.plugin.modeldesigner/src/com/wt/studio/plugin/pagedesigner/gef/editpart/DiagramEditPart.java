package com.wt.studio.plugin.pagedesigner.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
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
import com.wt.studio.plugin.pagedesigner.gef.layout.DiagramFreeFormLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.DiagramLayoutEditPolicy;

public class DiagramEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	protected List<Element> getModelChildren()
	{
		
		Diagram diagram = (Diagram)getModel();
		if(diagram.getType()==0)
		{
		List<Element> result = new ArrayList<Element>();
		result.addAll(diagram.getPageModels());
		return  result;
		}
		else {
			List<Element> result = new ArrayList<Element>();
			result.add(diagram.getFunc());
			result.addAll(diagram.getFunctionTableModels());
			return result;
		}
	}
	public void activate()
	{
		if (isActive()) {
			((Diagram) getModel()).addPropertyChangeListener(this);
		}
		super.activate();
		((Diagram) getModel()).addPropertyChangeListener(this);
	}
	@Override
	public void propertyChange(PropertyChangeEvent event)
	{
		String propName = event.getPropertyName();
		if (Diagram.PROP_PAGES.equals(propName)
				) {
			refreshChildren();
		}
		refreshVisuals();
	}
	protected void refreshVisuals()
	{
		Diagram node = (Diagram) this.getModel();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), new Rectangle(0,0,-1,-1));
	}
	@Override
	protected IFigure createFigure()
	{
		Figure figure = new FreeformLayer();
		figure.setLayoutManager(new DiagramFreeFormLayout());
		return figure;
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
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutEditPolicy());
	}
	

}