package com.wt.studio.plugin.wizard.projects.services.util;

import java.util.List;

import com.wt.studio.plugin.wizard.projects.services.components.ComponentType;

public class HTML5Model {

	private String id;//标识
	private int level;//层级
	private String layout;
	private ComponentType type;//类型
	private String fielddesc; //字段描述
	private String fieldControl; //字段控件
	private String fieldDateSrouce; //字段数据源
	private String parentId; //父ID
	private List<HTML5Model> models; //子集
	private String pname; //项目名称
	private String mname; //模块名称

	public String getFieldControl() {
		return fieldControl;
	}

	public String getFieldDateSrouce() {
		return fieldDateSrouce;
	}

	public String getFielddesc() {
		return fielddesc;
	}

	public String getId() {
		return id;
	}

	public String getLayout() {
		return layout;
	}

	public int getLevel() {
		return level;
	}

	public String getMname() {
		return mname;
	}

	public List<HTML5Model> getModels() {
		return models;
	}

	public String getParentId() {
		return parentId;
	}

	public String getPname() {
		return pname;
	}

	public ComponentType getType() {
		return type;
	}

	public void setFieldControl(String fieldControl) {
		this.fieldControl = fieldControl;
	}

	public void setFieldDateSrouce(String fieldDateSrouce) {
		this.fieldDateSrouce = fieldDateSrouce;
	}

	public void setFielddesc(String fielddesc) {
		this.fielddesc = fielddesc;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public void setModels(List<HTML5Model> models) {
		this.models = models;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setType(String type) {
		for (ComponentType t : ComponentType.values()) {
			if (t.toString().equals(type)) {
				this.type = t;
			}
		}
	}

}
