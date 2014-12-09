package com.wt.studio.plugin.modeldesigner.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;



public class TableDialog  extends Dialog
{
	private HdbTableModel tableModel;
	final ArrayList<TableEditor> editors=new ArrayList();
	final ArrayList<TableEditor> checkEditors=new ArrayList();
	private Button b;
	private Text codeText;
	private List <String> uuid=new ArrayList();
	private Table table;
	

	@SuppressWarnings("deprecation")
	public TableDialog(Shell parentShell,HdbTableModel table)
	{
		super(parentShell);
		this.tableModel=table;
		setShellStyle(getShellStyle()  |  SWT.RESIZE  |  SWT.MAX);
		// TODO Auto-generated constructor stub
	}
	@Override 
	protected Point getInitialSize() { 
	return new Point(600,600); 
	} 
	
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("配置数据表");
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
				
	    }
	});
	}
	  
	protected Control createDialogArea(Composite parent)
	{
		Composite comp=(Composite)super.createDialogArea(parent); 
		comp.setBounds(0, 0, 600, 600);
		comp.setLayout(new FillLayout());
		final TabFolder tab=new TabFolder(comp,SWT.TOP);
		CreateGeneralTab(tab);
		CreateColumnTab(tab);
		CreateIndexTab(tab);
		CreateKeyTab(tab);
		CreateCodeTab(tab);
		tab.setLayout(new FillLayout());
		tab.pack();
		return parent;
	 
	} 
	public void CreateGeneralTab(TabFolder tab)
	{
		TabItem item=new TabItem(tab,SWT.None);
		item.setText("General");
		Composite  composite =new Composite(tab,SWT.NONE);
		item.setControl(composite);
		//整体布局
		GridLayout grid=new GridLayout();
		grid.numColumns=2;
		grid.verticalSpacing=10;
		grid.horizontalSpacing=10;
		//水平填充
		GridData gridData=new GridData();
		gridData.horizontalAlignment=SWT.FILL;
		gridData.grabExcessHorizontalSpace=true;
		//TextArea
		GridData textArea=new GridData();
		textArea.horizontalAlignment=SWT.FILL;
		textArea.verticalAlignment=SWT.FILL;
		textArea.grabExcessHorizontalSpace=true;
		textArea.grabExcessVerticalSpace=true;
		textArea.verticalSpan=5;
		composite.setLayout(grid);
		Label l1=new Label(composite,SWT.NONE);
		l1.setText("Name:");
		final Text t1=new Text(composite,SWT.BORDER);
		t1.setText(tableModel.getTitle());
		t1.setLayoutData(gridData);
		t1.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent eve)
			{
				tableModel.setTitle(t1.getText());
				
			}
			
		});
		Label l2=new Label(composite,SWT.NONE);
		l2.setText("Code:");
		final Text t2=new Text(composite,SWT.BORDER);
		t2.setText(tableModel.getCode()==null?"":tableModel.getCode());
		t2.setLayoutData(gridData);
		t2.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent eve)
			{
				tableModel.setCode(t2.getText());
			}
			
		});
		Label l3=new Label(composite,SWT.NONE);
		l3.setText("Comment:");
		final Text t3=new Text(composite,SWT.WRAP|SWT.BORDER);
		t3.setText(tableModel.getComment()==null?"":tableModel.getComment());
		t3.setLayoutData(textArea);
		t3.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent eve)
			{
				tableModel.setComment(t3.getText());	
			}
			
		});
		for(int i=0;i<4;i++)
		{
			Label label=new Label(composite,SWT.NONE);
		}
		Label l4=new Label(composite,SWT.NONE);
		l4.setText("Stereotype:");
		Combo c4=new Combo(composite,SWT.DROP_DOWN);
		c4.setLayoutData(gridData);
		Label l5=new Label(composite,SWT.NONE);
		l5.setText("Owner:");
		Combo c5=new Combo(composite,SWT.DROP_DOWN);
		c5.setLayoutData(gridData);
		Label l6=new Label(composite,SWT.NONE);
		l6.setText("Number:");
		Composite com2=new Composite(composite,SWT.NONE);
		com2.setLayout(new RowLayout());
		//com2.setLayoutData(gridData);
		RowData r1=new RowData();
		r1.width=90;
		Text t61=new Text(com2,SWT.BORDER);
		t61.setLayoutData(r1);
		Label l61=new Label(com2,SWT.NONE);
		l61.setText("Row growth rate(per year):");
		Text t62=new Text(com2,SWT.BORDER);
		t62.setText("10%");
		RowData r2=new RowData();
		r2.width=75;
		Label l7=new Label(composite,SWT.NONE);
		l7.setText("Dimensional type:");
		Composite com3=new Composite(composite,SWT.NONE);
		com3.setLayout(new RowLayout());
		Combo c71=new Combo(com3,SWT.NONE);
		c71.setLayoutData(r2);
		Button b72=new Button(com3,SWT.CHECK|SWT.LEFT);
		b72.setText("Generate");
		Label l8=new Label(composite,SWT.NONE);
		l8.setText("Type:");
		Composite com4=new Composite(composite,SWT.NONE);
		com4.setLayout(new RowLayout());
		Combo c81=new Combo(com4,SWT.NONE);
		c81.setLayoutData(r2);
		Label l9=new Label(composite,SWT.NONE);
		l9.setText("Keywords:");
		Text t9=new Text(composite,SWT.BORDER);
		t9.setLayoutData(gridData);
		
	}
	public void CreateColumnTab(TabFolder tab)
	{
		TabItem item=new TabItem(tab,SWT.None);
		item.setText("Column");
		final Composite  composite =new Composite(tab,SWT.NONE);
		item.setControl(composite);
		composite.setLayout(new GridLayout());
		Composite top=new Composite(composite,SWT.NONE);
		top.setLayout(new RowLayout());
		final Button b1=new Button(top,SWT.NONE);
		b1.setText("添加");
		final Button b2=new Button(top,SWT.NONE);
		b2.setText("删除");
		final Button b3=new Button(top,SWT.NONE);
		b3.setText("应用");
		Composite center=new Composite(composite,SWT.NONE);
		center.setLayout(new FillLayout());
		GridData cData=new GridData();
		cData.horizontalAlignment=SWT.FILL;
		cData.verticalAlignment=SWT.FILL;
		cData.grabExcessHorizontalSpace=true;
		cData.grabExcessVerticalSpace=true;;
		center.setLayoutData(cData);
		table=new Table(center,SWT.CHECK|SWT.MULTI|SWT.FULL_SELECTION);
		String[] tableHeader={"Select","Name             ","Code           ",
				"Domain","Data Type","Lenth","Precision","PK     ","FK    "};
		for(int i=0;i<tableHeader.length;i++)
		{
			TableColumn tableColumn=new TableColumn(table,SWT.NONE);
			tableColumn.setText(tableHeader[i]);
			tableColumn.setMoveable(true);
		}
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		for(int i=0;i<table.getColumnCount();i++)
		{
			table.getColumn(i).pack();
		}
		List<HdbColumnModel> columns=tableModel.getColumns();
		for(HdbColumnModel column:columns)
		{
			String PK;
			String FK;
			TableItem ditem=new TableItem(table,SWT.NONE);
			ditem.setData(column.getId());
			ditem.setText(1, column.getName());
			ditem.setText(2, column.getCode());
			ditem.setText(4,column.getDataType());
			ditem.setText(5,String.valueOf(column.getLength()));
			PK=column.isPK()?"true":"false";
			ditem.setText(7, PK);
			FK=column.isFK()?"true":"false";
			ditem.setText(8,FK);
			CreateTableCheckButton(table,ditem,7);
			CreateTableCheckButton(table,ditem,8);
			
		}
		table.addSelectionListener(new SelectionListener(){

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0)
				{    
					int total=table.getItemCount();
					for(int i=0;i<total;i++)
					{
						
						if(table.isSelected(i))
						{
							TableItem item=table.getItem(i);
						     for(TableEditor editor:editors)
						     {
							     Control cont = editor.getEditor();
								 cont.dispose();
							 }
							 CreateTableText(table,item,1);
							 CreateTableText(table,item,2);
							 CreateTableComb(table,item,3);
							 CreateTableText(table,item,4);
							 CreateTableText(table,item,5);
							 CreateTableText(table,item,6);
						}
				}
					   composite.layout();	
				}
				
			
			
		});
		
			SelectionListener listener=new SelectionListener(){

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0)
				{
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent e)
				{
					 Widget w=e.widget;
					 if(w==b1)
					 {
						  TableItem tabItem=new TableItem(table,SWT.NONE);
						  tabItem.setData(uuid());
						  tabItem.setText(7,"false");
						  tabItem.setText(8,"false");
						  CreateTableCheckButton(table,tabItem,7);
						  CreateTableCheckButton(table,tabItem,8);
					 }
					 else if(w==b2)
				     {
					     for(TableEditor editor:editors)
					     {
						     Control cont = editor.getEditor();
							 cont.dispose();
						 }
					     for(TableEditor editor:checkEditors)
					     {
					    	 Control cont=editor.getEditor();
					    	 cont.dispose();
					     }
						 TableItem[] items=table.getItems();
					     for(int i=0;i<items.length;i++)
						 {
							 if(!items[i].getChecked())
								 continue;
							 int index=table.indexOf(items[i]);
							 if(index<0)
								 continue;
						     table.remove(index);
						 }
					     TableItem[] newItems=table.getItems();
					     for(TableItem item:newItems)
					     {
					    	 CreateTableCheckButton(table,item,7);
							 CreateTableCheckButton(table,item,8);
					     }
					     
					 }
					 else if(w==b3)
					 {
						 tableModel.getColumns().clear();
						 TableItem[] items=table.getItems();
					     for(int i=0;i<items.length;i++)
						 {
						     if(!items[i].getText(1).equals("")&&!items[i].getText(2).equals(""))
						     {
							     HdbColumnModel column=new HdbColumnModel();
							     column.setId(items[i].getData().toString());
								 column.setName(items[i].getText(1));
								 column.setCode(items[i].getText(2));
								 column.setDataType(items[i].getText(4));
								 column.setLength(Integer.valueOf(items[i].getText(5)));
								 boolean isPK="true".equals(items[i].getText(7))?true:false;
								 column.setPK(isPK);
								 boolean isFK="true".equals(items[i].getText(8))?true:false;
								 column.setFK(isFK);
								 tableModel.addColumn(column);
								 tableModel.refresh();
							 }
						 }
						 String code=createCode();
						 tableModel.setCodeString(code);
						 codeText.setText(code);
					 }
				}
				
			};
			b1.addSelectionListener(listener);
			b2.addSelectionListener(listener);
			b3.addSelectionListener(listener);
	}
	protected String createCode()
	{
		StringBuffer str=new StringBuffer();
		str.append("Create table "+tableModel.getCode()+"\n");
		str.append("(\n");
		List<HdbColumnModel> PKColumns=new ArrayList<HdbColumnModel>();
		List<HdbColumnModel> FKColumns=new ArrayList<HdbColumnModel>();
		int num=0;
		for(HdbColumnModel column:tableModel.getColumns())
		{
			str.append(column.getCode()+" "+column.getDataType()+"("
					+column.getLength()+"),\n");
			if(column.isPK())
				PKColumns.add(column);
			else if(column.isFK())
				FKColumns.add(column);
				
		}
		if(PKColumns.size()>0)
		{
			str.append("constraint pk_"+tableModel.getCode()+" primary key clustered (");
		for(HdbColumnModel column:PKColumns)
		{  
			num++;
			str.append(column.getCode());
			if(num<PKColumns.size())
				str.append(",");
		}
		    str.append(")\n");
		}
		str.append(");");
		
		return str.toString();
	}
	public void CreateIndexTab(TabFolder tab)
	{
		TabItem item=new TabItem(tab,SWT.None);
		item.setText("Index");
		Composite  composite =new Composite(tab,SWT.NONE);
		item.setControl(composite);
		
	}
	public void CreateKeyTab(TabFolder tab)
	{
		TabItem item=new TabItem(tab,SWT.None);
		item.setText("Keys");
		Composite  composite =new Composite(tab,SWT.NONE);
		item.setControl(composite);
		
	}
	public void CreateCodeTab(TabFolder tab)
	{
		TabItem item=new TabItem(tab,SWT.None);
		item.setText("Code");
		Composite  composite =new Composite(tab,SWT.NONE);
		composite.setLayout(new FillLayout());
		codeText=new Text(composite,SWT.BORDER|SWT.MULTI|SWT.WRAP|SWT.H_SCROLL|SWT.V_SCROLL);
		 String code=createCode();
		codeText.setText(code);
		item.setControl(composite);
		
		
	}
	public void CreateTableText(Table table,TableItem item,final int i)
	{
		final TableEditor editor=new TableEditor(table);
		editors.add(editor);
		final Text text=new Text(table,SWT.NONE);
		text.setText(item.getText(i));
		editor.grabHorizontal=true;
		editor.setEditor(text,item,i);
		text.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent evt)
			{
				editor.getItem().setText(i,text.getText());
			}
			
		});
		
	}
	public void CreateTableComb(Table table,TableItem item,final int i)
	{
		final TableEditor editor=new TableEditor(table);
		editors.add(editor);
		final Combo combo=new Combo(table,SWT.NONE);
		editor.grabHorizontal=true;
		editor.setEditor(combo,item,i);
	
	}
	public void CreateTableCheckButton(Table table,final TableItem item,final int i)
	{
		final TableEditor editor=new TableEditor(table);
		checkEditors.add(editor);
		final Button button=new Button(table,SWT.CHECK|SWT.LEFT);
		editor.grabHorizontal=true;
		editor.setEditor(button,item,i);
		if("true".equals(item.getText(i)))
			button.setSelection(true);
		else
			button.setSelection(false);
		button.addMouseListener(new MouseListener(){

			@Override
			public void mouseDoubleClick(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseDown(MouseEvent arg0)
			{
				
			}

			@Override
			public void mouseUp(MouseEvent arg0)
			{
				if (button.getSelection()==true)
					item.setText(i,"true");
				else
					item.setText(i, "false");
			}
		});
	
	}
	private static String uuid()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}

}
