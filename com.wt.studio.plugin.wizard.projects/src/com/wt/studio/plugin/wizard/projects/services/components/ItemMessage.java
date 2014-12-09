package com.wt.studio.plugin.wizard.projects.services.components;

import java.lang.reflect.Method;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;

import com.wt.studio.plugin.wizard.projects.services.util.HTML5Model;

public class ItemMessage {
	
	public static Component getInstance(String className) {
		Class<?> clazz = null;
		Component com = null;
		try {
			clazz = Class
					.forName("com.hirisun.ide.plugin.wizard.projects.services.components."
							+ className);
			com = (Component) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return com;
	}

	public static Menu getMenu(Tree tree, String className) { 
		return getInstance(className).getSubMenu(tree);
	}
	
	public static String getBeforeHTML5Code(HTML5Model model, String className) {
		return getInstance(className).getBeforeHTML5Code(model);
	}
	
	public static String getAfterHTML5Code(HTML5Model model, String className) {
		return getInstance(className).getAfterHTML5Code(model);
	}	
	
	public static String getJavaScriptCode(HTML5Model model, String className) {
		return getInstance(className).getJavaScriptCode(model);
	}
	
	public static String getJavaCode(HTML5Model model, String className) {
		return getInstance(className).getJavaCode(model);
	}
	
	public static String getAfterJavaCode(HTML5Model model, String className) {
		return getInstance(className).getAfterJavaCode(model);
	}

}
