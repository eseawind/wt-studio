<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'updateIndex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="themePath" rel="Stylesheet" href="${pageContext.request.contextPath}/css/theme/${empty theme? 'blue':theme}/subindex.css" />
	<!-- ajax树状结构所需文件 -->
	<link href="${pageContext.request.contextPath}/css/component/dhtmlxtreepro/dhtmlXTree.css" rel="stylesheet" type="text/css" >
	<script src="${pageContext.request.contextPath}/script/component/dhtmlxtreepro/dhtmlXCommon.js"></script>
	<script src="${pageContext.request.contextPath}/script/component/dhtmlxtreepro/dhtmlXTree.js"></script>
	
	<%@include file="../../../script/rbac/index/updateIndexParent_js.jsp" %>
  </head>
  
  <body onload="initilTree();">
	<div>
		<form action="${pageContext.request.contextPath}/heaIndexAction.hea">
			<input type="hidden" name="action" value="updateIndexPid">
			<input type="hidden" name="indexId" value="${param.indexuuid}">
			<input type="hidden" name="indexPId">
			<input type="submit" class="btn" value="修改">
		</form>
	</div>
 	<div id="divTree"></div>
  </body>
</html>
