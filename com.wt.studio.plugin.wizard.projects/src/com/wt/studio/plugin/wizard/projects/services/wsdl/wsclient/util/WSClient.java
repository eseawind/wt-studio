package com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain.ComplexObjectInfo;
import com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain.MessageInfo;
import com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain.OperationInfo;
import com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain.ParameterInfo;
import com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain.ServiceInfo;

public class WSClient {

	private String url = "";

	private static WSClient ws;
	
	public static void displaytree(MessageInfo f, int level) {       //递归显示树
		  
		  String preStr = "";
		  for(int i=0; i<level; i++) {
		   preStr += "    ";
		  }
	
		  for(int i=0; i<f.getChilds().size(); i++) {
			  MessageInfo t = f.getChilds().get(i);
			  if(t==null){
				  continue;
			  }
			  System.out.println(preStr + "-"+t.getName()+"-"+t.getFiledType()+"-"+t.getType());
		   
		   if(t.getChilds()!=null&&!t.getChilds().isEmpty()) {
			   displaytree(t, level + 1);
		   }
		  }
	 }
	
	public static synchronized WSClient getInstance(){
		if(ws==null){
			ws = new WSClient();
		}
		
		return ws;
	}
	
	public WSClient setURL(String url){
		this.url=url;
		return this;
	}
	
	public void fire(MessageInfo message){
		try {
			ComponentBuilder builder = new ComponentBuilder();
			ServiceInfo serviceInfo = new ServiceInfo();
			serviceInfo.setWsdllocation(url);
			serviceInfo = builder.buildserviceinformation(serviceInfo);
			message.setServiceName(serviceInfo.getName());
			//System.out.println("");
			Iterator iter = serviceInfo.getOperations();
			//System.out.println("现在可以查看远端Web服务对象的有关情况了(对应本地Web服务类,ServiceInfo)");
			//System.out.println(serviceInfo.getName() + "提供的操作有:");
			Map<String,MessageInfo> map = new HashMap<String,MessageInfo>();
			if(builder.getList()!=null&&!builder.getList().isEmpty()){
				//System.out.println("对象打印:");
				for (ComplexObjectInfo object : builder.getList()) {
					MessageInfo msg = new MessageInfo();
					msg.setComlex(true);
					msg.setType(MessageInfo.Type.complexObj);
					msg.setFiledType(object.getKind());
					msg.setName(object.getName());
					msg.setServiceName(serviceInfo.getName());
					map.put(object.getName(), msg);
					//System.out.println("对象打印:" + object.getName());
					//System.out.println(object.getName());
					for (ParameterInfo parame : object.getFileds()) {
						MessageInfo child = new MessageInfo();
						child.setComlex(isComlexObject(parame.getKind()));
						child.setFiledType(parame.getKind());
						child.setName(parame.getName());
						child.setParent(msg);
						child.setServiceName(serviceInfo.getName());
						child.setType(MessageInfo.Type.filed);
						msg.addChilds(child);
						
						//System.out.println(parame.getName());
						//System.out.println(parame.getKind());
						//System.out.println("变量名:" + parame.getName());
						//System.out.println("类型为:" + parame.getKind());
					}
					//System.out.println();
				}
			}
			while (iter.hasNext()) {
				OperationInfo oper = (OperationInfo) iter.next();
				
				MessageInfo msg = new MessageInfo();
				msg.setParent(message);
				msg.setType(MessageInfo.Type.operation);
				msg.setServiceName(serviceInfo.getName());
				msg.setName(oper.getTargetMethodName());
				
				message.addChilds(msg);
				//System.out.println("");
				//System.out.println("操作:" + i + " " + oper.getTargetMethodName());
				List inps = oper.getInparameters();
				List outps = oper.getOutparameters();
				if (inps.size() == 0) {
					//System.out.println("此操作所需的输入参数为:");
					//System.out.println("执行此操作不需要输入任何参数!");
				} else {
					//System.out.println("此操作所需的输入参数为:");
					for (Iterator iterator1 = inps.iterator(); iterator1.hasNext();) {
						ParameterInfo element = (ParameterInfo) iterator1.next();
						
						MessageInfo in = new MessageInfo();
						in.setServiceName(serviceInfo.getName());
						in.setParent(msg);

						in.setType(MessageInfo.Type.filed);
						in.setOutput(false);
						in.setName(element.getName());
						in.setFiledType(element.getKind());
						
						if(isComlexObject(element.getKind())){
							in.addChilds(map.get(element.getKind()));
						}
						
						msg.addChilds(in);
						
						//System.out.println("参数名为:" + element.getName());
						//System.out.println("参数类型为:" + element.getKind());
					}
				}
				if (outps.size() == 0) {
					//System.out.println("执行此操作不返回任何参数!");
				} else {
					//System.out.println("此操作的输出参数为:");
					for (Iterator iterator2 = outps.iterator(); iterator2.hasNext();) {
						ParameterInfo element = (ParameterInfo) iterator2.next();

						MessageInfo out = new MessageInfo();
						out.setServiceName(serviceInfo.getName());
						out.setParent(msg);

						out.setType(MessageInfo.Type.filed);
						out.setOutput(true);
						out.setName(element.getName());
						out.setFiledType(element.getKind());
						
						if(isComlexObject(element.getKind())){
							out.addChilds(map.get(element.getKind()));
						}
						
						msg.addChilds(out);
						
						//System.out.println("参数名:" + element.getName());
						//System.out.println("类型为:" + element.getKind());
					}
				}
				//System.out.println("");
			}

		}

		catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private boolean isComlexObject(String kind) {
		String[] type = new String[]{"int","string","long"};
		if(kind==null||kind.length()==0){
			return false;
		}
		for (String string : type) {
			if(string.equals(kind)){
				return false;
			}
		}
		return true;

	}
	
}
