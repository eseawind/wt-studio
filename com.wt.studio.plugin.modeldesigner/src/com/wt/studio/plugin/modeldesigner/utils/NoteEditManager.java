package com.wt.studio.plugin.modeldesigner.utils;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;

public class NoteEditManager extends DirectEditManager {

	private NoteModel note;

	public NoteEditManager(GraphicalEditPart source, Class editorType,
			CellEditorLocator locator) {
		super(source, editorType, locator);
		this.note = (NoteModel) source.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initCellEditor() {
		TextCellEditor editor = (TextCellEditor) this.getCellEditor();

		if (note.getText() != null) {
			editor.setValue(note.getText());
		}

		Text text = (Text) editor.getControl();

		text.selectAll();
	}
}
