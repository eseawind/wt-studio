package com.wt.studio.plugin.wizard.projects.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.wizard.projects.Activator;
import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel;
import com.wt.studio.plugin.wizard.projects.dbhelp.HelpDBInit;
import com.wt.studio.plugin.wizard.projects.dbhelp.TableModel;
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
public class MultiModelPage extends HEABasePage {

	/**
	 * container
	 */
	private Composite container;

	/**
	 * 表信息
	 */
	private Combo comDS;
	private Text txtPak;
	private Text search;
	private String pname;

	private TableEditor editor;
	/**
	 * 列表
	 */
	private Table table;

	private String dburl = "";
	private String dbuser = "";
	private String dbpass = "";
	private String dbType = "";

	protected List<TableModel> tableModels;

	private static String[] tableHeader = {
			"           表名称                             ",
			"          Model          ",
			"                描述                                        ",
			"              问题                                    " };

	/**
	 * 页面设置
	 */
	public MultiModelPage(String pname) {
		super("Other properties");
		setTitle("HEA Model Wizard");
		setDescription("批量生成Model及Dao内容");
		this.pname = pname;
	}

	public Text getTxtPak() {
		return txtPak;
	}

	public void setTxtPak(Text txtPak) {
		this.txtPak = txtPak;
	}

	public List<TableModel> getTableModels() {
		return tableModels;
	}

	/**
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setBounds(new Rectangle(0, 0, 800, 500));
		container.setLayout(new GridLayout(1, false));
		Group gpTable = new Group(container, SWT.NONE);
		gpTable.setText("数据源信息");
		GridData gd1 = new GridData();
		gd1.horizontalSpan = 2;
		gd1.horizontalAlignment = GridData.FILL;
		gpTable.setLayoutData(gd1);
		gpTable.setLayout(new GridLayout(2, false));

		new Label(gpTable, SWT.NULL).setText("包名称:");
		txtPak = new Text(gpTable, SWT.BORDER);
		txtPak.setText(Consistant.COMP_PRIX + pname);
		txtPak.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		new Label(gpTable, SWT.NULL).setText("数据源:");
		comDS = new Combo(gpTable, SWT.NONE | SWT.READ_ONLY);
		comDS.add("--请选择数据源--");
		comDS.select(0);

		for (String dbname : HelpDBInit.getDBNames()) {
			comDS.add(dbname);
		}

		comDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridData gd2 = new GridData();
		gd2.horizontalAlignment = SWT.FILL;
		gd2.grabExcessHorizontalSpace = true;
		gd2.verticalAlignment = SWT.FILL;
		gd2.verticalSpan = 0;

		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.verticalAlignment = SWT.FILL;
		gd.heightHint = 200;
		search = new Text(container, SWT.BORDER);
		search.setLayoutData(gd2);
		search.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				table.removeAll();
				for (final TableModel lc : tableModels) {
					if (StringUtils.contains(lc.getTableName(),
							StringUtils.upperCase(search.getText()))
							|| StringUtils.contains(lc.getTableName(),
									StringUtils.lowerCase(search.getText()))) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(new String[] { lc.getTableName(),
								Activator.getTable2ModelName(lc.getTableName()), "" });
					}
				}
			}
		});

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.CHECK);

		table.setHeaderVisible(true); // 显示表头
		table.setLinesVisible(true); // 显示表格线
		table.setLayoutData(gd);

		for (int i = 0; i < tableHeader.length; i++) {
			TableColumn tableColumn = new TableColumn(table, SWT.FILL);
			tableColumn.setWidth(300);
			tableColumn.setText(tableHeader[i]);
			table.setHeaderVisible(true);
		}

		comDS.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				try {

					table.removeAll();
					search.setText("");
					if (comDS.getSelectionIndex() != 0) {
						String name = comDS.getText();
						dbType = HelpDBInit.getDBType(name);
						dburl = HelpDBInit.getDBUrl(name);
						dbuser = HelpDBInit.getDBUser(name);
						dbpass = HelpDBInit.getDBPass(name);
						tableModels = HelpDBInit.getTableList(name, dbType,
								dburl, dbuser, dbpass);

						for (final TableModel lc : tableModels) {
							TableItem item = new TableItem(table, SWT.NONE);
							item.setText(new String[] { lc.getTableName(),
									Activator.getTable2ModelName(lc.getTableName()), "" });
						}
					}
				} catch (Exception e) {
					MessageDialog.openWarning(getShell(), "提示", "检查数据库连接！");
				}
			}
		});

		for (int i = 0; i < tableHeader.length; i++) {
			table.getColumn(i).pack();
		}

		setPageComplete(false);
		setControl(container);
	}

	protected void assembleColumeModels(List<ColumeModel> columeModels2,
			List<ColumeModel> oldColumeModels2) {
		for (ColumeModel item : columeModels2) {
			for (ColumeModel item2 : oldColumeModels2) {
				if (StringUtils.equals(item.getColumeName(),
						item2.getColumeName())) {
					item.setComment(item2.getComment());
					item.setIsKey(StringUtils.equals(item2.getIsKey(), "1") ? "PK"
							: "");
					item.setIsQueryCond(item2.getIsQueryCond());
					item.setIsListShow(StringUtils.equals(
							item2.getIsListShow(), "1") ? "是" : "");
					item.setManageControlType(item2.getManageControlType());
					item.setManageDataType(item2.getManageDataType());
					item.setValideType(item2.getValideType());
					item.setRequid(StringUtils.equals(item2.getRequid(), "1") ? "非空"
							: "");
				}
			}
		}
	}

	@Override
	public boolean isPageComplete() {
		boolean flag = false;
		return true;
	}
	
	public List<TableModel> getSeletedTable() {
		List<TableModel> result = new ArrayList<TableModel>();

		for (TableItem item : table.getItems()) {
			if (item.getChecked()) {
				result.add(new TableModel(item.getText(0), item.getText(2),
						item.getText(1)));
			}
		}
		return result;
	}

	public List<ColumeModel> getSeletedColumes(String table) {
		String name = comDS.getText();
		dbType = HelpDBInit.getDBType(name);
		dburl = HelpDBInit.getDBUrl(name);
		dbuser = HelpDBInit.getDBUser(name);
		dbpass = HelpDBInit.getDBPass(name);
		try {
			return HelpDBInit.getTableColumeList(dbType, dburl, dbuser, dbpass,
					table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
