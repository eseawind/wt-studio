package com.wt.studio.plugin.platform.preferences;

import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * UUM地址列表
 * 
 * @author Administrator
 * 
 */
public class UUMListEditor extends ListEditor
{
	/**
	 * 
	 * <pre>
	 * 业务名:
	 * 功能说明: 
	 * 编写日期:	2013-1-7
	 * 作者:	Administrator
	 * 
	 * 历史记录
	 * 1、修改日期：
	 *    修改人：
	 *    修改内容：
	 * </pre>
	 */
	public class MyInputValidator implements IInputValidator
	{
		/**
		 * 继承方法
		 * 
		 * @param arg0
		 *            arg0
		 * @return string
		 */
		public String isValid(String arg0)
		{
			return null;
		}
	}

	/**
	 * 继承方法
	 * 
	 * @param arg0
	 *            arg0
	 */
	protected void adjustForNumColumns(int arg0)
	{
		super.adjustForNumColumns(arg0);
	}

	/**
	 * 初始化
	 * 
	 * @param arg0
	 *            arg0
	 * @param arg1
	 *            arg1
	 * @param arg2
	 *            arg2
	 */
	public UUMListEditor(String arg0, String arg1, Composite arg2)
	{
		super(arg0, arg1, arg2);
		adjustForNumColumns(2);
	}

	/**
	 * 创建list
	 * 
	 * @param arg0
	 *            数组
	 * @return string
	 */
	protected String createList(String[] arg0)
	{
		String encodedArray = "";
		if (arg0 != null) {
			int size = arg0.length;
			for (int i = 0; i < size; i++)
				encodedArray = encodedArray.concat(arg0[i] + ";");
		}
		return encodedArray;
	}

	/**
	 * 继承方法
	 * 
	 * @return string
	 */
	protected String getNewInputObject()
	{
		InputDialog dialog = new InputDialog(new Shell(), "Enter an UUMAddress", "请输入UUM地址", "",
				new MyInputValidator());
		dialog.create();
		dialog.open();
		String text = dialog.getValue();
		return text;
	}

	/**
	 * 继承方法
	 * 
	 * @param arg0
	 *            arg0
	 * @return string
	 */
	protected String[] parseString(String arg0)
	{
		StringTokenizer stok = new StringTokenizer(arg0, ";");
		int arraySize = stok.countTokens();
		String[] decodedArray = new String[arraySize];
		for (int i = 0; i < arraySize; i++) {
			decodedArray[i] = stok.nextToken(";");
		}
		return decodedArray;
	}

}
