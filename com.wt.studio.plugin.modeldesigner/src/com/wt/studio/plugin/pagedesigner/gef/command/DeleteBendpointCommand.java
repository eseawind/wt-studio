package com.wt.studio.plugin.pagedesigner.gef.command;

import com.wt.studio.plugin.pagedesigner.gef.model.ConnectionBendpoint;



public class DeleteBendpointCommand extends BendpointCommand{
	private ConnectionBendpoint deletedBendpoint;
	public void execute() {
		deletedBendpoint = (ConnectionBendpoint)connection.getBendpoints().get(index);
		connection.removeBendpoint(index);
	}

	public void undo() {
		connection.addBendpoint(index, deletedBendpoint);
	}

}
