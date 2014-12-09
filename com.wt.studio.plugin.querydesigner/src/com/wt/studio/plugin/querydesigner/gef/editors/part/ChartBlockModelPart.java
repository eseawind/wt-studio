package com.wt.studio.plugin.querydesigner.gef.editors.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.wt.studio.plugin.querydesigner.gef.editors.policies.ChartBlockModelEditPolicy;
import com.wt.studio.plugin.querydesigner.gef.figures.ChartBlockModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.model.SqlSet;
import com.wt.studio.plugin.querydesigner.wizard.CreateChartSqlWizard;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateSelectChartPage;

public class ChartBlockModelPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener
{

	private ChartBlockModelFigure figure;

	@Override
	protected List<Element> getModelChildren()
	{
		List<Element> elements = new ArrayList<Element>();
		ChartBlockModel block = (ChartBlockModel) getModel();
		return elements;
	}

	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((ChartBlockModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((ChartBlockModel) getModel()).removePropertyChangeListener(this);
	}

	protected void refreshVisuals()
	{
		ChartBlockModel node = (ChartBlockModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((ChartBlockModelFigure) this.getFigure()).setBlockModel((ChartBlockModel) this.getModel());
		((ChartBlockModelFigure) this.getFigure()).getContainer(((ChartBlockModel) this.getModel())
				.getChartType());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
	}

	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if (prop.equals(ChartBlockModel.PROP_NAME) || prop.equals(ChartBlockModel.PROP_RECTANGLE)
				|| prop.equals(ChartBlockModel.PROP_CHART_TYPE)) {
			refreshVisuals();
		} else if (prop.equals(ChartBlockModel.PROP_PARAMS)) {
			refreshChildren();
		}

	}

	@Override
	public void performRequest(Request request)
	{
		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			SqlSet sqlSet = new SqlSet();
			CreateChartSqlWizard wizard = new CreateChartSqlWizard(sqlSet);
			final WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(),
					wizard);
			if (((ChartBlockModel) this.getModel()).getSqlSet() != null) {
				final IPageChangedListener pageChangedListener = new IPageChangedListener() {
					@Override
					public void pageChanged(PageChangedEvent event)
					{
						if (event.getSelectedPage() instanceof CreateSelectChartPage) {
							((CreateSelectChartPage) dialog.getCurrentPage())
									.setData(((ChartBlockModel) getModel()).getSqlSet());
							dialog.removePageChangedListener(this);
						}
					}
				};
				dialog.addPageChangedListener(pageChangedListener);
			}
			dialog.open();
			if (sqlSet.getChartType() != null && sqlSet.getSqls() != null) {
				((ChartBlockModel) getModel()).setSqlSet(sqlSet);
				((ChartBlockModel) getModel()).setChartType(sqlSet.getChartType().getName());
				((ChartBlockModel) getModel()).setChartTypeDisplayName(sqlSet.getChartType()
						.getDisplayName());
				((ChartBlockModel) getModel()).setBlockName(sqlSet.getName());
				this.getRoot().getViewer().getEditDomain().getCommandStack().execute(new Command() {
				});
			}
		}
	}

	@Override
	protected IFigure createFigure()
	{
		if (figure == null) {
			figure = new ChartBlockModelFigure();
		}
		return figure;
	}

	@Override
	protected void createEditPolicies()
	{
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ChartBlockModelEditPolicy());
	}

}
