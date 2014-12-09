package com.wt.studio.plugin.querydesigner.gef.descriptor;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.wt.studio.plugin.querydesigner.model.InjectModel;
import com.wt.studio.plugin.querydesigner.wizard.CreateInjectWizard;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateCTablePage;

public class CTableDialogCellEditor extends DialogCellEditor
{

	private InjectModel injectModel;

	public InjectModel getInjectModel()
	{
		return injectModel;
	}

	public void setInjectModel(InjectModel injectModel)
	{
		this.injectModel = injectModel;
	}

	public CTableDialogCellEditor(Composite parent)
	{
		super(parent);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow)
	{
		this.setInjectModel((InjectModel) this.getValue());
		CreateInjectWizard wizard = new CreateInjectWizard(this);
		final WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		if (getInjectModel() != null) {
			final IPageChangedListener pageChangedListener = new IPageChangedListener() {
				@Override
				public void pageChanged(PageChangedEvent event)
				{
					if (event.getSelectedPage() instanceof CreateCTablePage) {
						((CreateCTablePage) dialog.getCurrentPage())
								.setData((InjectModel) getInjectModel());
						dialog.removePageChangedListener(this);
					}
				}
			};
			dialog.addPageChangedListener(pageChangedListener);
		}
		dialog.open();
		return this.getInjectModel();
	}

}
