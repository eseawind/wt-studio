package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import com.wt.studio.plugin.pagedesigner.gef.layout.FillLayout;
import com.wt.studio.plugin.pagedesigner.gef.layout.FormXYLayout;
import com.wt.studio.plugin.pagedesigner.gef.layout.PageXYLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;

public class FormModelFigure extends Figure {
	private FormModel form;
	protected Rectangle rec;
	private IFigure container;

	public FormModelFigure() {
		setLayoutManager(new PageXYLayout());
	}

	public FormModel getFormModel() {
		return this.form;
	}

	public void setFormModel(FormModel form) {
		this.form = form;
		this.repaint();
	}

	public Rectangle getRectangle() {
		return rec;
	}

	public void setRectangle(Rectangle rec) {
		this.rec = rec;
	}

	public void setTitle(int flag) {
		if(flag==0)
		{
			FormFrameBorder border=new FormFrameBorder();
			border.setLabel(this.form.getName());
			this.setBorder(border);
		}
		else if(flag==1)
		{
			this.setBorder(new LineBorder());
		}
		this.repaint();
	}
}
