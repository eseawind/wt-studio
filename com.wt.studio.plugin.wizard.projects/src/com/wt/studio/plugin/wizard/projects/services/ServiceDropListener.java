package com.wt.studio.plugin.wizard.projects.services;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.widgets.TreeItem;

/**
 * 调用顺序
 * dragEnter 
 * dragOver 
 * dragOperationChanged 
 * dragAccept 
 * drop
 * 
 * @author gl
 * 
 */
public class ServiceDropListener implements DropTargetListener {
	AssembleServicePage page = null;

	/**
	 * 
	 * 
	 * @param page
	 */
	public ServiceDropListener(AssembleServicePage page) {
		super();
		this.page = page;
	}


	public void dragEnter(DropTargetEvent e) {
		// TODO Auto-generated method stub

	}


	public void dragLeave(DropTargetEvent e) {
		// TODO Auto-generated method stub

	}


	public void dragOperationChanged(DropTargetEvent e) {
		// TODO Auto-generated method stub
		if (e.detail == DND.DROP_DEFAULT) {
			if ((e.operations & DND.DROP_COPY) != 0) {
				e.detail = DND.DROP_COPY;
			} else {
				e.detail = DND.DROP_NONE;
			}
		}

	}

	@Override
	public void dragOver(DropTargetEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drop(DropTargetEvent e) {
		// TODO Auto-generated method stub
		TreeItem item = (TreeItem)e.item;
		item.setText(3, (String)e.data);
	}

	@Override
	public void dropAccept(DropTargetEvent e) {
		// TODO Auto-generated method stub

	}

}
