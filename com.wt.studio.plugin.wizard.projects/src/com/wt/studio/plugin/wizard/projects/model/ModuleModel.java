package com.wt.studio.plugin.wizard.projects.model;

import java.util.List;

import com.wt.studio.plugin.wizard.projects.dbhelp.ColumeModel;
import com.wt.studio.plugin.wizard.projects.dbhelp.TableModel;

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
public class ModuleModel {
	/**
	 * 
	 */
	private String MOVERSION = "1.0.0";

	private String projectName;

	private String moduleName;

	private String modalName;

	private String packageName;

	private String moduleDesc;

	private TableModel tableModel;

	private List<ColumeModel> columeModels;

	public List<ColumeModel> getColumeModels() {
		return columeModels;
	}

	public String getModalName() {
		return modalName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getProjectName() {
		return projectName;
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setColumeModels(List<ColumeModel> columeModels) {
		this.columeModels = columeModels;
	}

	public void setModalName(String modalName) {
		this.modalName = modalName;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getMOVERSION() {
		return MOVERSION;
	}

	public void setMOVERSION(String mOVERSION) {

	}

}
