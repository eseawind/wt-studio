package com.wt.studio.plugin.note.editors;

import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class NoteFormPage extends FormPage {
	private static String ID = "com.hirisun.ide.plugin.note.editors.NoteFormPage";
	private FormToolkit toolkit;
	
	
	public NoteFormPage(FormEditor editor) {
		// 构造方法，设置Form页的ID和名称
		super(editor, ID, "第一页");
	}

	// 覆盖父类中的方法
	// 在该方法中创建表单区域的各种控件
	protected void createFormContent(IManagedForm managedForm) {
		// 通过managedForm对象获得表单工具对象
		toolkit = managedForm.getToolkit();
		// 通过managedForm对象获得ScrolledForm可滚动的表单对象
		ScrolledForm form = managedForm.getForm();
		// 设置表单文本
		form.setText("这是第一页，Hello, Eclipse 表单");
		// 创建表格布局
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;// 表格的列数
		layout.bottomMargin = 0;// 下补白
		layout.topMargin = 0;// 上补白
		layout.leftMargin = 0;// 左补白
		layout.rightMargin = 0;// 右补白
		form.getBody().setLayout(layout);// 设置表格的布局
		
		form.getBody().setBackground(
				form.getBody().getDisplay()
						.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
	}
}
