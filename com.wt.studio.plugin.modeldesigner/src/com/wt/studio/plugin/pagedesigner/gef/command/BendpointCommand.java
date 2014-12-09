package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;


public class BendpointCommand extends Command {

    protected int index;
    protected ColumnConnection connection;
    protected Dimension d1, d2;

    public void setConnection(ColumnConnection connection) {
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
