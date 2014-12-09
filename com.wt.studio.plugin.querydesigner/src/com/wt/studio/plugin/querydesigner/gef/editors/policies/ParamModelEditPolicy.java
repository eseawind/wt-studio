package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.DeleteParamCommand;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;

public class ParamModelEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteParamCommand deleteCommand = new DeleteParamCommand();
		deleteCommand.setBlock((AbstractBlockModel) parent);
		deleteCommand.setParamModel((ParamModel) getHost().getModel());
		return deleteCommand;
	}
}