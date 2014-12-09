<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP '404.jsp' starting page</title>
		<link type="text/css" href="${pageContext.request.contextPath}/css/common/404_css.jsp" rel="stylesheet" />
	</head>

	<body>
		<center>
			<table id="tabMessage" cellpadding="0" cellspacing="0" style="margin-top: 120px;" >
				<tr >
					<td  id="tdhead_l">			
					</td>
					<td id="tdhead">
						<span id="tdspan">系统提示</span>
					</td>
					<td  id="tdhead_r">					
					</td>
				</tr>
				
				<tr >
					<td colspan="3" >
						<span class="tdMessage">
							
							<span id="tdmc">
							<br/>
							&nbsp;&nbsp;没有页面
							</span>
						</span>
					</td>
				</tr>
				<tr>
					<td height="13" colspan="3">
						<img
							src="${pageContext.request.contextPath}/images/admin/final_out_b.gif"/>
					</td>
				</tr>
			</table>
		</center>

	</body>
</html>
