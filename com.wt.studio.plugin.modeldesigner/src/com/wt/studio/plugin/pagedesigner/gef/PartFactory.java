package com.wt.studio.plugin.pagedesigner.gef;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.wt.studio.plugin.modeldesigner.editor.editpart.BOModelDiagramEditPart;
import com.wt.studio.plugin.modeldesigner.editor.editpart.TableModelEditPart;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.pagedesigner.gef.editpart.BOFunctionTableModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.ColumnConnectionEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.ColumnModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.ControlModelPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.DiagramEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.FormModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.FunctionColumnModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.FunctionTableConnectionEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.GhostElementEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.HorizonBlockModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.MOFunctionTableModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.PageEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.VOFunctionTableModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.VerticalBlockModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.editpart.XYBlockModelEditPart;
import com.wt.studio.plugin.pagedesigner.gef.model.BOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.ColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlModel;
import com.wt.studio.plugin.pagedesigner.gef.model.ControlPageModel;
import com.wt.studio.plugin.pagedesigner.gef.model.Diagram;
import com.wt.studio.plugin.pagedesigner.gef.model.FormModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.GhostElement;
import com.wt.studio.plugin.pagedesigner.gef.model.HorizonBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.VOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.VerticalBlockModel;
import com.wt.studio.plugin.pagedesigner.gef.model.XYBlockModel;

public class PartFactory implements EditPartFactory
{

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;
		if (model instanceof Diagram) {
			editPart = new DiagramEditPart();
		}else if (model instanceof ControlPageModel) {
			editPart = new PageEditPart();
		}else if (model instanceof HorizonBlockModel) {
			editPart = new HorizonBlockModelEditPart();
		} else if (model instanceof VerticalBlockModel) {
			editPart = new VerticalBlockModelEditPart();
		} else if (model instanceof GhostElement) {
			editPart = new GhostElementEditPart();
		} else if (model instanceof ControlModel) {
			editPart = new ControlModelPart();
		} else if (model instanceof FormModel) {
			editPart = new FormModelEditPart();
		} else if (model instanceof XYBlockModel) {
			editPart = new XYBlockModelEditPart();
		} else if (model instanceof VOFunctionTableModel) {
			editPart = new VOFunctionTableModelEditPart();
		} else if(model instanceof FunctionColumnModel){
			editPart=new FunctionColumnModelEditPart();
		} else if (model instanceof BOFunctionTableModel) {
			editPart = new BOFunctionTableModelEditPart();
		} else if (model instanceof MOFunctionTableModel) {
			editPart = new MOFunctionTableModelEditPart();
		}else if(model instanceof ColumnConnection){
		    editPart=new ColumnConnectionEditPart();
		}else if(model instanceof ColumnModel)
			editPart=new ColumnModelEditPart();
		else if(model instanceof TableConnection){
		    editPart=new FunctionTableConnectionEditPart();
		}
		if (editPart != null) {
			editPart.setModel(model);
		}
		return editPart;
	}

}
