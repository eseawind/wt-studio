package com.wt.studio.plugin.modeldesigner.editor.handle;

import org.eclipse.draw2d.Cursors;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.MoveHandle;

import com.wt.studio.plugin.modeldesigner.editor.figure.NodeLineBorder;

public class NodeMoveHandle  extends MoveHandle {

	public NodeMoveHandle(GraphicalEditPart owner) {
		super(owner);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initialize() {
		setOpaque(false);
		setBorder(new NodeLineBorder());
		setCursor(Cursors.SIZEALL);
	}
}