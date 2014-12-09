package com.wt.studio.plugin.platform.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.wt.studio.plugin.platform.Activator;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. By
 * subclassing <samp>FieldEditorPreferencePage</samp>, we can use the field support built into JFace
 * that allows us to create a page that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that
 * belongs to the main plug-in class. That way, preferences can be accessed directly via the
 * preference store.
 */

public class UumPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage
{

	/**
	 * 初始化
	 */
	public UumPreferencePage()
	{
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("HEA Porject Preference");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to
	 * manipulate various types of preferences. Each field editor knows how to save and restore
	 * itself.
	 */
	public void createFieldEditors()
	{
		UUMListEditor editor = new UUMListEditor(PreferenceConstants.UUM_LIST, "UUM Address:",
				getFieldEditorParent());
		addField(editor);
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