package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;


public class MoveElementCommand extends Command
{

	private BONodeModel element;
	private Rectangle oldRectangle;
	private Rectangle newRectangle;

	public void setRectangle(Rectangle rectangle)
	{
		this.newRectangle = rectangle;
	}

	public void setElement(BONodeModel tablekModel)
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
