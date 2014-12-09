package com.wt.studio.plugin.note.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.json.JSONArray;
import org.json.JSONException;

import com.wt.studio.plugin.note.Activator;
import com.wt.studio.plugin.note.editors.NoteEditorInput;
import com.wt.studio.plugin.note.utils.GetResult;
import com.wt.studio.plugin.note.utils.Json2Map;

/**
 * 笔记本视图
 * 
 * @author gl
 * 
 */
public class NoteView extends ViewPart {

	public static final String ID = "com.hirisun.ide.plugin.note.editors.NoteEditor";

	private TreeViewer viewer;
	private TableViewer table;
	private DrillDownAdapter drillDownAdapter;
	private Action refreshAction;
	private Action action2;
	private Action doubleClickAction;
	private Map topics;
	
	public class Article
	{
		private String title;
		private String author;
		private String creatDate;
		private String modifyDate;
		private String cid;
		public Article(String title,String creatDate,String modifyDate,String author,String cid){
			this.title=title;
			this.author=author;
			this.creatDate=creatDate;
			this.modifyDate=modifyDate;
			this.cid=cid;
		}
		public String getTitle(){
			return this.title;
		}
		public String getAuthor(){
			return this.author;
		}
		public String getCreatdate(){
			return this.creatDate;
		}
		public String getModifyDate(){
			return this.modifyDate;
		}
		public String getCid(){
			return this.cid;
		}

	}
	class TreeObject implements IAdaptable {
		private String name;
		private TreeParent parent;

		public TreeObject(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setParent(TreeParent parent) {
			this.parent = parent;
		}

		public TreeParent getParent() {
			return parent;
		}

		public String toString() {
			return getName();
		}

		public Object getAdapter(Class key) {
			return null;
		}
	}

	class TreeParent extends TreeObject {
		private ArrayList children;

		public TreeParent(String name) {
			super(name);
			children = new ArrayList();
		}

		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}

		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren() {
			return (TreeObject[]) children.toArray(new TreeObject[children
					.size()]);
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		private TreeParent invisibleRoot;

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent.equals(getViewSite())) {
				if (invisibleRoot == null)
					try {
						initialize();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}

		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent) parent).hasChildren();
			return false;
		}

		private void initialize() throws ParseException, JSONException {
			String url="http://192.168.20.131:8080/opencms/rest/xcms/getTopicShowJson?userid=cmsadmin";
			topics=new HashMap();
			try {
				String json=GetResult.httpGet(url);
				JSONArray ja=Json2Map.ParseJson(json);
				invisibleRoot = new TreeParent("");
				for(int i=0;i<ja.length();i++)
				{
					TreeParent book = new TreeParent(ja.getJSONObject(i).getString("title"));
					invisibleRoot.addChild(book);
					topics.put(ja.getJSONObject(i).getString("title"), ja.getJSONObject(i).getString("topicid"));
					String json1=GetResult.httpGet(url+"&pId="+ja.getJSONObject(i).getString("topicid"));
					JSONArray ja1=Json2Map.ParseJson(json1);
					if(ja1.length()>0)
					{
						for(int k=0;k<ja1.length();k++)
						{
						TreeParent book1 = new TreeParent(ja1.getJSONObject(k).getString("title"));
						book.addChild(book1);
						topics.put(ja1.getJSONObject(k).getString("title"), ja1.getJSONObject(k).getString("topicid"));
						String json2=GetResult.httpGet(url+"&pId="+ja1.getJSONObject(k).getString("topicid"));
						JSONArray ja2=Json2Map.ParseJson(json2);
						if(ja2.length()>0)
						{
							for(int j=0;j<ja2.length();j++)
							{
								TreeParent book2 = new TreeParent(ja2.getJSONObject(j).getString("title"));
							    book1.addChild(book2);
							    topics.put(ja2.getJSONObject(j).getString("title"), ja2.getJSONObject(j).getString("topicid"));
							}
						}
						}
					}
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}

		public Image getImage(Object obj) {
			if (obj instanceof TreeParent)
				return Activator
			    		.getImageDescriptor("/icons/logo.png").createImage();
			return Activator
		    		.getImageDescriptor("/icons/notelogo.png").createImage();
		}
	}

	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public NoteView() {
	}
	
	
	
	
	class TableViewContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose()
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void inputChanged(Viewer arg0, Object arg1, Object arg2)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object[] getElements(Object inputElement)
		{
			// TODO Auto-generated method stub
			TreeParent element=(TreeParent)inputElement;
			String name=element.getName();
			JSONArray articles;
			List listArticles;
				
			try {
						articles = getAticles(name);
						listArticles = objectArticles(articles);
						if(listArticles!=null)
						     return ((List)listArticles).toArray();
				} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
					
					
			
			return null;
			
			
			//return (Array)inputElement
		}
	}
	
	class TableViewLabelProvider implements ITableLabelProvider{

		@Override
		public void addListener(ILabelProviderListener arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dispose()
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isLabelProperty(Object arg0, String arg1)
		{
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public Image getColumnImage(Object element, int index)
		{
			if(element!=null)
			{
			// TODO Auto-generated method stub
			if(index==0)
			{
				return Activator
			    		.getImageDescriptor("/icons/save.png").createImage();
			}
			return null;
			}
			return null;
		}

		@Override
		public String getColumnText(Object element, int index)
		{
			// TODO Auto-generated method stub
			if(element!=null)
			{
			Article article=(Article)element;
			if(index==1)
				return article.getTitle();
			if(index==2)
				return article.getCreatdate();
			if(index==3)
				return article.getModifyDate();
			if(index==4)
				return article.getAuthor();
			return "";
			}
			return null;
		}
		
	}
	
	
	
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		GridLayout griddata=new GridLayout();
		griddata.numColumns=1;
		parent.setLayout(griddata);
		GridData gridDataForm = new GridData();
		gridDataForm.grabExcessHorizontalSpace = true; // 抢占垂直方向额外空间
		gridDataForm.horizontalAlignment = GridData.FILL; // 水平方向充满
		gridDataForm.grabExcessVerticalSpace = true; // 抢占水平方向额外空间
		gridDataForm.verticalAlignment = GridData.FILL; // 垂直方向充满
		gridDataForm.horizontalSpan = 1;

		Composite container = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 5;
		container.setLayout(gl);
		GridData gridata=new GridData();
		gridata.horizontalSpan=3;
		gridata.verticalSpan=5;
		
		
		Composite picture=new Composite(container,SWT.BORDER);
		picture.setLayoutData(gridata);
		picture.setBackgroundImage(Activator
		    		.getImageDescriptor("/icons/touxiang.jpg").createImage());
		Label name=new Label(container,SWT.NONE);
		name.setText("用户名：");
		Label name1=new Label(container,SWT.NONE);
		name1.setText("longqiang");
		
		Label id=new Label(container,SWT.NONE);
		id.setText("邮箱：");
		Link link=new Link(container,SWT.NONE);
		link.setText("<A>542521030@qq.com</A>");
		
		Label email=new Label(container,SWT.NONE);
		email.setText("分享：");
		Label email1=new Label(container,SWT.NONE);
		email1.setText("100");
		
		Label article=new Label(container,SWT.NONE);
		article.setText("金币：");
		Label article1=new Label(container,SWT.NONE);
		article1.setText("500");
		
		
		
		SashForm form=new SashForm(parent,SWT.VERTICAL);
		form.setLayoutData(gridDataForm);
		
		Composite containerV = new Composite(form, SWT.NONE);
		FillLayout fill = new FillLayout();
		fill.type = SWT.VERTICAL;
		containerV.setLayout(fill);
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true; // 抢占垂直方向额外空间
		gridData.horizontalAlignment = GridData.FILL; // 水平方向充满
		gridData.grabExcessVerticalSpace = true; // 抢占水平方向额外空间
		gridData.verticalAlignment = GridData.FILL; // 垂直方向充满
		gridData.horizontalSpan = 1;
		containerV.setLayoutData(gridData);

		viewer = new TreeViewer(containerV,SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		//viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		
		
		Composite list=new Composite(form,SWT.NONE);
		list.setLayout(fill);
		list.setLayoutData(gridData);
		table=new TableViewer(list,SWT.MULTI|SWT.H_SCROLL
				|SWT.V_SCROLL|SWT.FULL_SELECTION);
		TableColumn column1=new TableColumn(table.getTable(),SWT.NONE);
		column1.setText("状态");
		TableColumn column2=new TableColumn(table.getTable(),SWT.NONE);
		column2.setText("分享文章");
		TableColumn column3=new TableColumn(table.getTable(),SWT.NONE);
		column3.setText("发布时间");
		TableColumn column4=new TableColumn(table.getTable(),SWT.NONE);
		column4.setText("修改时间");
		TableColumn column5=new TableColumn(table.getTable(),SWT.NONE);
		column5.setText("作者");
		
		column1.setWidth(40);
		column2.setWidth(250);
		column3.setWidth(60);
		column4.setWidth(60);
		column5.setWidth(60);
		
		table.getTable().setHeaderVisible(true);
		table.getTable().setLinesVisible(true);
		table.setContentProvider(new TableViewContentProvider());
		table.setLabelProvider(new  TableViewLabelProvider());
		table.addDoubleClickListener(new IDoubleClickListener(){

			@Override
			public void doubleClick(DoubleClickEvent event)
			{
				StructuredSelection select=(StructuredSelection)event.getSelection();
				Article a=(Article)select.getFirstElement();
				IWorkbenchPage page = getPage();
				ISelection selection = viewer.getSelection();
				NoteEditorInput note = new NoteEditorInput(a.getTitle(),a.getCid());
				try {
					page.openEditor(note, NoteView.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				
			}
			
		});
	}

	private List objectArticles(JSONArray articles) throws JSONException
	{
		// TODO Auto-generated method stub
		List ListArticles=new ArrayList();
		for(int i=0;i<articles.length();i++)
		{
			Article art=new Article(articles.getJSONObject(i).getString("title"),
					articles.getJSONObject(i).getString("createdate"),
					articles.getJSONObject(i).getString("modifydate"),
					articles.getJSONObject(i).getString("author"),
					articles.getJSONObject(i).getString("cid"));
			ListArticles.add(art);
			
		}
		return ListArticles;
	}

	private JSONArray getAticles(String name) throws IllegalStateException, IOException, ParseException, JSONException
	{
		String id=topics.get(name).toString();
		String url="http://192.168.20.131:8080/opencms/rest/xcms/getTopicContents?topicid="+id+"&child=true";
		String aticlesJson=GetResult.httpGet(url);
		JSONArray articles=Json2Map.ParseJson1(aticlesJson);
	    return articles;	
	}


	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				NoteView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(action2);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(refreshAction);
		manager.add(action2);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		refreshAction = new Action() {
			public void run() {
				showMessage("已经同步到最新文章");
			}
		};

		refreshAction.setText("Action 1");
		refreshAction.setToolTipText("同步");
		refreshAction.setImageDescriptor(Activator
	    		.getImageDescriptor("/icons/refresh.png"));

		action2 = new Action() {
			public void run() {
			   //showMessage("新建文章");
				/*public class Input extends ApplicationWindow{

					public Input(Shell parentShell)
					{
						super(parentShell);
						// TODO Auto-generated constructor stub
					}
					
				}*/
				InputDialog inputDialog=new InputDialog(Display.getCurrent().getActiveShell(),"输入文章标题","请输入文章标题","新建文章",null );
				int r=inputDialog.open();
				if(r==Window.OK)
				{	
				IWorkbenchPage page = getPage();
				ISelection selection = viewer.getSelection();
				NoteEditorInput note = new NoteEditorInput(inputDialog.getValue(),"aedsjhkdsljfkdlsf");
				try {
					page.openEditor(note, NoteView.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
				}
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("新建");
		action2.setImageDescriptor(Activator
	    		.getImageDescriptor("/icons/notelogo.png"));

		doubleClickAction = new Action() {
			public void run() {/*
				IWorkbenchPage page = getPage();
				ISelection selection = viewer.getSelection();
				NoteEditorInput note = new NoteEditorInput("");
				try {
					page.openEditor(note, NoteView.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			*/
				table.setInput(getTreeSelection());
				}

			private Object getTreeSelection()
			{
				// TODO Auto-generated method stub
				IStructuredSelection selection=(IStructuredSelection)viewer.getSelection();
				if(selection.size()!=1)
					return null;
				return selection.getFirstElement();
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
				
				
			}
		});
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),"NoteView", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus(){
		viewer.getControl().setFocus();
	}

	public IWorkbenchPage getPage() {
		return this.getViewSite().getWorkbenchWindow().getActivePage();
	}

}