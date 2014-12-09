package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.editpart.ColumnConnectionEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.FunctionColumnModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.FunctionTableConnectionEditPart;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;

public class DeleteTableConnectionCommand extends Command {

	private MOFunctionTableModel source;

	private MOFunctionTableModel target;

    private TableConnection connection;

	private FunctionTableConnectionEditPart editPart;
 
    //Setters
    public void setConnection(TableConnection connection) {
        this.connection = connection;
    }

    public void setSource(MOFunctionTableModel source) {
        this.source = source;
    }

    public void setTarget(MOFunctionTableModel target) {
        this.target = target;
    }

    public void execute() {
        source.removeOutput(connection);
        target.removeInput(connection);
        connection.setSource(null);
        connection.setTarget(null);
        
        }
      
    

    public String getLabel() {
        return "Delete Connection";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        connection.setSource(source);
        connection.setTarget(target);
        source.addOutput(connection);
        target.addInput(connection);
    }

	
}

