package com.wt.studio.plugin.querydesigner.provider.content;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

class ColumModelContentProvider implements IStructuredContentProvider
{
	private static ColumModelContentProvider instance;

	public static ColumModelContentProvider getInstance()
	{
		synchronized (ColumModelContentProvider.class) {
			if (instance == null) {
				instance = new ColumModelContentProvider();
			}
			return instance;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getElements(Object inputElement)
	{
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		}
		if (inputElement instanceof Collection) {
			return ((Collection) inputElement).toArray();
		}
		return new Object[0];
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
		// TableViewer tableViewer = (TableViewer) viewer;
		// Table table = tableViewer.getTable();
		// for (int i = 0, n = table.getColumnCount(); i < n; i++) {
		// table.getColumn(i).pack();
		// }
	}

	public void dispose()
	{
	}
}