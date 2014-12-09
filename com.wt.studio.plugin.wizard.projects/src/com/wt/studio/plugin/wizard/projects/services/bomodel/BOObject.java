package com.wt.studio.plugin.wizard.projects.services.bomodel;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.util.BOModel;

public class BOObject implements ModelItem {
	private ModelType type = ModelType.Object;

	private static String[] popmenus = new String[] { "String", "Integer",
			"Double", "Array", "Object", "Delete Object" };

	@Override
	public Menu getSubMenu(final Tree tree) {
		Menu menu = null;
		if (menu != null)
			return menu;

		menu = new Menu(tree);
		tree.setMenu(menu);
		MenuItem menu1 = new MenuItem(menu, SWT.PUSH);
		menu1.setText(popmenus[0]);
		menu1.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				TreeItem i = new TreeItem(tree.getSelection()[0], SWT.NULL);
				i.setText(ModelType.String.toString());
				tree.getSelection()[0].setExpanded(true);
			}
		});

		MenuItem menu2 = new MenuItem(menu, SWT.PUSH);
		menu2.setText(popmenus[1]);
		menu2.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				TreeItem i = new TreeItem(tree.getSelection()[0], SWT.NULL);
				i.setText(ModelType.Integer.toString());
				tree.getSelection()[0].setExpanded(true);
			}
		});

		MenuItem menu3 = new MenuItem(menu, SWT.PUSH);
		menu3.setText(popmenus[2]);
		menu3.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				TreeItem i = new TreeItem(tree.getSelection()[0], SWT.NULL);
				i.setText(ModelType.Double.toString());
				tree.getSelection()[0].setExpanded(true);
			}
		});

		MenuItem menu4 = new MenuItem(menu, SWT.PUSH);
		menu4.setText(popmenus[3]);
		menu4.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				TreeItem i = new TreeItem(tree.getSelection()[0], SWT.NULL);
				i.setText(ModelType.Array.toString());
				TreeItem child = new TreeItem(i, SWT.NULL);
				child.setText(ModelType.Object.toString());
				i.setExpanded(true);
				tree.getSelection()[0].setExpanded(true);
			}
		});

		MenuItem menu5 = new MenuItem(menu, SWT.PUSH);
		menu5.setText(popmenus[4]);
		menu5.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				TreeItem i = new TreeItem(tree.getSelection()[0], SWT.NULL);
				i.setText(ModelType.Object.toString());
				tree.getSelection()[0].setExpanded(true);
			}
		});

		if (tree.getSelection()[0].getParentItem().getText(1).equals("Array")) {
			MenuItem menu6 = new MenuItem(menu, SWT.PUSH);
			menu6.setText(popmenus[5]);
			menu6.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event e) {
					TreeItem i = tree.getSelection()[0];
					i.removeAll();
					i.dispose();
				}
			});
		}

		return menu;
	}

	@Override
	public String getCommonJavaCode(BOModel model) {
		return "";
	}

	@Override
	public String getAfterJavaCode(BOModel model) {
		String s = "\n    }";
		return s;
	}

	@Override
	public String getBeforeJavaCode(BOModel model) {
		String s = "\n    public class BO" + model.getBoname() + " {\n";
		return s;
	}

}
