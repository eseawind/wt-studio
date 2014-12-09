package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.ToolbarLayout;

import com.wt.studio.plugin.querydesigner.gef.layout.FillLayout;
import com.wt.studio.plugin.querydesigner.gef.layout.ScrollAreaLayout;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;

public class TableModelFigure extends Figure
{
	private TableModel tableModel;
	private FrameBorder border;
	private IFigure headerFigure;

	public TableModelFigure()
	{
		ToolbarLayout layout = new ToolbarLayout();
		layout.setHorizontal(true);
		layout.setSpacing(10);
		layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);

		setLayoutManager(new FillLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new FrameBorder("列表");
		border.setLabel("Block");
		this.setBorder(border);
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setHorizontalScrollBarVisibility(1);
		scrollpane.setVerticalScrollBarVisibility(0);
		headerFigure = new FreeformLayer();
		headerFigure.setLayoutManager(new ScrollAreaLayout());
		headerFigure.setBackgroundColor(ColorConstants.white);
		add(scrollpane);
		scrollpane.setViewport(new FreeformViewport());
		scrollpane.setContents(headerFigure);
	}

	public TableModel getTableModel()
	{
		return tableModel;
	}

	public void setTableModel(TableModel tableModel)
	{
		this.tableModel = tableModel;
		this.repaint();
	}

	public FrameBorder getBorder()
	{
		return border;
	}

	public IFigure getContent()
	{
		return headerFigure;
	}

}