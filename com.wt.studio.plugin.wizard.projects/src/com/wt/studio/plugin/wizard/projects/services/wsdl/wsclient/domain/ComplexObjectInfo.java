package com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain;

import java.util.ArrayList;
import java.util.List;

public class ComplexObjectInfo implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9024896505622619859L;
	private String name;//参数名
	private String kind;//参数类型
	private int id;//参数标识 
	private String serviceid;//服务id
	private List<ParameterInfo> fileds;//属性
	
	public List<ParameterInfo> getFileds() {
		return fileds;
	}
	public void setFileds(List<ParameterInfo> fileds) {
		this.fileds = fileds;
	}
	public void addFiled(ParameterInfo parameterInfo){
		if(fileds==null){
			fileds = new ArrayList<ParameterInfo>();
		}
		fileds.add(parameterInfo);
	}
	public String getServiceid() {
		return serviceid;
	}
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String name2) {
		this.kind = name2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ComplexObjectInfo)) {
			return false;
		}
		ComplexObjectInfo other = (ComplexObjectInfo) obj;
		if (name == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!name.equals(other.getName())) {
			return false;
		}
		return true;
	}

}
