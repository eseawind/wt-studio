package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;


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

		this.index = this.parentblock.getAllElement().indexOf(this.childblock);
		this.after = this.parentblock.getAllElement().indexOf(this.afterblock);
		if (index != 0) {
			if (after == 0) {
				this.parentblock.getAllElement().set(index, this.parentblock.getAllElement().get(1));
				this.parentblock.getAllElement().set(1, childblock);
			} else {
				this.parentblock.getAllElement().set(index, afterblock);
				this.parentblock.getAllElement().set(after, childblock);
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
