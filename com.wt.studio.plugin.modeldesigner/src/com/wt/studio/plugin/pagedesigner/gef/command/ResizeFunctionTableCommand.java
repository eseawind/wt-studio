package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;

public class ResizeFunctionTableCommand extends Command
{

	private FunctionModel funcModel;
	private Rectangle oldRectangle;
	private Rectangle newRectangle;

	// setters

	public void setNode(FunctionModel node)
	{
		this.funcModel = node;
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
		oldRectangle = this.funcModel.getRectangle();
		this.funcModel.setRectangle(newRectangle);
	}

	public void undo()
	{
		this.funcModel.setRectangle(oldRectangle);
	}

	public void redo()
	{
		this.funcModel.setRectangle(newRectangle);
	}

}
