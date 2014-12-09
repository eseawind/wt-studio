package com.wt.studio.plugin.pagedesigner.gef.policy;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;


import com.wt.studio.plugin.pagedesigner.gef.command.CreateAddChildBlockCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.CreateColumnModelCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.MoveColumnCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.createAddColumnCommand;
import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.TableControlModel;

public class TableLayoutPolicy extends FlowLayoutEditPolicy
{

	@Override
	protected Command createAddCommand(EditPart before, EditPart after)
	{
		// TODO Auto-generated method stub
		if(before!=null&&after!=null)
		{
		createAddColumnCommand command=new createAddColumnCommand();
		command.setChild((ColumnModel)before.getModel());
		command.setBeforeParent((Element) before.getParent().getModel());
		command.setParent((Element) after.getParent().getModel());
		return command;
		}
		return null;
	}

	@Override
	protected Command createMoveChildCommand(EditPart before, EditPart after)
	{
		// TODO Auto-generated method stub
		if(before!=null&&after!=null)
		{
		MoveColumnCommand command=new MoveColumnCommand();
		command.setBeforeColumn((ColumnModel) before.getModel());
		command.setAfterColumn((ColumnModel) after.getModel());
		command.setParent((Element) before.getParent().getModel());
		return command;
		}
		return null;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request)
	{
		// TODO Auto-generated method stub
		if(this.getHost().getModel() instanceof TableControlModel)
		{
			TableControlModel table=(TableControlModel) this.getHost().getModel();
			CreateColumnModelCommand command=new CreateColumnModelCommand();
			command.setChild((ColumnModel)request.getNewObject());
			command.setTableParent(table);
			return command;
		}
		else if(this.getHost().getModel() instanceof ColumnModel)
		{
			ColumnModel column=(ColumnModel)this.getHost().getModel();
			CreateColumnModelCommand command=new CreateColumnModelCommand();
			command.setChild((ColumnModel)request.getNewObject());
			command.setColumnParent(column);
			return command;
		}
		else
			return null;
	}
	@Override
	protected Command getAddCommand(Request generic)
	{
		
		ChangeBoundsRequest request = (ChangeBoundsRequest) generic;
		List editParts = request.getEditParts();
			CompoundCommand command = new CompoundCommand();
     		command.setDebugLabel("Add in ConstrainedLayoutEditPolicy");//$NON-NLS-1$
			GraphicalEditPart child;
	
			for (int i = 0; i < editParts.size(); i++) {
				child = (GraphicalEditPart) editParts.get(i);
				createAddColumnCommand addCommand=new createAddColumnCommand();
				ColumnModel childElement=(ColumnModel) child.getModel();
				GraphicalEditPart beforeParent=(GraphicalEditPart) child.getParent();
				Element beforeParentModel=(Element) beforeParent.getModel();
				addCommand.setChild(childElement);
				addCommand.setBeforeParent(beforeParentModel);
				addCommand.setParent((Element) this.getHost().getModel());
				command.add(addCommand);
			}
			return command.unwrap();
		//return null;
	}

}
