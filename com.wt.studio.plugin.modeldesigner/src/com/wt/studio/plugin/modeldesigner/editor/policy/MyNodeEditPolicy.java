package com.wt.studio.plugin.modeldesigner.editor.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.modeldesigner.editor.commands.DeleteMyNodeCommand;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;


public class MyNodeEditPolicy extends ComponentEditPolicy{
	
	  protected Command createDeleteCommand(GroupRequest deleteRequest) 
	  {
		Object parent = getHost().getParent().getModel();
		DeleteMyNodeCommand deleteCommand = new DeleteMyNodeCommand();
		deleteCommand.setParent((BOModelDiagram) parent);
		deleteCommand.setNode((BONodeModel) getHost().getModel());
		return deleteCommand;
	  }

}
