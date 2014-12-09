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

import com.wt.studio.plugin.querydesigner.gef.editors.policies.BlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.IXYLayoutEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.XYBlockModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.XYBlockModel;

public class XYBlockModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	private XYBlockModelFigure XYblockModelFigure;

	@Override
	public List<Element> getModelChildren()
	{
		return ((XYBlockModel) this.getModel()).getElements();
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((XYBlockModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((XYBlockModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		XYBlockModel node = (XYBlockModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	@Override
	protected IFigure createFigure()
	{
		if (XYblockModelFigure == null) {
			XYblockModelFigure = new XYBlockModelFigure();
		}
		return XYblockModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new BlockModelEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new IXYLayoutEditPolicy());
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
		if (prop.equals(XYBlockModel.PROP_NAME) || prop.equals(XYBlockModel.PROP_RECTANGLE)
				|| prop.equals(XYBlockModel.PROP_TYPE)) {
			refreshVisuals();
		} else if (XYBlockModel.PROP_BLOCKMODELS.equals(prop)
				|| XYBlockModel.PROP_QUERYBLOCKMODELS.equals(prop)
				|| XYBlockModel.PROP_TABLES.equals(prop)
				|| XYBlockModel.PROP_CHARTBLOCKMODELS.equals(prop)) {
			refreshChildren();
		}
	}
}