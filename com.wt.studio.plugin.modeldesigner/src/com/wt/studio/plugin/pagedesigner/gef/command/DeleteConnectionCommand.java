package com.wt.studio.plugin.pagedesigner.gef.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.pagedesigner.gef.editpart.ColumnConnectionEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.FunctionColumnModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.figure.FunctionColumnModelFigure;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;



public class DeleteConnectionCommand extends Command {

	private FunctionColumnModel source;

	private FunctionColumnModel target;

    private ColumnConnection connection;

	private ColumnConnectionEditPart editPart;
 
    //Setters
    public void setConnection(ColumnConnection connection) {
        this.connection = connection;
    }

    public void setSource(FunctionColumnModel source) {
        this.source = source;
    }

    public void setTarget(FunctionColumnModel target) {
        this.target = target;
    }

    public void execute() {
    	 FunctionColumnModelEditPart sourcePart=(FunctionColumnModelEditPart) this.editPart.getSource();
         sourcePart.getFigure().setBackgroundColor(ColorConstants.white);
         FunctionColumnModelEditPart targetPart=(FunctionColumnModelEditPart) this.editPart.getTarget();
         targetPart.getFigure().setBackgroundColor(ColorConstants.white);
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

	public void setEditPart(ColumnConnectionEditPart part)
	{
		// TODO Auto-generated method stub
		this.editPart=part;
	}
}
