package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;

public class ReconnectTargetCommand extends Command
{
	 private NodeConnection connection;
	 private BONodeModel newTarget;
     private BONodeModel oldTarget;
	 private BONodeModel source;
	 
	  public void setConnection(NodeConnection connection) {
	        this.connection = connection;
	        this.source=this.connection.getSource();
	        this.oldTarget=this.connection.getTarget();
	    }
	  public void setTarget(BONodeModel target) {
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
