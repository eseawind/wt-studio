<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'indexManager.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link id="themePath" rel="Stylesheet" href="${pageContext.request.contextPath}/css/theme/${empty theme? 'blue':theme}/subindex.css" />
		<jsp:include page="../../../script/rbac/index/indexManager_js.jsp"/>
	</head>

	<body>
		<div>
			<table>
				<tr>
					<td>
						<c:if test="${consoleIsAdmin}">
						<input type="button" class="btn" value="添加" onclick="addIndex('${param.indexId}');">
						<input type="button" class="btn" value="修改" onclick="updateIndex('${param.indexId}');">
						<input type="button" class="btn" value="删除" onclick="deleteIndex('${param.indexId}');">
						<input type="button" class="btn" value="修改父节点" onclick="updateIndexParent('${param.indexId}');" style="width: 80px;">
						</c:if>
						<input type="button" class="btn" value="权限分配" onclick="updateIGTree('${param.indexId}');" style="width: 80px;">
					</td>
				</tr>
			</table>
		</div>
		<iframe id="iframeIndex" name="iframeIndex" width="100%" height="90%"></iframe>
	</body>
</html>
