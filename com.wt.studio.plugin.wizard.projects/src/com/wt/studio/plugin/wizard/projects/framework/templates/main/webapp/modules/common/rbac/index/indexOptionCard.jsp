<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
	<head>
		<title>jQuery UI Tabs - Open on mouseover</title>
		<link type="text/css"
			href="${pageContext.request.contextPath}/view_ref/jui/cupertino/ui.all.css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/view_ref/jui/ui.core.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/view_ref/jui/ui.tabs.js"></script>
		<script type="text/javascript">
			
			$( function() {
				$("#tabs").tabs();
			});
			function openTab(url){
				window.open(url,"indexMain");
			}
			
	      function setTab(){
			if($.browser.msie){
				$("#tabs").css('width','100%');
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
	<body onload="setTab()" >
		<div id="tabs">
			<ul>
				
				<li >
					<a  href="#tabs-1"  onmousedown="openTab('${pageContext.request.contextPath}/heaIndexAction.hea?action=showIndex&indexuuid=${param.indexuuid}')">资源链接编辑</a>
				</li>
				
				<li >
					<a   href="#tabs-1" onmousedown="openTab('${pageContext.request.contextPath}/heaIndexAction.hea?action=addIndexForward&indexuuid=${param.indexuuid}')">新增资源链接</a>
				</li>
				<li >
					<a  href="#tabs-1"  onmousedown="openTab('${pageContext.request.contextPath}/heaIndexAction.hea?action=indexGroup&indexuuid=${param.indexuuid}');" >组关联</a>
				</li>
				
				<li >
					<a  href="#tabs-1" onmousedown="openTab('${pageContext.request.contextPath}/heaIndexAction.hea?action=indexLinkUserByIndexuuid&indexuuid=${param.indexuuid}');">可见人员</a>
				</li>
				
				</ul>
				<div id="tabs-1" style="margin: 0;padding: 0;">
					<iframe name="indexMain" frameborder="0" src="${pageContext.request.contextPath}/heaIndexAction.hea?action=showIndex&indexuuid=${param.indexuuid}" width="100%" height="91%"></iframe>	
			    </div>
		</div>
	</body>
	
</html>

