package com.wt.studio.plugin.ws.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.wt.studio.plugin.platform.Activator;
import com.wt.studio.plugin.platform.util.HeaProjectModel;
import com.wt.studio.plugin.ws.codegen.eclipse.CodeGenWizard;

/**
 * 
 * <pre>
 * 业务名:导出包
 * 功能说明: 导出包
 * 编写日期:	2013-9-25
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
@SuppressWarnings("restriction")
public class WSDL2JavaAction implements IObjectActionDelegate, IWorkbenchWindowActionDelegate
{

	private Object selected = null;
	@SuppressWarnings("rawtypes")
	private Class selectedClass = null;
	private IWorkbenchWindow window = null;
	private HeaProjectModel heaProjectModel;	

	/**
	 * Constructor for EasyExploreAction.
	 */
	public WSDL2JavaAction()
	{
		super();
	}

	@Override
	public void run(IAction arg0) {
		try {
			CodeGenWizard wizard = new CodeGenWizard();
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.open();
		} catch (Throwable e) {
			Activator.log(e);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		heaProjectModel = new HeaProjectModel(selection);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void init(IWorkbenchWindow arg0) {
		
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		
	}

}