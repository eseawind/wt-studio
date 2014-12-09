package com.wt.studio.plugin.querydesigner.wizard;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.Wizard;

import com.wt.studio.plugin.querydesigner.gef.descriptor.CTableDialogCellEditor;
import com.wt.studio.plugin.querydesigner.model.InjectModel;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateCTablePage;

public class CreateInjectWizard extends Wizard
{

	private DialogSettings dialogSettings = new DialogSettings(null);
	public static final String PAGE = "PAGE";
	private CTableDialogCellEditor cellEditor;
	private CreateCTablePage page;

	public CreateInjectWizard(CTableDialogCellEditor cellEditor)
	{
		this.cellEditor = cellEditor;
		this.setDialogSettings(dialogSettings);
		this.page = new CreateCTablePage(PAGE, "注入页", null);
		this.addPage(page);
		this.setWindowTitle("配置列表注入");
	}

	@Override
	public IDialogSettings getDialogSettings()
	{
		return super.getDialogSettings();
	}

	@Override
	public boolean canFinish()
	{
		return true;
	}

	@Override
	public boolean performFinish()
	{
		InjectModel model = (InjectModel) ((CreateCTablePage) this.getPage(PAGE)).getInjectModel();
		this.cellEditor.setInjectModel(model);
		return true;
	}
}
