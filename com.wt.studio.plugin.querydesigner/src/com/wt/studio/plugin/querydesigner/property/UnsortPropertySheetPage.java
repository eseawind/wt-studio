package com.wt.studio.plugin.querydesigner.property;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.properties.UndoablePropertySheetPage;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetSorter;

public class UnsortPropertySheetPage extends UndoablePropertySheetPage
{
	public UnsortPropertySheetPage(CommandStack commandStack, IAction undoAction, IAction redoAction)
	{
		super(commandStack, undoAction, redoAction);
		setSorter(new PropertySheetSorter() {
			@Override
			public int compareCategories(String categoryA, String categoryB)
			{
				return 0;
			}

			@Override
			public int compare(IPropertySheetEntry entryA, IPropertySheetEntry entryB)
			{
				return 0;
			}

			@Override
			public void sort(IPropertySheetEntry[] entries)
			{
				// do nothing
			}
		});
	}
}
