package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;

public class DeleteParamCommand extends Command
{
	private AbstractBlockModel block;
	private ParamModel param;
	private int beforeNum;

	public void setBlock(AbstractBlockModel queryBlockModel)
	{
		this.block = queryBlockModel;
	}

	public void setParamModel(ParamModel tableModel)
	{
		this.param = tableModel;
	}

	public String getLabel()
	{
		return "Delete param";
	}

	public void execute()
	{

		this.beforeNum = this.block.getElements().indexOf(param);
		this.block.removeElement(param);

	}

	public void undo()
	{
		this.block.addElement(beforeNum, param);
	}

	public void redo()
	{
		this.execute();
	}
}