package com.wt.studio.plugin.wizard.projects.services;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.bomodel.MenuMessage;
import com.wt.studio.plugin.wizard.projects.services.components.ItemMessage;
import com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain.MessageInfo;
import com.wt.studio.plugin.wizard.projects.uitl.HEABasePage;

public class AssembleModelPage extends HEABasePage {
	private Composite container;

	private Tree modelTree;

	private Menu menu;
	
	

	public Tree getModelTree() {
		return modelTree;
	}

	public AssembleModelPage() {
		super("Other properties");
		setTitle("HEA Service Moudle");
		setDescription("根据Rest、WebService 配置 BOModel");
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		Group gpConn = new Group(container, SWT.NONE);
		gpConn.setText(" Select Model ");
		gpConn.setLayout(new RowLayout());
		GridData gdConn = new GridData();
		gdConn.horizontalAlignment = GridData.FILL;
		gdConn.grabExcessHorizontalSpace = true;
		gpConn.setLayoutData(gdConn);
		gpConn.setLayout(new GridLayout(3, false));

		Button btnSTD = new Button(gpConn, SWT.NONE);
		btnSTD.setText("Standard Model");
		
		Button btnWS = new Button(gpConn, SWT.NONE);
		btnWS.setText("WS Model");
		
		Button btnREST = new Button(gpConn, SWT.NONE);
		btnREST.setText("Rest Model");

//		btnDesc.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				String wsdlURL = wsdlUTL.getText();
//				if (wsdlURL.isEmpty()) {
//					wsdlUTL.setFocus();
//				} else {
//					try {
//						
//					} catch (Exception e1) {
//						MessageDialog.openInformation(null, "消息框", "WSDL解析失败！");
//						setPageComplete(false);
//					}
//				}
//			}
//		});

		Group gpConn1 = new Group(container, SWT.NONE);
		gpConn1.setText("Setting BOModel");
		gpConn1.setLayout(new RowLayout());
		GridData gdConn1 = new GridData();
		gdConn1.verticalAlignment = GridData.FILL;
		gdConn1.grabExcessHorizontalSpace = true;
		gdConn1.grabExcessVerticalSpace = true;
		gpConn1.setLayoutData(gdConn1);
		gpConn1.setLayout(new GridLayout(1, false));

		modelTree = new Tree(gpConn1, SWT.BORDER | SWT.FULL_SELECTION);
		final TreeEditor nameEditor = new TreeEditor(modelTree);
		modelTree.setLayoutData(gdConn1);

		TreeColumn columnService = new TreeColumn(modelTree, SWT.NONE);
		columnService.setText("                  Type                  ");
		
		TreeColumn columnType = new TreeColumn(modelTree, SWT.NONE);
		columnType.setText("      Name    ");
		
		TreeColumn columnModelType = new TreeColumn(modelTree, SWT.NONE);
		columnModelType.setText("      Model Type    ");
		
		TreeColumn columnObject = new TreeColumn(modelTree, SWT.NONE);
		columnObject.setText("            Source Object        ");
		
		TreeColumn columnField = new TreeColumn(modelTree, SWT.NONE);
		columnField.setText("            Source Field          ");
	

		final TreeItem modelRoot = new TreeItem(modelTree, SWT.NULL, 0);
		modelRoot.setText("Models");

		// modelTree.addListener(SWT.MouseDown, new Listener() {
		//
		// @Override
		// public void handleEvent(Event event) {
		// Rectangle clientArea = modelTree.getClientArea();
		// Point pt = new Point(event.x, event.y);
		// for (int index = 0; index < modelTree.getItemCount(); index++) {
		// while (index < modelTree.getItemCount()) {
		// boolean visible = false;
		// final TreeItem item = modelTree.getItem(index);
		// System.out.println(item.getText());
		// for (int i = 0; i < modelTree.getColumnCount(); i++) {
		// Rectangle rect = item.getBounds(i);
		// if (rect.contains(pt)) {
		// System.out.println("Hi!");
		// }
		// if (!visible && rect.intersects(clientArea)) {
		// visible = true;
		// }
		// }
		// if (!visible)
		// return;
		// }
		// }
		//
		// }
		// });

		modelTree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				Control oldEditor = nameEditor.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}
				TreeItem item = (TreeItem) e.item;
				if (item == null)
					return;
				item.setExpanded(true);
				Text textEditor = new Text(modelTree, SWT.NONE);
				textEditor.setText(item.getText(1));

				nameEditor.grabHorizontal = true;
				textEditor.computeSize(SWT.DEFAULT, modelTree.getItemHeight());
				nameEditor.grabHorizontal = true;
				nameEditor.minimumHeight = textEditor.getSize().y;
				nameEditor.minimumWidth = textEditor.getSize().x;

				textEditor.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						nameEditor.getItem().setText(1,
								((Text) e.widget).getText());
					}
				});

				textEditor.selectAll();
				textEditor.setFocus();
				

				nameEditor.setEditor(textEditor, item, 1);
			}
		});

		modelTree.addMenuDetectListener(new MenuDetectListener() {
			@Override
			public void menuDetected(MenuDetectEvent e) {
				Control oldEditor = nameEditor.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}
				TreeItem item = ((Tree) e.getSource()).getSelection()[0];
				modelTree.setMenu(MenuMessage.getMenu(modelTree, item.getText()));
			}
		});

		for (int i = 0; i < modelTree.getColumnCount(); i++) {
			modelTree.getColumn(i).pack();
		}

		modelTree.setLinesVisible(true);
		modelTree.setHeaderVisible(true);
		
		modelRoot.setExpanded(true);
		
		assembleModel(modelTree, modelRoot);
		setControl(container);
	}

	/**
	 * 组装Model Tree
	 * @param tree
	 */
	protected void assembleModel(Tree tree, TreeItem modelRoot) {
		
		TreeItem mode = new TreeItem(modelRoot, 1);
		mode.setText(0, "Object");
		mode.setText(1, "User");
		mode.setText(2, "");
		mode.setText(3, "");
		mode.setText(4, "");		
		modelRoot.setText(1, "");
		
		TreeItem mName = new TreeItem(mode, 1);
		mName.setText(0, "String");
		mName.setText(1, "name");
		mName.setText(2, "Standard Model");
		mName.setText(3, "User");
		mName.setText(4, "name");
		
		TreeItem mAge = new TreeItem(mode, 1);
		mAge.setText(0, "String");
		mAge.setText(1, "age");
		mAge.setText(2, "Standard Model");
		mAge.setText(3, "User");
		mAge.setText(4, "age");
		
		TreeItem mAddress = new TreeItem(mode, 1);
		mAddress.setText(0, "Object");
		mAddress.setText(1, "Address");
		mAddress.setText(2, "");
		mAddress.setText(3, "");
		mAddress.setText(4, "");
		
		TreeItem mCity = new TreeItem(mAddress, 1);
		mCity.setText(0, "String");
		mCity.setText(1, "city");
		mCity.setText(2, "Standard Model");
		mCity.setText(3, "Address");
		mCity.setText(4, "city");
		
		TreeItem mCountry = new TreeItem(mAddress, 1);
		mCountry.setText(0, "String");
		mCountry.setText(1, "country");
		mCountry.setText(2, "Standard Model");
		mCountry.setText(3, "Address");
		mCountry.setText(4, "country");	
		
		TreeItem mService = new TreeItem(mode, 1);
		mService.setText(0, "Array");
		mService.setText(1, "tasks");
		mService.setText(2, "");
		mService.setText(3, "");
		mService.setText(4, "");	
		
		TreeItem mWS = new TreeItem(mService, 1);
		mWS.setText(0, "Object");
		mWS.setText(1, "TaskUser");
		mWS.setText(2, "");
		mWS.setText(3, "");
		mWS.setText(4, "");
		
		TreeItem mBname = new TreeItem(mWS, 1);
		mBname.setText(0, "String");
		mBname.setText(1, "uName");
		mBname.setText(2, "WS Model");
		mBname.setText(3, "com.hirisun.nf.UUMService.getUser");
		mBname.setText(4, "uname");
		
		TreeItem mBTask = new TreeItem(mWS, 1);
		mBTask.setText(0, "String");
		mBTask.setText(1, "uId");
		mBTask.setText(2, "WS Model");
		mBTask.setText(3, "com.hirisun.nf.UUMService.getUser");
		mBTask.setText(4, "uid");
		
		TreeItem mTDesc = new TreeItem(mWS, 1);
		mTDesc.setText(0, "String");
		mTDesc.setText(1, "uDept");
		mTDesc.setText(2, "WS Model");
		mTDesc.setText(3, "com.hirisun.nf.UUMService.getUser");
		mTDesc.setText(4, "udept");
		
		TreeItem num = new TreeItem(mode, 1);
		num.setText(0, "String");
		num.setText(1, "taskCount");
		num.setText(2, "Rest Model");
		num.setText(3, "http://localhost:8080/getUserTasks");
		num.setText(4, "");
	}

	/**
	 * 构建服务
	 * 
	 * @param tree
	 * @param wsdlURL
	 */
	private void assembleServiceParam(Tree tree, String wsdlURL) {
		MessageInfo message = new MessageInfo();
		com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.util.WSClient
				.getInstance().setURL(wsdlURL).fire(message);
		if (message == null)
			return;
		TreeItem root = null;
		if (message.getChilds() != null) {
			for (MessageInfo m : message.getChilds()) {
				root = new TreeItem(tree, SWT.NULL);
				root.setText(
						0,
						m.getType() == MessageInfo.Type.operation ? m
								.getServiceName() + "." + m.getName() : m
								.getName());
				root.setText(1, m.getFiledType() != null ? m.getFiledType() : m
						.getType().toString());
				assembleMessageInfo(root, m);
			}

		}
	}

	/**
	 * 获取子服务
	 * 
	 * @param parent
	 * @param message
	 */
	private void assembleMessageInfo(TreeItem parent, MessageInfo message) {
		TreeItem child = null;
		for (MessageInfo m : message.getChilds()) {
			child = new TreeItem(parent, SWT.NULL);
			child.setText(
					0,
					m.getType() == MessageInfo.Type.operation ? m
							.getServiceName() + "." + m.getName() : m.getName());
			child.setText(1, m.getFiledType() != null ? m.getFiledType() : m
					.getType().toString());

			if (m.getChilds() != null) {
				assembleMessageInfo(child, m);
			}
		}
	}

	private void assembleHTMLTree(Tree tree, TreeItem root) {
		tree.getColumn(1);
		TreeItem view = new TreeItem(root, SWT.NULL);
		view.setText("View");
	}

}
