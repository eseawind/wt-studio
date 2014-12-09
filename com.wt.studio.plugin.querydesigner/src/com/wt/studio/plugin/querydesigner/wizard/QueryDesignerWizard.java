package com.wt.studio.plugin.querydesigner.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class QueryDesignerWizard extends Wizard implements INewWizard, IExecutableExtension
{

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1)
	{
		// TODO Auto-generated method stub
		// CommonEclipseUtil.getConnectionProfiles();
	}

	@Override
	public void setInitializationData(IConfigurationElement arg0, String arg1, Object arg2)
			throws CoreException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
