package com.wt.studio.plugin.querydesigner.wizard;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.Wizard;

import com.wt.studio.plugin.querydesigner.gef.descriptor.SqlAreaDialogCellEditor;
import com.wt.studio.plugin.querydesigner.model.TableSqlAreaModel;
import com.wt.studio.plugin.querydesigner.provider.model.DirtyModel;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateTableSqlPageOne;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateTableSqlPageTwo;

public class CreateTableSqlWizard extends Wizard
{
	private DialogSettings dialogSettings = new DialogSettings(null);
	public static final String PAGE_ONE = "PAGE_ONE";
	public static final String PAGE_TWO = "PAGE_TWO";
	public static final String PAGE_THREE = "PAGE_THREE";
	private SqlAreaDialogCellEditor cellEditor;
	private CreateTableSqlPageOne pageOne;
	private CreateTableSqlPageTwo pageTwo;
	private TableSqlAreaModel tableSql;

	public CreateTableSqlWizard(SqlAreaDialogCellEditor cellEditor)
	{
		this.cellEditor = cellEditor;
		this.setDialogSettings(dialogSettings);
		this.pageOne = new CreateTableSqlPageOne(PAGE_ONE, "配置列表SQL", null);
		this.pageOne.setConnectionProfile(CommonEclipseUtil.getConnectionProfiles()[0]);
		this.pageTwo = new CreateTableSqlPageTwo(PAGE_TWO, "配置SQL列", null);
		this.addPage(pageOne);
		this.addPage(pageTwo);
		this.setWindowTitle("配置列表");
	}

	public CreateTableSqlWizard(TableSqlAreaModel tableSql)
	{
		this.tableSql = tableSql;
		this.setDialogSettings(dialogSettings);
		this.pageOne = new CreateTableSqlPageOne(PAGE_ONE, "配置列表SQL", null);
		this.pageOne.setConnectionProfile(CommonEclipseUtil.getConnectionProfiles()[0]);
		this.pageTwo = new CreateTableSqlPageTwo(PAGE_TWO, "配置SQL列", null);
		this.addPage(pageOne);
		this.addPage(pageTwo);
		this.setWindowTitle("配置列表");
	}

	@Override
	public boolean performFinish()
	{
		TableSqlAreaModel model = (TableSqlAreaModel) ((CreateTableSqlPageTwo) this
				.getPage(PAGE_TWO)).getSqlAreaModel();
		if (this.cellEditor != null) {
			DirtyModel isDirty = (DirtyModel) ((CreateTableSqlPageTwo) this.getPage(PAGE_TWO))
					.getIsDirty();
			if (isDirty.isDirty()) {
				TableSqlAreaModel newModel = new TableSqlAreaModel();
				newModel.setCms(model.getCms());
				newModel.setSqlArea(model.getSqlArea());
				newModel.setSqlName(model.getSqlName());
				this.cellEditor.setSqlAreaModel(newModel);
			} else {
				this.cellEditor.setSqlAreaModel(model);
			}
		} else {
			this.tableSql.setCms(model.getCms());
			this.tableSql.setSqlArea(model.getSqlArea());
			this.tableSql.setSqlName(model.getSqlName());
		}
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
