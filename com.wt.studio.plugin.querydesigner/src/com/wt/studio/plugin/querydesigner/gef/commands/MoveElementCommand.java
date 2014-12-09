package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.Element;

public class MoveElementCommand extends Command
{

	private Element element;
	private Rectangle oldRectangle;
	private Rectangle newRectangle;

	public void setRectangle(Rectangle rectangle)
	{
		this.newRectangle = rectangle;
	}

	public void setElement(Element tablekModel)
	{
		this.element = tablekModel;
	}

	@Override
	public String getLabel()
	{
		return "move element";
	}

	@Override
	public void execute()
	{
		oldRectangle = this.element.getRectangle();
		this.element.setRectangle(newRectangle);
	}

	@Override
	public void undo()
	{
		this.element.setRectangle(oldRectangle);
	}

	@Override
	public void redo()
	{
		this.element.setRectangle(newRectangle);
	}

}