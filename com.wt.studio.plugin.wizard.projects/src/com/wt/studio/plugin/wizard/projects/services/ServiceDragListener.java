package com.wt.studio.plugin.wizard.projects.services;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.widgets.TreeItem;

public class ServiceDragListener implements DragSourceListener {
	AssembleServicePage page = null;
	
	
	public ServiceDragListener(AssembleServicePage page) {
		super();
		this.page = page;
	}

	@Override
	public void dragFinished(DragSourceEvent e) {
		// TODO Auto-generated method stub
		if (e.detail == DND.DROP_MOVE) {
			
		}

	}

	@Override
	public void dragSetData(DragSourceEvent e) {
		TreeItem item = page.getServiceTree().getSelection()[0];
		e.data = item.getText(1);

	}

	@Override
	public void dragStart(DragSourceEvent e) {
		// TODO Auto-generated method stub
		// e.doit = false;

	}

}
