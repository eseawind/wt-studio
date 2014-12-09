package com.wt.studio.plugin.wizard.projects.model.util;

/**
 * java代码生成模板参数
 * 
 * @author
 * 
 */
public class JavaTemplateArgs extends BaseTemplateArgs
{
	private String packageName;// 包名
	private String className;// 类名
	private JavaTemplateType type;// 模板类型

	public String getModeName()
	{
		return getPackageNameInfo().getModeName();
	}

	/**
	 * 
	 * 方法说明：继承方法
	 * 
	 * @return string
	 */
	public String getPackageName()
	{
		switch (type) {
		case MODEL:
			packageName = getPackageNameInfo().getModelPackageName();
			break;
		case DAO:
			packageName = getPackageNameInfo().getDaoPackageName();
			break;
		case DAOIMPL:
			packageName = getPackageNameInfo().getDaoImplPackageName();
			break;
		case SERVICE:
			packageName = getPackageNameInfo().getServicPackageName();
			break;
		case SERVICEIMPL:
			packageName = getPackageNameInfo().getServicImplPackageName();
			break;
		case MANAGECLASS:
			packageName = getPackageNameInfo().getWebClassPackageName();
			break;
		case UPDATECLASS:
			packageName = getPackageNameInfo().getWebClassPackageName();
			break;
		case MANAGEHTML:
			packageName = getPackageNameInfo().getWebHtmlPackageName();
			break;
		case UPDATEHTML:
			packageName = getPackageNameInfo().getWebHtmlPackageName();
			break;
		case M2RestService:
			packageName = getPackageNameInfo().getM2RestPackageName();
			break;
		case BOModel:
			packageName = getPackageNameInfo().getBOModelPackageName();
			break;
		}
		return packageName;
	}

	/**
	 * 
	 * 方法说明：继承方法
	 * 
	 * @return string
	 */
	public String getClassName()
	{
		switch (type) {
		case MODEL:
			className = getClassNameInfo().getModelClassName();
			break;
		case DAO:
			className = getClassNameInfo().getDaoClassName();
			break;
		case DAOIMPL:
			className = getClassNameInfo().getDaoImplClassName();
			break;
		case SERVICE:
			className = getClassNameInfo().getServicClassName();
			break;
		case SERVICEIMPL:
			className = getClassNameInfo().getServicImplClassName();
			break;
		case MANAGECLASS:
			className = getClassNameInfo().getManageClassClassName();
			break;
		case UPDATECLASS:
			className = getClassNameInfo().getUpdateClassClassName();
			break;
		case MANAGEHTML:
			className = getClassNameInfo().getManageHtmlClassName();
			break;
		case UPDATEHTML:
			className = getClassNameInfo().getUpdateHtmlClassName();
			break;
		case M2RestService:
			className = getClassNameInfo().getM2RestServicClassName();
			break;
		case BOModel:
			className = getClassNameInfo().getBOModelClassName();
			break;
		}
		return className;
	}

	public JavaTemplateType getType()
	{
		return type;
	}

	public void setType(JavaTemplateType type)
	{
		this.type = type;
	}

	/**
	 * 
	 * <pre>
	 * 业务名:继承类
	 * 功能说明:继承类
	 * 编写日期:	2013-1-7
	 * 作者:	Administrator
	 * 
	 * 历史记录
	 * 1、修改日期：
	 *    修改人：
	 *    修改内容：
	 * </pre>
	 */
	public enum JavaTemplateType
	{
		MODEL, DAO, SERVICE, DAOIMPL, SERVICEIMPL, MANAGECLASS, UPDATECLASS, MANAGEHTML, UPDATEHTML,
		BOModel,M2RestService, M2HTML5, BOService, BOServiceIMPL
	}
}
