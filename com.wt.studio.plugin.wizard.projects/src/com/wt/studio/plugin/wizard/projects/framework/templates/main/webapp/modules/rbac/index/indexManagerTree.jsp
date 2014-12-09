<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>指标管理</title>
<link id="themePath" rel="Stylesheet" href="${pageContext.request.contextPath}/css/theme/${empty theme? 'blue':theme}/subindex.css" />
<link href="${pageContext.request.contextPath }/css/rbac/treeWin.css" rel="stylesheet" type="text/css" />
<!-- ajax树状结构所需文件 -->
<link href="${pageContext.request.contextPath}/css/component/dhtmlxtree.css" rel="stylesheet" type="text/css" >
<script src="${pageContext.request.contextPath}/script/component/dhtmlxcommon.js"></script>
<script src="${pageContext.request.contextPath}/script/component/dhtmlxtree.js"></script>
<script src="${pageContext.request.contextPath }/script/common/jquery-1.4.2.min.js"  type="text/javascript"></script>
<jsp:include page="../../../script/rbac/index/indexManagerTree_js.jsp">
	<jsp:param value="${param.type}" name="indexType"/>
</jsp:include>


</head>
<body>
	<table width="100%" height="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td id="tdSidebar">
				<div id="sidebar"></div>
			</td>
			<td>
				<iframe name="iframeContent" width="100%" height="100%" src=""></iframe>
			</td>
		</tr>
	</table>
</body>
</html>