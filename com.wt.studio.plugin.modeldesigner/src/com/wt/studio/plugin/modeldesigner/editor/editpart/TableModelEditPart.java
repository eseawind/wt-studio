package com.wt.studio.plugin.modeldesigner.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;

import com.wt.studio.plugin.modeldesigner.dialog.TableDialog;
import com.wt.studio.plugin.modeldesigner.editor.commands.MoveElementCommand;
import com.wt.studio.plugin.modeldesigner.editor.figure.ITableModelFigure;
import com.wt.studio.plugin.modeldesigner.editor.figure.TableModelFigure;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;


public class TableModelEditPart extends BONodeEditPart implements PropertyChangeListener, DeleteableEditPart
{
	private ITableModelFigure rectangleModelFigure;
	
	protected List<HdbColumnModel> getModelChildren()
	{
		return ((HdbTableModel)this.getModel()).getColumns();
	}
	@Override
	public void activate()
	{
		if (isActive()) {
			return;
		}
		super.activate();
		((HdbTableModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate()
	{
		if (!isActive()) {
			return;
		}
		super.deactivate();
		((HdbTableModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	protected void refreshVisuals()
	{
		HdbTableModel node = (HdbTableModel) this.getModel();
		Rectangle rectangle = node.getRectangle();
		((ITableModelFigure) this.getFigure()).setTableModel((HdbTableModel) this.getModel());
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
		/*for (Object child : this.getChildren()) {
			ColumnEditPart part = (ColumnEditPart) child;
			part.refreshTableColumns();
		}*/
		
	}

	
	public void performRequest(Request request)
	{
		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			try {
				performRequestOpen();

			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}

	public void performRequestOpen() throws CloneNotSupportedException {
		HdbTableModel table = (HdbTableModel) this.getModel();
		HdbTableModel temp=table.CopyData();
		BOModelDiagram diagram = (BOModelDiagram)this.getParent().getModel();
		TableDialog dialog = new TableDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),temp);

		if (dialog.open() == IDialogConstants.OK_ID) {
			table.getCopydata(temp);
			temp=null;
		}
	}
	protected IFigure createFigure()
	{
		if (rectangleModelFigure == null) {
			rectangleModelFigure = new ITableModelFigure();
		}
		//rectangleModelFigure.setTableModel((TableModel) this.getModel());
		return rectangleModelFigure;
	}

	@Override
	protected void createEditPolicies()
	{
		//installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
		super.createEditPolicies();
	}
	@Override
	public void setSelected(int value) {

		super.setSelected(value);
	}
	 

	@Override
	public void propertyChange(PropertyChangeEvent changeEvent)
	{
		String prop = changeEvent.getPropertyName();
		if (prop.equals(HdbTableModel.PROP_NAME) || prop.equals(HdbTableModel.PROP_RECTANGLE)
				|| prop.equals(HdbTableModel.PROP_TYPE)) {
			refreshVisuals();
		} else if (prop.equals(HdbTableModel.PROP_COLUMNS)) {
			refreshVisuals();
			refreshChildren();
			HdbTableModel note=(HdbTableModel) this.getModel();
			Rectangle rectangle=new Rectangle(note.getRectangle().x,note.getRectangle().y,note.getRectangle().width,-1);
			MoveElementCommand scommand = new MoveElementCommand();
			scommand.setElement(note);
			scommand.setRectangle(rectangle);
			scommand.execute();
		}
	   super.propertyChange(changeEvent);
	}

	@Override
	public boolean isDeleteable()
	{
		// TODO Auto-generated method stub
		return true;
	}
	/*public IFigure getContentPane() {
		TableModelFigure figure = (TableModelFigure) super.getContentPane();
		return figure.getColumns();
	}*/

	
}
