package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.DeleteColumnGroupCommand;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;

public class ColumnGroupEditPolicy extends ComponentEditPolicy
{
	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteColumnGroupCommand command = new DeleteColumnGroupCommand();
		command.setParentModel((AbstractBlockModel) parent);
		command.setColumnGroupModel((ColumnGroupModel) getHost().getModel());
		return command;
	}

}
