package com.wt.studio.plugin.querydesigner.gef.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.wt.studio.plugin.querydesigner.gef.model.AbstractBlockModel;
import com.wt.studio.plugin.querydesigner.gef.model.ColumnGroupModel;
import com.wt.studio.plugin.querydesigner.gef.model.Element;
import com.wt.studio.plugin.querydesigner.gef.model.GhostModel;

public class DeleteColumnGroupCommand extends Command
{
	private AbstractBlockModel parentModel;
	private ColumnGroupModel columnGroup;
	private List<Element> elements;
	private int index;

	public void setParentModel(AbstractBlockModel parentModel)
	{
		this.parentModel = parentModel;
	}

	public void setColumnGroupModel(ColumnGroupModel columnGroup)
	{
		this.columnGroup = columnGroup;
	}

	public String getLabel()
	{
		return "delete columnGroup";
	}

	public void execute()
	{
		index = parentModel.getElements().indexOf(columnGroup);
		elements = columnGroup.getElements();
		for (Element element : elements) {
			if (!(element instanceof GhostModel)) {
				parentModel.addElement(-1, element);
			}
		}
		this.parentModel.removeElement(columnGroup);
	}

	public void undo()
	{
		this.parentModel.addElement(index, columnGroup);
		for (Element element : elements) {
			parentModel.removeElement(element);
		}
	}

	public void redo()
	{
		this.execute();
	}
}