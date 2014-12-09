package com.wt.studio.plugin.querydesigner.gef.descriptor;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.querydesigner.gef.model.HtmlAreaModel;

public class HtmlDialog extends Dialog
{

	protected HtmlDialog(IShellProvider parentShell)
	{
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	private String value;
	private Text text;
	private HtmlAreaModel model;

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public HtmlDialog(Shell parentShell, HtmlAreaModel model)
	{
		super(parentShell);
		this.model = model;
		// TODO Auto-generated constructor stub
	}

	protected Point getInitialSize()
	{
		return new Point(600, 600);
	}

	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("信息输入框");
	}

	protected Control createDialogArea(Composite parent)
	{
		Composite comp = (Composite) super.createDialogArea(parent);
		comp.setBounds(0, 0, 600, 600);
		comp.setLayout(new FillLayout());
		text = new Text(comp, SWT.BORDER | SWT.WRAP);
		value = this.model.getTextArea();
		text.setText(model.getTextArea());
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0)
			{
				// TODO Auto-generated method stub
				value = text.getText();
			}

		});
		return parent;

	}

	protected void createButtonsForButtonBar(Composite parent)
	{
		super.createButtonsForButtonBar(parent);
		Button b = this.getButton(OK);
		b.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent eve)
			{

				model.setTextArea(value);

			}
		});
	}
}
