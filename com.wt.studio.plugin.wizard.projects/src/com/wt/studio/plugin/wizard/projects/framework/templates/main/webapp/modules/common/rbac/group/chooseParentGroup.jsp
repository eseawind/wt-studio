<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'rbac/group/chooseParentGroup.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/view_ref/script/g_tree/jsframework.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
	</head>

	<body>


		<script type="text/javascript">
   	function closeWindow(){
   		parent.window.document.getElementById('groupTree').style.display="none";
   	}
   	function returnGroups(){
   			var group=$("input[checked='true'][name='mzRadio']")[0].value.split('_');
   			
   			//parent.window.document.getElementById('group.parentGroup.groupname').value=group[1];
   			//alert(group[0]+"_"+group[1]);
   			if(window.confirm("您选择了 "+group[1]+" 组,是否再一次确认")){
   				parent.window.document.getElementById('groupTree').style.display="none";
   				parent.window.document.getElementById('group.parentgroupuuid').value=group[0];
   				parent.window.document.getElementById("group.parentGroup.groupname").value=group[1];
   	   		}
			//parent.window.document.getElementById('groupTree').style.display="none";
   	}
   	
   	
   	var parentGroupUuid="${groupForm.group.parentgroupuuid}";
    	 var hasgroup="";
   	 function hasgroupF(groupuuid){
   	 		hasgroup="";
   	 		if(groupuuid==parentGroupUuid){
   	 			hasgroup='checked';
   	 		}
   	 }
   		
	 <bean:size id="dsize" name="allGroups" />
	 var data={};
	 <c:if test="${dsize >0}">
	 	hasgroupF("${allGroups[0].groupuuid}");
	 	data["-1_${allGroups[0].groupuuid}"] = "text:${allGroups[0].groupname};checked:"+hasgroup+";value:${allGroups[0].groupuuid};";
	 	var rootid="${allGroups[0].groupuuid}";
	
 	 <c:forEach begin="1" end="${dsize-1}" items="${allGroups}" var="gitem">
     hasgroupF("${gitem.groupuuid}");
	 data['${gitem.parentgroupuuid}_${gitem.groupuuid}'] = "text: ${gitem.groupname };checked:"+hasgroup+";value:${gitem.groupuuid};";
	</c:forEach>
   </c:if>
	</script>
		<c:if test="${dsize >0}">
			<a style="font-size: 12px; text-decoration: none;"
				href="javascript:a.expandAll('${allGroups[0].groupuuid}')">展开</a>
			<a style="font-size: 12px; text-decoration: none;"
				href="javascript:a.collapseAll('${allGroups[0].groupuuid}')">收起</a>
		</c:if>
		<a style="font-size: 12px; text-decoration: none; float: right;"
			href="javascript:closeWindow()">关闭</a>
		<c:if test="${dsize >0}">
			<a
				style="font-size: 12px; text-decoration: none; float: right; margin-right: 10px"
				href="javascript:returnGroups()">确认</a>
		</c:if>
		<script type="text/javascript">
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
       // alert(rootid);
       // a.autoSort=true;
       // a.rootId=rootid;
        a.useRadio=true;
        //a.useCheckbox=true;
        a.canOperate=true;
        document.write(a.render());
        a.expandLevel(1);
</script>
	</body>

</html>
