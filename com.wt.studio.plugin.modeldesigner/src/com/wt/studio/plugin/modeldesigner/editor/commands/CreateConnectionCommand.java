package com.wt.studio.plugin.modeldesigner.editor.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;


public class CreateConnectionCommand extends Command {

    protected NodeConnection connection;

    protected BONodeModel source;

    protected BONodeModel target;
    protected List<String> pColumnModels;

    public BONodeModel getSource(){
    	return this.source;
    }
    public void setSource(BONodeModel source) {
        this.source = source;
    }

    public void setConnection(NodeConnection connection) {
        this.connection = connection;
    }

    public BONodeModel getTarget(){
    	return this.target;
    }
    public void setTarget(BONodeModel target) {
        this.target = target;
    }

    //------------------------------------------------------------------------
    // Overridden from Command

    public void execute() {
    	if(target instanceof HdbTableModel)
    	{
    		if(pColumnModels!=null)
    		{
    		pColumnModels.clear();
    		}
    		pColumnModels=((HdbTableModel)target).getPColumns();
    	}
    	if(connection==null){
        connection = new NodeConnection(source, target);
    	}else{
    		connection.setTarget(target);
        	this.target.addInput(connection);
    		if(source instanceof HdbTableModel)
    			 connection.setSource(source,pColumnModels);
    		else
    	         connection.setSource(source);
    	    this.source.addOutput(connection);
    	
    	}
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
        if(pColumnModels!=null&&source instanceof HdbTableModel)
        {
        	for(String codeName :this.connection.getSourceColumnModels())
        	{
        	((HdbTableModel)source).removeColumnByCodeName(codeName);
        	}
        }
    }

    public boolean canExecute() {
       /* if (source.equals(target))
            return false;
            */
        return true;
    }

	public void setPColumns(List<String> pColumns)
	{
		this.pColumnModels=pColumns;
		
	}
}