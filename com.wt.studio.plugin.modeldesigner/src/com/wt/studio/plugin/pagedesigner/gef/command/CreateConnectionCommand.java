package com.wt.studio.plugin.pagedesigner.gef.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;


public class CreateConnectionCommand extends Command {

    protected ColumnConnection connection;

    protected FunctionColumnModel source;

    protected FunctionColumnModel target;
    protected List<String> pColumnModels;

    public FunctionColumnModel getSource(){
    	return this.source;
    }
    public void setSource(FunctionColumnModel source) {
        this.source = source;
    }

    public void setConnection(ColumnConnection connection) {
        this.connection = connection;
    }

    public FunctionColumnModel getTarget(){
    	return this.target;
    }
    public void setTarget(FunctionColumnModel target) {
        this.target = target;
    }

    //------------------------------------------------------------------------
    // Overridden from Command

    public void execute() {
    	
        connection = new ColumnConnection(source, target);
  
    }

    public String getLabel() {
        return "Create Connection";
    }

    public void redo() {
        this.source.addOutput(this.connection);
        this.target.addInput(this.connection);
    }

    public void undo() {
        this.source.removeOutput(this.connection);
        this.target.removeInput(this.connection);
    }

    public boolean canExecute() {
        if (source.equals(target))
            return false;
        return true;
    }
}