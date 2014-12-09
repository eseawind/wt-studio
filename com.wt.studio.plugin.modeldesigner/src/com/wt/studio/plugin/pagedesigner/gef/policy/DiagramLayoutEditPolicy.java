package com.wt.studio.plugin.pagedesigner.gef.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.wt.studio.plugin.pagedesigner.gef.command.CreateFunctionModelCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ResizeFunctionTableCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ResizePageCommand;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;


public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy
{
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child,
			Object constraint)
	{
		
		Rectangle rectangle = (Rectangle) constraint;
		//System.out.println("得到矩形");
		if(child.getModel() instanceof ControlPageModel)
		{
		ResizePageCommand command = new ResizePageCommand();
		command.setNode((ControlPageModel) child.getModel());
		command.setRectangle(rectangle);
		return command;
		}
		else if(child.getModel() instanceof FunctionModel)
		{
			ResizeFunctionTableCommand command = new ResizeFunctionTableCommand();
			command.setNode((FunctionModel) child.getModel());
			command.setRectangle(rectangle);
			return command;
		}
		return null;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request)
	{
		Diagram diagram=(Diagram)getHost().getModel();
		if(request.getNewObject() instanceof FunctionModel)
		{
			CreateFunctionModelCommand command=new CreateFunctionModelCommand();
			command.setFunctionTableModel((FunctionModel)request.getNewObject());
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			command.setRectangle(constraint);
			command.setBlock(diagram);
			return command;
		}
		return null;
	}

}
