package com.wt.studio.plugin.wizard.projects.services.components;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.util.HTML5Model;

public class InParam implements Component {
	private ComponentType type = ComponentType.InParam;
	
	private static String[] popmenus = new String[] { "Delete IN Param"};
	
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
			public void handleEvent(Event arg0) {
				TreeItem i = tree.getSelection()[0];
				i.removeAll();
				i.dispose();
			}
		});
		return menu;
	}

	@Override
	public String getBeforeHTML5Code(HTML5Model model) {
		return "";
	}

	@Override
	public String getAfterHTML5Code(HTML5Model model) {
		return "";
	}

	@Override
	public String getJavaScriptCode(HTML5Model model) {
		return "";
	}

	@Override
	public String getJavaCode(HTML5Model model) {
		return "";
	}

	@Override
	public String getAfterJavaCode(HTML5Model model) {
		return "";
	}

}
