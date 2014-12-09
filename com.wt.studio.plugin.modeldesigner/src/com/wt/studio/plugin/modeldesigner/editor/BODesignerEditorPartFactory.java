package com.wt.studio.plugin.modeldesigner.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.wt.studio.plugin.modeldesigner.editor.editpart.BOModelDiagramEditPart;
import com.wt.studio.plugin.modeldesigner.editor.editpart.BONodeEditPart;
import com.wt.studio.plugin.modeldesigner.editor.editpart.ColumnEditPart;
import com.wt.studio.plugin.modeldesigner.editor.editpart.NodeConnectionEditPart;
import com.wt.studio.plugin.modeldesigner.editor.editpart.NoteModelEditPart;
import com.wt.studio.plugin.modeldesigner.editor.editpart.PagableFreeformRootEditPart;
import com.wt.studio.plugin.modeldesigner.editor.editpart.TableModelEditPart;
import com.wt.studio.plugin.modeldesigner.editor.editpart.ViewModelEditPart;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbColumnModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NodeConnection;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;
import com.wt.studio.plugin.modeldesigner.editor.model.ViewModel;
import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;

public class BODesignerEditorPartFactory implements EditPartFactory {
	

	public BODesignerEditorPartFactory() {
	}

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;
		if (model instanceof BOModelDiagram) {
			editPart = new BOModelDiagramEditPart();
		}else if (model instanceof HdbTableModel) {
			editPart = new TableModelEditPart();
		}else if (model instanceof NoteModel) {
			editPart = new NoteModelEditPart();
		}else if (model instanceof NodeConnection) {
			editPart = new NodeConnectionEditPart();
		}else if (model instanceof ViewModel) {
			editPart = new ViewModelEditPart();
		}else if(model instanceof HdbColumnModel){
			editPart=new ColumnEditPart();
		}
		if (editPart != null) {
			editPart.setModel(model);
		}
		return editPart;
	}

}
