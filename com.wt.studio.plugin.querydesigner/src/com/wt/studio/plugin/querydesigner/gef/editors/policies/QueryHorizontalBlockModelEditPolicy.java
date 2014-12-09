package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.DeleteQueryHorizontalBlockModelCommand;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;

public class QueryHorizontalBlockModelEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteQueryHorizontalBlockModelCommand deleteCommand = new DeleteQueryHorizontalBlockModelCommand();
		deleteCommand.setBlock((QueryBlockModel) parent);
		deleteCommand.setNode((QueryHorizontalBlockModel) getHost().getModel());
		return deleteCommand;
	}
}