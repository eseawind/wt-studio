package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.GhostModel;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;

public class CreateColumnGroupCommand extends Command
{

	private Element parent;
	private ColumnGroupModel columnGroup;
	private Rectangle rectangle;
	private GhostModel ghost = new GhostModel();

	// setters

	public void setColumnGroupModel(ColumnGroupModel columnGroup)
	{
		this.columnGroup = columnGroup;
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
			this.columnGroup.setRectangle(this.rectangle);
		}
		this.columnGroup.addElement(-1, ghost);
		if (parent instanceof TableModel)
			((TableModel) parent).addColumnGroup(this.columnGroup);
		else if (parent instanceof ColumnGroupModel)
			((ColumnGroupModel) parent).addColumnGroup(this.columnGroup);
	}

	public void undo()
	{
		if (parent instanceof TableModel)
			((TableModel) parent).removeColumnGroup(this.columnGroup);
		else if (parent instanceof ColumnGroupModel)
			((ColumnGroupModel) parent).removeColumnGroup(this.columnGroup);
	}

	public void redo()
	{
		this.execute();
	}

}
