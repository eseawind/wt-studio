package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;

import com.wt.studio.plugin.querydesigner.gef.model.TitleModel;

public class TitleModelFigure extends Figure
{
	private TitleModel title;
	private Label label;

	public TitleModel getTitle()
	{
		return title;
	}

	public void setTitle(TitleModel title)
	{
		this.title = title;
		label.setText(title.getName());
	}

	public TitleModelFigure()
	{
		setLayoutManager(new GridLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		LineBorder border = new LineBorder();
		border.setColor(ColorConstants.gray);
		this.setBorder(border);
		label = new Label();
		this.add(label);
	}

}
