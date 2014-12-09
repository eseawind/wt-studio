package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.graphics.Color;

import com.wt.studio.plugin.pagedesigner.gef.model.XYBlockModel;

public class XYBlockModelFigure extends Figure
{
	protected XYBlockModel blockModel;

	protected LineBorder border;
	Color color = new Color(null, 0, 196, 0);

	public XYBlockModelFigure()
	{
		setLayoutManager(new XYLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new LineBorder();
		border.setColor(ColorConstants.darkBlue);
		border.setWidth(1);
		setBorder(border);
	}


	public XYBlockModel getModel()
	{
		return blockModel;
	}

	/*public String getText()
	{
		return border.getLabel();
	}*/

	public void setModel(XYBlockModel blockModel)
	{
		this.blockModel = blockModel;
	}
	

}
