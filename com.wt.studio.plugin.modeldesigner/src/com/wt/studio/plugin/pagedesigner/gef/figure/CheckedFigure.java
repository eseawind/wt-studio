package com.wt.studio.plugin.pagedesigner.gef.figure;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

public abstract class CheckedFigure extends Figure {
	

	/*public static final Image CHECKBOX_CHECKED = createImage("icons/checkbox_checked.gif");

	public static final Image CHECKBOX_UNCHECKED = createImage("icons/checkbox_unchecked.gif");

	public static final Image RADIO_CHECKED = createImage("icons/radiobutton_checked.gif");

	public static final Image RADIO_UNCHECKED = createImage("icons/radiobutton_unchecked.gif");*/

	private static Image createImage(String name) {
		InputStream stream = CheckedFigure.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

	private Image decrotorImage;
	private String text;

	protected boolean selection;

	public CheckedFigure() {
		this("Checkable");
	}

	public CheckedFigure(String text) {
		setText(text);
	}

	/**
	 * @return the decrotorImage
	 */
	public Image getDecrotorImage() {
		return decrotorImage;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the selection
	 */
	public boolean isSelection() {
		return selection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Label#paintFigure(org.eclipse.draw2d.Graphics)
	 */
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		Rectangle bound = getBounds();
		int y = bound.y + (bound.height - decrotorImage.getBounds().height) / 2
				+ 1;
		graphics.drawImage(decrotorImage, bound.x, y);
		graphics.drawText(getText(), bound.x + decrotorImage.getBounds().width
				+ 1, y + 1);
	}

	/**
	 * @param decrotorImage
	 *            the decrotorImage to set
	 */
	public void setDecrotorImage(Image decrotorImage) {
		this.decrotorImage = decrotorImage;
	}

	/**
	 * @param selection
	 *            the selection to set
	 */
	public abstract void setSelection(boolean selection);

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.draw2d.Figure#setBounds(org.eclipse.draw2d.geometry.Rectangle
	 * )
	 */
	@Override
	public void setBounds(Rectangle rect) {
		//Ensure that the checked figure has a limited minimum height.
		if (rect.height < 19) {
			rect.height = 19;
		}
		super.setBounds(rect);
	}
}