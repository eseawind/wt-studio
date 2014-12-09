package com.wt.studio.plugin.pagedesigner.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.pagedesigner.gef.command.DeleteBlockCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.DeleteControlCommand;
import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;

public class ControlEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		Diagram diagram=(Diagram) (this.getHost().getViewer().getContents().getModel());
		VOFunctionTableModel func=diagram.getFunc();
		DeleteControlCommand deleteCommand = new DeleteControlCommand();
	    deleteCommand.setElementParent((BlockModel) parent);
		deleteCommand.setNode((Element) getHost().getModel());
		deleteCommand.setFunctionModel(func);
		return deleteCommand;
	}
}


