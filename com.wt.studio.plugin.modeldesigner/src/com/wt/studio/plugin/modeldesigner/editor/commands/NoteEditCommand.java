package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;


public class NoteEditCommand  extends Command {

	private String oldText;

	private String text;

	private NoteModel note;

	public NoteEditCommand(NoteModel note, String text) {
		this.note = note;
		this.oldText = this.note.getText();
		this.text = text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		this.note.setText(text);
		
		//this.note.refreshVisuals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		this.note.setText(oldText);
		
		//this.note.refreshVisuals();
	}
}
