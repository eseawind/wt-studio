package com.wt.studio.plugin.pagedesigner.gef.figure;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.SWT;

import com.wt.studio.plugin.pagedesigner.gef.model.BOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;

public class FunctionTableFigure extends Figure
{
	protected FunctionModel tableModel;
	private  FrameBorder border;
	public FunctionModel getTableModel()
	{
		return tableModel;
	}

	public void setTableModel(FunctionModel tableModel)
	{
		this.tableModel = tableModel;
		addBorder();
		border.setLabel(((FunctionModel)tableModel).getTitle());
	}


	private void addBorder()
	{
		// TODO Auto-generated method stub
		if(tableModel instanceof VOFunctionTableModel)
		{
			border = new  VOFrameBorder();
		}
		else if(tableModel instanceof MOFunctionTableModel)
		{
			border=new  MOFrameBorder();
		}
		else if(tableModel instanceof BOFunctionTableModel)
		{
			border=new BOFrameBorder();
		}
		setBorder(border);
	}

	public FunctionTableFigure()
	{
		ToolbarLayout layout = new ToolbarLayout();
		layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		setLayoutManager(layout);
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
	}


	public  FrameBorder getBorder()
	{
		return border;
	}

}
