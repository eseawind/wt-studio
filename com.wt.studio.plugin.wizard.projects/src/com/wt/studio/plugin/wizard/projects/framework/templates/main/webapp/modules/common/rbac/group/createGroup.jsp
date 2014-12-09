<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'rbac/group/createGroup.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link type="text/css" href="${pageContext.request.contextPath}/view_ref/jui/cupertino/ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.draggable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.resizable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/external/bgiframe/jquery.bgiframe.js"></script>

	<style type="text/css">
		body{
			padding:0;
			margin: 0;
			width: 100%;
			height: 100%;
			font-size: 12px;
			overflow: hidden;
		}
		
		.tdname{
			background-color: #F2F2F2;
		}
		
		.tbotton{
			border-style:ridge;
			border-width:1px;
			height: 18px;
			float: right;
		}
		
		.tabletd{
			border-bottom-style: solid;
			border-bottom-width: 1px;
			border-bottom-color: #CDC9C9;
		}
		.deleteButton{
			background-image: url(${pageContext.request.contextPath}/view_ref/images/delete.gif);
			background-repeat: no-repeat;
			border-bottom-style: solid;
			border-bottom-width: 1px;
			border-bottom-color: #CDC9C9;
			padding-left: 10px;
			background-position-y:5px; 
			float: right;
		}
		
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/tree/jsframework.js"></script>
  	
  	  	<script type="text/javascript">
		function validateForm(){
			var gname=$.trim(document.getElementById('group.groupname').value);
			if(null==gname||""==gname){
				document.getElementById('vgname').innerText='组名称不能为空';
				return false;
			}
			if(gname.length>100){
				document.getElementById('vgname').innerText='组名称字符过长';
				return false;
			}
			var gdesc=$.trim(document.getElementById('group.groupdesc').innerText);
			if(null==gdesc||""==gdesc){
				document.getElementById('vgdesc').innerText='组描述不能为空';
				return false;
			}
			if(gdesc.length>300){
				document.getElementById('vgdesc').innerText='组描述字符过长';
				return false;
			}
			document.getElementById('group.groupname').value=gname;
			document.getElementById('group.groupdesc').innerText=gdesc;
			return true;
		}
	
	</script>
  </head>
  
  <body>
 	<html:form action="heaGroupAction.hea" method="post" onsubmit="return validateForm()">
  	<html:hidden property="action" value="createGroup"/>
  	<input type="hidden" name="parentgroupuuid" value="${param.parentgroupuuid }">
  	<div>
  		<table  width="100%" height="170" style="font-size: 14px;" border="0" cellpadding="0">
  			<tr height="20">
  				<td>&nbsp; </td>
  				<td width="100"><h4>增加权限组</h4></td>
  			</tr>
  			<tr  height="20">
  				<td  class="tdname">名称</td>
  				<td class="tabletd" >
  					<html:text property="group.groupname"></html:text>
  				</td>
  			</tr>
  			<tr  height="20">
  				<td class="tdname">父级组</td>
  				<td class="tabletd" >
  					<html:text property="group.parentGroup.groupname" readonly="true" onfocus="this.blur()"></html:text>
  				 	<html:hidden property="group.parentgroupuuid"/>
  				 </td>
  			</tr>
  			
			<tr  height="80">
  				<td class="tdname">描述 </td>
  				<td class="tabletd">
  				 	<html:textarea style="float:left;" cols="30" rows="4" property="group.groupdesc"></html:textarea>
  				</td>
  				
  			</tr>	
  			<tr>
  				<td>&nbsp;</td>
	  			<td ><div><input type="submit"   value="提   交" ></div></td>
			</tr>
  		</table>
  	</div>
  	</html:form>
  </body>
  
</html>
