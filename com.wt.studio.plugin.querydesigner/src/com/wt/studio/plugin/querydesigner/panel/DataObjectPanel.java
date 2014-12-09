package com.wt.studio.plugin.querydesigner.panel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.wt.studio.plugin.querydesigner.model.ColumnModel;
import com.wt.studio.plugin.querydesigner.model.DataObjectModel;
import com.wt.studio.plugin.querydesigner.model.DataSourceModel;
import com.wt.studio.plugin.querydesigner.utils.CommonEclipseUtil;

public class DataObjectPanel extends Composite
{
	private Tree dataObjectTree;

	public DataObjectPanel(Composite parent, int style)
	{
		super(parent, style);

		FormLayout layout = new FormLayout();
		layout.marginTop = 3;
		layout.marginLeft = 3;
		layout.marginRight = 3;
		layout.marginBottom = 3;
		this.setLayout(layout);

		Group group = new Group(this, SWT.NULL);
		group.setLayout(new FillLayout());
		group.setText("数据对象");
		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.left = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		formData.right = new FormAttachment(100);
		group.setLayoutData(formData);
		group.setLayout(new FillLayout());
		dataObjectTree = new Tree(group, SWT.SINGLE);

		/*
		DragSource ds = new DragSource(dataObjectTree, DND.DROP_COPY);
		ds.setTransfer(new Transfer[] { ColumnModelTransfer.getInstance() });
		ds.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragFinished(DragSourceEvent event)
			{
				super.dragFinished(event);
			}

			public void dragSetData(DragSourceEvent event)
			{
				String type = (String) dataObjectTree.getSelection()[0].getData("type");
				if ("DataObject".equals(type)) {
					TreeItem selection = dataObjectTree.getSelection()[0];
					DataObjectModel dataObjectModel = (DataObjectModel) selection.getData();
					ColumnModel[] cols = new ColumnModel[selection.getItemCount()];
					for (int i = 0; i < selection.getItemCount(); i++) {
						cols[i] = (ColumnModel) selection.getItem(i).getData();
						cols[i].setSql(dataObjectModel.getSql());
					}
					event.data = cols;
				} else if ("column".equals(type)) {
					DataObjectModel dataObjectModel = (DataObjectModel) dataObjectTree
							.getSelection()[0].getParentItem().getData();
					ColumnModel[] cols = new ColumnModel[dataObjectTree.getSelectionCount()];
					for (int i = 0; i < dataObjectTree.getSelectionCount(); i++) {
						cols[i] = (ColumnModel) dataObjectTree.getSelection()[i].getData();
						cols[i].setSql(dataObjectModel.getSql());
					}
					event.data = cols;
				}
			}

			@Override
			public void dragStart(DragSourceEvent event)
			{
				if (dataObjectTree.getSelectionCount() == 0) {
					event.doit = false;
					return;
				}
			}
		});
		*/
	}

	public void reLoadDataObject()
	{
		dataObjectTree.removeAll();
		for (IConnectionProfile profile : CommonEclipseUtil.getConnectionProfiles()) {
			TreeItem dataSourceItem = new TreeItem(dataObjectTree, SWT.NULL);
			dataSourceItem.setText(profile.getName());
			dataSourceItem.setData(profile);
			dataSourceItem.setImage(CommonEclipseUtil.getImage("icons/database.png").createImage());
			addItem(profile, "Users", defaultSql, dataSourceItem);
			dataSourceItem.setExpanded(true);
		}
		dataObjectTree.layout();
	}

	public void addItem(IConnectionProfile profile, String table)
	{
		try {
			if (!CommonEclipseUtil.testSql(profile, sql + table)) {
				CommonEclipseUtil.showError("测试未通过[" + sql + table + "]");
				return;
			}
		} catch (Exception e) {
			CommonEclipseUtil.errorDialogWithStackTrace(e.getMessage(), e);
		}
		TreeItem[] items = dataObjectTree.getItems();
		for (TreeItem item : items) {
			if (item.getText().equals(profile.getName())) {
				addItem(profile, table, item);
				dataObjectTree.layout();
				return;
			}
		}
		addItem(profile, table, dataObjectTree);
		dataObjectTree.layout();
	}

	private void addItem(IConnectionProfile profile, String table, Tree parent)
	{
		TreeItem item = new TreeItem(parent, SWT.NULL);
		item.setText(profile.getName());
		item.setData("connectionProfile", profile);
		item.setImage(CommonEclipseUtil.getImage("icons/database.png").createImage());
		addItem(profile, table, item);
	}

	private void addItem(IConnectionProfile profile, String table, TreeItem parent)
	{
		addItem(profile, table, sql + table, parent);
	}

	private void addItem(IConnectionProfile profile, String name, String sql, TreeItem parent)
	{
		for (TreeItem it : parent.getItems()) {
			if (it.getText().equals(name)) {
				it.dispose();
				break;
			}
		}
		DataObjectModel dataObjectModel = new DataObjectModel();
		dataObjectModel.setConnectionProfile(profile);
		dataObjectModel.setName(name);
		dataObjectModel.setSql(sql);
		List<ColumnModel> cms = new ArrayList<ColumnModel>();
		dataObjectModel.setCms(cms);
		TreeItem tableItem = new TreeItem(parent, SWT.NULL);
		tableItem.setText(name);
		tableItem.setData(dataObjectModel);
		tableItem.setData("type", "DataObject");
		tableItem.setImage(CommonEclipseUtil.getImage("icons/table.gif").createImage());
		for (String s : CommonEclipseUtil.getColumnsFromSql(profile, sql)) {
			TreeItem column = new TreeItem(tableItem, SWT.NULL);
			column.setText(s);
			column.setData("type", "column");
			column.setImage(CommonEclipseUtil.getImage("icons/column.png").createImage());
			ColumnModel cm = new ColumnModel();
			cm.setName(s);
			cm.setDescription(s);
			cms.add(cm);
			column.setData(cm);
		}
	}

	public void addItem(DataObjectModel dataObjectModel)
	{
		TreeItem[] items = dataObjectTree.getItems();
		for (TreeItem item : items) {
			if (item.getText().equals(dataObjectModel.getConnectionProfile().getName())) {
				addItem(dataObjectModel, item);
				dataObjectTree.layout();
				return;
			}
		}
		addItem(dataObjectModel, dataObjectTree);
		dataObjectTree.layout();
	}

	private void addItem(DataObjectModel dataObjectModel, Tree parent)
	{
		TreeItem item = new TreeItem(parent, SWT.NULL);
		item.setText(dataObjectModel.getConnectionProfile().getName());
		item.setData("connectionProfile", dataObjectModel.getConnectionProfile());
		item.setImage(CommonEclipseUtil.getImage("icons/database.png").createImage());
		addItem(dataObjectModel, item);
	}

	private void addItem(DataObjectModel dataObjectModel, TreeItem parent)
	{
		for (TreeItem it : parent.getItems()) {
			if (it.getText().equals(dataObjectModel.getName())) {
				it.dispose();
				break;
			}
		}
		TreeItem tableItem = new TreeItem(parent, SWT.NULL);
		tableItem.setText(dataObjectModel.getName());
		tableItem.setData(dataObjectModel);
		tableItem.setData("type", "DataObject");
		tableItem.setImage(CommonEclipseUtil.getImage("icons/table.gif").createImage());
		for (ColumnModel cm : dataObjectModel.getCms()) {
			TreeItem column = new TreeItem(tableItem, SWT.NULL);
			column.setText(cm.getName());
			column.setData("type", "column");
			column.setData(cm);
			column.setImage(CommonEclipseUtil.getImage("icons/column.png").createImage());
		}
	}

	public List<DataObjectModel> getDataObjectModels()
	{
		List<DataObjectModel> result = new ArrayList<DataObjectModel>();
		for (TreeItem dataSourceItem : dataObjectTree.getItems()) {
			for (TreeItem dataObjectItem : dataSourceItem.getItems()) {
				result.add((DataObjectModel) dataObjectItem.getData());
			}
		}
		return result;
	}

	public List<DataSourceModel> getDataSourceModels()
	{
		List<DataSourceModel> result = new ArrayList<DataSourceModel>();
		for (TreeItem dataSourceItem : dataObjectTree.getItems()) {
			ConnectionProfile connectionProfile = (ConnectionProfile) dataSourceItem.getData();
			DataSourceModel dataSourceModel = new DataSourceModel();
			dataSourceModel.setName(connectionProfile.getName());
			dataSourceModel.setDesc(connectionProfile.getDescription());
			dataSourceModel.setUrl(connectionProfile.getBaseProperties().getProperty(
					IJDBCConnectionProfileConstants.URL_PROP_ID));
			dataSourceModel.setUser(connectionProfile.getBaseProperties().getProperty(
					IJDBCConnectionProfileConstants.USERNAME_PROP_ID));
			dataSourceModel.setPass(connectionProfile.getBaseProperties().getProperty(
					IJDBCConnectionProfileConstants.PASSWORD_PROP_ID));
			dataSourceModel.setType(connectionProfile.getBaseProperties().getProperty(
					IJDBCConnectionProfileConstants.DATABASE_CATEGORY_ID));
			for (TreeItem dataObjectItem : dataSourceItem.getItems()) {
				dataSourceModel.getObjectModels().add((DataObjectModel) dataObjectItem.getData());
			}
			result.add(dataSourceModel);
		}
		return result;
	}

	public Tree getDataObjectTree()
	{
		return dataObjectTree;
	}

	public void setDataObjectTree(Tree dataObjectTree)
	{
		this.dataObjectTree = dataObjectTree;
	}

	private String sql = "select * from ";
	private final String defaultSql = "select 'John' Name, 'M' Gender, 39 Age from dual union select 'Mary' Name, 'F' Gender, 20 Age from dual union select 'Tom' Name, 'M' Gender, 22 Age from dual union select 'Foo' Name, 'F' Gender, 23 Age from dual";
}
