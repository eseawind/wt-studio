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
import org.eclipse.gef.tools.DirectEditManager;

import com.wt.studio.plugin.querydesigner.gef.editors.policies.BlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.BlockModelLayoutEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.HorizontalBlockModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.HorizontalBlockModel;

public class HorizontalBlockModelPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener
{
	protected DirectEditManager manager;
	private HorizontalBlockModelFigure blockModelFigure;

	@Override
	public List<Element> getModelChildren()
	{

		return ((HorizontalBlockModel) this.getModel()).getElements();
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((BlockModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((BlockModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		HorizontalBlockModel node = (HorizontalBlockModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	@Override
	public void performRequest(Request request)
	{
	}

	@Override
	protected IFigure createFigure()
	{
		if (blockModelFigure == null) {
			blockModelFigure = new HorizontalBlockModelFigure();
		}
		return blockModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new BlockModelEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new BlockModelLayoutEditPolicy());
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
		if (prop.equals(BlockModel.PROP_NAME) || prop.equals(BlockModel.PROP_RECTANGLE)
				|| prop.equals(BlockModel.PROP_TYPE)) {
			refreshVisuals();
		} else if (BlockModel.PROP_BLOCKMODELS.equals(prop)
				|| BlockModel.PROP_QUERYBLOCKMODELS.equals(prop)
				|| BlockModel.PROP_TABLES.equals(prop)
				|| BlockModel.PROP_CHARTBLOCKMODELS.equals(prop)) {
			refreshChildren();
		}
	}
}
