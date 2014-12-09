package com.wt.studio.plugin.tools.views.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.wt.studio.plugin.tools.utils.HttpClientUtil;

public class RestControl
{
public static void init(TabFolder tabFolder)
{
	final ArrayList<TableEditor> editors=new ArrayList();
	TabItem item = new TabItem(tabFolder, SWT.NONE);
	item.setText("Rest");
	final Composite container = new Composite(tabFolder, SWT.NONE);
	GridLayout l1 = new GridLayout();
	l1.numColumns = 1;
	container.setLayout(l1);
	item.setControl(container);
	GridData griddata=new GridData();
	griddata.verticalSpan=3;
	griddata.verticalAlignment=SWT.FILL;
	griddata.horizontalAlignment=SWT.FILL;
	griddata.grabExcessHorizontalSpace=true;
	griddata.grabExcessVerticalSpace=true;
	GridData g1=new GridData();
	g1.horizontalAlignment=SWT.FILL;
	g1.grabExcessHorizontalSpace=true;
	g1.heightHint=40;
	GridData g4=new GridData();
	g4.horizontalAlignment=SWT.FILL;
	g4.grabExcessHorizontalSpace=true;
	Composite c1=new Composite(container,SWT.NONE);
	GridLayout l2=new GridLayout();
	l2.numColumns=6;
	c1.setLayout(l2);
	c1.setLayoutData(g1);
	//--------------c1内容布局-------------------------
	Label label=new Label(c1,SWT.NONE);
	label.setText("填入url：");
	final Text t1=new Text(c1,SWT.BORDER);
	t1.setLayoutData(g4);
	final Combo combo=new Combo(c1,SWT.DROP_DOWN|SWT.BORDER);
	String[] items=new String[4];
	items[0]="GET";
	items[1]="POST-多媒体";
	items[2]="POST-表单";
	items[3]="POST-raw";
	combo.setItems(items);
	combo.select(0);
	final Button b2=new Button(c1,SWT.PUSH);
	b2.setText("发送");
	final Button b1=new Button(c1,SWT.PUSH);
	b1.setText("解析");
	final Button b4=new Button(c1,SWT.PUSH);
	b4.setText("重置");
	
	final Composite folder=new Composite(container,SWT.NONE);
	folder.setLayoutData(griddata);
	folder.setLayout(new FillLayout());
	final TabFolder tabFolder1=new TabFolder(folder,SWT.NONE);
	tabFolder1.setLayout(new FillLayout());
	//-------------folderContent1布局内容(url参数)-----------------------
	final Composite folderContent1=new Composite(tabFolder1,SWT.NONE);
	folderContent1.setLayout(l1);
	//c2
	Composite c2=new Composite(folderContent1,SWT.NONE);
	GridLayout l3=new GridLayout();
	l3.numColumns=6;
	c2.setLayout(l3);
	c2.setLayoutData(g1);
	GridData g2=new GridData();
	g2.widthHint=200;
	final Button b3=new Button(c2,SWT.PUSH);
	b3.setText("添加");
	final Button getdel=new Button(c2,SWT.PUSH);
	getdel.setText("删除");
	//c3
	
	final Composite c3=new Composite(folderContent1,SWT.NONE);
	c3.setLayoutData(griddata);
	c3.setLayout(new FillLayout());
	final Table getTable=new Table(c3,SWT.MULTI|SWT.CHECK|SWT.FULL_SELECTION);//----------->get参数表格
	String[] tableHeader={"参数名称                             ","参数值                                     "};
	for(int i=0;i<tableHeader.length;i++)
	{
		TableColumn tableColumn=new TableColumn(getTable,SWT.NONE);
		tableColumn.setText(tableHeader[i]);
		tableColumn.setMoveable(true);
	}
	getTable.setHeaderVisible(true);
	getTable.setLinesVisible(true);
	for(int i=0;i<getTable.getColumnCount();i++)
	{
		getTable.getColumn(i).pack();
	}
	TableItem ditem=new TableItem(getTable,SWT.NONE);
	getTable.addSelectionListener(new SelectionListener(){
	   TableEditor editor=new TableEditor(getTable);
	   TableEditor editor1=new TableEditor(getTable);
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void widgetSelected(SelectionEvent arg0)
		{    
			int total=getTable.getItemCount();
			for(int i=0;i<total;i++)
			{
				
				if(getTable.isSelected(i))
				{
					TableItem item=getTable.getItem(i);
					if(i==total-1)
						{
						TableItem litem=new TableItem(getTable,SWT.NONE);
						}
					 if(editor.getEditor()!= null) {
						 Control cont = editor.getEditor();
						 cont.dispose();
					 }
					 if(editor1.getEditor()!= null) {
						 Control cont1 = editor1.getEditor();
						 cont1.dispose();
					 }
					
					final Text text=new Text(getTable,SWT.NONE);
					text.setText(item.getText(0));
					editor.grabHorizontal=true;
					editor.setEditor(text,item,0);
					editors.add(editor);
					text.addModifyListener(new ModifyListener(){

						@Override
						public void modifyText(ModifyEvent arg0)
						{
							editor.getItem().setText(0,text.getText());
							String url[]=t1.getText().split("\\?");
							t1.setText(url[0]+"?"+HttpClientUtil.Splice(getTable));
						}
						
					});
					
					
					
					 final Text text1=new Text(getTable,SWT.NONE);
					 text1.setText(item.getText(1));
					 editor1.setEditor(text1,item,1);
					 editor1.grabHorizontal=true;
					 editors.add(editor1);
					 text1.addModifyListener(new ModifyListener(){

						@Override
						public void modifyText(ModifyEvent arg0)
						{
							editor1.getItem().setText(1,text1.getText());
							String url[]=t1.getText().split("\\?");
							t1.setText(url[0]+"?"+HttpClientUtil.Splice(getTable));
						}
				   });	
				}
			
		}
			  folderContent1.layout();
			   c3.layout();	
		}
		
	});
	//------------------folderContent2布局内容（post 多媒体方式）----------
	
	final Composite folderContent2=new Composite(tabFolder1,SWT.NONE);
	folderContent2.setLayout(l1);
	Composite c51=new Composite(folderContent2,SWT.NONE);
	GridLayout grid=new GridLayout();
	grid.numColumns=2;
	c51.setLayout(grid);
	final Button add=new Button(c51,SWT.PUSH);
	add.setText("添加");
	final Button del=new Button(c51,SWT.PUSH);
	del.setText("删除");
	
	
	final Composite c5=new Composite(folderContent2,SWT.NONE);
	c5.setLayoutData(griddata);
	c5.setLayout(new FillLayout());
	final Table postMultiTable=new Table(c5,SWT.MULTI|SWT.CHECK|SWT.FULL_SELECTION);//---------------------->Post 多媒体参数表格
	String[] tableHeader2={"参数名称        ","参数值                                  ","参数类型     ","文件地址             "};
	for(int i=0;i<tableHeader2.length;i++)
	{
		TableColumn tableColumn=new TableColumn(postMultiTable,SWT.NONE);
		tableColumn.setText(tableHeader2[i]);
		tableColumn.setMoveable(true);
	}
	postMultiTable.setHeaderVisible(true);
	postMultiTable.setLinesVisible(true);
	for(int i=0;i<postMultiTable.getColumnCount();i++)
	{
		postMultiTable.getColumn(i).pack();
	}
	TableItem ditem1=new TableItem(postMultiTable,SWT.NONE);
	TableItem ditem3=new TableItem(postMultiTable,SWT.NONE);
	TableItem ditem4=new TableItem(postMultiTable,SWT.NONE);
	postMultiTable.addSelectionListener(new SelectionListener(){
		   TableEditor editor=new TableEditor(postMultiTable);
		   TableEditor editor1=new TableEditor(postMultiTable);
		   TableEditor editor2=new TableEditor(postMultiTable);
		   TableEditor editor3=new TableEditor(postMultiTable);
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0)
			{    
				int total=postMultiTable.getItemCount();
				for(int i=0;i<total;i++)
				{
					
					if(postMultiTable.isSelected(i))
					{   
						TableItem item=postMultiTable.getItem(i);
						if(i==total-1)
					    {
					      TableItem litem=new TableItem(postMultiTable,SWT.NONE);
					    }
						if(item.getText(2).equals(""))
						      item.setText(2,"Text");
						 if(editor.getEditor()!= null) {
							 Control cont = editor.getEditor();
							 cont.dispose();
						 }
						 if(editor2.getEditor()!= null) {
							 Control cont = editor2.getEditor();
							 cont.dispose();
						 }
						final Text text=new Text(postMultiTable,SWT.NONE);
						text.setText(item.getText(0));
						editor.grabHorizontal=true;
						editor.setEditor(text,item,0);
						editors.add(editor);
						text.addModifyListener(new ModifyListener(){

							@Override
							public void modifyText(ModifyEvent arg0)
							{
								editor.getItem().setText(0,text.getText());
								
							}
							
						});
						 final CCombo combo=new CCombo(postMultiTable,SWT.NONE);
						 combo.add("Text");
						 combo.add("File");
						 combo.setText(item.getText(2));
						 editor2.grabHorizontal=true;
						 editor2.setEditor(combo, item, 2);
						 editors.add(editor2);
						 getCommboControl(combo,item);
						 combo.addModifyListener(new ModifyListener(){
							public void modifyText(ModifyEvent arg0)
							{
								editor2.getItem().setText(2, combo.getText());
								TableItem item= editor2.getItem();
								item.setText(1, "");
								item.setText(3, "");
								getCommboControl(combo,item);
			   }
		   });
		 }
	   }
	 }
			/**
			 * 
			 * 方法说明：根据combo的选项得到相应的表格控件。
			 *
			 * @param combo
			 * @param item
			 */
			public void getCommboControl(CCombo combo,TableItem item){
				if(combo.getText().equals("Text"))
				{
					
					 if(editor3.getEditor() != null) {
						 Control cont = editor3.getEditor();
						 cont.dispose();
					 }
					 if(editor1.getEditor() != null) {
						 Control cont = editor1.getEditor();
						 cont.dispose();
					 }
					 final Text text1=new Text(postMultiTable,SWT.NONE);
					 text1.setText(item.getText(1));
					 editor1.grabHorizontal=true;
					 editor1.setEditor(text1, item, 1);
					 editors.add(editor1);
					 text1.addModifyListener(new ModifyListener(){
					 public void modifyText(ModifyEvent e)
							{
								editor1.getItem().setText(1, text1.getText());
							}	
							}
							);
					
					
				}
			 else if(combo.getText().equals("File"))
				{
					 if(editor1.getEditor() != null) {
						 Control cont = editor1.getEditor();
						 cont.dispose();
					 }
					 if(editor3.getEditor() != null) {
						 Control cont = editor3.getEditor();
						 cont.dispose();
					 }
					 final Button b1= new Button(postMultiTable,SWT.NONE);
					 b1.setText("请选择文件");
					 editor3.grabHorizontal=true;
					 editor3.setEditor(b1, item, 1);
					 editors.add(editor3);
					 b1.addSelectionListener(new SelectionListener(){

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0)
						{
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0)
						{
							FileDialog dialog=new FileDialog(container.getShell(),SWT.OPEN);
							dialog.setFilterPath(System.getProperty("java.home"));
							dialog.setFilterExtensions(new String[]{".txt","*.*"});
							dialog.setFilterNames(new String[]{"Text File(*.txt)","All Files(*.*)"});
							String fileAdress=dialog.open();
							editor.getItem().setText(1, fileAdress);
							editor.getItem().setText(3, fileAdress);
							
						}
						 
	 });
  }
			} 
	});
	//------------------folderContent3布局内容（post 表单方式）----------
	final Composite folderContent3=new Composite(tabFolder1,SWT.NONE);
	folderContent3.setLayout(new GridLayout());
	//c61
	Composite c61=new Composite(folderContent3,SWT.NONE);
	c61.setLayout(l3);
	c61.setLayoutData(g1);
	final Button b5=new Button(c61,SWT.PUSH);
	final Button postdel=new Button(c61,SWT.PUSH);
	b5.setText("添加");
	postdel.setText("删除");
	
	
	//c6
	final Composite c6=new Composite(folderContent3,SWT.NONE);
	c6.setLayout(new FillLayout());
	c6.setLayoutData(griddata);
	final Table postFormTable=new Table(c6,SWT.MULTI|SWT.CHECK|SWT.FULL_SELECTION);//----------------------->Post 表单参数表格
	String[] tableHeader1={"参数名称           ","参数值                                   "};
	for(int i=0;i<tableHeader1.length;i++)
	{
		TableColumn tableColumn=new TableColumn(postFormTable,SWT.NONE);
		tableColumn.setText(tableHeader1[i]);
		tableColumn.setMoveable(true);
	}
	postFormTable.setHeaderVisible(true);
	postFormTable.setLinesVisible(true);
	for(int i=0;i<postFormTable.getColumnCount();i++)
	{
		postFormTable.getColumn(i).pack();
	}
	TableItem ditem2=new TableItem(postFormTable,SWT.NONE);
	postFormTable.addSelectionListener(new SelectionListener(){
		   TableEditor editor=new TableEditor(postFormTable);
		   TableEditor editor1=new TableEditor(postFormTable);
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0)
			{    
				int total=postFormTable.getItemCount();
				for(int i=0;i<total;i++)
				{
					
					if(postFormTable.isSelected(i))
					{
						 TableItem item=postFormTable.getItem(i);
						 if(i==total-1)
						    {
						      TableItem litem=new TableItem(postFormTable,SWT.NONE);
						    }
						 if(editor.getEditor()!= null) {
							 Control cont = editor.getEditor();
							 cont.dispose();
						 }
						 if(editor1.getEditor()!= null) {
							 Control cont1 = editor1.getEditor();
							 cont1.dispose();
						 }
						
						final Text text=new Text(postFormTable,SWT.NONE);
						text.setText(item.getText(0));
						editor.grabHorizontal=true;
						editor.setEditor(text,item,0);
						editors.add(editor);
						text.addModifyListener(new ModifyListener(){

							@Override
							public void modifyText(ModifyEvent arg0)
							{
								editor.getItem().setText(0,text.getText());
								
							}
							
						});
						
						 final Text text1=new Text(postFormTable,SWT.NONE);
						 text1.setText(item.getText(1));
						 editor1.setEditor(text1,item,1);
						 editor1.grabHorizontal=true;
						 editors.add(editor1);
						 text1.addModifyListener(new ModifyListener(){

							@Override
							public void modifyText(ModifyEvent arg0)
							{
								editor1.getItem().setText(1,text1.getText());
							}
					   });	
					}
				
			     }
			}
	});
	//-----------------folderContent4布局内容（post RAW方式）--------------
	final Composite folderContent4=new Composite(tabFolder1,SWT.NONE);
    folderContent4.setLayout(new GridLayout());
    final Combo combo1=new Combo(folderContent4,SWT.DROP_DOWN|SWT.BORDER);
	String[] items1=new String[4];
	items1[0]="Text";
	items1[1]="JSON";
	items1[2]="XML";
	items1[3]="HTML";
	combo1.setItems(items1);
	combo1.select(0);
	final Text t5=new Text(folderContent4,SWT.BORDER|SWT.WRAP|SWT.V_SCROLL);
	t5.setLayoutData(griddata);
	//-----------------result内容布局-----------------------
	final Composite result=new Composite(container,SWT.NONE);
	result.setLayoutData(griddata);
	result.setLayout(new FillLayout());
	final Text t4=new Text(result,SWT.BORDER|SWT.WRAP|SWT.V_SCROLL);
	//------------------选项卡布局-------------
	TabItem item1=new TabItem(tabFolder1,SWT.NONE);
	item1.setText("url参数");
	item1.setControl(folderContent1);
	TabItem item2=new TabItem(tabFolder1,SWT.NONE);
	item2.setText("post参数(多媒体)");
	item2.setControl(folderContent2);
	TabItem item3=new TabItem(tabFolder1,SWT.NONE);
	item3.setText("post参数(表单)");
	item3.setControl(folderContent3);
	TabItem item4=new TabItem(tabFolder1,SWT.NONE);
	item4.setText("post参数(raw)");
	item4.setControl(folderContent4);
	folderContent2.setEnabled(false);
	folderContent3.setEnabled(false);
	folderContent4.setEnabled(false);
	folderContent1.setEnabled(true);
    //******************布局结束************************
	combo.addModifyListener(new ModifyListener(){

		@Override
		public void modifyText(ModifyEvent arg0)
		{
			if(combo.getText().equals("GET"))
				{tabFolder1.setSelection(0);
			    folderContent2.setEnabled(false);
			    folderContent3.setEnabled(false);
			    folderContent4.setEnabled(false);
			    folderContent1.setEnabled(true);}
			else if(combo.getText().equals("POST-多媒体"))
				{tabFolder1.setSelection(1);
				folderContent3.setEnabled(false);
			    folderContent4.setEnabled(false);
			    folderContent1.setEnabled(true);
			    folderContent2.setEnabled(true);
				}
			else if(combo.getText().equals("POST-表单"))
				{
				tabFolder1.setSelection(2);
				folderContent2.setEnabled(false);
			    folderContent4.setEnabled(false);
			    folderContent1.setEnabled(true);
			    folderContent3.setEnabled(true);
				}
			else if(combo.getText().equals("POST-raw"))
				{
				tabFolder1.setSelection(3);
				folderContent3.setEnabled(false);
			    folderContent2.setEnabled(false);
			    folderContent1.setEnabled(true);
			    folderContent4.setEnabled(true);
				}
		}
		
	});	
	/**
	 * 按钮监听函数
	 */
	SelectionListener listener=new SelectionListener(){
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0)
		{	
		}
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			Widget w=e.widget;
			if(w==b1)//********************解析按钮事件
			{   if(combo.getText().equals("GET"))
			   {
				getTable.removeAll();
				Map map=new LinkedHashMap();
			    map=HttpClientUtil.urlSplit(t1.getText());
			    Iterator iter = map.entrySet().iterator();
			       while(iter.hasNext()){
			    	    Map.Entry entry=(Map.Entry)iter.next();
			            Object key=entry.getKey();
			    	    Object val=entry.getValue();
			    	    TableItem item=new TableItem(getTable,SWT.NONE);
			    	    item.setText(new String[]{key.toString(),val.toString()});
			    	   }	
			    folderContent1.layout();
			    c3.layout();	
			   }
			}
			 if(w==b2)//****************发送按钮事件
			{
				 if(combo.getText().equals("GET"))
				 {
			     String url=t1.getText();
			     if(url.equals("")||url.equals("请填写url"))
			     {
				      t1.setText("请填写url");
			     }
			     else{
			    	 if(combo.getSelectionIndex()==0)
			         {
			         try {
				          t4.setText(HttpClientUtil.httpGet(url));
			             } catch (IllegalStateException e1) {
				                  e1.printStackTrace();
			             } catch (IOException e1) {
				                  e1.printStackTrace();}
			          }
			        
			         else if(combo.getSelectionIndex()==1)
			        {
			    	  
			        }
			        }
			      }
				 else if(combo.getText().equals("POST-多媒体"))
				 {
					 String url=t1.getText();
				     if(url.equals("")||url.equals("请填写url"))
				     {
					      t1.setText("请填写url");
				     }
				     else
				     { 
				    	 Map<String, String> text=new HashMap();
				    	 Map<String, String> file=new HashMap();
				    	 TableItem[] items=postMultiTable.getItems();
				    	   for(int i=0;i<items.length;i++)
				    	   {   
				    		   if(!(items[i].getText(0).equals("")||items[i].getText(1).equals(""))){
				    			   
				    		   
				    		   if(items[i].getText(2).equals("File"))
				    		          file.put(items[i].getText(0), items[i].getText(1));
				    	       else
				    	    	      text.put(items[i].getText(0), items[i].getText(1));
				    		   }
				    	   }
				    		   
				    	   try {
							t4.setText(HttpClientUtil.httpPostMulti(url,file,text));
						} catch (ClientProtocolException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				     }
				 }
				 else if(combo.getText().equals("POST-表单"))
				 {     String url=t1.getText();
			           if(url.equals("")||url.equals("请填写url"))
			           {
				       t1.setText("请填写url");
			           }
					   Map<String, String> params=new HashMap();
			    	   TableItem[] items=postFormTable.getItems();
			    	   for(int i=0;i<items.length;i++)
			    	   {
			    		   if(!(items[i].getText(0).equals("")||items[i].getText(1).equals("")))
			    		              params.put(items[i].getText(0), items[i].getText(1));
			    	   }
			    	   try {
						t4.setText(HttpClientUtil.httpPostForm(url,params));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 else if(combo.getText().equals("POST-raw"))
				 {
					   String url=t1.getText();
			           if(url.equals("")||url.equals("请填写url"))
			           {
				       t1.setText("请填写url");
			           }
			           try {
			        	if(combo1.getText().equals("Text"))   
						       t4.setText(HttpClientUtil.httpPostRaw(url,t5.getText(),0));
			        	else if(combo1.getText().equals("JSON")) 
			        		   t4.setText(HttpClientUtil.httpPostRaw(url,t5.getText(),1));
			        	else if(combo1.getText().equals("XML")) 
		        		       t4.setText(HttpClientUtil.httpPostRaw(url,t5.getText(),2));
			        	else if(combo1.getText().equals("HTML")) 
		        		       t4.setText(HttpClientUtil.httpPostRaw(url,t5.getText(),3));
					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 
			}
			else if(w==b3)//*******************get添加按钮事件
			{
				String url=t1.getText();
				 if(url.equals("")||url.equals("请填写url"))
				    	t1.setText("请填写url");
				 
				 else{
					 TableItem item=new TableItem(getTable,SWT.NONE);
					 folderContent1.layout();
					 c3.layout(); 
				 }
				
			}
			else if(w==b4)//*******************reset按钮事件
			{
				for(TableEditor editor:editors){
				     Control cont = editor.getEditor();
					 cont.dispose();
					}
				getTable.removeAll();
				postFormTable.removeAll();
				postMultiTable.removeAll();
				t1.setText("");
				t4.setText("");
				t5.setText("");
				container.layout();
			    folderContent1.layout();
			    c3.layout();
			    c6.layout();
			    c5.layout();
  
			}
			else if(w==b5)//***************************post表单添加按钮
			{
				TableItem item=new TableItem(postFormTable,SWT.NONE);
				 c6.layout();
			}
			else if(w==add)//**********************添加post多媒体参数按钮
			{
				 final TableItem item=new TableItem(postMultiTable,SWT.NONE);
				 
			}
			else if(w==getdel)//*************************删除get参数按钮
			{
			  for(TableEditor editor:editors){
				     Control cont = editor.getEditor();
					 cont.dispose();
					}
				   TableItem[] items=getTable.getItems();
				for(int i=0;i<items.length;i++)
				{
					if(!items[i].getChecked())
						continue;
					int index=getTable.indexOf(items[i]);
					if(index<0)
						continue;
				getTable.remove(index);
				}
				if(!t1.getText().equals(""))
				{
					String url[]=t1.getText().split("\\?");
					t1.setText(url[0]+"?"+HttpClientUtil.Splice(getTable));
				}
				
			}
			else if(w==postdel)//*************************删除post表单参数按钮
			{
			  for(TableEditor editor:editors){
				     Control cont = editor.getEditor();
					 cont.dispose();
					}
				   TableItem[] items=postFormTable.getItems();
				for(int i=0;i<items.length;i++)
				{
					if(!items[i].getChecked())
						continue;
					int index=postFormTable.indexOf(items[i]);
					if(index<0)
						continue;
				postFormTable.remove(index);
				}
			}
			else if(w==del)//*************************删除多媒体参数按钮
			{
				for(TableEditor editor:editors){
				     Control cont = editor.getEditor();
					 cont.dispose();
					}
				TableItem[] items=postMultiTable.getItems();
				for(int i=0;i<items.length;i++)
				{
					if(!items[i].getChecked())
						continue;
					int index=postMultiTable.indexOf(items[i]);
					if(index<0)
						continue;
					postMultiTable.remove(index);
				}
				
			}
		}
		
	
};

b1.addSelectionListener(listener);
b2.addSelectionListener(listener);
b3.addSelectionListener(listener);
b4.addSelectionListener(listener);
b5.addSelectionListener(listener);
add.addSelectionListener(listener);
del.addSelectionListener(listener);
getdel.addSelectionListener(listener);
postdel.addSelectionListener(listener);
}
}
