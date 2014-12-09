package com.wt.studio.plugin.wizard.projects.services.components;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.util.HTML5Model;

public class SubTitle implements Component{
	private ComponentType type = ComponentType.SubTitle;

	private static String[] popmenus = new String[] { "New Field", "Delete SubTitle" };

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
				i.setText(ComponentType.Field.toString());
				i.setText(1,"title");
				i.setText(2,"Label");
				tree.getSelection()[0].setExpanded(true);
			}
		});
		
		
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

	public String getBeforeHTML5Code(HTML5Model model) {
		return "<li>" + model.getFielddesc() + "\n<ul>\n";
	}

	public String getAfterHTML5Code(HTML5Model model) {
		return "</ul>\n" + "</li>\n";
	}
	
	@Override
	public String getJavaScriptCode(HTML5Model model) {
		return "";
	}

	@Override
	public String getJavaCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");	
		return sb.toString();
	}

	@Override
	public String getAfterJavaCode(HTML5Model model) {
		return  "";
	}	

}
