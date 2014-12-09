<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.hirisun.hea.rbac.model.Index"%>
<%@page import="java.util.List"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>战略指标监测系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/component/sdmenu/xz/sdmenu.css" />
<link href="${pageContext.request.contextPath}/css/theme/${empty theme ? 'blue':theme}/xznew/css.css" rel="stylesheet" id="themePath"
 type="text/css"/>
<link href="${pageContext.request.contextPath}/css/rbac/louver/index.css" rel="stylesheet" type="text/css" />
<!-- ajax树状结构所需文件 -->
<link href="${pageContext.request.contextPath}/css/component/dhtmlxtree.css" rel="stylesheet" type="text/css">

<link id="themePath"
	href="${pageContext.request.contextPath }/css/theme/jquery/jquery.ui.all.css"
	type="text/css" rel="stylesheet" />
<script	src="${pageContext.request.contextPath}/script/component/dhtmlxcommon.js"></script>
<script	src="${pageContext.request.contextPath}/script/component/dhtmlxtree.js"></script>
<%@include file="../../../script/rbac/louver/index_js.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/component/sdmenu/sdmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/common/json.js"></script>
<script type="text/javascript" src="${pageConetxt.request.contextPath}/script/rbac/louver/indexextend.js"></script>

</head>
<body onload="changeIframeBG('mainContent_0');autoWindowHeight();">
<div class="container">
	<div class="header" id="header">
		<div class="topnav"><a href="###" style="color:#ffffff;" onclick="loginOut()">注销</a></div>
	  <div class="banner">
		   <ul class="nav">
		  <c:forEach items="${topMenus}" var="item" varStatus="vst">
		  	<c:set var="iUrl" value="${item.indexurl}"></c:set>
	  		<c:set var="tar" value="${item.target}_${vst.index}"></c:set>
		  	<c:if test="${vst.first}">
		  		<script type="text/javascript">
		  			selectId = '${item.indexuuid}';
		  		</script>
		  		<li id="divMenu_${vst.index}" onclick="showMenu('${item.indexuuid}');clickTitleBtn('${item.indexuuid}',${vst.index});changeBackground(this)"><a style="background-image:url(${pageContext.request.contextPath }/images/theme/blue/xznew/nav11.gif)" href="###" >${item.indexname}</a></li>
		  	</c:if>
		  	<c:if test="${vst.first != true}">
			  	<li id="divMenu_${vst.index}" onclick="showMenu('${item.indexuuid}');clickTitleBtn('${item.indexuuid}',${vst.index});changeBackground(this)"><a href="###">${item.indexname }</a></li>
		  	</c:if>
		  </c:forEach>
		  </ul>
	  </div>  
	</div>
	  <div class="topbar" id="mainnav">
	    <div class="topbar_left">${user.name}，欢迎您访问忻州战略指标执行监测系统！ </div>
	  </div>
	<div class="maincontent" id="maincontent"><!--主内容区间-->
		<div class="sidebar"><!--侧边栏-->
		  <div class="siderbar_menu" id="siderbar_menu"><!--此处显示  class "siderbar_menu" 的内容-->
	  		<!-- 菜单开始 -->
		   <c:forEach items="${topMenus}" var="i" varStatus="status">
		    <div style=" ${status.count ==1 ? 'display: display':'display:none'}" id="my_menu_${i.indexuuid}" class="sdmenu" name="sdmenu">
			    <c:forEach items="${i.subIndexes}" var="sec" varStatus="secStatu">
			    	<c:forEach items="${userResources}" var="userResourceEle">
			    		<c:if test="${userResourceEle.indexuuid eq sec.indexuuid}">	
				   	<div>
				        <span>${sec.indexname} </span>
				        <c:forEach items="${sec.subIndexes}" var="thir" varStatus="thirStatu">
				        	<c:forEach items="${userResources}" var="uResourceEle">
			        			<c:if test="${uResourceEle.indexuuid eq thir.indexuuid}">
				        	<label class="submenu_1">
								<label class="submenu_2">
									<c:if test="${!(empty thir.indexurl)}">
										<c:if test="${fn:contains(thir.indexurl,'?')}">
											<c:set var="url1" value="${thir.indexurl}&p_zgs=${dept.uuid}"></c:set>
										</c:if>
										<c:if test="${!(fn:contains(thir.indexurl,'?'))}">
											<c:set var="url1" value="${thir.indexurl}?p_zgs=${dept.uuid}"></c:set>
										</c:if>
									</c:if>
									<c:if test="${empty thir.indexurl}">
										<c:set var="url1" value="###"></c:set>
									</c:if>
									<c:set var="thirTarget" value="${thir.target }_${status.index}"></c:set>
									<a class="changestyle" href="${url1 }" target="${empty thir.indexurl ? '' : thirTarget}" onclick="loadChildNode(this,'${thir.indexuuid}',1,'${status.index}');">${thir.indexname}</a>
								</label>
							</label>
							</c:if>
							</c:forEach>
				        </c:forEach>
				     </div>
				    </c:if>
				    </c:forEach>
			     </c:forEach>
		    	</div>
			</c:forEach>
	<!-- 菜单结束 -->
		  </div>
		</div><!--侧边栏结束-->
		<div class="drawback"><div class="drawback_icon" onclick="showHideMenu()"></div></div><!--收缩按钮-->
		<div class="content_1" id="content_1">
			<c:forEach items="${topMenus}" var="item" varStatus="vst">
				<c:if test="${vst.first}">
					<iframe id="mainContent_${vst.index}" name="mainContent_${vst.index}" src="${item.indexurl}" allowTransparency="true" frameborder="0" class="iframeContent_1"></iframe>
		  		</c:if>
			  	<c:if test="${vst.first != true}">
			  		<iframe id="mainContent_${vst.index}" name="mainContent_${vst.index}" src="${pageContext.request.contextPath}/modules/rbac/louver/welcome.jsp" allowTransparency="true" frameborder="0" class="iframeContent_2"></iframe>
			  	</c:if>
			</c:forEach>
		</div>
	</div>
	<div class="footer" id="footer">版权所有：山西省电力公司</div>
</div>
</body>
</html>
