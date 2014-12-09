package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;

public class MoveParamCommand extends Command
{

	private ParamModel model;
	private Rectangle oldRectangle;
	private Rectangle newRectangle;

	public void setRectangle(Rectangle rectangle)
	{
		this.newRectangle = rectangle;
	}

	public void setTablekModel(ParamModel tablekModel)
	{
		this.model = tablekModel;
	}

	@Override
	public String getLabel()
	{
		return "Move table";
	}

	@Override
	public void execute()
	{
		oldRectangle = this.model.getRectangle();
		this.model.setRectangle(newRectangle);
	}

	@Override
	public void undo()
	{
		this.model.setRectangle(oldRectangle);
	}

	@Override
	public void redo()
	{
		this.model.setRectangle(newRectangle);
	}

}