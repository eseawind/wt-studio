package com.wt.studio.plugin.pagedesigner.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.pagedesigner.gef.command.DeleteBlockCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.DeleteColumnCommand;
import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;

public class ColumnModelEditPolicy extends ComponentEditPolicy
{
	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteColumnCommand deleteCommand = new DeleteColumnCommand();
		deleteCommand.setParent((Element) parent);
		deleteCommand.setColumn((ColumnModel) getHost().getModel());
		return deleteCommand;
	}

}
