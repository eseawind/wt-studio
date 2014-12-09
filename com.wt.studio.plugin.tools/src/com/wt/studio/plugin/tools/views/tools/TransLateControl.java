package com.wt.studio.plugin.tools.views.tools;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.tools.utils.TranslateGoogle;

public class TransLateControl
{
	public static void init(TabFolder tabFolder)
	{

		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("在线翻译");
		Composite container = new Composite(tabFolder, SWT.NONE);
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
		GridLayout gll = new GridLayout();
		gpTable.setLayout(gll);

		Button btnEncode = new Button(gpTable, 0);
		btnEncode.setText("  英-》汉      ");
		Button btnDncode = new Button(gpTable, 0);
		btnDncode.setText("  汉-》英      ");

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
				String source = textSource.getText();
				if (StringUtils.isEmpty(source)) {
					return;
				}
				StringBuilder result = new StringBuilder("");
				try {
					String[] ss = StringUtils.split(source, "\n");
					for (String s : ss) {
						if (StringUtils.isNotEmpty(StringUtils.trim(s))) {
							result.append(
									TranslateGoogle
											.translate(
													s,
													TranslateGoogle.LANG_EN,
													TranslateGoogle.LANG_CHINESE_SIMPLE))
									.append("\n");
						} else {
							result.append("\n");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				textTarget.setText(result.toString());
			}
		});

		btnDncode.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent selectionevent) {
				// TODO Auto-generated method stub
			}

			@Override
			public void widgetSelected(SelectionEvent selectionevent) {
				String source = textSource.getText();
				if (StringUtils.isEmpty(source)) {
					return;
				}
				StringBuilder result = new StringBuilder("");
				try {
					String[] ss = StringUtils.split(source, "\n");
					for (String s : ss) {
						if (StringUtils.isNotEmpty(StringUtils.trim(s))) {
							result.append(
									TranslateGoogle
											.translate(
													s,
													TranslateGoogle.LANG_CHINESE_SIMPLE,
													TranslateGoogle.LANG_EN))
									.append("\n");
						} else {
							result.append("\n");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				textTarget.setText(result.toString());
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

}
