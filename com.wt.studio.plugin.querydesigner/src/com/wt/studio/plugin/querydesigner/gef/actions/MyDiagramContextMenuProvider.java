package com.wt.studio.plugin.querydesigner.gef.actions;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;

public class MyDiagramContextMenuProvider extends ContextMenuProvider
{

	/** The editor's action registry. */
	private ActionRegistry actionRegistry;

	public MyDiagramContextMenuProvider(EditPartViewer viewer, ActionRegistry registry)
	{
		super(viewer);
		if (registry == null) {
			throw new IllegalArgumentException();
		}
		actionRegistry = registry;
	}

	public void buildContextMenu(IMenuManager menu)
	{

		// 添加用户自定义菜单项
		// GEFActionConstants.addStandardActionGroups(menu);
		menu.add(getAction("addQueryHorizontalBlockModel"));
	}

	// 过滤不需要的上下文菜单
	public IContributionItem[] getItems()
	{
		IContributionItem[] ic = super.getItems();
		filterSystemPopMenu(ic);
		return ic;
	}

	private void filterSystemPopMenu(IContributionItem[] ic)
	{
		for (int i = 0; i < ic.length; i++) {
			if (!("addQueryHorizontalBlockModel").equals(ic[i].getId()))

			{
				ic[i].setVisible(false);
			}

		}
	}

	private IAction getAction(String actionId)
	{
		return actionRegistry.getAction(actionId);
	}
}