package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.ViewModel;

public class CreateViewCommand extends Command
{
	private BOModelDiagram diagram;
	private ViewModel viewModel;
	private Rectangle rectangle;

	// setters

	public void setDiagram(BOModelDiagram diagram)
	{
		this.diagram = diagram;
	}

	public void setNode(ViewModel node)
	{
		this.viewModel = node;
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
			this.viewModel.setRectangle(rectangle);
		}
		this.diagram.addViewModel(this.viewModel);
	}

	public void undo()
	{
		this.diagram.removeViewModel(this.viewModel);
	}

	public void redo()
	{
		this.execute();
	}
	
	
}

