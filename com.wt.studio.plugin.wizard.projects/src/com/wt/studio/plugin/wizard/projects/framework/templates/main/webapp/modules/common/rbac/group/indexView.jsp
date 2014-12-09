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
			src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
			<script type="text/javascript"
			src="${pageContext.request.contextPath}/view_ref/script/i_tree/jsframework.js"></script>
			<script type="text/javascript">
				var jc="${jc}";
				
			function changeGlob(){
					window.open("${pageContext.request.contextPath}/heaGroupAction.hea?action=toIndexView&groupuuid=${param.groupuuid}&rootIndexuuid=rootindex&isExtends="+jc,"_self");
			}
			</script>
	
		<style type="text/css">
body {
	padding: 0;
	margin: 0;
	width: 100%;
	height: 100%;
	font-size: 12px;
}

.tbotton{
			border-style:ridge;
			border-width:1px;
			height: 18px;
		}
</style>
		
	</head>

	<body >
<bean:size id="dsize" name="selfIndexs" />

		<logic:notEmpty name="jc">
		<input type="button" value="私有预览" onclick="changeGlob()" class="tbotton"><br/>
		</logic:notEmpty>
		<logic:empty name="jc">
		<input type="button" value="继承预览" onclick="changeGlob()" class="tbotton"><br/>
		</logic:empty>
拥有指标个数：【${dsize}】<br>
		<div style="margin-left: 50px;">
			<script type="text/javascript">
				var indexs="${rootIndexs}".split('_');
				
			function expAll(){
				for(var i=0;i<indexs.length;i++){
					a.expandAll(indexs[i]);
				}
			}
			
			function collAll(){
				for(var i=0;i<indexs.length;i++){
					a.collapseAll(indexs[i]);
				}
			}
   	 	var	hasIndex="";
   	 	function hasIndexF(indexuuid,puuid){
   	 		hasIndex=puuid;
   	 		for(var i=0;i< indexs.length;i++){
   	 			if(indexuuid==indexs[i]){
   	 				hasIndex="-1";
   	 				break;
   	 			}
   	 		}
   	 	}
    var data={};
    <c:if test="${dsize >0}">
	    <c:forEach  items="${selfIndexs}" var="gitem">
	      	 hasIndexF('${gitem.indexuuid}','${gitem.parentindexuuid}');
	    	 data[hasIndex+'_${gitem.indexuuid}'] = "text: ${gitem.indexname };";
	    </c:forEach>
       
    </c:if> 

</script>
	
 <c:if test="${dsize >0}">
	<a style="font-size: 12px;text-decoration: none;" href="javascript:expAll()">展开</a>
	<a style="font-size: 12px;text-decoration: none;" href="javascript:collAll()">收起</a>
	</c:if>
<script type="text/javascript">
        //*
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
       // alert(rootid);
       // a.autoSort=true;
       // a.useCheckbox=true;
        //a.rootId=rootid;
        a.autoSort=false;
        a.canOperate=true;
        document.write(a.render());
        a.expandLevel(1);
</script>
		</div>

	</body>

</html>
