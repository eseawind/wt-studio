package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;

public class ReconnectTargetCommand extends Command
{
	 private ColumnConnection connection;
	 private FunctionColumnModel newTarget;
     private FunctionColumnModel oldTarget;
	 private FunctionColumnModel source;
	 
	 public void setConnection(ColumnConnection connection) {
	        this.connection = connection;
	        this.source=this.connection.getSource();
	        this.oldTarget=this.connection.getTarget();
	    }
	  public void setTarget(FunctionColumnModel target) {
	        this.newTarget = target;
	    }
	  

	  public void execute() {
	        oldTarget.removeInput(connection);
	        newTarget.addInput(connection);
	        connection.setTarget(newTarget);
	    }

	  public String getLabel() {
	        return "Reconnect Target";
	    }

	    public void redo() {
	        execute();
	    }

	    public void undo() {
	        newTarget.removeInput(connection);
	        oldTarget.addInput(connection);
	        connection.setTarget(oldTarget);
	    }

}