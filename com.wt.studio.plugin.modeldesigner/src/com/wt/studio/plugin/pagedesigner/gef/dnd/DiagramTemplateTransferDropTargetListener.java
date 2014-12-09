package com.wt.studio.plugin.pagedesigner.gef.dnd;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import com.wt.studio.plugin.pagedesigner.gef.model.ElementFactory;

public class DiagramTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener
{

	public DiagramTemplateTransferDropTargetListener(EditPartViewer viewer)
	{
		super(viewer);
	}

	// ------------------------------------------------------------------------
	// Abstract methods from TemplateTransferDropTargetListener

	protected CreationFactory getFactory(Object template)
	{
		return new ElementFactory(template);
	}
}