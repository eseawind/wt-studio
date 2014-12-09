package com.wt.studio.plugin.querydesigner.gef.editors.part;

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
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.rulers.RulerProvider;

import com.wt.studio.plugin.querydesigner.gef.editors.policies.QueryBlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.QueryBlockModelLayoutEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.QueryBlockModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;

public class QueryBlockModelPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener
{
	private QueryBlockModelFigure figure;

	@Override
	protected List<Element> getModelChildren()
	{
		List<Element> children = new ArrayList<Element>();
		QueryBlockModel block = (QueryBlockModel) getModel();
		// children.addAll(block.getParams());
		children.addAll(block.getElements());
		return children;
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((QueryBlockModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((QueryBlockModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		QueryBlockModel node = (QueryBlockModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((QueryBlockModelFigure) this.getFigure()).setBlockModel((QueryBlockModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	@Override
	public void performRequest(Request request)
	{
		super.performRequest(request);
	}

	@Override
	protected IFigure createFigure()
	{
		if (figure == null) {
			figure = new QueryBlockModelFigure();
		}
		return figure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new QueryBlockModelEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new QueryBlockModelLayoutEditPolicy());
	}

	@Override
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
		String prop = changeEvent.getPropertyName();
		if (prop.equals(QueryBlockModel.PROP_NAME) || prop.equals(QueryBlockModel.PROP_RECTANGLE)) {
			refreshVisuals();
		} else if (prop.equals(QueryBlockModel.PROP_PARAMS)
				|| prop.equals(AbstractBlockModel.PROP_BLOCKMODELS)) {
			refreshChildren();
		}
	}
}