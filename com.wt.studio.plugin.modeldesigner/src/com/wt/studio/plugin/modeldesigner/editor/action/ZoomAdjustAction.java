package com.wt.studio.plugin.modeldesigner.editor.action;

import org.eclipse.gef.Disposable;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.jface.action.Action;

import com.wt.studio.plugin.modeldesigner.Activator;
import com.wt.studio.plugin.modeldesigner.utils.ImageResource;
public class ZoomAdjustAction  extends Action implements ZoomListener,
Disposable {

public static final String ID = ZoomAdjustAction.class.getName();

protected ZoomManager zoomManager;

public ZoomAdjustAction(ZoomManager zoomManager) {
super("adjudtout",
		Activator.getImageDescriptor(ImageResource.ZOOM_ADJUST));
this.zoomManager = zoomManager;
zoomManager.addZoomListener(this);

setToolTipText("adjust");
setId(ID);
}

public void dispose() {
this.zoomManager.removeZoomListener(this);
}

@Override
public void run() {
this.zoomManager.setZoomAsText(ZoomManager.FIT_ALL);
}

public void zoomChanged(double zoom) {
setEnabled(true);
}

}