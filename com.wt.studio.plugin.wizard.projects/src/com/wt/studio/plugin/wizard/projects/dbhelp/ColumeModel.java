package com.wt.studio.plugin.wizard.projects.dbhelp;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <pre>
 * 业务名:model类
 * 功能说明: model类
 * 编写日期:	2013-1-21
 * 作者:	Administrator
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class ColumeModel implements Serializable {
	public enum UITemplateType {
		INPUT, DATEPICKER, SELECT
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -763236810745170860L;
	private String columeName;
	private String dataType;
	private String dataLength;
	private String comment;

	/**
	 * MOdel 主键
	 */
	private String isKey;

	/**
	 * Model 字段
	 */
	private String name;

	/**
	 * Model 验证类型
	 */
	private String valideType;
	/**
	 * Model 查询条件
	 */
	private String isQueryCond;

	private String queryControlType; // 日期，输入 下拉列表

	private String queryDataType;// 下拉列表 数据内容

	private String isListShow;

	private String manageControlType;// 日期，输入 下拉列表
	private String manageDataType;	

	private String requid; //是否非空
	public String getColumeName() {
		return columeName;
	}
	public String getComment() {
		return comment;
	}
	
	public String getDataLength() {
		return dataLength;
	}

	public String getDataType() {
		return dataType;
	}

	public String getGetMethodName() {
		return "get" + StringUtils.capitalize(name);
	}

	public String getIsKey() {
		return isKey;
	}

	public String getIsListShow() {
		return isListShow;
	}

	public String getIsQueryCond() {
		return isQueryCond;
	}

	public String getManageControlType() {
		return manageControlType;
	}

	public String getManageDataType() {
		return manageDataType;
	}

	public String getName() {
		return name;
	}

	public String getQueryControlType() {
		return queryControlType;
	}

	public String getQueryDataType() {
		return queryDataType;
	}

	public String getRequid() {
		return requid;
	}

	public String getSetMethodName() {
		return "set" + StringUtils.capitalize(name);
	}

	/**
	 * 
	 * 方法说明：获取类型
	 * 
	 * @return string
	 */
	public String getType() {
		String flag = StringUtils.upperCase(dataType);
		if (flag.equals("VARCHAR") || flag.equals("VARCHAR2") || flag.equals("NVARCHAR2") || flag.equals("CHAR")) {
			return "String";
		} else if(flag.equals("BLOB")) {
			return "Blob";
		} else if(flag.equals("CLOB")) {
			return "Clob";
		} else if(flag.equals("DATE") || flag.equals("TIMESTAMP")) {
			return "Date";
		} else if(flag.equals("LONG") || flag.equals("INT")) {
			return "Integer";
		} else if(flag.equals("NUMBER")) {
			return "Double";
		} else {
			return "String";
		}
	}

	public String getValideType() {
		return valideType;
	}

	public void setColumeName(String columeName) {
		this.columeName = columeName;
	}

	/**
	 * 
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;

	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}

	public void setIsListShow(String isListShow) {
		this.isListShow = isListShow;
	}

	public void setIsQueryCond(String isQueryCond) {
		this.isQueryCond = isQueryCond;
	}

	public void setManageControlType(String manageControlType) {
		this.manageControlType = manageControlType;
	}

	public void setManageDataType(String manageDataType) {
		this.manageDataType = manageDataType;
	}

	/**
	 * 
	 * 方法说明：设置名字
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		StringBuilder sb = new StringBuilder();
		String[] names = name.split("_");
		for (int i = 0; i < names.length; i++) {
			if (i == 0) {
				sb.append(names[i]);
			} else {
				sb.append(StringUtils.capitalize(names[i]));
			}
		}
		this.name = sb.toString();
	}

	public void setQueryControlType(String queryControlType) {
		this.queryControlType = queryControlType;
	}

	public void setQueryDataType(String queryDataType) {
		this.queryDataType = queryDataType;
	}

	public void setRequid(String requid) {
		this.requid = requid;
	}

	public void setValideType(String valideType) {
		this.valideType = valideType;
	}

}
