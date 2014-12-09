package com.wt.studio.plugin.platform.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.wt.studio.plugin.platform.p2.InstallNewSoftwareHandler;

public class HEAStudioInstallAction implements IObjectActionDelegate,
		IWorkbenchWindowActionDelegate {
	
	private IWorkbenchWindow window = null;

	@Override
	public void run(IAction arg0) {
		InstallNewSoftwareHandler e = new InstallNewSoftwareHandler();
		e.execute(null);
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void init(IWorkbenchWindow arg0) {
		window = arg0;
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {

	}

}
