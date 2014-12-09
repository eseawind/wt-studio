package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;

public class ReconnectTableTargetCommand extends Command
{
	 private TableConnection connection;
	 private MOFunctionTableModel newTarget;
    private  MOFunctionTableModel oldTarget;
	 private MOFunctionTableModel source;
	 
	 public void setConnection(TableConnection connection) {
	        this.connection = connection;
	        this.source=this.connection.getSource();
	        this.oldTarget=this.connection.getTarget();
	    }
	  public void setTarget(MOFunctionTableModel target) {
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