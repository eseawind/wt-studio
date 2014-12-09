package com.wt.studio.plugin.querydesigner.provider.content;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

class ColumModel2ContentProvider implements IStructuredContentProvider
{
	private static ColumModel2ContentProvider instance;

	public static ColumModel2ContentProvider getInstance()
	{
		synchronized (ColumModel2ContentProvider.class) {
			if (instance == null) {
				instance = new ColumModel2ContentProvider();
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

	}

	public void dispose()
	{
	}
}