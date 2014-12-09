package com.wt.studio.plugin.querydesigner.provider.label;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.wt.studio.plugin.querydesigner.model.ColumnModel;

public class ColumnModelLabelProvider extends LabelProvider implements ITableLabelProvider
{
	public static String[] colNames = { "名称", "类别", "描述" };
	public static String[] types = { "事实", "维度" };

	@Override
	public String getColumnText(Object element, int columnIndex)
	{
		ColumnModel cm = (ColumnModel) element;
		switch (columnIndex) {
		case 0:
			return cm.getName();
		case 1:
			return types[cm.getType()];
		case 2:
			return cm.getDescription();
		default:
			break;
		}
		return null;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex)
	{
		return null;
	}
}
