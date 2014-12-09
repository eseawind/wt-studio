package com.wt.studio.plugin.pagedesigner.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.wt.studio.plugin.pagedesigner.gef.command.CreateConnectionCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.CreateTableotnConnectionCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.CreateTableotoConnectionCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ReconnectSourceCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ReconnectTableSourceCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ReconnectTableTargetCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.ReconnectTargetCommand;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotoConnection;

public class TableotoGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
    	if(request.getNewObject() instanceof TableotoConnection)
    	{
        CreateTableotoConnectionCommand command = (CreateTableotoConnectionCommand) request.getStartCommand();
        MOFunctionTableModel target=(MOFunctionTableModel) getHost().getModel();
        TableotoConnection conn=new TableotoConnection();
        command.setConnection(conn);
        command.setTarget(target);
        return command;
    	}
    	else if(request.getNewObject() instanceof TableotnConnection)
    	{
        CreateTableotnConnectionCommand command = (CreateTableotnConnectionCommand) request.getStartCommand();
        MOFunctionTableModel target=(MOFunctionTableModel) getHost().getModel();
        TableotnConnection conn=new TableotnConnection();
        command.setConnection(conn);
        command.setTarget(target);
        return command;
    	}
    	return null;
    }

    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
    	if(request.getNewObject() instanceof TableotoConnection)
    	{	
    	CreateTableotoConnectionCommand command = new CreateTableotoConnectionCommand();
        MOFunctionTableModel source=(MOFunctionTableModel) getHost().getModel();
        command.setSource(source);
        request.setStartCommand(command);
        return command;
    	}
    	else if(request.getNewObject() instanceof TableotnConnection)
    	{	
    	CreateTableotnConnectionCommand command = new CreateTableotnConnectionCommand();
        MOFunctionTableModel source=(MOFunctionTableModel) getHost().getModel();
        command.setSource(source);
        request.setStartCommand(command);
        return command;
    	}
    	return null;
    }

    protected Command getReconnectSourceCommand(ReconnectRequest request) {

    	
		ReconnectTableSourceCommand cmd = new ReconnectTableSourceCommand();
		cmd.setConnection((TableConnection)request.getConnectionEditPart().getModel());
		cmd.setSource((MOFunctionTableModel)getHost().getModel());
		return cmd;
    }

    protected Command getReconnectTargetCommand(ReconnectRequest request) {
    	ReconnectTableTargetCommand cmd = new ReconnectTableTargetCommand();
		cmd.setConnection((TableConnection)request.getConnectionEditPart().getModel());
		cmd.setTarget((MOFunctionTableModel)getHost().getModel());
		return cmd;
    }
}