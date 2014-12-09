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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx}/view_ref/script/g_tree_extend/jsframework.js"></script>
	<script type="text/javascript" src="${ctx}/view_ref/script/jquery-1.3.2.js"></script>
  </head>
  
  <body>
  
  	
   	<script type="text/javascript">
	var  cl1,cl2;
	function jlc1(id,istrue){
		cl1=$("input[type='checkbox'][id^='"+id+"_']");
			for(var i=0;i<cl1.length;i++){
				//alert(cl1[i].id);
				cl1[i].checked=istrue;
				setTimeout("jlc1('"+cl1[i].id.split('_')[1]+"',"+istrue+")",8);
			}
	}
	
	function jlc2(id,istrue){
		 cl2=$("input[type='checkbox'][id$='_"+id+"']");
		if(cl2.length>0){
				cl2[0].checked=istrue;
				//jlc2(cl2[0].id.split('_')[0],istrue);
				setTimeout("jlc2('"+cl2[0].id.split('_')[0]+"',"+istrue+")",8);
		}
	}
	function  jlc(pid,id){
		var cl=$("#"+pid+"_"+id)[0];
		//alert(cl.id);
		var ischecked=cl.checked;
		if(ischecked){
		jlc2(pid,false);
		}
		jlc1(id,false);
	}

	
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

   	
   	function returnGroups(){
   		var indexidss=$(":checkbox");
			var ids="";
			var alink="&nbsp;";
			var icount=0;
			for(var i in indexidss){
				if(indexidss[i].checked==true){
				ids+=indexidss[i].value;
				ids+="_";
				alink+="<a href='ulUser.do?method=toIndexsView&groupIds="+indexidss[i].value+"' target='chooseGroupTree'>"+indexidss[i].title+"</a> "
				icount++;
				}
			}
			if(ids.length>1){
				ids=ids.substring(0,ids.length-1);
			}
			//alert(ids);
			parent.window.document.getElementById('groupIds').value=ids;
			parent.window.document.getElementById('groupNames').innerHTML=alink;
			//alert('选中组个数：'+icount);
			//parent.window.document.getElementById('groupTree').style.display="none";
   	}
   	
   	
   	
    	var groupUuids=new Array();
    	<c:forEach items="${userForm.user.groups}" var="gitem" varStatus="gi">
		     groupUuids['${gi.count}']='${gitem.uuid}';
		</c:forEach>
    	 var hasgroup="";
   	 function hasgroupF(groupuuid){
   	 	for(var i=0;i< groupUuids.length;i++){
   	 		hasgroup="";
   	 		if(groupuuid==groupUuids[i]){
   	 			hasgroup='checked';
   	 			break;
   	 		}
   	 	}
   	 }
   	var rootIds=new Array();
	var data={};
   	<c:forEach  items="${requestScope.groupLists}" var="glist" varStatus="gstatus">
	 	<bean:size id="dsize" name="glist"/>
		<c:if test="${dsize >0}">
			rootIds[${gstatus.count-1}]="${glist[0].uuid}";
		 	hasgroupF("${glist[0].uuid}");
		 	data["-1_${glist[0].uuid}"] = "text:${glist[0].name};checked:"+hasgroup+";value:${glist[0].uuid};";
			 <c:forEach begin="1" items="${glist}" var="gitem">
			     hasgroupF("${gitem.uuid}");
				 data['${gitem.parentuuid}_${gitem.uuid}'] = "text: ${gitem.name };checked:"+hasgroup+";value:${gitem.uuid}";
			</c:forEach>
   		</c:if>
	</c:forEach>
    
	</script>
	<a style="font-size: 12px;text-decoration: none;" href="javascript:treeOpenOrClose(true)">展开</a>
	<a style="font-size: 12px;text-decoration: none;" href="javascript:treeOpenOrClose(false)">收起</a>
	<a style="font-size: 12px;text-decoration: none;float:right;margin-right: 10px" href="javascript:returnGroups()" >确认</a>
	<script type="text/javascript">
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
        
        a.autoSort=false;
        a.useCheckbox=true;
        a.canOperate=true;
        document.write(a.render());
        treeOpenOrClose(true);
	</script>
  </body>
</html>
