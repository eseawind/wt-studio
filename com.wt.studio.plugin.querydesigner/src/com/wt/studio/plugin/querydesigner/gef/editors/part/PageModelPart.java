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
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.tools.DirectEditManager;

import com.wt.studio.plugin.querydesigner.gef.editors.policies.BlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.PageLayoutEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.PageModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.PageModel;
import com.wt.studio.plugin.querydesigner.gef.model.VerticalBlockModel;

public class PageModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	protected DirectEditManager manager;
	private PageModelFigure blockModelFigure;

	@Override
	public List<Element> getModelChildren()
	{
		return ((PageModel) this.getModel()).getElements();
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((PageModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((PageModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		PageModel node = (PageModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	@Override
	protected IFigure createFigure()
	{
		if (blockModelFigure == null) {
			blockModelFigure = new PageModelFigure();
		}
		return blockModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new BlockModelEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PageLayoutEditPolicy());
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
		if (prop.equals(VerticalBlockModel.PROP_NAME)
				|| prop.equals(VerticalBlockModel.PROP_RECTANGLE)
				|| prop.equals(VerticalBlockModel.PROP_TYPE)) {
			refreshVisuals();
		} else if (VerticalBlockModel.PROP_BLOCKMODELS.equals(prop)
				|| VerticalBlockModel.PROP_QUERYBLOCKMODELS.equals(prop)
				|| VerticalBlockModel.PROP_TABLES.equals(prop)
				|| VerticalBlockModel.PROP_CHARTBLOCKMODELS.equals(prop)) {
			refreshChildren();
		}
	}
}