package com.wt.studio.plugin.platform.exports;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.wt.studio.plugin.platform.Activator;
import com.wt.studio.plugin.platform.util.FileUtil;
import com.wt.studio.plugin.platform.util.HeaProjectModel;
import com.wt.studio.plugin.platform.util.RefModel;

/**
 * 
 * <pre>
 * 业务名:导出包
 * 功能说明: 导出包
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class WarExportPage extends WizardPage
{
	/**
	 * container
	 */
	private Composite container;

	private HeaProjectModel heaProjectModel;
	/**
	 * uum
	 */
	private Combo uumCombo;

	public String getUumCombo()
	{
		return uumCombo.getText();
	}

	public void setUumCombo(Combo uumCombo)
	{
		this.uumCombo = uumCombo;
	}

	/**
	 * jndi
	 */
	private Combo jndiCombo;

	public Combo getJndiCombo()
	{
		return jndiCombo;
	}

	public void setJndiCombo(Combo jndiCombo)
	{
		this.jndiCombo = jndiCombo;
	}

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
	protected WarExportPage(HeaProjectModel heaProjectModel)
	{
		super("");
		this.heaProjectModel = heaProjectModel;
		setTitle("HEA Export");
		setDescription("导出工程");
	}

	@Override
	public void createControl(Composite parent)
	{
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		new Label(container, SWT.NULL).setText("UUM地址:");
		String uums = preferenceStore.getString("UumStringPreference");
		StringTokenizer stok = new StringTokenizer(uums, ";");
		int arraySize = stok.countTokens();
		String[] decodedArray = new String[arraySize];
		for (int i = 0; i < arraySize; i++) {
			decodedArray[i] = stok.nextToken(";");
		}
		uumCombo = new Combo(container, SWT.NONE);
		uumCombo.setItems(decodedArray);
		uumCombo.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0)
			{
				if (!"".equals(uumCombo.getText())) {
					setPageComplete(true);
				} else {
					setPageComplete(false);
				}
			}
		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		uumCombo.setLayoutData(gd);

		new Label(container, SWT.NULL).setText("JNDI源:");
		jndiCombo = new Combo(container, SWT.NONE | SWT.READ_ONLY);
		try {
			File file = new File(heaProjectModel.getDataSource());
			List<RefModel> jndiList;
			if (file.exists()) {
				jndiList = FileUtil.readXml(file);
			} else {
				String bfile = heaProjectModel.getSrc() + "/main/resources/com/hirisun/"
						+ heaProjectModel.getName() + "/config/applicationContext-dataSource.xml";
				jndiList = FileUtil.readXml(new File(bfile));
			}
			for (RefModel refModel : jndiList) {
				if (refModel.getJndiId() != null) {
					jndiCombo.add(refModel.getJndiId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jndiCombo.select(0);
		jndiCombo.setLayoutData(gd);

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
