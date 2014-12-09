package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.DeleteBlockCommand;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;

public class BlockModelEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteBlockCommand deleteCommand = new DeleteBlockCommand();
		if (parent instanceof BlockModel)
			deleteCommand.setParentBlockModel((BlockModel) parent);
		deleteCommand.setNode((BlockModel) getHost().getModel());
		return deleteCommand;
	}
}