package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;

public class DeleteAbstractBlockModelCommand extends Command
{

	private AbstractBlockModel frameModel;
	private BlockModel parentblockmodel;

	public void setParentBlockModel(BlockModel pblock)
	{
		this.parentblockmodel = pblock;
	}

	public void setAbstractBlockModel(AbstractBlockModel frameModel)
	{
		this.frameModel = frameModel;
	}

	public String getLabel()
	{
		return "Delete block";
	}

	public void execute()
	{
		if (this.parentblockmodel != null)
			this.parentblockmodel.removeElement(frameModel);
	}

	public void undo()
	{
		if (this.parentblockmodel != null)
			this.parentblockmodel.addElement(-1, frameModel);
	}

	public void redo()
	{
		this.execute();
	}
}
