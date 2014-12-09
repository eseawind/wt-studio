package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;

public class CreateTableCommand extends Command
{
	private BOModelDiagram diagram;
	private HdbTableModel tableModel;
	private Rectangle rectangle;

	// setters

	public void setDiagram(BOModelDiagram diagram)
	{
		this.diagram = diagram;
	}

	public void setNode(HdbTableModel node)
	{
		this.tableModel = node;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Create Node";
	}

	public void execute()
	{
		if (this.rectangle != null) {
			this.tableModel.setRectangle(rectangle);
		}
		this.diagram.addTableModel(this.tableModel);
	}

	public void undo()
	{
		this.diagram.removeTableModel(this.tableModel);
	}

	public void redo()
	{
		this.execute();
	}
	
	
}
