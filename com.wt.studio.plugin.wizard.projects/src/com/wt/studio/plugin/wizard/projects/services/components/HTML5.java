package com.wt.studio.plugin.wizard.projects.services.components;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.wizard.projects.services.util.HTML5Model;

public class HTML5 implements Component {
	private ComponentType type = ComponentType.HTML5;

	private static String[] popmenus = new String[] { "New TabStrip",
			"New IN Param" };

	public Menu getSubMenu(final Tree tree) {
		Menu menu = null;
		if (menu != null)
			return menu;

		menu = new Menu(tree);
		tree.setMenu(menu);
		boolean flag = false;
		for (TreeItem item : tree.getSelection()[0].getItems()) {
			if (item.getText(0).equals("TabStrip")) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			MenuItem menu1 = new MenuItem(menu, SWT.PUSH);
			menu1.setText(popmenus[0]);
			menu1.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event e) {
					TreeItem i = new TreeItem(tree.getSelection()[0], SWT.NULL);
					i.setText(ComponentType.TabStrip.toString());
					tree.getSelection()[0].setExpanded(true);
				}
			});
		}

		MenuItem menu2 = new MenuItem(menu, SWT.PUSH);
		menu2.setText(popmenus[1]);
		menu2.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {

				TreeItem i = new TreeItem(tree.getSelection()[0], SWT.NULL);
				i.setText(ComponentType.InParam.toString());
				tree.getSelection()[0].setExpanded(true);
			}
		});
		return menu;
	}

	public String getBeforeHTML5Code(HTML5Model model) {
		return "";
	}

	public String getAfterHTML5Code(HTML5Model model) {
		return "";

	}

	@Override
	public String getJavaScriptCode(HTML5Model model) {
		return "";
	}

	public static String getCommonBeforeJavaScriptCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();
		if (model.getType() == ComponentType.InParam) {
			sb.append("\n	var " + model.getFielddesc() + " =  $.getUrlVar(\""
					+ model.getFielddesc() + "\")");
		}
		for (int i = 0; i < model.getModels().size(); i++) {
			sb.append(getCommonBeforeJavaScriptCode(model.getModels().get(i)));
		}
		return sb.toString();
	}

	public static String getCommonJavaScriptCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("	function listViewInit(e) { \n");
		if (model.getModels() == null)
			return "";

		sb.append("		$.ajax({\n");
		sb.append("			url:\"/" + model.getPname() + "/rest/app/"
				+ model.getMname() + "/getDataModel\",\n");
		sb.append("			type:\"POST\",\n");
		sb.append("			data:{"
				+ StringUtils.removeEnd(getInParamsForJSON(model), ",")
				+ "},\n");
		sb.append("			success:function(data){\n");
		sb.append("				var viewModel = new kendo.observable(data);\n");
		sb.append("				kendo.bind($(\"span\"), viewModel);\n");

		for (int i = 0; i < model.getModels().size(); i++) {
			sb.append(getChildCommonJavaScriptCode(model.getModels().get(i)));
		}

		sb.append("			}\n");
		sb.append("		})\n");

		sb.append("	}\n");
		return sb.toString();
	}

	public static String getInParamsForJSON(HTML5Model model) {
		StringBuilder sb = new StringBuilder();
		if (model.getType() == ComponentType.InParam) {
			sb.append("\"" + model.getFielddesc() + "\" : "
					+ model.getFielddesc() + ",");
		}
		for (int i = 0; i < model.getModels().size(); i++) {
			sb.append(getInParamsForJSON(model.getModels().get(i)));
		}
		return sb.toString();
	}

	private static String getChildCommonJavaScriptCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();
		if (model.getModels() == null)
			return "";
		if (model.getType() == ComponentType.TemplateSubTitle) {
			sb.append("		e.view.element.find(\"#templateListview"
					+ model.getId() + "\").kendoMobileListView({ \n");
			sb.append("	             dataSource :  new kendo.data.HierarchicalDataSource({data : data."
					+ model.getId() + "}), \n");
			sb.append("	             template : $(\"#dataTemplate"
					+ model.getId() + "\").html()}); \n");
		}

		for (int i = 0; i < model.getModels().size(); i++) {
			sb.append(getChildCommonJavaScriptCode(model.getModels().get(i)));
		}
		return sb.toString();
	}

	@Override
	public String getJavaCode(HTML5Model model) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n")
				.append("	@POST\n")
				.append("	@Path(\"/getDataModel\")\n")
				.append("	public Object getDataModel("
						+ StringUtils.removeEnd(getInParamsForJAVA(model), ",")
						+ ")\n").append("	{\n").append("	    return null;\n")
				.append("	}\n");
		return sb.toString();
	}

	public static String getInParamsForJAVA(HTML5Model model) {
		StringBuilder sb = new StringBuilder();
		if (model.getType() == ComponentType.InParam) {
			sb.append("@FormParam(\"" + model.getFielddesc() + "\") String "
					+ model.getFielddesc() + ",");
		}
		for (int i = 0; i < model.getModels().size(); i++) {
			sb.append(getInParamsForJAVA(model.getModels().get(i)));
		}
		return sb.toString();
	}

	@Override
	public String getAfterJavaCode(HTML5Model model) {
		return "";
	}

}
