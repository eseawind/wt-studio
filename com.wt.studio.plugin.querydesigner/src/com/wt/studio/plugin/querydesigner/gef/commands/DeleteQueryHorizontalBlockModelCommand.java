package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;

public class DeleteQueryHorizontalBlockModelCommand extends Command
{
	private QueryBlockModel block;
	private QueryHorizontalBlockModel node;

	public void setBlock(QueryBlockModel queryBlockModel)
	{
		this.block = queryBlockModel;
	}

	public void setNode(QueryHorizontalBlockModel node)
	{
		this.node = node;
	}

	public String getLabel()
	{
		return "Delete param";
	}

	public void execute()
	{
		this.block.removeElement(node);
	}

	public void undo()
	{

		this.block.addElement(-1, node);

	}

	public void redo()
	{
		this.execute();
	}
}