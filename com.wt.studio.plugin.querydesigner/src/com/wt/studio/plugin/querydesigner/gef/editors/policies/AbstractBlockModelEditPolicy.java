package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.DeleteAbstractBlockModelCommand;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;

public class AbstractBlockModelEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteAbstractBlockModelCommand deleteCommand = new DeleteAbstractBlockModelCommand();
		deleteCommand.setParentBlockModel((BlockModel) parent);
		deleteCommand.setAbstractBlockModel((AbstractBlockModel) getHost().getModel());
		return deleteCommand;
	}
}
