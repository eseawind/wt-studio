package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;

public class CreateNoteCommand extends Command
{
	private BOModelDiagram diagram;
	private NoteModel noteModel;
	private Rectangle rectangle;

	// setters

	public void setDiagram(BOModelDiagram diagram)
	{
		this.diagram = diagram;
	}

	public void setNode(NoteModel node)
	{
		this.noteModel = node;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Create Node";
	}

	public void execute()
	{
		if (this.rectangle != null) {
			this.noteModel.setRectangle(rectangle);
		}
		this.diagram.addNoteModel(this.noteModel);
	}

	public void undo()
	{
		this.diagram.removeNoteModel(this.noteModel);
	}

	public void redo()
	{
		this.execute();
	}
	
}

