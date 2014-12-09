package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;

public class ReconnectTableSourceCommand  extends Command {
    private TableConnection connection;

    private MOFunctionTableModel newSource;

    private MOFunctionTableModel oldSource;

    private MOFunctionTableModel target;

    //setters
    public void setConnection(TableConnection connection) {
        this.connection = connection;
        this.target=this.connection.getTarget();
        this.oldSource=this.connection.getSource();
    }

    public void setSource(MOFunctionTableModel source) {
        this.newSource = source;
    }

    public void execute() {
        oldSource.removeOutput(connection);
        newSource.addOutput(connection);
        connection.setSource(newSource);
    }

    public String getLabel() {
        return "Reconnect Source";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        newSource.removeOutput(connection);
        oldSource.addOutput(connection);
        connection.setSource(oldSource);
    }
}