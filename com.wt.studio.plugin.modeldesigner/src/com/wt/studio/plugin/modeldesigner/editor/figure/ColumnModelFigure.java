package com.wt.studio.plugin.modeldesigner.editor.figure;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;

public class ColumnModelFigure extends Figure
{
	HdbColumnModel column;
	Label label;
	ImageFigure FKImage;
	
	
	public HdbColumnModel getColumn()
	{
		return column;
	}

	public void setColumn(HdbColumnModel column)
	{
		this.removeAll();
		CreateContainer();
		this.column = column;
		StringBuffer str=new StringBuffer();
		str.append(column.getCode());
		str.append("  ");
		str.append(column.getDataType());
		label.setText(str.toString());
		if(column.isPK())
		{
		    FKImage.setImage(Activator.getImageDescriptor("icons/pkey.gif").createImage());
		}
		if(column.isFK())
		{
			FKImage.setImage(Activator.getImageDescriptor("icons/fkey.gif").createImage());
		}
		label.setForegroundColor(ColorConstants.darkBlue);
	}

	private void CreateContainer()
	{
		 
		 FKImage = new ImageFigure();
		 FKImage.setBounds(new Rectangle(this.bounds.x,this.bounds.y,80,20));
		 FKImage.setBorder(new MarginBorder(new Insets(5, 2, 5, 2)));
		// FKImage.setImage(null);
		 Rectangle tempRect=new Rectangle();
		 Point loc=this.getLocation();
		 Dimension dim=new Dimension(16,16);
		 tempRect.setBounds(loc,dim);
		 this.add(FKImage,tempRect);
		 label = new Label();
		 label.setBorder(new MarginBorder(new Insets(3, 5, 3, 5)));
		 label.setLabelAlignment(PositionConstants.LEFT);
		 this.add(label);
		 
	}

	public ColumnModelFigure()
	{
		this.setLayoutManager(new FlowLayout());
		this.setOpaque(true);
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent me)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent me)
			{
				// TODO Auto-generated method stub
				Figure source=(Figure) me.getSource();
				if(!column.isSelected())
				{
				source.setBackgroundColor(ColorConstants.titleInactiveGradient);
				}
			}

			@Override
			public void mouseExited(MouseEvent me)
			{
				// TODO Auto-generated method stub
				
				Figure source=(Figure) me.getSource();
				if(!column.isSelected())
				{
				source.setBackgroundColor(ColorConstants.white);
				}
			}

			@Override
			public void mouseHover(MouseEvent me)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent me)
			{
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	protected void paintFigure(Graphics graphics) {
		if (graphics.getBackgroundColor().equals(
				this.getParent().getBackgroundColor())) {
			graphics.setAlpha(0);
		}

		super.paintFigure(graphics);
	}
	protected void paintClientArea(Graphics graphics) {
		List children = this.getChildren();
		if (children.isEmpty())
			return;

		boolean optimizeClip = getBorder() == null || getBorder().isOpaque();

		if (useLocalCoordinates()) {
			graphics.translate(getBounds().x + getInsets().left, getBounds().y
					+ getInsets().top);
			if (!optimizeClip)
				graphics.clipRect(getClientArea(new Rectangle()));
			graphics.pushState();
			paintChildren(graphics);
			graphics.popState();
			graphics.restoreState();
		} else {
			if (optimizeClip)
				paintChildren(graphics);
			else {
				graphics.clipRect(getClientArea(new Rectangle()));
				graphics.pushState();
				paintChildren(graphics);
				graphics.popState();
				graphics.restoreState();
			}
		}
	}

	public void paint(Graphics graphics) {
		if (getLocalBackgroundColor() != null)
			graphics.setBackgroundColor(getLocalBackgroundColor());
		if (getLocalForegroundColor() != null)
			graphics.setForegroundColor(getLocalForegroundColor());
		if (font != null)
			graphics.setFont(font);

		graphics.pushState();
		try {
			paintFigure(graphics);
			graphics.restoreState();
			paintClientArea(graphics);
			paintBorder(graphics);
			Rectangle tempRect=new Rectangle();
			tempRect.setBounds(this.getBounds());
			Rectangle rec = tempRect;
			graphics.setLineStyle(SWT.LINE_DASHDOTDOT);
			graphics.setForegroundColor(ColorConstants.titleGradient);
			graphics.setLineWidth(1);
			graphics.drawLine(rec.x, rec.y+rec.height-1, rec.width+rec.x+2, rec.y+rec.height-1);
		} finally {
			graphics.popState();
		}
	}

}
