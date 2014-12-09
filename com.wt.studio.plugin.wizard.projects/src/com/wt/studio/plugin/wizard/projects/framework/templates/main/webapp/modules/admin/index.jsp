<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<!-- view/jsp/admin/index.jsp -->
		<title>欢迎  ${user.name } 登陆XX电力公司－权限管理系统</title>
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view_ref/rbac/css/layout.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
		<script type="text/javascript">
			function showGroupTree(){
				window.open('${pageContext.request.contextPath}/heaGroupAction.hea?action=showGroupTree','groups');
				setTimeout('showIndexTree()',1000);
			}
			
			function showIndexTree(){
			   window.open('${pageContext.request.contextPath}/heaIndexAction.hea?action=showIndexTree','indexs');
			}

		</script>
		<script type="text/javascript">
			var loopcount=3;
			function changTab(tabnum){
				for(var i=1;i<=loopcount;i++){
					document.getElementById("tab"+i).className="noSelectTab";
					document.getElementById("div"+i).className="sidebarHidden";
				}
			document.getElementById("tab"+tabnum).className="selectTab";
			document.getElementById("div"+tabnum).className="sidebarShow";
			document.getElementById("contentWindow").src='';
			}
		</script>
	</head>

	<body onload="showGroupTree();">
		<div id="container">
			<div id="menu">
				<ul id="minuul">
					<li><a href="javascript:changTab(1)" id="tab1" class="selectTab"  >部门</a></li>
					<li><a href="javascript:changTab(2)" id="tab2" class="noSelectTab">权限组</a></li>
					<li><a href="javascript:changTab(3)" id="tab3" class="noSelectTab">资源</a></li>

				</ul>
			</div>
			<div id="mainContent" >
				<div id="sidebar">
					<div class="sidebarShow" id="div1"><iframe src="${pageContext.request.contextPath}/heaDepartmentAction.hea?action=showDepartmentTree&useruuid=${user.uuid}" id="users" name="users"  scrolling="yes" frameborder="0"></iframe></div>
					<div class="sidebarHidden" id="div2"><iframe id="groups" name="groups" scrolling="yes" frameborder="0" ></iframe></div>
					<div class="sidebarHidden" id="div3"><iframe id="indexs" name="indexs" scrolling="yes" frameborder="0"></iframe></div>
					<div class="sidebarHidden" id="div4"><iframe id="belts" name="belts"  scrolling="yes" frameborder="0"></iframe></div>
					<div class="sidebarHidden" id="div5"><iframe id="website" name="website"  scrolling="yes" frameborder="0"></iframe></div>
				</div>
				<div id="content">
					<iframe id="contentWindow" name="contentWindow"  frameborder="0"></iframe>
				</div>
			</div>
			
		</div>
		

	</body>
	<script type="text/javascript">
	function changeContextHeight() {
		var sheight = document.body.clientHeight;
		//var header=document.getElementById("header").offsetHeight;
		var menu=document.getElementById("menu").offsetHeight;
		//var footer=document.getElementById("footer").offsetHeight;
		
		var feat=sheight-menu-3;
		//alert('sheight:'+sheight+ 'header:'+header+' menu:'+menu+' footer:'+footer+ ' feat:'+feat);
		
		document.getElementById("mainContent").style.height=feat;
	}
	changeContextHeight();
	
	window.onresize = changeContextHeight;
</script>
</html>
