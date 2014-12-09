package com.wt.studio.plugin.modeldesigner.editor.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.wt.studio.plugin.modeldesigner.editor.commands.CreateNoteCommand;
import com.wt.studio.plugin.modeldesigner.editor.commands.CreateTableCommand;
import com.wt.studio.plugin.modeldesigner.editor.commands.CreateViewCommand;
import com.wt.studio.plugin.modeldesigner.editor.commands.MoveElementCommand;
import com.wt.studio.plugin.modeldesigner.editor.model.BOModelDiagram;
import com.wt.studio.plugin.modeldesigner.editor.model.BONodeModel;
import com.wt.studio.plugin.modeldesigner.editor.model.HdbTableModel;
import com.wt.studio.plugin.modeldesigner.editor.model.NoteModel;
import com.wt.studio.plugin.modeldesigner.editor.model.ViewModel;

public class BODiagramLayoutEditPolicy extends XYLayoutEditPolicy
{

	@Override
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child,
			Object constraint)
	{
		Rectangle rectangle = (Rectangle) constraint;
		MoveElementCommand command = new MoveElementCommand();
		command.setElement((BONodeModel) child.getModel());
		command.setRectangle(rectangle);
		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request)
	{
		if (request.getNewObject() instanceof HdbTableModel) {
			CreateTableCommand command = new CreateTableCommand();
			command.setDiagram((BOModelDiagram) getHost().getModel());
			command.setNode((HdbTableModel) request.getNewObject());
			Rectangle rectangle = (Rectangle) getConstraintFor(request);
			command.setRectangle(rectangle);
			return command;
		}else if (request.getNewObject() instanceof NoteModel) {
			CreateNoteCommand command = new CreateNoteCommand();
			command.setDiagram((BOModelDiagram) getHost().getModel());
			command.setNode((NoteModel) request.getNewObject());
			Rectangle rectangle = (Rectangle) getConstraintFor(request);
			command.setRectangle(rectangle);
			return command;
		}  else if (request.getNewObject() instanceof ViewModel) {
			CreateViewCommand command = new CreateViewCommand();
			command.setDiagram((BOModelDiagram) getHost().getModel());
			command.setNode((ViewModel) request.getNewObject());
			Rectangle rectangle = (Rectangle) getConstraintFor(request);
			command.setRectangle(rectangle);
			return command;
		}  
		else
		    return null;
	}

	//@Override
	/*protected EditPolicy createChildEditPolicy(EditPart child) {
		return new NodeSelectionEditPolicy();
	}*/
}
