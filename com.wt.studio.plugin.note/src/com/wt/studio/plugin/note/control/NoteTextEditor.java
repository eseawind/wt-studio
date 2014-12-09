package com.wt.studio.plugin.note.control;

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.IRichTextToolBar;
import org.eclipse.epf.richtext.RichTextEditor;
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
import org.eclipse.epf.richtext.actions.OutdentAction;
import org.eclipse.epf.richtext.actions.PasteAction;
import org.eclipse.epf.richtext.actions.SubscriptAction;
import org.eclipse.epf.richtext.actions.SuperscriptAction;
import org.eclipse.epf.richtext.actions.TidyActionGroup;
import org.eclipse.epf.richtext.actions.UnderlineAction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorSite;

class NoteTextEditor extends RichTextEditor {

	public NoteTextEditor(Composite parent, int style, IEditorSite editorSite,
			String basePath) {
		super(parent, style, editorSite, basePath);
	}

	public NoteTextEditor(Composite parent, int style, IEditorSite editorSite) {
		super(parent, style, editorSite);
	}

	@Override
	public void fillToolBar(IRichTextToolBar toolBar) {
		toolBar.addAction(new FontStyleAction(this));
		toolBar.addAction(new FontNameAction(this));
		toolBar.addAction(new FontSizeAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new CutAction(this));
		toolBar.addAction(new CopyAction(this));
		toolBar.addAction(new PasteAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new ClearContentAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new BoldAction(this));
		toolBar.addAction(new ItalicAction(this));
		toolBar.addAction(new UnderlineAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new SubscriptAction(this));
		toolBar.addAction(new SuperscriptAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new TidyActionGroup(this));
		toolBar.addSeparator();
		toolBar.addAction(new AddOrderedListAction(this));
		toolBar.addAction(new AddUnorderedListAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new OutdentAction(this));
		toolBar.addAction(new IndentAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new FindReplaceAction(this) {
			@Override
			public void execute(IRichText richText) {
				richText.getFindReplaceAction().execute(richText);
			}
		});
		toolBar.addSeparator();
		toolBar.addAction(new AddLinkAction(this));
		toolBar.addAction(new AddImageAction(this));
		toolBar.addAction(new AddTableAction(this));
	}
}
