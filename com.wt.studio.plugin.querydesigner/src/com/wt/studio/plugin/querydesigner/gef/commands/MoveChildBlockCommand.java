package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;

public class MoveChildBlockCommand extends Command
{
	private BlockModel parentblock;
	private Element childblock;
	private Element afterblock;
	private int index;
	private int after;

	public void setChildElement(Element element)
	{
		this.childblock = element;
	}

	public void setAfterElement(Element element)
	{
		this.afterblock = element;
	}

	public void setParentBlock(BlockModel block)
	{
		this.parentblock = block;
	}

	public void execute()
	{

		this.index = this.parentblock.getElements().indexOf(this.childblock);
		this.after = this.parentblock.getElements().indexOf(this.afterblock);
		if (index != 0) {
			if (after == 0) {
				this.parentblock.getElements().set(index, this.parentblock.getElements().get(1));
				this.parentblock.getElements().set(1, childblock);
			} else {
				this.parentblock.getElements().set(index, afterblock);
				this.parentblock.getElements().set(after, childblock);
			}
		}
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
