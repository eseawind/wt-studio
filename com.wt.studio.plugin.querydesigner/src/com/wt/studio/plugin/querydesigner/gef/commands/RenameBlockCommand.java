package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;

public class RenameBlockCommand extends Command
{

	private BlockModel node;
	private String newName;
	private String oldName;

	// setters

	public void setName(String name)
	{
		this.newName = name;
	}

	public void setNode(BlockModel node)
	{
		this.node = node;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Rename Node";
	}

	public void execute()
	{
		oldName = this.node.getName();
		this.node.setName(newName);
	}

	public void undo()
	{
		this.node.setName(oldName);
	}

	public void redo()
	{
		this.node.setName(newName);
	}
}