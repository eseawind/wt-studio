package com.wt.studio.plugin.easyexplore.preferences;

import com.wt.studio.plugin.easyexplore.EasyExploreActivator;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * 
 * <pre>
 * 业务名:继承类
 * 功能说明: 继承类
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class EasyExplorePreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage
{
	public static final String P_TARGET = "com.hirisun.ide.plugin.easyexplore.targetPreference";

	/**
	 * 
	 */
	public EasyExplorePreferencePage()
	{
		super(GRID);
		setPreferenceStore(EasyExploreActivator.getDefault().getPreferenceStore());
		setDescription("Set up your file explorer application.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to
	 * manipulate various types of preferences. Each field editor knows how to save and restore
	 * itself.
	 */

	public void createFieldEditors()
	{
		addField(new StringFieldEditor(P_TARGET, "&Target:", getFieldEditorParent()));

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