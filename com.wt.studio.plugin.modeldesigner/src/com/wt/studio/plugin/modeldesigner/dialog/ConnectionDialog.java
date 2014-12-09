package com.wt.studio.plugin.modeldesigner.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;

public class ConnectionDialog  extends Dialog
{
	private NodeConnection conn;
	final ArrayList<TableEditor> editors=new ArrayList();
	List<String> sourceNames;
	List<String> targetNames;
	List<String> relation=new ArrayList<String>();
	private Text codeText;
	private Table table;

	public ConnectionDialog(Shell parentShell,NodeConnection conn)
	{
		super(parentShell);
		this.conn=conn;
	}
	@Override 
	protected Point getInitialSize() { 
	return new Point(540,480); 
	} 
	
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText("配置Connection");
		setShellStyle(getShellStyle()  |  SWT.RESIZE  |  SWT.MAX);
	}
	protected Control createDialogArea(Composite parent)
	{
		Composite comp=(Composite)super.createDialogArea(parent);
		comp.setBounds(0, 0, 540, 480);
		comp.setLayout(new FillLayout());
		final TabFolder tab=new TabFolder(comp,SWT.TOP);
		//tab.setBounds(200,200,200,500);
		tab.setLayout(new FillLayout());
		CreateGeneralTab(tab);
		CreateJoinsTab(tab);
		//CreateIntegrity(tab);
		CreateCodeTab(tab);
		tab.pack();
		return parent;
	}
	private void CreateIntegrity(TabFolder tab)
	{
		
	}
	private void CreateJoinsTab(TabFolder tab)
	{
		TabItem item=new TabItem(tab,SWT.None);
		item.setText("Joins");
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
		cData.grabExcessVerticalSpace=true;
		center.setLayoutData(cData);
	    table=new Table(center,SWT.CHECK|SWT.MULTI|SWT.FULL_SELECTION);
		String[] tableHeader={"Select","Source Table Column","Target Table Column"};
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
		if(conn.getSource() instanceof HdbTableModel&&conn.getTarget() instanceof HdbTableModel)
		{
			HdbTableModel source=(HdbTableModel)conn.getSource();
			HdbTableModel target=(HdbTableModel)conn.getTarget();
			List <HdbColumnModel>sourceColumns=source.getColumns();
		    sourceNames=new ArrayList<String>();
			for(HdbColumnModel column:sourceColumns)
			{
				String name=column.getCode();
				sourceNames.add(name);
			}
			List<HdbColumnModel> targetColumns=target.getColumns();
			targetNames=new ArrayList<String>();
			for(HdbColumnModel column:targetColumns)
			{
				String name=column.getCode();
				targetNames.add(name);
			}
		for(int k=0;k<conn.getSourceColumnModels().size();k++)
		{
			String sourceId=conn.getSourceColumnModels().get(k);
			HdbColumnModel fromSource=((HdbTableModel)conn.getSource()).getColumnById(sourceId);
			if(fromSource!=null)
			{
			TableItem item1=new TableItem(table,SWT.NULL);
			item1.setText(1,fromSource.getCode());
			relation.add(fromSource.getCode());
			String targetId=conn.getTargetColumnModels().get(k);
			HdbColumnModel fromTarget=((HdbTableModel)conn.getTarget()).getColumnById(targetId);
			item1.setText(2,fromTarget.getCode());
			}
		}
		
			
		};
		table.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
			    
				int total=table.getItemCount();
				for(int i=0;i<total;i++)
				{
					
					if(table.isSelected(i))
					{
						
						final TableItem item=table.getItem(i);
					     for(TableEditor editor:editors)
					     {
						     Control cont = editor.getEditor();
							 cont.dispose();
						 }
					        final TableEditor editor=new TableEditor(table);
							editors.add(editor);
							final Combo combo=new Combo(table,SWT.READ_ONLY);
							editor.grabHorizontal=true;
							combo.setItems((String[])sourceNames.toArray(new String[sourceNames.size()]));
							combo.select(sourceNames.indexOf(item.getText(1)));
							editor.setEditor(combo,item,1);
							final TableEditor editor1=new TableEditor(table);
							editors.add(editor1);
							final Combo combo1=new Combo(table,SWT.READ_ONLY);
							editor1.grabHorizontal=true;
							combo1.setItems((String[])targetNames.toArray(new String[targetNames.size()]));
							combo1.select(targetNames.indexOf(item.getText(2)));
							editor1.setEditor(combo1,item,2);
							ModifyListener comboModify=new ModifyListener(){

								@Override
								public void modifyText(ModifyEvent e)
								{
									Widget w=e.widget;
									if(w==combo)
									{
										item.setText(1, sourceNames.get(combo.getSelectionIndex()));
										//conn.getSourceColumnModels().set(itemFlag,combo.getSelectionIndex() );
									}
									else if(w==combo1)
									{
										item.setText(2,targetNames.get(combo1.getSelectionIndex()));
										//conn.getTargetColumnModels().set(itemFlag, combo1.getSelectionIndex());
									}
								}
					};
					         combo.addModifyListener(comboModify);
					         combo1.addModifyListener(comboModify);
			}
				   composite.layout();	
			
			}
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
					 //tabItem.setText(1, sourceNames.get(0));
					// tabItem.setText(2,targetNames.get(0));
					 
				 }else if(w==b2)
				 {
					 for(TableEditor editor:editors)
				     {
					     Control cont = editor.getEditor();
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
						 conn.getSourceColumnModels().remove(index);
						 conn.getTargetColumnModels().remove(index);
						 relation.remove(index);
					     table.remove(index);
					     deleteModifiedColumnFromTarget();
					 }
				 }else if(w==b3)
				 {
					 deleteModifiedColumnFromTarget();
					 conn.getSourceColumnModels().clear();
					 conn.getTargetColumnModels().clear();
					 relation.clear();
					 for(TableItem item:table.getItems())
					 {
						 if(!"".equals(item.getText(1))&&!"".equals(item.getText(2)))
						 {
						 conn.getSourceColumnModels().add(((HdbTableModel)conn.getSource()).
								 getColumnByCode(item.getText(1)).getId());
						 conn.getTargetColumnModels().add(((HdbTableModel)conn.getTarget()).
								 getColumnByCode(item.getText(2)).getId());
						 }
						 relation.add(item.getText(1));
					 }
					 deleteModifiedColumnFromTarget();
					 codeText.setText(createCode());
				 }
				
			}

			
			
		}; 
		b1.addSelectionListener(listener);
		b2.addSelectionListener(listener);
		b3.addSelectionListener(listener);
		}
	private void deleteModifiedColumnFromTarget()
	{
		List<HdbColumnModel> sourceColumns=((HdbTableModel)conn.getSource()).getColumns();
		List<HdbColumnModel> delColumns=new ArrayList<HdbColumnModel>();
		for(HdbColumnModel column:sourceColumns)
		{
			
			if (isModify(column))
			{
				int index=((HdbTableModel)conn.getSource()).getColumns().indexOf(column);
				delColumns.add(column);
				//((TableModel)conn.getSource()).removeColumn(column);
				sourceNames.remove(index);
				
			}
		}
		sourceColumns.removeAll(delColumns);
		((HdbTableModel)conn.getSource()).refresh();
		
	}
	protected boolean isModify(HdbColumnModel column)
	{
		if(relation.contains(column.getCode()))
			return false;
		else if( isNotInFromTargetColumns(column)) 
			return false;
		else
			return true;
	}
	private boolean isNotInFromTargetColumns(HdbColumnModel column)
	{
		for(HdbColumnModel columnModel:conn.getFromTargetColumns())
		{
			if((column.getId()).equals(columnModel.getId()))
			   return false;
		}
		return true;
	}
	private void CreateGeneralTab(TabFolder tab)
	{
		TabItem item=new TabItem(tab,SWT.None);
		item.setText("General");
		Composite  composite =new Composite(tab,SWT.NONE);
		item.setControl(composite);
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
		Text t1=new Text(composite,SWT.BORDER);
		t1.setLayoutData(gridData);
		Label l2=new Label(composite,SWT.NONE);
		l2.setText("Code:");
		Text t2=new Text(composite,SWT.BORDER);
		//t2.setText(tableModel.getCode());
		t2.setLayoutData(gridData);
		Label l3=new Label(composite,SWT.NONE);
		l3.setText("Comment:");
		Text t3=new Text(composite,SWT.WRAP|SWT.BORDER);
		//t3.setText(tableModel.getComment());
		t3.setLayoutData(textArea);
	}

	public void CreateTableComb(Table table,TableItem item,int i)
	{
		final TableEditor editor=new TableEditor(table);
		editors.add(editor);
		final Combo combo=new Combo(table,SWT.NONE);
		editor.grabHorizontal=true;
		editor.setEditor(combo,item,i);
	
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
	private String createCode()
	{
		StringBuffer str=new StringBuffer();
		if(relation.size()>0)
		{
		str.append("alter table "+((HdbTableModel)conn.getSource()).getCode()+"\n");
		str.append("add constraint conn foreign key (");
		for(int i=0;i<table.getItems().length;i++)
		{
			TableItem item=table.getItem(i);
			String sourceCodeName=item.getText(1);
			str.append(sourceCodeName);
			if(i<table.getItemCount()-1)
				str.append(",");
		}
		str.append(")\n");
		str.append("reference "+((HdbTableModel)conn.getTarget()).getCode()+"(");
		for(int i=0;i<table.getItems().length;i++)
		{
			TableItem item=table.getItem(i);
			String targetCodeName=item.getText(2);
			str.append(targetCodeName);
			if(i<table.getItemCount()-1)
				str.append(",");
		}
		
		str.append(")\n");
		str.append("on update restrict\n");
		str.append("on delete restrict;");
		return str.toString();
		}

		else
		    return "";
	}
}