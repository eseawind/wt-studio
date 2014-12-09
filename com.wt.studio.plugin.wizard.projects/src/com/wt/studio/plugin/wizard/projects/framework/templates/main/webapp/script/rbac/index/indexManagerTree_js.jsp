<%@ page language="java"%>
<%
	String indexType = request.getParameter("indexType");
	request.setAttribute("indexType",indexType);
 %>
<script type="text/javascript">
		//左侧树初始化
		$(document).ready(function(){
			
	var tree=new dhtmlXTreeObject('sidebar',"100%","100%",0);
	tree.setImagePath("${pageContext.request.contextPath}/images/component/dhtmlxtree/csh_bluebooks/");
	tree.setXMLAutoLoading("${pageContext.request.contextPath}/heaIndexAction.hea?action=loadNextNodesIndex&indexType=${indexType}");
	tree.loadXML("${pageContext.request.contextPath}/heaIndexAction.hea?action=initTreeIndex&indexType=${indexType}");//load root level from xml
	
	tree.setOnClickHandler(function(id){
					if(id == '__Y__'){
						 document.getElementsByName('iframeContent')[0].src = '';
						 return;
					}
					document.getElementsByName('iframeContent')[0].src = '${pageContext.request.contextPath}/modules/rbac/index/indexManager.jsp?indexId=' + id;
				});
						
	});       	
 </script>