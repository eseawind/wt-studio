package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.SchemeBorder;
import org.eclipse.draw2d.TitleBarBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class VerticalBlockFrameBorder extends FrameBorder
{
	protected void createBorders()
	{
		inner = new BlockTitleBarBorder();
		outer = new SchemeBorder(SCHEME_FRAME);
	}
}

class BlockTitleBarBorder extends TitleBarBorder
{

	Color orange = new Color(null, 0, 196, 0);
	private Insets padding = new Insets(1, 3, 2, 2);

	public void paint(IFigure figure, Graphics g, Insets insets)
	{
		tempRect.setBounds(getPaintRectangle(figure, insets));
		Rectangle rec = tempRect;
		rec.height = 3;
		g.clipRect(rec);
		g.setBackgroundColor(orange);
		g.fillRectangle(rec);

		int x = rec.x + padding.left;
		int y = rec.y + padding.top;

		int textWidth = getTextExtents(figure).width;
		int freeSpace = rec.width - padding.getWidth() - textWidth;

		if (getTextAlignment() == PositionConstants.CENTER)
			freeSpace /= 2;
		if (getTextAlignment() != PositionConstants.LEFT)
			x += freeSpace;

		g.setFont(getFont(figure));
		g.setForegroundColor(getTextColor());
	}

}
