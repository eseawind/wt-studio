package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;

public class ResizeBlockModelCommand extends Command
{
	private BlockModel blockModel;
	private Rectangle oldRectangle;
	private Rectangle newRectangle;

	// setters

	public void setNode(BlockModel node)
	{
		this.blockModel = node;
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
		oldRectangle = this.blockModel.getRectangle();
		this.blockModel.setRectangle(newRectangle);
	}

	public void undo()
	{
		this.blockModel.setRectangle(oldRectangle);
	}

	public void redo()
	{
		this.blockModel.setRectangle(newRectangle);
	}

}

