package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Rectangle;

import com.wt.studio.plugin.pagedesigner.gef.layout.FormXYLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;

public class FormFigure  extends Figure
{
	private FormModel form;
	private LineBorder border;
	protected Rectangle rec;
	
	
	public FormFigure(){
		setLayoutManager(new FormXYLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new LineBorder();
		border.setColor(ColorConstants.gray);
		this.setBorder(border);
		
	}
	public FormModel getFormModel(){
		return this.form;
	}
	public void setFormModel(FormModel form)
	{
		this.form=form;
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
