package com.wt.studio.plugin.wizard.projects.services;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.bomodel.MenuMessage;
import com.wt.studio.plugin.wizard.projects.services.components.ComponentType;
import com.wt.studio.plugin.wizard.projects.services.components.ItemMessage;
import com.wt.studio.plugin.wizard.projects.services.util.BOModel;
import com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain.MessageInfo;
import com.wt.studio.plugin.wizard.projects.uitl.HEABasePage;

public class AssembleServicePage extends HEABasePage {
	private Composite container;

	private Tree modelTree;

	private Tree html5Tree;

	private Menu menu;

	private static String[] html5TreeLables = new String[] {
			"                布局                                       ",
			"       标题                ", "    控件             ",
			"         属性字段           " };

	public Tree getServiceTree() {
		return modelTree;
	}

	public void setServiceTree(Tree serviceTree) {
		this.modelTree = serviceTree;
	}

	public Tree getHtml5Tree() {
		return html5Tree;
	}

	public void setHtml5Tree(Tree html5Tree) {
		this.html5Tree = html5Tree;
	}

	public Tree getModelTree() {
		return modelTree;
	}

	public void setModelTree(Tree modelTree) {
		this.modelTree = modelTree;
	}

	private static String[] controls = new String[] { "Label", "Input",
			"PassWord", "Slider" };

	public AssembleServicePage() {
		super("Other properties");
		setTitle("HEA Service Moudle ");
		setDescription("设置业务模型BOModel 生成多客户端例如：PC、 Mobile Page");
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));

		Group gpConnP = new Group(container, SWT.NONE);
		gpConnP.setLayout(new RowLayout());
		GridData gdConnP = new GridData();
		gdConnP.verticalAlignment = GridData.FILL;
		gdConnP.grabExcessHorizontalSpace = true;
		gdConnP.grabExcessVerticalSpace = true;
		gpConnP.setLayoutData(gdConnP);
		gpConnP.setLayout(new GridLayout(2, false));

		Group gpConn1 = new Group(gpConnP, SWT.NONE);
		gpConn1.setBounds(new Rectangle(0, 0, 250, 450));
		gpConn1.setText("BOModel Info");
		gpConn1.setLayout(new RowLayout());
		GridData gdConn1 = new GridData();
		gdConn1.minimumWidth = 245;
		gdConn1.minimumHeight = 350;
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
		columnService.setWidth(150);
		TreeColumn columnType = new TreeColumn(modelTree, SWT.NONE);
		columnType.setText("      Name    ");
		columnType.setWidth(150);

		TreeItem modelRoot = new TreeItem(modelTree, SWT.NULL, 0);
		modelRoot.setText("Models");

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
		assembleModelTree(modelTree, modelRoot);
		modelRoot.setExpanded(true);

		Group gpConn2 = new Group(gpConnP, SWT.NONE);
		gpConn2.setText("Mobile HTML5");
		gpConn2.setLayout(new RowLayout());
		GridData gdConn2 = new GridData();
		gpConn2.setBounds(new Rectangle(101, 0, 700, 450));
		gdConn2.minimumWidth = 700;
		gdConn2.minimumHeight = 350;
		gdConn2.horizontalAlignment = GridData.FILL;
		gdConn2.verticalAlignment = GridData.FILL;
		gdConn2.grabExcessHorizontalSpace = true;
		gdConn2.grabExcessVerticalSpace = true;
		gpConn2.setLayoutData(gdConn2);
		gpConn2.setLayout(new GridLayout(1, true));

		html5Tree = new Tree(gpConn2, SWT.BORDER | SWT.FULL_SELECTION);
		final TreeEditor editor = new TreeEditor(html5Tree);
		final TreeEditor editor2 = new TreeEditor(html5Tree);
		html5Tree.setLayoutData(gdConn2);

		TreeColumn columnHtml = null;
		for (int i = 0; i < html5TreeLables.length; i++) {
			columnHtml = new TreeColumn(html5Tree, SWT.None);
			columnHtml.setText(html5TreeLables[i]);
		}

		html5Tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				Control oldEditor = editor.getEditor();
				Control oldEditor2 = editor2.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}
				if (oldEditor2 != null) {
					oldEditor2.dispose();
				}
				TreeItem item = (TreeItem) e.item;

				if (item == null)
					return;
				item.setExpanded(true);
				Text textEditor = new Text(html5Tree, SWT.NONE);
				textEditor.setText(item.getText(1));

				editor.grabHorizontal = true;
				textEditor.computeSize(SWT.DEFAULT, html5Tree.getItemHeight());
				editor.grabHorizontal = true;
				editor.minimumHeight = textEditor.getSize().y;
				editor.minimumWidth = textEditor.getSize().x;

				textEditor.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent e) {
						editor.getItem()
								.setText(1, ((Text) e.widget).getText());
					}
				});

				textEditor.selectAll();
				textEditor.setFocus();
				if (item.getText(0).equals("Field")) {
					Combo comEditor = new Combo(html5Tree, SWT.READ_ONLY
							| SWT.NONE);

					for (String controlName : controls) {
						comEditor.add(controlName);
					}

					editor2.grabHorizontal = true;
					comEditor.computeSize(SWT.DEFAULT,
							html5Tree.getItemHeight());
					editor2.grabHorizontal = true;
					editor2.minimumHeight = comEditor.getSize().y;
					editor2.minimumWidth = comEditor.getSize().x;
					comEditor.setText(item.getText(2));
					comEditor.addModifyListener(new ModifyListener() {
						@Override
						public void modifyText(ModifyEvent e) {
							editor.getItem().setText(2,
									((Combo) e.widget).getText());
						}
					});
					editor2.setEditor(comEditor, item, 2);
				}
				editor.setEditor(textEditor, item, 1);
			}
		});

		TreeItem html5Root = new TreeItem(html5Tree, SWT.NULL, 0);
		html5Root.setText("HTML5");
		html5Tree.addMenuDetectListener(new MenuDetectListener() {
			@Override
			public void menuDetected(MenuDetectEvent e) {
				
				Control oldEditor = editor.getEditor();
				Control oldEditor2 = editor2.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}
				if (oldEditor2 != null) {
					oldEditor2.dispose();
				}
				
				TreeItem item = ((Tree) e.getSource()).getSelection()[0];
				html5Tree.setMenu(ItemMessage.getMenu(html5Tree, item.getText()));
			}
		});

		for (int i = 0; i < html5Tree.getColumnCount(); i++) {
			html5Tree.getColumn(i).pack();
		}

		html5Tree.setLinesVisible(true);
		html5Tree.setHeaderVisible(true);
		assembleHTMLTree(html5Tree, html5Root); // html5Tree
		html5Root.setExpanded(true);
		TextTransfer textTransfer = TextTransfer.getInstance();
		DragSource source = new DragSource(modelTree, DND.DROP_MOVE
				| DND.DROP_COPY);
		source.setTransfer(new Transfer[] { textTransfer });
		source.addDragListener(new ServiceDragListener(this));

		DropTarget target = new DropTarget(html5Tree, DND.DROP_MOVE
				| DND.DROP_COPY);
		target.setTransfer(new Transfer[] { textTransfer });
		target.addDropListener(new ServiceDropListener(this));

		setControl(container);
		setPageComplete(false);
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
		view.setText(ComponentType.TabStrip.toString());
	}

	private void assembleModelTree(Tree tree, TreeItem root) {
		tree.getColumn(1);
		TreeItem view = new TreeItem(root, SWT.NULL);
		view.setText("Object");
	}
}
