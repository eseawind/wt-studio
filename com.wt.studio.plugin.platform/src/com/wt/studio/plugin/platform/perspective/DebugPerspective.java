package com.wt.studio.plugin.platform.perspective;

import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

/**
 * 
 * <pre>
 * 业务名:调试视图
 * 功能说明: 调试视图
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class DebugPerspective implements IPerspectiveFactory
{

	private IPageLayout factory;

	/**
	 * 构造函数
	 */
	public DebugPerspective()
	{
		super();
	}

	@Override
	public void createInitialLayout(IPageLayout layout)
	{
		// 默认当前窗体为隐藏
		// layout.setEditorAreaVisible(false);

		// 初始化IPageLayout
		this.factory = layout;
		// 注意添加顺序
		addTopLeftViews();
		addTopRightViews();
		addBottonViews();
		addRightViews();
		addActionSets();
		addPerspectiveShortcuts();
		addNewWizardShortcuts();
		addViewShortcuts();
	}

	/**
	 * 
	 * 方法说明：添加方法
	 * 
	 */
	private void addTopLeftViews()
	{
		IFolderLayout topLeft = factory.createFolder("leftTop", IPageLayout.TOP, 0.35f,
				factory.getEditorArea());
		topLeft.addView("org.eclipse.debug.ui.DebugView");
		topLeft.addView("org.eclipse.wst.server.ui.ServersView");
	}

	/**
	 * 
	 * 方法说明：右上视图
	 * 
	 */
	private void addTopRightViews()
	{
		IFolderLayout topRight = factory.createFolder("rightTop", IPageLayout.RIGHT, 0.50f,
				"leftTop");
		topRight.addView("org.eclipse.debug.ui.VariableView");
		topRight.addView("org.eclipse.debug.ui.BreakpointView");
	}

	/**
	 * 
	 * 方法说明：中部右视图
	 * 
	 */
	private void addRightViews()
	{
		IFolderLayout topRight = factory.createFolder("right", IPageLayout.RIGHT, 0.65f,
				factory.getEditorArea());
		topRight.addView("org.eclipse.ui.views.ContentOutline");
	}

	/**
	 * 
	 * 方法说明：底部视图
	 * 
	 */
	private void addBottonViews()
	{
		IFolderLayout bottom = factory.createFolder("bottom", IPageLayout.BOTTOM, 0.70f,
				factory.getEditorArea());
		bottom.addView("org.eclipse.ui.console.ConsoleView");
		bottom.addView("org.eclipse.mylyn.tasks.ui.views.tasks");
		bottom.addPlaceholder(IPageLayout.ID_BOOKMARKS);
	}

	/**
	 * 
	 * 方法说明：当前视图菜单项
	 * 
	 */
	private void addActionSets()
	{
		factory.addActionSet("org.eclipse.debug.ui.launchActionSet");
		factory.addActionSet("org.eclipse.debug.ui.debugActionSet");
		factory.addActionSet("org.eclipse.debug.ui.profileActionSet");
		factory.addActionSet("org.eclipse.jdt.debug.ui.JDTDebugActionSet");
		factory.addActionSet("org.eclipse.jdt.junit.JUnitActionSet");
		factory.addActionSet("org.eclipse.team.ui.actionSet");
		factory.addActionSet("org.eclipse.ant.ui.actionSet.presentation");
		factory.addActionSet(JavaUI.ID_ACTION_SET);
		factory.addActionSet(JavaUI.ID_ELEMENT_CREATION_ACTION_SET);
		factory.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
	}

	/**
	 * 
	 * 方法说明：当前透视图默认选项
	 * 
	 */
	private void addPerspectiveShortcuts()
	{
		factory.addPerspectiveShortcut("com.hirisun.ide.plugin.preferences.perspective.DevPerspective");
		factory.addPerspectiveShortcut("com.hirisun.ide.plugin.preferences.perspective.DebugPerspective");
		factory.addPerspectiveShortcut("org.eclipse.team.ui.TeamSynchronizingPerspective");
	}

	/**
	 * 
	 * 方法说明：New时快捷方式
	 * 
	 */
	private void addNewWizardShortcuts()
	{
		factory.addNewWizardShortcut("com.hirisun.ide.plugin.wizard.projects.business.BusinessPorjectWizard");
		factory.addNewWizardShortcut("com.hirisun.ide.plugin.wizard.projects.framework.HeaPorjectWizard");
		factory.addNewWizardShortcut("com.hirisun.ide.plugin.wizard.projects.model.BusiModulelProjectWizard");
		factory.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewPackageCreationWizard");
		factory.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewClassCreationWizard");
		factory.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewInterfaceCreationWizard");
		factory.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewEnumCreationWizard");
		factory.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewAnnotationCreationWizard");
		factory.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewSourceFolderCreationWizard");
		factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
	}

	/**
	 * 
	 * 方法说明：当前视图默认选项
	 * 
	 */
	private void addViewShortcuts()
	{
		factory.addShowViewShortcut("org.eclipse.ant.ui.views.AntView");
		factory.addShowViewShortcut("org.eclipse.team.ccvs.ui.AnnotateView");
		factory.addShowViewShortcut("org.eclipse.pde.ui.DependenciesView");
		factory.addShowViewShortcut("org.eclipse.jdt.junit.ResultView");
		factory.addShowViewShortcut("org.eclipse.team.ui.GenericHistoryView");
		factory.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
		factory.addShowViewShortcut(JavaUI.ID_PACKAGES);
		factory.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		factory.addShowViewShortcut(IPageLayout.ID_OUTLINE);
	}
}
