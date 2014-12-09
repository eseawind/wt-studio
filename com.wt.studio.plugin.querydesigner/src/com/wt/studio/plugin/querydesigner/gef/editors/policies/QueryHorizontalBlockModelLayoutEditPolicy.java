package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.CreateAddParamCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.CreateParamCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.MoveChildParamCommand;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;

public class QueryHorizontalBlockModelLayoutEditPolicy extends FlowLayoutEditPolicy
{

	protected Command getCreateCommand(CreateRequest request)
	{
		QueryHorizontalBlockModel block = (QueryHorizontalBlockModel) getHost().getModel();
		if (request.getNewObject() instanceof ParamModel) {
			CreateParamCommand command = new CreateParamCommand();
			EditPart target = getInsertionReference(request);
			if (target != null) {
				command.setTarget((Element) target.getModel());
			}
			command.setBlock(block);
			command.setParamModel((ParamModel) request.getNewObject());
			return command;
		}
		return null;
	}

	@Override
	protected Command createAddCommand(EditPart child, EditPart after)
	{
		if (child != null && after != null) {
			if (child.getParent().getModel() instanceof AbstractBlockModel
					&& after.getParent().getModel() instanceof AbstractBlockModel) {
				AbstractBlockModel childparentblock = (AbstractBlockModel) child.getParent()
						.getModel();
				AbstractBlockModel afterparentblock = (AbstractBlockModel) after.getParent()
						.getModel();
				Element childelement = (Element) child.getModel();
				Element afterelement = (Element) after.getModel();
				CreateAddParamCommand command = new CreateAddParamCommand();
				command.setChildParent(childparentblock);
				command.setAfterParent(afterparentblock);
				command.setChild(childelement);
				command.setAfter(afterelement);
				return command;
			}
		}
		return null;
	}

	@Override
	protected Command createMoveChildCommand(EditPart child, EditPart after)
	{
		// TODO Auto-generated method stub
		if (child != null && after != null) {
			QueryHorizontalBlockModel queryBlock = (QueryHorizontalBlockModel) getHost().getModel();
			if (child.getModel() instanceof ParamModel && after.getModel() instanceof ParamModel) {
				ParamModel childParam = (ParamModel) child.getModel();
				ParamModel afterParam = (ParamModel) after.getModel();
				MoveChildParamCommand command = new MoveChildParamCommand();
				command.setParentBlock(queryBlock);
				command.setChildParam(childParam);
				command.setAfterParam(afterParam);
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
			CreateAddParamCommand addCommand = new CreateAddParamCommand();
			ParamModel childElement = (ParamModel) child.getModel();
			GraphicalEditPart beforeParent = (GraphicalEditPart) child.getParent();
			Element beforeParentModel = (Element) beforeParent.getModel();
			addCommand.setChild(childElement);
			addCommand.setChildParent((AbstractBlockModel) beforeParentModel);
			addCommand.setAfterParent((AbstractBlockModel) this.getHost().getModel());
			command.add(addCommand);
		}
		return command.unwrap();
		// return null;
	}
}