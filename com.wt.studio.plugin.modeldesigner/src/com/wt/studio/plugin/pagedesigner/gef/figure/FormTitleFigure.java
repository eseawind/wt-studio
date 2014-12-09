package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.pagedesigner.gef.layout.FormXYLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;

public class FormTitleFigure extends Figure
{
	private FormModel form;
	private FrameBorder border;
	protected Rectangle rec;
	
	
	public FormTitleFigure(){
		setLayoutManager(new FormXYLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new FormFrameBorder();
		border.setLabel("Block");
		this.setBorder(border);
	}
	public String getText()
	{
		return border.getLabel();
	}
	public FormModel getFormModel(){
		return this.form;
	}
	public void setFormModel(FormModel form)
	{
		this.form=form;
		this.border.setLabel(form.getName());
		this.repaint();
	}
	public Rectangle getRectangle()
	{
		return rec;
	}
	public void setRectangle(Rectangle rec)
	{
		this.rec=rec;
	}

}


