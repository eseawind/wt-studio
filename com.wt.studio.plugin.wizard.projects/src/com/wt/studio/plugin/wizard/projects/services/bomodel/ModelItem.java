package com.wt.studio.plugin.wizard.projects.services.bomodel;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;

import com.wt.studio.plugin.wizard.projects.services.util.BOModel;

public interface ModelItem {
	/**
	 * 获取子菜单
	 * @param tree
	 * @return
	 */
	public Menu getSubMenu(Tree tree);

	/**
	 * 生成通用Java脚本
	 * @param model
	 * @return
	 */
	public String getCommonJavaCode(BOModel model);
	
	/**
	 * 生成Java脚本
	 * @param model
	 * @return
	 */
	public String getAfterJavaCode(BOModel model);	
	
	/**
	 * 生成Java脚本
	 * @param model
	 * @return
	 */
	public String getBeforeJavaCode(BOModel model);		
}
