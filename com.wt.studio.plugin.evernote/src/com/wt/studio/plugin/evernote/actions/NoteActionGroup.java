package com.wt.studio.plugin.evernote.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;

public class NoteActionGroup extends ActionGroup {
	/*
	 * 加入按钮
	 */
	public void fillActionBars(IActionBars actionBars) {
		if (actionBars == null)
			return;
//		IToolBarManager toolBar = actionBars.getToolBarManager();
//		toolBar.add(new SynsAction());
//		toolBar.add(new BooksAction());
	}

	/*
	 * 加入下拉菜单、右键弹出菜单
	 */
	public void fillContextMenu(IMenuManager menu) {
		if (menu == null)
			return;
//		menu.add(new SynsAction());
//		menu.add(new BooksAction());
	}

	private class SynsAction extends Action {
		public SynsAction() {
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_DTOOL_NEW_FASTVIEW);
			setHoverImageDescriptor(imageDesc);
			this.setToolTipText("同步");
		}

		public void run() {
		}
	}

	private class BooksAction extends Action {
		public BooksAction() {
			ImageDescriptor imageDesc = WorkbenchImages
					.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_EXPORT_WIZ);
			setHoverImageDescriptor(imageDesc);
			this.setToolTipText("笔记本");
		}

		public void run() {
		}
	}
}
