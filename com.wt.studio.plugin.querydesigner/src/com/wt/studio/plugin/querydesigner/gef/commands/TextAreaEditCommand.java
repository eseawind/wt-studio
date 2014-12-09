package com.wt.studio.plugin.querydesigner.gef.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.TextAreaModel;

public class TextAreaEditCommand extends Command
{

	private String oldText;

	private String text;

	private TextAreaModel note;

	public TextAreaEditCommand(TextAreaModel note, String text)
	{
		this.note = note;
		this.oldText = this.note.getTextArea();
		this.text = text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute()
	{
		this.note.setTextArea(text);

		// this.note.refreshVisuals();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo()
	{
		this.note.setTextArea(oldText);

		// this.note.refreshVisuals();
	}
}
