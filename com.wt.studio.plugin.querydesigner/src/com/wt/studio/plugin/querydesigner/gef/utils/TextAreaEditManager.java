package com.wt.studio.plugin.querydesigner.gef.utils;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.querydesigner.gef.model.TextAreaModel;

public class TextAreaEditManager extends DirectEditManager
{

	private TextAreaModel textArea;

	public TextAreaEditManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator)
	{
		super(source, editorType, locator);
		this.textArea = (TextAreaModel) source.getModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initCellEditor()
	{
		TextCellEditor editor = (TextCellEditor) this.getCellEditor();

		if ((textArea).getTextArea() != null) {
			editor.setValue((textArea).getTextArea());
		}

		Text text = (Text) editor.getControl();

		text.selectAll();
	}
}
