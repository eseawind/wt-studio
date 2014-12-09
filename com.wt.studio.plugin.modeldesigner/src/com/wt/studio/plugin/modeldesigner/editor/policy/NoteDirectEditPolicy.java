package com.wt.studio.plugin.modeldesigner.editor.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

import com.wt.studio.plugin.modeldesigner.editor.commands.MoveElementCommand;
import com.wt.studio.plugin.modeldesigner.editor.commands.NoteEditCommand;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;
public class NoteDirectEditPolicy extends DirectEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		CompoundCommand command = new CompoundCommand();
		
		String text = (String) request.getCellEditor().getValue();

		NoteModel note = (NoteModel) getHost().getModel();
		NoteEditCommand noteEditCommand = new NoteEditCommand(note, text);
		command.add(noteEditCommand);
		Rectangle rectangle=new Rectangle(note.getX(),note.getY(),-1,-1);
		MoveElementCommand scommand = new MoveElementCommand();
		scommand.setElement(note);
		scommand.setRectangle(rectangle);
		command.add(scommand);
		return command.unwrap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
	}
}
