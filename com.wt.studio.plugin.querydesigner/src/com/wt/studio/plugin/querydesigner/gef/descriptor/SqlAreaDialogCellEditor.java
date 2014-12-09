package com.wt.studio.plugin.querydesigner.gef.descriptor;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.wt.studio.plugin.querydesigner.model.SqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.TableSqlAreaModel;
import com.wt.studio.plugin.querydesigner.wizard.CreateTableSqlWizard;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateTableSqlPageOne;

public class SqlAreaDialogCellEditor extends DialogCellEditor
{
	private TableSqlAreaModel sqlAreaModel;

	public SqlAreaDialogCellEditor(Composite parent)
	{
		super(parent);
	}

	protected Object openDialogBox(Control cellEditorWindow)
	{
		this.setSqlAreaModel((TableSqlAreaModel) this.getValue());
		CreateTableSqlWizard wizard = new CreateTableSqlWizard(this);
		final WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		if (getSqlAreaModel() != null) {
			final IPageChangedListener pageChangedListener = new IPageChangedListener() {
				@Override
				public void pageChanged(PageChangedEvent event)
				{
					if (event.getSelectedPage() instanceof CreateTableSqlPageOne) {
						((CreateTableSqlPageOne) dialog.getCurrentPage())
								.setData((TableSqlAreaModel) getSqlAreaModel());
						dialog.removePageChangedListener(this);
					}
				}
			};
			dialog.addPageChangedListener(pageChangedListener);
		}
		dialog.open();
		return this.getSqlAreaModel();
	}

	public SqlAreaModel getSqlAreaModel()
	{
		return sqlAreaModel;
	}

	public void setSqlAreaModel(TableSqlAreaModel sqlAreaModel)
	{
		this.sqlAreaModel = sqlAreaModel;
	}

}