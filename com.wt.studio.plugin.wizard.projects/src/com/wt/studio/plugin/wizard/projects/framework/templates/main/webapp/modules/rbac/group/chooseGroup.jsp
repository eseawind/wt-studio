<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录选择组</title>
<link id="themePath" rel="Stylesheet" href="${pageContext.request.contextPath}/css/theme/${empty theme? 'blue':theme}/subindex.css" />
<script type="text/javascript">
	
	//选择所有组~~~~~~~~~~~~~~~~~~~~~~~~~
	var groupIds = "";
	<c:forEach items="${groupList}" var="group" varStatus="vst">
		groupIds += "${group.uuid},";
	</c:forEach>
	groupIds = groupIds.substring(0,groupIds.length-1);
	window.location.href = "${pageContext.request.contextPath}/heaUserAction.hea?action=regLoginGroup&groupIds=" + groupIds;
	//选择所有组-------------------------	
	
	//单选组~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//window.parent.showDialogWindow2("请选择登录组", 280, 350);
	//单选组-----------------------------
	
	function colseWin(){
		window.parent.closeDialogWindow();
		return true;
	}
</script>
</head>
<body>
	<div>
		<form action="${pageContext.request.contextPath}/heaUserAction.hea" onsubmit="return colseWin();">
			<input type="hidden"  name="action" value="regLoginGroup"/>
			<table>
				<c:forEach items="${groupList}" var="group" varStatus="vst">
					<tr>
						<td>
							<c:if test="${vst.count == 1}">
								<input type="radio" name="groupIds" checked="checked" value="${group.uuid}"/>${group.name}
							</c:if>
							<c:if test="${vst.count > 1}">
								<input type="radio" name="groupIds" value="${group.uuid}"/>${group.name}
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td><input class="btn" type="submit" value="提交"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>