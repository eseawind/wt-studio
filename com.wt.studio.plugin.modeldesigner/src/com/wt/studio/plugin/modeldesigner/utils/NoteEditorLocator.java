package com.wt.studio.plugin.modeldesigner.utils;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.modeldesigner.editor.figure.NoteModelFigure;

public class NoteEditorLocator implements CellEditorLocator {

	private IFigure figure;

	public NoteEditorLocator(IFigure figure) {
		this.figure = figure;
	}

	public void relocate(CellEditor cellEditor) {
		Text text = (Text) cellEditor.getControl();

		Rectangle rect = this.figure.getBounds().getCopy();
		this.figure.translateToAbsolute(rect);

		text.setBounds(rect.x + NoteModelFigure.RETURN_WIDTH, rect.y
				+ NoteModelFigure.RETURN_WIDTH, rect.width - NoteModelFigure.RETURN_WIDTH
				* 2, rect.height - NoteModelFigure.RETURN_WIDTH * 2);
	}

}
