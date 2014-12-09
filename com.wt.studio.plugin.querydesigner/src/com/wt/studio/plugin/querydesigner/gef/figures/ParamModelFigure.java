package com.wt.studio.plugin.querydesigner.gef.figures;

import org.eclipse.draw2d.CheckBox;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;

import com.wt.studio.plugin.querydesigner.gef.model.ParamModel;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;

public class ParamModelFigure extends Figure
{
	private ParamModel paramModel;
	private Label paramDescriptionLabel;
	private IFigure container;
	private GridData gridData;

	public ParamModelFigure()
	{
		setLayoutManager(new GridLayout(2, false));
		paramDescriptionLabel = new Label("");
		add(paramDescriptionLabel);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = GridData.CENTER;
	}

	public ParamModel getParamModel()
	{
		return paramModel;
	}

	public void setParamModel(ParamModel model)
	{
		this.paramModel = model;
		this.setParamDescription(model.getDescription());
		this.repaint();
	}

	public void setParamDescription(String description)
	{
		paramDescriptionLabel.setText(description);
	}

	public void setWidget(String type)
	{
		if (container != null) {
			this.remove(container);
		}
		if (ParamModel.TYPE_DATE.equals(type)) {
			container = this.createDate();
		} else if (ParamModel.TYPE_USER_TREE.equals(type)) {
			container = this.createUserTree();
		} else if (ParamModel.TYPE_DEPTTREE.equals(type)) {
			container = this.createDeptTree();
		} else if (ParamModel.TYPE_TREE.equals(type)) {
			container = this.createTree();
		} else {
			container = this.createTextInput();
		}
		if (container != null) {
			this.add(container, gridData);
		}
	}

	public IFigure createTree()
	{
		ImageFigure imageFigure = new ImageFigure();
		imageFigure.setImage(CommonEclipseUtil.getImage("icons/figure/tree.png").createImage());
		return imageFigure;
	}

	public IFigure createSelect()
	{
		ImageFigure imageFigure = new ImageFigure();
		imageFigure.setImage(CommonEclipseUtil.getImage("icons/figure/select.png").createImage());
		return imageFigure;
	}

	public IFigure createDate()
	{
		ImageFigure imageFigure = new ImageFigure();
		imageFigure.setImage(CommonEclipseUtil.getImage("icons/figure/date.png").createImage());
		return imageFigure;
	}

	public IFigure createTextInput()
	{
		ImageFigure imageFigure = new ImageFigure();
		imageFigure.setImage(CommonEclipseUtil.getImage("icons/figure/input.png").createImage());
		imageFigure.setAlignment(PositionConstants.CENTER);
		return imageFigure;
	}

	public IFigure createUserTree()
	{
		ImageFigure imageFigure = new ImageFigure();
		imageFigure.setImage(CommonEclipseUtil.getImage("icons/figure/usertree.png").createImage());
		return imageFigure;
	}

	public IFigure createDeptTree()
	{
		ImageFigure imageFigure = new ImageFigure();
		imageFigure.setImage(CommonEclipseUtil.getImage("icons/figure/depttree.png").createImage());
		return imageFigure;
	}

	public IFigure createCheckbox()
	{
		return new CheckBox();
	}
}