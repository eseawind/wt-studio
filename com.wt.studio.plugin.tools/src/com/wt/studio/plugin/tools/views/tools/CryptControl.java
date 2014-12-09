package com.wt.studio.plugin.tools.views.tools;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.tools.utils.CodeDigest;
import com.wt.studio.plugin.tools.utils.CodeDigest.CodeType;

public class CryptControl
{
	public static void init(TabFolder tabFolder)
	{
		TabItem itemp = new TabItem(tabFolder, SWT.NONE);
		itemp.setText("加密解密");
		Composite containerp = new Composite(tabFolder, SWT.NONE);
		GridLayout glp = new GridLayout();
		containerp.setLayout(glp);
		itemp.setControl(containerp);

		TabFolder tab = new TabFolder(containerp, SWT.NONE);
		setControlProperty(tab, true, 1);
		createChild1(tab);
		createChild2(tab);
		createChild3(tab);
	}
	private static void createChild1(TabFolder tab) {

		TabItem item = new TabItem(tab, SWT.NONE);
		item.setText("BASE64");
		Composite container = new Composite(tab, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		container.setLayout(gl);
		item.setControl(container);

		final Text textSource = new Text(container, SWT.MULTI | SWT.V_SCROLL
				| SWT.BORDER | SWT.WRAP);
		setControlProperty(textSource, true, 1);

		Group gpTable = new Group(container, SWT.NONE);
		GridData gdConn1 = new GridData();
		gdConn1.grabExcessVerticalSpace = true;
		gpTable.setLayoutData(gdConn1);
		GridLayout grid = new GridLayout();
		grid.numColumns = 1;
		gpTable.setLayout(grid);

		Button btnEncode = new Button(gpTable, 0);
		btnEncode.setText("  编码   ");
		setControlProperty(btnEncode);
		Button btnDncode = new Button(gpTable, 0);
		btnDncode.setText("  解码   ");
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
				String type = CodeType.BASE64.toString();
				String sect = null;// secret.getText();
				String s = CodeDigest.encodeByType(CodeType.valueOf(type),
						textSource.getText(), sect);
				textTarget.setText(s);
			}

		});

		btnDncode.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent selectionevent) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent selectionevent) {
				String type = CodeType.BASE64.toString();
				String sect = null;// secret.getText();
				String s = CodeDigest.decodeByType(CodeType.valueOf(type),
						textSource.getText(), sect);
				textTarget.setText(s);
			}

		});
	
	}
	private static void createChild2(TabFolder tab){

		TabItem item = new TabItem(tab, SWT.NONE);
		item.setText("散列/哈希");
		Composite container = new Composite(tab, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		container.setLayout(gl);
		item.setControl(container);

		final Text textSource = new Text(container, SWT.MULTI | SWT.V_SCROLL
				| SWT.BORDER | SWT.WRAP);
		setControlProperty(textSource, true, 1);

		Group gpTable = new Group(container, SWT.NONE);
		GridData gdConn1 = new GridData();
		gdConn1.grabExcessVerticalSpace = true;
		gpTable.setLayoutData(gdConn1);
		GridLayout grid = new GridLayout();
		grid.numColumns = 2;
		gpTable.setLayout(grid);

		final Combo combo = new Combo(gpTable, SWT.NONE | SWT.READ_ONLY);
		combo.add(CodeType.MD5.toString(), 0);
		combo.add(CodeType.SHA1.toString(), 1);
		combo.add(CodeType.SHA224.toString(), 2);
		combo.add(CodeType.SHA256.toString(), 3);
		combo.add(CodeType.SHA384.toString(), 4);
		combo.add(CodeType.SHA512.toString(), 5);
		combo.select(0);
		setControlProperty(combo, 2);
		Label label = new Label(gpTable, SWT.NONE);
		label.setText("密钥：");
		setControlProperty(label);
		final Text secret = new Text(gpTable, SWT.BORDER);
		setControlProperty(secret);
		secret.setEnabled(false);
		Button btnEncode = new Button(gpTable, 0);
		setControlProperty(btnEncode, 2);
		btnEncode.setText("    Hash    ");

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
				String type = combo.getText();
				String sect = null;// secret.getText();
				String s = CodeDigest.encodeByType(CodeType.valueOf(type),
						textSource.getText(), sect);
				textTarget.setText(s);
			}
		});
	
	}
	
	private static void createChild3(TabFolder tab)
	{

		TabItem item = new TabItem(tab, SWT.NONE);
		item.setText("加密/解密");
		Composite container = new Composite(tab, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		container.setLayout(gl);
		item.setControl(container);

		final Text textSource = new Text(container, SWT.MULTI | SWT.V_SCROLL
				| SWT.BORDER | SWT.WRAP);
		setControlProperty(textSource, true, 1);

		Group gpTable = new Group(container, SWT.NONE);
		GridData gdConn1 = new GridData();
		gdConn1.grabExcessVerticalSpace = true;
		gpTable.setLayoutData(gdConn1);
		GridLayout grid = new GridLayout();
		grid.numColumns = 2;
		gpTable.setLayout(grid);

		final Combo combo = new Combo(gpTable, SWT.NONE | SWT.READ_ONLY);
		setControlProperty(combo, 2);
		combo.add(CodeType.AES.toString(), 0);
		combo.add(CodeType.DES.toString(), 1);
		combo.add(CodeType.RC4.toString(), 2);
		combo.add(CodeType.Rabbit.toString(), 3);
		combo.add(CodeType.TripleDes.toString(), 4);
		combo.select(0);

		Label label = new Label(gpTable, SWT.NONE);
		setControlProperty(label);
		label.setText("密码：");
		final Text secret = new Text(gpTable, SWT.BORDER);
		setControlProperty(secret);
		Button btnEncode = new Button(gpTable, 0);
		setControlProperty(btnEncode);
		btnEncode.setText("  加密     ");
		Button btnDncode = new Button(gpTable, 0);
		setControlProperty(btnDncode);
		btnDncode.setText("  解密      ");

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
				String type = combo.getText();
				String sect = secret.getText();
				String s = CodeDigest.encodeByType(CodeType.valueOf(type),
						textSource.getText(), sect);
				textTarget.setText(s);
			}

		});

		btnDncode.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent selectionevent) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent selectionevent) {
				String type = combo.getText();
				String sect = secret.getText();
				String s = CodeDigest.decodeByType(CodeType.valueOf(type),
						textSource.getText(), sect);
				textTarget.setText(s);
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
