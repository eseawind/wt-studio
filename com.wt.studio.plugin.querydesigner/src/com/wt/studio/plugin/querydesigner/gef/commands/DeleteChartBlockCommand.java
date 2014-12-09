package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;

public class DeleteChartBlockCommand extends Command
{

	private Diagram diagram;
	private ChartBlockModel block;
	private BlockModel parentblockmodel;

	public void setDiagram(Diagram diagram)
	{
		this.diagram = diagram;
	}

	public void setParentBlockModel(BlockModel pblock)
	{
		this.parentblockmodel = pblock;
	}

	public void setBlock(ChartBlockModel node)
	{
		this.block = node;
	}

	public String getLabel()
	{
		return "Delete chart block";
	}

	public void execute()
	{
		if (this.diagram != null)
			this.diagram.removeChartBlockModel(block);
		if (this.parentblockmodel != null)
			this.parentblockmodel.removeElement(block);
	}

	public void undo()
	{
		if (this.diagram != null)
			this.diagram.addChartBlockModel(this.block);
		if (this.parentblockmodel != null)
			this.parentblockmodel.addElement(-1, block);
	}

	public void redo()
	{
		this.execute();
	}
}
