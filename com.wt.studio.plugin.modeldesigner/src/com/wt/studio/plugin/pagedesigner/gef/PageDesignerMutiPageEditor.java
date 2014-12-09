package com.wt.studio.plugin.pagedesigner.gef;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wt.studio.plugin.pagedesigner.gef.model.BOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.GhostElement;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;

public class PageDesignerMutiPageEditor extends MultiPageEditorPart
{

	private Diagram diagram;
	private boolean savePreviouslyNeeded;
	private PartFactory partFactory;

	@Override
	protected void createPages()
	{
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("diagram", Diagram.class);
		String result;
		try {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			this.setPartName(file.getName());

			if (!file.isSynchronized(IResource.DEPTH_ONE)) {
				file.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
			}
			InputStream in = file.getContents();
			result = getStreamString(in);
			if (!result.equals("")) {
				Diagram newdia = (Diagram) xStream.fromXML(result);
				this.diagram = newdia;
			} else {
				diagram = new Diagram();
				ControlPageModel page = new ControlPageModel();
				page.addElement(-1, new  GhostElement());
				diagram.addPageModel(page);
				VOFunctionTableModel func = new VOFunctionTableModel();
				func.setTitle("(Master)VO");
				func.setRectangle(new Rectangle(50, 50, 200, 200));
				diagram.setFunc(func);

				BOFunctionTableModel bo = new BOFunctionTableModel();
				FunctionColumnModel funcColumn = new FunctionColumnModel();
				funcColumn.setId("checkName()");
				funcColumn.setTitle("userName");
				bo.addColumn(funcColumn);
				diagram.getFunctionTableModels().add(bo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		partFactory = new PartFactory();
		PageDesignerEditor pageEditor = new PageDesignerEditor(this.partFactory, this.diagram, 0);
		PageDesignerEditor funcEditor = new PageDesignerEditor(this.partFactory, this.diagram, 1);
		int index;
		try {
			index = this.addPage(pageEditor, this.getEditorInput());
			this.setPageText(index, "原形视图");
			index = this.addPage(funcEditor, this.getEditorInput());
			this.setPageText(index, "逻辑视图");
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	protected Composite createPageContainer(Composite parent)
	{
		try {
			IWorkbenchPage page = this.getSite().getWorkbenchWindow().getActivePage();

			if (page != null) {
				page.showView(IPageLayout.ID_OUTLINE);
			}

		} catch (PartInitException e) {
			e.printStackTrace();
		}

		return super.createPageContainer(parent);
	}

	@Override
	public void doSave(IProgressMonitor monitor)
	{

		for (int i = 0; i < this.getPageCount(); i++) {
			IEditorPart editor = this.getEditor(i);
			editor.doSave(monitor);
		}
	}

	@Override
	public void doSaveAs()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSaveAsAllowed()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public GraphicalEditorWithPalette getActiveEditor()
	{
		return (GraphicalEditorWithPalette) super.getActiveEditor();
	}

	protected void pageChange(int newPageIndex)
	{
		PageDesignerEditor currentEditor = (PageDesignerEditor) this.getActiveEditor();
		currentEditor.removeSelection();
		super.pageChange(newPageIndex);

		// for (int i = 0; i < this.getPageCount(); i++) {
		// ERDiagramEditor editor = (ERDiagramEditor) this.getEditor(i);
		// editor.removeSelection();
		// }
		PageDesignerEditor newEditor = (PageDesignerEditor) this.getActiveEditor();
		if (newPageIndex == 1) {
			this.diagram.setType(1);
			//this.diagram.initFunctionModel();
		} else {
			this.diagram.setType(0);
		}
		this.diagram.refresh();
	}

	public static String getStreamString(InputStream tInputStream)
	{
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(
						tInputStream));
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

}