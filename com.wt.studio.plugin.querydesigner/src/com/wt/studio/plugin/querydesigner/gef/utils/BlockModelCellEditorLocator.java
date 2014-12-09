package com.wt.studio.plugin.querydesigner.gef.utils;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.querydesigner.gef.figures.BlockModelFigure;

public class BlockModelCellEditorLocator implements CellEditorLocator
{
	private BlockModelFigure blockModelFigure;

	public BlockModelCellEditorLocator(BlockModelFigure nodeFigure)
	{
		this.blockModelFigure = nodeFigure;
	}

	// ------------------------------------------------------------------------
	// Abstract methods from CellEditorLocator
	@Override
	public void relocate(CellEditor celleditor)
	{
		Rectangle rectangle = blockModelFigure.getBounds();
		Text text = (Text) celleditor.getControl();
		Point pref = text.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		text.setBounds(rectangle.x - 1, rectangle.y - 1, pref.x + 1, pref.y + 1);
	}
}