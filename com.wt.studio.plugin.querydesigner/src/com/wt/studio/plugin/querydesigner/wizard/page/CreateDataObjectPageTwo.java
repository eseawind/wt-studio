package com.wt.studio.plugin.querydesigner.wizard.page;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.wt.studio.plugin.querydesigner.model.ColumnModel;
import com.wt.studio.plugin.querydesigner.model.DataObjectModel;
import com.wt.studio.plugin.querydesigner.provider.cellmodifier.ColumnModelCellModifier;
import com.wt.studio.plugin.querydesigner.provider.label.ColumnModelLabelProvider;

public class CreateDataObjectPageTwo extends WizardPage
{
	private DataObjectModel dataObjectModel;
	private TableViewer tableViewer;

	public CreateDataObjectPageTwo(String pageName, String title, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
	}

	@Override
	public void createControl(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NULL);
		FormLayout formLayout = new FormLayout();
		formLayout.marginHeight = 3;
		formLayout.marginWidth = 3;
		composite.setLayout(formLayout);

		Group group = new Group(composite, SWT.NULL);
		group.setText("Columns");
		group.setLayout(new FillLayout());

		FormData formData = new FormData();
		formData.top = new FormAttachment(0);
		formData.bottom = new FormAttachment(100);
		formData.left = new FormAttachment(0);
		formData.right = new FormAttachment(100);
		group.setLayoutData(formData);

		tableViewer = new TableViewer(group, SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		for (String colName : ColumnModelLabelProvider.colNames) {
			TableColumn col = new TableColumn(tableViewer.getTable(), SWT.CENTER);
			col.setText(colName);
		}
		tableViewer.setLabelProvider(new ColumnModelLabelProvider());
		CellEditor[] editors = new CellEditor[3];
		// editors[0] = new TextCellEditor(table);
		editors[1] = new ComboBoxCellEditor(table, ColumnModelLabelProvider.types);
		editors[2] = new TextCellEditor(table);
		tableViewer.setCellEditors(editors);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setColumnProperties(ColumnModelLabelProvider.colNames);
		tableViewer.setCellModifier(new ColumnModelCellModifier(tableViewer));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tableViewer.setInput(new ArrayList<ColumnModel>());
		packTableViewer();
		setControl(composite);
	}

	public void refreshColums(DataObjectModel model)
	{
		this.dataObjectModel = model;
		tableViewer.setInput(model.getCms());
		packTableViewer();
	}

	public void packTableViewer()
	{
		Table table = tableViewer.getTable();
		for (TableColumn col : table.getColumns()) {
			col.pack();
		}
	}

	@SuppressWarnings("unchecked")
	private List<ColumnModel> getTableContent()
	{
		return (List<ColumnModel>) tableViewer.getInput();
	}

	public DataObjectModel getDataObjectModel()
	{
		dataObjectModel.setCms(getTableContent());
		return dataObjectModel;
	}

	public void setDataObjectModel(DataObjectModel dataObjectModel)
	{
		this.dataObjectModel = dataObjectModel;
	}

}
