<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'rbac/department/indexsUnderGroup.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/component/d_tree/jsframework.js"></script>

  </head>
  
  <body>
  <span style="font-size: 13;text-decoration:none;color: green;margin: 5;">[${group.groupname }]下的指标有:</span>
   	<script type="text/javascript">
	 <bean:size id="dsize" name="indexs" />
	 var data={};
	 <c:if test="${dsize >0}">
	 	data["-1_${indexs[0].indexuuid}"] = "text:${indexs[0].indexname};";
	 	var rootid="${ indexs[0].indexuuid }";
	</c:if>
	<c:forEach begin="1" end="${dsize-1}" items="${indexs}" var="it">
		 data['${it.parentindexuuid}_${it.indexuuid}'] = "text: ${it.indexname};";
	</c:forEach>
   

        //*
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
       // alert(rootid);
       // a.autoSort=true;
        a.rootId=rootid;
        a.autoSort=false;
        a.canOperate=true;
        document.write(a.render());
        a.expandLevel(1);
</script>
  </body>
  
</html>
