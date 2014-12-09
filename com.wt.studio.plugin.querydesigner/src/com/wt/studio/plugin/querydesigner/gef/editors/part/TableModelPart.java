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
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.wt.studio.plugin.querydesigner.gef.editors.policies.TableModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.editors.policies.TableModelLayoutEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.TableModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;
import com.wt.studio.plugin.querydesigner.model.TableSqlAreaModel;
import com.wt.studio.plugin.querydesigner.wizard.CreateTableSqlWizard;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateTableSqlPageOne;

public class TableModelPart extends AbstractGraphicalEditPart implements PropertyChangeListener
{
	private TableModelFigure tableModelFigure;

	@Override
	protected List<Element> getModelChildren()
	{
		return ((TableModel) getModel()).getElements();
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((TableModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((TableModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		TableModel tableModel = (TableModel) this.getModel();
		Rectangle rectangle = tableModel.getRectangle();
		((TableModelFigure) this.getFigure()).setTableModel(tableModel);
		((TableModelFigure) this.getFigure()).getBorder().setLabel(tableModel.getName());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	@Override
	public void performRequest(Request request)
	{
		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			try {
				TableSqlAreaModel sqlArea = new TableSqlAreaModel();
				CreateTableSqlWizard wizard = new CreateTableSqlWizard(sqlArea);
				final WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(),
						wizard);
				if (((TableModel) this.getModel()).getSqlAreaModel() != null) {

					final IPageChangedListener pageChangedListener = new IPageChangedListener() {
						@Override
						public void pageChanged(PageChangedEvent event)
						{
							if (event.getSelectedPage() instanceof CreateTableSqlPageOne) {
								((CreateTableSqlPageOne) dialog.getCurrentPage())
										.setData(((TableModel) getModel()).getSqlAreaModel());
								dialog.removePageChangedListener(this);
							}
						}
					};
					dialog.addPageChangedListener(pageChangedListener);
				}
				dialog.open();
				if (sqlArea.getSqlName() != null && sqlArea.getSqlArea() != null
						&& sqlArea.getCms() != null) {
					((TableModel) getModel()).setSqlAreaModel(sqlArea);
					((TableModel) getModel()).setSql(sqlArea.getSqlArea());
					((TableModel) getModel()).setSqlName(sqlArea.getSqlName());
					((TableModel) getModel()).removeAllColumn();
					((TableModel) getModel()).addAllColumn(sqlArea.getCms());
					((TableModel) getModel()).setBlockName(sqlArea.getSqlName());
					this.getRoot().getViewer().getEditDomain().getCommandStack()
							.execute(new Command() {
							});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected IFigure createFigure()
	{
		if (tableModelFigure == null) {
			tableModelFigure = new TableModelFigure();
		}
		return tableModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new TableModelEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new TableModelLayoutEditPolicy());
	}

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if (prop.equals(TableModel.PROP_NAME) || Element.PROP_RECTANGLE.equals(prop)) {
			refreshVisuals();
		} else if (prop.equals(TableModel.PROP_COLUMNS)
				|| prop.equals(TableModel.PROP_COLUMNGROUPS)
				|| prop.equals(TableModel.PROP_BLOCKMODELS)) {
			refreshChildren();
		} else if (prop.equals(TableModel.PROP_SQL)) {
			refreshVisuals();
		}
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

	public IFigure getContentPane()
	{
		TableModelFigure figure = (TableModelFigure) super.getContentPane();
		return figure.getContent();
	}

	@Override
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
							editPart.getFigure().getChildren().clear();
							editPart.getFigure().setSize(0, 0);
							editPart.getFigure().setLocation(new Point(0, 0));
						} else {
							editPart.getFigure().setVisible(true);
						}
					}
				}
			}
		}
	}
}