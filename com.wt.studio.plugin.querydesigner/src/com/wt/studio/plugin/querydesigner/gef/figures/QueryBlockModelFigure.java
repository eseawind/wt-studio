package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;

import com.wt.studio.plugin.querydesigner.gef.layout.QueryBlockModelLayout;
import com.wt.studio.plugin.querydesigner.gef.model.QueryBlockModel;

public class QueryBlockModelFigure extends Figure
{
	private QueryBlockModel blockModel;

	private FrameBorder border;

	public QueryBlockModelFigure()
	{
		setLayoutManager(new QueryBlockModelLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new FrameBorder();
		border.setLabel("Block");
		this.setBorder(border);
	}

	public String getText()
	{
		return border.getLabel();
	}

	public QueryBlockModel getBlockModel()
	{
		return blockModel;
	}

	public void setBlockModel(QueryBlockModel blockModel)
	{
		this.blockModel = blockModel;
		this.border.setLabel(blockModel.getName());
		this.repaint();
	}
}