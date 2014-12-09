package com.wt.studio.plugin.wizard.projects.model.util;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.core.ICompilationUnit;

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
public class ClassNameInfo
{
	private String entityClassName;
	
	private String moduleName;

	/**
	 * 
	 * @param compilationUnit
	 *            compilationUnit
	 */
	public ClassNameInfo(ICompilationUnit compilationUnit, String moduleName)
	{
		String entityFileName = compilationUnit.getElementName();
		this.entityClassName = entityFileName.substring(0, entityFileName.lastIndexOf("."));// entity类名
		this.moduleName = moduleName;
	}

	/**
	 * 实体类名称
	 * 
	 * @return String
	 */
	public String getEntityClassName()
	{
		return entityClassName;
	}

	/**
	 * 实体对象名称
	 * 
	 * @return string
	 */
	public String getEntityObjectName()
	{
		return StringUtils.uncapitalize(entityClassName);
	}

	/**
	 * dao类名称
	 * 
	 * @return string
	 */
	public String getDaoClassName()
	{
		return entityClassName + "Dao";
	}
	

	/**
	 * dao类实现
	 * 
	 * @return string
	 */
	public String getDaoImplClassName()
	{
		return entityClassName + "DaoImpl";
	}

	/**
	 * 获取模型名称
	 * 
	 * @return string
	 */
	public String getModelClassName()
	{
		return entityClassName;
	}

	/**
	 * dao对象名称
	 * 
	 * @return string
	 */
	public String getDaoObjectName()
	{
		return StringUtils.uncapitalize(entityClassName + "Dao");
	}

	/**
	 * service类名称
	 * 
	 * @return string
	 */
	public String getServicClassName()
	{
		return moduleName + "Service";
	}
	
	/**
	 * service类名称
	 * 
	 * @return string
	 */
	public String getServicImplClassName()
	{
		return moduleName + "ServiceImpl";
	}
	
	/**
	 * manageClass类名称
	 * 
	 * @return string
	 */
	public String getManageClassClassName()
	{
		return moduleName + "MainPage";
	}
	
	/**
	 * updateClass类名称
	 * 
	 * @return string
	 */
	public String getUpdateClassClassName()
	{
		return moduleName + "UpdatePage";
	}
	
	/**
	 * manageHtml名称
	 * 
	 * @return string
	 */
	public String getManageHtmlClassName()
	{
		return moduleName + "MainPage";
	}
	
	/**
	 * updateClass类名称
	 * 
	 * @return string
	 */
	public String getUpdateHtmlClassName()
	{
		return moduleName + "UpdatePage";
	}

	/**
	 * service对象名称
	 * 
	 * @return string
	 */
	public String getServicObjectName()
	{
		return StringUtils.uncapitalize(moduleName + "Service");
	}
	
	public String getM2RestServicClassName()
	{
		return StringUtils.capitalize(moduleName + "RestService");
	}
	
	public String getBOModelClassName()
	{
		return StringUtils.capitalize("BO" + moduleName);
	}	

}
