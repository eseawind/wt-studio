<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'qxfpdtree.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
		function loadSiderbar(obj){
				window.open("${pageContext.request.contextPath}/heaGroupAction.hea?action=toAuthorizeTree&groupuuid=${param.groupuuid}&global=${global}","_self");
		}
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/d_tree/jsframework.js"></script>

		<style type="text/css">
body {
	padding: 0;
	margin: 0;
	width: 100%;
	height: 100%;
}
</style>
  </head>
  
  <body>
   		<script type="text/javascript">
   		var rootid='';
	 <bean:size id="dsize" name="departments" />
	 var data={};
	 <c:if test="${dsize >0}">
	 	data["-1_${departments[0].uuid}"] = "text:${departments[0].name};url:heaGroupAction.hea?action=departmentUsers&departmentuuid=${departments[0].uuid}&groupuuid=${groupuuid}&rootduuid=${departments[0].uuid};target:userList;";
	 rootid="${departments[0].uuid}";
	
  <c:forEach begin="1" end="${dsize-1}" items="${departments}" var="pitem">
	 data['${pitem. parentuuid}_${pitem.uuid}'] = "text: ${pitem.name };url:heaGroupAction.hea?action=departmentUsers&departmentuuid=${pitem.uuid}&groupuuid=${groupuuid}&rootduuid=${departments[0].uuid};target:userList;";
</c:forEach>
</c:if>
   

        //*
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
        a.rootId=rootid;
        a.autoSort=false;
       // a.useCheckbox=true
        a.canOperate=true;
        document.write(a.render());
        a.expandLevel(1);
        </script>
        	<div style="width: 50px;height: 30px;top: 10px;right: 2px;position: absolute;" >
        	<%
			/*<logic:notEmpty name="admin" scope="session">
				<input type="button" value="全局" style="border-style:ridge;	border-width:1px;height: 18px;" onclick="loadSiderbar(this)"/>
			</logic:notEmpty>*/
			 %>	
		</div>
  </body>
  <script type="text/javascript">
  	window.open("heaGroupAction.hea?action=departmentUsers&departmentuuid=${departments[0].uuid}&groupuuid=${groupuuid}","userList");
  </script>
</html>
