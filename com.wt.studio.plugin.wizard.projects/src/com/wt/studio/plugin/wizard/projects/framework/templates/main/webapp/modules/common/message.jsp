<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'message.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style type="text/css">
#tabMessage {
	width: 371px;
}


#tdhead{
	background-image:
		url(${pageContext.request.contextPath}/view_ref/images/message/head_bg.gif);
		background-repeat:repeat-x;
	width:357px;
	height: 29px;
}

#tdhead_l{
	background-image:
		url(${pageContext.request.contextPath}/view_ref/images/message/head_l.gif);
		background-repeat:repeat-x;
	width:9px;
	height: 29px;
}

#tdhead_r{
	background-image:
		url(${pageContext.request.contextPath}/view_ref/images/message/head_r.gif);
		background-repeat:repeat-x;
	width:8px;
	height: 29px;
}

.tdMessage{
	width:370px;
	padding: 5px;
	
	border-left-width: 1px;
	border-right-width: 1px;
	border-color: #DDDCDC;
	border-left-style: solid;
	border-right-style:solid;
	font-family: 宋体;
	font-size: 14px;
	color: #2F4F4F;
}

#tdList{
	background-image:
		url(${pageContext.request.contextPath}/view_ref/images/message/list_bg.jpg);
		background-repeat:repeat-x;
	height: 30px;

}

#tdspan{
	text-align:center;
	height:28px;
	font-size:14px;
	font-weight:bold;
	background-image:
		url(${pageContext.request.contextPath}/view_ref/images/message/tab_bg2.gif);
	background-repeat:repeat-x;
	width: 70px;
	margin-left: 3px;
}

#tdmc{
	width:355px;
	height:100%;
	padding: 10px;
	border-width: 1px;
	border-color: #828282;
	border-style: dashed;
	font-family: 宋体;
	font-size: 14px;
	color: #2F4F4F;
}

</style>
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
							&nbsp;&nbsp;${ulMessage}
							</span>
						</span>
					</td>
				</tr>
				<tr>
					<td height="13" colspan="3">
						<img src="${pageContext.request.contextPath}/view_ref/images/message/out_b.gif"/>
					</td>
				</tr>
			</table>
		</center>

	</body>
</html>
