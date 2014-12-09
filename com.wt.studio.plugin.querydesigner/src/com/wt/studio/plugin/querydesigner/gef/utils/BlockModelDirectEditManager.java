package com.wt.studio.plugin.querydesigner.gef.utils;

import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Text;

import com.wt.studio.plugin.querydesigner.gef.figures.BlockModelFigure;

public class BlockModelDirectEditManager extends DirectEditManager
{

	Font scaledFont;
	protected BlockModelFigure blockModelFigure;

	public BlockModelDirectEditManager(GraphicalEditPart source, Class editorType,
			CellEditorLocator locator, BlockModelFigure nodeFigure)
	{
		super(source, editorType, locator);
		this.blockModelFigure = nodeFigure;
	}

	@Override
	protected void bringDown()
	{
		Font disposeFont = this.scaledFont;
		this.scaledFont = null;
		super.bringDown();
		if (disposeFont != null) {
			disposeFont.dispose();
		}
	}

	@Override
	protected void unhookListeners()
	{
		super.unhookListeners();
	}

	@Override
	protected void initCellEditor()
	{
		Text text = (Text) getCellEditor().getControl();
		FrameBorder border = (FrameBorder) this.blockModelFigure.getBorder();
		getCellEditor().setValue(border.getLabel());
		IFigure figure = ((GraphicalEditPart) getEditPart()).getFigure();
		scaledFont = figure.getFont();
		FontData data = scaledFont.getFontData()[0];
		Dimension fontSize = new Dimension(0, data.getHeight());
		data.setHeight(fontSize.height);
		scaledFont = new Font(null, data);

		text.setFont(scaledFont);
		text.selectAll();
	}

	@Override
	protected void handleValueChanged()
	{
		super.handleValueChanged();
	}
}