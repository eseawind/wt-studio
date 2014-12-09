package com.wt.studio.plugin.wizard.projects.data2model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;

import com.wt.studio.plugin.platform.util.HeaProjectModel;
import com.wt.studio.plugin.wizard.projects.uitl.HEABaseWizard;

public class Data2ModelWizard extends HEABaseWizard {

	private ModelPage modelPage;
	
	public Data2ModelWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	
	@Override
	public void addPages() {
		this.setWindowTitle("New Model Class Wizard");
		modelPage = new ModelPage();
		addPage(modelPage);

	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.setSelection(selection);
		this.setHeaProjectModel(new HeaProjectModel(selection));
	}

	@Override
	public void setInitializationData(IConfigurationElement arg0, String arg1,
			Object arg2) throws CoreException {

	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
