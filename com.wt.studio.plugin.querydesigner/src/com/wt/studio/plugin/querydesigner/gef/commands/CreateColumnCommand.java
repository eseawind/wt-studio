package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;

public class CreateColumnCommand extends Command
{

	private Element parent;
	private ColumnModel2 col;
	private Rectangle rectangle;

	// setters

	public void setColumnModel(ColumnModel2 col)
	{
		this.col = col;
	}

	public void setParentModel(Element parent)
	{
		this.parent = parent;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Create column";
	}

	public void execute()
	{
		if (this.rectangle != null) {
			this.col.setRectangle(this.rectangle);
		}
		if (parent instanceof TableModel)
			((TableModel) parent).addColumn(this.col);
		else if (parent instanceof ColumnGroupModel)
			((ColumnGroupModel) parent).addColumn(this.col);
	}

	public void undo()
	{
		if (parent instanceof TableModel)
			((TableModel) parent).removeColumn(this.col);
		else if (parent instanceof ColumnGroupModel)
			((ColumnGroupModel) parent).removeColumn(this.col);
	}

	public void redo()
	{
		this.execute();
	}
}