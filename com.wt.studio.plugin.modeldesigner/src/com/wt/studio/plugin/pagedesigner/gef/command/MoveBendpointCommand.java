package com.wt.studio.plugin.pagedesigner.gef.command;

import org.eclipse.draw2d.geometry.Dimension;

import com.wt.studio.plugin.pagedesigner.gef.model.ConnectionBendpoint;

public class MoveBendpointCommand extends BendpointCommand {
	private Dimension oldDim1;
	private Dimension oldDim2;

	public void setOldRelativeDimensions(Dimension d1, Dimension d2) {
		this.oldDim1 = d1;
		this.oldDim2 = d2;
	}

	public void execute() {
		//Remember old location
		ConnectionBendpoint cbp=(ConnectionBendpoint)connection.getBendpoints().get(index);
		setOldRelativeDimensions(cbp.getFirstRelativeDimension(), cbp.getSecondRelativeDimension());
		//Set new location
		connection.setBendpointRelativeDimensions(index,d1,d2);
	}

	public void undo() {
		ConnectionBendpoint cbp=(ConnectionBendpoint)connection.getBendpoints().get(index);
		cbp.setRelativeDimensions(oldDim1, oldDim2);
	}

}