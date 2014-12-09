package com.wt.studio.plugin.wizard.projects.services.components;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.util.HTML5Model;


public class TemplateListView implements Component{
	private ComponentType type = ComponentType.TemplateListView;

	private static String[] popmenus = new String[] { "Delete TemplateListView" };

	public Menu getSubMenu(final Tree tree) {
		Menu menu = null;
		if (menu != null)
			return menu;

		menu = new Menu(tree);
		tree.setMenu(menu);
		
		MenuItem delMenu = new MenuItem(menu, SWT.PUSH);
		delMenu.setText(popmenus[1]);
		delMenu.addListener(SWT.Selection, new Listener() {
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
		StringBuilder sb = new StringBuilder();
		sb.append("<ul id=\"templateListview"+ model.getId() +"\" data-role=\"listview\" data-template=\"listViewTemplate"+ model.getId() +"\"> \n")
		  .append("<script id=\"templateListview"+ model.getId() +"\" type=\"text/x-kendo-template\">")
		  .append("</script>");
		return sb.toString();
	}

	public String getAfterHTML5Code(HTML5Model model) {
		return "</ul>\n";
	}
	
	@Override
	public String getJavaScriptCode(HTML5Model model) {
		return "";
	}

	@Override
	public String getJavaCode(HTML5Model model) {
		return  "";
	}

	@Override
	public String getAfterJavaCode(HTML5Model model) {
		return  "";
	}	

}
