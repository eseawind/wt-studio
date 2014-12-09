<%@ page language="java"%>
<%
	String indexType = request.getParameter("indexType");
	request.setAttribute("indexType",indexType);
 %>
<script type="text/javascript">
		
		var selectId;
		var tree;
		//左侧树初始化
		function initilTree(){
			tree =new dhtmlXTreeObject('divTree',"100%","100%",0);
			tree.setImagePath("${pageContext.request.contextPath}/images/component/dhtmlxtree/csh_bluebooks/");
			tree.setXMLAutoLoading("${pageContext.request.contextPath}/heaIndexAction.hea?action=loadTreeNodeChooseParentIndex");
			tree.loadXML("${pageContext.request.contextPath}/heaIndexAction.hea?action=initTreeChooseParentIndex&indexId=${param.indexuuid}");//load root level from xml
			//tree.enableRadiobuttons();
			tree.enableRadioButtons(true);
			setTimeout('setSelectedRedio()',2000);
		}				
		
		
		function setSelectedRedio(){
			var selectIds =  tree.getAllChecked().split(',');
			if(selectIds.length>1){
				var num = 1;
				for(var i = 0;i<selectIds.length;i++){
					if(selectIds[i] != selectId && num == 1){
						selectId = selectIds[i];
						num++;
					}else{
						tree.setCheck(selectIds[i],false);
					}						
				}
			}else if(selectIds.length = 1){
				selectId = selectIds[0];
			}
			document.forms[0].indexPId.value = selectId;
			if(tree.getIndexById(selectId)){
				tree.setCheck(selectId,true);
			}
			setTimeout('setSelectedRedio()',300);
		}
 </script>