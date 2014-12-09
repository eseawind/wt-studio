package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;

public class MovePageChildBlockCommand extends Command
{
	private BlockModel parentblock;
	private int beforeNum;
	private int flag;
	private Element after;
	private Element before;
	private int afterNum;

	public void setBeforeNum(int num)
	{
		this.beforeNum = num;
	}

	public void setFlag(int flag)
	{
		this.flag = flag;
	}

	public void setParentBlock(BlockModel block)
	{
		this.parentblock = block;
	}

	public void execute()
	{
		if (flag == 1) {
			afterNum = beforeNum + 1;

		} else if (flag == 0) {
			afterNum = beforeNum - 1;
		}
		after = this.parentblock.getElements().get(afterNum);
		before = this.parentblock.getElements().get(beforeNum);
		this.parentblock.getElements().set(beforeNum, after);
		this.parentblock.getElements().set(afterNum, before);
		this.parentblock.reRank();
	}

	public void undo()
	{
		this.parentblock.getElements().set(beforeNum, before);
		this.parentblock.getElements().set(afterNum, after);
		this.parentblock.reRank();
	}

	public void redo()
	{
		this.execute();
	}

}
