package com.wt.studio.plugin.querydesigner.gef.descriptor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class TableModelDialogPropertyDescriptor extends PropertyDescriptor
{

	public TableModelDialogPropertyDescriptor(Object id, String displayName)
	{
		super(id, displayName);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent)
	{
		DialogCellEditor cellEditor = new SqlAreaDialogCellEditor(parent);
		// DialogCellEditor cellEditor = new SqlSetDialogCellEditor(parent);
		if (getValidator() != null) {
			cellEditor.setValidator(getValidator());
		}
		return cellEditor;
	}
}
