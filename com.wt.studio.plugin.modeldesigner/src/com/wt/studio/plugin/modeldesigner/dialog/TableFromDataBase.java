package com.wt.studio.plugin.modeldesigner.dialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.internal.Workbench;

import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.io.Reader.DiagramXmlReader;
import com.wt.studio.plugin.pagedesigner.gef.editpart.MOFunctionTableModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.utils.ConvertUtils;
import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel;
import com.wt.studio.plugin.wizard.projects.dbhelp.HelpDBInit;
import com.wt.studio.plugin.wizard.projects.dbhelp.TableModel;

public class TableFromDataBase extends Dialog
{
	
	private List<TableModel> tables=new ArrayList<TableModel>();
	private List<HdbTableModel> hdbTables=new ArrayList<HdbTableModel>();
	private Button b;
	Composite fileArea;
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

	public List<HdbTableModel> getHdbTables()
	{
		return hdbTables;
	}
	public void setHdbTables(List<HdbTableModel> hdbTables)
	{
		this.hdbTables = hdbTables;
	}	
	public TableFromDataBase(Shell parentShell) throws FileNotFoundException, MalformedURLException, DocumentException
	{
		super(parentShell);
		setShellStyle(getShellStyle()  |  SWT.RESIZE  |  SWT.MAX);
	}
	protected Point getInitialSize() { 
		return new Point(800,600); 
	} 
		
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("从数据库中导入MO模型");
	}
	protected void createButtonsForButtonBar(Composite parent) {  
	    super.createButtonsForButtonBar(parent);  
	    b=this.getButton(OK);
	    b.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent eve)
			{
			 hdbTables.clear();
			 for(TableModel model:tables)
			 {
				 List<ColumeModel>columns=getSeletedColumes(model.getTableName());
				 HdbTableModel hdbTable=new HdbTableModel();
				 hdbTable.setCode(model.getTableName());
				 hdbTable.setTitle(getTable2ModelName(model.getTableName()));
				 for (ColumeModel lc : columns) {
						HdbColumnModel column=new HdbColumnModel();
						column.setId(uuid());
						column.setCode(lc.getColumeName());
						column.setName(lc.getName());
						column.setDataType(lc.getDataType());
						column.setLength(20);
						boolean isKey=lc.getIsKey().equals("")?false:true;
						column.setPK(isKey);
						hdbTable.addColumn(column);
					}
				 hdbTables.add(hdbTable);
			 }
	    }
	});
	}
	protected Control createDialogArea(Composite parent)
	{
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.verticalAlignment = SWT.FILL;
		container = new Composite(parent, SWT.NONE);
		container.setBounds(new Rectangle(0, 0, 800, 500));
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(gd);
		Group gpTable = new Group(container, SWT.NONE);
		gpTable.setText("数据源信息");
		GridData gd1 = new GridData();
		gd1.horizontalSpan = 2;
		gd1.horizontalAlignment = GridData.FILL;
		gpTable.setLayoutData(gd1);
		gpTable.setLayout(new GridLayout(2, false));

//		new Label(gpTable, SWT.NULL).setText("包名称:");
//		txtPak = new Text(gpTable, SWT.BORDER);
//		txtPak.setText(COMP_PRIX + pname);
//		txtPak.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
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

	    gd = new GridData();
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
				hdbTables.clear();
				for (final TableModel lc : tableModels) {
					if (StringUtils.contains(lc.getTableName(),
							StringUtils.upperCase(search.getText()))
							|| StringUtils.contains(lc.getTableName(),
									StringUtils.lowerCase(search.getText()))) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(new String[] { lc.getTableName(),
								getTable2ModelName(lc.getTableName()), "" });
					}
				}
			}
		});

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION
				| SWT.CHECK);

		table.setHeaderVisible(true); // 显示表头
		table.setLinesVisible(true); // 显示表格线
		table.setLayoutData(gd);
        table.addListener(SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event eve)
			{
				// TODO Auto-generated method stub
				TableItem item=(TableItem)eve.item;
				if(item.getChecked())
				{
					tables.add((TableModel) item.getData());
				}
				else
				{
					if(tables.contains(item.getData()))
					{
						tables.remove(item.getData());
					}
				}
			}
        	
        });
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
							item.setData(lc);
							item.setText(new String[] { lc.getTableName(),
									getTable2ModelName(lc.getTableName()), "" });
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
        return parent;
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
/*	public void CreateTableButton(Table table,final TableItem item,final int i)
	{
		final TableEditor editor=new TableEditor(table);
		checkEditors.add(editor);
		final Button button=new Button(table,SWT.NULL);
		button.setText("导入");
		editor.grabHorizontal=true;
		editor.setEditor(button,item,i);
		button.addSelectionListener(new SelectionListener(){

				@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
					// TODO Auto-generated method stub
					
			}

				@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				ConvertUtils conv=new ConvertUtils();
				// TODO Auto-generated method stub
				TableModel table=(TableModel) item.getData();
				function.getColumns().clear();
				List<ColumnModel> dbColumns=table.getColumns();
				for(ColumnModel dbColumn:dbColumns)
				{
				FunctionColumnModel fcolumn=new FunctionColumnModel();
				fcolumn.setDbColumnName(dbColumn.getCode());
				fcolumn.setTitle(conv.convertTitle(dbColumn.getCode()));
				try {
					fcolumn.setId(conv.convertDataType(dbColumn.getDataType(),"orcal"));
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					fcolumn.setId("String");
				}
				try {
					fcolumn.setDataType(conv.convertDataType(dbColumn.getDataType(),"orcal"));
				} catch (DocumentException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
					fcolumn.setDataType("String");
				}
				fcolumn.setUuid(dbColumn.getUuid());
				boolean isFK=dbColumn.isFK()?true:false;
				fcolumn.setFK(isFK);
				boolean isPK=dbColumn.isPK()?true:false;
				fcolumn.setPK(isPK);
				function.addColumn(fcolumn);
				}
				function.setTableName(table.getCode());
				function.setTitle(conv.convertTitle(table.getCode()));
			}
				
			});
		}*/
/*	private void createTableData(IFile file)
	{
		// TODO Auto-generated method stub
		BOModelDiagram diagram = null;
		try {
			diagram = paresDiagram(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tableData.removeAll();
		List <TableModel>tables=diagram.getTableModels();
		for(TableModel table:tables)
		{
			TableItem item=new TableItem(tableData,SWT.NONE);
			item.setText(0, table.getTitle());
			item.setText(1,table.getCode());
			item.setText(2,table.getComment());
			item.setData(table);
			CreateTableButton(tableData,item,5);
		}
		fileArea.layout();
	}
*/
	public List<ColumeModel> getSeletedColumes(String table) {
		try {
			return HelpDBInit.getTableColumeList(dbType, dburl, dbuser, dbpass,
					table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	 public static String getStreamString(InputStream tInputStream) {
			if (tInputStream != null) {
				try {
					BufferedReader tBufferedReader = new BufferedReader(
							new InputStreamReader(tInputStream));
					StringBuffer tStringBuffer = new StringBuffer();
					String sTempOneLine = new String("");
					while ((sTempOneLine = tBufferedReader.readLine()) != null) {
						tStringBuffer.append(sTempOneLine);
					}
					return tStringBuffer.toString();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return null;
		}
		public static IProject getProject(){
	        IProject project = null;
	        
	        //1.根据当前编辑器获取工程
	        IEditorPart part = Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	        if(part != null){
	            Object object = part.getEditorInput().getAdapter(IFile.class);
	            if(object != null){
	                project = ((IFile)object).getProject();
	            }
	        }
	        
	        if(project == null){
	            ISelectionService selectionService =   
	                    Workbench.getInstance().getActiveWorkbenchWindow().getSelectionService();  
	            ISelection selection = selectionService.getSelection();  
	            if(selection instanceof IStructuredSelection) {  
	                Object element = ((IStructuredSelection)selection).getFirstElement();  
	                if (element instanceof IResource) {  
	                    project= ((IResource)element).getProject();  
	                } 
	            }   
	        }
	        
	        return project;
	    }
		private static String uuid()
		{
			return UUID.randomUUID().toString().replace("-", "");
		}
		public static String getTable2ModelName(String table) {
			String result = "";
			for (String s : StringUtils.replaceEach(table,
					new String[] { "-", " " }, new String[] { "", "" }).split("_")) {
				result += StringUtils.capitalize(StringUtils.lowerCase(s));

			}
			return result;
		}	
}

