package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.SimpleLoweredBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;

public class ColumnModelFigure extends Figure
{
	private ColumnModel2 columnModel;
	private Label columnDescriptionLabel;

	public ColumnModelFigure()
	{
		setBorder(new SimpleLoweredBorder());
		setLayoutManager(new ToolbarLayout());
		columnDescriptionLabel = new Label("abc:");
		add(columnDescriptionLabel);
	}

	public ColumnModel2 getColumnModel()
	{
		return columnModel;
	}

	public void setColumnModel(ColumnModel2 model)
	{
		this.columnModel = model;
		if (model.getClickurl() != null) {
			if (!model.getClickurl().trim().equals("")) {
				this.columnDescriptionLabel.setForegroundColor(Display.getCurrent().getSystemColor(
						SWT.COLOR_BLUE));
			} else {
				this.columnDescriptionLabel.setForegroundColor(this.getForegroundColor());
			}
		}
		this.setColumnDescription(model.getDescription());
		this.repaint();
	}

	public void setColumnDescription(String description)
	{
		columnDescriptionLabel.setText(description);
	}
}