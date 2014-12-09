package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;

public class CreateQueryBlockCommand extends Command
{

	private Diagram diagram;
	private QueryBlockModel block;
	private Rectangle rectangle;
	private BlockModel blockModel;

	// setters

	public void setDiagram(Diagram diagram)
	{
		this.diagram = diagram;
	}

	public void setBlock(QueryBlockModel block)
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
		this.block.addElement(-1, new QueryHorizontalBlockModel());
		if (this.diagram != null)
			this.diagram.addQueryBlockModel(this.block);
		if (this.blockModel != null)
			this.blockModel.addElement(-1, this.block);

	}

	public void undo()
	{
		if (this.diagram != null)
			this.diagram.removeQueryBlockModel(this.block);
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