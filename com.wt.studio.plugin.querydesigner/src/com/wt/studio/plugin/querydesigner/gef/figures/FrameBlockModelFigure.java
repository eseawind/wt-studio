package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;

import com.wt.studio.plugin.querydesigner.gef.model.FrameBlockModel;

public class FrameBlockModelFigure extends Figure
{
	private FrameBlockModel frame;

	public FrameBlockModel getFrame()
	{
		return frame;
	}

	public void setFrame(FrameBlockModel frame)
	{
		this.frame = frame;
		this.border.setLabel(frame.getName());
		this.repaint();
	}

	private FrameBorder border;
	private IFigure container;
	private GridData gridData;

	public FrameBlockModelFigure()
	{
		setLayoutManager(new GridLayout());
		setOpaque(true);
		setBackgroundColor(ColorConstants.white);
		border = new FrameBorder();
		border.setLabel("Block");
		this.setBorder(border);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.CENTER;
		getContainer();
	}

	public void getContainer()
	{
		if (container != null) {
			this.remove(container);
		}
		container = new ImageFigure();
		if (container != null) {
			this.add(container, gridData);
		}

	}
}
