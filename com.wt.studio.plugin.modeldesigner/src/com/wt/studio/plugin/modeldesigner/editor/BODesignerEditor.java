package com.wt.studio.plugin.modeldesigner.editor;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wt.studio.plugin.modeldesigner.context.ImportContextMenuProvider;
import com.wt.studio.plugin.modeldesigner.context.ImportTableFromDatabaseAction;
import com.wt.studio.plugin.modeldesigner.editor.dnd.DiagramTemplateTransferDropTargetListener;
import com.wt.studio.plugin.modeldesigner.editor.editpart.PagableFreeformRootEditPart;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.io.Writer.DiagramXmlWriter;
import com.wt.studio.plugin.modeldesigner.utils.ColorResource;
/**
 * BODesigner Editor
 * 
 * @author gl
 * 
 */
public class BODesignerEditor extends GraphicalEditorWithPalette {
	
	/**
	 * 生产组件工厂
	 */
	private BODesignerEditorPartFactory editPartFactory;
	
	/**
	 * 图数据模型
	 */
	private BOModelDiagram diagram;
	/**
	 * OutLine
	 */
	private boolean savePreviouslyNeeded;
	private KeyHandler sharedKeyHandler;
	private BODesignerOutlinePage outlinePage;
	private BODiagramActionBarContributor actionBarContributor;

	private boolean dirty;

	public BODesignerEditor(BOModelDiagram diagram,
			BODesignerEditorPartFactory partFactory,
			BODesignerOutlinePage outlinePage) {
		DefaultEditDomain domain = new DefaultEditDomain(this);
		this.setEditDomain(domain);
		this.diagram = diagram;
		this.editPartFactory = partFactory;
		this.outlinePage = outlinePage;
	}

	public BODiagramActionBarContributor getActionBarContributor() {
		return actionBarContributor;
	}
	@Override
	protected PaletteRoot getPaletteRoot() {
		return new BODesignerEditorPaletteRoot();
	}

	/**
	 * 初始化View
	 */
	@Override
	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = this.getGraphicalViewer();
		viewer.setEditPartFactory(editPartFactory);
		
		this.initViewerAction(viewer);
		
		
		super.getGraphicalViewer().addDropTargetListener(
				new DiagramTemplateTransferDropTargetListener(super.getGraphicalViewer()));
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1),
				MouseWheelZoomHandler.SINGLETON);
		viewer.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, true);
		viewer.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, true);
		viewer.setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, true);
		 GraphicalViewer view=getGraphicalViewer();
		viewer.setContents(diagram);

	}
	protected void configureGraphicalViewer()
	{
		super.configureGraphicalViewer();
		getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart());
		//getGraphicalViewer().setEditPartFactory(new BODesignerEditorPartFactory());
		ContextMenuProvider cmProvider = new ImportContextMenuProvider(getGraphicalViewer(),
				getActionRegistry());
        getGraphicalViewer().setContextMenu(cmProvider);
        getSite().registerContextMenu(cmProvider, getGraphicalViewer());
	}
	
	protected void createActions()
	{
		// Create the actions
		super.createActions();
		ActionRegistry ar = getActionRegistry();
		// 一组继承自selectionaction的action
		IAction[] actions = new IAction[] { new ImportTableFromDatabaseAction(this) // 编辑属性
		};
		for (IAction a : actions) {
			ar.registerAction(a);
			getSelectionActions().add(a.getId());
		}
	}
	/**
	 * 初始化PaletteViewer
	 */
	protected void initializePaletteViewer()
	{
		super.initializePaletteViewer();
		super.getPaletteViewer().addDragSourceListener(
				new TemplateTransferDragSourceListener(super.getPaletteViewer()));
	}
	/**
	 * 初始化Editor内容
	 * @param viewer
	 */
	private void initViewerAction(GraphicalViewer viewer) {
		ScalableFreeformRootEditPart rootEditPart = new PagableFreeformRootEditPart(
				this.diagram);
		viewer.setRootEditPart(rootEditPart);
		ZoomManager manager = rootEditPart.getZoomManager();

		double[] zoomLevels = new double[] { 0.1, 0.25, 0.5, 0.75, 0.8, 1.0,
				1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels);
		
		List<String> zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);
		
		ZoomInAction zoomInAction = new ZoomInAction(manager);
		ZoomOutAction zoomOutAction = new ZoomOutAction(manager);

		this.getActionRegistry().registerAction(zoomInAction);
		this.getActionRegistry().registerAction(zoomOutAction);

		this.addKeyHandler(zoomInAction);
		this.addKeyHandler(zoomOutAction);		
		this.actionBarContributor = new BODiagramActionBarContributor();
		this.actionBarContributor.init(this.getEditorSite().getActionBars(),
				this.getSite().getPage());
		IFigure gridLayer = rootEditPart.getLayer(LayerConstants.GRID_LAYER);
		gridLayer.setForegroundColor(ColorResource.GRID_COLOR);
		
		
	}
	
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IEditorPart editorPart = getSite().getPage().getActiveEditor();
		if (editorPart instanceof BODesignerMultiPageEditor) {
			BODesignerMultiPageEditor multiPageEditorPart = (BODesignerMultiPageEditor) editorPart;
			if (this.equals(multiPageEditorPart.getActiveEditor())) {
				updateActions(this.getSelectionActions());
			}
		} else {
			super.selectionChanged(part, selection);
		}
	}

	@Override
	public void doSave(IProgressMonitor monitor) {

		IFile file = ((IFileEditorInput) this.getEditorInput()).getFile();
		IProject proejct = file.getProject();

		//CreateDiagramHtml(diagram, proejct);
		DiagramXmlWriter writer=new DiagramXmlWriter();
		String xml=writer.createXml(diagram);
		//XStream xStream = new XStream(new DomDriver());
		//xStream.alias("diagram", BOModelDiagram.class);
		//String xml = xStream.toXML(diagram);
		try {
			ByteArrayInputStream source = new ByteArrayInputStream(
					xml.getBytes());
			if (!file.exists()) {
				file.create(source, true, monitor);

			} else {
				file.setContents(source, true, false, monitor);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		getCommandStack().markSaveLocation();
		savePreviouslyNeeded = false;
		try {
			proejct.refreshLocal(1, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class) {
			return ((ScalableFreeformRootEditPart) getGraphicalViewer()
					.getRootEditPart()).getZoomManager();
		} 

		return super.getAdapter(type);
	}
	private void addKeyHandler(IAction action) {
		IHandlerService service = (IHandlerService) this.getSite().getService(
				IHandlerService.class);
		service.activateHandler(action.getActionDefinitionId(),
				new ActionHandler(action));
	}
	protected KeyHandler getCommonKeyHandler()
	{
		if (sharedKeyHandler == null) {
			sharedKeyHandler = new KeyHandler();
			sharedKeyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry()
					.getAction(ActionFactory.DELETE.getId()));
		}
		return sharedKeyHandler;
	}

	public void commandStackChanged(EventObject event)
	{
		if (isDirty()) {
			if (!this.savePreviouslyNeeded) {
				this.savePreviouslyNeeded = true;
				firePropertyChange(IEditorPart.PROP_DIRTY);
			}
		} else {
			savePreviouslyNeeded = false;
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
		super.commandStackChanged(event);
	}

}
