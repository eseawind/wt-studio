package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.MoveElementCommand;
import com.wt.studio.plugin.querydesigner.gef.model.Element;

public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy
{
	@Override
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child,
			Object constraint)
	{
		Rectangle rectangle = (Rectangle) constraint;
		MoveElementCommand command = new MoveElementCommand();
		command.setElement((Element) child.getModel());
		command.setRectangle(rectangle);
		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

}