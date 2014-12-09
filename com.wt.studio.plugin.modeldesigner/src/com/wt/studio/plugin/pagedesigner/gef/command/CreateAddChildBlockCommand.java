package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.GhostElement;
import com.wt.studio.plugin.pagedesigner.gef.model.XYBlockModel;


public class CreateAddChildBlockCommand extends Command
{
	private BlockModel childparentblock;
	private BlockModel afterparentblock;
	private Element childelement;
	private Element afterelement;
	private Rectangle layout;
	private int before;

	public void setChildParent(BlockModel block)
	{
		this.childparentblock = block;
	}

	public void setAfterParent(BlockModel block)
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
		if(!(this.childelement instanceof GhostElement))
		{
		before = childparentblock.getAllElement().indexOf(this.childelement);
		
			if(afterelement!=null)
			{
			childparentblock.removeElement(childelement);
			int after = this.afterparentblock.getAllElement().indexOf(this.afterelement);
			afterparentblock.addElement(after+1,childelement);
			int now = this.afterparentblock.getAllElement().indexOf(this.childelement);
			}
			else if(afterelement==null)
			{
				childparentblock.removeElement(childelement);
				if(afterparentblock instanceof XYBlockModel)
				{
					childelement.setRectangle(layout);
				}
				afterparentblock.addElement(-1,childelement);	
			}
		
		childparentblock.reRank();
		afterparentblock.reRank();
		}
	}

	public void undo()
	{
		afterparentblock.removeElement(childelement);
		childparentblock.addElement(before,childelement);
		afterparentblock.reRank();
		childparentblock.reRank();
	}

	public void redo()
	{
		this.execute();
	}

	public void setRectangle(Rectangle layout)
	{
		// TODO Auto-generated method stub
		this.layout=layout;
	}

}
