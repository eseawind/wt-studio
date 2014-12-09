<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>

		<title>My JSP '/rbac/group/fenpei.jsp' starting page</title>

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

#mainTable th {
	background-image:
		url(${pageContext.request.contextPath}/view_ref/images/title_bg.gif);
}

#mainTable tr {
	
}

#mainTable td {
	text-align: center;
	font-size: 12;
}

#mainTable th {
	text-align: center;
	font-size: 15;
}

.tr1 {
	background-color: #EAEAEA;
}

.tr2 {
	background-color: #FCFCFC;
}

.tbotton {
	border-style: ridge;
	border-width: 1px;
	height: 18px;
}
</style>
	<link type="text/css" href="${pageContext.request.contextPath}/view_ref/jui/cupertino/ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.draggable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.resizable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/external/bgiframe/jquery.bgiframe.js"></script>
		<script type="text/javascript">

	function andChild1(obj){
		var ischoose=obj.checked;
		if(ischoose==true){
			window.open("${pageContext.request.contextPath}/heaGroupAction.hea?action=departmentUsers&departmentuuid=${departmentuuid}&groupuuid=${groupuuid}&andChild=on","_self");
		}else{
			window.open("${pageContext.request.contextPath}/heaGroupAction.hea?action=departmentUsers&departmentuuid=${departmentuuid}&groupuuid=${groupuuid}&andChild=","_self");
		}
	}
	
	function changeSelect(obj){
	var bottonname=obj.value;
		if(bottonname=="全选"){
			$("input[name='userUuids'][checked!='true']").attr("checked", true);
			obj.value="全取消";
		}else{
			$("input[name='userUuids'][checked='true']").attr("checked", false);
			obj.value="全选";
		}
	}
	
	$(function(){
			$("#dialog").dialog({
			bgiframe: true,
			modal: true,
			buttons: {
				取消: function() {
					$(this).dialog('close');
				},
				确认: function() {
					$(this).dialog('close');
					var count=$("input[name='userUuids'][checked='true']").length;
					$("input[name='checkboxCount']")[0].value=count;
					//alert(count);
					document.forms[0].submit();
				}
			}
			});
		}
		);
		
	function f1submit(){
			$("#dialog").dialog('open');
		}
	
</script>
<style type="text/css">
	body{
		padding: 0px;
		margin: 0px;
	}
</style>
	</head>

	<body>
		<span style="font-size: 13;text-decoration:none;">${groupRecursionalMessage }</span>
		<div id="userListMain">
			<html:form action="heaGroupAction.hea" method="post">
				<input type="hidden" name="action" value="usersBindToGroup" />
				<input type="hidden" name="groupuuid" value="${groupuuid }" />
				<input type="hidden" name="checkboxCount"/> 
				<table width="100%">
					<tr>
						<td style="font-size: 12px;">
							<table width="100%" >
								<tr style="font-size: 12px;">
									<td width="200">
										&nbsp;
									</td>
									<td>
										<span id="tabletitle" style="float: right;"></span>
									</td>
								</tr>
								<tr style="font-size: 12px;">
									<td>
										包含下级部门人员:
										
										<input type="checkbox"  onclick="andChild1(this)" id="isContain" accesskey="on" value="${andChild }">
									</td>

									<td >
										<span style="float: right;"> <input type="button"
												value="全选" class="tbotton" onclick="changeSelect(this)"/>&nbsp;&nbsp;&nbsp;&nbsp;
												<input  type="button" class="tbotton" value="提交" onclick="f1submit()"> </span>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table width="95%" id="mainTable" cellspacing="1" border="0">
								<tr>
										<th width="50">序号</th>
										<th>用户ID</th>
										<th>姓名</th>
										<th>用户状态</th> 
										<th>直属部门</th>
										<th>关联状态</th>
								</tr>
								<logic:empty  name="users" property="data">
		   							<tr>
		   								<td colspan="6"><center style="color:red">暂无数据</center></td>
		   							</tr>
   								</logic:empty>
								
								<logic:notEmpty name="users" property="data">
									<logic:iterate id="duser" name="users" indexId="index" property="data">
										<tr class="tr${index%2+1}">
											<td>${index+1}</td>
											<td>${duser.id }</td>
											<td>${duser.name }</td>
										 	<td>活动</td>
											<td>${duser.department.name }</td>
											<td>
												<html:multibox  property="userUuids"  >
													<bean:write name="duser" property="uuid"/>
												</html:multibox>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
							</table>
						</td>
					</tr>
					
					
					<tr>
						<td style="font-size: 12px">
							
							<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++ begin-->
	   						<form action="${pageContext.request.contextPath}/heaGroupAction.hea">
									<input type="hidden" name="action" value="showUserList"/>
									<input type="hidden" value="${groupuuid }" name="groupuuid">
								
									共 ${page.totalRecord eq null ? 0:page.totalRecord}条记录&nbsp;&nbsp;
								
									每页<select name="perPageRecord">
											<option value="15" selected="selected">15</option>
											<option value="20">20</option>
											<option value="30">30</option>
										</select>
										
									跳页：
									<input size="3" name="currPageNum" id="currPageNum" value="${page.currPageNum}" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
									&nbsp;
									<input class="comb" value="Go" type="submit">
									
									页次${page.currPageNum }/${page.totalPage }
									<a href="${pageContext.request.contextPath}/heaGroupAction.hea?action=showUserList&groupuuid=${groupuuid}">首页</a>
									
									<c:choose>
										<c:when test="${page.hasPreviouPage }">
											<a target="_self" href="${pageContext.request.contextPath}/heaGroupAction.hea?action=showUserList&groupuuid=${groupuuid}&currPageNum=${page.currPageNum-1}&perPageRecord=${page.perPageRecord}">上一页</a>
										</c:when>
										<c:otherwise>
											<a >无上一页</a>
										</c:otherwise>
									</c:choose>
									
									<c:choose>
										<c:when test="${page.hasNextPage }">
											<a target="_self" href="${pageContext.request.contextPath}/heaGroupAction.hea?action=showUserList&groupuuid=${groupuuid}&currPageNum=${page.currPageNum+1}&perPageRecord=${page.perPageRecord}">下一页</a>
										</c:when>
										<c:otherwise>
											<a >无下一页</a>
										</c:otherwise>
									</c:choose>
									<a target="_self" href="${pageContext.request.contextPath}/heaGroupAction.hea?action=showUserList&groupuuid=${groupuuid}&currPageNum=${page.totalPage}&perPageRecord=${page.perPageRecord}">尾页</a>
									
							</form>
   							<!-- ++++++++++++++++++++++++++++++++++++++++++++++++++++ end-->
						</td>
					</tr>
				</table>
			</html:form>
			
		</div>
			<div id="dialog" title="组权限分配" >
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		&nbsp;
	</p>
</div>
	</body>
<script>
/*
 * 设置是否显示子部门的人员信息
 */
	$("#isContain").checked="checked";
	var is = '${andChild}';
	if(is == 'on' ){
		$("#isContain").attr("checked","checked");
	}else{
		$("#isContain").attr("checked","");
	}
	
	//alert($("#isContain").attr("checked","checked"));
</script>
</html:html>
