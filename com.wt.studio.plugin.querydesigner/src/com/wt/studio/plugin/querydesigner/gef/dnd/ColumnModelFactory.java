package com.wt.studio.plugin.querydesigner.gef.dnd;

import org.eclipse.gef.requests.CreationFactory;

import com.wt.studio.plugin.querydesigner.model.ColumnModel;

public class ColumnModelFactory implements CreationFactory
{

	private ColumnModel[] columnModels = new ColumnModel[0];

	public Object getNewObject()
	{
		return columnModels;
	}

	public Object getObjectType()
	{
		return ColumnModel[].class;
	}

	public void setColumnModels(ColumnModel[] columnModels)
	{
		this.columnModels = columnModels;
	}

}