package com.wt.studio.plugin.querydesigner.utils;

public enum DataBaseType
{
	ORACLE("org.eclipse.datatools.enablement.oracle.connectionProfile"), MYSQL(
			"org.eclipse.datatools.enablement.mysql.connectionProfile");
	private String providerID;

	private DataBaseType(String providerID)
	{
		this.providerID = providerID;
	}

	public String getProviderID()
	{
		return providerID;
	}

	public void setProviderID(String providerID)
	{
		this.providerID = providerID;
	}
}
