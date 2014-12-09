package com.wt.studio.plugin.modeldesigner.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
public class BODiagramMultiActionBarContributor extends MultiPageEditorActionBarContributor {

	private ZoomComboContributionItem zoomComboContributionItem;

	

	@Override
	public void setActivePage(IEditorPart activeEditor) {
		IActionBars actionBars = this.getActionBars();
		actionBars.clearGlobalActionHandlers();
		actionBars.getToolBarManager().removeAll();

		BODesignerEditor editor = (BODesignerEditor) activeEditor;

		BODiagramActionBarContributor activeContributor =editor.getActionBarContributor();
		if (this.zoomComboContributionItem == null) {
			this.zoomComboContributionItem = new ZoomComboContributionItem(
					this.getPage());
		}
		activeContributor.contributeToToolBar(actionBars.getToolBarManager(),
				this.zoomComboContributionItem);
		activeContributor.setActiveEditor(editor);
		ZoomComboContributionItem item = (ZoomComboContributionItem) getActionBars()
				.getToolBarManager().find(
						GEFActionConstants.ZOOM_TOOLBAR_WIDGET);
		if (item != null) {
			ZoomManager zoomManager = (ZoomManager) editor
					.getAdapter(ZoomManager.class);
			item.setZoomManager(zoomManager);
		}
		actionBars.updateActionBars();
	}
}


