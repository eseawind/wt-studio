package com.wt.studio.plugin.wizard.projects.data2model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.wt.studio.plugin.wizard.projects.uitl.HEABasePage;

public class ModelPage extends HEABasePage {

	private Composite container;

	
	public ModelPage() {
		super("Other properties");
		setTitle("HEA Model Class");
		setDescription("根据数据库表生成模型");
	}	

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.None);
		container.setLayout(new GridLayout(1, false));

		setControl(container);
		setPageComplete(false);
	}

}
