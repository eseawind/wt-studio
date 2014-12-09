package com.wt.studio.plugin.platform.util;

/**
 * 
 * <pre>
 * 业务名: model类
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
public class RefModel
{
	private String jndiId;
	private String jdbcId;
	private String jdbcUrl;
	private String defRef;
	private String user;
	private String pwd;
	private String ref;
	
	public String getJndiId()
	{
		return jndiId;
	}
	public void setJndiId(String jndiId)
	{
		this.jndiId = jndiId;
	}
	public String getJdbcId()
	{
		return jdbcId;
	}
	public void setJdbcId(String jdbcId)
	{
		this.jdbcId = jdbcId;
	}
	public String getJdbcUrl()
	{
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl)
	{
		this.jdbcUrl = jdbcUrl;
	}
	public String getDefRef()
	{
		return defRef;
	}
	public void setDefRef(String defRef)
	{
		this.defRef = defRef;
	}
	public String getUser()
	{
		return user;
	}
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getPwd()
	{
		return pwd;
	}
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	public String getRef()
	{
		return ref;
	}
	public void setRef(String ref)
	{
		this.ref = ref;
	}
}