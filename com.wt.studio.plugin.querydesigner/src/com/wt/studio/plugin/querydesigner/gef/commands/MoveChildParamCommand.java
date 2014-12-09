package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;

public class MoveChildParamCommand extends Command
{
	private AbstractBlockModel queryblock;
	private Element childParam;
	private Element afterParam;
	private int index;
	private int after;

	public void setChildParam(Element param)
	{
		this.childParam = param;
	}

	public void setAfterParam(Element param)
	{
		this.afterParam = param;
	}

	public void setParentBlock(AbstractBlockModel queryblock)
	{
		this.queryblock = queryblock;
	}

	public void execute()
	{

		this.index = this.queryblock.getElements().indexOf(this.childParam);
		this.after = this.queryblock.getElements().indexOf(this.afterParam);
		// this.queryblock.getElements().set(after, childParam);
		this.queryblock.getElements().remove(childParam);
		this.queryblock.getElements().add(after, childParam);
		// this.queryblock.getElements().set(index, afterParam);
		this.queryblock.reRank();
	}

	public void undo()
	{
		this.queryblock.getElements().remove(childParam);
		this.queryblock.getElements().add(index, childParam);
		this.queryblock.reRank();
	}

	public void redo()
	{
		undo();
	}

}
