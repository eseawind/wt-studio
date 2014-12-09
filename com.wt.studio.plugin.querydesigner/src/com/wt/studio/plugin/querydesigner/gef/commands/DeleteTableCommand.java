package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;

public class DeleteTableCommand extends Command
{

	private Diagram diagram;
	private TableModel tableModel;
	private BlockModel parentblockmodel;

	public void setDiagram(Diagram diagram)
	{
		this.diagram = diagram;
	}

	public void setParentBlockModel(BlockModel pblock)
	{
		this.parentblockmodel = pblock;
	}

	public void setTableModel(TableModel tableModel)
	{
		this.tableModel = tableModel;
	}

	public String getLabel()
	{
		return "Delete block";
	}

	public void execute()
	{
		if (this.diagram != null)
			this.diagram.removeTable(this.tableModel);
		if (this.parentblockmodel != null)
			this.parentblockmodel.removeElement(tableModel);
	}

	public void undo()
	{
		if (this.diagram != null)
			this.diagram.addTable(this.tableModel);
		if (this.parentblockmodel != null)
			this.parentblockmodel.addElement(-1, tableModel);
	}

	public void redo()
	{
		this.execute();
	}
}