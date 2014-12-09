package com.wt.studio.plugin.wizard.projects.services;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.wizard.projects.uitl.HEABasePage;

public class ModuleServicePage extends HEABasePage {
	private Composite container;

	// 数据库类型
	private Text mname;
	// 数据库类型
	private Text mdesc;

	public Text getMname() {
		return mname;
	}

	public void setMname(Text mname) {
		this.mname = mname;
	}

	public Text getMdesc() {
		return mdesc;
	}

	public void setMdesc(Text mdesc) {
		this.mdesc = mdesc;
	}

	public ModuleServicePage() {
		super("Other properties");
		setTitle("HEA Service Moudle ");
		setDescription("设置Moudle信息");
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.FILL);
		container.setLayout(new GridLayout(1, false));

		Group gpConn = new Group(container, SWT.NONE);
		gpConn.setText("模块描述信息");
		gpConn.setLayout(new RowLayout());
		GridData gdConn = new GridData();
		gdConn.horizontalAlignment = GridData.FILL;
		gdConn.grabExcessHorizontalSpace = true;
		gpConn.setLayoutData(gdConn);
		gpConn.setLayout(new GridLayout(2, false));
		Label labName = new Label(gpConn, SWT.LEFT);
		labName.setText("模块名称:");
		mname = new Text(gpConn, SWT.BORDER);
		mname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label labDesc = new Label(gpConn, SWT.LEFT);
		labDesc.setText("模块描述:");
		mdesc = new Text(gpConn, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
		GridData gridData = new GridData();
		gridData.verticalSpan = 10; // 跨两行
		gridData.grabExcessHorizontalSpace = true; // 抢占垂直方向额外空间
		gridData.horizontalAlignment = GridData.FILL; // 水平方向充满
		gridData.grabExcessVerticalSpace = true; // 抢占水平方向额外空间
		gridData.verticalAlignment = GridData.FILL; // 垂直方向充满
		mdesc.setLayoutData(gridData);
		
		setControl(container);
		new Label(gpConn, SWT.LEFT);

		setPageComplete(true);
	}
}
