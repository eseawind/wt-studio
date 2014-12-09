package com.wt.studio.plugin.tools.views;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.part.ViewPart;

import com.wt.studio.plugin.tools.utils.MonitorUtil;
import com.wt.studio.plugin.tools.views.tools.CryptControl;
import com.wt.studio.plugin.tools.views.tools.RegexControl;
import com.wt.studio.plugin.tools.views.tools.RestControl;
import com.wt.studio.plugin.tools.views.tools.TransLateControl;
import com.wt.studio.plugin.tools.views.tools.URLControl;

public class CommonView extends ViewPart {
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.hirisun.ide.plugin.tools.views.URLView";
	private Composite mcontainer;

	/**
	 * The constructor.
	 */
	public CommonView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		TabFolder tabFolder = new TabFolder(parent, SWT.BOTTOM | SWT.V_SCROLL);
		mcontainer = new Composite(tabFolder, SWT.NONE);
		createInitTransLateControl(tabFolder);
		createInitURLControl(tabFolder);
		createInitCryptControl(tabFolder);
		createInitRegexControl(tabFolder);
		createInitRestControl(tabFolder);
		createInitWSControl(tabFolder);
		createInitCommandControl(tabFolder);
		
	}

	private void createInitURLControl(TabFolder tabFolder) {
		URLControl.init(tabFolder);
	}

	private void createInitCryptControl(TabFolder tabFolder) {
		CryptControl.init(tabFolder);
	}


	private void createInitWSControl(TabFolder tabFolder) {
		// TODO Auto-generated method stub
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("WebService");
		Composite container = new Composite(tabFolder, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 3;
		container.setLayout(gl);
		item.setControl(container);
	}
	
    /**
     * 
     * 方法说明：Rest工具
     *
     * @param tabFolder
     */
	private void createInitRestControl(TabFolder tabFolder) {
		RestControl.init(tabFolder);
	}

	/**
	 * 正则工具
	 * @param tabFolder
	 */
	private void createInitRegexControl(TabFolder tabFolder) {
		RegexControl.init(tabFolder);
	}

	/**
	 * 常用快捷命令调用
	 * @param tabFolder
	 */
	private void createInitCommandControl(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText("快捷方式");
		Composite container = new Composite(tabFolder, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		container.setLayout(gl);
		item.setControl(container);
	}

	/**
	 * 创建翻译
	 * @param tabFolder
	 */
	private void createInitTransLateControl(TabFolder tabFolder) {
		TransLateControl.init(tabFolder);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		mcontainer.setFocus();
	}
}
