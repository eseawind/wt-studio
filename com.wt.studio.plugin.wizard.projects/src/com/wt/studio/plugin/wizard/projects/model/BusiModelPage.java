package com.wt.studio.plugin.wizard.projects.model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.wizard.projects.uitl.Consistant;
import com.wt.studio.plugin.wizard.projects.uitl.HEABasePage;

/**
 * <pre>
 * 业务名:框架向导
 * 功能说明: 数据库信息页
 * 编写日期:	2012-12-17
 * 作者:	DongYibo
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class BusiModelPage extends HEABasePage {
	/**
	 * container
	 */
	private Composite container;

	/**
	 * 模块名称
	 */
	private Text txtName;

	/**
	 * 模块代码
	 */
	private Text txtCode;

	/**
	 * 包代码
	 */
	private Text txtPak;

	private String projectName;

	/**
	 * 页面设置
	 */
	public BusiModelPage() {
		super("Other properties");
		setTitle("HEA Busi Module");
		setDescription("设置业务名称、数据表等内容");
	}

	public BusiModelPage(String projectName) {
		super("Other properties");
		setTitle("HEA Busi Module");
		setDescription("设置业务名称、数据表等内容");
		this.projectName = projectName;
	}

	public Text getTxtPak() {
		return txtPak;
	}

	public void setTxtPak(Text txtPak) {
		this.txtPak = txtPak;
	}

	/**
	 * 创建页面
	 * 
	 * @param parent
	 *            parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.None);
		container.setLayout(new GridLayout(1, false));

		Group gpConn = new Group(container, SWT.NONE);
		gpConn.setText("模块信息");
		gpConn.setLayout(new RowLayout());

		GridData gdc = new GridData();
		gdc.minimumWidth = 1000;
		gdc.minimumHeight = 250;
		gdc.verticalAlignment = GridData.FILL;
		gdc.grabExcessHorizontalSpace = true;
		gdc.grabExcessVerticalSpace = true;
		gpConn.setLayoutData(gdc);
		gpConn.setLayout(new GridLayout(2, false));

		Label labName = new Label(gpConn, SWT.LEFT);
		labName.setText("模块名称:");
		txtName = new Text(gpConn, SWT.BORDER);
		txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				boolean result = validText();
				setPageComplete(result);
			}
		});

		Label labPak = new Label(gpConn, SWT.LEFT);
		labPak.setText("包名称:");
		txtPak = new Text(gpConn, SWT.BORDER);
		txtPak.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtPak.setText(Consistant.COMP_PRIX + projectName);

		txtPak.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				boolean result = validText();
				setPageComplete(result);
			}
		});

		Label labCode = new Label(gpConn, SWT.LEFT);
		labCode.setText("模块描述:");
		txtCode = new Text(gpConn, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER
				| SWT.WRAP);
		GridData gridData = new GridData();
		gridData.verticalSpan = 10; // 跨两行
		gridData.grabExcessHorizontalSpace = true; // 抢占垂直方向额外空间
		gridData.horizontalAlignment = GridData.FILL; // 水平方向充满
		gridData.grabExcessVerticalSpace = true; // 抢占水平方向额外空间
		gridData.verticalAlignment = GridData.FILL; // 垂直方向充满
		txtCode.setLayoutData(gridData);

		setControl(container);
		setPageComplete(false);

	}

	public Text getTxtCode() {
		return txtCode;
	}

	public Text getTxtName() {
		return txtName;
	}

	public void setTxtCode(Text txtCode) {
		this.txtCode = txtCode;
	}

	public void setTxtName(Text txtName) {
		this.txtName = txtName;
	}

	public boolean validText() {
		boolean result0 = false;
		boolean result1 = false;
		if (!"".equals(getTxtName().getText())) {
			result0 = true;
		}
		if (!"".equals(getTxtPak().getText())) {
			result1 = true;
		}
		return result0 && result1;
	}

}
