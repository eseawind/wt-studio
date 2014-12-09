package com.wt.studio.plugin.querydesigner.wizard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.wizard.Wizard;

import com.wt.studio.plugin.querydesigner.gef.descriptor.SqlSetDialogCellEditor;
import com.wt.studio.plugin.querydesigner.model.SqlSet;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateChartSqlPageOne;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateChartSqlPageTwo;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateConfigChartPage;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateSelectChartPage;
import com.wt.studio.plugin.querydesigner.wizard.page.CreateSqlChartPage;

public class CreateChartSqlWizard extends Wizard
{
	private DialogSettings dialogSettings = new DialogSettings(null);
	public static final String PAGE_SELECT = "PAGE_SELECT";
	public static final String PAGE_ONE = "PAGE_ONE";
	public static final String PAGE_SQL = "PAGE_SQL";
	public static final String PAGE_CONFIG = "PAGE_CONFIG";
	public static final String PAGE_TWO = "PAGE_TWO";
	private SqlSetDialogCellEditor cellEditor;
	private CreateSelectChartPage selectPage;
	private CreateChartSqlPageOne pageOne;
	private CreateChartSqlPageTwo pageTwo;
	private CreateSqlChartPage sqlPage;
	private CreateConfigChartPage conPage;
	private SqlSet sqlSet;

	private Map<String, List<String>> map = new HashMap<String, List<String>>();

	public CreateChartSqlWizard(SqlSetDialogCellEditor cellEditor)
	{

		this.cellEditor = cellEditor;
		this.setDialogSettings(dialogSettings);
		this.selectPage = new CreateSelectChartPage(PAGE_SELECT, "选择图例", null);
		// this.pageOne = new CreateChartSqlPageOne(PAGE_ONE, "配置图表SQL", null);
		// this.pageOne.setConnectionProfile(CommonEclipseUtil.getConnectionProfiles()[0]);
		// this.pageTwo = new CreateChartSqlPageTwo(PAGE_TWO, "配置SQL列", null);
		this.sqlPage = new CreateSqlChartPage(PAGE_SQL, "配置sql", null);
		this.conPage = new CreateConfigChartPage(PAGE_CONFIG, "配置图表", null);
		this.sqlPage.setConnectionProfile(CommonEclipseUtil.getConnectionProfiles()[0]);
		this.addPage(selectPage);
		// this.addPage(pageOne);
		// this.addPage(pageTwo);
		this.addPage(sqlPage);
		this.addPage(conPage);
		this.setWindowTitle("配置图表");
	}

	public CreateChartSqlWizard(SqlSet sqlSet)
	{
		this.sqlSet = sqlSet;
		this.setDialogSettings(dialogSettings);
		this.selectPage = new CreateSelectChartPage(PAGE_SELECT, "选择图例", null);
		// this.pageTwo = new CreateChartSqlPageTwo(PAGE_TWO, "配置SQL列", null);
		// this.pageOne = new CreateChartSqlPageOne(PAGE_ONE, "配置图表SQL", null);
		// this.pageOne.setConnectionProfile(CommonEclipseUtil.getConnectionProfiles()[0]);
		this.sqlPage = new CreateSqlChartPage(PAGE_SQL, "配置sql", null);
		this.conPage = new CreateConfigChartPage(PAGE_CONFIG, "配置图表", null);
		this.sqlPage.setConnectionProfile(CommonEclipseUtil.getConnectionProfiles()[0]);
		this.addPage(selectPage);
		this.addPage(sqlPage);
		this.addPage(conPage);
		// this.addPage(pageOne);
		// this.addPage(pageTwo);
		this.setWindowTitle("配置图表");
	}

	@Override
	public boolean performFinish()
	{
		SqlSet sqlSet = ((CreateConfigChartPage) this.getPage(PAGE_CONFIG)).getSqlSet();
		if (this.cellEditor != null) {
			this.cellEditor.setSqlSet(sqlSet);
			return true;
		} else {
			this.sqlSet.setChartType(sqlSet.getChartType());
			this.sqlSet.setSqlModelSet(sqlSet.getSqls());
			this.sqlSet.setName(sqlSet.getName());
			return true;
		}
	}

	public boolean canFinish()
	{
		return this.getContainer().getCurrentPage().getName().equals(PAGE_CONFIG);
	}
}
