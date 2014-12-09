package com.wt.studio.plugin.wizard.projects.utilpage;

import java.util.StringTokenizer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.platform.Activator;

/**
 * uum属性设置向导页面
 * 
 * @author yefeng
 * 
 */
public class UumPreferencesPage extends WizardPage {

	/**
	 * uum
	 */
	private Combo uumCombo;

	private Combo type;

	private Button btnC;
	// 域名（domain）、加密串（accessKey）和模式（mode）
	private Text domain;
	private Text accesskey;
	private Combo mode;
	private Text expiredTime;

	private String sdomain;
	private String saccesskey;
	private String smode;
	private String sexpiredTime;

	/**
	 * container
	 */
	private Composite container;
	/**
	 * Hea首选项
	 */
	private static IPreferenceStore preferenceStore = Activator.getDefault()
			.getPreferenceStore();

	/**
	 * 页面设置
	 */
//	public UumPreferencesPage() {
//		super("Other properties");
//		setTitle("UUM 环境设置");
//		setDescription("请填写下面的其它属性");
//	}

	public UumPreferencesPage(String domain, String accesskey, String mode,
			String expiredTime) {
		super("Other properties");
		setTitle("UUM 环境设置");
		setDescription("请填写下面的其它属性");
		this.sdomain = domain;
		this.saccesskey = accesskey;
		this.smode = mode;
		this.sexpiredTime = expiredTime;
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

		Group gpUUM = new Group(container, SWT.NONE);
		GridData grd = new GridData();
		grd.horizontalAlignment = GridData.FILL;
		grd.grabExcessHorizontalSpace = true;
		gpUUM.setLayoutData(grd);
		gpUUM.setLayout(new GridLayout(2, false));
		gpUUM.setText("UUM设置");

		Label label2 = new Label(gpUUM, SWT.NULL);
		label2.setText("UUM地址:");
		String uums = preferenceStore.getString("UumStringPreference");
		StringTokenizer stok = new StringTokenizer(uums, ";");
		int arraySize = stok.countTokens();
		String[] decodedArray = new String[arraySize];
		for (int i = 0; i < arraySize; i++) {
			decodedArray[i] = stok.nextToken(";");
		}
		uumCombo = new Combo(gpUUM, SWT.NONE | SWT.READ_ONLY);
		uumCombo.setItems(decodedArray);
		uumCombo.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				if (!"".equals(uumCombo.getText())) {
					setPageComplete(true);
				} else {
					setPageComplete(false);
				}
			}
		});

		Group gpHigh = new Group(container, SWT.NONE);
		gpHigh.setText("LCTA单点设置");
		GridData gdConn = new GridData();
		gpHigh.setLayout(new GridLayout(2, false));
		gdConn.horizontalAlignment = GridData.FILL;
		gdConn.grabExcessHorizontalSpace = true;
		gpHigh.setLayoutData(gdConn);
		gpHigh.setLayout(new GridLayout(2, false));

		new Label(gpHigh, SWT.NULL).setText("自定义配置:");

		btnC = new Button(gpHigh, SWT.CHECK); // SWT.PUSH 表示按钮
		btnC.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				boolean b = true;
				if (btnC.getSelection()) {
					b = true;
				} else {
					b = false;
				}
				domain.setEnabled(b);
				accesskey.setEnabled(b);
				mode.setEnabled(b);
				expiredTime.setEnabled(b);
			}
		});

		new Label(gpHigh, SWT.NULL).setText("域(Domain):");
		domain = new Text(gpHigh, SWT.BORDER);
		domain.setText(sdomain);
		domain.setEnabled(false);
		new Label(gpHigh, SWT.NULL).setText("加密串(accesskey):");
		accesskey = new Text(gpHigh, SWT.BORDER);
		accesskey.setText(saccesskey);
		accesskey.setEnabled(false);
		new Label(gpHigh, SWT.NULL).setText("模式(Mode):");
		mode = new Combo(gpHigh, SWT.BORDER | SWT.READ_ONLY);
		mode.add("SLAVE", 0);
		mode.add("MASTER", 1);
		mode.select(0);
		mode.setEnabled(false);

		new Label(gpHigh, SWT.NULL).setText("过期值(expiredTime):");
		expiredTime = new Text(gpHigh, SWT.BORDER);
		expiredTime.setText(sexpiredTime);
		expiredTime.setEnabled(false);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);

		uumCombo.setLayoutData(gd);
		domain.setLayoutData(gd);
		accesskey.setLayoutData(gd);
		mode.setLayoutData(gd);
		expiredTime.setLayoutData(gd);

		setControl(container);
		setPageComplete(false);

	}
	
	public String getSdomain() {
		return this.domain.getText();
	}

	public String getSaccesskey() {
		return this.accesskey.getText();
	}

	public String getSmode() {
		return this.mode.getText();
	}

	public String getSexpiredTime() {
		return this.expiredTime.getText();
	}

	/**
	 * 获取方法
	 * 
	 * @return string
	 */
	public String getUumaddress() {
		return uumCombo.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#getControl()
	 */
	public Control getControl() {
		return container;
	}
}
