package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;

import com.wt.studio.plugin.querydesigner.gef.layout.VerticalFillLayout;
import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;

public class GhostModelFigure extends Figure
{
	protected BlockModel parentblockModel;

	// protected FrameBorder border;

	public GhostModelFigure()
	{
		VerticalFillLayout vertical = new VerticalFillLayout();
		vertical.setSpacing(0);
		setLayoutManager(vertical);
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
	}

	public BlockModel getBlockModel()
	{
		return parentblockModel;
	}

	public void setParentBlockModel(BlockModel blockModel)
	{
		this.parentblockModel = blockModel;
		this.repaint();
	}

}
