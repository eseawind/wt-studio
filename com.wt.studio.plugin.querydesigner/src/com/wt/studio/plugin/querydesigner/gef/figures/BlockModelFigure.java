package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;

import com.wt.studio.plugin.querydesigner.gef.model.BlockModel;

public class BlockModelFigure extends Figure
{
	protected BlockModel blockModel;

	protected LineBorder border;

	public BlockModel getBlockModel()
	{
		return blockModel;
	}

	public void setBlockModel(BlockModel blockModel)
	{
		this.blockModel = blockModel;
	}

}
