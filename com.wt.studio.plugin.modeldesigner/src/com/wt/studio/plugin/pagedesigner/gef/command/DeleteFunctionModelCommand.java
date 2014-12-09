package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;

public class DeleteFunctionModelCommand extends Command
{
	private Element element;
	private int index;
	private Diagram diagram;


	public void setNode(Element node)
	{
		this.element = node;
	}

	public void setElementParent(Diagram diagram)
	{
		this.diagram = diagram;
	}

	public String getLabel()
	{
		return "Delete block";
	}

	public void execute()
	{
		this.diagram.removeFunctionModel((FunctionModel)element);
	}

	public void undo()
	{
		this.diagram.addFunctionModel((FunctionModel)element);
	
	}

	public void redo()
	{
		this.execute();
	}

}