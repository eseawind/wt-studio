package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;
import com.wt.studio.plugin.pagedesigner.gef.model.HorizonBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;

public class DeleteBlockCommand extends Command
{
	private Element element;
	private int index;
	private Element parentelement;
	
	public void setNode(Element node)
	{
		this.element = node;
	}

	public void setElementParent(Element parent)
	{
		this.parentelement = parent;
	}

	public String getLabel()
	{
		return "Delete block";
	}

	public void execute()
	{
		 if (this.parentelement != null){
			index=((BlockModel) this.parentelement).getAllElement().indexOf(element);
			((BlockModel)this.parentelement).removeElement(element);
		}
			
	}

	public void undo()
	{
		
		if (this.parentelement != null)
		{
		    ((BlockModel)this.parentelement).addElement(index,element);	
		}
	}

	public void redo()
	{
		this.execute();
	}

}
