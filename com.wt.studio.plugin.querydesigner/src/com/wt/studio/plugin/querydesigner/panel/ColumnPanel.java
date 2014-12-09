package com.wt.studio.plugin.querydesigner.panel;
//package com.hirisun.ide.plugin.querydesigner.panel;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.ModifyEvent;
//import org.eclipse.swt.events.ModifyListener;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Group;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.swt.widgets.TreeItem;
//import org.eclipse.swt.widgets.Widget;
//
//import com.hirisun.ide.plugin.querydesigner.model.ColumnModel;
//
//public class ColumnPanel extends Composite
//{
//	private Group group;
//	private Text columnName;
//	private Text columnDesc;
//	private Button btnDimension;
//	private Widget widget;
//
//	public ColumnPanel(Composite parent, int style)
//	{
//		super(parent, style);
//		this.setLayout(new FillLayout());
//		group = new Group(this, SWT.NULL);
//		group.setText("Properties");
//		group.setLayout(new GridLayout(2, false));
//		columnName = makeText("Column Name");
//		columnName.setEditable(false);
//		columnDesc = makeText("Column description");
//		Label label = new Label(group, SWT.NULL);
//		label.setText("Group");
//		btnDimension = new Button(group, SWT.CHECK);
//
//		initEvent();
//	}
//
//	private void initEvent()
//	{
//		SelectionListener selectionListener = new SelectionListener() {
//			@Override
//			public void widgetSelected(SelectionEvent e)
//			{
//				if (getWidget() != null) {
//					ColumnModel columnModel = (ColumnModel) getWidget().getData();
//					if (e.getSource() == btnDimension) {
//						columnModel.setDimension(((Button) e.getSource()).getSelection());
//					}
//				}
//			}
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e)
//			{
//			}
//		};
//		btnDimension.addSelectionListener(selectionListener);
//
//		ModifyListener modifyListener = new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent e)
//			{
//				if (getWidget() != null) {
//					ColumnModel columnModel = (ColumnModel) getWidget().getData();
//					if (e.getSource() == columnDesc) {
//						columnModel.setColumnDesc(columnDesc.getText());
//					}
//				}
//			}
//		};
//		columnDesc.addModifyListener(modifyListener);
//	}
//
//	public Widget getWidget()
//	{
//		return widget;
//	}
//
//	public void setWidget(Widget widget)
//	{
//		this.widget = widget;
//		ColumnModel columnModel = this.widget.getData() == null ? new ColumnModel()
//				: (ColumnModel) this.widget.getData();
//		this.widget.setData(columnModel);
//		columnName.setText(((TreeItem) getWidget()).getText());
//		columnDesc.setText(columnModel.getColumnDesc());
//		btnDimension.setSelection(columnModel.isDimension());
//	}
//
//	private GridData makeGridData()
//	{
//		GridData gridData = new GridData();
//		gridData.grabExcessHorizontalSpace = true;
//		gridData.horizontalAlignment = GridData.FILL;
//		return gridData;
//	}
//
//	private Text makeText(String text)
//	{
//		Label label = new Label(group, SWT.NULL);
//		label.setText(text);
//		Text result = new Text(group, SWT.SINGLE);
//		result.setLayoutData(makeGridData());
//		return result;
//	}
// }
