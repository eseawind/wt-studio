package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.RenameBlockCommand;
import com.wt.studio.plugin.querydesigner.gef.figures.HorizontalBlockModelFigure;
import com.wt.studio.plugin.querydesigner.gef.figures.VerticalBlockModelFigure;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;

public class BlockModelDirectEditPolicy extends DirectEditPolicy
{

	// ------------------------------------------------------------------------
	// Abstract methods from DirectEditPolicy
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request)
	{
		RenameBlockCommand cmd = new RenameBlockCommand();
		cmd.setNode((BlockModel) getHost().getModel());
		cmd.setName((String) request.getCellEditor().getValue());
		return cmd;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request)
	{
		String value = (String) request.getCellEditor().getValue();
		if (getHostFigure() instanceof VerticalBlockModelFigure)
			((VerticalBlockModelFigure) getHostFigure()).getBlockModel().setName(value);
		if (getHostFigure() instanceof HorizontalBlockModelFigure)
			((HorizontalBlockModelFigure) getHostFigure()).getBlockModel().setName(value);
	}
}