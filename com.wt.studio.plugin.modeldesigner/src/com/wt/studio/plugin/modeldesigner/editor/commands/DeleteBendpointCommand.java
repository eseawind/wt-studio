package com.wt.studio.plugin.modeldesigner.editor.commands;

import com.wt.studio.plugin.modeldesigner.editor.model.ConnectionBendpoint;


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
