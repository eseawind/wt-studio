package com.wt.studio.plugin.note.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * 
 * <pre>
 * 业务名:开发视图
 * 功能说明: 开发视图
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class NotePerspective implements IPerspectiveFactory {
	private IPageLayout factory;

	/**
	 * 构造函数
	 */
	public NotePerspective() {
		super();
	}

	@Override
	public void createInitialLayout(IPageLayout layout) {

		this.factory = layout;
		addTopLeftViews();
		addTopRightViews();
		addBottomViews();
		addActionSets();
		addPerspectiveShortcuts();
		addNewWizardShortcuts();
		addViewShortcuts();
	}

	/**
	 * 
	 * 方法说明：左视图
	 * 
	 */
	private void addTopLeftViews() {
		IFolderLayout left = factory.createFolder("left", IPageLayout.LEFT,
				(float) 0.22, factory.getEditorArea());
		left.addView("com.hirisun.ide.plugin.note.views.NoteView");
	}

	/**
	 * 
	 * 方法说明：右视图
	 * 
	 */
	private void addTopRightViews() {
		IFolderLayout topRight = factory.createFolder("topright",
				IPageLayout.RIGHT, (float) 0.82, factory.getEditorArea());

		topRight.addView("org.eclipse.ui.views.ContentOutline");

		IFolderLayout topRight2 = factory.createFolder("rbottom",
				IPageLayout.BOTTOM, 0.5f, "topright");

		topRight2.addView("org.eclipse.mylyn.tasks.ui.views.tasks");
	}

	/**
	 * 
	 * 方法说明：中间左视图
	 * 
	 */
	private void addBottomViews() {
		IFolderLayout bottom = factory.createFolder("bottom",
				IPageLayout.BOTTOM, 0.72f, factory.getEditorArea());
		bottom.addView("com.hirisun.ide.plugin.tools.views.CommonView");

	}

	/**
	 * 
	 * 方法说明：当前视图菜单项
	 * 
	 */
	private void addActionSets() {
	}

	/**
	 * 
	 * 方法说明：当前透视图默认选项
	 * 
	 */
	private void addPerspectiveShortcuts() {
		factory.addPerspectiveShortcut("com.hirisun.ide.plugin.platform.perspective.DevPerspective");
		factory.addPerspectiveShortcut("com.hirisun.ide.plugin.note.perspective.NotePerspective");
		factory.addPerspectiveShortcut("com.hirisun.ide.plugin.platform.perspective.DebugPerspective");
		factory.addPerspectiveShortcut("org.eclipse.team.ui.TeamSynchronizingPerspective");
	}

	/**
	 * 
	 * 方法说明：New时快捷方式
	 * 
	 */
	private void addNewWizardShortcuts() {

	}

	/**
	 * 
	 * 方法说明：当前视图默认选项
	 * 
	 */
	private void addViewShortcuts() {

	}

}
