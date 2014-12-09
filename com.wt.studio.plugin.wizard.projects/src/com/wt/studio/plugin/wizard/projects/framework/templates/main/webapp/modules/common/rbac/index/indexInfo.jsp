<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'rbac/index/indexInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link type="text/css" href="${pageContext.request.contextPath}/view_ref/jui/cupertino/ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.draggable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.resizable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/ui.dialog.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/jui/external/bgiframe/jquery.bgiframe.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/tree/jsframework.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/rbac/ajaxupload.3.5.js"></script>
	
	<style type="text/css">
		body{
			padding:0;
			margin: 0;
			width: 100%;
			height: 100%;
			font-size: 12px;
		}
		
		.tdname{
			background-color: #F2F2F2;
			
		}
		
		.tbotton{
			border-style:ridge;
			height: 18px;
		}
		
		.tabletd{
			border-bottom-style: solid;
			border-bottom-width: 1px;
			border-bottom-color: #CDC9C9;
		}
		
    	.deleteImg {
    		cursor: pointer;
    	}
    	#uploaddiv img {
    		margin-left: 20px;
    	}
		td{
			font-size: 12px;
		}
	</style>
	<script type="text/javascript">
            function chooseParentIndexUuid(){
                   document.getElementById('parentIndexTree').style.display='block';
	  	  	    // var  isg=document.getElementById('isadmin').checked;
	  	  	    var  indexuuid=document.getElementById('parentindexuuid').value;
	  	  	     //  alert(indexuuid);
	  	  	    // if(isg){
    			   // window.open('${pageContext.request.contextPath}/ulIndex.do?method=showIndexTree&indexRadio=rads&parentindexuuid=${index.parentindexuuid}','chooseParentIndexTree');
	  	  	    // }else{
    			   window.open('${pageContext.request.contextPath}/heaIndexAction.hea?action=pointParentIndex&indexRadio=rads&parentindexuuid=${index.parentindexuuid}&indexuuid=${index.indexuuid}','chooseParentIndexTree');
	  	  	    // }
    			   document.getElementById('parentIndexTree').style.display='block';
    			   // alert(document.getElementById('parentIndexTree').style.display);
    		}
			//指标修改js
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
						//document.forms[0].submit();
						indexForm.action="${pageContext.request.contextPath}/heaIndexAction.hea?action=editIndex&indexuuid=${index.indexuuid}";
				        indexForm.submit();
					}
				}
				});
			}
			);
			function f1submit(){
			         var numberpattern =/^[0-9]+$/; 
				     var  nameValue= document.getElementById('indexname').value;
				     var  parentIndexuuid= document.getElementById('parentindexuuid').value;
                     var  orderNum =document.getElementById('indexorder').value;
                     if(nameValue.length< 1){
                          alert('请填写指标名字！');
                     }else if(parentIndexuuid.length<1){
                     	  alert('请选择父级指标!');
                     }else if(!numberpattern.test(orderNum)){
                          alert('请输入数字！');
                     }else{
                          $("#dialog").dialog('open');
				      }					    
			
			   
			}
			
		//指标删除js
		$(function(){
				$("#dialogDelete").dialog({
				bgiframe: true,
				modal: true,
				buttons: {
					取消: function() {
						$(this).dialog('close');
					},
					确认: function() {
						$(this).dialog('close');
						window.open('${pageContext.request.contextPath}/heaIndexAction.hea?action=deleteIndex&indexuuid=${index.indexuuid}' ,'contentWindow');
					}
				}
				});
			}
			);
		function deleteSubmit(){
			    $("#dialogDelete").dialog('open');
			}

		$(document).ready(function(){
			$("input[name='frame']").bind('click',function(){
				var t=$("input[name='frame'][checked='true']");
				$("#index_target")[0].value= t[0].value;
			});
		});
	</script>
 </head>
  
  <body >
  
  	<div id="dialog" title="指标修改" >
		<p>
			<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
			&nbsp;
		</p>
		<p>
			确定要修改指标：<b> ${index.indexname}</b> 
			的信息吗？ 
		</p>
   </div>
   <div id="dialogDelete" title="指标删除" >
		<p>
			<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
			&nbsp;
		</p>
		<p>
			确定要删除指标：<b>${index.indexname}</b> 
		    以及<b> 所属指标</b>吗？<font color="red">请谨慎操作！</font> 
		</p>
   </div>



  	<html:form action="heaIndexAction.hea" method="post"  styleId="indexForm" enctype="multipart/form-data">
  	      <input type="hidden" name="indexuuid"  value="${index.indexuuid}" >
  	<div>
  		<table id="userInfo" border="0">
  			
  			<tr height="20">
  				<td width="40;">&nbsp;</td>
  				<td width="130;" style="padding-left: 100px"><h4>资源信息</h4></td>
  				<td>
  				    <input  type="button"   class="tbotton" value="删除资源" onclick="deleteSubmit()" >
  				</td>
  			</tr>
  			
  			<tr  height="20">
  				<td class="tdname" align="left">资源链接名</td>
  				<td class="tabletd" colspan="2">
  					<input type="hidden" name="index.indexuuid" value="${param.indexuuid }"/>
  					<input type="text" id="indexname" name="index.indexname" value="${index.indexname}" >
  				</td>
  			</tr>
  			
  			
  			<tr  height="50">
  				<td class="tdname" align="left">资源链接地址</td>
  				<td>
  				   <textarea rows="6" cols="30" id="indexurl" name="index.indexurl">${index.indexurl}</textarea>
  			    </td>
  				<td width="100px" class="tabletd" align="right"></td>
  			</tr>
  			
  			<tr  height="20">
  				<td  width="100px" class="tdname" align="left">链接链接图标</td>
  				<td colspan="2">
  					<html:file property="file" />
  					<input type="hidden"  size="80" id="indexicon" name="index.indexicon" value="${index.indexicon}" >
	  			</td>
  			</tr>
  			
  			
  			<tr  height="20">
  				<td class="tdname" align="left">父级链接</td>
  				<td class="tabletd" >
  				  <input  type="text" name="parentindexName"  size="16"  disabled="disabled" value="${index.parentIndex.indexname}">
  				  <input  type="hidden" id="parentindexuuid" name="index.parentindexuuid"  size="40" value="${index.parentindexuuid}">
  				</td>
  				<td><input type="button" class="tbotton" value="修改父级" onclick="chooseParentIndexUuid()"> </td>
  			</tr>
  			<tr  height="20">
  				<td class="tdname" align="left">目标窗口</td>
  				<td class="tabletd" colspan="2">
					 <input type="radio" name="frame" value="mainFrame" ${index.target eq 'mainFrame' ? "checked='checked'" : ""}>mainFrame
					 <input type="radio" name="frame" value="leftFrame" ${index.target eq 'leftFrame' ? "checked='checked'" : ""}>leftFrame
					 <input type="radio" name="frame" value="topFrame" ${index.target eq 'topFrame' ? "checked='checked'" : ""}>topFrame
					 <input type="radio" name="frame" value="footFrame" ${index.target eq 'footFrame' ? "checked='checked'" : ""}>footFrame
					 <br></br>自定义
					 <input id="index_target" name="index.target" value="${index.target }" >
					 
  				</td>
  			</tr>
  			<tr  height="20">
  				<td class="tdname" align="left">排序号</td>
  				<td class="tabletd" colspan="2">
  				   <input type="text"  size="2" id="indexorder" name="index.indexorder"  value="${index.indexorder}"  >
					
  				</td>
  			</tr>
  			<tr  height="20">
  				<td class="tdname" align="left">是否禁用</td>
  				<td class="tabletd">
					 启用<input  type="radio" name="index.way"   value="1" checked='checked'>&nbsp;&nbsp;
					 禁用<input  type="radio" name="index.way"  value="0"  ${index.way eq '0' ? 'checked=checked':''} >
  				</td>
  				<td><input  type="button"   class="tbotton" value="保存修改" onclick="f1submit()"></td>
  			</tr>
  		</table>
  	</div>
  	</html:form>
  	<div id="parentIndexTree" style="display:none;position:absolute;top:0px;right: 0px;z-index: 3;height: 100%">
  		<iframe name="chooseParentIndexTree" width="220" height="91%"  frameborder="0"></iframe>
  	</div>
  </body>
  
</html>
