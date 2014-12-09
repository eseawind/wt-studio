package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;

public class MoveQueryBlockCommand extends Command
{

	private QueryBlockModel block;
	private Rectangle oldRectangle;
	private Rectangle newRectangle;

	// setters

	public void setModel(QueryBlockModel block)
	{
		this.block = block;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public void setRectangle(Rectangle rectangle)
	{
		this.newRectangle = rectangle;
	}

	public String getLabel()
	{
		return "Move query block";
	}

	public void execute()
	{
		oldRectangle = this.block.getRectangle();
		this.block.setRectangle(newRectangle);
	}

	public void undo()
	{
		this.block.setRectangle(oldRectangle);
	}

	public void redo()
	{
		this.block.setRectangle(newRectangle);
	}

}