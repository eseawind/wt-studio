package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;

public class ReconnectSourceCommand extends Command {
    private NodeConnection connection;

    private BONodeModel newSource;

    private BONodeModel oldSource;

    private BONodeModel target;

    //setters
    public void setConnection(NodeConnection connection) {
        this.connection = connection;
        this.target=this.connection.getTarget();
        this.oldSource=this.connection.getSource();
    }

    public void setSource(BONodeModel source) {
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