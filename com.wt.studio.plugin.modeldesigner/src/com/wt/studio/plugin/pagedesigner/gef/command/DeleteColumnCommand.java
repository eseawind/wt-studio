package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.TableControlModel;

public class DeleteColumnCommand extends Command
{
	private ColumnModel column;
	private Element parent;
	public ColumnModel getColumn()
	{
		return column;
	}
	public void setColumn(ColumnModel column)
	{
		this.column = column;
	}
	public Element getParent()
	{
		return parent;
	}
	public void setParent(Element parent)
	{
		this.parent = parent;
	}
	public String getLabel()
	{
		return "Delete column";
	}

	public void execute()
	{
		if(parent instanceof ColumnModel)
			((ColumnModel)parent).removeColumn(column);
		else if(parent instanceof TableControlModel)
			((TableControlModel)parent).removeColumn(column);
	}

	public void undo()
	{
		if(parent instanceof ColumnModel)
			((ColumnModel)parent).addColumn(column);
		else if(parent instanceof TableControlModel)
			((TableControlModel)parent).addColumn(column);
	}

	public void redo()
	{
		this.execute();
	}
	

}
