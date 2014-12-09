package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;

public class CreateParamCommand extends Command
{

	private AbstractBlockModel block;
	private ParamModel param;
	private Rectangle rectangle;
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

	public void setParamModel(ParamModel ParamModel)
	{
		this.param = ParamModel;
	}

	public void setBlock(AbstractBlockModel block)
	{
		this.block = block;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Create param";
	}

	public void execute()
	{

		if (this.rectangle != null) {
			this.param.setRectangle(this.rectangle);
		}
		if (this.target != null) {
			int index = this.block.getElements().indexOf(target);

			this.block.addElement(index, this.param);
		} else
			this.block.addElement(-1, this.param);

	}

	public void undo()
	{
		if (this.block instanceof QueryBlockModel) {
			((QueryBlockModel) this.block).removeElement(this.param);
		} else if (this.block instanceof QueryHorizontalBlockModel) {
			((QueryHorizontalBlockModel) this.block).removeElement(this.param);
		}
	}

	public void redo()
	{
		this.execute();
	}

}