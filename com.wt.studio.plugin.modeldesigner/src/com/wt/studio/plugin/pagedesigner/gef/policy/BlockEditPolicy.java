package com.wt.studio.plugin.pagedesigner.gef.policy;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.pagedesigner.gef.command.DeleteBlockCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.DeleteControlCommand;
import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;

public class BlockEditPolicy extends ComponentEditPolicy
{

	// ------------------------------------------------------------------------
	// Overridden from ComponentEditPolicy

	CompoundCommand command = new CompoundCommand();
	protected Command createDeleteCommand(GroupRequest request)
	{
		Object parent = getHost().getParent().getModel();
		deleteAllChildren(((BlockModel)this.getHost().getModel()));	
		DeleteBlockCommand deleteCommand = new DeleteBlockCommand();
		deleteCommand.setElementParent((Element) parent);
		deleteCommand.setNode((Element) getHost().getModel());
		command.add(deleteCommand);
		return command.unwrap();
	}

	private void deleteAllChildren(BlockModel parent)
	{
		List<Element>children=parent.getAllElement();
		for(Element child:children)
		{
		// TODO Auto-generated method stub
		if(child instanceof ControlModel)
		{
			DeleteControlCommand deleteChildrenCommand = new DeleteControlCommand();
			Diagram diagram=(Diagram) (this.getHost().getViewer().getContents().getModel());
			VOFunctionTableModel func=diagram.getFunc();
			deleteChildrenCommand.setElementParent(parent);
			deleteChildrenCommand.setFunctionModel(func);
			deleteChildrenCommand.setNode(child);
			command.add(deleteChildrenCommand);
		}
		else if(child instanceof BlockModel )
		{
			deleteAllChildren((BlockModel)child);
		}
		}
	}
}
