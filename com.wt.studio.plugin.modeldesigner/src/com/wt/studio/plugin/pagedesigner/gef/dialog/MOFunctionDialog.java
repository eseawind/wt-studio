package com.wt.studio.plugin.pagedesigner.gef.dialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dom4j.DocumentException;


import org.eclipse.core.internal.resources.Container;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
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

public class MOFunctionDialog extends Dialog
{
	private MOFunctionTableModel function;
	private BOModelDiagram diagram;
	private MOFunctionTableModelEditPart editPart;
	String url="D:\\龚泽强\\HEA Studio V1.1.0-SR1-win32-x86_32\\HEA Studio\\runtime-EclipseApplication\\test\\src\\test.hdb";
	private File db;
	private Table tableData;
	private List<Button> tableButton=new ArrayList<Button>();
	Composite fileArea;
	final ArrayList<TableEditor> checkEditors=new ArrayList();

	public MOFunctionDialog(Shell parentShell,MOFunctionTableModel function, MOFunctionTableModelEditPart editPart) throws FileNotFoundException, MalformedURLException, DocumentException
	{
		super(parentShell);
		this.function=function;
		this.editPart=editPart;
		setShellStyle(getShellStyle()  |  SWT.RESIZE  |  SWT.MAX);
	}
	

	private List<IFile> getDBFiles() throws CoreException 
	{
		// TODO Auto-generated method stub
		IContainer project= getProject();
		IResource[] resources=project.members();
		List<IFile> dbFiles=new ArrayList<IFile>();
		for(IResource re:resources)
		 {
			 
			 if(re instanceof IFile&&re.getFileExtension().equals("hdb"))
			 {

				dbFiles.add((IFile) re);
			 }
			 else if(re instanceof IFolder&&!re.getName().equals("bin"))
				 dbFiles.addAll(getFileFromFolder((IFolder)re));
		 }
		return dbFiles;
	}


	private List<IFile> getFileFromFolder(IFolder folder) throws CoreException
	{
		IResource[] resources=folder.members();
		List<IFile> dbFiles=new ArrayList<IFile>();
		for(IResource re:resources)
		 {
			 
			 if(re instanceof IFile&&re.getFileExtension().equals("hdb"))
			 {

				dbFiles.add((IFile) re);
			 }
			 else if(re instanceof IFolder)
				 dbFiles.addAll(getFileFromFolder((IFolder)re));
		 }
		return dbFiles;
	}


	private BOModelDiagram paresDiagram(IFile db) throws FileNotFoundException, MalformedURLException, DocumentException, CoreException
	{
		DiagramXmlReader reader=new DiagramXmlReader();
		InputStream fis= db.getContents();
		String contents=getStreamString(fis);
		BOModelDiagram newdia;
		if (!contents.equals("")) {
		    newdia = reader.parseDiagram(contents);
		} else {
			newdia = new BOModelDiagram();
		}
		return newdia;
	}


	protected Point getInitialSize() { 
		return new Point(600,600); 
		} 
		
		protected void configureShell(Shell newShell)
		{
			super.configureShell(newShell);
			newShell.setText("配置MO模型");
		}
		
		protected Control createDialogArea(Composite parent)
		{
			Composite comp=(Composite)super.createDialogArea(parent); 
			comp.setBounds(0, 0, 600, 600);
			comp.setLayout(new FillLayout());
			Group table=new Group(comp,SWT.NULL);
			table.setText("表数据源");
			table.setLayout(new GridLayout());
			Composite fileRadio=new Composite(table,SWT.NULL);
			fileRadio.setLayout(new GridLayout(5,false));
			fileArea=new Composite(table,SWT.NULL);
			fileArea.setLayout(new FillLayout());
			GridData gridData=new GridData();
			gridData.horizontalAlignment = GridData.FILL;
			gridData.grabExcessHorizontalSpace = true;
            gridData.verticalAlignment=GridData.FILL;
            gridData.grabExcessVerticalSpace=true;
            fileArea.setLayoutData(gridData);
			tableData=new Table(fileArea,SWT.FULL_SELECTION);
			tableData.setHeaderVisible(true);
			tableData.setLinesVisible(true);
			String[] tableHeader={"Name","Code","Comment","Type","keyWords",""};
			for(int i=0;i<tableHeader.length;i++)
			{
				TableColumn tableColumn=new TableColumn(tableData,SWT.NONE);
				tableColumn.setText(tableHeader[i]);
			}
			for(int i=0;i<tableHeader.length;i++)
			{
				tableData.getColumn(i).setWidth(80);
			}
			List<IFile> files = null;
			try {
				files = getDBFiles();
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(final IFile file:files)
			{
				final Button button=new Button(fileRadio,SWT.RADIO);
				button.setText(file.getName());
				button.setData(file);
				tableButton.add(button);
				if(function.getDbFile()!=null&&function.getDbFile().equals(file.getName()))
				{
					button.setSelection(true);
				}
				button.addSelectionListener(new SelectionListener(){

					@Override
					public void widgetDefaultSelected(SelectionEvent e)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void widgetSelected(SelectionEvent e)
					{
						// TODO Auto-generated method stub
						if(button.getSelection())
						{
							createTableData((IFile)button.getData());
							function.setDbFile(file.getName());
						}
					}
					
				});
			}
			showSelectedTable();		
			return parent;
		 
		} 
		
		private void showSelectedTable()
		{
			// TODO Auto-generated method stub
			for(Button b:tableButton)
			{
				if(b.getSelection())
					createTableData((IFile)b.getData());
			}
			
		}


		public void CreateTableButton(Table table,final TableItem item,final int i)
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
					HdbTableModel table=(HdbTableModel) item.getData();
					function.getColumns().clear();
					List<HdbColumnModel> dbColumns=table.getColumns();
					for(HdbColumnModel dbColumn:dbColumns)
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
		}
		private void createTableData(IFile file)
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
			List <HdbTableModel>tables=diagram.getTableModels();
			for(HdbTableModel table:tables)
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
}
