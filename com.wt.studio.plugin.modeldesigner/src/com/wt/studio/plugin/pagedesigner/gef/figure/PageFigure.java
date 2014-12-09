package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.XYLayout;

import com.wt.studio.plugin.pagedesigner.gef.layout.PageXYLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;

public class PageFigure extends Figure
{
	private ControlPageModel page;
	private FrameBorder border;
	
	
	public PageFigure(){
		setLayoutManager(new PageXYLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new FrameBorder();
		border.setLabel("Block");
		this.setBorder(border);
	}
	public String getText()
	{
		return border.getLabel();
	}
	public ControlPageModel GetpageModel(){
		return this.page;
	}
	public void setPageModel(ControlPageModel page)
	{
		this.page=page;
		this.border.setLabel(page.getName());
		this.repaint();
	}
	

}
