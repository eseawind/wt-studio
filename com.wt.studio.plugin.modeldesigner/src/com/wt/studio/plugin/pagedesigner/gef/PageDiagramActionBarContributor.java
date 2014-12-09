package com.wt.studio.plugin.pagedesigner.gef;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

public class PageDiagramActionBarContributor extends ActionBarContributor {

	
	@Override
	protected void buildActions()
	{
		addRetargetAction(new RetargetAction(ActionFactory.SELECT_ALL
				.getId(), "selectAll"));
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
	}

	@Override
	protected void declareGlobalActionKeys()
	{
		addGlobalActionKey(IWorkbenchActionConstants.PRINT_EXT);

	}

	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager)
	{
		super.contributeToToolBar(toolBarManager);
		toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
	}

}
