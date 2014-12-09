package com.wt.studio.plugin.pagedesigner.gef;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.properties.IPropertySheetPage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wt.studio.plugin.modeldesigner.context.ImportContextMenuProvider;
import com.wt.studio.plugin.modeldesigner.context.ImportTableFromDatabaseAction;
import com.wt.studio.plugin.modeldesigner.editor.BODesignerMultiPageEditor;
import com.wt.studio.plugin.pagedesigner.gef.dnd.DiagramTemplateTransferDropTargetListener;
import com.wt.studio.plugin.pagedesigner.gef.model.BlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ButtonModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.Element;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;
import com.wt.studio.plugin.pagedesigner.gef.model.HorizonBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.InputModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;
import com.wt.studio.plugin.pagedesigner.property.UnsortPropertySheetPage;
import com.wt.studio.plugin.pagedesigner.wizard.templates.BOServiceImplTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.BOServiceInterfaceTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.BOServiceTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.BOTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.BOTemplate1;
import com.wt.studio.plugin.pagedesigner.wizard.templates.DaoImplTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.DaoTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.FuncHTMLTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.MOTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.SearchConditionTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.SearchConditionsTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.ServiceImplTemplate;
import com.wt.studio.plugin.pagedesigner.wizard.templates.ServiceInterfaceTemplate;

public class PageDesignerEditor extends GraphicalEditorWithPalette {
	private Diagram diagram;
	private boolean savePreviouslyNeeded;
	private KeyHandler sharedKeyHandler;
	private PartFactory part;
	private int PaletteRootFlag;

	public int getPaletteRootFlag()
	{
		return PaletteRootFlag;
	}

	public void setPaletteRootFlag(int paletteRootFlag)
	{
		PaletteRootFlag = paletteRootFlag;
	}

	public PageDesignerEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	public PageDesignerEditor(PartFactory part,Diagram diagram,int flag) {
		this.PaletteRootFlag=flag;
		setEditDomain(new DefaultEditDomain(this));
		this.diagram=diagram;
		this.part=part;
	}
	@Override
	protected void initializeGraphicalViewer() {

		super.initializePaletteViewer();

		super.getGraphicalViewer().setContents(this.diagram);
		super.getGraphicalViewer().addDropTargetListener(
				new DiagramTemplateTransferDropTargetListener(super
						.getGraphicalViewer()));
		super.getGraphicalViewer().setProperty(
				SnapToGrid.PROPERTY_GRID_ENABLED, true);
		super.getGraphicalViewer().setProperty(
				SnapToGrid.PROPERTY_GRID_VISIBLE, true);
		super.getGraphicalViewer().setProperty(
				SnapToGeometry.PROPERTY_SNAP_ENABLED, true);
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer()
				.setRootEditPart(new ScalableFreeformRootEditPart());
		getGraphicalViewer().setEditPartFactory(this.part);
		getGraphicalViewer().setKeyHandler(
				new GraphicalViewerKeyHandler(getGraphicalViewer())
						.setParent(getCommonKeyHandler()));
		// configure the context menu provider
	}

	@Override
	protected void initializePaletteViewer() {
		super.initializePaletteViewer();
		super.getPaletteViewer()
				.addDragSourceListener(
						new TemplateTransferDragSourceListener(super
								.getPaletteViewer()));
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IPropertySheetPage.class) {
			return new UnsortPropertySheetPage(getCommandStack(),
					getActionRegistry().getAction(ActionFactory.UNDO.getId()),
					getActionRegistry().getAction(ActionFactory.REDO.getId()));
		}
		return super.getAdapter(adapter);
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		if(this.PaletteRootFlag==0)
		    return PaletteFactory.createPalette();
		else
			return  PaletteFactory.createFunctionPalette();
	
	}

	@Override
	protected void setInput(IEditorInput input) {
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

	
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IEditorPart editorPart = getSite().getPage().getActiveEditor();
		if (editorPart instanceof PageDesignerMutiPageEditor) {
			PageDesignerMutiPageEditor multiPageEditorPart = (PageDesignerMutiPageEditor) editorPart;
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
		IProject project = file.getProject();
		try {
			createFileSystem(project);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createDiagramHtml(diagram, project);
		createBOModel(diagram.getFunctionTableModels(),project);
		createMOModel(diagram.getFunctionTableModels(),project);
		createSearchCondition(project);
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("diagram", Diagram.class);
		String xml = xStream.toXML(diagram);
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
			project.refreshLocal(1, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	
	private void createFileSystem(IProject project) throws CoreException
	{
		// TODO Auto-generated method stub
		IContainer container = (IContainer) project;
		String urlStr="src/main/java/com/hirisun/"+project.getName();
		String urls[]=urlStr.split("/");
		String url=urls[0];
		int i=0;
		while (i<urls.length)
		{
			i++;
			if(!(container.getFolder(new Path("/"+url)).exists()))
			{
				container.getFolder(new Path("/"+url)).create(true, true, null);
			}
			if(i<urls.length)
			{
			url=url+"/"+urls[i];
			}
		}
		
	}

	private void createSearchCondition(IProject project)
	{
		String projectName=project.getName();
		// TODO Auto-generated method stub
		SearchConditionTemplate searchTemplate = new SearchConditionTemplate();
		String searchContent=searchTemplate.generate(projectName);
		try {
			boolean result = false;
			try {
				InputStream contentStream = IOUtils.toInputStream(searchContent);
				final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/model/SearchCondition.java");
				if (file.exists()) {
					file.setContents(contentStream, true, true, null);
				} else {
					IFolder folder=project.getFolder("/src/main/java/com/hirisun/"+projectName+"/model");
					if(!folder.exists())
					{
						IContainer container = (IContainer) project;
						final IFolder styleFolder = container.getFolder(new Path("/src/main/java/com/hirisun/"+projectName+"/model"));
						styleFolder.create(true, true, null);
					}
					file.create(contentStream, true, null);
				}
				result = true;
			} catch (CoreException e) {
				result = false;
				e.printStackTrace();
			}
		} finally {

		}
		
		SearchConditionsTemplate searchsTemplate = new SearchConditionsTemplate();
		String searchsContent=searchsTemplate.generate(projectName);
		try {
			boolean result = false;
			try {
				InputStream contentStream = IOUtils.toInputStream(searchsContent);
				final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/model/SearchConditions.java");
				if (file.exists()) {
					file.setContents(contentStream, true, true, null);
				} else {
					IFolder folder=project.getFolder("/src/main/java/com/hirisun/"+projectName+"/model");
					if(!folder.exists())
					{
						IContainer container = (IContainer) project;
						final IFolder styleFolder = container.getFolder(new Path("/src/main/java/com/hirisun/"+projectName+"/model"));
						styleFolder.create(true, true, null);
					}
					file.create(contentStream, true, null);
				}
				result = true;
			} catch (CoreException e) {
				result = false;
				e.printStackTrace();
			}
		} finally {

		}
	}

	private void createMOModel(List<Element> functionTableModels, IProject project)
	{
		String projectName=project.getName();
		// TODO Auto-generated method stub
		for(Element table:functionTableModels)
		{
			List<Object>argument=new ArrayList<Object>();
			argument.add(table);
			argument.add(projectName);
			if(table instanceof MOFunctionTableModel)
			{
				MOTemplate moTemplate = new MOTemplate();
				String MoContent=moTemplate.generate(argument);
				try {
					boolean result = false;
					try {
						InputStream contentStream = IOUtils.toInputStream(MoContent);
						final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/model/"+((MOFunctionTableModel) table).getTitle()+".java");
						if (file.exists()) {
							file.setContents(contentStream, true, true, null);
						} else {
							IFolder folder=project.getFolder("/src/main/java/com/hirisun/"+projectName+"/model");
							if(!folder.exists())
							{
								IContainer container = (IContainer) project;
								final IFolder styleFolder = container.getFolder(new Path("/src/main/java/com/hirisun/"+projectName+"/model"));
								styleFolder.create(true, true, null);
							}
							file.create(contentStream, true, null);
						}
						result = true;
					} catch (CoreException e) {
						result = false;
						e.printStackTrace();
					}
				} finally {

				}
				DaoTemplate daoTemplate=new DaoTemplate();
				String daoContent=daoTemplate.generate(argument);
				try {
					boolean result = false;
					try {
						InputStream contentStream = IOUtils.toInputStream(daoContent);
						final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/dao/"+((MOFunctionTableModel) table).getTitle()+"Dao.java");
						if (file.exists()) {
							file.setContents(contentStream, true, true, null);
						} else {
							IFolder folder=project.getFolder("/src/main/java/com/hirisun/"+projectName+"/dao");
							if(!folder.exists())
							{
								IContainer container = (IContainer) project;
								final IFolder styleFolder = container.getFolder(new Path("/src/main/java/com/hirisun/"+projectName+"/dao"));
								styleFolder.create(true, true, null);
							}
							file.create(contentStream, true, null);
						}
						result = true;
					} catch (CoreException e) {
						result = false;
						e.printStackTrace();
					}
				} finally {

				}
				
				DaoImplTemplate daoImplTemplate=new DaoImplTemplate();
				String daoImplContent=daoImplTemplate.generate(argument);
				try {
					boolean result = false;
					try {
						InputStream contentStream = IOUtils.toInputStream(daoImplContent);
						final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/dao/impl/"+((MOFunctionTableModel) table).getTitle()+"DaoImpl.java");
						if (file.exists()) {
							file.setContents(contentStream, true, true, null);
						} else {
							IFolder folder=project.getFolder("/src/main/java/com/hirisun/"+projectName+"/dao/impl");
							if(!folder.exists())
							{
								IContainer container = (IContainer) project;
								final IFolder styleFolder = container.getFolder(new Path("/src/main/java/com/hirisun/"+projectName+"/dao/impl"));
								styleFolder.create(true, true, null);
							}
							file.create(contentStream, true, null);
						}
						result = true;
					} catch (CoreException e) {
						result = false;
						e.printStackTrace();
					}
				} finally {

				}
				
				
			}
		}
	}

	private void createBOModel(List<Element> functionTableModels, IProject project)
	{
		// TODO Auto-generated method stub
		String projectName=project.getName();
		for(Element table:functionTableModels)
		{
			List<Object>argument=new ArrayList<Object>();
			argument.add(table);
			argument.add(projectName);
			if(table instanceof MOFunctionTableModel)
			{
				BOTemplate1 boTemplate = new BOTemplate1();
				String BoContent=boTemplate.generate(argument);
				try {
					boolean result = false;
					try {
						InputStream contentStream = IOUtils.toInputStream(BoContent);
						final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/bo/"+((MOFunctionTableModel) table).getTitle()+"BO.java");
						if (file.exists()) {
							file.setContents(contentStream, true, true, null);
						} else {
							IFolder folder=project.getFolder("/src/main/java/com/hirisun/"+projectName+"/bo");
							if(!folder.exists())
							{
								IContainer container = (IContainer) project;
								final IFolder styleFolder = container.getFolder(new Path("/src/main/java/com/hirisun/"+projectName+"/bo"));
								styleFolder.create(true, true, null);
							}
							file.create(contentStream, true, null);
						}
						result = true;
					} catch (CoreException e) {
						result = false;
						e.printStackTrace();
					}
				} finally {

				}
				BOServiceInterfaceTemplate servInterfaceTemplate=new BOServiceInterfaceTemplate();
				String BoServInterfaceContent=servInterfaceTemplate.generate(argument);
				try {
					boolean result = false;
					try {
						InputStream contentStream = IOUtils.toInputStream(BoServInterfaceContent);
						final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/service/"+((MOFunctionTableModel) table).getTitle()+"BOService.java");
						if (file.exists()) {
							file.setContents(contentStream, true, true, null);
						} else {
							IFolder folder=project.getFolder("/src/main/java/com/hirisun/"+projectName+"/service");
							if(!folder.exists())
							{
								IContainer container = (IContainer) project;
								final IFolder styleFolder = container.getFolder(new Path("/src/main/java/com/hirisun/"+projectName+"/service"));
								styleFolder.create(true, true, null);
							}
							file.create(contentStream, true, null);
						}
						result = true;
					} catch (CoreException e) {
						result = false;
						e.printStackTrace();
					}
				} finally {

				}
				
				BOServiceImplTemplate boImplTemplate=new BOServiceImplTemplate();
				String boServContent=boImplTemplate.generate(argument);
				try {
					boolean result = false;
					try {
						InputStream contentStream = IOUtils.toInputStream(boServContent);
						final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/service/impl/"+((MOFunctionTableModel) table).getTitle()+"BOServiceImpl.java");
						if (file.exists()) {
							file.setContents(contentStream, true, true, null);
						} else {
							IFolder folder=project.getFolder("/src/main/java/com/hirisun/"+projectName+"/service/impl");
							if(!folder.exists())
							{
								IContainer container = (IContainer) project;
								final IFolder styleFolder = container.getFolder(new Path("/src/main/java/com/hirisun/"+projectName+"/service/impl"));
								styleFolder.create(true, true, null);
							}
							file.create(contentStream, true, null);
						}
						result = true;
					} catch (CoreException e) {
						result = false;
						e.printStackTrace();
					}
				} finally {

				}
				ServiceInterfaceTemplate InterfaceTemplate=new ServiceInterfaceTemplate();
				String servInterfaceContent=InterfaceTemplate.generate(argument);
				try {
					boolean result = false;
					try {
						InputStream contentStream = IOUtils.toInputStream(servInterfaceContent);
						final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/service/"+((MOFunctionTableModel) table).getTitle()+"Service.java");
						if (file.exists()) {
							file.setContents(contentStream, true, true, null);
						} else {
							IFolder folder=project.getFolder("/src/main/java/com/hirisun/"+projectName+"/service");
							if(!folder.exists())
							{
								IContainer container = (IContainer) project;
								final IFolder styleFolder = container.getFolder(new Path("/src/main/java/com/hirisun/"+projectName+"/service"));
								styleFolder.create(true, true, null);
							}
							file.create(contentStream, true, null);
						}
						result = true;
					} catch (CoreException e) {
						result = false;
						e.printStackTrace();
					}
				} finally {

				}
				ServiceImplTemplate implTemplate=new ServiceImplTemplate();
				String servContent=implTemplate.generate(argument);
				try {
					boolean result = false;
					try {
						InputStream contentStream = IOUtils.toInputStream(servContent);
						final IFile file = project.getFile("/src/main/java/com/hirisun/"+projectName+"/service/impl/"+((MOFunctionTableModel) table).getTitle()+"ServiceImpl.java");
						if (file.exists()) {
							file.setContents(contentStream, true, true, null);
						} else {
							file.create(contentStream, true, null);
						}
						result = true;
					} catch (CoreException e) {
						result = false;
						e.printStackTrace();
					}
				} finally {

				}
				
				
			}
		}
	}

	protected KeyHandler getCommonKeyHandler() {
		if (sharedKeyHandler == null) {
			sharedKeyHandler = new KeyHandler();
			sharedKeyHandler
					.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
							getActionRegistry().getAction(
									ActionFactory.DELETE.getId()));
		}
		return sharedKeyHandler;
	}

	public void commandStackChanged(EventObject event) {
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

	
	public void removeSelection() {
		this.getGraphicalViewer().deselectAll();
		
	}
	public GraphicalViewer getGraphicalViewer() {
		return super.getGraphicalViewer();
	}
	/**
	 * 创建FuncHTML 页面
	 * 
	 * @param diagram
	 */
	public void createDiagramHtml(Diagram diagram, IProject project) {
		FuncHTMLTemplate funcHTMLTemplate = new FuncHTMLTemplate();

		String funcCode = null;

		StringBuffer sb = new StringBuffer();

		for (ControlPageModel page : diagram.getPageModels()) {
			for (Element element : page.getAllElement()) {
				if(element instanceof BlockModel)
				{
					sb.append(createBlockModelHtml((BlockModel) element));
				}
				else if(element instanceof ControlModel)
				{
					sb.append(createControlModelHtml((ControlModel)element,1));
				}
			}
			//System.out.println(sb);
		}

		funcCode = StringUtils.replace(
				funcHTMLTemplate.generate(new Object[] {}), "$FUNC_BODY$",
				sb.toString());

		try {
			boolean result = false;
			try {
				InputStream contentStream = IOUtils.toInputStream(funcCode);
				final IFile file = project.getFile(this.getPartName().replaceAll(".hpage", "")+".html");
				if (file.exists()) {
					file.setContents(contentStream, true, true, null);
				} else {
					file.create(contentStream, true, null);
				}
				result = true;
			} catch (CoreException e) {
				result = false;
				e.printStackTrace();
			}
		} finally {

		}

	}

	public String createBlockModelHtml(BlockModel block) {
		StringBuffer sb = new StringBuffer();
		int childrenNum=0;
		if(block instanceof HorizonBlockModel)
		    childrenNum=block.getAllElement().size()-1;
		if(block instanceof VerticalBlockModel)
			childrenNum=1;
		sb.append("<div class=\"row\">");
		for (Element element : block.getAllElement()) {
			 if(element instanceof FormModel)
			{
				//System.out.println("进入Form");
				sb.append("<from class=\"\">");
				sb.append(createFormModelHtml((FormModel)element,childrenNum));
				sb.append("</from>");
			}
			else if(element instanceof VerticalBlockModel)
			{
				sb.append(createVerticalBlockModelHtml((VerticalBlockModel)element,childrenNum));
			}
		    else if (element instanceof BlockModel)
		    {
				sb.append(createBlockModelHtml((BlockModel) element));
		    }
			else if (element instanceof ControlModel)
				sb.append(createControlModelHtml((ControlModel) element,childrenNum));
		}
		return sb.append("</div>").toString();
	}
	public String createVerticalBlockModelHtml(VerticalBlockModel block,int childrenNum)
	{
		StringBuffer sb = new StringBuffer();
		if(childrenNum==0)
			childrenNum=1;
		sb.append("<div class=col-md-"+12/childrenNum+">");
		for (Element element : block.getAllElement()) {
			 if(element instanceof FormModel)
			{
				//System.out.println("进入Form");
				sb.append("<from class=\"\">");
				sb.append(createFormModelHtml((FormModel)element,1));
				sb.append("</from>");
			}
			else if(element instanceof VerticalBlockModel)
			{
				sb.append(createVerticalBlockModelHtml((VerticalBlockModel)element,1));
			}
		    else if (element instanceof BlockModel)
		    {
				sb.append(createBlockModelHtml((BlockModel) element));
		    }
			else if (element instanceof ControlModel)
				sb.append(createControlModelHtml((ControlModel) element,1));
		}
		sb.append("</div>");
		return sb.toString();
	}

	public String createControlModelHtml(ControlModel control,int childrenNum ) {
		if(childrenNum==0)
			childrenNum=1;
		if (control instanceof InputModel) {
			return "<div class=col-md-"+12/childrenNum+"> " +
					"<div class=\"form-group\"> " +
					"<label class=\"col-md-3 control-label\">"+((InputModel) control).getName() + "</label> " +
					"<div class=\"col-md-8\"> <input type=\"text\" class=\"form-control\" name=\"" + ((InputModel) control).getName() + 
					"\"/></div></div></div>";
		}
		if (control instanceof ButtonModel) {
			return "<button type=\"button\">"
					+ ((ButtonModel) control).getName() + "</button>";
		}
		return "";
	}
	
	public String createFormModelHtml(FormModel form,int childrenNum)
	{
		StringBuffer fsb = new StringBuffer();
		if(childrenNum==0)
			childrenNum=1;
		fsb.append("<div class=col-md-"+12/childrenNum+">");
		fsb.append("<div class=\"panel panel-default\">");
		fsb.append("<div class=\"panel-heading\">");
		fsb.append(form.getName());
		fsb.append("</div>");
		fsb.append("<div class=\"panel-body\">");
		for (Element element : form.getAllElement()) {
			if(element instanceof BlockModel)
			   fsb.append(createBlockModelHtml((BlockModel) element));
			else if(element instanceof ControlModel)
			   fsb.append(createControlModelHtml((ControlModel) element,1));
		}
		fsb.append("</div>");
		fsb.append("</div>");
		fsb.append("</div>");
		return fsb.toString();
	}
}
