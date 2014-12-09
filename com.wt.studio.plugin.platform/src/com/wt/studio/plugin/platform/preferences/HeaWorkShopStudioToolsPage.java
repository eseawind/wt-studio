package com.wt.studio.plugin.platform.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.wt.studio.plugin.platform.Activator;

public class HeaWorkShopStudioToolsPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage
{

	/**
	 * 初始化
	 */
	public HeaWorkShopStudioToolsPage()
	{
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Hea WorkShop Studio Tools");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to
	 * manipulate various types of preferences. Each field editor knows how to save and restore
	 * itself.
	 */
	public void createFieldEditors()
	{
	}

	/**
	 * 继承方法
	 * 
	 * @param workbench
	 *            workbench
	 */
	public void init(IWorkbench workbench)
	{
	}
}
