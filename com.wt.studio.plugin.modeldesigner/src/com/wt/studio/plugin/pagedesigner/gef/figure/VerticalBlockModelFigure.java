package com.wt.studio.plugin.pagedesigner.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.swt.graphics.Color;

import com.wt.studio.plugin.pagedesigner.gef.layout.PageXYLayout;
import com.wt.studio.plugin.pagedesigner.gef.layout.VerticalLayout;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;


public class VerticalBlockModelFigure extends Figure
{
	protected VerticalBlockModel blockModel;

	protected LineBorder border;
	Color color = new Color(null, 0, 196, 0);

	public VerticalBlockModelFigure()
	{
		// if (blockModel instanceof VerticalBlockModel)
		PageXYLayout vertical = new PageXYLayout();
		//vertical.setSpacing(5);
		// setLayoutManager(new VerticalFillLayout());
		// if (blockModel instanceof HorizontalBlockModel)
		// setLayoutManager(new HorizontalFillLayout());
		// FlowLayout vertical = new FlowLayout();
		// vertical.setSpacing(MAX_FLAG);
		// vertical.setVertical(true);
		setLayoutManager(vertical);
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		// this.setBounds(rect)
		border = new LineBorder();
		// border.setLabel("垂直容器");
		border.setColor(color);
		border.setWidth(1);
		// FocusBorder focus = new FocusBorder();
		// this.setBorder(border);
		// border = new TitleBarBorder("Table dsf");
		setBorder(border);
	}


	public VerticalBlockModel getModel()
	{
		return blockModel;
	}

	/*public String getText()
	{
		return border.getLabel();
	}*/

	public void setModel(VerticalBlockModel blockModel)
	{
		this.blockModel = blockModel;
		// this.border.setLabel(blockModel.getName());
		// this.repaint();
	}
	

}
