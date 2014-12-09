package com.wt.studio.plugin.querydesigner.provider.cellmodifier;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.TableItem;

import com.wt.studio.plugin.querydesigner.model.ColumnModel;
import com.wt.studio.plugin.querydesigner.provider.label.ColumnModelLabelProvider;

public class ColumnModelCellModifier implements ICellModifier
{
	private Viewer viewer;

	public ColumnModelCellModifier(Viewer viewer)
	{
		this.viewer = viewer;
	}

	public boolean canModify(Object element, String property)
	{
		return true;
	}

	public Object getValue(Object element, String property)
	{
		if (element instanceof ColumnModel) {
			ColumnModel col = (ColumnModel) element;
			if (property.equals(ColumnModelLabelProvider.colNames[0])) {
				return col.getName();
			} else if (property.equals(ColumnModelLabelProvider.colNames[1])) {
				return col.getType();
			} else if (property.equals(ColumnModelLabelProvider.colNames[2])) {
				return col.getDescription();
			}
		}
		return null;
	}

	@Override
	public void modify(Object element, String property, Object value)
	{
		if (element instanceof TableItem) {
			element = ((TableItem) element).getData();
			ColumnModel col = (ColumnModel) element;
			if (property.equals(ColumnModelLabelProvider.colNames[0])) {
				col.setName((String) value);
			} else if (property.equals(ColumnModelLabelProvider.colNames[1])) {
				col.setType((Integer) value);
			} else if (property.equals(ColumnModelLabelProvider.colNames[2])) {
				col.setDescription((String) value);
			}
			viewer.refresh();
		}
	}
}