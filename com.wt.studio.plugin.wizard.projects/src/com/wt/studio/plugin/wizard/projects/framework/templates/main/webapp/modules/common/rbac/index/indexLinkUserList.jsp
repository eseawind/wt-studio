<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'rbac/index/indexLinkUserList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{
			padding:0;
			margin: 0;
			width: 100%;
			height: 100%;
		}
		
		#mainTable th{
			background-image: url(${pageContext.request.contextPath}/view_ref/images/title_bg.gif);
		}
		
		#mainTable tr{
			
		}
		
		#mainTable td{
			text-align: center;
			font-size: 12;
		}
		
		#mainTable th{
			text-align: center;
			font-size: 15;
		}
		
		.tr1{
			background-color: #EAEAEA;
		}
		.tr2{
			background-color: #FCFCFC;
		}
		div{
		  padding-left: 10px;
		}
	</style>
	
  </head>
  
  <body>
  
<div id="userListMain"> 
  		<input type="hidden" name="method" value="showUserList"/>
   		<table width="100%">
   			<tr>
   			   <td>&nbsp;</td>
   			   <td>&nbsp;</td>
   			   <td>&nbsp;</td>
   			   <td>&nbsp;</td>
   			</tr>
   			<tr>
   				<td> 
   					<table width="100%" id="mainTable" cellspacing="1" border="0">
   						<tr>
   							<th width="50">序号</th>
   							<th>用户ID</th>
   							<th>姓名</th>
   							<th>状态</th>
   							<th>直属部门</th>
   						</tr>
   						<logic:iterate  name="users"  id="userZZ" indexId="index">
   							<tr class="tr${index%2+1}">
   								<td>
   									${index+1}
   								</td>
   								<td>
   									${userZZ.uuid }
   								</td>
   								<td>
   									${userZZ.name }
   								</td>
   								<td>激活</td>
   								<td>
   									${userZZ.department.name }
   								</td>
   							</tr>
   						</logic:iterate>
   						
   						
   						
   						
   						 
   						
   						<c:choose>
   							<c:when test="${fn:length(users) ne 0}">
			   						<tr>
			   							<td colspan="5">
						   							
						   							
						   							
						   						<!-- 分页底部信息++++++++++++++++++++++++++++++++++++++++++++++++++++ begin-->
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
						   						<!-- 分页底部信息++++++++++++++++++++++++++++++++++++++++++++++++++++ end-->
						   						
			   						
			   							</td>
			   						</tr>
	   						
	   						</c:when>
	   						<c:otherwise>
	   							<tr>
	   								<td colspan="5"><span style="color: red">没有相关数据</span></td>
	   							</tr>
	   							
	   						</c:otherwise>
   						</c:choose>
   						
   						
   						
   						
   					</table>
   				</td>
   			</tr>
   		</table>
   </div>
  </body>
  
</html>
