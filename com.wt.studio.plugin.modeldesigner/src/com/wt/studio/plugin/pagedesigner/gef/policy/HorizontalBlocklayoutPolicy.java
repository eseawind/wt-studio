package com.wt.studio.plugin.pagedesigner.gef.policy;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.wt.studio.plugin.pagedesigner.gef.command.CreateAddChildBlockCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.CreateBlockCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.CreateControlCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.MoveChildBlockCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ResizeBlockModelCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ResizeControlModelCommand;
import com.wt.studio.plugin.pagedesigner.gef.editpart.ControlModelPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.HorizonBlockModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.VerticalBlockModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.XYBlockModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
public class HorizontalBlocklayoutPolicy extends FlowLayoutEditPolicy
{
	
	public Command getCommand(Request request) {
	    if (REQ_RESIZE_CHILDREN.equals(request.getType())) {
	    	EditPart modelPart=(EditPart) ((ChangeBoundsRequest)request).getEditParts().get(0);
	    	if(modelPart instanceof ControlModelPart)
  	    	{
  	    	ResizeControlModelCommand cmd = new ResizeControlModelCommand();
  	    	
	    	ControlModelPart part=(ControlModelPart)modelPart;
	    	ControlModel control =(ControlModel)part.getModel();
	    	Rectangle before1=control.getRectangle();
	    	Rectangle before=part.getFigure().getBounds(); 
	    	Rectangle after=new Rectangle();
	    	Point start=new Point(before.x,before.y);
	    	after.setX(before1.x);
	    	after.setY(before1.y);
	    	Point end=((ChangeBoundsRequest)request).getMouseLocation();
	    	after.setSize(end.x-start.x,before.height);
	    	cmd.setNode(control);
	    	cmd.setRectangle(after);
	        return cmd;
  	    	}
	    	else if((modelPart instanceof HorizonBlockModelEditPart)||(modelPart instanceof VerticalBlockModelEditPart)
	    			||(modelPart instanceof XYBlockModelEditPart))
	    	{
	    		AbstractGraphicalEditPart part=(AbstractGraphicalEditPart) modelPart;
	    		ResizeBlockModelCommand cmd=new ResizeBlockModelCommand();
	    		BlockModel block=(BlockModel) part.getModel();
	    		Rectangle before1=block.getRectangle();
		    	Rectangle before=part.getFigure().getBounds(); 
		    	Rectangle after=new Rectangle();
		    	Point start=new Point(before.x,before.y);
		    	after.setX(before1.x);
		    	after.setY(before1.y);
		    	Point end=((ChangeBoundsRequest)request).getMouseLocation();
		    	after.setSize(end.x-start.x,before.height);
		    	cmd.setNode(block);
		    	cmd.setRectangle(after);
		        return cmd;
	    	}
	    }
	    return super.getCommand(request);
	}

	@Override
	protected Command createAddCommand(EditPart child, EditPart after)
	{
		if (child != null && after != null) {
			BlockModel childparentblock = (BlockModel) child.getParent().getModel();
			BlockModel afterparentblock = (BlockModel) after.getParent().getModel();
			Element childelement = (Element) child.getModel();
			Element afterelement = (Element) after.getModel();
			CreateAddChildBlockCommand command = new CreateAddChildBlockCommand();
			command.setChildParent(childparentblock);
			command.setAfterParent(afterparentblock);
			command.setChild(childelement);
			command.setAfter(afterelement);

			return command;
		}
		return null;
	}

	@Override
	protected Command createMoveChildCommand(EditPart child, EditPart after)
	{
		if (child != null && after != null) {

			BlockModel parentblock = (BlockModel) getHost().getModel();
			Element afterblock = null;
			Element childblock = (Element) child.getModel();
			if (after != null)
				afterblock = (Element) after.getModel();
			MoveChildBlockCommand command = new MoveChildBlockCommand();
			command.setParentBlock(parentblock);
			command.setChildElement(childblock);
			command.setAfterElement(afterblock);
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
			Diagram diagram=(Diagram) (this.getHost().getViewer().getContents().getModel());
			VOFunctionTableModel func=diagram.getFunc();
			CreateControlCommand command = new CreateControlCommand();
			command.setParentElement(element);
			command.setControlModel((ControlModel) request.getNewObject());
			command.setFunctionModel(func);
			return command;
		}
		else 
		     return null;
	}

	protected EditPolicy createChildEditPolicy(EditPart child) {
	    ResizableEditPolicy policy = new ResizableEditPolicy();
	    policy.setResizeDirections(PositionConstants.EAST_WEST);
	    return policy;
	}
}
