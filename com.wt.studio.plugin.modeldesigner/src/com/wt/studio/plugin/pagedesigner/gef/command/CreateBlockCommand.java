package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;
import com.wt.studio.plugin.pagedesigner.gef.model.GhostElement;
import com.wt.studio.plugin.pagedesigner.gef.model.HorizonBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;


public class CreateBlockCommand extends Command
{

	private Element element;
	private Rectangle rectangle;
	private BlockModel blockParent;
	private GhostElement ghost=new GhostElement();
  
	// setters



	public void setNode(Element node)
	{
		this.element = node;
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
		if (this.element instanceof BlockModel)  {
			((BlockModel)this.element).addElement(-1,ghost);
		}
		if (this.rectangle != null) {
			this.element.setRectangle(rectangle);
		}
			this.blockParent.addElement(-1,this.element);
	}

	public void undo()
	{
		
			this.blockParent.removeElement(this.element);
		
	}

	public void redo()
	{
		this.execute();
	}

	public void setElementParent(BlockModel element)
	{
		this.blockParent = element;
	}
}
