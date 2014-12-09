package com.wt.studio.plugin.modeldesigner.editor.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.modeldesigner.editor.commands.DeleteViewCommand;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.ViewModel;


public class ViewModelEditPolicy extends ComponentEditPolicy
{

	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		DeleteViewCommand deleteCommand = new DeleteViewCommand();
		deleteCommand.setParent((BOModelDiagram) parent);
		deleteCommand.setNode((ViewModel) getHost().getModel());
		return deleteCommand;
	}
}
