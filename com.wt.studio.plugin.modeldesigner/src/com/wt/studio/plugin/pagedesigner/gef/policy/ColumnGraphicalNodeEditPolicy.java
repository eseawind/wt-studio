package com.wt.studio.plugin.pagedesigner.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;


import com.wt.studio.plugin.pagedesigner.gef.command.CreateConnectionCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ReconnectSourceCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ReconnectTargetCommand;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;

public class ColumnGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
    	if(request.getNewObject() instanceof ColumnConnection)
    	{
        CreateConnectionCommand command = (CreateConnectionCommand) request.getStartCommand();
        FunctionColumnModel target=(FunctionColumnModel) getHost().getModel();
        ColumnConnection conn=new ColumnConnection();
        command.setConnection(conn);
        command.setTarget(target);
        return command;
    	}
    	return null;
    }

    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
    	if(request.getNewObject() instanceof ColumnConnection)
    	{	
        CreateConnectionCommand command = new CreateConnectionCommand();
        FunctionColumnModel source=(FunctionColumnModel) getHost().getModel();
        command.setSource(source);
        request.setStartCommand(command);
        return command;
    	}
    	return null;
    }

    protected Command getReconnectSourceCommand(ReconnectRequest request) {

		ReconnectSourceCommand cmd = new ReconnectSourceCommand();
		cmd.setConnection((ColumnConnection)request.getConnectionEditPart().getModel());
		cmd.setSource((FunctionColumnModel)getHost().getModel());
		return cmd;
    }

    protected Command getReconnectTargetCommand(ReconnectRequest request) {
    	ReconnectTargetCommand cmd = new ReconnectTargetCommand();
		cmd.setConnection((ColumnConnection)request.getConnectionEditPart().getModel());
		cmd.setTarget((FunctionColumnModel)getHost().getModel());
		return cmd;
    }
}
