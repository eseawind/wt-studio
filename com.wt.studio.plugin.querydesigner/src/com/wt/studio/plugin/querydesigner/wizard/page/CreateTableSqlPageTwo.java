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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.wt.studio.plugin.querydesigner.gef.model.ColumnModel2;
import com.wt.studio.plugin.querydesigner.model.SqlAreaModel;
import com.wt.studio.plugin.querydesigner.model.TableSqlAreaModel;
import com.wt.studio.plugin.querydesigner.provider.cellmodifier.ColumnModel2CellModifier;
import com.wt.studio.plugin.querydesigner.provider.label.ColumnModel2LabelProvider;
import com.wt.studio.plugin.querydesigner.provider.model.DirtyModel;

public class CreateTableSqlPageTwo extends WizardPage
{
	private TableSqlAreaModel sqlAreaModel;
	private TableViewer tableViewer;
	private DirtyModel isDirty = new DirtyModel();

	public DirtyModel getIsDirty()
	{
		return isDirty;
	}

	public void setIsDirty(DirtyModel isDirty)
	{
		this.isDirty = isDirty;
	}

	public CreateTableSqlPageTwo(String pageName, String title, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
	}

	@Override
	public void createControl(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 10;
		gridLayout.marginHeight = 10;
		composite.setLayout(gridLayout);

		Group group = new Group(composite, SWT.NULL);
		group.setText("Columns");
		group.setLayout(new FillLayout());
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		group.setLayoutData(gridData);
		// group.setLayoutData(formData);

		tableViewer = new TableViewer(group, SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		for (String colName : ColumnModel2LabelProvider.colNames) {
			TableColumn col = new TableColumn(tableViewer.getTable(), SWT.LEFT);
			col.setText(colName);
		}
		tableViewer.setLabelProvider(new ColumnModel2LabelProvider());
		CellEditor[] editors = new CellEditor[15];
		// editors[0] = new CheckboxCellEditor(table);
		editors[1] = new TextCellEditor(table);
		editors[2] = new ComboBoxCellEditor(table, ColumnModel2.LABEL_YES_NO);
		editors[3] = new TextCellEditor(table);
		editors[4] = new TextCellEditor(table);
		editors[5] = new ComboBoxCellEditor(table, ColumnModel2.LABEL_ALIGNS);
		editors[6] = new TextCellEditor(table);
		editors[7] = new TextCellEditor(table);
		editors[8] = new ComboBoxCellEditor(table, ColumnModel2.LABEL_OPERATIONS);
		editors[9] = new ComboBoxCellEditor(table, ColumnModel2.LABEL_YES_NO);
		editors[10] = new TextCellEditor(table);
		editors[11] = new ComboBoxCellEditor(table, ColumnModel2.LABEL_TARGETS);
		editors[12] = new TextCellEditor(table);
		editors[13] = new TextCellEditor(table);
		editors[14] = new TextCellEditor(table);
		tableViewer.setCellEditors(editors);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setColumnProperties(ColumnModel2LabelProvider.colNames);
		tableViewer.setCellModifier(new ColumnModel2CellModifier(tableViewer, isDirty));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tableViewer.setInput(new ArrayList<ColumnModel2>());
		packTableViewer();
		setControl(composite);
	}

	public void refreshColums(TableSqlAreaModel model)
	{
		List<ColumnModel2> cms = null;
		if (this.sqlAreaModel == null) {
			cms = model.getCms();
		} else {
			cms = new ArrayList<ColumnModel2>();
			@SuppressWarnings("unchecked")
			List<ColumnModel2> oldCms = (List<ColumnModel2>) tableViewer.getInput();
			for (ColumnModel2 cm : model.getCms()) {
				int i = oldCms.indexOf(cm);
				if (i > -1) {
					cms.add(oldCms.get(i));
				} else {
					cms.add(cm);
				}
			}
		}
		this.sqlAreaModel = model;
		this.sqlAreaModel.setCms(cms);
		tableViewer.setInput(cms);
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
	private List<ColumnModel2> getTableContent()
	{
		return (List<ColumnModel2>) tableViewer.getInput();
	}

	public SqlAreaModel getSqlAreaModel()
	{
		sqlAreaModel.setCms(getTableContent());
		return sqlAreaModel;
	}

	public void setSqlAreaModel(TableSqlAreaModel sqlAreaModel)
	{
		this.sqlAreaModel = sqlAreaModel;
	}

}
