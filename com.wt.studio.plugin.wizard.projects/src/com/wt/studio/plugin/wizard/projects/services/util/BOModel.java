package com.wt.studio.plugin.wizard.projects.services.util;

import java.util.List;

import com.wt.studio.plugin.wizard.projects.services.bomodel.ModelType;

public class BOModel {
	private ModelType type;

	/**
	 * BOModel 类型
	 */
	private String bontype;

	/**
	 * BOModel 名称
	 */
	private String boname;

	/**
	 * Model子集
	 */
	private List<BOModel> models;

	/**
	 * 源对象
	 */
	private String sourceObject;

	/**
	 * 源字段
	 */
	private String sourceField;

	public BOModel() {
		super();
	}

	public BOModel(ModelType type, String bontype, String boname,
			List<BOModel> models, String sourceObject, String sourceField) {
		super();
		this.type = type;
		this.bontype = bontype;
		this.boname = boname;
		this.models = models;
		this.sourceObject = sourceObject;
		this.sourceField = sourceField;
	}

	public ModelType getType() {
		return type;
	}

	public void setType(String type) {
		for (ModelType t : ModelType.values()) {
			if (t.toString().equals(type)) {
				this.type = t;
			}
		}
	}

	public String getBontype() {
		return bontype;
	}

	public void setBontype(String bontype) {
		this.bontype = bontype;
	}

	public String getBoname() {
		return boname;
	}

	public void setBoname(String boname) {
		this.boname = boname;
	}

	public List<BOModel> getModels() {
		return models;
	}

	public void setModels(List<BOModel> models) {
		this.models = models;
	}

	public String getSourceObject() {
		return sourceObject;
	}

	public void setSourceObject(String sourceObject) {
		this.sourceObject = sourceObject;
	}

	public String getSourceField() {
		return sourceField;
	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}

}
