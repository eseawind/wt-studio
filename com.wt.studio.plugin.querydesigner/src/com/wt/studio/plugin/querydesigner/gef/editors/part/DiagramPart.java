package com.wt.studio.plugin.querydesigner.gef.editors.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.rulers.RulerProvider;

import com.wt.studio.plugin.querydesigner.gef.editors.policies.DiagramLayoutEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.layout.DiagramFreeFormLayout;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.Element;

public class DiagramPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	@Override
	protected List<Element> getModelChildren()
	{
		Diagram diagram = (Diagram) getModel();
		List<Element> result = new ArrayList<Element>();
		result.addAll(diagram.getBlockModels());
		return result;
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((Diagram) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((Diagram) getModel()).removePropertyChangeListener(this);
	}

	// ------------------------------------------------------------------------

	@Override
	protected IFigure createFigure()
	{
		Figure figure = new FreeformLayer();
		figure.setLayoutManager(new DiagramFreeFormLayout());
		return figure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutEditPolicy());
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
	public void propertyChange(PropertyChangeEvent event)
	{
		String propName = event.getPropertyName();
		if (Diagram.PROP_BLOCKMODELS.equals(propName)
				|| Diagram.PROP_QUERYBLOCKMODELS.equals(propName)
				|| Diagram.PROP_TABLES.equals(propName)
				|| Diagram.PROP_CHARTBLOCKMODELS.equals(propName)) {
			refreshChildren();
		}
	}
}