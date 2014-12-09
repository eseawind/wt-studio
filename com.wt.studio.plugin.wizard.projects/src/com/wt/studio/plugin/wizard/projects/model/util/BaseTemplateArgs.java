package com.wt.studio.plugin.wizard.projects.model.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板参数基类.
 * 
 * @author
 * 
 */
public class BaseTemplateArgs
{
	private PackageNameInfo packageNameInfo;
	private ClassNameInfo classNameInfo;
	List<EntityFieldInfo> entityFieldInfoList = new ArrayList<EntityFieldInfo>();

	public PackageNameInfo getPackageNameInfo()
	{
		return packageNameInfo;
	}

	public void setPackageNameInfo(PackageNameInfo packageNameInfo)
	{
		this.packageNameInfo = packageNameInfo;
	}

	public ClassNameInfo getClassNameInfo()
	{
		return classNameInfo;
	}

	public void setClassNameInfo(ClassNameInfo classNameInfo)
	{
		this.classNameInfo = classNameInfo;
	}

	public List<EntityFieldInfo> getEntityFieldInfoList()
	{
		return entityFieldInfoList;
	}

	public void setEntityFieldInfoList(List<EntityFieldInfo> entityFieldInfoList)
	{
		this.entityFieldInfoList = entityFieldInfoList;
	}

}
