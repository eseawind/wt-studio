package com.wt.studio.plugin.tools.views.tools;

import java.io.UnsupportedEncodingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class URLControl
{
	public static void init(TabFolder tabFolder)
	{

		TabItem urlItem = new TabItem(tabFolder, SWT.NONE);
		urlItem.setText("URL编解码");
		Composite container = new Composite(tabFolder, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		container.setLayout(gl);
		urlItem.setControl(container);

		final Text textSource = new Text(container, SWT.MULTI | SWT.V_SCROLL
				| SWT.BORDER | SWT.WRAP);
		setControlProperty(textSource, true, 1);

		Group gpTable = new Group(container, SWT.NONE);
		GridData gdConn1 = new GridData();
		gdConn1.grabExcessVerticalSpace = true;
		gdConn1.minimumWidth = 150;
		gpTable.setLayoutData(gdConn1);
		GridLayout grid = new GridLayout();
		grid.numColumns = 2;
		gpTable.setLayout(grid);
		final Combo combo = new Combo(gpTable, SWT.NONE | SWT.READ_ONLY);
		combo.add("UTF-8", 0);
		combo.add("GB2312", 1);
		combo.select(0);
		setControlProperty(combo, 2);
		Button btnEncode = new Button(gpTable, 0);
		btnEncode.setText("编码");
		setControlProperty(btnEncode);
		Button btnDncode = new Button(gpTable, 0);
		btnDncode.setText("解码");
		setControlProperty(btnDncode);

		final Text textTarget = new Text(container, SWT.MULTI | SWT.V_SCROLL
				| SWT.BORDER | SWT.WRAP);

		setControlProperty(textTarget, true, 1);

		btnEncode.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent selectionevent) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent selectionevent) {
				try {
					textTarget.setText(java.net.URLEncoder.encode(
							textSource.getText(), combo.getText()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		});

		btnDncode.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent selectionevent) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent selectionevent) {
				try {
					textTarget.setText(java.net.URLDecoder.decode(
							textSource.getText(), combo.getText()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

		});
	
	}
	private static void setControlProperty(Control control, boolean vb, int span) {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true; // 抢占垂直方向额外空间
		gridData.horizontalAlignment = GridData.FILL; // 水平方向充满
		if (vb) {
			gridData.grabExcessVerticalSpace = true; // 抢占水平方向额外空间
			gridData.verticalAlignment = GridData.FILL; // 垂直方向充满
		}
		gridData.horizontalSpan = span;
		control.setLayoutData(gridData);
	}

	private static void setControlProperty(Control control) {
		setControlProperty(control, false, 1);
	}

	private static void setControlProperty(Control control, int span) {
		setControlProperty(control, false, span);
	}
}
