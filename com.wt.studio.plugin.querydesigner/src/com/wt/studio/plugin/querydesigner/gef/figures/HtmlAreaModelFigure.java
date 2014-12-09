package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;

import com.wt.studio.plugin.querydesigner.gef.layout.FillLayout;
import com.wt.studio.plugin.querydesigner.gef.model.HtmlAreaModel;

public class HtmlAreaModelFigure extends Figure
{
	private HtmlAreaModel html;

	public HtmlAreaModel getHtml()
	{
		return html;
	}

	public void setHtml(HtmlAreaModel html)
	{
		this.html = html;
		this.border.setLabel(html.getName());
		this.repaint();
	}

	private FrameBorder border;

	public HtmlAreaModelFigure()
	{
		setLayoutManager(new FillLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new FrameBorder();
		border.setLabel("Block");
		this.setBorder(border);
	}
}