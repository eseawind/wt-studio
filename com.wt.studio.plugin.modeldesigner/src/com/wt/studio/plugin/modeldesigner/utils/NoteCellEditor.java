package com.wt.studio.plugin.modeldesigner.utils;


import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class NoteCellEditor extends TextCellEditor {

	public NoteCellEditor(Composite parent) {
		super(parent, SWT.MULTI | SWT.WRAP);
	}

}
