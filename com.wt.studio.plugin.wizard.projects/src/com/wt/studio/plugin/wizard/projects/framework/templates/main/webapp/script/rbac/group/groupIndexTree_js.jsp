<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
var tree1;
var tree2;
//左侧树初始化
$(document).ready(function() {
	tree1 = new dhtmlXTreeObject('sidebar1', "100%", "100%", 0);
	tree1.enableCheckBoxes(1);
	//tree1.enableThreeStateCheckboxes(true);   
	tree1.setImagePath("${pageContext.request.contextPath}/images/component/dhtmlxtree/csh_bluebooks/");
	tree1.setXMLAutoLoading("${pageContext.request.contextPath}/heaGroupAction.hea?action=loadNextNodes");
	tree1.loadXML("${pageContext.request.contextPath}/heaGroupAction.hea?action=initIndexGroupTree&groupId=${groupId}&type=5");//load root level from xml
	
	tree2 = new dhtmlXTreeObject('sidebar2', "100%", "100%", 0);
	tree2.enableCheckBoxes(1);
	//tree2.enableThreeStateCheckboxes(true);   
	tree2.setImagePath("${pageContext.request.contextPath}/images/component/dhtmlxtree/csh_bluebooks/");
	tree2.setXMLAutoLoading("${pageContext.request.contextPath}/heaGroupAction.hea?action=loadNextNodes");
	tree2.loadXML("${pageContext.request.contextPath}/heaGroupAction.hea?action=initIndexGroupTree&groupId=${groupId}&type=0");//load root level from xml
});

var selectIdsArray = new Array();
var sids = 0;
function openSelectedItem(){
	var selectIds1 = tree1.getAllChecked().split(',');
	for(var i = 0;i<selectIds1.length;i++){
		var hadId1 = false;
		for(var j = 0; j < selectIdsArray.length;j++){
			if(selectIds1[i] == selectIdsArray[j]){
				hadId1 = true;
				break;
			}
		}
		if(tree1.getIndexById(selectIds1[i])!=null && hadId1 == false){
			tree1.openItem(selectIds1[i]);
			selectIdsArray[sids] = selectIds1[i];
			sids++;
		}
	}
	var selectIds2 = tree2.getAllChecked().split(',');
	for(var i = 0;i<selectIds2.length;i++){
		var hadId2 = false;
		for(var j = 0; j < selectIdsArray.length;j++){
			if(selectIds2[i] == selectIdsArray[j]){
				hadId2 = true;
				break;
			}
		}
		if(tree2.getIndexById(selectIds2[i])!=null && hadId2 == false){
			tree2.openItem(selectIds2[i]);
			selectIdsArray[sids] = selectIds2[i];
			sids++;
		}
	}
	setTimeout('openSelectedItem()',2000);
}
setTimeout('openSelectedItem()',2000);

var bubs = new Array();
var bubIndex = 0;
function bubItemsId(){
	var bub = (tree1.getAllChecked() +',' + tree2.getAllChecked()).split(',');
	for(var i = 0; i < bub.length ;i++){
		var isBub = false;
		for(var j = 0; j < bubs.length ;j++){
			if(bub[i] == bubs[j]){
				isBub = true;
				break;
			}					
		}
		if(isBub == false){
			bubs[bubIndex] = bub[i];
			bubIndex++;
		}
	}
	document.updateIGForm.bubs.value = bubs;
	setTimeout('bubItemsId()',2000);	
}

setTimeout('bubItemsId()',3000);
function updateIGFormSubmit() {
	var tree1value = tree1.getAllChecked().replace('__Y__,','').replace('__Y__','');
	var tree2value = tree2.getAllChecked().replace('__Y__,','').replace('__Y__','');
	document.updateIGForm.selectedIndexIdsStr.value = tree1value + "," + tree2value;
	return true;
}

function switchShow(idNum){
	for(var i = 1;i <= 2;i++){
		if(idNum == i){
			document.getElementById("sidebar" + i).style.display = "block";
		}else{
			document.getElementById("sidebar" + i).style.display = "none";
		}
	}
}
</script>
