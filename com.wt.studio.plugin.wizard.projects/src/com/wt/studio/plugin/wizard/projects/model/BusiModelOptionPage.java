package com.wt.studio.plugin.wizard.projects.model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

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
public class BusiModelOptionPage extends HEABasePage {
	/**
	 * container
	 */
	private Composite container;

	private Button modelBtn;
	private Button daoBtn;
	private Button serviceBtn;
	private Button mainPageBtn;
	private Button updatePageBtn;

	/**
	 * 页面设置
	 */
	public BusiModelOptionPage() {
		super("Other properties");
		setTitle("HEA Busi Module");
		setDescription("生成设置");
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
		gpConn.setText("模块信息列表");
		gpConn.setLayout(new RowLayout());

		GridData gdc = new GridData();
		gdc.minimumWidth = 1000;
		gdc.minimumHeight = 250;
		gdc.verticalAlignment = GridData.FILL;
		gdc.grabExcessHorizontalSpace = true;
		gdc.grabExcessVerticalSpace = true;
		gpConn.setLayoutData(gdc);
		gpConn.setLayout(new GridLayout(1, false));

		modelBtn = new Button(gpConn, SWT.CHECK);
		modelBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		modelBtn.setSelection(true);
		modelBtn.setEnabled(false);

		daoBtn = new Button(gpConn, SWT.CHECK);
		daoBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		daoBtn.setSelection(true);

		serviceBtn = new Button(gpConn, SWT.CHECK);
		serviceBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		serviceBtn.setSelection(true);

		mainPageBtn = new Button(gpConn, SWT.CHECK);
		mainPageBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		mainPageBtn.setSelection(true);

		updatePageBtn = new Button(gpConn, SWT.CHECK);
		updatePageBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		updatePageBtn.setSelection(true);

		setPageComplete(false);
		setControl(container);
	}

	public void refresh(String pkgname, String pname, String mname,
			String mClassName) {
		modelBtn.setText(pkgname + ".model." + mClassName + ".class");
		daoBtn.setText(pkgname + ".dao." + mClassName + ".class");
		serviceBtn.setText(pkgname + ".service." + mClassName + ".class");
		mainPageBtn.setText(pkgname + ".web." + mname + "." + mClassName
				+ "MainPage.class");
		updatePageBtn.setText(pkgname + ".web." + mname + "." + mClassName
				+ "UpdatePage.class");
	}

	public Button getModelBtn() {
		return modelBtn;
	}

	public void setModelBtn(Button modelBtn) {
		this.modelBtn = modelBtn;
	}

	public Button getDaoBtn() {
		return daoBtn;
	}

	public void setDaoBtn(Button daoBtn) {
		this.daoBtn = daoBtn;
	}

	public Button getServiceBtn() {
		return serviceBtn;
	}

	public void setServiceBtn(Button serviceBtn) {
		this.serviceBtn = serviceBtn;
	}

	public Button getMainPageBtn() {
		return mainPageBtn;
	}

	public void setMainPageBtn(Button mainPageBtn) {
		this.mainPageBtn = mainPageBtn;
	}

	public Button getUpdatePageBtn() {
		return updatePageBtn;
	}

	public void setUpdatePageBtn(Button updatePageBtn) {
		this.updatePageBtn = updatePageBtn;
	}

}
