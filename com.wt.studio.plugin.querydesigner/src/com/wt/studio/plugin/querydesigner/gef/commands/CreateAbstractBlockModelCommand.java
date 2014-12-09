package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;

public class CreateAbstractBlockModelCommand extends Command
{

	private AbstractBlockModel frameModel;
	private Rectangle rectangle;
	private BlockModel blockModel;

	public void setBlockModel(BlockModel blockModel)
	{
		this.blockModel = blockModel;
	}

	public void setAbstractBlockModel(AbstractBlockModel frameModel)
	{
		this.frameModel = frameModel;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Create Table";
	}

	public void execute()
	{
		if (this.rectangle != null) {
			this.frameModel.setRectangle(this.rectangle);
		}
		if (this.blockModel != null)
			this.blockModel.addElement(-1, this.frameModel);
	}

	public void undo()
	{
		if (this.blockModel != null)
			this.blockModel.removeElement(this.frameModel);
	}

	public void redo()
	{
		this.execute();
	}
}