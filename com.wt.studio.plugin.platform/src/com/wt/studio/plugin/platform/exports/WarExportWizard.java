package com.wt.studio.plugin.platform.exports;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

import com.wt.studio.plugin.platform.util.HeaProjectHelp;
import com.wt.studio.plugin.platform.util.HeaProjectModel;

/**
 * 
 * <pre>
 * 业务名:导出包向导
 * 功能说明: 导出包向导
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
@SuppressWarnings("unused")
public class WarExportWizard extends Wizard implements IExportWizard
{

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

	private WarExportPage firstPage;
	private HeaProjectModel heaProjectModel;

	/**
	 * 构造函数
	 * 
	 * @param heaProjectModel
	 *            调用jiazai路径的类
	 */
	public WarExportWizard(HeaProjectModel heaProjectModel)
	{
		super();
		this.heaProjectModel = heaProjectModel;
		setNeedsProgressMonitor(true);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection arg1)
	{
		this.workbench = workbench;
	}

	/**
	 * 继承方法
	 * 
	 * @param config
	 *            config
	 * @param propertyName
	 *            propertyName
	 * @param data
	 *            data
	 */
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException
	{
		this.config = config;
	}

	@Override
	public boolean performFinish()
	{
		final String uumrest = firstPage.getUumCombo();

		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			protected void execute(IProgressMonitor monitor) throws CoreException
			{
				 monitor.beginTask("generate", 100);	// 100表示进度条被分成100份
//	                for(int i=0;i<3000;i++){
//	                    if(monitor.isCanceled()){
//	                        return;
//	                    }
//	                   	// 表示进度每次完成多少份，会相应增加
//	                    try {
//							Thread.sleep(50);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//	                }
//	                monitor.done();

				// 1 拷贝文件内容
				HeaProjectHelp.copyPorectFile(heaProjectModel);
				 monitor.worked(10);
				// 2设置UUM REST 文件
				HeaProjectHelp.settingUUMAddr(heaProjectModel, uumrest);
				monitor.worked(10);
				// 3设置数据库连接为 JNDI
				HeaProjectHelp.settingJNDI(heaProjectModel, firstPage);
				monitor.worked(10);
				// 4压缩文件内容
				HeaProjectHelp.exportWarfile(heaProjectModel);
//				monitor.worked(70);
				for(int i=0;i<70;i++){
                    if(monitor.isCanceled()){
                        return;
                    }
                   	// 表示进度每次完成多少份，会相应增加
                    monitor.worked(2);
                    try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
				monitor.done();
				Display.getDefault().asyncExec(new Runnable() {
				    @Override
				    public void run() {
				      MessageDialog.openInformation(null, "消息框", "打包完成！");
				    }
				  });
				
				// 刷新工程
				heaProjectModel.refresh();
			}
		};
		
		/*
		 * This isn't as robust as the code in the BasicNewProjectResourceWizard
		 * class. Consider beefing this up to improve error handling.
		 */
		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}

		return true;
	}

	@Override
	public void addPages()
	{
		this.setWindowTitle("新建-向导");
		firstPage = new WarExportPage(heaProjectModel);
		this.addPage(firstPage);

	}

	@Override
	public boolean canFinish()
	{
		if (this.getContainer().getCurrentPage() != firstPage)
			return false;
		return super.canFinish();
	}

	/**
	 * 
	 * 方法说明：修改文件
	 * 
	 * @param input
	 *            输出流
	 * @param key
	 *            要修改的文件
	 * @param value
	 *            修改后的文件
	 * @return 输出流
	 * @throws CoreException
	 */
	public InputStream openContentStream(InputStream input, String key, String value)
			throws CoreException
	{

		/* We want to be truly OS-agnostic */
		final String newline = System.getProperty("line.separator");
		String line;
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			try {
				while ((line = reader.readLine()) != null) {
					line = line.replaceAll("\\$\\{" + key + "\\}", value);
					sb.append(line);
					sb.append(newline);
				}
			} finally {
				reader.close();
			}
		} catch (IOException ioe) {
			IStatus status = new Status(IStatus.ERROR, "com.hirisun.ide.plugin.wizard.projects",
					IStatus.OK, ioe.getLocalizedMessage(), null);
			throw new CoreException(status);
		}
		return new ByteArrayInputStream(sb.toString().getBytes());

	}

}
