package com.wt.studio.plugin.wizard.projects.services.components;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;

import com.wt.studio.plugin.wizard.projects.services.util.HTML5Model;

public interface Component {
	/**
	 * 获取子菜单
	 * @param tree
	 * @return
	 */
	public Menu getSubMenu(Tree tree);

	/**
	 * 获取模型前置HTML5代码
	 * @param model
	 * @return
	 */
	public String getBeforeHTML5Code(HTML5Model model);

	/**
	 * 获取模型后置HTML5代码
	 * @param model
	 * @return
	 */
	public String getAfterHTML5Code(HTML5Model model);
	
	/**
	 * 生成JavaScript脚本
	 * @param model
	 * @return
	 */
	public String getJavaScriptCode(HTML5Model model);
	
	/**
	 * 生成JavaScript脚本
	 * @param model
	 * @return
	 */
	public String getJavaCode(HTML5Model model);
	
	/**
	 * 生成JavaScript脚本
	 * @param model
	 * @return
	 */
	public String getAfterJavaCode(HTML5Model model);	
}
