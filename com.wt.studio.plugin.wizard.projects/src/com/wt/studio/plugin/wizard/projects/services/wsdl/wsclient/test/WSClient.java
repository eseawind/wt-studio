package com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.test;

import com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain.MessageInfo;

public class WSClient {

	private static MessageInfo message = new MessageInfo();

	private static void displaytree(MessageInfo f, int level) { // 递归显示树

		String preStr = "";
		for (int i = 0; i < level; i++) {
			preStr += "    ";
		}

		for (int i = 0; i < f.getChilds().size(); i++) {
			MessageInfo t = f.getChilds().get(i);
			if (t == null) {
				continue;
			}
			System.out.println(preStr + "-" + t.getName() + "-"
					+ t.getFiledType() + "-" + t.getType());

			if (t.getChilds() != null && !t.getChilds().isEmpty()) {
				displaytree(t, level + 1);
			}
		}
	}

	public static void main(String[] args) {
		com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.util.WSClient
				.getInstance()
				.setURL("http://192.168.37.55:8081/test1/services/Test?wsdl")
				.fire(message);

		WSClient.displaytree(message, 0);

		// if(!message.getOperations().isEmpty()){
		// //打印方法
		// for (OperationInfo oper : message.getOperations()) {
		// System.out.println("操作:" + oper.getTargetMethodName());
		// List inps = oper.getInparameters();
		// List outps = oper.getOutparameters();
		// if (inps.size() == 0) {
		// System.out.println("此操作所需的输入参数为:");
		// System.out.println("执行此操作不需要输入任何参数!");
		// } else {
		// System.out.println("此操作所需的输入参数为:");
		// for (Iterator iterator1 = inps.iterator(); iterator1.hasNext();) {
		// ParameterInfo element = (ParameterInfo) iterator1.next();
		// System.out.println("参数名为:" + element.getName());
		// System.out.println("参数类型为:" + element.getKind());
		// }
		// }
		// if (outps.size() == 0) {
		// System.out.println("执行此操作不返回任何参数!");
		// } else {
		// System.out.println("此操作的输出参数为:");
		// for (Iterator iterator2 = outps.iterator(); iterator2.hasNext();) {
		// ParameterInfo element = (ParameterInfo) iterator2.next();
		// System.out.println("参数名:" + element.getName());
		// System.out.println("类型为:" + element.getKind());
		// }
		// }
		// }
		// }
		// System.out.println();
		// System.out.println("对象打印分隔线~~~~~~~~~~~~~~~~~~~~~~~~");
		// System.out.println();
		// if(!message.getComplexObjs().isEmpty()){
		// for (ComplexObjectInfo object : message.getComplexObjs()) {
		// System.out.println("对象打印:" + object.getName());
		// for (ParameterInfo parame : object.getFileds()) {
		// System.out.println("变量名:" + parame.getName());
		// System.out.println("类型为:" + parame.getKind());
		// }
		// }
		//
		// }
	}

	public WSClient() {

	}

}
