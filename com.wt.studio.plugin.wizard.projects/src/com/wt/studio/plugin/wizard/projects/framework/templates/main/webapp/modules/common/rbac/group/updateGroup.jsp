<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'rbac/group/updateGroup.jsp' starting page</title>
    
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
  			
  			
    		function chooseGroups(){
    			var  isg=document.getElementById('isadmin');
    			if(isg==null||!isg.checked){
    				window.open("heaGroupAction.hea?action=showParentGroupTree&groupuuid=${groupForm.group.groupuuid}&ad=ad","chooseGroupTree");
    			}else{
    				window.open("heaGroupAction.hea?action=showParentGroupTree&groupuuid=${groupForm.group.groupuuid}","chooseGroupTree");
    			}
    			document.getElementById('groupTree').style.display='block';
    		}
    		
    		
  	</script>
  	
  	  	<script type="text/javascript">
		
		
		$(function(){
			$("#dialog1").dialog({
			bgiframe: true,
			modal: true,
			buttons: {
				取消: function() {
					$(this).dialog('close');
				},
				确认: function() {
					$(this).dialog('close');
					document.forms[0].submit();
				}
			}
			});
		}
		);
		$(function(){
			$("#dialog2").dialog({
			bgiframe: true,
			modal: true,
			buttons: {
				取消: function() {
					$(this).dialog('close');
				},
				确认: function() {
					$(this).dialog('close');
					window.open("heaGroupAction.hea?action=deleteGroup&groupuuid=${groupForm.group.groupuuid}","contentWindow");
					
				}
			}
			});
		}
		);
		
		function f1submit(){
			if(validateForm()){
				$("#dialog1").dialog('open');
			}
		}
		
		function deleteGroup(){
			$("#dialog2").dialog('open');
		}
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
  
  <body  >
  	<div id="dialog1" title="组信息更新" >
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		&nbsp;
	</p>
	<p>
		确定要修改组 ：  <b>${groupForm.group.groupname}</b> 
		的信息吗？ 
	</p>
</div>

  	<div id="dialog2" title="删除组" >
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;">
		
		</span>
		&nbsp;
	</p>
	<p>
		提示：删除组将删除对应的人员关联，子标关联以及子级组，请谨慎操作！！！
	</p>
</div>

	${script }
  	<html:form action="heaGroupAction" method="post" >
  	<html:hidden property="action" value="updateGroup"/>
  	<input type="hidden" name="groupuuid" value="${groupForm.group.groupuuid }">
  	<div>
  		<table style="font-size: 14px;" border="0" cellpadding="0">
  			<tr height="20">
  				<td ><h4>权限组信息</h4></td>
  				<td width="50">
  					&nbsp;
  				</td>
  				<td width="100px">
					<input type="button" onclick="deleteGroup()" class="deleteButton" value="删除组" />
				</td>
  			</tr>
  			<tr  height="20">
  				<td  class="tdname">名称</td>
  				<td class="tabletd" colspan="2">
  					<html:text property="group.groupname"></html:text>
  				</td>
  			</tr>
  			<tr  height="20">
  				<td class="tdname">父级组</td>
  				<td class="tabletd" >
  					<html:text property="group.parentGroup.groupname" onfocus="this.blur();" readonly="true"></html:text>
  				 </td>
  				 <td valign="bottom" class="tabletd" align="right">
  					<input type="button" class="tbotton" value="设置父级组" onclick="chooseGroups()">
  					<html:hidden property="group.parentgroupuuid"/>
  				</td>
  			</tr>
  			
			<tr  height="80">
  				<td class="tdname">描述 </td>
  				<td class="tabletd">
  				 	<html:textarea style="float:left;" cols="30" rows="4" property="group.groupdesc"></html:textarea>
  				</td>
  				<td valign="bottom" class="tabletd">&nbsp;</td>
  				
  			</tr>
			<tr>
				<td colspan="3" height="10">
					<input  type="button" class="tbotton"  value="提   交" onclick="f1submit()">
				</td>
			</tr>  			
  		</table>
  		${ulMessage}
  	</div>
  	</html:form>
  	<div id="groupTree" style="display:none;position:absolute;top:0px;right: 0px;z-index: 3;height: 98%">
  		<iframe name="chooseGroupTree" width="240" height="96%"  frameborder="0"></iframe>
  	</div>
  </body>
  
</html>
