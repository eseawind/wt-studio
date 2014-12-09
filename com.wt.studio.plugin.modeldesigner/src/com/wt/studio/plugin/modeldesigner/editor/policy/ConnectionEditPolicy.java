package com.wt.studio.plugin.modeldesigner.editor.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wt.studio.plugin.modeldesigner.editor.commands.DeleteConnectionCommand;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;


public class ConnectionEditPolicy extends ComponentEditPolicy{
	
    protected Command createDeleteCommand(GroupRequest deleteRequest) {
    	
        NodeConnection conn=(NodeConnection)getHost().getModel();
        DeleteConnectionCommand cmd=new DeleteConnectionCommand();
        cmd.setConnection(conn);
        cmd.setSource(conn.getSource());
        cmd.setTarget(conn.getTarget());
        return cmd;
    }
}
