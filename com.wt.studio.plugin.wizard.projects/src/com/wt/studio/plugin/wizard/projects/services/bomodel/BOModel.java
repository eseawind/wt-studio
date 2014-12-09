package com.wt.studio.plugin.wizard.projects.services.bomodel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class BOModel implements ModelItem {
	private ModelType type = ModelType.Model;
	private static String[] popmenus = new String[] { "New Object"};
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
				i.setText(ModelType.Object.toString());
				tree.getSelection()[0].setExpanded(true);
			}
		});
		
		
		return menu;
	}

	@Override
	public String getCommonJavaCode(
			com.wt.studio.plugin.wizard.projects.services.util.BOModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAfterJavaCode(
			com.wt.studio.plugin.wizard.projects.services.util.BOModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBeforeJavaCode(
			com.wt.studio.plugin.wizard.projects.services.util.BOModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
