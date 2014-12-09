package com.wt.studio.plugin.platform.perspective;

import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import com.wt.studio.plugin.platform.Activator;
import com.wt.studio.plugin.platform.preferences.PreferenceConstants;
import com.wt.studio.plugin.platform.preferences.UserPreferencePage;
import com.wt.studio.plugin.platform.startup.LoginWizard;

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
public class DevPerspective implements IPerspectiveFactory {
	private IPageLayout factory;

	/**
	 * 构造函数
	 */
	public DevPerspective() {
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

		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				boolean flag = false;

				try {
					String user = Activator.getDefault().getPreferenceStore()
							.getString(PreferenceConstants.USER_NAME);
					String pass = Activator.getDefault().getPreferenceStore()
							.getString(PreferenceConstants.USER_PWD);
					
					if (user == null || user.equals("")) {
						flag = false;
					} else {
						flag = UserPreferencePage.AcceptanceUUM(user, pass);
					}
				} catch (Exception e) {
					flag = false;
				}

				if (!flag) {

					LoginWizard wizard = new LoginWizard();
					WizardDialog dialog = new WizardDialog(Display.getCurrent()
							.getActiveShell(), wizard);
					dialog.open();
				}

			}

		});

	}

	/**
	 * 
	 * 方法说明：左视图
	 * 
	 */
	private void addTopLeftViews() {
		IFolderLayout left = factory.createFolder("left", IPageLayout.LEFT,
				(float) 0.23, factory.getEditorArea());
		left.addView("org.eclipse.jdt.ui.PackageExplorer");
		left.addView("org.eclipse.ui.navigator.ProjectExplorer");
	}

	/**
	 * 
	 * 方法说明：右视图
	 * 
	 */
	private void addTopRightViews() {
		IFolderLayout topRight = factory.createFolder("topright",
				IPageLayout.RIGHT, (float) 0.77, factory.getEditorArea());

		topRight.addView("org.eclipse.ui.views.ContentOutline");

		IFolderLayout topRight2 = factory.createFolder("rbottom",
				IPageLayout.BOTTOM, 0.5f, "topright");


		topRight2
				.addView("org.eclipse.datatools.connectivity.DataSourceExplorerNavigator");
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
		bottom.addView("org.eclipse.wst.server.ui.ServersView");
		bottom.addView("org.eclipse.ui.console.ConsoleView");
		bottom.addView("org.eclipse.ui.views.ProblemView");
		// bottom.addView("org.eclipse.pde.runtime.LogView");
		bottom.addView("org.eclipse.ui.views.PropertySheet");
		bottom.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
	}

	/**
	 * 
	 * 方法说明：当前视图菜单项
	 * 
	 */
	private void addActionSets() {
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
		factory.addNewWizardShortcut("com.hirisun.ide.plugin.wizard.projects.framework.HeaPorjectWizard");
		factory.addNewWizardShortcut("com.hirisun.ide.plugin.wizard.projects.business.BusinessPorjectWizard");
		factory.addNewWizardShortcut("com.hirisun.ide.plugin.wizard.projects.model.MultiModelWizard");
		factory.addNewWizardShortcut("com.hirisun.ide.plugin.wizard.projects.model.BusiModulelWizard");	
		factory.addNewWizardShortcut("com.hirisun.ide.plugin.querydesigner.wizard.QueryDesignerWizard");
		factory.addNewWizardShortcut("com.hirisun.ide.plugin.wizard.projects.services.ServiceWizard");

		factory.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewPackageCreationWizard");
		factory.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewClassCreationWizard");
		factory.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewInterfaceCreationWizard");
		factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");
	}

	/**
	 * 
	 * 方法说明：当前视图默认选项
	 * 
	 */
	private void addViewShortcuts() {
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
