package com.wt.studio.plugin.modeldesigner.editor.tool;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.tools.PanningSelectionTool;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public class MovablePanningSelectionTool extends PanningSelectionTool{
	private boolean shift = false;
	
	


	@Override
	public void mouseDown(MouseEvent e, EditPartViewer viewer) {
		// TODO Auto-generated method stub
		super.mouseDown(e, viewer);
	}



	@Override
	protected boolean handleKeyDown(KeyEvent e) {
		// TODO Auto-generated method stub
		return super.handleKeyDown(e);
	}
	
	

}
