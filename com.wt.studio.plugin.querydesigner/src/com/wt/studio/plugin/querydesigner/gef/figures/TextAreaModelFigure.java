package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

import com.wt.studio.plugin.querydesigner.gef.model.TextAreaModel;

public class TextAreaModelFigure extends Figure
{
	private TextAreaModel title;
	private TextFlow label;
	public static final int RETURN_WIDTH = 5;

	public TextAreaModel getTitle()
	{
		return title;
	}

	public void setTitle(TextAreaModel title)
	{
		this.title = title;
	}

	public TextAreaModelFigure()
	{

		this.setLayoutManager(new BorderLayout());
		FlowPage page = new FlowPage();

		label = new TextFlow();
		ParagraphTextLayout layout = new ParagraphTextLayout(label,
				ParagraphTextLayout.WORD_WRAP_SOFT);
		label.setLayoutManager(layout);
		label.setOpaque(false);
		page.add(label);
		this.add(page, BorderLayout.CENTER);
		this.setBorder(new LineBorder());
		this.setMinimumSize(new Dimension(RETURN_WIDTH * 2, RETURN_WIDTH * 2));
	}

	public void setText(String text)
	{
		this.label.setText(text);
	}

}
