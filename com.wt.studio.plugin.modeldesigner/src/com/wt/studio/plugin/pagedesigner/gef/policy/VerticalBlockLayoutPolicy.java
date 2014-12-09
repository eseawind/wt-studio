package com.wt.studio.plugin.pagedesigner.gef.policy;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.wt.studio.plugin.pagedesigner.gef.command.ChangeChildBlockNumCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.CreateAddChildBlockCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.CreateBlockCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.CreateControlCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ResizeBlockModelCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ResizeControlModelCommand;
import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;

public class VerticalBlockLayoutPolicy  extends XYLayoutEditPolicy
{

	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child,
			Object constraint)
	{
		Rectangle rectangle = (Rectangle) constraint;
		if(child.getModel() instanceof BlockModel)
		{
		ResizeBlockModelCommand command = new ResizeBlockModelCommand();
		command.setNode((BlockModel) child.getModel());
		command.setRectangle(rectangle);
		return command;
		}
		if(child.getModel() instanceof ControlModel)
		{
		ResizeControlModelCommand command = new ResizeControlModelCommand();
		command.setNode((ControlModel) child.getModel());
		command.setRectangle(rectangle);
		return command;
		}
		return null;
	}


	@Override
	protected Command getCreateCommand(CreateRequest request)
	{
		BlockModel element = (BlockModel) getHost().getModel();
		if (request.getNewObject() instanceof BlockModel) {
			CreateBlockCommand command = new CreateBlockCommand();
			command.setElementParent(element);
			command.setNode((BlockModel) request.getNewObject());
			return command;
		} else if (request.getNewObject() instanceof ControlModel) {
			CreateControlCommand command = new CreateControlCommand();
			Diagram diagram=(Diagram) (this.getHost().getViewer().getContents().getModel());
			VOFunctionTableModel func=diagram.getFunc();
			command.setParentElement(element);
			command.setControlModel((ControlModel) request.getNewObject());
			command.setFunctionModel(func);
			return command;
		}
		else 
		     return null;
	}
	
	protected Command getMoveChildrenCommand(Request request)
	{

		EditPart target = null;
		EditPart source = null;
		BlockModel parentblock = (BlockModel) getHost().getModel();
		if (request instanceof ChangeBoundsRequest) {
			List<EditPart> editParts = ((ChangeBoundsRequest) request).getEditParts();
			Point point = ((ChangeBoundsRequest) request).getMoveDelta();
			source = editParts.get(0);
			List<EditPart> childParts = this.getHost().getChildren();
			int before = childParts.indexOf(source);
			if (point.y > 0 && before < childParts.size() - 1) {
				ChangeChildBlockNumCommand command = new ChangeChildBlockNumCommand();
				command.setParentBlock(parentblock);
				command.setBeforeNum(before);
				command.setFlag(1);
				return command;
			} else if (point.y < 0 && before > 0) {
				ChangeChildBlockNumCommand command = new ChangeChildBlockNumCommand();
				command.setParentBlock(parentblock);
				command.setBeforeNum(before);
				command.setFlag(0);
				return command;
			}
		}
		return null;
	}
	protected Command getAddCommand(Request generic)
	{
		ChangeBoundsRequest request = (ChangeBoundsRequest) generic;
			List editParts = request.getEditParts();
				CompoundCommand command = new CompoundCommand();
	     		command.setDebugLabel("Add in ConstrainedLayoutEditPolicy");//$NON-NLS-1$
				GraphicalEditPart child;
		
				for (int i = 0; i < editParts.size(); i++) {
					child = (GraphicalEditPart) editParts.get(i);
					CreateAddChildBlockCommand addCommand=new CreateAddChildBlockCommand();
					Element childElement=(Element) child.getModel();
					GraphicalEditPart beforeParent=(GraphicalEditPart) child.getParent();
					BlockModel beforeParentBlock=(BlockModel) beforeParent.getModel();
					addCommand.setChild(childElement);
					addCommand.setChildParent(beforeParentBlock);
					addCommand.setAfterParent((BlockModel) this.getHost().getModel());
					command.add(addCommand);
				}
				return command.unwrap();
	}
}
