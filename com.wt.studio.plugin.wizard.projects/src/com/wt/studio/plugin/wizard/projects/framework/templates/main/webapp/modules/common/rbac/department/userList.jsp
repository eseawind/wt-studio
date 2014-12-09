<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP '/view/jsp/department/userList.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
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

  <body>
   <span style="font-size: 13;text-decoration:none;">部门导航:${departmentRecursionalMessage }</span>
  <div style="margin: 20,0,0,0">
  <table  width="90%" border="0">
  	<tr>
  		<td>
  				<!-- 搜索框 -->
  				<table border="0"  width="100%" cellpadding="0" cellspacing="0" style="font-size:14px; background-image: url(${pageContext.request.contextPath}/view_ref/images/tbg.gif);">
			  		<tr>
						<td width="300">
							<form action="${pageContext.request.contextPath}/heaDepartmentAction.hea" class="searchForm" id="showUserInCurrDepartment">
								 ${isSelected==true ?'显示单层部门用户':'显示多层部门用户'}
								<input  type="checkbox" name="isSelected"  ${isSelected==true ?'checked="checked"':''} onclick="showUserInCurrDepartment.submit();"/>
								<input type="hidden" name="action" value="showUserInCurrDepartment"/>
								<input type="hidden" name="departmentuuid" value="${param.departmentuuid}" />
							</form>
						</td>
						<td width="*" align="right">
			   					<html:form action="heaUserKeyAction" styleClass="searchForm">
				   					人员搜索：<html:text style="width:80px" property="keyWord"/>
				   					<html:select property="searchType" >
				   						<html:option value="0" >选择搜索条件</html:option>
				   						<html:option value="id">按ID</html:option>
				   						<html:option value="name" >按姓名</html:option>
				   					</html:select>
				   					&nbsp;
				   					&nbsp;
				   					<html:submit >搜  索</html:submit>
				   					<input type="hidden" name="action" value="userKeySearch"/>
				   					<input type="hidden" name="departmentuuid" value="${param.departmentuuid }"/>
			   					</html:form>
						</td>
					</tr>
			  	</table>
  		</td>
  	</tr>
  	
  	
  	<tr>
  		<td>
  			<!-- 用户列表 -->
			<table width="100%" id="mainTable" cellspacing="1" border="0">
				<tr>
					<th width="50">序号</th>
					<th>用户账号</th>
					<th>姓名</th>
					<th>用户状态</th>
					<th>直属部门</th>
					<th width="200px">用户角色</th>
				</tr>
				
				<c:forEach items="${page.data}" var="u" varStatus="sta">
					<tr class="tr${sta.count%2==0 ? '1':'2'}">
						<td>
							${sta.count }
						</td>
						<td>
							${u.id}
						</td>
						<td>
							<a target="_self" href="${pageContext.request.contextPath}/heaDepartmentAction.hea?action=showUserInfo&useruuid=${u.uuid}">${u.name}</a>
						</td>
						<td>激活</td>
						<td>
							${u.department.name}
						</td>
						
						<td>
							<c:forEach items="${u.groups}" var="group">
								[${group.groupname}] &nbsp;&nbsp; 
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
				
		
				
				
					<tr>
						<td colspan="6">
							<form action="${pageContext.request.contextPath}/heaDepartmentAction.hea">
								<input type="hidden" name="action" value="jumpToPage"/>
								<input type="hidden" value="${param.departmentuuid }" name="departmentuuid">
							
								共 ${page.totalRecord}条记录&nbsp;&nbsp;
							
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
								<a target="_self" href="${pageContext.request.contextPath}/heaDepartmentAction.hea?departmentuuid=${param.departmentuuid}&action=jumpToPage&currPageNum=1&perPageRecord=${page.perPageRecord}">首页</a>
								
								<c:choose>
									<c:when test="${page.hasPreviouPage }">
										<a target="_self" href="${pageContext.request.contextPath}/heaDepartmentAction.hea?departmentuuid=${param.departmentuuid}&action=jumpToPage&currPageNum=${page.currPageNum-1}&perPageRecord=${page.perPageRecord}">上一页</a>
									</c:when>
									<c:otherwise>
										<a >无上一页</a>
									</c:otherwise>
								</c:choose>
								
								<c:choose>
									<c:when test="${page.hasNextPage }">
										<a target="_self" href="${pageContext.request.contextPath}/heaDepartmentAction.hea?departmentuuid=${param.departmentuuid}&action=jumpToPage&currPageNum=${page.currPageNum+1}&perPageRecord=${page.perPageRecord}">下一页</a>
									</c:when>
									<c:otherwise>
										<a >无下一页</a>
									</c:otherwise>
								</c:choose>

								<a target="_self" href="${pageContext.request.contextPath}/heaDepartmentAction.hea?departmentuuid=${param.departmentuuid}&action=jumpToPage&currPageNum=${page.totalPage}&perPageRecord=${page.perPageRecord}">尾页</a>
								
							</form>
						</td>
					</tr>
			</table>		
  		</td>
  	</tr>
  </table>
  

	</div>
  </body>
</html>
