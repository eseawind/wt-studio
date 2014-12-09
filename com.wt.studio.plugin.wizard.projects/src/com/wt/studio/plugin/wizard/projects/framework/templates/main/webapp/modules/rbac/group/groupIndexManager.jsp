<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>组与指标绑定</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="themePath" rel="Stylesheet" href="${pageContext.request.contextPath}/css/theme/${empty theme? 'blue':theme}/subindex.css" />
	<link href="${pageContext.request.contextPath}/css/component/dhtmlxtree.css"
			rel="stylesheet" type="text/css">
	<script	src="${pageContext.request.contextPath}/script/component/dhtmlxcommon.js"></script>
	<script	src="${pageContext.request.contextPath}/script/component/dhtmlxtree.js"></script>
	<script src="${pageContext.request.contextPath }/script/common/jquery-1.4.2.min.js"  type="text/javascript"></script>
	<jsp:include page="../../../script/rbac/group/groupIndexManager_js.jsp"/>
  </head>
  
  <body>
    <table height="100%">
    	<tr>
    		<td width="300">
    			<div id="sidebar"></div>
    		</td>
    		<td  width="400">
    			<iframe name="iframeContent"  width="100%" height="100%" frameborder="0"></iframe>
    		</td>
    	</tr>
    </table>
  </body>
</html>
