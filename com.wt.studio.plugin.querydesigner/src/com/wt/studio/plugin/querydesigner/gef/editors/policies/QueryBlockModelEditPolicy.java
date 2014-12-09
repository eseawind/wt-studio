package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.DeleteQueryBlockCommand;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;

public class QueryBlockModelEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteQueryBlockCommand command = new DeleteQueryBlockCommand();
		if (parent instanceof Diagram)
			command.setDiagram((Diagram) parent);
		if (parent instanceof BlockModel)
			command.setParentBlockModel((BlockModel) parent);
		command.setBlock((QueryBlockModel) getHost().getModel());
		return command;
	}
}