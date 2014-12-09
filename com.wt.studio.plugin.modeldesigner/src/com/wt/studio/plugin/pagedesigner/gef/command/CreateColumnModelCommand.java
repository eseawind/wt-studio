package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableControlModel;

public class CreateColumnModelCommand extends Command
{

	private ColumnModel child;
	private TableControlModel tableParent;
	private ColumnModel columnParent;
	public ColumnModel getChild()
	{
		return child;
	}
	public void setChild(ColumnModel child)
	{
		this.child = child;
	}
	public TableControlModel getTableParent()
	{
		return tableParent;
	}
	public void setTableParent(TableControlModel tableParent)
	{
		this.tableParent = tableParent;
	}
	public ColumnModel getColumnParent()
	{
		return columnParent;
	}
	public void setColumnParent(ColumnModel columnParent)
	{
		this.columnParent = columnParent;
	}
	 public void execute() {
	    
		 child.setRectangle(new Rectangle(0,0,50,100));
	    if(this.tableParent!=null)
	       tableParent.addColumn(child);
	    else if(this.columnParent!=null)
	       columnParent.addColumn(child);
	    }

	    public String getLabel() {
	        return "Create ColumnModel";
	    }

	    public void redo() {
	    	execute();
	    }

	    public void undo() 
	    {	
	    	  if(this.tableParent!=null)
		      {
		    	  tableParent.removeColumn(child);
		      }
		      else if(this.columnParent!=null)
		      {
		    	  columnParent.removeColumn(child);
		      }
	    }	
}
