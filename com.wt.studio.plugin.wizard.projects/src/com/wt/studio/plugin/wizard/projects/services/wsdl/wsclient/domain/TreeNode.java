package com.wt.studio.plugin.wizard.projects.services.wsdl.wsclient.domain;

import java.util.LinkedList;

public class TreeNode {

	String data;
	TreeNode parent;
	LinkedList<TreeNode> childlist;
	
	TreeNode()
	{
		data = null;
		childlist = new LinkedList<TreeNode>();
		parent = null;
	}
	
	TreeNode(TreeNode parent,String data,LinkedList<TreeNode> childlist)
	{
		this.data = data;
		this.childlist = childlist;
		this.parent = parent;
	}
	
	public static void main(String[] args) {
		TreeNode t = new TreeNode();
		t.data="parent";
		t.childlist = new LinkedList<TreeNode>();
		t.childlist.add(new TreeNode(t,"child1",new LinkedList<TreeNode>()));
		TreeNode child2 = new TreeNode(t,"child2",new LinkedList<TreeNode>());
		child2.addChild(new TreeNode(child2,"children",new LinkedList<TreeNode>()));
		t.childlist.add(child2);
		TreeNode.displaytree(t, 0);
	}
	
	private void addChild(TreeNode treeNode) {
		if(childlist==null){
			childlist = new LinkedList<TreeNode>();
		}
		childlist.add(treeNode);
	}

	private static void displaytree(TreeNode f, int level) {       //递归显示树
		  
		  String preStr = "";
		  for(int i=0; i<level; i++) {
		   preStr += "    ";
		  }
	
		  for(int i=0; i<f.childlist.size(); i++) {
			  TreeNode t = f.childlist.get(i);
			  System.out.println(preStr + "-"+t.data);
		   
		   if(! t.childlist.isEmpty()) {
			   displaytree(t, level + 1);
		   }
		  }
	 }
}