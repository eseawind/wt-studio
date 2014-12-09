package com.wt.studio.plugin.querydesigner.gef.descriptor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class CTableInjectDialogDescriptor extends PropertyDescriptor
{

	public CTableInjectDialogDescriptor(Object id, String displayName)
	{
		super(id, displayName);
		// TODO Auto-generated constructor stub
	}

	public CellEditor createPropertyEditor(Composite parent)
	{
		DialogCellEditor cellEditor = new CTableDialogCellEditor(parent);
		// DialogCellEditor cellEditor = new SqlSetDialogCellEditor(parent);
		if (getValidator() != null) {
			cellEditor.setValidator(getValidator());
		}
		return cellEditor;
	}
}
