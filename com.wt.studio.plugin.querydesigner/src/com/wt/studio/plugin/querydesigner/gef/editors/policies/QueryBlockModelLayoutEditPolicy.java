package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.CreateAddParamCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.CreateParamCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.MoveChildParamCommand;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;

public class QueryBlockModelLayoutEditPolicy extends FlowLayoutEditPolicy
{

	protected Command getCreateCommand(CreateRequest request)
	{
		QueryBlockModel block = (QueryBlockModel) getHost().getModel();
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
		// TODO Auto-generated method stub
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
			QueryBlockModel queryBlock = (QueryBlockModel) getHost().getModel();

			Element childParam = (Element) child.getModel();
			Element afterParam = (Element) after.getModel();
			MoveChildParamCommand command = new MoveChildParamCommand();
			command.setParentBlock(queryBlock);
			command.setChildParam(childParam);
			command.setAfterParam(afterParam);
			return command;

		}
		return null;
	}

}