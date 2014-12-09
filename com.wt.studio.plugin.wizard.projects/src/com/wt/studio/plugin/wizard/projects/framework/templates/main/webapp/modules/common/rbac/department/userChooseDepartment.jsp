<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'department/sidebar.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript"
			src="${ctx}/view_ref/script/d_tree/jsframework.js"></script>
	</head>

	<body>


		<script type="text/javascript">
		function returnDepartment(){
			var mzRadios=document.getElementsByName('mzRadio');
			var departmentStr='';
			for(var i=0;i<mzRadios.length;i++){
				if(mzRadios[i].checked){
					departmentStr=mzRadios[i].value;
					break;	
				}
			}
			var departmentValues=departmentStr.split('_');
   			parent.window.document.getElementById('depId').value=departmentValues[0];
			parent.window.document.getElementById('depName').innerHTML=departmentValues[1];
   			
	  	}
   	
   	
   	var parentGroupUuid="${requestScope.userDepartmentId}";
    var hasgroup="";
   	 function hasgroupF(groupuuid){
   	 		hasgroup="";
   	 		if(groupuuid==parentGroupUuid){
   	 			hasgroup='checked';
   	 		}
   	 }
   		
	 <bean:size id="dsize" name="departments" />
	 var data={};
	 <c:if test="${dsize >0}">
	 	hasgroupF("${departments[0].uuid}");
	 	data["-1_${departments[0].uuid}"] = "text:${departments[0].name};checked:"+hasgroup+";value:${departments[0].uuid};";
	 	var rootid="${departments[0].uuid}";
	
 	 <c:forEach begin="1" end="${dsize-1}" items="${departments}" var="gitem">
     hasgroupF("${gitem.uuid}");
	 data['${gitem.parentuuid}_${gitem.uuid}'] = "text: ${gitem.name };checked:"+hasgroup+";value:${gitem.uuid};";
	</c:forEach>
   </c:if>
	</script>
		<c:if test="${dsize >0}">
			<a style="font-size: 12px; text-decoration: none;"
				href="javascript:a.expandAll('${departments[0].uuid}')">展开</a>
			<a style="font-size: 12px; text-decoration: none;"
				href="javascript:a.collapseAll('${departments[0].uuid}')">收起</a>
		</c:if>
		
		<c:if test="${dsize >0}">
			<a
				style="font-size: 12px; text-decoration: none; float: right; margin-right: 10px"
				href="javascript:returnDepartment()">确认</a>
		</c:if>
		<script type="text/javascript">
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
         a.rootId=rootid;
        a.useRadio=true;
        a.canOperate=true;
        document.write(a.render());
        a.expandLevel(1);
        a.expandAll('${departments[0].uuid}');
        
</script>
	</body>

</html>
