package com.wt.studio.plugin.pagedesigner.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;


import com.wt.studio.plugin.pagedesigner.gef.command.DeleteFunctionModelCommand;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;

public class FunctionModelEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteFunctionModelCommand deleteCommand = new DeleteFunctionModelCommand();
	    deleteCommand.setElementParent((Diagram) parent);
		deleteCommand.setNode((Element) getHost().getModel());
		return deleteCommand;
	}
}
