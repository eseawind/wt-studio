<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'updateIndex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link id="themePath" rel="Stylesheet" href="${pageContext.request.contextPath}/css/theme/${empty theme? 'blue':theme}/subindex.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/rbac/index/updateIndex.js"></script>
	
  </head>
  
  <body>
    <html:form action="/heaIndexAction.hea" onsubmit="return indexUpdate();">
    	<input type="hidden" name="action" value="updateIndex">
    	<html:hidden property="index.hasChild"/>
    	<html:hidden property="index.indexuuid"/>
    	<html:hidden property="index.indexicon"/>
    	<html:hidden property="index.indexlevel"/>
    	<html:hidden property="index.indextype"/>
    	<c:if test="${indexForm.index.indexuuid == null}">
    		<html:hidden property="index.parentindexuuid" value="${parentUuid}"/>
    	</c:if>
    	<c:if test="${indexForm.index.indexuuid != null}">
    		<html:hidden property="index.parentindexuuid" />
    	</c:if>
 	   <table>
 	   	<tr>
 	   		<td>名称:</td>
 	   		<td><html:text styleClass="ipt_2" maxlength="100" property="index.indexname"></html:text></td>
 	   	</tr>
 	   	<tr>
 	   		<td>URL：</td>
 	   		<td><html:text styleClass="ipt_2" maxlength="2000" property="index.indexurl"></html:text></td>
 	   	</tr>
 	   	<tr>
 	   		<td>排序号：</td>
 	   		<td><html:text styleClass="ipt_2" property="index.indexorder"></html:text></td>
 	   	</tr>
 	   	<tr>
 	   		<td>目标：</td>
 	   		<td>
				<label for="target0">主窗口</label><input id="target0" type="radio" value="mainContent" name="index.target" ${index.target eq 'mainContent' ? "checked=checked" : ""} >
				<label for="target1">新窗口</label><input id="target1" type="radio" value="_blank" name="index.target" ${index.target eq '_blank' ? "checked=checked" : ""} >
			</td>
 	   	</tr>
 	   	<tr>
 	   		<td>是否禁用：</td>
 	   		<td>
 	   			<html:select styleClass="ipt_2" property="index.way" >
 	   				<html:option value="0">禁用</html:option>
 	   				<html:option value="1">启用</html:option>
 	   			</html:select>
 	   		</td>
 	   	</tr>
 	   	<tr>
 	   		<td>备注：</td>
 	   		<td><html:text styleClass="ipt_2" maxlength="255" property="index.description"/></td>
 	   	</tr>
 	   	<tr>
 	   		<td>创建时间：</td>
 	   		<td><bean:write property="index.createTime" name="indexForm" format="yyyy-MM-dd HH:mm:ss"/></td>
 	   	</tr>
 	   	<c:if test="${add == true}">
 	   	<tr>
 	   		<td>关联组：</td>
 	   		<td>
 	   			<select class="ipt_2" name="igGroupUuid">
 	   				<c:forEach items="${groupList}" var="item">
 	   					<option value="${item.uuid}">${item.name}</option>
 	   				</c:forEach>
 	   			</select>
 	   		</td>
 	   	</tr>
 	   	</c:if>
 	   	<tr>
 	   		<td><html:submit styleClass="btn">提交</html:submit></td>
 	   	</tr>
 	   </table>
    </html:form>
  </body>
</html>
