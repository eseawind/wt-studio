package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.HorizonBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;

public class DeleteControlCommand  extends Command
{
	private Element element;
	private int index;
	private BlockModel parentelement;
	private VOFunctionTableModel func;
	private FunctionColumnModel column;


	public void setNode(Element node)
	{
		this.element = node;
	}

	public void setElementParent(BlockModel parent)
	{
		this.parentelement = parent;
	}

	public String getLabel()
	{
		return "Delete block";
	}

	public void execute()
	{
		index=((BlockModel) this.parentelement).getAllElement().indexOf(element);
       	parentelement.removeElement(element);
       	column=func.getColumnByUuid(((ControlModel)this.element).getUuid());
       	func.removeColumn(column);
	}

	public void undo()
	{
		parentelement.addElement(index,element);
		func.addColumn(column);
	
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

