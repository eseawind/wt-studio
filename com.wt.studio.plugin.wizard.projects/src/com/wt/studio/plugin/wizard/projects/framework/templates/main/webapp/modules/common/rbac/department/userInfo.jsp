<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>rbac/department/userInfo.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		body{margin: 0; font-size: 12px;}
		table,input{font-size: 13;text-decoration:none;}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jquery/jquery-1.3.2.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#btnUpdateGroup').bind('click',function(){
					var iWidth=800; //窗口宽度
					var iHeight=500;//窗口高度
					var iTop=(window.screen.height-iHeight)/2;
					var iLeft=(window.screen.width-iWidth)/2;
					window.open("${pageContext.request.contextPath}/heaDepartmentAction.hea?action=showLoginUserGroups&useruuid=${requestScope.user.uuid}","chooseGroupTree");
					//window.showModalDialog("${pageContext.request.contextPath}/heaDepartmentAction.hea?action=showLoginUserGroups&useruuid=${user.uuid}","_self","dialogHeight: "+iHeight+"px; dialogWidth: "+iWidth+"px;dialogTop: "+iTop+"; dialogLeft: "+iLeft+"; resizable: yes; status: no;scroll:no");
				
			});
		});
	</script>
  </head>
  
  <body>
  
   
   	<html:form action="heaUserAction.hea">
  	<html:hidden property="method" value="updateUser"/>
  	<span style="font-size: 13;text-decoration:none;">${departmentRecursionalMessage}</span>
  	<div >
  		
  		<table id="userInfo" border="1" width="600" >
  			<tr>
  				<td colspan="4" align="center"><h4>用户信息</h4></td>
  			</tr>
  			
  			<tr>
  				<td>ID</td>
  				<td colspan="2">${requestScope.user.id }<input type="hidden" value="${requestScope.user.id}" name="id"></td>
  				<td>&nbsp;</td>
  			</tr>
  			
  		
  			<tr>
  				<td>姓名</td>
  				<td colspan="2"><input type="text" value="${requestScope.user.id}"/></td>
  				<td>&nbsp;</td>
  			</tr>
  			
  			
  			<tr>
  				<td >用户状态</td>
  				<td colspan="2">激活</td>
  				<td>&nbsp;</td>
  			</tr>
  			
  			
  			<tr>
  				<td >直属部门</td>
  				<td colspan="2" id="depName">${departmentRecursionalMessage}</td>
  				<td>&nbsp;</td>
  			</tr>
			<tr>
  				<td >直属权限组 </td>
  				<td id="groupNames" colspan="2">&nbsp;
  				 	<c:forEach items="${requestScope.user.groups}" var="group" varStatus="status">
  				 		<!-- -->
  				 		<a href="${pageContext.request.contextPath}/heaDepartmentAction.hea?action=showIndexsUnderGroup&groupuuid=${group.groupuuid}" target="chooseGroupTree">[${group.groupname}]</a>
  				 		 
  				 		 <!-- 
  				 		 [${group.groupname}]
  				 		  -->
  				 	</c:forEach>
  				</td>
  				<td >
  					<input type="button"  value="修改关联组" id="btnUpdateGroup">
  				</td>
  			</tr>
  			<tr>
  				<td colspan="3">&nbsp;</td>
  				<td><input type="button"  value="预览可见指标" ></td>
  			</tr>
  			<tr>
  				<td >原始密码</td>
  				<td colspan="2"><input id="pwd" type="password" name="pwd" value="${requestScope.user.password }"></input></td>
  				<td>&nbsp;</td>
  			</tr>
  			
  			<tr>
  				<td >新密码</td>
  				<td colspan="2"><input id="newPwd1" name="newPwd1" type="password"></input></td>
  				<td>&nbsp;</td>
  			</tr>
  			
  			<tr>
  				<td >确认新密码</td>
  				<td colspan="2"><input id="newPwd2" name="newPwd2" type="password"></input></td>
  				<td>&nbsp;</td>
  			</tr>
  		</table>
  		
  	</div>
  	</html:form>
  	
  	<div id="groupTree" style="position:absolute;top:0px;right: 0px;z-index: 3;height: 98%">
  		<iframe name="chooseGroupTree" width="320" height="99%"  frameborder="0"></iframe>
  	</div>
  </body>
</html>
