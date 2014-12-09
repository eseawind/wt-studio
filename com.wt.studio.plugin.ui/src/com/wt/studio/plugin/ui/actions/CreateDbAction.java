package com.wt.studio.plugin.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.wt.studio.plugin.ui.wizards.NewDataDesignerWizard;



public class CreateDbAction implements IObjectActionDelegate, IWorkbenchWindowActionDelegate
{

	private Object selected = null;
	@SuppressWarnings("rawtypes")
	private Class selectedClass = null;
	private  ISelection selection;

	/**
	 * Constructor for EasyExploreAction.
	 */
	public CreateDbAction()
	{
		super();
	}

	/**
	 * 继承方法
	 * 
	 * @param action
	 *            action
	 * @param targetPart
	 *            targetpart
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart)
	{
	}

	/**
	 * 继承方法
	 * 
	 * @param action
	 *            action
	 */
	public void run(IAction action)
	{
		NewDataDesignerWizard data=new NewDataDesignerWizard(this.selection);
		final WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), data);
		dialog.open();
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 * @param action
	 *            action
	 * @param selection
	 *            selection
	 */
	public void selectionChanged(IAction action, ISelection selection)
	{
		this.selection=selection;
	}

	@Override
	public void dispose()
	{

	}

	@Override
	public void init(IWorkbenchWindow arg0)
	{

	}

}
