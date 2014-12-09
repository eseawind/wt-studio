<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'rbac/group/userList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{padding:0;margin: 0;width: 100%;height: 100%;}
		
		#mainTable th{background-image: url(${pageContext.request.contextPath}/view_ref/images/title_bg.gif);}
		
		#mainTable tr{}
		
		#mainTable td{text-align: center;font-size: 12;}
		
		#mainTable th{text-align: center;font-size: 15;}
		
		.header td{text-align: center;font-size: 12;}
		
		.tr1{background-color: #EAEAEA;}
		.tr2{background-color: #FCFCFC;}
		.searchForm{margin: 0}
	</style>
	
  </head>
  
  <body>
  
<div id="userListMain"> 
		<br/>
	    <span style="font-size: 13;text-decoration:none;">部门导航:${groupRecursionalMessage }</span>
  		<input type="hidden" name="action" value="showUserList"/>
   		<table width="95%" border="0">
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
   						<logic:empty  name="page" property="data">
   							<tr>
   								<td colspan="4"><center style="color:red">暂无数据</center></td>
   							</tr>
   						</logic:empty>
   						<logic:notEmpty name="page" property="data">
   						<logic:iterate id="user" name="page" indexId="index" property="data">
   							<tr class="tr${index%2+1}">
   								<td>
   									${index+1}
   								</td>
   								<td>
   									${user.id }
   								</td>
   								<td>
   									${user.name }
   								</td>
   								<td>激活</td>
   								<td>
   									${user.department.name }
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
   	</div>
  
  </body>
  
</html>
