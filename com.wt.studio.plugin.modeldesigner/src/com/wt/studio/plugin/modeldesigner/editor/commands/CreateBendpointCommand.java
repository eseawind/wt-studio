package com.wt.studio.plugin.modeldesigner.editor.commands;

import com.wt.studio.plugin.modeldesigner.editor.model.ConnectionBendpoint;



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
