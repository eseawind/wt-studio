package com.wt.studio.plugin.pagedesigner.gef.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotoConnection;

public class CreateTableotoConnectionCommand extends Command {

    protected TableotoConnection connection;

    protected MOFunctionTableModel source;
    protected MOFunctionTableModel target;
    protected List<String> pColumnModels;

    public FunctionModel getSource(){
    	return this.source;
    }
    public void setSource(MOFunctionTableModel source) {
        this.source = source;
    }

    public void setConnection(TableotoConnection connection) {
        this.connection = connection;
    }

    public FunctionModel getTarget(){
    	return this.target;
    }
    public void setTarget(MOFunctionTableModel target) {
        this.target = target;
    }

    //------------------------------------------------------------------------
    // Overridden from Command

    public void execute() {
    	
        connection = new TableotoConnection(source, target);
  
    }

    public String getLabel() {
        return "Create Tableoto Connection";
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