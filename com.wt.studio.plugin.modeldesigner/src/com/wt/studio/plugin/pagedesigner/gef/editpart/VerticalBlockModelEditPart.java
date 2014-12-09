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
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.rulers.RulerProvider;

import com.wt.studio.plugin.pagedesigner.gef.figure.VerticalBlockModelFigure;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.policy.BlockEditPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.HorizontalBlocklayoutPolicy;
import com.wt.studio.plugin.pagedesigner.gef.policy.VerticalBlockLayoutPolicy;

public class VerticalBlockModelEditPart  extends AbstractGraphicalEditPart implements PropertyChangeListener
{

	private VerticalBlockModelFigure blockmodelfigure;
	protected List<Element> getModelChildren()
	{
		VerticalBlockModel vertical = (VerticalBlockModel) getModel();
		return vertical.getAllElement();
	}
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((VerticalBlockModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((VerticalBlockModel) getModel()).removePropertyChangeListener(this);
	}
	protected void refreshVisuals()
	{
		VerticalBlockModel node = (VerticalBlockModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((VerticalBlockModelFigure) this.getFigure()).setModel((VerticalBlockModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}
	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if ( prop.equals(VerticalBlockModel.PROP_RECTANGLE)) {
			refreshVisuals();
		}
		else if( prop.equals(VerticalBlockModel.PROP_ELEMENTS)){
			refreshChildren();
		}
		
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
	protected IFigure createFigure()
	{
		if(blockmodelfigure==null)
		{
		   blockmodelfigure = new VerticalBlockModelFigure();
		}
		return blockmodelfigure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new VerticalBlockLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new BlockEditPolicy());
	}

}