<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>jQuery UI Tabs - Open on mouseover</title>
		<link type="text/css"
			href="${ctx}/css/theme/jquery/blue/ui.all.css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${ctx}/script/common/jquery-1.4.2.min.js"></script>
		<script type="text/javascript"
			src="${ctx}/script/jquery/jquery.ui.core.js"></script>
		<script type="text/javascript"
			src="${ctx}/script/jquery/jquery.ui.tabs.js"></script>
		<script type="text/javascript">
	$( function() {
		$("#tabs").tabs();
	});
	function openTab(url){
		window.open(url,"userMain");
	}
	
	function setTab(){
		if($.browser.msie){
			$("#tabs").css('width','101%');
			$("#tabs").css('height','100%');
		}
	}
</script>
		<style type="text/css">
		    body{
		    	width: 100%;
		    	height: 100%;
		    	margin: 0;
		    	padding: 0;
		    }
		    #tabs{
		    	margin: 0;
		    	padding: 0;
		    	position: relative;
		    	
		    	
		    }
			#tabs a {
				font-size: 12px;
			}
			.ifcss{
				width: 100%;
				height: 91%;
				margin: 0;
				padding: 0;
			}
		</style>
	</head>
	<body onload="setTab();">
		<div id="tabs">
			<ul>
				<li >
					<a href="#tabs-1" onmousedown="openTab('${ctx}/ulUser.do?method=toUserList')">用户</a>
				</li>
				<li >
					<a  href="#tabs-1"  onmousedown="openTab('quangxianfenpei.jsp');">修改部门信息</a>
				</li>
				<li >
					<a href="#tabs-1" onmousedown="openTab('${ctx}/ulGroup.do?method=toIndexView')">增加部门</a>
				</li>
				
			</ul>
			<div id="tabs-1" style="margin: 0;padding: 0;">
				
				<iframe name="userMain"  frameborder="0" src="${ctx}/ulUser.do?method=toUserList" width="100%" height="91%"></iframe>
				
				
			</div>
		</div>
	</body>
</html>

