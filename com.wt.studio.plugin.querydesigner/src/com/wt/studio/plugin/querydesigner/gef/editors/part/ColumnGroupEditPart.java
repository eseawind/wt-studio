package com.wt.studio.plugin.querydesigner.gef.editors.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
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

import com.wt.studio.plugin.querydesigner.gef.editors.policies.ColumnGroupEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.ColumnGroupLayoutEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.ColumnGroupFigure;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.gef.model.Element;

public class ColumnGroupEditPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener
{
	private ColumnGroupFigure columnGroupFigure;

	protected List<Element> getModelChildren()
	{
		return ((ColumnGroupModel) this.getModel()).getElements();
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((ColumnGroupModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((ColumnGroupModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if (prop.equals(ColumnGroupModel.PROP_TITLE) || Element.PROP_RECTANGLE.equals(prop)) {
			refreshVisuals();
		} else if (prop.equals(ColumnGroupModel.PROP_COLUMNS)
				|| prop.equals(ColumnGroupModel.PROP_COLUMNGROUPS)
				|| prop.equals(ColumnGroupModel.PROP_BLOCKMODELS)) {
			refreshChildren();
		}

	}

	@Override
	protected void refreshVisuals()
	{
		ColumnGroupModel group = (ColumnGroupModel) this.getModel();
		Rectangle rectangle = group.getRectangle();
		((ColumnGroupFigure) this.getFigure()).getBorder().setLabel(group.getTitle());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	@Override
	protected IFigure createFigure()
	{
		if (columnGroupFigure == null)
			columnGroupFigure = new ColumnGroupFigure();
		return columnGroupFigure;
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

	protected void refreshChildren()
	{
		super.refreshChildren();
		GraphicalEditPart editPart;
		Object model;
		List children = getChildren();
		int size = children.size();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				editPart = (GraphicalEditPart) children.get(i);
				model = editPart.getModel();
				if (model instanceof ColumnModel2) {
					if (((ColumnModel2) model).getIfshow() != null) {
						if (!((ColumnModel2) model).getIfshow().equals("Y")) {
							editPart.getFigure().setVisible(false);
							editPart.getFigure().setSize(0, 0);
							editPart.getFigure().setLocation(new Point(0, 0));
						} else {
							editPart.getFigure().setVisible(true);
						}
					}
				} else {
					editPart.getFigure().setVisible(true);
				}
			}
		}
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ColumnGroupLayoutEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ColumnGroupEditPolicy());
	}
}
