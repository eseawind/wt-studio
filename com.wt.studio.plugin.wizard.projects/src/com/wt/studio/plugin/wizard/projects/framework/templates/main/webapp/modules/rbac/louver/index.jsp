<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.hirisun.hea.rbac.model.Index"%>
<%@page import="java.util.List"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>战略指标监测系统</title>
<link href="${pageContext.request.contextPath}/css/theme/${empty theme ? 'blue':theme}/xz/css.css" rel="stylesheet" id="themePath"
 type="text/css"/>
<link href="${pageContext.request.contextPath}/css/rbac/louver/index.css" rel="stylesheet" type="text/css" />
<!-- ajax树状结构所需文件 -->
<link	href="${pageContext.request.contextPath}/css/component/dhtmlxtree.css" rel="stylesheet" type="text/css">

<link id="themePath"
	href="${pageContext.request.contextPath }/css/theme/jquery/jquery.ui.all.css"
	type="text/css" rel="stylesheet" />
<script	src="${pageContext.request.contextPath}/script/component/dhtmlxcommon.js"></script>
<script	src="${pageContext.request.contextPath}/script/component/dhtmlxtree.js"></script>
<%@include file="../../../script/rbac/louver/index_js.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/jquery-1.4.2.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/component/sdmenu/sdmenu.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/component/sdmenu/sdmenu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/common/json.js"></script>
<script type="text/javascript">
	function changeMainNavState()
	{
		var mainNavState = document.getElementById("mainNav_draw").className;
		if(mainNavState == "mainnav_menu_drawback")
			{
				document.getElementById("mainNav_draw").className = 'mainnav_menu_drawgo';
			}else
				{
					document.getElementById("mainNav_draw").className = 'mainnav_menu_drawback';
				}
		$("#header").slideToggle("fast", function autoChangeHeight(){
					var mainNavState = document.getElementById("mainNav_draw").className;
					var headerHeight =0;
					if(mainNavState == "mainnav_menu_drawback")
						{
							headerHeight =document.getElementById('header').offsetHeight;
						}
			     	var mainnavHeight = document.getElementById('mainnav').offsetHeight;
			     	var footerHeight = document.getElementById('footer').offsetHeight;
			     	var loginHeight = document.getElementById('login').offsetHeight;
			     	var clientHeight = document.body.clientHeight;
			     	var maincontentHeight = clientHeight - headerHeight - mainnavHeight - footerHeight;
			     	document.getElementById('maincontent').style.height = maincontentHeight;
			     	document.getElementById('siderbar_menu').style.height = maincontentHeight - loginHeight;
			     });
	}
	function changeFrameStyle(stylePath)
	{
		var myframes = window.frames;
		for (var i = 0; i < myframes.length; i++) {
			var linkObj = myframes[i].document.getElementById("themePath");
			if(linkObj)
				{
					linkObj.href ="${pageContext.request.contextPath}/"+ stylePath;
				}
		}
	}
	
	/**
	 * 
	 * @param {Object} id
	 */
	function loadChildNode(aLink,id,level,frmindex){
		var pad = (Number(level)+2) * 10;
		var linkp = $(aLink).parent();
		var labels = linkp.children("label");
		if(labels.length == 0){
			$.ajax({
				url:'${pageContext.request.contextPath}/heaIndexAction.hea?action=ajaxLoadIndex&id=' + id,
				async:false,
				success:function(data){
					var j = data;
					$(j).each(function(i,o){
						var ur = o.indexurl;
						if(ur.indexOf("&") >=0){//给url添加部门id
							ur = ur + "&p_zgs=${dept.uuid}";
						}else{
							ur = ur + "?p_zgs=${dept.uuid}";
						}
						var ht = "<label class=''>"
									+ "<label class=''>"
									+ "<a style='padding-left:"+ pad +"px;' href='" + ur +"' target='" + o.target + "_" + frmindex + "'"
	 								+ "onclick=loadChildNode(this,'" + o.indexuuid+ "'," + (Number(level)+ 1) + "," + frmindex + ")>&gt;" + o.indexname +"</a>"
									+ "</label>"
								+"</label>";
						linkp.append(ht);
					})
				}
			});
			
		}else{//再次点击删除已添加的数据
			$(labels).each(function(i,o){
				$(o).remove();
			})
		}
		
		return false;
	}
	
	$(function(){
		autoWindowHeight();
		//$("#divMenu_0").click(function(){
		//	showHideMenu(0);
		//});
		//$("#divMenu_0").trigger("click");
	})
</script>

</head>

<body onload="changeIframeBG('mainContent_0');autoWindowHeight();">
<div class="container">
	<div class="header_1" id="header">
	  <div class="logo_1"></div>
	  <div class="banner">
	  		<div class="topnav"><a href="###" style="color:#ffffff;" onclick="loginOut()">注销</a></div>
	  </div>  
	   <ul class="nav">
	  <c:forEach items="${topMenus}" var="item" varStatus="vst">
	  <c:set var="iUrl" value="${item.indexurl}"></c:set>
	  	<c:if test="${vst.first}">
	  		<c:set var="tar" value="${item.target}_${vst.index}"></c:set>
	  		<script type="text/javascript">
	  			selectId = '${item.indexuuid}';
	  		</script>
	  		<li class="navactive" id="divMenu_${vst.index}" onclick="showMenu('${item.indexuuid}');clickTitleBtn('${item.indexuuid}',${vst.index});changeBackground(this)"><a href="${empty iUrl ?'###':iUrl}" target='${empty iUrl ? "" : tar}'>${item.indexname}</a></li>
	  	</c:if>
	  	<c:if test="${vst.first != true}">
		  	<li id="divMenu_${vst.index}" onclick="showMenu('${item.indexuuid}');clickTitleBtn('${item.indexuuid}',${vst.index});changeBackground(this)"><a href="###">${item.indexname }</a></li>
	  	</c:if>
	  </c:forEach>
	  </ul>
	</div>

	<!-- mainnav start-->
	<div id="mainnav">
	  <div class="welcome">
	  <div id="mainNav_draw" class="mainnav_menu_drawback" onclick="changeMainNavState()"></div>
	</div>
	</div>
	<!-- mainnav end-->

	<div class="maincontent" id="maincontent"><!--主内容区间-->
		<div class="siderbar"><!--侧边栏-->
		  <div class="login" id="login"><!--此处显示login 的内容-->
			<div class="login_content">欢迎${user.name}</div>
		  </div>
		  <div class="siderbar_menu" id="siderbar_menu"><!--此处显示  class "siderbar_menu" 的内容-->
	  		<!-- 菜单开始 -->
		   <c:forEach items="${topMenus}" var="i" varStatus="status">
		    <div style=" ${status.count ==1 ? 'display: display':'display:none'}" id="my_menu_${i.indexuuid}" class="sdmenu" name="sdmenu">
			    <c:forEach items="${i.subIndexes}" var="sec" varStatus="secStatu">
			    	<% 
			    		Index ii=(Index)pageContext.getAttribute("sec");
			    		List<Index> userResources=(List<Index>)request.getAttribute("userResources");
			    		for(Index n: userResources){
			    			if(n.getIndexuuid().equals(ii.getIndexuuid())){
			    	%>	
				   	<div>
				        <span>${sec.indexname} </span>
				        <c:forEach items="${sec.subIndexes}" var="thir" varStatus="thirStatu">
				        	<%
					        	Index ttt=(Index)pageContext.getAttribute("thir");
					        	for(Index t: userResources){
				        			if(t.getIndexuuid().equals(ttt.getIndexuuid())){
				        	%>
				        	<label class="submenu_1">
								<label class="submenu_2">
									<c:set var="url1" value="${thir.indexurl}&p_zgs=${dept.uuid}"></c:set>
									<c:set var="url2" value="${thir.indexurl}?p_zgs=${dept.uuid}"></c:set>
									<a href="${fn:contains(thir.indexurl,'?') ? url1 : url2}" target="${thir.target }_${status.index}" onclick="loadChildNode(this,'${thir.indexuuid}',1,'${status.index}');">&gt;${thir.indexname}</a>
								</label>
							</label>
							<%
				        		}
				        	}
				        	%>
				        </c:forEach>
				     </div>
				     <%
			    			}
			    		}
				     %>
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
	<div class="footer" id="footer"></div>
</div>
</body>
<script type="text/javascript">
  		 function buildMenu() {
	  		<c:forEach items="${topMenus}" var="i" varStatus="status">
						var myMenu_${i.indexuuid} = new SDMenu("my_menu_${i.indexuuid}");
						myMenu_${i.indexuuid}.init();
						myMenu_${i.indexuuid}.speed=5;
			</c:forEach>
		}
		window.onload =buildMenu;
		
		
		var firstIndexUuid="${firstNode.indexuuid}";
		function showMenu(indexuuid){
			
			var arr=document.getElementsByTagName("div");
			//全部隐藏
			for(i=0;i<arr.length;i++){
				if(arr[i].id.indexOf("my_menu_") != -1){
					arr[i].className="sdmenu";
					arr[i].style.display="none";
				}
			}
			//显示点击的层
			for(i=0;i<arr.length;i++){
				if(arr[i].id.indexOf("my_menu_") != -1){
					var myDiv=document.getElementById("my_menu_"+indexuuid);
					myDiv.style.display="block";
					myDiv.className="sdmenu";
					break;
				}
			}
			
		}
		
		function changeBackground(myli){
			$(".nav > li").each(function(i,o){
				$(o).removeClass("navactive");
			})	
			$(myli).addClass("navactive");
		}
		</script>
</html>
