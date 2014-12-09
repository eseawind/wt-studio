package com.wt.studio.plugin.wizard.projects.services.components;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.util.HTML5Model;

public class TemplateSubTitle implements Component {
	private ComponentType type = ComponentType.TemplateSubTitle;

	private static String[] popmenus = new String[] { "Delete TemplateSubTitle" };

	public Menu getSubMenu(final Tree tree) {
		Menu menu = null;
		if (menu != null)
			return menu;

		menu = new Menu(tree);
		tree.setMenu(menu);

		MenuItem delMenu = new MenuItem(menu, SWT.PUSH);
		delMenu.setText(popmenus[0]);
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
		sb.append("<li>" + model.getFielddesc())
		  .append("<ul id=\"templateListview"
						+ model.getId()
						+ "\" data-role=\"listview\" data-template=\"dataTemplate"
						+ model.getId() + "\"> \n")
		  .append("<script id=\"dataTemplate" + model.getId() + "\" type=\"text/x-kendo-template\">")
		  .append("</script>");
		return sb.toString();
	}

	public String getAfterHTML5Code(HTML5Model model) {
		return "</ul></li>\n";
	}

	public String getJavaScriptCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n")
		  .append("	var dtList" + model.getId()  +" = new kendo.data.HierarchicalDataSource({\n")
		  .append("	    transport : {\n")
		  .append("	        read : {\n")
		  .append("	             url:\"/"+ model.getPname() +"/rest/app/"+ model.getMname() + "/getList"+ model.getId() +"\",\n")
		  .append("		         dataType : \"json\",\n")
		  .append("		         type : \"POST\"\n")
		  .append("	        }\n")
		  .append("	    },\n")
		  .append("	    schema : {\n")
		  .append("	        model : {\n")
		  .append("	        }\n")
		  .append("	    }\n")
		  .append("	});\n");
		return sb.toString();
	}

	@Override
	public String getJavaCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n")
		  .append("	@POST\n")
		  .append("	@Path(\"/getList" + model.getId() + "\")\n")
		  .append("	public List<Object> getList"+ model.getId()  +"()\n")
		  .append("	{\n")
		  .append("	    //TODO Call WebService\n")
		  .append("	    return null;\n")
		  .append("	}\n");
		return sb.toString();
	}

	@Override
	public String getAfterJavaCode(HTML5Model model) {
		return  "";
	}

}
