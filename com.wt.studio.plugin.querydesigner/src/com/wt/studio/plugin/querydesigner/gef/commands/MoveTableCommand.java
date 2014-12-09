package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.TableModel;

public class MoveTableCommand extends Command
{

	private TableModel tablekModel;
	private Rectangle oldRectangle;
	private Rectangle newRectangle;

	public void setRectangle(Rectangle rectangle)
	{
		this.newRectangle = rectangle;
	}

	public void setTablekModel(TableModel tablekModel)
	{
		this.tablekModel = tablekModel;
	}

	@Override
	public String getLabel()
	{
		return "Move table";
	}

	@Override
	public void execute()
	{
		oldRectangle = this.tablekModel.getRectangle();
		this.tablekModel.setRectangle(newRectangle);
	}

	@Override
	public void undo()
	{
		this.tablekModel.setRectangle(oldRectangle);
	}

	@Override
	public void redo()
	{
		this.tablekModel.setRectangle(newRectangle);
	}

}