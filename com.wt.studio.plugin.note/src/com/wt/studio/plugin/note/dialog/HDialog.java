package com.wt.studio.plugin.note.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class HDialog extends Dialog {

	protected HDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout layout = (GridLayout) composite.getLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		layout.numColumns = 2;
		GridData gridData = (GridData) composite.getLayoutData();
		gridData.verticalIndent = 10;
		return composite;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, 0, IDialogConstants.OK_LABEL, true);
		createButton(parent, 1, IDialogConstants.CANCEL_LABEL, false);
		okButton = super.getButton(0);
		cancelButton = super.getButton(1);
	}

	protected Button okButton;
	protected Button cancelButton;

}
