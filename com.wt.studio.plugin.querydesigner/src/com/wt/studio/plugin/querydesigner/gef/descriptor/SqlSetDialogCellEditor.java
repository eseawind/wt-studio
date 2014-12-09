package com.wt.studio.plugin.querydesigner.gef.descriptor;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.wt.studio.plugin.querydesigner.model.SqlSet;
import com.wt.studio.plugin.querydesigner.wizard.CreateChartSqlWizard;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateSelectChartPage;

public class SqlSetDialogCellEditor extends DialogCellEditor
{
	private SqlSet sqlSet;

	public SqlSetDialogCellEditor(Composite parent)
	{
		super(parent);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow)
	{

		this.setSqlSet((SqlSet) this.getValue());
		CreateChartSqlWizard wizard = new CreateChartSqlWizard(this);
		final WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		if (getSqlSet() != null) {
			final IPageChangedListener pageChangedListener = new IPageChangedListener() {
				@Override
				public void pageChanged(PageChangedEvent event)
				{
					if (event.getSelectedPage() instanceof CreateSelectChartPage) {
						((CreateSelectChartPage) dialog.getCurrentPage()).setData(getSqlSet());
						dialog.removePageChangedListener(this);
					}
				}
			};
			dialog.addPageChangedListener(pageChangedListener);
		}
		dialog.open();
		return this.getSqlSet();

	}

	public void setSqlSet(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;
	}

	public SqlSet getSqlSet()
	{
		return this.sqlSet;
	}

}
