package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;

public class ReconnectSourceCommand extends Command {
    private ColumnConnection connection;

    private FunctionColumnModel newSource;

    private FunctionColumnModel oldSource;

    private FunctionColumnModel target;

    //setters
    public void setConnection(ColumnConnection connection) {
        this.connection = connection;
        this.target=this.connection.getTarget();
        this.oldSource=this.connection.getSource();
    }

    public void setSource(FunctionColumnModel source) {
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