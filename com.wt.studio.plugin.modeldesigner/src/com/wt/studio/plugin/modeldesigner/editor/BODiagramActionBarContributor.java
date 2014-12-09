package com.wt.studio.plugin.modeldesigner.editor;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.editor.action.ZoomAdjustAction;
import com.wt.studio.plugin.modeldesigner.editor.action.ZoomAdjustRetargetAction;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;
public class BODiagramActionBarContributor  extends ActionBarContributor {

	public BODiagramActionBarContributor() {
	}

	@Override
	protected void buildActions()
	{
		this.addRetargetAction(new RetargetAction(ActionFactory.SELECT_ALL
				.getId(), "selectAll"));
		this.addRetargetAction(new UndoRetargetAction());
		this.addRetargetAction(new RedoRetargetAction());
		this.addRetargetAction(new DeleteRetargetAction());
		
		ZoomInRetargetAction zoomInAction = new ZoomInRetargetAction();
		zoomInAction.setImageDescriptor(Activator
				.getImageDescriptor(ImageResource.ZOOM_IN));
		ZoomOutRetargetAction zoomOutAction = new ZoomOutRetargetAction();
		zoomOutAction.setImageDescriptor(Activator
				.getImageDescriptor(ImageResource.ZOOM_OUT));
		this.addRetargetAction(zoomInAction);
		this.addRetargetAction(zoomOutAction);
		this.addRetargetAction(new ZoomAdjustRetargetAction());
		
	}

	@Override
	protected void declareGlobalActionKeys()
	{
		// TODO Auto-generated method stub
		addGlobalActionKey(IWorkbenchActionConstants.PRINT_EXT);
		
	}


	public void contributeToToolBar(IToolBarManager toolBarManager,
			ZoomComboContributionItem zoomComboContributionItem) {
		toolBarManager.add(this.getAction(ActionFactory.DELETE.getId()));
		toolBarManager.add(this.getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(this.getAction(ActionFactory.REDO.getId()));
		
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ZOOM_IN));
		toolBarManager.add(getActionRegistry().getAction(
				GEFActionConstants.ZOOM_OUT));
		toolBarManager.add(getActionRegistry().getAction(ZoomAdjustAction.ID));

		toolBarManager.add(zoomComboContributionItem);
		this.getPage().addSelectionListener(new ISelectionListener() {

			public void selectionChanged(IWorkbenchPart part,
					ISelection selection) {

				if (selection instanceof IStructuredSelection) {
					List selectedEditParts = ((IStructuredSelection) selection)
							.toList();

					if (!selectedEditParts.isEmpty()) {
						if (selectedEditParts.get(0) instanceof EditPart) {}
					}
				}
			}
		});
	}
}
