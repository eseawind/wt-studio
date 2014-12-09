package com.wt.studio.plugin.wizard.projects.model.util;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.core.IPackageFragment;

/**
 * 包信息
 * 
 * @author
 * 
 */
public class PackageNameInfo {
	private String entityPackageName;// entity包名
	private String projectName;// 名
	private String modeName;// 名

	/**
	 * 构造方法
	 * 
	 * @param packageFragment
	 *            packageFragment
	 */
	public PackageNameInfo(IPackageFragment packageFragment, String modeName) {
		this.entityPackageName = packageFragment.getElementName();
		String basePackageName = entityPackageName.substring(0,
				entityPackageName.lastIndexOf("."));
		this.projectName = basePackageName.substring(
				basePackageName.lastIndexOf(".") + 1, basePackageName.length());// 模块名
		this.modeName = modeName;
	}

	/**
	 * 获取实体类包名
	 * 
	 * @return string
	 */
	public String getEntityPackageName() {
		return entityPackageName;
	}

	/**
	 * 获取model包名
	 * 
	 * @return string
	 */
	public String getModelPackageName() {
		return getPackageName(entityPackageName, "model");
	}

	/**
	 * 获取dao包名
	 * 
	 * @return string
	 */
	public String getDaoPackageName() {
		return getPackageName(entityPackageName, "dao");
	}

	/**
	 * 获取daoImpl包名
	 * 
	 * @return string
	 */
	public String getDaoImplPackageName() {
		return getPackageName(entityPackageName, "dao.impl");
	}

	/**
	 * 获取service包名
	 * 
	 * @return string
	 */
	public String getServicPackageName() {
		return getPackageName(entityPackageName, "service");
	}

	/**
	 * 获取serviceImpl包名
	 * 
	 * @return string
	 */
	public String getServicImplPackageName() {
		return getPackageName(entityPackageName, "service.impl");
	}

	/**
	 * 获取web类包名
	 * 
	 * @return string
	 */
	public String getWebClassPackageName() {
		return getPackageName(entityPackageName, "web." + StringUtils.lowerCase(modeName));
	}

	/**
	 * 获取web页面包名
	 * 
	 * @return string
	 */
	public String getWebHtmlPackageName() {
		return getPackageName(entityPackageName, "web." + StringUtils.lowerCase(modeName));
	}
	
	/**
	 * 获取RestService包名
	 * @return
	 */
	public String getM2RestPackageName() {
		return getPackageName(entityPackageName, "rest");
	}
	
	/**
	 * 获取RestService包名
	 * @return
	 */
	public String getBOModelPackageName() {
		return getPackageName(entityPackageName, "bo");
	}	

	/**
	 * 获取模块名
	 * 
	 * @return string
	 */
	public String getModeName() {
		return modeName;
	}

	/**
	 * 取得包名
	 * 
	 * @param entityPackageName
	 *            entityPackageName
	 * @param type
	 *            type
	 * @return string
	 */
	private String getPackageName(String entityPackageName, String type) {
		if (entityPackageName.indexOf(".") != -1) {
			int index = entityPackageName.lastIndexOf(".");
			return entityPackageName.substring(0, index) + "." + type;
		} else {
			return type;
		}
	}

}
