package com.wt.studio.plugin.pagedesigner.gef.figure;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;

public class RadioButtonFigure extends CheckedFigure {

	public RadioButtonFigure() {
		this("RadioButton");
	}

	public RadioButtonFigure(String text) {
		super(text);
		setDecrotorImage(Activator.getImageDescriptor(ImageResource.MO).createImage());
	}


	/**
	 * @param selection
	 *            the selection to set
	 */
	public void setSelection(boolean selection) {
		this.selection = selection;
		setDecrotorImage(selection?Activator.getImageDescriptor(ImageResource.MO).createImage():Activator.getImageDescriptor(ImageResource.MO).createImage());
		repaint();
	}
}