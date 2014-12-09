package com.wt.studio.plugin.modeldesigner.editor.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.TitleBarBorder;
import org.eclipse.draw2d.ToolbarLayout;

import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;



public class ITableModelFigure extends Figure
{
	protected BONodeModel tableModel;
	private  FrameBorder border;
	public BONodeModel getTableModel()
	{
		return tableModel;
	}

	public void setTableModel(BONodeModel tableModel)
	{
		this.tableModel = tableModel;
		border.setLabel(((HdbTableModel)tableModel).getTitle());
	}


	public ITableModelFigure()
	{
		ToolbarLayout layout = new ToolbarLayout();
		//layout.setSpacing(10);
		layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		setLayoutManager(layout);
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new  IFrameBorder();
		setBorder(border);
		this.add(new ScrollPane());
	}

	public  FrameBorder getBorder()
	{
		return border;
	}

}
