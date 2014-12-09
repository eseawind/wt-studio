package com.wt.studio.plugin.modeldesigner.editor.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
public class DeleteConnectionCommand extends Command {

	BONodeModel source;

	BONodeModel target;

    NodeConnection connection;
    List <HdbColumnModel> columnsFromTarget=new ArrayList<HdbColumnModel>();

    //Setters
    public void setConnection(NodeConnection connection) {
        this.connection = connection;
    }

    public void setSource(BONodeModel source) {
        this.source = source;
    }

    public void setTarget(BONodeModel target) {
        this.target = target;
    }

    public void execute() {
        source.removeOutput(connection);
        target.removeInput(connection);
        if(source instanceof HdbTableModel&&target instanceof HdbTableModel)
        {
        	List<HdbColumnModel> delColumns=new ArrayList<HdbColumnModel>();
            List<HdbColumnModel> sourceColumns=(List<HdbColumnModel>)((HdbTableModel) connection.getSource()).getColumns();
        	for(HdbColumnModel sourceColumn:sourceColumns)
        	{
        		for(HdbColumnModel column:connection.getFromTargetColumns())
        		{
        		    if(column.getId().equals(sourceColumn.getId()))
        	              //((TableModel)source).removeColumn(sourceColumn);
        		    	delColumns.add(sourceColumn);
        		}
        	}
        	for(HdbColumnModel column:delColumns)
        	{
        		sourceColumns.remove(column);
        		if(((HdbTableModel)source).repeatNum>0)
        		      ((HdbTableModel)source).repeatNum--;
        	}
        	//((TableModel)source).getColumns().removeAll(delColumns); 
        	((HdbTableModel)source).refresh();
        
        }
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
        for(HdbColumnModel column:connection.getFromTargetColumns())
        {
        	((HdbTableModel)source).addColumn(column);
        	//((TableModel)source).refresh();
        }
    }
}