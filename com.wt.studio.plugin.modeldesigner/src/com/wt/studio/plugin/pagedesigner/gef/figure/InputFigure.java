package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;

public class InputFigure extends Figure {

	private Label paramDescriptionLabel;
	private IFigure container;
	private String name;

	public InputFigure() {
		setLayoutManager(new FlowLayout());
		paramDescriptionLabel = new Label("参数名：");
		add(paramDescriptionLabel);
		TextFieldFigure text = new TextFieldFigure("test",0);
		add(text);
	}

	public void setName(String name) {
		this.name = name;
		this.paramDescriptionLabel.setText(name);
		repaint();
	}

	public String getName() {
		return this.name;
	}

	public void setBounds(Rectangle rect) {
		rect.height = 25;
		rect.width = 250;
		super.setBounds(rect);
	}

}
