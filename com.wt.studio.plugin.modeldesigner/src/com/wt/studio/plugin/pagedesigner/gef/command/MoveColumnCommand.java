package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.TableControlModel;

public class MoveColumnCommand extends Command
{

	private ColumnModel beforeColumn;
	private ColumnModel afterColumn;
	private Element parent;
	public ColumnModel getBeforeColumn()
	{
		return beforeColumn;
	}
	public void setBeforeColumn(ColumnModel beforeColumn)
	{
		this.beforeColumn = beforeColumn;
	}
	public ColumnModel getAfterColumn()
	{
		return afterColumn;
	}
	public void setAfterColumn(ColumnModel afterColumn)
	{
		this.afterColumn = afterColumn;
	}
	public Element getParent()
	{
		return parent;
	}
	public void setParent(Element parent)
	{
		this.parent = parent;
	}
	public void execute()
	{
		if(parent instanceof ColumnModel)
		{
			int before=((ColumnModel)parent).getChildren().indexOf(beforeColumn);
			int after=((ColumnModel)parent).getChildren().indexOf(afterColumn);
			((ColumnModel)parent).getChildren().set(before, afterColumn);
			((ColumnModel)parent).getChildren().set(after, beforeColumn);
			((ColumnModel)parent).refresh();
		}
		else if(parent instanceof TableControlModel)
		{
			int before=((TableControlModel)parent).getChildren().indexOf(beforeColumn);
			int after=((TableControlModel)parent).getChildren().indexOf(afterColumn);
			((TableControlModel)parent).getChildren().set(before, afterColumn);
			((TableControlModel)parent).getChildren().set(after, beforeColumn);
			((TableControlModel)parent).refresh();
		}
		
	}
	public void redo()
	{
		execute();
	}
	public void undo()
	{
		execute();
	}
}
