package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.XYLayout;

import com.wt.studio.plugin.pagedesigner.gef.layout.PageXYLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;

public class DiagramFigure extends Figure
{
	private Diagram diagram;
	private FrameBorder border;
	
	public DiagramFigure(){
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
	public Diagram GetDiagramModel(){
		return this.diagram;
	}
	public void setDiagramModel(Diagram diagram)
	{
		this.diagram=diagram;
		this.border.setLabel(diagram.getName());
		this.repaint();
	}
	

	
}
