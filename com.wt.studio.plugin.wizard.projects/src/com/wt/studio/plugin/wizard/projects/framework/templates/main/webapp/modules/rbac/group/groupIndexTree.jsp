<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>组至指标关联</title>
		<link id="themePath" rel="Stylesheet" href="${pageContext.request.contextPath}/css/theme/${empty theme? 'blue':theme}/subindex.css" />
		<link href="${pageContext.request.contextPath }/css/rbac/treeWin.css"
			rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/component/dhtmlxtree.css" rel="stylesheet" type="text/css"><script	src="${pageContext.request.contextPath}/script/component/dhtmlxcommon.js"></script>
		<script	src="${pageContext.request.contextPath}/script/component/dhtmlxtree.js"></script>
		<script	src="${pageContext.request.contextPath }/script/common/jquery-1.4.2.min.js"	type="text/javascript"></script>
		<jsp:include page="../../../script/rbac/group/groupIndexTree_js.jsp"/>
	</head>
	<body>
	<form name="updateIGForm"
		action="${pageContext.request.contextPath}/heaGroupAction.hea"
		onsubmit="return updateIGFormSubmit();">
		<input type="hidden" name="action" value="updateIG">
		<input type="hidden" name="bubs">
		<input type="hidden" name="indexIdsStr" value="${indexIdsStr}">
		<input type="hidden" name="selectedIndexIdsStr"
			value="${indexIdsStr}">
		<input type="hidden" name="groupId" value="${groupId}">
			<table width="100%" height="100%" cellpadding="0" cellspacing="0">
				<tr >
					<td>
						<table width="100%" height="100%">
							<tr height="30">
								<td>
									<span style="float: left;">
										<input type="button" class="btn" value="系统" onclick="switchShow(1)">
										<input type="button" class="btn" value="资源" onclick="switchShow(2)">
									</span>
									<span style="float: right;">
									 <input type="submit" class="btn" value="提交">
									</span>
								</td>
							</tr>
							<tr>
								<td>
									<div id="sidebar1"></div>
									<div id="sidebar2" style="display:none;"></div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>