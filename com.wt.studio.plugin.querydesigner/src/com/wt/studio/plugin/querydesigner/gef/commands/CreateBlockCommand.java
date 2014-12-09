package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.GhostModel;

public class CreateBlockCommand extends Command
{

	private BlockModel blockModel;
	private Rectangle rectangle;
	private BlockModel blockModelParent;
	private GhostModel ghost = new GhostModel();

	// setters

	public void setNode(BlockModel node)
	{
		this.blockModel = node;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Create Node";
	}

	public void execute()
	{
		blockModel.addElement(-1, this.ghost);
		if (this.rectangle != null) {
			this.blockModel.setRectangle(rectangle);
		}
		if (this.blockModelParent != null) {
			this.blockModelParent.addElement(-1, this.blockModel);
			// this.blockModelParent.addElement(this.blockModel);
		}
	}

	public void undo()
	{
		if (this.blockModelParent != null) {
			this.blockModelParent.removeElement(this.blockModel);
			// this.blockModelParent.removeElement(this.blockModel);
		}
	}

	public void redo()
	{
		this.execute();
	}

	public void setBlockModel(BlockModel block)
	{
		this.blockModelParent = block;
	}
}