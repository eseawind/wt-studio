package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;

public class MoveChildEventModelCommand extends Command
{
	private BlockModel parentblock;
	private AbstractBlockModel childblock;
	private AbstractBlockModel afterblock;
	private int index;
	private int after;

	public void setChildBlock(AbstractBlockModel block)
	{
		this.childblock = block;
	}

	public void setAfterBlock(AbstractBlockModel block)
	{
		this.afterblock = block;
	}

	public void setParentBlock(BlockModel block)
	{
		this.parentblock = block;
	}

	public void execute()
	{
		this.index = this.parentblock.getElements().indexOf(this.childblock);
		this.after = this.parentblock.getElements().indexOf(this.afterblock);
		this.parentblock.getElements().set(index, (AbstractBlockModel) afterblock);
		this.parentblock.getElements().set(after, (AbstractBlockModel) childblock);
		this.parentblock.reRank();
	}

	public void undo()
	{
		this.execute();
	}

	public void redo()
	{
		this.execute();
	}
}
