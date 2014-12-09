package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;

public class DeleteQueryBlockCommand extends Command
{

	private Diagram diagram;
	private QueryBlockModel block;
	private BlockModel parentblockmodel;

	public void setDiagram(Diagram diagram)
	{
		this.diagram = diagram;
	}

	public void setParentBlockModel(BlockModel pblock)
	{
		this.parentblockmodel = pblock;
	}

	public void setBlock(QueryBlockModel node)
	{
		this.block = node;
	}

	public String getLabel()
	{
		return "Delete query block";
	}

	public void execute()
	{
		if (this.diagram != null)
			this.diagram.removeQueryBlockModel(block);
		if (this.parentblockmodel != null)
			this.parentblockmodel.removeElement(block);

	}

	public void undo()
	{
		if (this.diagram != null)
			this.diagram.addQueryBlockModel(this.block);
		if (this.parentblockmodel != null)
			this.parentblockmodel.addElement(-1, block);
	}

	public void redo()
	{
		this.execute();
	}
}