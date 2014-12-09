package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;


import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;


public class ChangeChildBlockNumCommand extends Command
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
		after = this.parentblock.getAllElement().get(afterNum);
		before = this.parentblock.getAllElement().get(beforeNum);
		this.parentblock.getAllElement().set(beforeNum, after);
		this.parentblock.getAllElement().set(afterNum, before);
		this.parentblock.reRank();
	}

	public void undo()
	{
		this.parentblock.getAllElement().set(beforeNum, before);
		this.parentblock.getAllElement().set(afterNum, after);
		this.parentblock.reRank();
	}

	public void redo()
	{
		this.execute();
	}

}
