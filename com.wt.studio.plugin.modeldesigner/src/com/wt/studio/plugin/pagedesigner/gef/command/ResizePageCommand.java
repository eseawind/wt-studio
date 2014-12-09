package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;



public class ResizePageCommand extends Command
{

	private ControlPageModel pageModel;
	private Rectangle oldRectangle;
	private Rectangle newRectangle;

	// setters

	public void setNode(ControlPageModel node)
	{
		this.pageModel = node;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public void setRectangle(Rectangle rectangle)
	{
		this.newRectangle = rectangle;
	}

	public String getLabel()
	{
		return "Move Node";
	}

	public void execute()
	{
		oldRectangle = this.pageModel.getRectangle();
		this.pageModel.setRectangle(newRectangle);
	}

	public void undo()
	{
		this.pageModel.setRectangle(oldRectangle);
	}

	public void redo()
	{
		this.pageModel.setRectangle(newRectangle);
	}

}
