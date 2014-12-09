package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.TableControlModel;

public class createAddColumnCommand extends Command
{

	private ColumnModel child;
	private Element beforeParent;
	private Element parent;
	public ColumnModel getChild()
	{
		return child;
	}
	public void setChild(ColumnModel child)
	{
		this.child = child;
	}
	public Element getBeforeParent()
	{
		return beforeParent;
	}
	public void setBeforeParent(Element beforeParent)
	{
		this.beforeParent = beforeParent;
	}
	public Element getParent()
	{
		return parent;
	}
	public void setParent(Element parent)
	{
		this.parent = parent;
	}
	public void execute()
	{
		if(beforeParent instanceof ColumnModel)
		{
			((ColumnModel) beforeParent).removeColumn(child);
		}
		else if(beforeParent instanceof TableControlModel)
		{
			((TableControlModel)beforeParent).removeColumn(child);
		}
		if(parent instanceof ColumnModel)
		{
			((ColumnModel) parent).addColumn(child);
		}
		else if(parent instanceof TableControlModel)
		{
			((TableControlModel)parent).addColumn(child);
		}
	}
	public void redo()
	{
		execute();
	}
	public void undo()
	{
		if(beforeParent instanceof ColumnModel)
		{
			((ColumnModel) beforeParent).addColumn(child);
		}
		else if(beforeParent instanceof TableControlModel)
		{
			((TableControlModel)beforeParent).addColumn(child);
		}
		if(parent instanceof ColumnModel)
		{
			((ColumnModel) parent).removeColumn(child);
		}
		else if(parent instanceof TableControlModel)
		{
			((TableControlModel)parent).removeColumn(child);
		}
	}
}
