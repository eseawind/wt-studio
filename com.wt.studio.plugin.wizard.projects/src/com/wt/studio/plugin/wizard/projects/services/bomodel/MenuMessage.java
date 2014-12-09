package com.wt.studio.plugin.wizard.projects.services.bomodel;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;

import com.wt.studio.plugin.wizard.projects.services.util.BOModel;

public class MenuMessage {
	
	public static ModelItem getInstance(String className) {
		Class<?> clazz = null;
		ModelItem com = null;
		try {
			clazz = Class
					.forName("com.hirisun.ide.plugin.wizard.projects.services.bomodel.BO"
							+ className);
			com = (ModelItem) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return com;
	}

	public static Menu getMenu(Tree tree, String className) { 
		return getInstance(className).getSubMenu(tree);
	}
	
	
	
	public static String getCommonJavaCode(BOModel model, String className) {
		return getInstance(className).getCommonJavaCode(model);
	}
	
	public static String getBeforeJavaCode(BOModel model, String className) {
		return getInstance(className).getBeforeJavaCode(model);
	}	
	
	public static String getAfterJavaCode(BOModel model, String className) {
		return getInstance(className).getAfterJavaCode(model);
	}

}
