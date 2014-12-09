package com.wt.studio.plugin.wizard.projects.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.platform.util.HeaProjectModel;
import com.wt.studio.plugin.wizard.projects.Activator;
import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel;
import com.wt.studio.plugin.wizard.projects.dbhelp.HelpDBInit;
import com.wt.studio.plugin.wizard.projects.dbhelp.TableModel;
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
public class BusiDBModelPage extends HEABasePage {

	private HeaProjectModel heaProjectModel;

	/**
	 * container
	 */
	private Composite container;

	/**
	 * 表信息
	 */
	private Combo comDS;

	private Combo comTable;
	private Table table;

	/**
	 * 表描述
	 */
	private Label tableDesc;
	private List<Control> controls = new ArrayList<Control>();

	private String dburl = "";

	private String dbuser = "";
	private String dbpass = "";
	private String dbType = "";

	private int iDesc = 2;
	private int iKey = 3;
	private int iQueryCond = 6;
	private int iListShow = 7;
	private int iControle = 8;
	private int iManageDataType = 9;
	private int iValid = 10;
	private int iRequid = 11;

	private TableEditor editor1;
	private TableEditor editor2;
	private TableEditor editor3;
	private TableEditor editor4;
	private TableEditor editor5;
	private TableEditor editor6;
	private TableEditor editor7;
	private TableEditor editor8;
	/**
	 * 对应表列表
	 */
	private List<TableModel> tableModels;
	private String oldTable;

	private List<ColumeModel> oldColumeModels;
	/**
	 * 对应表列表
	 */
	private List<ColumeModel> columeModels;

	private String modelName;

	private static String[] tableHeader = { "     字段      ",
			"     字段类型       ", "      字段描述      ", "主键", " 属性名     ",
			" 属性类型    ", "  查询条件     ", " 列表显示   ", " 控件类型    ", "     数据类型 ",
			"  验证类型     ", " 非空  " };

	/**
	 * 页面设置
	 */
	public BusiDBModelPage(HeaProjectModel heaProjectModel) {
		super("Other properties");
		setTitle("HEA Busi Module");
		setDescription("根据数据表设置查询条件、列表信息等内容");
		this.heaProjectModel = heaProjectModel;
	}

	/**
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setBounds(new Rectangle(0, 0, 800, 500));
		container.setLayout(new GridLayout(1, false));
		Group gpTable = new Group(container, SWT.NONE);
		gpTable.setText("表信息");
		GridData gd1 = new GridData();
		gd1.horizontalSpan = 2;
		gd1.horizontalAlignment = GridData.FILL;
		gpTable.setLayoutData(gd1);
		gpTable.setLayout(new GridLayout(2, false));

		new Label(gpTable, SWT.NULL).setText("数据源:");
		comDS = new Combo(gpTable, SWT.NONE | SWT.READ_ONLY);
		comDS.add("--请选择数据源--");
		comDS.select(0);

		for (String dbname : HelpDBInit.getDBNames()) {
			comDS.add(dbname);
		}

		Label label2 = new Label(gpTable, SWT.NULL);
		label2.setText("选择表:");
		comTable = new Combo(gpTable, SWT.NONE | SWT.READ_ONLY);
		comTable.add("--请选择表--");
		comTable.select(0);

		Label labDesc = new Label(gpTable, SWT.NULL);
		labDesc.setText("表描述:");
		tableDesc = new Label(gpTable, SWT.NULL | SWT.READ_ONLY | SWT.BORDER);
		GridData gridData = new GridData();
		gridData.verticalSpan = 3; // 跨两行
		gridData.grabExcessHorizontalSpace = true; // 抢占垂直方向额外空间
		gridData.horizontalAlignment = GridData.FILL; // 水平方向充满
		gridData.grabExcessVerticalSpace = true; // 抢占水平方向额外空间
		gridData.verticalAlignment = GridData.FILL; // 垂直方向充满
		tableDesc.setLayoutData(gridData);
		comDS.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comTable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.verticalAlignment = SWT.FILL;
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);

		editor1 = new TableEditor(table);
		editor2 = new TableEditor(table);
		editor3 = new TableEditor(table);
		editor4 = new TableEditor(table);
		editor5 = new TableEditor(table);
		editor6 = new TableEditor(table);
		editor7 = new TableEditor(table);
		editor8 = new TableEditor(table);

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
					comTable.removeAll();
					disposeControl();
					comTable.add("--请选择表--");
					comTable.select(0);
					tableDesc.setText("");
					table.removeAll();
					if (comDS.getSelectionIndex() != 0) {
						String name = comDS.getText();
						dbType = HelpDBInit.getDBType(name);
						dburl = HelpDBInit.getDBUrl(name);
						dbuser = HelpDBInit.getDBUser(name);
						dbpass = HelpDBInit.getDBPass(name);
						tableModels = HelpDBInit.getTableList(name, dbType,
								dburl, dbuser, dbpass);
						for (TableModel tableModel : tableModels) {
							comTable.add(tableModel.getTableName());
						}
					}
				} catch (Exception e) {
					MessageDialog.openWarning(getShell(), "提示", "检查数据库连接！");
				}
			}
		});

		comTable.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				if (comTable.getSelectionIndex() <= 0) {
					return;
				}
				setPageComplete(false);
				try {
					if (((Combo) arg0.getSource()).getSelectionIndex() == 0) {
						tableDesc.setText("");
						table.removeAll();
						return;
					}
					tableDesc
							.setText(getSeletedTableModel().getComments() == null ? ""
									: getSeletedTableModel().getComments());
					// 清除控件
					for (Control control : controls) {
						control.dispose();
					}
					disposeControl();
					table.removeAll();

					columeModels = HelpDBInit.getTableColumeList(dbType, dburl,
							dbuser, dbpass, comTable.getText());
					if (StringUtils.equals(comTable.getText(), oldTable)) {
						assembleColumeModels(columeModels, oldColumeModels);
					}

					for (final ColumeModel lc : columeModels) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(new String[] { lc.getColumeName(),
								lc.getDataType(), lc.getComment(),
								lc.getIsKey(), lc.getName(), lc.getType(),
								lc.getIsQueryCond(), lc.getIsListShow(),
								lc.getManageControlType(),
								lc.getManageDataType(), lc.getValideType(),
								lc.getRequid() });
					}

					table.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							operatorDesc(editor1, e, iDesc);
							operatorKey(editor2, e);
							operatorCond(editor3, e, iQueryCond);
							operatorInputControl(editor4, e, editor7);
							operatorValide(editor5, e);
							operatorNull(editor6, e);
							operatorCond(editor8, e, iListShow);
						}

						@Override
						public void widgetDefaultSelected(SelectionEvent e) {
							super.widgetDefaultSelected(e);
						}
						
						
					});

				} catch (Exception e) {
					e.printStackTrace();
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
		for(ColumeModel item: columeModels2) {
			for(ColumeModel item2: oldColumeModels2) {
				if(StringUtils.equals(item.getColumeName(), item2.getColumeName())) {
					item.setComment(item2.getComment());
					item.setIsKey(StringUtils.equals(item2.getIsKey(),"1") ? "PK" : "");
					item.setIsQueryCond(item2.getIsQueryCond());
					item.setIsListShow(StringUtils.equals(item2.getIsListShow(),"1")?"是":"");
					item.setManageControlType(item2.getManageControlType());
					item.setManageDataType(item2.getManageDataType());
					item.setValideType(item2.getValideType());
					item.setRequid(StringUtils.equals(item2.getRequid(),"1")?"非空":"");
				}
			}
		}
	}

	private void disposeControl() {
		if (editor1.getEditor() != null) {
			editor1.getEditor().dispose();
		}
		if (editor2.getEditor() != null) {
			editor2.getEditor().dispose();
		}
		if (editor3.getEditor() != null) {
			editor3.getEditor().dispose();
		}
		if (editor4.getEditor() != null) {
			editor4.getEditor().dispose();
		}
		if (editor5.getEditor() != null) {
			editor5.getEditor().dispose();
		}
		if (editor6.getEditor() != null) {
			editor6.getEditor().dispose();
		}
		if (editor7.getEditor() != null) {
			editor7.getEditor().dispose();
		}
		if (editor8.getEditor() != null) {
			editor8.getEditor().dispose();
		}		
	}

	public String getComTable() {
		return comTable.getText();
	}

	public List<ColumeModel> getOldColumeModels() {
		return oldColumeModels;
	}

	public String getOldTable() {
		return oldTable;
	}

	/**
	 * 获取选择表列名称
	 * 
	 * @return List<ColumeModel> 返回尋則表列信息
	 */
	public List<ColumeModel> getSeletedColumeModelList() {
		TableItem item = null;
		for (int i = 0; i < table.getItemCount(); i++) {
			item = table.getItem(i);
			String key = item.getText(0);

			for (ColumeModel columeModel : columeModels) {
				if (columeModel.getColumeName().equals(key)) {
					columeModel.setComment(item.getText(iDesc));
					columeModel.setIsKey(item.getText(iKey).equals("PK") ? "1" : "");
					columeModel.setIsQueryCond(item.getText(iQueryCond));
					columeModel.setIsListShow(item.getText(iListShow).equals("是") ? "1" : "");
					columeModel.setManageControlType(StringUtils.isNotEmpty(item.getText(iControle))? item.getText(iControle) : "INPUT" );
					columeModel.setManageDataType(item.getText(iManageDataType));
					columeModel.setValideType(item.getText(iValid));
					columeModel.setRequid(item.getText(iRequid).equals("非空") ? "1": "");
				}
			}
		}
		return columeModels;
	}

	/**
	 * 获取选择模型名称
	 * 
	 * @return String
	 */
	public String getSeletedModelName() {
		return Activator.getTable2ModelName(modelName);
	}

	/**
	 * 获取选择表名称
	 * 
	 * @return TableModel 返回選擇表信息
	 */
	public TableModel getSeletedTableModel() {
		TableModel result = null;
		if (tableModels != null) {
			for (TableModel tm : tableModels) {
				if (tm.getTableName().equals(comTable.getText())) {
					result = tm;
					break;
				}
			}
		}
		modelName = result.getTableName();
		return result;
	}

	@Override
	public boolean isPageComplete() {
		boolean flag = false;
		return true;
	}

	public void operatorCond(final TableEditor editor, SelectionEvent e,
			int iCon) {
		// 处理表格编辑 内容
		final int sort = iCon;
		Control oldEditor = editor.getEditor();
		if (oldEditor != null) {
			oldEditor.dispose();
		}
		TableItem item = (TableItem) e.item;
		if (item == null)
			return;

		// 校验列 为第二列绑定下来框CCombo
		CCombo combo = new CCombo(table, SWT.NONE | SWT.READ_ONLY);
		combo.add("");
		combo.add("是");
		combo.setText(item.getText(sort));
		editor.grabHorizontal = true;
		combo.computeSize(SWT.DEFAULT, table.getItemHeight());
		editor.grabHorizontal = true;
		editor.minimumHeight = combo.getSize().y;
		editor.minimumWidth = combo.getSize().x;

		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				editor.getItem().setText(sort,
						((CCombo) arg0.getSource()).getText());
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				editor.getItem().setText(sort,
						((CCombo) arg0.getSource()).getText());
			}
		});
		editor.setEditor(combo, item, sort);
	}

	public void operatorDesc(final TableEditor editor, SelectionEvent e,
			final int sort) {
		Control oldEditor = editor.getEditor();
		if (oldEditor != null) {
			oldEditor.dispose();
		}
		TableItem item = (TableItem) e.item;
		if (item == null)
			return;
		Text textEditor = new Text(table, SWT.NONE);
		textEditor.setText(item.getText(sort));
		editor.grabHorizontal = true;
		textEditor.computeSize(SWT.DEFAULT, table.getItemHeight());
		editor.grabHorizontal = true;
		editor.minimumHeight = textEditor.getSize().y;
		editor.minimumWidth = textEditor.getSize().x;

		textEditor.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				editor.getItem().setText(sort, ((Text) e.widget).getText());
			}
		});
		textEditor.selectAll();
		textEditor.setFocus();
		editor.setEditor(textEditor, item, sort);

	}

	public void operatorInputControl(final TableEditor editor,
			final SelectionEvent e, final TableEditor editor2) {
		// 处理表格编辑 内容
		final int sort = iControle;
		Control oldEditor = editor.getEditor();
		Control oldEditor2 = editor2.getEditor();
		if (oldEditor != null) {
			oldEditor.dispose();
		}
		if (oldEditor2 != null) {
			oldEditor2.dispose();
		}
		TableItem item = (TableItem) e.item;
		if (item == null)
			return;

		// 校验列 为第二列绑定下来框CCombo
		CCombo combo = new CCombo(table, SWT.NONE | SWT.READ_ONLY);
		combo.add("INPUT");
		combo.add("SELECT");
		combo.add("DATEPICKER");
		combo.add("PASSWORD");
		combo.add("TEXTAREA");
		combo.add("COM_ORG");
		combo.add("COM_PEOPLE");
		combo.setText(StringUtils.isNotEmpty(item.getText(sort)) ? item
				.getText(sort) : "INPUT");
		editor.grabHorizontal = true;
		combo.computeSize(SWT.DEFAULT, table.getItemHeight());
		editor.grabHorizontal = true;
		editor.minimumHeight = combo.getSize().y;
		editor.minimumWidth = combo.getSize().x;
		// 处理表格编辑 内容
		if (StringUtils.equals("SELECT", item.getText(sort))) {
			operatorDesc(editor2, e, iManageDataType);
		}

		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				int i = iManageDataType;
				String name = ((CCombo) arg0.getSource()).getText();
				editor.getItem().setText(sort, name);
				if (StringUtils.equals("SELECT", name)) {
					operatorDesc(editor2, e, i);
				} else {
					if (editor2.getEditor() != null) {
						editor2.getEditor().dispose();
					}
					editor.getItem().setText(i, "");
				}
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int i = iManageDataType;
				String name = ((CCombo) arg0.getSource()).getText();
				editor.getItem().setText(sort, name);
				if (StringUtils.equals("SELECT", name)) {
					operatorDesc(editor2, e, i);
				} else {
					if (editor2.getEditor() != null) {
						editor2.getEditor().dispose();
					}
					editor.getItem().setText(i, "");
				}
			}
		});
		editor.setEditor(combo, item, sort);
	}

	public void operatorKey(final TableEditor editor, SelectionEvent e) {
		final int sort = iKey;
		Control oldEditor = editor.getEditor();
		if (oldEditor != null) {
			oldEditor.dispose();
		}
		TableItem item = (TableItem) e.item;
		if (item == null)
			return;

		CCombo combo = new CCombo(table, SWT.NONE | SWT.READ_ONLY);
		combo.add("PK");
		combo.add("");
		combo.setText(item.getText(sort));
		editor.grabHorizontal = true;
		combo.computeSize(SWT.DEFAULT, table.getItemHeight());
		editor.grabHorizontal = true;
		editor.minimumHeight = combo.getSize().y;
		editor.minimumWidth = combo.getSize().x;

		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				editor.getItem().setText(sort,
						((CCombo) arg0.getSource()).getText());
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				editor.getItem().setText(sort,
						((CCombo) arg0.getSource()).getText());
			}
		});

		editor.setEditor(combo, item, sort);
	}

	public void operatorNull(final TableEditor editor, SelectionEvent e) {
		// 处理表格编辑 内容
		final int sort = iRequid;
		Control oldEditor = editor.getEditor();
		if (oldEditor != null) {
			oldEditor.dispose();
		}
		TableItem item = (TableItem) e.item;
		if (item == null)
			return;

		// 校验列 为第二列绑定下来框CCombo
		CCombo combo = new CCombo(table, SWT.NONE | SWT.READ_ONLY);
		combo.add("");
		combo.add("非空");
		combo.setText(item.getText(sort));
		editor.grabHorizontal = true;
		combo.computeSize(SWT.DEFAULT, table.getItemHeight());
		editor.grabHorizontal = true;
		editor.minimumHeight = combo.getSize().y;
		editor.minimumWidth = combo.getSize().x;

		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				editor.getItem().setText(sort,
						((CCombo) arg0.getSource()).getText());
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				editor.getItem().setText(sort,
						((CCombo) arg0.getSource()).getText());
			}
		});
		editor.setEditor(combo, item, sort);
	}

	public void operatorValide(final TableEditor editor, SelectionEvent e) {
		// 处理表格编辑 内容
		final int sort = iValid;
		Control oldEditor = editor.getEditor();
		if (oldEditor != null) {
			oldEditor.dispose();
		}
		TableItem item = (TableItem) e.item;
		if (item == null)
			return;

		// 校验列 为第二列绑定下来框CCombo
		CCombo combo = new CCombo(table, SWT.NONE | SWT.READ_ONLY);

		combo.add("文本");
		combo.add("数字");
		combo.add("邮件");
		combo.setText(item.getText(sort));
		editor.grabHorizontal = true;
		combo.computeSize(SWT.DEFAULT, table.getItemHeight());
		editor.grabHorizontal = true;
		editor.minimumHeight = combo.getSize().y;
		editor.minimumWidth = combo.getSize().x;

		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				editor.getItem().setText(sort,
						((CCombo) arg0.getSource()).getText());
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				editor.getItem().setText(sort,
						((CCombo) arg0.getSource()).getText());
			}
		});
		editor.setEditor(combo, item, sort);
	}

	public void setOldColumeModels(List<ColumeModel> oldColumeModels) {
		this.oldColumeModels = oldColumeModels;
	}

	public void setOldTable(String oldTable) {
		this.oldTable = oldTable;
	}
}
