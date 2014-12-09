<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'group/quangxianfenpei.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		<style type="text/css">
body {
	padding: 0;
	margin: 0;
	width: 100%;
	height: 100%;
}
</style>

	</head>

	<body>
		<div id="userListMain">
			<table width="100%" height="97%" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<table width="100%" height="100%">
							<!-- <tr height="50">
								<td>
									<div style="font-size: 12px; color: #36648B;">
										<llogic:iterate id="g" name="groupPath" scope="session">
								>>${g.groupname}
							</llogic:iterate>
									</div>
								</td>
							</tr> -->
							<tr>
								<td>
									<iframe src="" frameborder="0" name="userList" width="98%"
										height="99%"></iframe>
								</td>
							</tr>
						</table>

					</td>
					<td width="200">
						&nbsp;
						<iframe name="qxfpdepartment" width="150" frameborder="0"
							height="98%"
							src="${pageContext.request.contextPath}/heaGroupAction.hea?action=toAuthorizeTree&groupuuid=${param.groupuuid}"></iframe>
					</td>
				</tr>
			</table>
		</div>

	</body>

</html>
