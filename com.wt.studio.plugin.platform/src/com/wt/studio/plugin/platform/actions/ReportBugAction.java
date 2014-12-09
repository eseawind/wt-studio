package com.wt.studio.plugin.platform.actions;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

public class ReportBugAction implements IObjectActionDelegate,
		IWorkbenchWindowActionDelegate {
	private static String HIRISN_PM_URL = "http://pm.hirisun.com/browse/CBB";
	private IWorkbenchWindow window = null;

	@Override
	public void run(IAction arg0) {
		IWorkbenchBrowserSupport browserSupport = window.getWorkbench()
				.getBrowserSupport();
		URL webURL = null;

		try {
			webURL = new URL(HIRISN_PM_URL);
		} catch (MalformedURLException e) {
		}

		IWebBrowser browser;
		try {
			browser = browserSupport.createBrowser(
					IWorkbenchBrowserSupport.AS_EXTERNAL, "", "Hirisun CBB",
					"Hirisun PM");
			browser.openURL(webURL);
		} catch (PartInitException e) {
		}

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
