package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;
import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;

public class DeleteMyNodeCommand extends Command
{
	private BONodeModel node;
	private int index;
	private BOModelDiagram parent;
	
	public void setNode(BONodeModel node)
	{
		this.node = node;
	}

	public void setParent(BOModelDiagram parent)
	{
		this.parent = parent;
	}

	public String getLabel()
	{
		return "Delete block";
	}

	public void execute()
	{
		if(node instanceof NoteModel)
		   parent.removeNoteModel((NoteModel)node);
		if(node instanceof HdbTableModel)
		   parent.removeTableModel((HdbTableModel)node);
			
	}

	public void undo()
	{
		
		if(node instanceof NoteModel)
			   parent.addNoteModel((NoteModel)node);
		if(node instanceof HdbTableModel)
			   parent.addTableModel((HdbTableModel)node);
	}

	public void redo()
	{
		this.execute();
	}

}
