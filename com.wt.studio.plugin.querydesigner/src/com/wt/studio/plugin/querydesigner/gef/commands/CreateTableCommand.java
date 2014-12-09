package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;

public class CreateTableCommand extends Command
{

	private Diagram diagram;
	private TableModel tableModel;
	private Rectangle rectangle;
	private BlockModel blockModel;

	// setters
	public void setDiagram(Diagram blockModel)
	{
		this.diagram = blockModel;
	}

	public void setBlockModel(BlockModel blockModel)
	{
		this.blockModel = blockModel;
	}

	public void setTableModel(TableModel tableModel)
	{
		this.tableModel = tableModel;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Create Table";
	}

	public void execute()
	{
		if (this.rectangle != null) {
			this.tableModel.setRectangle(this.rectangle);
		}
		if (this.diagram != null)
			this.diagram.addTable(this.tableModel);
		if (this.blockModel != null)
			this.blockModel.addElement(-1, this.tableModel);
	}

	public void undo()
	{
		if (this.diagram != null)
			this.diagram.removeTable(this.tableModel);
		if (this.blockModel != null)
			this.blockModel.removeElement(this.tableModel);
	}

	public void redo()
	{
		this.execute();
	}
}