<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
		    <title>My JSP 'rbac/index/addIndex.jsp' starting page</title>
		    
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
			<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/tree/jsframework.js"></script>
		
			<style type="text/css">
				body{
					padding:0;
					margin: 0;
					width: 100%;
					height: 100%;
					font-size: 12px;
				}
				td{
					font-size: 12px;
				}
				.tdname{
					background-color: #F2F2F2;
				}
				
				.tbotton{
					border-style:ridge;
					height: 18px;
				}
				.tbottonp{
					border-style:ridge;
					height: 18px;
				}
				.tabletd{
					border-bottom-style: solid;
					border-bottom-width: 1px;
					border-bottom-color: #CDC9C9;
				}
			</style>
			<script type="text/javascript">
			$(document).ready(function(){
				$("input[name='frame']").bind('click',function(){
					var t=$("input[name='frame'][checked='true']");
					$("#index_target")[0].value= t[0].value;
				});
			});
			</script>
  </head>
  
  <body>
  	  <html:form action="heaIndexAction.hea" method="post"  styleId="indexForm"  enctype="multipart/form-data">
  	  <input type="hidden" name="action" value="addIndex"/>
  	  <input type="hidden" name="indexuuid" value="${index.indexuuid }"/>
  	<div style="width: 100%;margin-top: 10" >
  		<table id="userInfo">		
  			<tr height="20" >
  				<td colspan="3" align="center"><h4>新增资源</h4></td>
  			</tr>
  			
  			<tr  height="20">
  				<td  width="100px" class="tdname">资源名称</td>
  				<td ><html:text size="65" property="index.indexname" value=""/></td>
  				<td><input type="reset" value="重置"></td>
  			</tr>
  			
  			
  			<tr  height="50">
  				<td class="tdname">资源链接地址</td>
  				<td class="tabletd">
  					<html:textarea rows="5" cols="50"   property="index.indexurl" value=""/>
  				</td>
  				<td width="100px" class="tabletd" align="right">&nbsp;</td>
  			</tr>
  			<tr  height="20">
  				<td  width="100px" class="tdname">资源图标</td>
  				<td colspan="3">
  					<html:file property="file" />
  					<input type="hidden"  size="65" id="indexicon" name="index.indexicon" value="${index.indexicon}" >
	  			</td>
  			</tr>
  			<tr  height="20">
  				<td class="tdname">父级指标</td>
  				<td class="tabletd" >
  				   <input type="text" size="65" name="parentindexName"  disabled="disabled" value="${index.indexname }" size="16"  >
  				   <input type="hidden" id="parentindexuuid" name="index.parentindexuuid"   value="${index.indexuuid }" size="50"   >
  				</td>
  			</tr>
  			
  		    <tr  height="20">
  				<td class="tdname">目标窗口</td>
  				<td >
  					<fieldset title="打开的窗口名">
					 <input type="radio" name="frame" value="mainFrame" checked="checked" >mainFrame
					 <input type="radio" name="frame" value="leftFrame" >leftFrame
					 <input type="radio" name="frame" value="topFrame" >topFrame
					 <input type="radio" name="frame" value="footFrame" >footFrame
					<br/>|&nbsp;&nbsp;&nbsp;自定义<input id="index_target" name="index.target" value="${index.target }" >
					</fieldset>
  				</td>
  				<td>&nbsp;</td>
  			</tr>
  			
  			<tr  height="20">
  				<td class="tdname">排序号</td>
  				<td><html:text size="65" property="index.indexorder"  value="0" /></td>
  				<td><input type="submit"   class="tbotton" value="保存"></td>
  			</tr>
  			
  		</table>
  	</div>
  	</html:form>
  </body>
  
</html>
