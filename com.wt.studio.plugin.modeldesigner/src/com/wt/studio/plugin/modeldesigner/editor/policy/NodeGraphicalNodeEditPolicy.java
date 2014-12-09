package com.wt.studio.plugin.modeldesigner.editor.policy;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.wt.studio.plugin.modeldesigner.editor.commands.CreateConnectionCommand;
import com.wt.studio.plugin.modeldesigner.editor.commands.ReconnectSourceCommand;
import com.wt.studio.plugin.modeldesigner.editor.commands.ReconnectTargetCommand;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;


public class NodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        CreateConnectionCommand command = (CreateConnectionCommand) request.getStartCommand();
        BONodeModel target=(BONodeModel) getHost().getModel();
        if(target instanceof HdbTableModel)
        {
        	//List <ColumnModel> pColumns=((TableModel)target).getPColumns();
        	NodeConnection conn=new NodeConnection();
        	command.setConnection(conn);
        }
        command.setTarget(target);
        return command;
    }

    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        CreateConnectionCommand command = new CreateConnectionCommand();
        BONodeModel source=(BONodeModel) getHost().getModel();
        command.setSource(source);
        request.setStartCommand(command);
        return command;
    }

    protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ReconnectSourceCommand cmd = new ReconnectSourceCommand();
		cmd.setConnection((NodeConnection)request.getConnectionEditPart().getModel());
		cmd.setSource((BONodeModel)getHost().getModel());
		return cmd;
    }

    protected Command getReconnectTargetCommand(ReconnectRequest request) {
    	ReconnectTargetCommand cmd = new ReconnectTargetCommand();
		cmd.setConnection((NodeConnection)request.getConnectionEditPart().getModel());
		cmd.setTarget((BONodeModel)getHost().getModel());
		return cmd;
    }
}