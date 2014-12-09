package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.DeleteTableCommand;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;

public class TableModelEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteTableCommand deleteCommand = new DeleteTableCommand();
		if (parent instanceof Diagram)
			deleteCommand.setDiagram((Diagram) parent);
		if (parent instanceof BlockModel)
			deleteCommand.setParentBlockModel((BlockModel) parent);
		deleteCommand.setTableModel((TableModel) getHost().getModel());
		return deleteCommand;
	}
}