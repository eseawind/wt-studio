<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
		//左侧树初始化
		$(document).ready(function(){
			
	var tree=new dhtmlXTreeObject('sidebar',"100%","100%",0);
	tree.setImagePath("${pageContext.request.contextPath}/images/component/dhtmlxtree/csh_bluebooks/");
	tree.setXMLAutoLoading("${pageContext.request.contextPath}/heaGroupAction.hea?action=initTreeGroup");
	tree.loadXML("${pageContext.request.contextPath}/heaGroupAction.hea?action=loadNextNodesGroup");//load root level from xml
	
	tree.setOnClickHandler(function(id){
				
				 	var url=this.getUserData(id,"url");
				 	if(url==null || url=="")return ;
					//var name= this.getItemText(id);
					document.getElementsByName('iframeContent')[0].src ="${pageContext.request.contextPath}/"+ url;
					});
						
	});       	
 </script>