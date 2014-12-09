package com.wt.studio.plugin.wizard.projects.model.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <pre>
 * 业务名:继承类
 * 功能说明: 继承类
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class EntityFieldInfo
{
	private String fieldType;
	private List<String> fieldNames = new ArrayList<String>();

	public String getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(String fieldType)
	{
		this.fieldType = fieldType;
	}

	public List<String> getFieldNames()
	{
		return fieldNames;
	}

	/**
	 * 
	 * 方法说明：继承方法
	 * 
	 * @param fieldName
	 *            fieldName
	 */
	public void addFieldName(String fieldName)
	{
		this.fieldNames.add(fieldName);
	}
}
