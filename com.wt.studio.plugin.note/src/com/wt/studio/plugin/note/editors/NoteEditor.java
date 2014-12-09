package com.wt.studio.plugin.note.editors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichText;
import org.eclipse.epf.richtext.RichTextToolBar;
import org.eclipse.epf.richtext.actions.AddImageAction;
import org.eclipse.epf.richtext.actions.AddLinkAction;
import org.eclipse.epf.richtext.actions.AddOrderedListAction;
import org.eclipse.epf.richtext.actions.AddTableAction;
import org.eclipse.epf.richtext.actions.AddUnorderedListAction;
import org.eclipse.epf.richtext.actions.BoldAction;
import org.eclipse.epf.richtext.actions.ClearContentAction;
import org.eclipse.epf.richtext.actions.CopyAction;
import org.eclipse.epf.richtext.actions.CutAction;
import org.eclipse.epf.richtext.actions.FindReplaceAction;
import org.eclipse.epf.richtext.actions.FontNameAction;
import org.eclipse.epf.richtext.actions.FontSizeAction;
import org.eclipse.epf.richtext.actions.FontStyleAction;
import org.eclipse.epf.richtext.actions.IndentAction;
import org.eclipse.epf.richtext.actions.ItalicAction;
import org.eclipse.epf.richtext.actions.JustifyCenterAction;
import org.eclipse.epf.richtext.actions.JustifyLeftAction;
import org.eclipse.epf.richtext.actions.JustifyRightAction;
import org.eclipse.epf.richtext.actions.OutdentAction;
import org.eclipse.epf.richtext.actions.PasteAction;
import org.eclipse.epf.richtext.actions.SubscriptAction;
import org.eclipse.epf.richtext.actions.SuperscriptAction;
import org.eclipse.epf.richtext.actions.TidyActionGroup;
import org.eclipse.epf.richtext.actions.UnderlineAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WorkbenchBrowserSupport;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

public class NoteEditor extends EditorPart {
	private static String ID = "com.hirisun.ide.plugin.note.editors.NoteEditor";
	private RichText richtext;
	private RichTextToolBar toolBar;
	private FileEditorInput fileInput;
	private NoteEditorInput noteInput;

	private boolean bDirty = false;
	private boolean bSaveAS = false;

	public NoteEditor() {
		super();
	}

	public void dispose() {
		super.dispose();
	}

	/**
	 * 保存文件
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			monitor.beginTask("保存文件...", 100);

			OutputStream output = null;
			File f = null;
			try {
				monitor.worked(30);
				String ot = richtext.getText();
				if (fileInput != null) {
					f = new File(fileInput.getFile().getLocation().toString());
					output = new FileOutputStream(f);
					IOUtils.write(ot, output, "UTF-8");
					bDirty = false;
					this.firePropertyChange(PROP_DIRTY);
				}
				monitor.subTask("已完成80%");// 显示任务状态
				monitor.worked(100);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				f = null;
				IOUtils.closeQuietly(output);
			}
			monitor.done();
			if (monitor.isCanceled()) {
				throw new InterruptedException("取消保存");
			}

		} catch (InterruptedException e) {
		}
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(IEditorSite ieditorsite, IEditorInput ieditorinput)
			throws PartInitException {
		this.setSite(ieditorsite);
		this.setInput(ieditorinput);
		if (ieditorinput instanceof FileEditorInput) {
			fileInput = ((org.eclipse.ui.part.FileEditorInput) ieditorinput);
			this.setPartName(fileInput.getFile().getName());
		} else if (ieditorinput instanceof NoteEditorInput) {
			this.setPartName(ieditorinput.getName());
		} else {
			this.setPartName("New Node1");
		}
	}

	@Override
	public boolean isDirty() {
		return bDirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return bSaveAS;
	}

	@Override
	public void createPartControl(Composite composite) {
		GridLayout glP = new GridLayout();
		glP.numColumns = 1;
		GridData gridDataP = new GridData(GridData.BEGINNING);
		gridDataP.horizontalIndent = 0;
		gridDataP.verticalIndent = 0;
		composite.setLayoutData(gridDataP);
		composite.setLayout(glP);

		Composite container = new Composite(composite, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		container.setLayout(gl);

		Composite containerV = new Composite(composite, SWT.NONE);
		GridLayout glV = new GridLayout();
		glV.numColumns = 1;
		containerV.setLayout(glV);
		GridData gridData = new GridData(GridData.BEGINNING);
		gridData.grabExcessHorizontalSpace = true; // 抢占垂直方向额外空间
		gridData.horizontalAlignment = GridData.FILL; // 水平方向充满
		gridData.grabExcessVerticalSpace = true; // 抢占水平方向额外空间
		gridData.verticalAlignment = GridData.FILL; // 垂直方向充满
		gridData.horizontalSpan = 1;
		containerV.setLayoutData(gridData);

		richtext = new RichText(containerV, SWT.BORDER | SWT.V_SCROLL
				| SWT.FILL);
		toolBar = new RichTextToolBar(container, SWT.TOP, richtext);
		setRichTextToolBar();
		getInitRichTextData();
		richtext.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				setbDirty(true);
				firePropertyChange(PROP_DIRTY);
			}
		});

//		int style = IWorkbenchBrowserSupport.AS_EDITOR
//				| IWorkbenchBrowserSupport.LOCATION_BAR
//				| IWorkbenchBrowserSupport.STATUS;
//		IWebBrowser browser;
//		try {
//			browser = WorkbenchBrowserSupport.getInstance()
//					.createBrowser(style, "HBrowser", "Browser","HBrowser Tooltip");
//			browser.openURL(new URL("http://www.whatbrowser.org"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public void setbDirty(boolean bDirty) {
		this.bDirty = bDirty;
	}

	public void setbSaveAS(boolean bSaveAS) {
		this.bSaveAS = bSaveAS;
	}

	/**
	 * 初始化数据，可通过URL等方式取数据
	 */
	private void getInitRichTextData() {
		if (this.getEditorInput() instanceof FileEditorInput) {
			richtext.setInitialText(getInitRichTextDataByFile());
		} else if (this.getEditorInput() instanceof NoteEditorInput) {
			    richtext.setInitialText(getInitRichTextDataByURL("http://192.168.20.131:8080/opencms/rest/xcms/getTopicContentHtml?cid="+((NoteEditorInput) this.getEditorInput()).getCid()));
		}
	}

	/**
	 * 读取后缀.hnont文件内容
	 * 
	 * @return
	 */
	private String getInitRichTextDataByFile() {
		String result = "";
		if (fileInput != null) {
			File f = new File(fileInput.getFile().getLocation().toString());
			InputStream input = null;
			try {
				input = new FileInputStream(f);
				return IOUtils.toString(input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(input);
				f = null;
			}
		}
		return result;
	}

	/**
	 * 读取后缀URL文件内容
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private String getInitRichTextDataByURL(String url) {
		String result = "";
		try {
			result = IOUtils.toString(Request.Get(url).execute()
					.returnContent().asStream());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return result;
	}

	private void setRichTextToolBar() {
		toolBar.addAction(new JustifyLeftAction(richtext));
		toolBar.addAction(new JustifyRightAction(richtext));
		toolBar.addAction(new JustifyCenterAction(richtext));
		toolBar.addSeparator();
		toolBar.addAction(new CutAction(richtext));
		toolBar.addAction(new CopyAction(richtext));
		toolBar.addAction(new PasteAction(richtext));
		toolBar.addSeparator();
		toolBar.addAction(new ClearContentAction(richtext));
		toolBar.addSeparator();
		toolBar.addAction(new BoldAction(richtext));
		toolBar.addAction(new ItalicAction(richtext));
		toolBar.addAction(new UnderlineAction(richtext));
		toolBar.addSeparator();
		toolBar.addAction(new SubscriptAction(richtext));
		toolBar.addAction(new SuperscriptAction(richtext));
		toolBar.addSeparator();
		toolBar.addAction(new TidyActionGroup(richtext));
		toolBar.addSeparator();
		toolBar.addAction(new AddOrderedListAction(richtext));
		toolBar.addAction(new AddUnorderedListAction(richtext));
		toolBar.addSeparator();
		toolBar.addAction(new OutdentAction(richtext));
		toolBar.addAction(new IndentAction(richtext));
		toolBar.addSeparator();
		toolBar.addAction(new FindReplaceAction(richtext) {
			@Override
			public void execute(IRichText richText) {
				richText.getFindReplaceAction().execute(richText);
			}
		});
		toolBar.addSeparator();
		toolBar.addAction(new AddLinkAction(richtext));
		toolBar.addAction(new AddImageAction(richtext));
		toolBar.addAction(new AddTableAction(richtext));
		toolBar.addSeparator();
		toolBar.addAction(new FontStyleAction(richtext));
		toolBar.addAction(new FontNameAction(richtext));
		toolBar.addAction(new FontSizeAction(richtext));
	}

	@Override
	public void setFocus() {
		richtext.setFocus();
	}

}
