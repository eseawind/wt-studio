package com.wt.studio.plugin.platform.actions;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.wt.studio.plugin.platform.Activator;
import com.wt.studio.plugin.platform.util.HeaProjectModel;

/**
 * 
 * <pre>
 * 业务名:导出包
 * 功能说明: 导出包
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class UpdateLibsAction implements IObjectActionDelegate,
		IWorkbenchWindowActionDelegate {
	@SuppressWarnings("unused")
	private Shell shell;
	private HeaProjectModel heaProjectModel;

	/**
	 * 继承方法
	 * 
	 * @param action
	 *            action
	 * @param targetPart
	 *            targetPart
	 * 
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * 继承方法
	 * 
	 * @param action
	 *            action
	 */
	public void run(IAction action) {
		try {
			Activator.getDefault().scheduleClasspathUpdateJob(
					heaProjectModel.getProject());

			heaProjectModel.refresh();
		} catch (Throwable e) {
			Activator.log(e);
		}
	}

	/**
	 * 实现方法
	 * 
	 * @param action
	 *            action
	 * @param selection
	 *            selection
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		Object obj = ((IStructuredSelection) selection).getFirstElement();
		if (obj instanceof Project || obj instanceof JavaProject) {
			heaProjectModel = new HeaProjectModel(selection);
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void init(IWorkbenchWindow arg0) {

	}

}
