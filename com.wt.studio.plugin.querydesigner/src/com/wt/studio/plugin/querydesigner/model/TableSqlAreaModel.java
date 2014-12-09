package com.wt.studio.plugin.querydesigner.model;

import java.util.ArrayList;
import java.util.List;

import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;

public class TableSqlAreaModel extends SqlAreaModel
{

	private List<ColumnModel2> cms = new ArrayList<ColumnModel2>();

	public List<ColumnModel2> getCms()
	{
		return cms;
	}

	public void setCms(List<ColumnModel2> cms)
	{
		this.cms = cms;
	}

}
