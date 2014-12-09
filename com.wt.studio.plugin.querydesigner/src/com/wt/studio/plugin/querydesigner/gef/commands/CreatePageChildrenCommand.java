package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.GhostModel;

public class CreatePageChildrenCommand extends Command
{

	private Element node;
	private Rectangle rectangle;
	private BlockModel blockModelParent;
	private GhostModel ghost = new GhostModel();
	private Element target;

	// setters

	public Element getTarget()
	{
		return target;
	}

	public void setTarget(Element target)
	{
		this.target = target;
	}

	public void setNode(Element node)
	{
		this.node = node;
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

		if (node instanceof BlockModel) {
			((BlockModel) node).addElement(-1, this.ghost);
		}
		if (this.rectangle != null) {
			this.node.setRectangle(rectangle);
		}
		if (this.blockModelParent != null && this.target != null) {
			int index = this.blockModelParent.getElements().indexOf(target);
			this.blockModelParent.addElement(index, this.node);
			// this.blockModelParent.addElement(this.blockModel);
		} else {
			this.blockModelParent.addElement(-1, this.node);
		}
	}

	public void undo()
	{
		if (this.blockModelParent != null) {
			this.blockModelParent.removeElement(this.node);
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