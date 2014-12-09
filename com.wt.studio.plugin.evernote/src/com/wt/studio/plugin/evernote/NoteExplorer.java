package com.wt.studio.plugin.evernote;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.SharedNotebook;
import com.evernote.edam.type.Tag;
import com.wt.studio.plugin.evernote.actions.NoteActionGroup;
import com.wt.studio.plugin.evernote.util.EverNoteHelp;

public class NoteExplorer extends org.eclipse.ui.part.ViewPart implements
		ISelectionListener {

	private Tree tree;
	private Table table;
	
	private List<Notebook> notebooks;
	private List<SharedNotebook> sharedNotebooks;

	private EverNoteHelp everNoteHelp;

	@Override
	public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createPartControl(Composite parent) {
//		try {
//			everNoteHelp = new EverNoteHelp();
//		} catch (Exception e) {
//		}

		Composite composite = new Composite(parent, SWT.FILL);
		composite.setLayout(new FillLayout(SWT.VERTICAL));

//		tree = new Tree(composite, SWT.UP | SWT.FILL);
//
//		// 获取NoteBook 列表
//		notebooks = getUserNoteBookList();
//		TreeItem bookItem = new TreeItem(tree, 0);
//		bookItem.setText("我的笔记本 ");
//		bookItem.setExpanded(true);
//		for (Notebook notebook : notebooks) {
//			TreeItem notebookItem = new TreeItem(bookItem, 1);
//			notebookItem.setText(notebook.getName());
//		}
//
//		// 获取共享NoteBook 列表
//		try {
//			sharedNotebooks = everNoteHelp.listShareNoteBooks();
//			TreeItem sharedbookItem = new TreeItem(tree, 0);
//			sharedbookItem.setText("共享笔记本 ");
//
//			for (SharedNotebook sharedNotebook : sharedNotebooks) {
//				TreeItem notebookItem = new TreeItem(sharedbookItem, 1);
//				Notebook sharedNoteB = everNoteHelp
//						.getShareNoteBookByNotebook(sharedNotebook);
//				notebookItem.setText(sharedNoteB.getName());
//			}
//		} catch (Exception e) {
//
//		}
//		
//		// 获取标签 列表
//		TreeItem tagItem = new TreeItem(tree, 0);
//		tagItem.setText("标签");
//
//		tree.addListener(SWT.Selection, new Listener() {
//
//			@Override
//			public void handleEvent(Event event) {
//				final TreeItem focusItem = (TreeItem) event.item;
//				String item = focusItem.getText();
//				Notebook notebook = null;
//				table.removeAll();
//				for (Notebook nb : notebooks) {
//					if (nb.getName().equals(item)) {
//						notebook = nb;
//					}
//				}
//				try {
//					List<Note> notes = everNoteHelp.listNotes(notebook);
//					for (int i = 0; i < notes.size(); i++) {
//						Note note = notes.get(i);
//						TableItem tableItem = new TableItem(table, SWT.FILL);
//						tableItem.setText(new String[] { note.getTitle() });
//					}
//				} catch (Exception e) {
//				}
//
//			}
//
//		});
//
//		GridData gd = new GridData();
//		gd.horizontalAlignment = SWT.FILL;
//		gd.grabExcessHorizontalSpace = true;
//		gd.grabExcessVerticalSpace = true;
//		gd.verticalAlignment = SWT.FILL;
//		table = new Table(composite, SWT.FULL_SELECTION);
//		table.setHeaderVisible(true); // 显示表头
//		table.setLinesVisible(true); // 显示表格线
//		table.setLayoutData(gd);
//
//		String[] tableHeader = { "笔记" };
//		for (int i = 0; i < tableHeader.length; i++) {
//			TableColumn tableColumn = new TableColumn(table, SWT.FILL);
//			tableColumn.setWidth(300);
//			tableColumn.setText(tableHeader[i]);
//			table.setHeaderVisible(true);
//		}
//
//		NoteActionGroup actionGroup = new NoteActionGroup();
//		fillViewAction(actionGroup);// 加入视图的导航栏按钮
//		fillViewMenu(actionGroup);// 加入视图的下拉菜单
//		fillListMenu(actionGroup);// 加入视图的下拉菜单
	}

	private List<Notebook> getUserNoteBookList() {
		List<Notebook> result = null;
		try {
			result = everNoteHelp.listNoteBooks();
		} catch (Exception e) {
		}

		return result;
	}

	private List<Tag> getUserTagList() {
		List<Tag> result = null;
		try {
			result = everNoteHelp.listTags();
		} catch (Exception e) {
		}

		return result;
	}

	@Override
	public void setFocus() {
	}

	/**
	 * 加入视图的导航栏按钮
	 */
	private void fillViewAction(NoteActionGroup actionGroup) {
		IActionBars bars = getViewSite().getActionBars();
		actionGroup.fillActionBars(bars);
	}

	/**
	 * 加入视图的下拉菜单
	 */
	private void fillViewMenu(NoteActionGroup actionGroup) {
		IMenuManager menu = getViewSite().getActionBars().getMenuManager();
		actionGroup.fillContextMenu(menu);
	}

	/**
	 * 加入列表List的右键菜单
	 */
	private void fillListMenu(NoteActionGroup actionGroup) {
		MenuManager menu1 = new MenuManager();
		// Menu m = menu1.createContextMenu(list);
		// list.setMenu(m);
		actionGroup.fillContextMenu(menu1);
	}

}
