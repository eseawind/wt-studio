package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import com.wt.studio.plugin.pagedesigner.gef.layout.HorizonFillLayout;
import com.wt.studio.plugin.pagedesigner.gef.layout.HorizontalLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.HorizonBlockModel;


public class HorizonBlockModelFigure extends Figure
{
	protected HorizonBlockModel blockModel;
	protected Rectangle rec;

	protected LineBorder border;
	Color colour = new Color(null, 0, 0, 196);

	public HorizonBlockModelFigure()
	{
		HorizonFillLayout horizon=new HorizonFillLayout();
		horizon.setSpacing(5);
		setLayoutManager(horizon);
		border = new LineBorder();
		border.setColor(colour);
		border.setWidth(1);
		border.setStyle(SWT.LINE_DASHDOT);
		setBorder(border);
	}

	public HorizonBlockModel getModel()
	{
		return blockModel;
	}

	/**
	 * 
	 * 方法说明：设置水平布局两种布局方式：1、充满    2、不充满、可拖拽
	 *
	 * @param blockModel
	 */
	public void setModel(HorizonBlockModel blockModel)
	{
		this.blockModel = blockModel;
		if(blockModel.getLayoutType()==0)
			{
			HorizonFillLayout horizon=new HorizonFillLayout();
			horizon.setSpacing(5);
			this.setLayoutManager(horizon);
			}
		else if(blockModel.getLayoutType()==1)
		{
//			ToolbarLayout tool=new ToolbarLayout();
//			tool.setHorizontal(true);
			this.setLayoutManager(new HorizontalLayout());
		}
	}
	public Rectangle getRectangle()
	{
		return rec;
	}
	public void setRectangle(Rectangle rec)
	{
		this.rec=rec;
	}
}
