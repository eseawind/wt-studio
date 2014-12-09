<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>veiw/jsp/admin/adminIndex.jsp</title>
<link href="${pageContext.request.contextPath}/view_ref/rbac/css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".sidebarmenu1").bind('click',function(){
			$(this).next().slideToggle("slow");
		});
		$("#nav").bind("click",function(){
			$("#header").slideToggle("slow");
		});
		
	});
</script>

</head>

<body>
<div id="header">
	<div id="userinfo"><a href="#" class="list">首页</a></div>
	<div id="userinfo"><a href="#" class="list">登出</a></div>
	<div id="userinfo"><a href="#" class="list">帮助</a></div>
	<div id="userinfo"><a href="#" class="list">刷新</a></div>
</div>
<div id="nav">
  <div id="welcome">Hello ${user.name },Welcome  to  Hea AdminConsole</div>向上伸缩
</div>

<div id="container">

    <div id="sidebar">
      <div id="sideportlet">
        <div id="sidebarmenu1" class="sidebarmenu1">RBAC管理</div>
        <div id="sidebarcontent">
        	  <iframe width="100%" height="100%" scrolling="yes" frameborder="0" src="${pageContext.request.contextPath}/view/jsp/admin/rbacManager.jsp"></iframe>
        </div>
      </div>
      
      
      <div id="sideportlet">
        <div id="sidebarmenu1" class="sidebarmenu1">用户个性化管理</div>
        <div id="sidebarcontent">
			<iframe width="100%" height="500px" scrolling="yes" frameborder="0" src="${pageContext.request.contextPath}/view/jsp/admin/personalManager.jsp"></iframe>
		</div>
      </div>
      
    </div>
     
    
    <div id="content">
      <div id="contentportlet">
        <div id="contentmenu" >功能操作区</div>
        <div id="contentlist">
        	<iframe name="mainContent" frameborder="0" marginwidth="0" align="top" marginheight="0" width="100%" height="100%" src="${pageContext.request.contextPath}/view/jsp/admin/index.jsp" scrolling="auto"/>
        </div>
      </div>
      
     
    </div>
</div>
 <div >Copyright (c) 2010 Hea AdminConsole, Inc. All rights reserved. </div>
</body>
</html>
