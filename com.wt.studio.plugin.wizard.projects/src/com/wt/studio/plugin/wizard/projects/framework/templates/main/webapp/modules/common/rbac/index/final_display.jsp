<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>index/final_display.jsp</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/common/jquery-1.4.2.min.js"></script>
	<link id="themePath" rel="Stylesheet" href="${pageContext.request.contextPath}/css/theme/blue/subindex.css" />
	<!-- 资源列表表单模态层 -->
	<link type="text/css" href="${pageContext.request.contextPath }/css/theme/jquery/jquery.ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.bgiframe-2.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.ui.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.ui.button.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.ui.position.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.ui.resizable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery/jquery.effects.core.js"></script>
	<!-- 资源列表表单模态层 End; -->
	
	
	<script type="text/javascript">
/*漂亮表格动态效果功能函数*/
		/*
		$(function(){
			$("table:odd").addClass("p03");  //隔行换色处，额数行增加样式P03
			
			//鼠标经过样式变化处
			$(".dataGrid").hover( 
                function () { 
                    $(this).addClass("p02");   //鼠标经过时增加样式P02
                }, 
                function () { 
                    $(this).removeClass("p02"); //鼠标离开时移除样式P02
                }
            )
        })*/
			
        /*数据表格底部分页操作*/
        $(function(){
        	var url="heaIndexAction.hea?action=display";
        	var perPageRecord;
        	var currPageNum;
        	//跳转第几页
        	$("#btnJump").click(function(){
        		perPageRecord = $("#txtPerPageRecord")[0].value;
        		currPageNum = $("#txtCurrPageNum")[0].value;
        		treeNodeId = $("#treeNodeId")[0].value;
        		
        		window.open(url+"&treeNode="+treeNodeId+"&perPageRecord="+perPageRecord+"&currPageNum="+currPageNum,"_self");
        	});
        	//首页
        	$('#firstPage').click(function(){
        		perPageRecord = $("#txtPerPageRecord")[0].value;
        		currPageNum = 1;
        		treeNodeId = $("#treeNodeId")[0].value;
        		
        		window.open(url+"&treeNodeId="+treeNodeId+"&perPageRecord="+perPageRecord+"&currPageNum="+currPageNum,"_self");
        	});
        	//上页
        	$('#prePage').click(function(){
        		perPageRecord = $("#txtPerPageRecord")[0].value;
        		currPageNum = parseInt( $("#txtCurrPageNum")[0].value,10) - 1 ;
        		treeNodeId = $("#treeNodeId")[0].value;
        		window.open(url+"&treeNodeId="+treeNodeId+"&perPageRecord="+perPageRecord+"&currPageNum="+currPageNum,"_self");
        	});
        	//下页
        	$('#nextPage').click(function(){
        		perPageRecord = $("#txtPerPageRecord")[0].value;
        		currPageNum = parseInt( $("#txtCurrPageNum")[0].value,10) + 1 ;
        		treeNodeId = $("#treeNodeId")[0].value;
        		window.open(url+"&treeNodeId="+treeNodeId+"&perPageRecord="+perPageRecord+"&currPageNum="+currPageNum,"_self");
        	});
        	//末页
        	$('#lastPage').click(function(){
        		perPageRecord = $("#txtPerPageRecord")[0].value;
        		currPageNum = $("#lastPage")[0].title ;
        		treeNodeId = $("#treeNodeId")[0].value;
        		window.open(url+"&treeNodeId="+treeNodeId+"&perPageRecord="+perPageRecord+"&currPageNum="+currPageNum,"_self");
        	});
        	
        });
        
        
        
    //资源链接的CRUD操作
	$(function(){
		var url="";
		$(".imgDelete").click(function(){
			url=$(this).attr("title");
			var treeNodeId=$('#treeNodeId')[0].value;
			url=url+"&treeNodeId="+treeNodeId;
			$('#dialog-confirm').dialog('open');
			
		});
		
		/*
		$(".imgAdd").click(function(){
			var indexuuid=this.id.substring(4,this.id.length);
			alert(indexuuid);
			$("#parentindexuuid")[0].value=indexuuid;
			$('#dialog-form').dialog('open');
		});
		*/
		
		$(".imgUpdate").click(function(){
			var uid=$(this)[0].id;
			$('#dialog-form').dialog('open');
			$.ajax({
					cahce:false,
					url:'heaIndexAction.hea',
					type:'get',
					data:{action:'display',ops:'load',treeNodeId:$('#treeNodeId')[0].value,indexuuid:uid},
					dataType:'json',
					async: false,
					success:function(index){
						$("#dialog-form").find("#updateId").val(index.indexuuid);
						
						$("#dialog-form").find("#parentindexuuid").val(index.parentindexuuid);
						$("#dialog-form").find("#indexname").val(index.indexname);
						
						
						$("#dialog-form").find("#indexmappedurl").val(index.indexmappedurl);
						$("#dialog-form").find("#indexorder").val(index.indexorder);
						
						setSelectOption("indextype",index.indextype);
						$("#dialog-form").find("#indexurl").val(index.indexurl);
						$("#dialog-form").find("#target").text(index.target);
						setSelectOption("way",index.way);
						$("#dialog-form").find("#description").text(index.description);
						/*$("#dialog-form").find("#titleKeyHolderValue").val(index.titleKeyHolderValue);
						$("#dialog-form").find("#updateDate").val(index.updateDate);
						$("#dialog-form").find("#dataBlockComment").text(template.dataBlockComment);
						*/
						$('#dialog-form').dialog('open');
					}
			});
			
		});
		
		$("#addClickedNodeChilds").click(function(){
			$("#parentindexuuid")[0].value=$("#treeNodeId")[0].value;
			$('#dialog-form').dialog('open');
		});
		
		
		
		
		
		
		$("#dialog").dialog("destroy");
		$("#dialog-confirm").dialog("destroy")
		
		
		//初使化"子标删除提示对话框"
		$("#dialog-confirm").dialog({
				autoOpen: false,
				resizable: true,
				height:180,
				position: 'center',
				modal: true,
				buttons: {
					'确定': function() {
						$(this).dialog('close');
						window.open(url,"_self");
					},
					'取消': function() {
						$(this).dialog('close');
					}
				}
				});
		
		//初使化"子标添加表单"dialog
				var h=$(document.body).height(); 
				var w=$(document.body).width();
				h=400;
				w=420;
				
				$("#dialog-form").dialog({
				autoOpen: false,
				resizable: true,
				height:h,
				width: w,
				modal: true,
				position: 'top',
				buttons: {
					'关闭': function() {
						$(this).dialog('close');
					},
					'添加保存': function() {
						$("#ops").value="add";
						$("#indexForm").submit();
						
						$(this).dialog('close');
						
					},
					'修改提交': function() {
						$("#ops").val("update");
						$('#indexForm').submit();
						
						$(this).dialog('close');
					}
					
				}
				});

	});
	</script>
  </head>
  
  <body>
  	<div id="container" class="content_1">
  		<!-- 头部 -->
  		<div class="title">资源列表管理</div>
  		<input type="button" class="btn_3" value="删除" />
  		<input type="button" class="btn_3" value="修改" />
  		<input type="button" class="btn_3" value="新增"  id="addClickedNodeChilds"/>
		
  		<!-- <div  id="header">
  			<fieldset style="border: 2;">
  			<table width="650px" height="93px" >
  				<tr>
  					<td colspan="3" width="30%" valign="top" align="left">
  					<img src="${pageContext.request.contextPath}/view_ref/rbac/index/images/311.gif"/>
  					资源列表管理</td>
  				</tr>
  				<tr>
  					<td height="20px">&nbsp;</td>
  					<td>&nbsp;</td>
  					<td align="right">
  					<input type="checkbox"/>全选 
  					<span id="addClickedNodeChilds"><img src="${pageContext.request.contextPath}/view_ref/rbac/index/images/add.gif"/>新增&nbsp;&nbsp;</span>
  					<img src="${pageContext.request.contextPath}/view_ref/rbac/index/images/edit.gif"/>修改&nbsp;&nbsp;
  					<img src="${pageContext.request.contextPath}/view_ref/rbac/index/images/delete.gif"/>删除&nbsp;&nbsp;
  					</td>
  				</tr>
  			</table>
  			</fieldset>
	    </div> -->
	    <!-- 头部 End; -->
	    
	    <!-- 内容区 -->
	    <div id="mainContent">
	  					<div>
				  			<table  class="tab">
				  				<tr class="tab_title">
					  						<td >选择</td>
					  						<td>资源链接名称</td>
					  						<td >类型</td>
					  						<td >状态</td>
					  						<td >跳转方向</td>
					  						<td>层级数</td>
					  						<td >操作项</td>
					  			</tr>
								<c:forEach var="ele" items="${requestScope.page.data}" varStatus="c">
									<tr ${c.count % 2 == 0 ? "class='tab_trcurrent'" :"class=''" }>
											<td ><input class="checkedList" type="checkbox" value="${ele.indexuuid}"/></td>
											<td  align="left">${ele.indexname }</td>
											<td >
												<c:choose>
													<c:when test="${ele.indextype eq '0'}">链接</c:when>
													<c:when test="${ele.indextype eq '1'}">Html控件 </c:when>
													<c:when test="${ele.indextype eq '2'}">站点</c:when>
													<c:when test="${ele.indextype eq '3'}">菜单</c:when>
													<c:when test="${ele.indextype eq '4'}">文件模板类别</c:when>
													<c:otherwise>未知</c:otherwise>
												</c:choose>
											</td>
											<td ><font color="${ele.way == '0' ? 'red' : 'green'}">${ele.way == '0' ? '<b>禁用</b>' : '启用' }</font></td>
											<td >${ele.target }</td>
											<td >${ele.indexlevel }</td>
											
											<td >
												<!-- 
												<a class="imgAdd" id="add_${ele.indexuuid}" title="${pageContext.request.contextPath}/heaIndexAction.hea?action=display&ops=add&perPageRecord=${page.perPageRecord}&currPageNum=${page.currPageNum}&deleteId=${ele.indexuuid}"><img src="${pageContext.request.contextPath}/view_ref/rbac/index/images/add.gif"/>[新增下级]</a> -->
												<span class="imgUpdate"><a href="#" id="${ele.indexuuid}" title="${pageContext.request.contextPath}/heaIndexAction.hea?action=display&ops=update&perPageRecord=${page.perPageRecord}&currPageNum=${page.currPageNum}&updateId=${ele.indexuuid}"><img src="${pageContext.request.contextPath}/images/common/rowEdit.gif""/>[编辑]</a></span>
												<span  class="imgDelete"><a href="#" title="${pageContext.request.contextPath}/heaIndexAction.hea?action=display&ops=delete&perPageRecord=${page.perPageRecord}&currPageNum=${page.currPageNum}&deleteId=${ele.indexuuid}"><img src="${pageContext.request.contextPath}/images/common/rowDelete.gif""/>[删除]</a></span>
											</td>
									</tr>	
								</c:forEach>
								<tr  class="tab_trcurrent">
									<td  colspan="7" >
									<div class="position_cen">
										每页显示<input id="txtPerPageRecord"  class="num" size="1" maxlength="3" value="${page.perPageRecord}"/>条数据
										<a id="firstPage" href="#">首页</a>
										<span id="${page.hasPreviouPage == true ? 'prePage': 'noPrePage'}" >${page.hasPreviouPage == true ? '<a class="pre"  href="#"><img src="${pageContext.request.contextPath}/images/common/back.jpg"/>上页</a>': '无上页'}</span>
										<span id="${page.hasNextPage == true ? 'nextPage': 'noNextPage'}" >${page.hasNextPage == true ? '<a  class="next" href="#"><img src="${pageContext.request.contextPath}/images/common/go.jpg"/>下页</a>': '无下页'}</span>
										<a id="lastPage"  title="${page.totalPage}" href="#">末页</a>
										跳转<input id="txtCurrPageNum" size="4" class="num" maxlength="4" value="${page.currPageNum}"/>页<input id="btnJump" class="btn" type="button" value="确定">
									</div>
										</td>
										
										
										<!-- <td><a id="firstPage" >首页</a></td>
										<td>
											<span id="${page.hasPreviouPage == true ? 'prePage': 'noPrePage'}" >${page.hasPreviouPage == true ? '<a   href="#"><img src="${pageContext.request.contextPath}/images/common/back.jpg"/>上页</a>': '无上页'}</span>
										</td>
										<td><span id="${page.hasNextPage == true ? 'nextPage': 'noNextPage'}" >${page.hasNextPage == true ? '<a   href="#"><img src="${pageContext.request.contextPath}/images/common/go.jpg"/>下页</a>': '无下页'}</span></td>
										<td><span id="lastPage"  title="${page.totalPage}">末页</span></td>
										<td align="right">跳转<input id="txtCurrPageNum" size="4" maxlength="4" value="${page.currPageNum}"/>页<input id="btnJump" type="button" value="确定"></td>
									 	-->
									</tr>
							</table>
						
						
						</div>
								
		</div>
		<!-- 内容区 End; -->
	</div>
	

<!-- 资源删除 -->
<div id="dialog-confirm">
	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>删除该资源项，将会级联删除它子级所有资源项，是否删除所选的资源项？</p>
</div>
<!-- 测试 End-->



<!-- 资源添加,修改 -->
<div id="dialog-form" >
	<html:form action="heaIndexAction.hea?action=display"  enctype="multipart/form-data" styleId="indexForm" method="post">
		<input type="hidden" name="ops" value="add" id="ops">
		<input type="hidden" id="treeNodeId" name="treeNodeId" value="${param.treeNodeId == null ? parentIndex.indexuuid : param.treeNodeId}"/>
		<html:hidden property="index.indexuuid" value="${index.indexuuid}" styleId="updateId"></html:hidden>
		<table  cellspacing="0" style="float: left;text-align: left; margin: 0px 0px 0px 0px;">
			<tr>
				<td class="td_1">父项id:</td>
				<td  >
					<html:text  style="width:200px" styleId="parentindexuuid" property="index.parentindexuuid"  value="${param.treeNodeId == null ? parentIndex.indexuuid : param.treeNodeId}" readonly="true"></html:text></td>
			</tr>
			<tr>
				<td class="td_1">子标名称:</td>
				<td><html:text property="index.indexname" style="width:200px" value="${index.indexname}" styleId="indexname"></html:text></td>
			</tr>
			<tr>
				<td class="td_1">资源icon图标:</td>
				<td><html:file property="file" style="width:206px" /></td>
			</tr>
			<tr>
				<td class="td_1">url重写地址:</td>
				<td><html:textarea property="index.indexmappedurl" style="width:200px" value="${index.indexmappedurl}" styleId="indexmappedurl"></html:textarea></td>
			</tr>
			<tr>
				<td class="td_1">排序号:</td>
				<td><html:text property="index.indexorder"  style="width:200px" value="${index.indexorder }" styleId="indexorder"></html:text></td>
			</tr>
			<tr>
				<td class="td_1">资源类型:</td>
				<td>
					<html:select style="width:206px" property="index.indextype"  value="${index.indextype}" styleId="indextype">
						<html:option value="0" >URL链接</html:option>
						<html:option value="1">html控件类型</html:option>
						<html:option value="2">站点</html:option>
						<html:option value="3">菜单</html:option>
						<html:option value="4">文件模板类别</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="td_1">资源展现url:</td>
				<td><html:text style="width:200px" property="index.indexurl" value="${index.indexurl}" styleId="indexurl"></html:text></td>
			</tr>
			<tr>
				<td class="td_1">跳转的iframe或新浏览器:</td>
				<td><html:textarea property="index.target"  style="width:200px" value="${index.target}" styleId="target"></html:textarea></td>
			</tr>
			<tr>
				<td class="td_1">是否禁用:</td>
				<td>
					<html:select property="index.way" value="${index.way}" styleId="way">
						<html:option value="1">启用</html:option>
						<html:option value="0">禁用</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="td_1">备注:</td>
				<td>
					<html:textarea property="index.description" style="width:200px" value="${index.description}" styleId="description"></html:textarea>
				</td>
			</tr>
		</table>
		</html:form>
	</div>
  </body>
</html>
