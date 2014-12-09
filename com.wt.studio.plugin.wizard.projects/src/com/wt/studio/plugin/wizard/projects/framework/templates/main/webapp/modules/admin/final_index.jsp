<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理平台</title>
<link id="themePath" href="${pageContext.request.contextPath }/css/theme/blue/console.css" rel="stylesheet" type="text/css" />

<!-- ajax树状结构所需文件 -->
<link   href="${pageContext.request.contextPath}/css/component/dhtmlxtree.css" rel="stylesheet" type="text/css" >
<script src="${pageContext.request.contextPath}/script/component/dhtmlxcommon.js"></script>
<script src="${pageContext.request.contextPath}/script/component/dhtmlxtree.js"></script>
<!-- ajax树状结构所需文件 End:-->


<!-- tab页 -->
<link href="${pageContext.request.contextPath }/css/component/jquery.jerichotab.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath }/script/common/jquery-1.4.2.min.js"  type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/script/component/jquery.jerichotab.js" type="text/javascript" ></script>
<!-- tab页 End: -->

<!-- 自己定义js -->
<script type="text/javascript">
function changeTheme(color){
	var cssVar =document.getElementById('themePath') ;
	cssVar.href = "${pageContext.request.contextPath}"+"/css/theme/"+"green"+"/console.css";
}

function innitil(){
	<c:if test="${multiGroup eq true}">
		changDialogURL("${pageContext.request.contextPath}/heaUserAction.hea?action=userGroup");
	</c:if>
	<c:if test="${empty multiGroup}">
		loadConsoleTree();
	</c:if>
}
</script>

<!-- 自定义js End: -->
<jsp:include page="../../script/admin/final_index_js.jsp">
	<jsp:param value="5" name="indexType"/>
</jsp:include>
</head>
<body onload="innitil();">
<center>
<div class="container">
  <!-- 头部开始 -->
  <div class="header">
  	 <div class="logo"></div>
	  <div class="banner">
		<div class="topnav"><a href="${pageContext.request.contextPath}/heaUserAction.hea?action=logout&target=http://localhost:8080/hea/adminlogin.jsp"><bean:message key="ui.loginout" /></a></div>	
		<div class="topnav"><a href="${pageContext.request.contextPath}/adminlogin.jsp"><bean:message key="ui.sysmanage" /></a></div>
	  	<div class="topnav"><a href="javascript:changeTheme('red')"><bean:message key="ui.red"/><bean:message key="ui.theme"/></a></div>
		<div class="topnav"><a href="javascript:changeTheme('blue')"><bean:message key="ui.blue"/><bean:message key="ui.theme"/></a></div>
	  </div>  
  </div>
  <!-- 头部结束 End; -->
  <div class="mainContent">
    	<div  class="siderbar" >
	    	<div class="login">
				<div class="login_content"><bean:message key="ui.welcome" arg0="${user.name}"/></div>
			 </div>
			  <div class="siderbar_menu" id="sidebar"></div> 
    	</div>
    	<div class="drawback"><div class="drawback_icon"></div></div>
    <div id="rightContent" class="content_1"></div>
  </div>
  <div class="footer">此处显示footer的内容</div>
</div>
<%@include file="../rbac/include/dialogWindow.jsp" %>
</center>
</body>
</html>