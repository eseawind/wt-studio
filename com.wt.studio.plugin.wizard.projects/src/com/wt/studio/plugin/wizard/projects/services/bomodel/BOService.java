package com.wt.studio.plugin.wizard.projects.services.bomodel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.util.BOModel;

public class BOService implements ModelItem {
	private ModelType type = ModelType.Service;
	private static String[] popmenus = new String[] { "New Method" };
	
	@Override
	public Menu getSubMenu(final Tree tree) {
		Menu menu = null;
		if (menu != null)
			return menu;

		MenuItem menu2 = new MenuItem(menu, SWT.PUSH);
		menu2.setText(popmenus[1]);
		menu2.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				TreeItem i = new TreeItem(tree.getSelection()[0], SWT.NULL);
				i.setText(ModelType.Method.toString());
				tree.getSelection()[0].setExpanded(true);
			}
		});
		return menu;
	}

	@Override
	public String getCommonJavaCode(BOModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAfterJavaCode(BOModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBeforeJavaCode(BOModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
