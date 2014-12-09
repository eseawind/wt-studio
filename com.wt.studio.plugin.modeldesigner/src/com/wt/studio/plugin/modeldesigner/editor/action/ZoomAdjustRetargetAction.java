package com.wt.studio.plugin.modeldesigner.editor.action;

import org.eclipse.ui.actions.RetargetAction;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;

public class ZoomAdjustRetargetAction extends RetargetAction {

	public ZoomAdjustRetargetAction() {
		super(null, null);
		setText("adjust");
		setId(ZoomAdjustAction.ID);
		setToolTipText("adjust");
		setImageDescriptor(Activator.getImageDescriptor(ImageResource.ZOOM_ADJUST));
	}
}