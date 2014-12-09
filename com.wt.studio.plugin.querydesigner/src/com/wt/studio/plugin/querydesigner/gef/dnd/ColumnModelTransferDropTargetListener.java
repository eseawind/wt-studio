package com.wt.studio.plugin.querydesigner.gef.dnd;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;

import com.wt.studio.plugin.querydesigner.gef.editors.part.TableModelPart;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.gef.model.TableModel;
import com.wt.studio.plugin.querydesigner.model.ColumnModel;

public class ColumnModelTransferDropTargetListener extends AbstractTransferDropTargetListener
{
	public ColumnModelTransferDropTargetListener(EditPartViewer viewer, Transfer xfer)
	{
		super(viewer, xfer);
	}

	public ColumnModelTransferDropTargetListener(EditPartViewer viewer)
	{
		super(viewer, ColumnModelTransfer.getInstance());
	}

	@Override
	protected void updateTargetRequest()
	{
		((CreateRequest) createTargetRequest()).setLocation(getDropLocation());
	}

	@Override
	protected void handleDragOver()
	{
		super.handleDragOver();
		getCurrentEvent().detail = DND.DROP_COPY;
	}

	@Override
	protected void handleDrop()
	{
		super.handleDrop();
		EditPart editPart = getTargetEditPart();
		if (editPart != null && editPart instanceof TableModelPart) {
			Object tmp = getCurrentEvent().data;
			assert tmp == null;
			Object[] objects = (Object[]) tmp;
			TableModel model = (TableModel) editPart.getModel();
			for (Object obj : objects) {
				ColumnModel col = (ColumnModel) obj;
				ColumnModel2 c2 = new ColumnModel2();
				c2.setDescription(col.getDescription() == null
						|| col.getDescription().trim().equals("") ? col.getName() : col
						.getDescription());
				c2.setName(col.getName());
				model.addColumn(c2);
				model.setSql(col.getSql());
			}
			// factory.setColumnModels(cols);
		}
	}

	protected final CreateRequest getCreateRequest()
	{
		return ((CreateRequest) getTargetRequest());
	}

	@Override
	protected Request createTargetRequest()
	{
		CreateRequest request = new CreateRequest();
		request.setFactory(factory);
		return request;
	}

	private ColumnModelFactory factory = new ColumnModelFactory();
}