<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>
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
			src="${pageContext.request.contextPath}/view_ref/script/i_tree_extend/jsframework.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/view_ref/jui/cupertino/ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.draggable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.resizable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.dialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/external/bgiframe/jquery.bgiframe.js"></script>


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
		<script type="text/javascript">
		<bean:size id="dsize" name="allIndexs" />
 	  	var indexs="${indexs}".split('_');
 	    var hasIndex="";
   	 	function hasIndexF(indexuuid){
   	 		for(var i=0;i< indexs.length;i++){
   	 			hasIndex="";
   	 			if(indexuuid==indexs[i]){
   	 				hasIndex='checked';
   	 				break;
   	 			}
   	 		}
   	 	}
  
		
		var ad="${ad}";
			function changeGlob(){
					window.open("${pageContext.request.contextPath}/heaGroupAction.hea?action=toIndexSetting&groupuuid=${groupuuid}&ad="+ad,"_self");
			}
	
		function atimer(){
			var indexUuids=$("input[type='checkbox'][checked='true']");
			
			var selectCount=indexUuids.length;
			$("#selectIndexCount").attr('innerText','【'+selectCount+'】');
			setTimeout('atimer()',2000);
		}
		function returnIndexUuid(){
			var indexUuids=$("input[type='checkbox'][checked='true']");
			var indexStr='';
			for(var i in indexUuids){
				if(indexUuids[i].checked==true){
				indexStr+=indexUuids[i].value+"_";
				}
			}
			if(indexStr.length>0){
				indexStr=indexStr.substring(0,indexStr.length-1);
			}
			$("input[name='indexs']").attr('value',indexStr);
			$("form")[0].submit();
		}
		
		function expAll(){
			a.expandAll(rootid);
			setTimeout('collAll()',${dsize*10+1000});
		}
		function collAll(){
			a.collapseAll(rootid);
			$("#pinfenDiv").css('display','none');
			atimer();
		}
		
		$(function(){
			$("#dialog").dialog({
			bgiframe: true,
			modal: true,
			buttons: {
				取消: function() {
					$(this).dialog('close');
				},
				确认: function() {
					$(this).dialog('close');
					$("#pinfenDiv").css('display','block');
					setTimeout('returnIndexUuid()',${dsize*3});
					top.window.document.getElementById('divScreen').style.display='block';
				}
			}
			});
		}
		);
		
		function f1submit(){
			$("#dialog").dialog('open');
		}
		
		
	</script>
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
			jlc2(pid,ischecked);
			}
			jlc1(id,ischecked);
		}
	</script>
	</head>

	<body onload="expAll();">

		<html:form action="heaGroupAction.hea" method="post"
			onsubmit="return validateForm()">
			<html:hidden property="action" value="indexSetting" />
			<input type="hidden" name="indexs" />
			<input type="hidden" name="groupuuid"
				value="${groupuuid }">

		</html:form>
		
		指标总数:【${dsize}】
		<br>
		选中指标个数：<span id="selectIndexCount"></span>
		<div style="margin-left: 50px;">
			<script type="text/javascript">
	
	 var data={};
	 <c:if test="${dsize >0}">
	 	hasIndexF("${allIndexs[0].indexuuid}");
	 	data["-1_${allIndexs[0].indexuuid}"] = "text:${allIndexs[0].indexname};checked:"+hasIndex+";value:${allIndexs[0].indexuuid};";
	 	var rootid="${allIndexs[0].indexuuid}";
	
  <c:forEach begin="1" end="${dsize-1}" items="${allIndexs}" var="gitem">
  	hasIndexF("${gitem.indexuuid}");
	 data['${gitem.parentindexuuid}_${gitem.indexuuid}'] = "text: ${gitem.indexname };checked:"+hasIndex+";value:${gitem.indexuuid};";
</c:forEach>
   
</c:if>
</script>

			<c:if test="${dsize >0}">
				<a style="font-size: 12px; text-decoration: none;"
					href="javascript:a.expandAll('${allIndexs[0].indexuuid}')">展开</a>
				<a style="font-size: 12px; text-decoration: none;"
					href="javascript:a.collapseAll('${allIndexs[0].indexuuid}')">收起</a>
				<a
					style="font-size: 12px; text-decoration: none; margin-left: 100px;"
					href="javascript:f1submit()">提交</a>
			</c:if>
			<script type="text/javascript">
        //*
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data;
       // alert(rootid);
       // a.autoSort=true;
        a.useCheckbox=true;
        a.rootId=rootid;
        a.autoSort=false;
        a.canOperate=true;
        document.write(a.render());
        a.expandLevel(1);
</script>
		</div>
		<div id="dialog" title="指标分配" >
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		&nbsp;
	</p>
	<p>
		确定要修改子标关联吗 ？ 
	</p>
</div>
		<div id="pinfenDiv"
			style="z-index: 10; position: absolute; top: 10px; left: 0px; border:1px; height: 10000px;">
			<table height="100%" width="100%"  align="center">
				<tr height="60">
					<td width="200" ></td>
					<td>
						<img
							src="${pageContext.request.contextPath}/view_ref/images/spinner.gif" />
							请稍候!
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
	</body>
</html>
