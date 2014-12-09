package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;

public class CreateFunctionModelCommand extends Command
{
	private FunctionModel func;
	private Diagram diagram;
	private Rectangle rec;
	public void setRectangle(Rectangle  constraint)
	{
		this.rec=constraint;
	}
	public void setFunctionTableModel(FunctionModel func)
	{
		this.func=func;
		
		
	}
	public void setBlock(Diagram diagram)
	{
		this.diagram=diagram;
	}
	public void execute()
	{
		this.func.setRectangle(this.rec);
		this.diagram.addFunctionModel(func);
	}
	
	public void  undo()
	{
		this.diagram.removeFunctionModel(func);
	}
	public void redo()
	{
		execute();
	}
}
