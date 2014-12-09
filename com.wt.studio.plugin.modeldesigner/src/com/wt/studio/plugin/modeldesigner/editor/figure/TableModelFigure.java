package com.wt.studio.plugin.modeldesigner.editor.figure;

import java.util.List;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.ViewModel;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;


public class TableModelFigure  extends RoundedRectangle
{
	protected BONodeModel rectangleModel;

	private Label nameLabel;
	private Figure columns;
	public static Color VERY_LIGHT_GRAY = new Color(Display.getCurrent(), 230,
			230, 230);

	public TableModelFigure()
	{
		//setBackgroundColor(new Color(null,128,128,255));
		setBackgroundColor(new Color(null,176,224,230));
		//setBackgroundColor(new Color(null,192,192,192));
		this.setLayoutManager(new BorderLayout());
		initTitleBar(this);
		createColumnArea();
		createFooter(this);
		setOpaque(true);
	}

	

	/**
	 * {@inheritDoc}
	 */
	public void initTitleBar(Figure top) {
		top.setLayoutManager(new BorderLayout());

		Figure title = new Figure();
		top.add(title, BorderLayout.TOP);
		title.setLayoutManager(new FlowLayout());
		title.setBackgroundColor(ColorConstants.darkGreen);

		ImageFigure image = new ImageFigure();
		image.setBorder(new MarginBorder(new Insets(5, 10, 5, 2)));
		image.setImage(Activator.getImageDescriptor(ImageResource.TABLE).createImage());
		title.add(image);

		this.nameLabel = new Label();
		this.nameLabel.setBorder(new MarginBorder(new Insets(5, 0, 5, 20)));
		title.add(this.nameLabel);

		Figure separater = new Figure();
		separater.setSize(100, 100);
		separater.setBackgroundColor(ColorConstants.black);
		separater.setOpaque(false);
		top.add(separater, BorderLayout.BOTTOM);
	}

	/**
	 * {@inheritDoc}
	 */
	public void createColumnArea() {
		columns=new Figure();
		this.initColumnArea(columns);

		columns.setBorder(new MarginBorder(0, 0, 0, 0));
		columns.setBackgroundColor(ColorConstants.white);
		columns.setOpaque(true);

		Figure centerFigure = new Figure();
		centerFigure.setLayoutManager(new BorderLayout());
		centerFigure.setBorder(new MarginBorder(new Insets(0, 2, 0, 2)));
		centerFigure.setBackgroundColor(new Color(null,192,192,192));
		centerFigure.add(columns, BorderLayout.CENTER);
		this.add(centerFigure, BorderLayout.CENTER);
	}
	protected void initColumnArea(IFigure columns) {
		ToolbarLayout layout = new ToolbarLayout();
		layout.setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		layout.setStretchMinorAxis(true);
		layout.setSpacing(0);

		columns.setBorder(new MarginBorder(0, 2, 2, 2));
		columns.setLayoutManager(layout);

		//columns.setBackgroundColor(null);
		//columns.setOpaque(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public void createFooter(Figure figure) {
		IFigure footer = new Figure();
		BorderLayout footerLayout = new BorderLayout();
		footer.setLayoutManager(footerLayout);
		footer.setBorder(new MarginBorder(new Insets(0, 2, 0, 2)));

		IFigure footer1 = new Figure();
		footer1.setSize(-1, 10);
		footer1.setBackgroundColor(VERY_LIGHT_GRAY);
		footer1.setOpaque(true);

		footer.add(footer1, BorderLayout.TOP);

		IFigure footer2 = new Figure();
		footer2.setSize(-1, 7);
		footer.add(footer2, BorderLayout.BOTTOM);
		figure.add(footer, BorderLayout.BOTTOM);
	}

	public void setName(String name) {
		this.nameLabel.setForegroundColor(ColorConstants.black);
		this.nameLabel.setText(name);
	}

	public void setFont(Font font, Font titleFont) {
		this.nameLabel.setFont(titleFont);
	}

	public HdbTableModel getTableModel()
	{
		return (HdbTableModel)rectangleModel;
	}

	public void setTableModel(BONodeModel rectangleModel)
	{
		this.rectangleModel = rectangleModel;
	    this.setName(((HdbTableModel)rectangleModel).getTitle());
	    //this.addColumn();
		this.repaint();
	}
	@Override
	protected void fillShape(Graphics graphics) {
		graphics.setAlpha(200);
		super.fillShape(graphics);
	}



	public IFigure getColumns()
	{
		// TODO Auto-generated method stub
		return columns;
	}
}
