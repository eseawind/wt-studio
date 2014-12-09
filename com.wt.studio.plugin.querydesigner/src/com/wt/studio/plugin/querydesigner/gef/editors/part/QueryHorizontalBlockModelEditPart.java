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

import com.wt.studio.plugin.querydesigner.gef.editors.policies.QueryHorizontalBlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.QueryHorizontalBlockModelLayoutEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.QueryHorizontalBlockModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;

public class QueryHorizontalBlockModelEditPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener
{
	private QueryHorizontalBlockModelFigure figure;

	@Override
	protected List<Element> getModelChildren()
	{
		List<Element> children = new ArrayList<Element>();
		QueryHorizontalBlockModel block = (QueryHorizontalBlockModel) getModel();
		return block.getElements();
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((QueryHorizontalBlockModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((QueryHorizontalBlockModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		QueryHorizontalBlockModel node = (QueryHorizontalBlockModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
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
			figure = new QueryHorizontalBlockModelFigure();
		}
		return figure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new QueryHorizontalBlockModelEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new QueryHorizontalBlockModelLayoutEditPolicy());
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
		if (prop.equals(QueryHorizontalBlockModel.PROP_NAME)
				|| prop.equals(QueryHorizontalBlockModel.PROP_RECTANGLE)) {
			refreshVisuals();
		} else if (prop.equals(QueryHorizontalBlockModel.PROP_PARAMS)
				|| prop.equals(AbstractBlockModel.PROP_BLOCKMODELS)) {
			refreshChildren();
		}
	}
}