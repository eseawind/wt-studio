<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'sidebar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>My First Grid</title>
<link rel="stylesheet" type="text/css" media="screen"
href="${ctx}/view_ref/jqgrid/css/redmond/jquery-ui-1.7.2.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
href="${ctx}/view_ref/jqgrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen"
href="${ctx}/view_ref/css/qt.css" />
<style>
html, body {
    margin: 0;
    padding: 0;
    font-size: 75%;
}

</style>
<script src="${ctx}/view_ref/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${ctx}/view_ref/jqgrid/js/jquery-ui-1.7.2.custom.min.js" type="text/javascript"></script>
<script src="${ctx}/view_ref/jqgrid/js/src/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${ctx}/view_ref/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script>
function switchDiv(rowid){
  			document.getElementById("userInfoDiv").style.display="block";
  			document.getElementById("returnBotton").style.display="block";
  			document.getElementById("userListMain").style.display="none";	
  			window.open("${ctx}/ulUser.do?method=toUserInfo&uuuid="+rowid,"userInfoIframe");	
 }
	<%
		//返回按钮
	%>
	function returnTop(){
    		document.getElementById("returnBotton").style.display="none";
    		document.getElementById("userInfoDiv").style.display="none";
    		document.getElementById("userListMain").style.display="block";
    		document.getElementById("btnAddUser").style.display="block";	
    	}

	function toAddUser(){
		document.getElementById("userInfoDiv").style.display="block";
			document.getElementById("returnBotton").style.display="block";
			document.getElementById("userListMain").style.display="none";	
			document.getElementById("btnAddUser").style.display="none";	
			window.open("${ctx}/ulUser.do?method=toAddUser","userInfoIframe");	
	
	}
jQuery(document).ready(function(){ 
	jQuery("#list").jqGrid({
		url:'ulUser.do?method=doUserList',
		datatype: "json",
		mtype: 'POST',
		colModel:[
		{name:'id',index:'id',label:'用户ID', width:150,sorttype:'string'},
		{name:'name',index:'name',label:'姓名',width:150, align:'center', sorttype:"int"},
		{name:'status',index:'status',label:'状态', width:80, align:'center',sorttype:'int'},
		{name:'department',index:'department',label:'直属部门', width:200, align:"center"},
		{name:'groups',index:'groups',label:'权限组', width:300}
		<c:forEach items="${requestScope.tableConfigList}" var="item" >
			,{name:'element${item.ordernum}',index:'element${item.ordernum}',label:'${item.elementname}', width:200,align:'center',sortable:false}
		</c:forEach>
		],
		height:350,
		width:1000,
		ondblClickRow:function(rowid){
			switchDiv(rowid);
		},
		jsonReader : {
	         root: "rows",
	         page: "page",
	         total: "total",
	         records: "records",
	         repeatitems: false,
	         userdata: "userdata",
	         id: "0"
	     },
	    rowNum:10,
	    rowList:[10,20,30],
	    pager: '#pager',
	    sortname: 'id',
	     viewrecords: true,
	     sortorder: "desc", 
	     caption:"用户信息"
   	}); 
  jQuery("#list").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});
  jQuery("#list").jqGrid('navButtonAdd','#pager',{
    caption: "列筛选",
    title: "Reorder Columns",
    onClickButton : function (){
        jQuery("#list").jqGrid('columnChooser');
    }
});
  
  
}); 

</script>

</head>
<body >
<div class="headpath">
<logic:iterate id="p" name="departmentPath" scope="session">
>>${p.name}
</logic:iterate>
<input  type="button" id="btnAddUser" onclick="toAddUser()" value="增加用户" style="position: absolute;right:80px;top: 0px;border-style:ridge; border-width:1px;height: 18px;" >
<input id="returnBotton" type="button" onclick="returnTop()" value="返回" style="position: absolute;right:20px;top: 0px;border-style:ridge;display:none; border-width:1px;height: 18px;" >
</div>
<br/>
<div id="userListMain"> 
<table id="list"></table> 
<div id="pager"></div>
</div>
<div id="userInfoDiv" style="position: absolute;width:100%;height:95%; left: 0px;top: 20px;display: none;">
   		<iframe width="100%" height="100%" name="userInfoIframe" frameborder="0"></iframe>
</div>
</body>
	
</html>
