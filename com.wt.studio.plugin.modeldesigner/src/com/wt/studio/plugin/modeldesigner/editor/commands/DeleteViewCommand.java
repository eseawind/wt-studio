package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;
import com.wt.studio.plugin.modeldesigner.editor.model.ViewModel;

public class DeleteViewCommand extends Command
{
	private ViewModel view;
	//private int index;
	private BOModelDiagram parent;
	
	public void setNode(ViewModel view)
	{
		this.view = view;
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
		
		  parent.removeViewModel(view);
		
			
	}

	public void undo()
	{
		
		
		 parent.addViewModel(view);
		
	}

	public void redo()
	{
		this.execute();
	}


}
