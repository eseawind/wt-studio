package com.wt.studio.plugin.modeldesigner.context;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

import org.dom4j.DocumentException;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.wt.studio.plugin.modeldesigner.dialog.TableFromDataBase;
import com.wt.studio.plugin.modeldesigner.dialog.TableFromDataBaseDialog;
import com.wt.studio.plugin.modeldesigner.editor.editpart.BOModelDiagramEditPart;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.pagedesigner.gef.dialog.MOFunctionDialog;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;



public class ImportTableFromDatabaseAction extends SelectionAction
{
	private IWorkbenchPart part;

	public ImportTableFromDatabaseAction(IWorkbenchPart part)
	{
		super(part);
		setId("importTableFormDB");
		setText("导入");
		this.part = part;
	}

	@Override
	public void run()
	{
		//System.out.println("coming...");
		super.run();
		TableFromDataBase funs = null;
		try {
			funs = new TableFromDataBase(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (funs.open() == IDialogConstants.OK_ID) {
//			BOModelDiagramEditPart diagramEditPart = (BOModelDiagramEditPart) getSelectedObjects().get(0);
//			BOModelDiagram child = new BOModelDiagram();
//			((BOModelDiagram) diagramEditPart.getModel()).addTableModel(table);
			List<HdbTableModel> tableModels=funs.getHdbTables();
			int x=0;
			int y=0;
			for(HdbTableModel table:tableModels)
			{
				x=x+20;
				y=y+20;
				AbstractGraphicalEditPart editPart=(AbstractGraphicalEditPart) getSelectedObjects().get(0);
				BOModelDiagramEditPart diagramEditPart=(BOModelDiagramEditPart) editPart.getRoot().getViewer().getContents();
				//BOModelDiagramEditPart diagramEditPart = (BOModelDiagramEditPart) getSelectedObjects().get(0);
				BOModelDiagram child = new BOModelDiagram();
				table.setRectangle(new Rectangle(x,y,-1,-1));
				((BOModelDiagram) diagramEditPart.getModel()).addTableModel(table);
			}
			
		}
	}

	@Override
	protected boolean calculateEnabled()
	{
		
		return true;
	}
	
}
