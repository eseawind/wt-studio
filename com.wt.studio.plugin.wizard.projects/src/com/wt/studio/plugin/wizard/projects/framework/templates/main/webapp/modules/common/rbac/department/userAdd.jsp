<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link type="text/css" href="${ctx}/view_ref/css/qt.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/script/common/jquery-1.4.2.min.js"></script>

	<style type="text/css">
		body{
			padding:0;
			margin: 0;
			width: 100%;
			height: 100%;
			font-size: 12px;
			overflow: hidden;
		}
		
		.spanPwd{
			display: none;
			color: red;
			font-size: 12px;
		}
	</style>
	<script type="text/javascript" src="${ctx}/view_ref/script/tree/jsframework.js"></script>
  	<script type="text/javascript">
  		
  		
    		
    		function chooseGroups(){
    			window.open("ulUser.do?method=toGroupChoose","chooseGroupTree");
    		}

    		function chooseDepartment(){
    			window.open("ulUser.do?method=toDepartmentChoose&uuuid=${userForm.user.uuid}","chooseGroupTree");
    		}
    		
    		function showIndexs(){
    			var guuids=document.getElementById("groupIds").value;
    			window.open("ulUser.do?method=toIndexsView&groupIds="+guuids,"chooseGroupTree");
    			//document.getElementById('groupTree').style.display='block';
    		}

    		function validateUserId(){
				var userUuid=$("#userId").attr("value");
				$.get("ulUser.do", { method:"validateUserId", userId: userUuid },
					function(data){
						if(data){
							$("#spanUserId").text("id可用");
							return true;			
						}else{
							$("#spanUserId").text("id不可用");
							return false;
						}
					}
				 );
            }
  	</script>
  	
  	  	<script type="text/javascript">
		function formSubmit(){
			if(validateUserId()==false){
				return false;
			}
     		var pwd1=document.getElementById('password1');
     		var pwd2=document.getElementById('password2');
     		if(pwd1.value==pwd2.value){
     		   	$("#spanPwd1").text('');
				$("#spanPwd2").text('');
     		}else{
				$("#spanPwd1").text('两次密码不一致');
				$("#spanPwd2").text('再次密码不一致');
				return false;
            }	
            return true;
		}

	</script>
  </head>
  
  <body >


  	<html:form action="ulUser.do" method="post" >
  	<html:hidden property="method" value="addUser"/>
  	<div>
  		<table id="userInfo" width="100%" height="170" style="font-size: 14px;" >
  			<tr height="20">
  				<td></td>
  				<td  style="padding-left: 100px"><h4>增加用户</h4></td>
  				<td>${ulMessage }<input  type="submit" class="tbotton" value="提   交" onclick="return formSubmit();">
  				</td>
  				<td width="330px"></td>
  			</tr>
  			
  			<tr  height="20">
  				<td  width="100px" class="tdname">ID</td>
  				<td class="tabletd" colspan="2">
  					<html:text styleId="userId" property="user.uuid"></html:text>
  					<span id="spanUserId"></span>
  				</td>
  				<td width="330px">&nbsp;</td>
  			</tr>
  		
  			<tr  height="20">
  				<td class="tdname">姓名</td>
  				<td class="tabletd" colspan="2">
  					<html:text property="user.name"/>
  					<span id="spanUserName"></span>
  				</td>
  				<td>&nbsp;</td>
  			</tr>
  				<tr  height="20">
  				<td class="tdname">密码</td>
  				<td class="tabletd" colspan="2">
  					<html:password styleId="password1" property="user.password"/>
  					<span id="spanPwd1"></span>
  				</td>
  				<td>&nbsp;</td>
  			</tr>
  			<tr  height="20">
  				<td class="tdname">确认密码</td>
  				<td class="tabletd" colspan="2">
  					<input type="password" id="password2">
  					<span id="spanPwd2"></span> 
  				</td>
  				<td>&nbsp;</td>
  			</tr>
  			<tr  height="20">
  				<td class="tdname">用户状态</td>
  				<td class="tabletd" colspan="2">
	  				<html:select property="user.status" value="1">
	  					<html:option value="1">活动</html:option>
	  					<html:option value="0">禁用</html:option>
	  				</html:select>
  				</td>
  				<td>&nbsp;</td>
  			</tr>
  			<tr  height="20">
  				<td class="tdname">
  				直属部门
  				<html:hidden styleId="depId" property="user.department.uuid"/>
  				</td>
  				<td class="tabletd" id="depName">
  					&nbsp;${userForm.user.department.name}
  				</td>
  				<td  class="tabletd" align="right">
  					<input type="button" class="tbotton" value="选择部门" onclick="chooseDepartment()">
  				</td>
  				<td width="330px" height="40">&nbsp;</td>
  			</tr>
			<tr  height="50">
  				<td class="tdname">直属权限组 
  				<html:hidden  styleId="groupIds"  property="groupIds"/></td>
  				<td class="tabletd" id="groupNames" >&nbsp;
  				 	
  				</td>
  				<td  class="tabletd" align="right">
  					<input type="button" class="tbotton" value="选择关联组" onclick="chooseGroups()">
  				</td>
  				<td width="330px" height="40">&nbsp;</td>
  			</tr>
  			<tr>
  				<td></td>
  				<td></td>
  				<td><input type="button" class="tbotton" value="预览可见指标" onclick="showIndexs();">
  				</td>
  				<td>&nbsp;</td>
  			</tr>
  			<c:forEach items="${userForm.tableJoins}"  varStatus="tjstatus">
  			<tr  height="20">
  				<td class="tdname">${userForm.tableJoins[tjstatus.count-1].tableConfig.elementname}</td>
  				<td class="tabletd" colspan="2">
  					<html:text property="tableJoins[${tjstatus.count-1}].elementcontent"></html:text>
  				</td>
  				<td>&nbsp;</td>
  			</tr>
  			
  			</c:forEach>
  			
  		</table>
  		
  	</div>
  	
  	</html:form>
  	<div id="groupTree" style="position:absolute;top:0px;right: 0px;z-index: 3;height: 98%">
  		<iframe name="chooseGroupTree" width="320" height="99%"  frameborder="0"></iframe>
  	</div>
  	
  </body>
  
</html>
