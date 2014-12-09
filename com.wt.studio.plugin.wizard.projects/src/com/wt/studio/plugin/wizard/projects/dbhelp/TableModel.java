package com.wt.studio.plugin.wizard.projects.dbhelp;

import java.io.Serializable;

/**
 * 
 * <pre>
 * 业务名: 表model
 * 功能说明: 表model
 * 编写日期:	2013-1-7
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class TableModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4704490651759261201L;
	private String tableName;
	private String comments;
	private String clazzName;
	
	public TableModel() {
		super();
	}
	
	public TableModel(String tableName, String comments, String clazzName) {
		super();
		this.tableName = tableName;
		this.comments = comments;
		this.clazzName = clazzName;
	}

	public String getClazzName() {
		return clazzName;
	}



	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}



	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}
}