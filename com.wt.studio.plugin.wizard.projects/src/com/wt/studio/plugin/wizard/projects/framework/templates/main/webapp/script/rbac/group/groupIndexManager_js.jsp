<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
var tree;
//左侧树初始化
$(document).ready(function() {

		tree = new dhtmlXTreeObject('sidebar', "100%", "100%", 0);
		//tree.enableCheckBoxes(1);
		//tree.enableThreeStateCheckboxes(true);   
		tree.setImagePath("${pageContext.request.contextPath}/images/component/dhtmlxtree/csh_bluebooks/");
		tree.setXMLAutoLoading("${pageContext.request.contextPath}/heaGroupAction.hea?action=loadNextNodesGroup");
		tree.loadXML("${pageContext.request.contextPath}/heaGroupAction.hea?action=initGroupTree");//load root level from xml
		tree.setOnClickHandler(function(id) {
			if(id == '__Y__'){
				 document.getElementsByName('iframeContent')[0].src = '';
				 return;
			}
		 	var gid=this.getUserData(id,"groupUuid");
		 	if( gid==null || gid==""){
		 		return ;
		 	}
			document.getElementsByName('iframeContent')[0].src ="${pageContext.request.contextPath}/heaGroupAction.hea?action=toIndexGroupTree&groupId="+ gid;
		});
});

function updateIGFormSubmit() {
	document.updateIGForm.selectedGroupIdsStr.value = tree.getAllChecked().replace('__Y__,','').replace('__Y__','');
	return true;
}
</script>
