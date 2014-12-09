package com.wt.studio.plugin.tools.views.tools;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.wt.studio.plugin.tools.Activator;

public class RegexControl {
	public static void init(TabFolder tabFolder) {

		// TODO Auto-generated method stub
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("正则");
		// ******************布局开始************************
		Composite container = new Composite(tabFolder, SWT.NONE);
		container.setLayout(new FillLayout());
		item.setControl(container);

		SashForm form = new SashForm(container, SWT.HORIZONTAL | SWT.BORDER);
		form.setLayout(new FillLayout());
		Composite child1 = new Composite(form, SWT.NONE);
		GridLayout gridlayout = new GridLayout();
		child1.setLayout(gridlayout);
		gridlayout.numColumns = 2;
		GridData griddata = new GridData();
		griddata.verticalSpan = 3;
		griddata.verticalAlignment = SWT.FILL;
		griddata.horizontalAlignment = SWT.FILL;
		griddata.grabExcessHorizontalSpace = true;
		griddata.grabExcessVerticalSpace = true;
		GridData g1 = new GridData();
		g1.horizontalAlignment = SWT.FILL;
		g1.grabExcessHorizontalSpace = true;
		final Text t1 = new Text(child1, SWT.MULTI | SWT.BORDER);
		t1.setText("输入正则表达式");
		t1.setLayoutData(g1);
		Button b1 = new Button(child1, SWT.PUSH);
		b1.setText("匹配");
		Composite child2 = new Composite(form, SWT.NONE);
		GridLayout gridlayout1 = new GridLayout();
		gridlayout1.numColumns = 2;
		child2.setLayout(gridlayout1);
		Label l1 = new Label(child2, SWT.NONE);
		l1.setText("常用正则表达式");
		Label l2 = new Label(child2, SWT.NONE);
		l2.setText("（单击选择）");
		final Button b2 = new Button(child2, SWT.PUSH);
		b2.setText("匹配英文字符");
		b2.setData("[a-z]*");
		final Button b3 = new Button(child2, SWT.PUSH);
		b3.setText("匹配汉字");
		b3.setData("[\\u4e00-\\u9fa5]");
		final Button b4 = new Button(child2, SWT.PUSH);
		b4.setText("匹配qq号码");
		b4.setData("[1-9][0-9]{4,}");
		final Button b5 = new Button(child2, SWT.PUSH);
		b5.setText("匹配url");
		b5.setData("[a-zA-z]+://[^\\s]*");
		final Button b6 = new Button(child2, SWT.PUSH);
		b6.setText("匹配双字节字符");
		b6.setData("[^\\x00-\\xff]");
		final Button b7 = new Button(child2, SWT.PUSH);
		b7.setText("匹配邮件地址");
		b7.setData("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
		final Table table = new Table(child1, SWT.MULTI | SWT.BORDER);
		table.setLayoutData(griddata);
		table.setLinesVisible(true);
		String[] tableHeader = {
				"待匹配的字符串                                            ", "匹配结果",
				"匹配信息                                                  " };
		for (int i = 0; i < tableHeader.length; i++) {
			TableColumn tableColumn = new TableColumn(table, SWT.NONE);
			tableColumn.setText(tableHeader[i]);
			tableColumn.setMoveable(true);
		}
		table.setHeaderVisible(true);
		for (int i = 0; i < 11; i++) {
			TableItem item0 = new TableItem(table, SWT.NONE);
			item0.setText(new String[] { "", " ", "" });
		}
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(i).pack();
		}
		final TableItem[] items = table.getItems();
		for (int i = 0; i < items.length; i++) {
			final TableEditor editor = new TableEditor(table);
			final Text text = new Text(table, SWT.NONE);
			text.setText(items[i].getText(0));
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 0);
			text.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					editor.getItem().setText(0, text.getText());
				}
			});
		}
		form.setWeights(new int[] { 70, 30 });
		// ******************布局结束************************

		/***************** 选择常用表达式监听器， ****************/
		SelectionListener listener = new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				Widget w = e.widget;
				if (w == b2) {
					t1.setText((String) b2.getData());
				} else if (w == b3) {
					t1.setText((String) b3.getData());
				} else if (w == b4) {
					t1.setText((String) b4.getData());
				} else if (w == b5) {
					t1.setText((String) b5.getData());
				} else if (w == b6) {
					t1.setText((String) b6.getData());
				} else if (w == b7) {
					t1.setText((String) b7.getData());
				}
			}

		};
		b2.addSelectionListener(listener);
		b3.addSelectionListener(listener);
		b4.addSelectionListener(listener);
		b5.addSelectionListener(listener);
		b6.addSelectionListener(listener);
		b7.addSelectionListener(listener);

		/************************ 匹配功能监听器 ***************/
		SelectionListener listener1 = new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				for (int i = 0; i < items.length; i++) {
					String str = items[i].getText(0);
					Pattern pa = Pattern.compile(t1.getText());
					Matcher ma = pa.matcher(str);
					int flag = 0;
					String res1 = " ";
					ArrayList<String> strs = new ArrayList<String>();
					strs.add(res1);
					while (ma.find()) {
						if (ma.group() != null && !"".equals(ma.group())) {
							flag = 1;
							strs.add(ma.group() + "从第" + ma.start() + "位开始到第"
									+ ma.end() + "位结束。");
						}
					}
					for (String s : strs) {
						res1 = res1 + s;
					}
					if (flag == 1) {
						items[i].setText(2, res1);
						items[i].setImage(1, Activator
								.loadImageDescriptor("/icons/right.gif").createImage());
					} else {
						items[i].setText(2, "不匹配");
						items[i].setImage(1, Activator
								.loadImageDescriptor("/icons/wrong.gif").createImage());
					}
				}
			}
		};
		b1.addSelectionListener(listener1);

	}
}
