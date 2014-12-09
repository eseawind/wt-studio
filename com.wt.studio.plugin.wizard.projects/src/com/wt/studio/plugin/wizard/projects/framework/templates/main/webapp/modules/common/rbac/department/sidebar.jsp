<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'department/sidebar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/component/d_tree/jsframework.js"></script>
  </head>
  
  <body>
  	<bean:size id="dsize" name="departments" />
   	<script type="text/javascript">
	 
	 var data={};
	 <c:if test="${dsize >0}">
	 	data["-1_${departments[0].uuid}"] = "text:${departments[0].name};url:heaDepartmentAction.hea?action=showUser&departmentuuid=${departments[0].uuid};target:contentWindow;";
	 	var rootid="${departments[0].uuid}";
	</c:if>
<c:forEach begin="1" end="${dsize-1}" items="${departments}" var="pitem">
	 data['${pitem. parentuuid}_${pitem.uuid}'] = "text: ${pitem.name };url:heaDepartmentAction.hea?action=showUser&departmentuuid=${pitem.uuid};target:contentWindow;";
</c:forEach>
   

        //*
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
       // alert(rootid);
       // a.autoSort=true;
        a.rootId=rootid;
        a.autoSort=false;
       // a.useCheckbox=true
        a.canOperate=true;
        document.write(a.render());
        a.expandLevel(1);
</script>
  </body>
  
</html>
