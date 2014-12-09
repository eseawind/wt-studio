<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'sidebar.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript"
	src="${ctx}/view_ref/script/jquery-1.3.2.js"></script>
<script type="text/javascript"
	src="${ctx}/view_ref/script/i_tree/jsframework.js"></script>


<style type="text/css">
body {
	padding: 0;
	margin: 0;
	width: 100%;
	height: 100%;
	font-size: 12px;
}

.tbotton {
	border-style: ridge;
	border-width: 1px;
	height: 18px;
}
</style>

</head>

<body>
<bean:size id="dsize" name="indexs" />


可见指标个数：${dsize}
<br>
<div><script type="text/javascript">
	var rootIds=new Array();
	
	

	 	function treeOpenOrClose(isOpen){
			if(isOpen){
				for(var i=0;i<rootIds.length;i++ ){
					a.expandAll(rootIds[i]);
				}
			}else{
				for(var i=0;i<rootIds.length;i++ ){
					a.collapseAll(rootIds[i]);
				}
			}
	   	}
	 		var data={};
	 		<c:forEach items="${rootIndexIds}" var="rootId" varStatus="indexStatus">
	 			rootIds[${indexStatus.count-1}]='${rootId}';
		 		<c:forEach  items="${indexs}"  var="indexItem">
		 			<c:if test="${indexItem.uuid eq rootId}">
					data['-1_${indexItem.uuid}'] = "text: ${indexItem.name };";
		 			</c:if>
			  	</c:forEach>
		  	</c:forEach>
			<c:forEach items="${rootIndexIds}" var="rootId" varStatus="indexStatus">
		 		<c:forEach  items="${indexs}"  var="indexItem">
			 		<c:if test="${indexItem.uuid ne rootId}">
					data['${indexItem.parentuuid}_${indexItem.uuid}'] = "text: ${indexItem.name };";
		 			</c:if>
			  	</c:forEach>
	  		</c:forEach>
		  	
		   
</script> <c:if test="${dsize >0}">
	<a style="font-size: 12px; text-decoration: none;"
		href="javascript:treeOpenOrClose(true);">展开</a>
	<a style="font-size: 12px; text-decoration: none;"
		href="javascript:treeOpenOrClose(false);">收起</a>
</c:if> <script type="text/javascript">
        //*
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
        
        a.autoSort=false;
        a.canOperate=true;
        document.write(a.render());
        //treeOpenOrClose(true);
        a.expandLevel(1);
        
</script></div>
</body>

</html>
