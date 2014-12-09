package com.wt.studio.plugin.wizard.projects.framework;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * <pre>
 * 业务名:框架向导
 * 功能说明:参数页
 * 编写日期:	2012-12-17
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class HeaAttributePage extends WizardPage
{

	/**
	 * container
	 */
	private Composite container;
	// HEA
	private Text txtHea;
	// 数据库类型
	private Text txtAbout;

	public String getTxtHea()
	{
		return txtHea.getText();
	}

	public String getTxtAbout()
	{
		return txtAbout.getText();
	}

	/**
	 * 页面设置
	 */
	public HeaAttributePage()
	{
		super("Other properties");
		setTitle("HEA 框架属性设置");
		setDescription("基于 HEA FrameWork V2.3 设置标题、版权、样式、主题、Logo、Banner等信息 ");
		this.setPageComplete(false);
	}

	/**
	 * 创建页面
	 * 
	 * @param parent
	 *            parent
	 */
	public void createControl(Composite parent)
	{
		container = new Composite(parent, SWT.None);
		container.setLayout(new GridLayout(2, false));

		Label label = new Label(container, SWT.LEFT);
		label.setText("框架标题:");

		txtHea = new Text(container, SWT.BORDER);
		txtHea.setFocus();
		txtHea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtHea.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0)
			{
				if (!"".equals(getTxtHea()) && !"".equals(getTxtAbout())) {
					setPageComplete(true);
				} else {
					setPageComplete(false);
				}
			}
		});

		new Label(container, SWT.LEFT).setText("版权信息:");
		txtAbout = new Text(container, SWT.BORDER);
		txtAbout.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtAbout.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0)
			{
				if (!"".equals(getTxtHea()) && !"".equals(getTxtAbout())) {
					setPageComplete(true);
				} else {
					setPageComplete(false);
				}
			}
		});
		setControl(container);
		setPageComplete(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#getControl()
	 */
	public Control getControl()
	{
		return container;
	}
}
