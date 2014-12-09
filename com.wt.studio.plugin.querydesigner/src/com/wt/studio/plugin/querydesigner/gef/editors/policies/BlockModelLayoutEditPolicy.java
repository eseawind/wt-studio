package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.CreateAddChildBlockCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.CreatePageChildrenCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.MoveChildBlockCommand;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.FrameBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.HorizontalBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;
import com.wt.studio.plugin.querydesigner.gef.model.VerticalBlockModel;

public class BlockModelLayoutEditPolicy extends FlowLayoutEditPolicy
{

	@Override
	protected Command getCreateCommand(CreateRequest request)
	{
		BlockModel block = (BlockModel) getHost().getModel();
		if (request.getNewObject() instanceof HorizontalBlockModel) {
			CreatePageChildrenCommand command = new CreatePageChildrenCommand();
			command.setBlockModel(block);
			command.setNode((Element) request.getNewObject());
			if (getInsertionReference(request) != null) {
				command.setTarget((Element) getInsertionReference(request).getModel());
			}
			return command;
		} else if (request.getNewObject() instanceof VerticalBlockModel) {
			CreatePageChildrenCommand command = new CreatePageChildrenCommand();
			command.setBlockModel(block);
			command.setNode((Element) request.getNewObject());
			if (getInsertionReference(request) != null) {
				command.setTarget((Element) getInsertionReference(request).getModel());
			}
			return command;
		} else if (request.getNewObject() instanceof QueryBlockModel) {
			CreatePageChildrenCommand command = new CreatePageChildrenCommand();
			command.setBlockModel(block);
			command.setNode((Element) request.getNewObject());
			if (getInsertionReference(request) != null) {
				command.setTarget((Element) getInsertionReference(request).getModel());
			}
			return command;
		} else if (request.getNewObject() instanceof ChartBlockModel) {
			CreatePageChildrenCommand command = new CreatePageChildrenCommand();
			command.setBlockModel(block);
			command.setNode((Element) request.getNewObject());
			if (getInsertionReference(request) != null) {
				command.setTarget((Element) getInsertionReference(request).getModel());
			}
			return command;
		} else if (request.getNewObject() instanceof TableModel) {
			CreatePageChildrenCommand command = new CreatePageChildrenCommand();
			command.setBlockModel(block);
			command.setNode((Element) request.getNewObject());
			if (getInsertionReference(request) != null) {
				command.setTarget((Element) getInsertionReference(request).getModel());
			}
			return command;
		} else if (request.getNewObject() instanceof FrameBlockModel) {
			CreatePageChildrenCommand command = new CreatePageChildrenCommand();
			command.setBlockModel(block);
			command.setNode((Element) request.getNewObject());
			if (getInsertionReference(request) != null) {
				command.setTarget((Element) getInsertionReference(request).getModel());
			}
			return command;
		}
		return null;
	}

	@Override
	protected Command createAddCommand(EditPart child, EditPart after)
	{
		if (child != null && after != null) {
			if (child.getParent().getModel() instanceof BlockModel
					&& after.getParent().getModel() instanceof BlockModel) {
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
}