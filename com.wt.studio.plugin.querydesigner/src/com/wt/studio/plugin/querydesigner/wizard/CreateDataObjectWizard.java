package com.wt.studio.plugin.querydesigner.wizard;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.Wizard;

import com.wt.studio.plugin.querydesigner.model.DataObjectModel;
import com.wt.studio.plugin.querydesigner.views.QueryDesignerView;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateDataObjectPageOne;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateDataObjectPageTwo;

public class CreateDataObjectWizard extends Wizard
{
	QueryDesignerView queryDesignerView;
	private DialogSettings dialogSettings = new DialogSettings(null);
	public static final String PAGE_ONE = "PAGE_ONE";
	public static final String PAGE_TWO = "PAGE_TWO";
	private CreateDataObjectPageOne createDataObjectPageOne;
	private CreateDataObjectPageTwo createDataObjectPageTwo;

	public CreateDataObjectWizard(QueryDesignerView queryDesignerView)
	{
		this.setDialogSettings(dialogSettings);
		this.queryDesignerView = queryDesignerView;
		this.createDataObjectPageOne = new CreateDataObjectPageOne(PAGE_ONE, "创建新的数据对象", null);
		this.createDataObjectPageOne.setConnectionProfile(queryDesignerView.getConnectionProfile());
		this.createDataObjectPageTwo = new CreateDataObjectPageTwo(PAGE_TWO, "Config Data Object",
				null);
		this.addPage(createDataObjectPageOne);
		this.addPage(createDataObjectPageTwo);
		this.setWindowTitle("新建数据对象");
	}

	@Override
	public boolean performFinish()
	{
		DataObjectModel dataObjectModel = ((CreateDataObjectPageTwo) this.getPage(PAGE_TWO))
				.getDataObjectModel();
		queryDesignerView.getDataObjectPanel().addItem(dataObjectModel);
		return true;
	}

	@Override
	public IDialogSettings getDialogSettings()
	{
		return super.getDialogSettings();
	}

	@Override
	public boolean canFinish()
	{
		return this.getContainer().getCurrentPage().getName().equals(PAGE_TWO);
	}
}
