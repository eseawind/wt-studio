package com.wt.studio.plugin.ws.wizard;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.platform.util.HeaProjectModel;
import com.wt.studio.plugin.ws.Activator;



/**
 * 
 * <pre>
 * 业务名:
 * 功能说明: 导出包
 * 编写日期:	2013-11-25
 * 作者:	HEA IDE
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class ClazzPage extends WizardPage
{
	/**
	 * container
	 */
	private Composite container;

	private HeaProjectModel heaProjectModel;
	
	private Text wsdlurl;

	/**
	 * Hea首选项
	 */
	private static IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();

	/**
	 * 构造函数
	 * 
	 * @param heaProjectModel
	 *            heaProjectModel
	 */
	protected ClazzPage(HeaProjectModel heaProjectModel)
	{
		super("");
		this.heaProjectModel = heaProjectModel;
		setTitle("HEA WSDL2Java Tool");
		setDescription("WSDL2Java 属性设置");
	}

	@Override
	public void createControl(Composite parent)
	{
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		new Label(container, SWT.NULL).setText("WSDL URL:");
		wsdlurl = new Text(container, SWT.NULL);

		setControl(container);
		setPageComplete(false);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#getControl()
	 */
	public Control getControl()
	{
		return container;
	}

}
