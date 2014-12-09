package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;

public class CreateAddParamCommand extends Command
{
	AbstractBlockModel childparentblock;
	AbstractBlockModel afterparentblock;
	Element childelement;
	Element afterelement;
	int before;

	public void setChildParent(AbstractBlockModel block)
	{
		this.childparentblock = block;
	}

	public void setAfterParent(AbstractBlockModel block)
	{
		this.afterparentblock = block;
	}

	public void setChild(Element child)
	{
		this.childelement = child;
	}

	public void setAfter(Element after)
	{
		this.afterelement = after;
	}

	public void execute()
	{
		before = childparentblock.getElements().indexOf(this.childelement);
		childparentblock.removeElement(childelement);
		if (this.afterelement != null) {
			int after = this.afterparentblock.getElements().indexOf(this.afterelement);
			afterparentblock.addElement(after + 1, childelement);
		} else {
			afterparentblock.addElement(-1, childelement);
		}
		int now = this.afterparentblock.getElements().indexOf(this.childelement);
		childparentblock.reRank();
		afterparentblock.reRank();
	}

	public void undo()
	{
		afterparentblock.removeElement(childelement);
		childparentblock.addElement(before, childelement);
		afterparentblock.reRank();
		childparentblock.reRank();
	}

	public void redo()
	{
		this.execute();
	}

}
