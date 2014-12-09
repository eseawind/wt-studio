<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/modules/common/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'sidebar.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/g_tree/jsframework.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
  
  <style type="text/css">
  .tbotton {
	border-style: ridge;
	border-width: 1px;
	height: 18px;
}
  </style>
  <script type="text/javascript">
      var changeChoose="${changeChoose}";
      function changeGlob(){
		  window.open("${pageContext.request.contextPath}/heaIndexAction.hea?action=indexGroup&groupuuid=${ulBelt.group_uuid}&indexuuid=${param.indexuuid}&changeChoose="+changeChoose,"_self");
			}
	  function changeGlob2(){
		  window.open("${pageContext.request.contextPath}/heaIndexAction.hea?action=indexGroup&groupuuid=${ulBelt.group_uuid}&indexuuid=${param.indexuuid}","_self");
			}
  </script>
  
  </head>
  
  <body onload="atimer();">
   	<script type="text/javascript">
   	function atimer(){
		var indexUuids=$("input[type='checkbox'][checked='true']");
		
		var selectCount=indexUuids.length;
		$("#selectIndexCount").attr('innerText','【'+selectCount+'】');
		setTimeout('atimer()',2000);
	}
   	
   	function closeWindow(){
   		parent.window.document.getElementById('groupTree').style.display="none";
   	}
   	function returnGroups(){
   		var indexidss=$(":checkbox");
			var ids ="";
			for(var i in indexidss){
				if(indexidss[i].checked==true){
				ids+=indexidss[i].value;
				ids+="-";
				}
			}
			if(ids.length>1){
				ids=ids.substring(0,ids.length-1);
			}
			// alert('${indexuuid}'); 
		   window.open("${pageContext.request.contextPath}/heaIndexAction.hea?action=updateIndexLinkGroup&indexuuid=${param.indexuuid}&groupUuids="+ids ,'_self');

   	}
    	  //var groupUuids=parent.window.document.getElementById('groupuuids').value.split('-');
    	    var  hasgroup ="";
    	    var groupuuids= "${groupuuids}";
    	    var groupuuu = groupuuids.split("_");
    	    
    	    function hasgroupF(groupuuid){
    	        hasgroup ="";
    	   		 for(var i=0;i<groupuuu.length;i++){
                 		if(groupuuid==groupuuu[i]){
                    		 hasgroup='checked';
                    		 break;
                		 }
                 }
    	    }
	 <bean:size id="dsize" name="indexGroups" />
	 var data={};
	 var rootid="";
	 <c:if test="${dsize >0}">
	 	hasgroupF("${indexGroups[0].groupuuid}");
	 	data["-1_${indexGroups[0].groupuuid}"] = "text:${indexGroups[0].groupname};checked:"+hasgroup+";value:${indexGroups[0].groupuuid};";
	 	 rootid="${indexGroups[0].groupuuid}";
	
  <c:forEach begin="1" end="${dsize-1}" items="${indexGroups}" var="gitem">
      hasgroupF("${gitem.groupuuid}");
	 data['${gitem.parentgroupuuid}_${gitem.groupuuid}'] = "text: ${gitem.groupname };checked:"+hasgroup+";value:${gitem.groupuuid}";
</c:forEach>
   </c:if>
	</script>
	 <dir style="font-size: 12px;color:#36648B;margin-left: 0;padding-left: 0">
  	</dir>
  	<logic:notEmpty name="admin" scope="session">
			<c:if test="${changeChoose eq '0' }">
				<input type="button" value="全局" onclick="changeGlob()"
					class="tbotton">
					</c:if> 
				<br />
				<c:if test="${changeChoose eq '1' }">
				<input type="button" value="地市" onclick="changeGlob()"
					class="tbotton">
				<br />
				</c:if>			
		</logic:notEmpty>
  	<font size="2" style="text-decoration: inherit" >
  	 组总数：【${totalGroupSize}】 <br/>
  	 选中个数：<span id="selectIndexCount">【${checkGroupSize}】</span> <br/>
	</font>
	<div style="margin-left: 50px;">
	 <c:if test="${dsize >0}">
	<a style="font-size: 12px;text-decoration: none;" href="javascript:a.expandAll('${indexGroups[0].groupuuid}')">展开</a>
	<a style="font-size: 12px;text-decoration: none;" href="javascript:a.collapseAll('${indexGroups[0].groupuuid}')">收起</a>
	<!-- <a style="font-size: 12px;text-decoration: none;float:right;" href="javascript:closeWindow()">关闭</a> -->
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a style="font-size: 12px;text-decoration: none;float:inheritc;margin-right: 10px;"; href="javascript:returnGroups()" >提交</a>
	</c:if>
		<script type="text/javascript">
        //*
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
       // alert(rootid);
       // a.autoSort=true;
        a.rootId=rootid;
        a.autoSort=false;
        a.useCheckbox=true;
        a.canOperate=true;
        document.write(a.render());
        a.expandLevel(1);
        <c:if test="${dsize >0}">
        a.expandAll('${indexGroups[0].groupuuid}');
        </c:if>
      </script>
      </div>
  </body>
     
</html>
