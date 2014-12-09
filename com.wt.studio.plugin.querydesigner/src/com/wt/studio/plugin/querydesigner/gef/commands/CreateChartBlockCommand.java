package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;

public class CreateChartBlockCommand extends Command
{
	private Diagram diagram;
	private ChartBlockModel block;
	private Rectangle rectangle;
	private BlockModel blockModel;

	// setters

	public void setDiagram(Diagram diagram)
	{
		this.diagram = diagram;
	}

	public void setBlock(ChartBlockModel block)
	{
		this.block = block;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Create query block";
	}

	public void execute()
	{
		if (this.rectangle != null) {
			this.block.setRectangle(rectangle);
		}
		if (this.diagram != null)
			this.diagram.addChartBlockModel(this.block);
		if (this.blockModel != null) {
			this.blockModel.addElement(-1, this.block);
		}
	}

	public void undo()
	{
		if (this.diagram != null)
			this.diagram.removeChartBlockModel(this.block);
		if (this.blockModel != null)
			this.blockModel.removeElement(this.block);
	}

	public void redo()
	{
		this.execute();
	}

	public void setBlockModel(BlockModel block)
	{
		this.blockModel = block;

	}
}
