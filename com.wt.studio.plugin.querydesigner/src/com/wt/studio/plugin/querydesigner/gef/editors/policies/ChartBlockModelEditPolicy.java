package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.DeleteChartBlockCommand;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;

public class ChartBlockModelEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteChartBlockCommand command = new DeleteChartBlockCommand();
		if (parent instanceof Diagram)
			command.setDiagram((Diagram) parent);
		if (parent instanceof BlockModel)
			command.setParentBlockModel((BlockModel) parent);
		command.setBlock((ChartBlockModel) getHost().getModel());
		return command;
	}
}
