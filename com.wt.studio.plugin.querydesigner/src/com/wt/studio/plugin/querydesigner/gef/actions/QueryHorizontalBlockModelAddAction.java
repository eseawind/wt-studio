package com.wt.studio.plugin.querydesigner.gef.actions;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.wt.studio.plugin.querydesigner.gef.editors.part.QueryBlockModelPart;
import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.QueryHorizontalBlockModel;

public class QueryHorizontalBlockModelAddAction extends SelectionAction
{
	private IWorkbenchPart part;

	public QueryHorizontalBlockModelAddAction(IWorkbenchPart part)
	{
		super(part);
		setId("addQueryHorizontalBlockModel");
		setText("添加水平布局");
		this.part = part;
	}

	@Override
	public void run()
	{
		super.run();
		QueryBlockModelPart queryEditPart = (QueryBlockModelPart) getSelectedObjects().get(0);
		QueryHorizontalBlockModel child = new QueryHorizontalBlockModel();
		((AbstractBlockModel) queryEditPart.getModel()).addElement(-1,
				new QueryHorizontalBlockModel());
	}

	@Override
	protected boolean calculateEnabled()
	{
		if (getSelectedObjects().size() == 1
				&& (getSelectedObjects().get(0) instanceof QueryBlockModelPart)) {
			QueryBlockModelPart part = (QueryBlockModelPart) getSelectedObjects().get(0);
			if (part instanceof QueryBlockModelPart)
				return true;
		}
		return false;
	}
}
