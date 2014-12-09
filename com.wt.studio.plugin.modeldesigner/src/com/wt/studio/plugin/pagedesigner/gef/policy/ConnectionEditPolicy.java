package com.wt.studio.plugin.pagedesigner.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.pagedesigner.gef.command.DeleteConnectionCommand;
import com.wt.studio.plugin.pagedesigner.gef.command.DeleteTableConnectionCommand;
import com.wt.studio.plugin.pagedesigner.gef.editpart.ColumnConnectionEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.FunctionTableConnectionEditPart;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;



public class ConnectionEditPolicy extends ComponentEditPolicy{
	
    protected Command createDeleteCommand(GroupRequest deleteRequest) {
    	if(getHost() instanceof ColumnConnectionEditPart)
    	{
    		ColumnConnection conn=(ColumnConnection)getHost().getModel();
            ColumnConnectionEditPart part=(ColumnConnectionEditPart) getHost();
            DeleteConnectionCommand cmd=new DeleteConnectionCommand();
            cmd.setConnection(conn);
            cmd.setSource(conn.getSource());
            cmd.setTarget(conn.getTarget());
            cmd.setEditPart(part);
            return cmd;
    	}
    	else if(getHost() instanceof FunctionTableConnectionEditPart)
    	{
            TableConnection conn=(TableConnection)getHost().getModel();
            FunctionTableConnectionEditPart part=(FunctionTableConnectionEditPart) getHost();
            DeleteTableConnectionCommand cmd=new DeleteTableConnectionCommand();
            cmd.setConnection(conn);
            cmd.setSource(conn.getSource());
            cmd.setTarget(conn.getTarget());
           
            return cmd;
    	}
    	return null;
    }
}
