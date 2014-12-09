package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.CreateAbstractBlockModelCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.CreateBlockCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.CreateChartBlockCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.CreateQueryBlockCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.CreateTableCommand;
import com.wt.studio.plugin.querydesigner.gef.commands.MoveElementCommand;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;

public class IXYLayoutEditPolicy extends XYLayoutEditPolicy
{

	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child,
			Object constraint)
	{
		Rectangle rectangle = (Rectangle) constraint;
		if (child.getModel() instanceof Element) {
			MoveElementCommand command = new MoveElementCommand();
			command.setElement((Element) child.getModel());
			command.setRectangle(rectangle);
			return command;
		}
		return null;
	}

	protected Command getCreateCommand(CreateRequest request)
	{
		BlockModel page = (BlockModel) getHost().getModel();
		if (request.getNewObject() instanceof BlockModel) {
			CreateBlockCommand command = new CreateBlockCommand();
			command.setBlockModel(page);
			command.setNode((BlockModel) request.getNewObject());
			return command;
		} else if (request.getNewObject() instanceof QueryBlockModel) {
			CreateQueryBlockCommand command = new CreateQueryBlockCommand();
			command.setBlockModel(page);
			command.setBlock((QueryBlockModel) request.getNewObject());
			return command;
		} else if (request.getNewObject() instanceof ChartBlockModel) {
			CreateChartBlockCommand command = new CreateChartBlockCommand();
			command.setBlockModel(page);
			command.setBlock((ChartBlockModel) request.getNewObject());
			return command;
		} else if (request.getNewObject() instanceof TableModel) {
			CreateTableCommand command = new CreateTableCommand();
			command.setBlockModel(page);
			command.setTableModel((TableModel) request.getNewObject());
			return command;
		} else if (request.getNewObject() instanceof AbstractBlockModel) {
			CreateAbstractBlockModelCommand command = new CreateAbstractBlockModelCommand();
			command.setBlockModel(page);
			command.setAbstractBlockModel((AbstractBlockModel) request.getNewObject());
			return command;
		}
		return null;
	}
}
