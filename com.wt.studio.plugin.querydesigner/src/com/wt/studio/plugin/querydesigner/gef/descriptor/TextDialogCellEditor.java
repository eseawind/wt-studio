package com.wt.studio.plugin.querydesigner.gef.descriptor;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class TextDialogCellEditor extends DialogCellEditor
{
	private String text = "";

	public String getText()
	{
		return text;
	}

	public void setValue(String text)
	{
		this.text = text;
	}

	protected TextDialogCellEditor(Composite parent)
	{
		super(parent);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow)
	{
		this.setValue((String) this.getValue());
		TextDialog textDialog = new TextDialog(cellEditorWindow.getShell(), this);
		textDialog.open();
		if (textDialog.getReturnCode() == textDialog.OK) {
			setValue(textDialog.getValue());
		}
		return this.getText();
	}

}
