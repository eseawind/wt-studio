package com.wt.studio.plugin.ws.wizard;

import org.apache.axis2.description.AxisService;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import com.wt.studio.plugin.platform.util.HeaProjectModel;

/**
 * 
 * <pre>
 * 业务名:
 * 功能说明: 
 * 编写日期:
 * 作者:	HEA IDE
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
@SuppressWarnings("unused")
public class ClazzWizard extends Wizard implements IExportWizard {

	/**
	 * 向导页
	 */
	private WizardNewProjectCreationPage wizardPage;

	/**
	 * workbench
	 */
	private IWorkbench workbench;

	/**
	 * config
	 */
	private IConfigurationElement config;

	private ClazzPage wsdlPage;
	private HeaProjectModel heaProjectModel;

	AxisService service = null;

	@Override
	public void addPages() {
		this.setWindowTitle("");
		wsdlPage = new ClazzPage(heaProjectModel);
		this.addPage(wsdlPage);

	}

	@Override
	public boolean canFinish() {
		return true;
	}

	@Override
	public void init(IWorkbench arg0, IStructuredSelection arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}

}
