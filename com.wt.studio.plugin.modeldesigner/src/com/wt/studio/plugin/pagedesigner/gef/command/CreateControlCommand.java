package com.wt.studio.plugin.pagedesigner.gef.command;

import java.util.UUID;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.HorizonBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;

public class CreateControlCommand extends Command
{
	private ControlModel control;
	private BlockModel block;
	private Rectangle rectangle;
	private VOFunctionTableModel func;
	private FunctionColumnModel column;

	// setters

	public void setControlModel(ControlModel control)
	{
		this.control = control;
	}

	public void setParentElement(BlockModel element)
	{
		this.block = element;
	}

	public void setRectangle(Rectangle rectangle)
	{
		this.rectangle = rectangle;
	}

	// ------------------------------------------------------------------------
	// Overridden from Command

	public String getLabel()
	{
		return "Create chart";
	}

	public void execute()
	{
		if (this.rectangle != null) {
			this.control.setRectangle(this.rectangle);
		}
		    
			this.block.addElement(-1,this.control);
			column=new FunctionColumnModel();
			column.setId(this.control.getId());
			column.setTitle(this.control.getName());
			column.setUuid(control.getUuid());
			if(this.func!=null)
			{
				this.func.addColumn(column);
			}
	}

	public void undo()
	{
			this.block.removeElement(this.control);
			this.func.removeColumn(column);
		
	}

	public void redo()
	{
		this.execute();
	}

	public void setFunctionModel(VOFunctionTableModel func)
	{
		// TODO Auto-generated method stub
		this.func=func;
	}

	
}
