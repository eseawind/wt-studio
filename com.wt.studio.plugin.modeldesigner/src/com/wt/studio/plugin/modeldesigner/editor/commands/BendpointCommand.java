package com.wt.studio.plugin.modeldesigner.editor.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;

public class BendpointCommand extends Command {

    protected int index;
    protected NodeConnection connection;
    protected Dimension d1, d2;

    public void setConnection(NodeConnection connection) {
        this.connection = connection;
    }

    public void redo() {
        execute();
    }

    public void setRelativeDimensions(Dimension dim1, Dimension dim2) {
        d1 = dim1;
        d2 = dim2;
    }

    public void setIndex(int i) {
        index = i;
    }

}
