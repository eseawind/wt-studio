package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.ToolbarLayout;

import com.wt.studio.plugin.pagedesigner.gef.layout.HorizontalLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;

public class ColumnModelFigure extends Figure
{

	private ColumnModel column;
	private FrameBorder frame;

	public ColumnModelFigure(){
		frame=new FrameBorder();
		this.setBorder(frame);
		ToolbarLayout tool=new ToolbarLayout();
		tool.setHorizontal(true);
		tool.setSpacing(5);
		this.setLayoutManager(tool);
	}
	public ColumnModel getColumn()
	{
		return column;
	}

	public void setColumn(ColumnModel column)
	{
		this.column = column;
		frame.setLabel(column.getTitle());
	}
}
