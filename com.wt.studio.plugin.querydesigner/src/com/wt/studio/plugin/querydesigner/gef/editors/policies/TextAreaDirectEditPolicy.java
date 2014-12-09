package com.wt.studio.plugin.querydesigner.gef.editors.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import com.wt.studio.plugin.querydesigner.gef.commands.TextAreaEditCommand;
import com.wt.studio.plugin.querydesigner.gef.model.TextAreaModel;

public class TextAreaDirectEditPolicy extends DirectEditPolicy
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request)
	{
		CompoundCommand command = new CompoundCommand();

		String text = (String) request.getCellEditor().getValue();

		TextAreaModel note = (TextAreaModel) getHost().getModel();
		TextAreaEditCommand noteEditCommand = new TextAreaEditCommand(note, text);
		command.add(noteEditCommand);
		return command.unwrap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void showCurrentEditValue(DirectEditRequest request)
	{
	}
}