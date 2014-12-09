package com.wt.studio.plugin.querydesigner.provider.cellmodifier;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.TableItem;

import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.provider.label.ColumnModel2LabelProvider;
import com.wt.studio.plugin.querydesigner.provider.model.DirtyModel;
import com.wt.studio.plugin.querydesigner.utils.Utils;

public class ColumnModel2CellModifier implements ICellModifier
{
	private Viewer viewer;
	private DirtyModel isDirty;

	public ColumnModel2CellModifier(Viewer viewer, DirtyModel isDirty)
	{
		this.viewer = viewer;
		this.isDirty = isDirty;
	}

	public boolean canModify(Object element, String property)
	{
		return true;
	}

	public Object getValue(Object element, String property)
	{
		if (element instanceof ColumnModel2) {
			ColumnModel2 col = (ColumnModel2) element;
			if (property.equals(ColumnModel2LabelProvider.colNames[0])) {
				return col.getName();
			} else if (property.equals(ColumnModel2LabelProvider.colNames[1])) {
				return col.getDescription();
			} else if (property.equals(ColumnModel2LabelProvider.colNames[2])) {
				return Utils.search(ColumnModel2.LABEL_YES_NO, col.getIfshow());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[3])) {
				return col.getWidth();
			} else if (property.equals(ColumnModel2LabelProvider.colNames[4])) {
				return col.getOrder();
			} else if (property.equals(ColumnModel2LabelProvider.colNames[5])) {
				return Utils.search(ColumnModel2.LABEL_ALIGNS, col.getAlign());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[6])) {
				return col.getFormatter();
			} else if (property.equals(ColumnModel2LabelProvider.colNames[7])) {
				return col.getFormatterOptions();
			} else if (property.equals(ColumnModel2LabelProvider.colNames[8])) {
				return Utils.search(ColumnModel2.LABEL_OPERATIONS, col.getOptions());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[9])) {
				return Utils.search(ColumnModel2.LABEL_YES_NO, col.getIfHide());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[10])) {
				return col.getClickurl();
			} else if (property.equals(ColumnModel2LabelProvider.colNames[11])) {
				return Utils.search(ColumnModel2.LABEL_TARGETS, col.getClickUrlTarget());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[12])) {
				return col.getParamKey();
			} else if (property.equals(ColumnModel2LabelProvider.colNames[13])) {
				return col.getParamValueColumnName();
			} else if (property.equals(ColumnModel2LabelProvider.colNames[14])) {
				return col.getCord();
			}
		}
		return null;
	}

	@Override
	public void modify(Object element, String property, Object value)
	{
		this.isDirty.setDirty(true);
		if (element instanceof TableItem) {
			element = ((TableItem) element).getData();
			ColumnModel2 col = (ColumnModel2) element;
			if (property.equals(ColumnModel2LabelProvider.colNames[0])) {
				col.setName((String) value);
			} else if (property.equals(ColumnModel2LabelProvider.colNames[1])) {
				col.setDescription((String) value);
			} else if (property.equals(ColumnModel2LabelProvider.colNames[2])) {
				if ((Integer) value == -1) {
					value = 0;
				}
				col.setIfshow(ColumnModel2.LABEL_YES_NO[(Integer) value]);
			} else if (property.equals(ColumnModel2LabelProvider.colNames[3])) {
				col.setWidth(value.toString());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[4])) {
				col.setOrder(value.toString());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[5])) {
				if ((Integer) value == -1) {
					value = 0;
				}
				col.setAlign(ColumnModel2.LABEL_ALIGNS[(Integer) value]);
			} else if (property.equals(ColumnModel2LabelProvider.colNames[6])) {
				col.setFormatter(value.toString());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[7])) {
				col.setFormatterOptions(value.toString());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[8])) {
				if ((Integer) value == -1) {
					value = 0;
				}
				col.setOptions(ColumnModel2.LABEL_OPERATIONS[(Integer) value]);
			} else if (property.equals(ColumnModel2LabelProvider.colNames[9])) {
				if ((Integer) value == -1) {
					value = 0;
				}
				col.setIfHide(ColumnModel2.LABEL_YES_NO[(Integer) value]);
			} else if (property.equals(ColumnModel2LabelProvider.colNames[10])) {
				col.setClickurl(value.toString());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[11])) {
				col.setClickUrlTarget(ColumnModel2.LABEL_TARGETS[(Integer) value]);
			} else if (property.equals(ColumnModel2LabelProvider.colNames[12])) {
				col.setParamKey(value.toString());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[13])) {
				col.setParamValueColumnName(value.toString());
			} else if (property.equals(ColumnModel2LabelProvider.colNames[14])) {
				col.setCord(value.toString());
			}
			viewer.refresh();
		}
	}
}