package com.wt.studio.plugin.querydesigner.utils;

import java.util.ArrayList;
import java.util.List;

import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.gef.model.Element;

public class Utils
{
	public static int search(Object[] a, Object key)
	{
		for (int i = 0; i < a.length; i++) {
			if (key.equals(a[i])) {
				return i;
			}
		}
		return -1;
	}

	public static List<ColumnModel2> getColumnsFromGroup(ColumnGroupModel group, int num)
	{

		num++;
		List<ColumnModel2> columns = new ArrayList<ColumnModel2>();
		List<Element> elements = group.getElements();
		for (int k = 0; k < elements.size(); k++) {
			Element element = elements.get(k);
			if (element instanceof ColumnModel2) {
				((ColumnModel2) element).setColspanStyle("Y");
				((ColumnModel2) element).setHierarchy(String.valueOf(num));
				((ColumnModel2) element).setGroupTitle(group.getTitle());
				columns.add((ColumnModel2) element);
			} else if (element instanceof ColumnGroupModel) {
				List<ColumnModel2> columns1 = (getColumnsFromGroup((ColumnGroupModel) element, num));
				columns.addAll(columns1);
			}
		}
		return columns;
	}
}
