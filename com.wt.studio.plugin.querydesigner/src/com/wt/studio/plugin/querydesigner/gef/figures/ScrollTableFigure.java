package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.ToolbarLayout;

public class ScrollTableFigure extends ScrollPane
{

	private Panel panel;

	public ScrollTableFigure()
	{
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		ToolbarLayout layout = new ToolbarLayout();
		layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		layout.setStretchMinorAxis(false);
		layout.setSpacing(2);
		panel = new Panel();
		panel.setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(3)));
		panel.setLayoutManager(layout);
		setContents(panel);
	}

	public IFigure getContentFigure()
	{
		return panel;
	}

}