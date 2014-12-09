package com.wt.studio.plugin.querydesigner.provider.label;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;

public class ColumnModel2LabelProvider extends LabelProvider implements ITableLabelProvider
{
	public static String[] colNames = { "列名", "显示名称", "显示与否", "列宽", "顺序", "对齐方式", "列格式", "列格式参数",
			"列操作", "在显示结果中隐藏", "链接", "链接target", "参数key", "参数value列", "列属组" };
	public static String[] types = { "事实", "维度" };

	@Override
	public String getColumnText(Object element, int columnIndex)
	{
		if (element instanceof ColumnModel2) {
			ColumnModel2 cm = (ColumnModel2) element;
			switch (columnIndex) {
			case 0:
				return cm.getName();
			case 1:
				return cm.getDescription();
			case 2:
				return cm.getIfshow();
			case 3:
				return cm.getWidth();
			case 4:
				return cm.getOrder();
			case 5:
				return cm.getAlign();
			case 6:
				return cm.getFormatter();
			case 7:
				return cm.getFormatterOptions();
			case 8:
				return cm.getOptions();
			case 9:
				return cm.getIfHide();
			case 10:
				return cm.getClickurl();
			case 11:
				return cm.getClickUrlTarget();
			case 12:
				return cm.getParamKey();
			case 13:
				return cm.getParamValueColumnName();
			case 14:
				return cm.getCord();
			default:
				break;
			}
		}
		return null;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex)
	{
		return null;
	}
}
