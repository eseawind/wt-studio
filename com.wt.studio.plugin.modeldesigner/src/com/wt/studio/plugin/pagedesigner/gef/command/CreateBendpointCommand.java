package com.wt.studio.plugin.pagedesigner.gef.command;

import com.wt.studio.plugin.pagedesigner.gef.model.ConnectionBendpoint;


public class CreateBendpointCommand extends BendpointCommand {

	public void execute() {
		ConnectionBendpoint cbp = new ConnectionBendpoint();
		cbp.setRelativeDimensions(d1,d2);
		connection.addBendpoint(index, cbp);
	}

	public void undo() {
		connection.removeBendpoint(index);
	}

}

