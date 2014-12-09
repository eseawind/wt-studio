package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;

public class DeleteBlockCommand extends Command
{

	private Diagram diagram;
	private BlockModel blockModel;
	private int index;
	private BlockModel parentblockmodel;

	public void setDiagram(Diagram diagram)
	{
		this.diagram = diagram;
	}

	public void setNode(BlockModel node)
	{
		this.blockModel = node;
	}

	public void setParentBlockModel(BlockModel blockmodel)
	{
		this.parentblockmodel = blockmodel;
	}

	public String getLabel()
	{
		return "Delete block";
	}

	public void execute()
	{

		if (this.diagram != null) {
			this.index = this.diagram.getBlockModels().indexOf(this.blockModel);
			this.diagram.removeBlockModel(blockModel);
		}
		if (this.parentblockmodel != null)
			this.parentblockmodel.removeElement(blockModel);
	}

	public void undo()
	{
		if (this.diagram != null)
			this.diagram.addBlockModel(this.blockModel);
		if (this.parentblockmodel != null)
			this.parentblockmodel.addElement(-1, blockModel);
	}

	public void redo()
	{
		this.execute();
	}
}