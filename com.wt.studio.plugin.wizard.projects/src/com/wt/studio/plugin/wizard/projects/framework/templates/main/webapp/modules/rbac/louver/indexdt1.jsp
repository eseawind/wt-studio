 <%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.hirisun.hea.rbac.model.Index"%>
<%@page import="java.util.List"%>
<%@page import="com.hirisun.hea.common.model.SystemCode"%>
<%@page import="com.hirisun.uum.api.domain.Department"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
<link href="${pageContext.request.contextPath}/modules/eqmt/css/css.css" rel="stylesheet" id="themePath"
 type="text/css"/>
<link href="${pageContext.request.contextPath}/css/rbac/louver/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/component/sdmenu/sdmenu.css" />
<!-- ajax树状结构所需文件 -->
<link	href="${pageContext.request.contextPath}/css/component/dhtmlxtree.css" rel="stylesheet" type="text/css">

<link id="themePath"
	href="${pageContext.request.contextPath }/css/theme/jquery/jquery.ui.all.css"
	type="text/css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/component/outlookbar/outlookbar.css">
<script	src="${pageContext.request.contextPath}/script/component/dhtmlxcommon.js"></script>
<script	src="${pageContext.request.contextPath}/script/component/dhtmlxtree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/common/jquery-latest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/component/outlookbar/outlookbar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/component/lhgdialog/lhgcore.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/component/lhgdialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/component/sdmenu/sdmenu.js"></script>
<script type="text/javascript">
	var mainC ;
	var first = 0;
	var tree;
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
	 
	 var dialog;
	$(function(){
		$("#sidebar_split").click(function(){
			if($("#sidebar_split_image").attr("class") == "sidebar_split_go"){
				$("#sidebar").show();
				$("#sidebar_split_image").removeClass("sidebar_split_go");
				$("#sidebar_split_image").addClass("sidebar_split_back");
			}else{
				$("#sidebar").hide();
				$("#sidebar_split_image").removeClass("sidebar_split_back");
				$("#sidebar_split_image").addClass("sidebar_split_go");
			}
			autoWindowHeight();
		});
		 $(".changestyle").each(function(ii,oo){
			 $(oo).click(function(){
				 $(".changestyle").each(function(i,o){
					$(o).removeClass("siderbaractive");
				 });
				$(oo).addClass("siderbaractive");
			 	$("#content_header").text("当前位置:" + $(oo).attr("path"));
			 });
		});
			clickItem($("#divMenu_0").attr("url"),$("#divMenu_0").text());
		autoWindowHeight();
		
		//init();
		$(".nosiderbaractive").each(function(i,o){
			$(o).click(function(){
				$(".nosiderbaractive").each(function(ii,oo){
					$(oo).removeClass("navactive")
				});
					$(this).addClass("navactive");
			})
		})
		//设置sidebar隐藏
		$(".mymenu").each(function(i,o){
			$(o).hide();
		});
		$(".mymenu").first().show();
		
		$(".siderbar_menu_1_content").first().show();
		$(".siderbar_menu_1").click(function(){
			var displayStatus = $(this).next(".siderbar_menu_1_content").css("display");
			if(displayStatus=="none"){
				$(".siderbar_menu_1_content").slideUp("fast");
				$(this).next(".siderbar_menu_1_content").slideDown("fast");
			}
		});
	})
	
	function modifyPassword(){
		var dialog = new J.dialog({
				id:'cityShow',
				title:'修改密码',
				height:300,
				width:500,
				cover:true,
				resize:false,
				maxBtn:false,
				cancelBtn:false,
				page:'/uum2/w/updatePassword'
				});
				dialog.ShowDialog();
	}
</script>

</head>

<body id="wicketdialog">
<div class="container">
	<div class="header" id="header">
		<div class="banner">
			<div class="bnner_content">
			  <div class="banner_information"><a href="###" onclick="modifyPassword();">»修改密码</a>&nbsp;&nbsp;<!--<a href="#" onclick="">»帮助</a>  --><a href="#" onclick="loginOut();">»注销</a></div>
			  <div class="banner_logout"></div>
			</div>
	    </div>
	</div>
	
	<div class="nav">
			<ul id="mainnav">
			<h4>${dept.name}&nbsp;${user.name}&nbsp;您好! </h4>
					<c:forEach items="${topMenus}" var="item" varStatus="vst">
					  	<c:if test="${vst.first}">
					  		<script type="text/javascript">
					  			selectId = '${item.indexuuid}';
					  		</script>
					  		<li id="divMenu_${vst.index}" url="${item.indexurl}" onclick="clickItem('${item.indexurl}','${item.indexname}');showMenu('${item.indexuuid}','${item.description}',${vst.index});changeBackground(this)"><a class="nosiderbaractive navactive" href="###">${item.indexname}</a></li>
					  	</c:if>
					  	<c:if test="${vst.first != true}">
						  	<li id="divMenu_${vst.index}" url="${item.indexurl}" onclick="clickItem('${item.indexurl}','${item.indexname}');showMenu('${item.indexuuid}','${item.description}',${vst.index});changeBackground(this);"><a class="nosiderbaractive" href="###">${item.indexname }</a></li>
					  	</c:if>
				  </c:forEach>
 			</ul>
		</div>
	<div class="maincontent" id="maincontent"><!--主内容区间-->
		<div class="sidebar sidebar_bg" id="sidebar"><!--侧边栏-->
			<!-- <div class="sidebar_header" id="sidebarheader">功能操作</div> -->
			<div class="sidebar_content" id="sidebarcontent">
				<!-- 菜单开始 -->
		   <c:forEach items="${topMenus}" var="i" varStatus="status">
		    <div style=" ${status.count ==1 ? 'display: display':'display:none'}" id="menu_${i.indexuuid}" class="sdmenu" name="sdmenu">
			    <c:forEach items="${i.subIndexes}" var="sec" varStatus="secStatu">
			    	<c:forEach items="${userResources}" var="userResourceEle">
			    		<c:if test="${userResourceEle.indexuuid eq sec.indexuuid}">	
				   	<div>
				        <span>${sec.indexname} </span>
				        <c:forEach items="${sec.subIndexes}" var="thir" varStatus="thirStatu">
				        	<c:forEach items="${userResources}" var="uResourceEle">
			        			<c:if test="${uResourceEle.indexuuid eq thir.indexuuid}">
									<c:if test="${empty thir.indexurl}">
										<c:set var="url1" value="###"></c:set>
									</c:if>
									<a class="changestyle" path="${i.indexname}>${sec.indexname }>${thir.indexname}" href="${empty thir.indexurl ? url1 : thir.indexurl}" target="${empty thir.indexurl ? '' : thir.target}">${thir.indexname}</a>
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
			<div class="Sidebar_footer"></div>
		</div><!--侧边栏结束-->
		<div class="sidebar_split" id="sidebar_split">
			<div id="sidebar_split_image" class="sidebar_split_back">
			</div>
		</div>
		<div class="content_1" id="content_1">
			<div class="content_header">
			<div id="content_header" class="content_header_path">当前位置:</div>
			</div>
				<div class="content_content" id="content_content" style="height:97%">
					<iframe id="mainContent" name="mainContent" src="" allowTransparency="true" frameborder="0" class="iframeContent_1"></iframe>
				</div>
			<div class="content_footer"></div>
		</div>
	</div>
	<div class="footer" id="footer">${about}</div>
</div>
<div id="cityShow"></div>
<div id="tempUrl" style="display:none;"></div>
<iframe id="clearSession" style="display:none;"></iframe>
</body>
<script type="text/javascript">
function buildMenu() {
		<c:forEach items="${topMenus}" var="i" varStatus="status">
				var myMenu_${i.indexuuid} = new SDMenu("menu_${i.indexuuid}");
				myMenu_${i.indexuuid}.init();
				myMenu_${i.indexuuid}.speed=5;
	</c:forEach>
}
window.onload =buildMenu;
		var firstIndexUuid="${firstNode.indexuuid}";
		function showMenu(indexuuid,desc,num){
			//if(){
				$("#sidebar").show();
				$("#sidebar_split_image").removeClass("sidebar_split_go");
				$("#sidebar_split_image").addClass("sidebar_split_back");
			//}else{
			//}
			
				$(".sdmenu").each(function(i,o){
					$(o).hide();
				});
				var currMenus = $("#menu_" + indexuuid);
				currMenus.show();
				//处理滑动菜单
				if(desc == "" || desc == "menu"){
					var currSideBar = $(currMenus).find(".siderbar_menu_1_content");
					currSideBar.each(function(i,o){
						$(o).hide();
					});
					currSideBar.first().show();
				}else{
					//处理树状结构
					//buildTree(indexuuid,num);
					//$('#menu_' + indexuuid + "itembg0_0").click();
				}
			//}
		}
		
		/**
		 * 设置当前选中的菜单
		 * @param {Object} myli
		 */
		function changeBackground(myli){
			//$(".nav > li").each(function(i,o){
			//	$(o).removeClass("navactive");
			///})	
			//$(myli).addClass("navactive");
		}
		
	//登出
	function loginOut(){
		window.location.href='${pageContext.request.contextPath}/heaUserAction.hea?action=logoutLouver';
		$("#clearSession").attr("src",'/zdj/heaUserAction.hea?action=clearSession');
	}
     var loadTreeFlag = new Array();
     function buildTree(rootId,divNum){
     	for(var i =0;i < loadTreeFlag.length;i++){
     		//if(divNum == loadTreeFlag[i])
     			//return;	
     	}
     	loadTreeFlag[divNum] = divNum;
     	$('#menu_' + rootId).empty();

     	var outlookbar=new OutlookBar('menu_' + rootId);
		var t;

		$.post("${pageContext.request.contextPath}/heaIndexAction.hea?action=initLouver&rootId=" + rootId,function(data){
			var xml = $.parseXML(data.xml);
			var pathText = "";
			$(xml).find("tree > item").each(function(i,o){
				t=outlookbar.addtitle($(o).attr("text"),"javascript:void(0);",i,"images/component/outlookbar/foldericon.gif","popu1",null,"images/component/outlookbar/openfoldericon.gif");
				pathText = $(o).attr("text");
				var x = $.parseXML(o.xml);
				$(xml).find("tree > item > item").each(function(index,obj){
					var url = $(obj).text();//$($.parseXML(obj.xml)).find("userdata").get(0).text;
					var imgurl = $(obj).attr("imgurl");
					if(!imgurl || imgurl=="" || imgurl== null || imgurl == "null"){
						imgurl = "itemicon"	;
					}
					outlookbar.additem($(obj).attr("text"),"clickItem('"+url + "','" + pathText + ">>" + $(obj).attr("text")+"')",index,"images/component/outlookbar/"+imgurl+".gif","",null,t,"images/component/outlookbar/open"+imgurl+".gif");
				});
			});
			outlookbar.locatefoldIndex(0);
			outlookbar.show();
			$('#menu_' + rootId + "itembg0_0").click();
		});
     }
     
     var isShowMenu = 1;
     var showHideMenuInit = 1;
     var siderbarWidth;
     function showHideMenu(show_Int){
         if(showHideMenuInit == 1){
         	showHideMenuInit = 0;
         	siderbarWidth = $(".siderbar").width() + $(".drawback").width() + 10;
         }
     	if(show_Int != null)
     		isShowMenu = show_Int;
     	else
	     	isShowMenu = isShowMenu == 1?0:1;
     	if(isShowMenu == 1){
     		$(".siderbar").show(600);
     		$(".drawgo_icon").addClass("drawback_icon");
     		$(".drawgo_icon").removeClass("drawgo_icon");
     		for(var i = 1; i <= siderbarWidth; i++){
     			//document.getElementById('content_1').style.marginLeft = i + 'px';
     		}
     	}else if(isShowMenu == 0){
     		$(".siderbar").hide(600);
     		$(".drawback_icon").addClass("drawgo_icon");
     		$(".drawback_icon").removeClass("drawback_icon");
     		for(var i = siderbarWidth; i >= 0; i--){
     			//document.getElementById('content_1').style.marginLeft = i + 'px';
     		}
     	}
     }

     function clickItem(url , pathText){
			//if(flag !=""){
			//	$("#tempUrl").val(url);
			//}else{
				$("#content_header").html("当前位置:" + pathText);
				if(url==null || url=="" || url == undefined){;return ;}
				document.getElementById('mainContent').src = null;
				if(url.indexOf("?")>=0){
					document.getElementById('mainContent').src = url ;
				}else{
					document.getElementById('mainContent').src = url ;
				}
				//$("#tempUrl").val(url);
			//}
			
		 	//var aa = $(document.createElement('a'));
			//$(aa).attr("href",url);
			//$(aa).attr("target","mainContent");
			//$(aa).click(function(){
			//	window.open($(aa).attr("href"),$(aa).attr("target"));
			//});
			//$(aa).trigger("click");
     }
     
     function autoWindowHeight(){
     	var headerHeight = document.getElementById('header').offsetHeight;
     	var mainnavHeight = document.getElementById('mainnav').offsetHeight;
     	var footerHeight = document.getElementById('footer').offsetHeight;
     	var sidebarbgHeight = 5;
     	var loginHeight = 0;
     	var clientHeight1 = getDocumentBody().clientHeight;//document.body.clientHeight > document.documentElement.scrollHeight ? document.body.clientHeight:document.documentElement.scrollHeight;
     	var maincontentHeight = clientHeight1 - headerHeight - mainnavHeight - footerHeight;
     	var sidebarcontentHeight = maincontentHeight - sidebarbgHeight;
     	var clientWidth = document.body.clientWidth ;
     	var sidebarWidth = $("#sidebar").width();
     	if($("#sidebar").is(":hidden")){
     	$("#content_1").width(clientWidth -7);
        }else{
     	$("#content_1").width(clientWidth - sidebarWidth -7);
           }
     	$("#sidebar_split").height(maincontentHeight);
     	$("#content_1").height(maincontentHeight);
		$('#sidebarcontent').height(sidebarcontentHeight) ;
     	$('#sidebar').height(maincontentHeight) ;
     	$('#maincontent').height(maincontentHeight);
		$("#maincontent").height(maincontentHeight);
		$("#mainContent").height(maincontentHeight-28);
     	//document.getElementById("content_content").style.height = sidebarcontentHeight ;
     	//document.getElementById('mainContent2').style.height = maincontentHeight;
     	//document.getElementById('siderbar_menu').style.height = maincontentHeight - sidebarbgHeight;
     }
     window.onresize = autoWindowHeight;
     function init() {
 		document.onkeydown = function() { //回车键提交
 			if (window.event.keyCode == 13) {
 				document.forms[0].submit();
 			}
 			if (window.event.keyCode == 116) { ////禁用F5键刷新
 				window.event.keyCode = 0;
 				window.event.returnValue = false;
 			}

 			var k = event.keyCode;
 			if ((event.ctrlKey == true && k == 82) || (k == 116)
 					|| (event.ctrlKey == true && k == 116)) {
 				event.keyCode = 0;
 				event.returnValue = false;
 				event.cancelBubble = true;
 			}
 		}
 		document.oncontextmenu = new Function("event.returnValue=false;"); //禁用右键刷新
 		document.onkeydown=function(){if(event.keyCode==8)return false;};
 	}

     function getDocumentBody() {
    	    return (document.compatMode && document.compatMode != "BackCompat") ? document.documentElement : document.body;
    	}
		</script>
</html>
