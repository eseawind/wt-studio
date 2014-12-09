package com.wt.studio.plugin.querydesigner.gef.descriptor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class TextDataPropertyDescriptor extends PropertyDescriptor
{

	public TextDataPropertyDescriptor(Object id, String displayName)
	{
		super(id, displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent)
	{
		CellEditor editor = new TextDialogCellEditor(parent);
		if (getValidator() != null)
			editor.setValidator(getValidator());
		return editor;

	}

}
