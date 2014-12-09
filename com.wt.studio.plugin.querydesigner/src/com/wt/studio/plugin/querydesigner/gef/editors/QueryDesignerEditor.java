package com.wt.studio.plugin.querydesigner.gef.editors;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import com.wt.studio.plugin.querydesigner.gef.actions.MyDiagramContextMenuProvider;
import com.wt.studio.plugin.querydesigner.gef.actions.QueryHorizontalBlockModelAddAction;
import com.wt.studio.plugin.querydesigner.gef.dnd.ColumnModelTransferDropTargetListener;
import com.wt.studio.plugin.querydesigner.gef.dnd.DiagramTemplateTransferDropTargetListener;
import com.wt.studio.plugin.querydesigner.gef.editors.part.PartFactory;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.Diagram;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.GhostModel;
import com.wt.studio.plugin.querydesigner.gef.model.PageModel;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;
import com.wt.studio.plugin.querydesigner.gef.utils.PaletteFactory;
import com.wt.studio.plugin.querydesigner.io.reader.DiagramXmlReader;
import com.wt.studio.plugin.querydesigner.io.writer.DiagramXmlWriter;
import com.wt.studio.plugin.querydesigner.property.UnsortPropertySheetPage;
import com.wt.studio.plugin.querydesigner.utils.DataSourceUtils;

public class QueryDesignerEditor extends GraphicalEditorWithPalette
{
	private Diagram diagram;
	private boolean savePreviouslyNeeded;
	private KeyHandler sharedKeyHandler;
	private GhostModel ghost = new GhostModel();
	private Set<String> blockNames = new HashSet();
	private int blockNum;

	public QueryDesignerEditor()
	{
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected void initializeGraphicalViewer()
	{
		String result;
		try {
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			this.setPartName(file.getName());

			if (!file.isSynchronized(IResource.DEPTH_ONE)) {
				file.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
			}
			InputStream in = file.getContents();
			result = getStreamString(in);
			result = replacenbsp(result);
			if (!result.equals("")) {
				DiagramXmlReader reader = new DiagramXmlReader();
				Diagram newdia = reader.parseDiagram(result);
				this.diagram = newdia;
			} else {
				PageModel page = new PageModel();
				page.setRectangle(new Rectangle(10, 20, 400, 400));
				diagram = new Diagram();
				diagram.addBlockModel(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		super.initializePaletteViewer();

		super.getGraphicalViewer().setContents(this.diagram);
		super.getGraphicalViewer().addDropTargetListener(
				new DiagramTemplateTransferDropTargetListener(super.getGraphicalViewer()));
		super.getGraphicalViewer().addDropTargetListener(
				new ColumnModelTransferDropTargetListener(super.getGraphicalViewer()));
		super.getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, true);
		super.getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, true);
		super.getGraphicalViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, true);
	}

	private String replacenbsp(String result)
	{
		// TODO Auto-generated method stub
		if (!result.contains("<![CDATA[")) {
			result = result.replaceAll("<description>", "<description><![CDATA[");
			result = result.replaceAll("</description>", "]]></description>");
		}

		return result;
	}

	@Override
	protected void configureGraphicalViewer()
	{
		super.configureGraphicalViewer();
		getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart());
		getGraphicalViewer().setEditPartFactory(new PartFactory());
		getGraphicalViewer().setKeyHandler(
				new GraphicalViewerKeyHandler(getGraphicalViewer())
						.setParent(getCommonKeyHandler()));
		// configure the context menu provider
		ContextMenuProvider cmProvider = new MyDiagramContextMenuProvider(getGraphicalViewer(),
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
		IAction[] actions = new IAction[] { new QueryHorizontalBlockModelAddAction(this) // 编辑属性
		};
		for (IAction a : actions) {
			ar.registerAction(a);
			getSelectionActions().add(a.getId());
		}
	}

	@Override
	protected void initializePaletteViewer()
	{
		super.initializePaletteViewer();
		super.getPaletteViewer().addDragSourceListener(
				new TemplateTransferDragSourceListener(super.getPaletteViewer()));
	}

	@Override
	public Object getAdapter(Class adapter)
	{
		if (adapter == IPropertySheetPage.class) {
			return new UnsortPropertySheetPage(getCommandStack(), getActionRegistry().getAction(
					ActionFactory.UNDO.getId()), getActionRegistry().getAction(
					ActionFactory.REDO.getId()));
		}
		return super.getAdapter(adapter);
	}

	@Override
	protected PaletteRoot getPaletteRoot()
	{
		return PaletteFactory.createPalette();
	}

	@Override
	protected void setInput(IEditorInput input)
	{
		super.setInput(input);
		IFile file = ((IFileEditorInput) input).getFile();
		setPartName(file.getName());

		try {
			InputStream stream = file.getContents();
			if (stream.read() == -1) {
				stream.close();
			} else {
				stream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	// @Override
	public void doSaveDataBase(IProgressMonitor monitor)
	{
		try {
			if (diagram.getId().equals("")) {
				MessageDialog.openWarning(Display.getCurrent().getActiveShell(), "警告", "功能ID为必填项");
				return;
			}
			boolean exists = DataSourceUtils.existsFunc(diagram.getId());
			if (exists) {
				if (MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "替换？", "["
						+ diagram.getId() + "]已存在，是否确认替换？")) {
					boolean result = DataSourceUtils.saveFunc(diagram, exists);
					if (result) {
						MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
								"success", "恭喜您，保存成功。");
					} else {
						MessageDialog.openError(Display.getCurrent().getActiveShell(), "Failed",
								"对不起，保存失败。");
					}
					getCommandStack().markSaveLocation();
				}
			} else {
				boolean result = DataSourceUtils.saveFunc(diagram, exists);
				if (result) {
					MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "success",
							"恭喜您，保存成功。");
				} else {
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Failed",
							"对不起，保存失败。");
				}
				getCommandStack().markSaveLocation();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doSave(IProgressMonitor monitor)
	{
		blockNum = 0;
		blockNames.clear();
		List<Element> children = diagram.getBlockModels().get(0).getElements();
		for (Element child : children) {
			if (child instanceof TableModel) {
				blockNames.add(((TableModel) child).getSqlName());
				blockNum++;
			} else if (child instanceof ChartBlockModel) {
				blockNames.add(((ChartBlockModel) child).getSqlSet().getSqls()
						.get(((ChartBlockModel) child).getSqlSet().getSqls().size() - 1)
						.getSqlName());
				blockNum++;
			}
			if (child instanceof BlockModel) {
				getBlockName((BlockModel) child);
			}
		}
		List<String> nullStr = new ArrayList<String>();
		for (String str : blockNames) {
			if (str == null || "null".equals(str))
				nullStr.add(str);
		}
		blockNames.removeAll(nullStr);
		if (blockNames.size() < blockNum) {
			MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "warning",
					"列表或者图表名称重复！");
		} else {
			doSaveDataBase(monitor);
		}
		String xml = DiagramXmlWriter.createXml(diagram);
		IFile file = ((IFileEditorInput) this.getEditorInput()).getFile();
		try {
			ByteArrayInputStream source = new ByteArrayInputStream(xml.getBytes());
			if (!file.exists()) {
				file.create(source, true, monitor);

			} else {
				file.setContents(source, true, false, monitor);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		getCommandStack().markSaveLocation();
		// savePreviouslyNeeded = false;
	}

	private void getBlockName(BlockModel block)
	{
		// TODO Auto-generated method stub
		List<Element> children = block.getElements();
		for (Element child : children) {
			if (child instanceof TableModel) {
				blockNames.add(((TableModel) child).getBlockName());
				blockNum++;
			} else if (child instanceof ChartBlockModel) {
				blockNames.add(((ChartBlockModel) child).getBlockName());
				blockNum++;
			}
			if (child instanceof BlockModel) {
				getBlockName((BlockModel) child);
			}
		}
	}

	private void daSaveFile(IProgressMonitor monitor)
	{
		System.out.println(DiagramXmlWriter.createXml(diagram));

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
