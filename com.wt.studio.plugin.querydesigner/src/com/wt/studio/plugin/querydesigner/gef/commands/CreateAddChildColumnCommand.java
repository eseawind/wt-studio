package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.GhostModel;

public class CreateAddChildColumnCommand extends Command
{
	private AbstractBlockModel childParent;
	private AbstractBlockModel afterParent;
	private Element child;
	private Element after;
	private int before;

	public AbstractBlockModel getChildParent()
	{
		return childParent;
	}

	public void setChildParent(AbstractBlockModel childParent)
	{
		this.childParent = childParent;
	}

	public AbstractBlockModel getAfterParent()
	{
		return afterParent;
	}

	public void setAfterParent(AbstractBlockModel afterParent)
	{
		this.afterParent = afterParent;
	}

	public void setChild(Element child)
	{
		this.child = child;
	}

	public void setAfter(Element after)
	{
		this.after = after;
	}

	public void execute()
	{

		before = childParent.getElements().indexOf(this.child);
		if (!(this.child instanceof GhostModel)) {
			childParent.removeElement(child);
			int after = this.afterParent.getElements().indexOf(this.after);
			afterParent.addElement(after + 1, child);
			int now = this.afterParent.getElements().indexOf(this.child);
		}
		childParent.reRank();
		afterParent.reRank();
	}

	public void undo()
	{
		afterParent.removeElement(child);
		childParent.addElement(before, child);
		afterParent.reRank();
		childParent.reRank();
	}

	public void redo()
	{
		this.execute();
	}

}
