package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;

import com.wt.studio.plugin.querydesigner.gef.model.ChartBlockModel;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;

public class ChartBlockModelFigure extends Figure
{
	private ChartBlockModel blockModel;

	private FrameBorder border;
	private IFigure container;
	private GridData gridData;

	public ChartBlockModelFigure()
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
	}

	public void getContainer(String chartType)
	{
		if (container != null) {
			this.remove(container);
		}
		container = createImage(chartType);
		if (container != null) {
			this.add(container, gridData);
		}

	}

	private IFigure createImage(String chartType)
	{
		ImageFigure imageFigure = new ImageFigure();
		if (CommonEclipseUtil.getImage("icons/chart/" + chartType + ".jpg").createImage() != null) {
			imageFigure.setImage(CommonEclipseUtil.getImage("icons/chart/" + chartType + ".jpg")
					.createImage());
			return imageFigure;
		} else {
			imageFigure
					.setImage(CommonEclipseUtil.getImage("icons/chart/column.jpg").createImage());
			return imageFigure;

		}
	}

	public String getText()
	{
		return border.getLabel();
	}

	public ChartBlockModel getBlockModel()
	{
		return blockModel;
	}

	public void setBlockModel(ChartBlockModel blockModel)
	{
		this.blockModel = blockModel;
		this.border.setLabel(blockModel.getName());
		this.repaint();
	}

	public IFigure createColumn()
	{
		ImageFigure imageFigure = new ImageFigure();
		imageFigure.setImage(CommonEclipseUtil.getImage("icons/chart/column.jpg").createImage());
		return imageFigure;
	}

	public IFigure createLine()
	{
		ImageFigure imageFigure = new ImageFigure();
		imageFigure.setImage(CommonEclipseUtil.getImage("icons/chart/line.jpg").createImage());
		return imageFigure;
	}

	public IFigure createPie()
	{
		ImageFigure imageFigure = new ImageFigure();
		imageFigure.setImage(CommonEclipseUtil.getImage("icons/chart/pie.png").createImage());
		return imageFigure;
	}

}
