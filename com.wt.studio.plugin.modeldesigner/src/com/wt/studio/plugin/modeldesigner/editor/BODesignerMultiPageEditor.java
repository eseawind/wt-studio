package com.wt.studio.plugin.modeldesigner.editor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.properties.PropertySheetPage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.io.Reader.DiagramXmlReader;
import com.wt.studio.plugin.modeldesigner.utils.ConstantResource;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;

/**
 * BODesigner MultiPage 类
 * @author gl
 *
 */
public class BODesignerMultiPageEditor extends MultiPageEditorPart{
	private BODesignerEditorPartFactory editPartFactory;
	private BOModelDiagram diagram;
	private BODesignerOutlinePage outlinePage;
	private PropertySheetPage propertySheetPage;
	private boolean savePreviouslyNeeded;
	
	

	public BODesignerMultiPageEditor() {
		this.propertySheetPage = new PropertySheetPage();
		
	}

	@Override
	protected void createPages() {
		//XStream xStream = new XStream(new DomDriver());
//		xStream.alias("diagram", BOModelDiagram.class);
		DiagramXmlReader reader=new DiagramXmlReader();
		String result;
		try {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			this.setPartName(file.getName());

			if (!file.isSynchronized(IResource.DEPTH_ONE)) {
				file.refreshLocal(IResource.DEPTH_ONE,
						new NullProgressMonitor());
			}
			InputStream in = file.getContents();
			result = getStreamString(in);
			if (!result.equals("")) {
				BOModelDiagram newdia = reader.parseDiagram(result);
				this.diagram = newdia;
			} else {
				diagram = new BOModelDiagram();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		//this.diagram = new BOModelDiagram();
		this.editPartFactory = new BODesignerEditorPartFactory();
		this.outlinePage = new BODesignerOutlinePage(null);
		BODesignerEditor editor = new BODesignerEditor(this.diagram,
				this.editPartFactory, this.outlinePage);

		int index;
		try {
			index = this.addPage(editor, this.getEditorInput());
			this.setPageText(index, ConstantResource.BOModel_DESIGNER);
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		//this.initStartPage();
	}
	
	private void initStartPage() {
		this.setActivePage(0);
		BODesignerEditor activeEditor = (BODesignerEditor) this.getActiveEditor();
		ZoomManager zoomManager = (ZoomManager)activeEditor
				.getAdapter(ZoomManager.class);
		zoomManager.setZoom(this.diagram.getZoom());
	}

	@Override
	protected Composite createPageContainer(Composite parent) {
		try {
			IWorkbenchPage page = this.getSite().getWorkbenchWindow()
					.getActivePage();

			if (page != null) {
				page.showView(IPageLayout.ID_OUTLINE);
			}

		} catch (PartInitException e) {
			e.printStackTrace();
		}

		return super.createPageContainer(parent);
	}
	@Override
	public void doSave(IProgressMonitor monitor) {
		
		
		for (int i = 0; i < this.getPageCount(); i++) {
			IEditorPart editor = this.getEditor(i);
			editor.doSave(monitor);
		}
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
	public BODesignerEditor getActiveEditor() {
		return (BODesignerEditor) super.getActiveEditor();
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
}
