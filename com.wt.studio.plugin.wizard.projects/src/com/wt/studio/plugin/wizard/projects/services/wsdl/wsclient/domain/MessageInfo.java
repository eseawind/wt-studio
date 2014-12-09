package com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain;

import java.util.ArrayList;
import java.util.List;

public class MessageInfo {
	
	public enum Type{
		operation,filed,complexObj
	}
	
	private Type type = Type.operation;
	
	private boolean comlex;
	
	private boolean output;
	
	private String name;
	
	private String filedType;
	
	public String getFiledType() {
		return filedType;
	}

	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOutput() {
		return output;
	}

	public void setOutput(boolean output) {
		this.output = output;
	}

	public boolean isComlex() {
		return comlex;
	}

	public void setComlex(boolean comlex) {
		this.comlex = comlex;
	}

	private String serviceName = new String();
	
	private MessageInfo parent;
	
	private List<MessageInfo> childs;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public MessageInfo getParent() {
		return parent;
	}

	public void setParent(MessageInfo parent) {
		this.parent = parent;
	}

	public List<MessageInfo> getChilds() {
		return childs;
	}

	public void setChilds(List<MessageInfo> childs) {
		this.childs = childs;
	}

	public void addChilds(MessageInfo chlid) {
		if(this.childs==null){
			this.childs = new ArrayList<MessageInfo>();
		}
		this.childs.add(chlid);
	}
}
