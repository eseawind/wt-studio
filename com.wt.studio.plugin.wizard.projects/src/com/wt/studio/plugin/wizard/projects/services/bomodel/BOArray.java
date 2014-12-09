package com.wt.studio.plugin.wizard.projects.services.bomodel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.util.BOModel;

public class BOArray implements ModelItem {
	private ModelType type = ModelType.Array;

	private static String[] popmenus = new String[] { "New Object",
			"Delete Array" };

	@Override
	public Menu getSubMenu(final Tree tree) {
		Menu menu = null;
		if (menu != null)
			return menu;

		menu = new Menu(tree);
		
		if (tree.getSelection()[0].getItems().length == 0) {
			tree.setMenu(menu);
			MenuItem menu1 = new MenuItem(menu, SWT.PUSH);
			menu1.setText(popmenus[0]);
			menu1.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event e) {
					TreeItem i = new TreeItem(tree.getSelection()[0], SWT.NULL);
					i.setText(ModelType.Object.toString());
					tree.getSelection()[0].setExpanded(true);
				}
			});
		}

		MenuItem menu2 = new MenuItem(menu, SWT.PUSH);
		menu2.setText(popmenus[1]);
		menu2.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				TreeItem i = tree.getSelection()[0];
				i.removeAll();
				i.dispose();
			}
		});
		return menu;
	}

	@Override
	public String getCommonJavaCode(BOModel model) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getAfterJavaCode(BOModel model) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getBeforeJavaCode(BOModel model) {
		// TODO Auto-generated method stub
		return "";
	}

}
