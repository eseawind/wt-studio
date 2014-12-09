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

public class XYBlockLayoutPolicy extends XYLayoutEditPolicy
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
			Rectangle rectangle = (Rectangle) getConstraintFor(request);
			command.setRectangle(rectangle);
			command.setElementParent(element);
			command.setNode((BlockModel) request.getNewObject());
			return command;
		} else if (request.getNewObject() instanceof ControlModel) {
			CreateControlCommand command = new CreateControlCommand();
			Diagram diagram=(Diagram) (this.getHost().getViewer().getContents().getModel());
			VOFunctionTableModel func=diagram.getFunc();
			Rectangle rectangle = (Rectangle) getConstraintFor(request);
			command.setRectangle(rectangle);
			command.setParentElement(element);
			command.setControlModel((ControlModel) request.getNewObject());
			command.setFunctionModel(func);
			return command;
		}
		else 
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
					Point mouse=request.getLocation().translate(0, 0);
					Rectangle parentLayout=((GraphicalEditPart)this.getHost()).getFigure().getBounds();
					((GraphicalEditPart)this.getHost()).getFigure().translateToAbsolute(parentLayout);
					Rectangle layout=new Rectangle();
					layout.setX(mouse.x-parentLayout.x-50);
					layout.setY(mouse.y-parentLayout.y);
					layout.setHeight(child.getFigure().getBounds().height);
					layout.setWidth(child.getFigure().getBounds().width);
					addCommand.setRectangle(layout);
					command.add(addCommand);
				}
				return command.unwrap();
	}
}

