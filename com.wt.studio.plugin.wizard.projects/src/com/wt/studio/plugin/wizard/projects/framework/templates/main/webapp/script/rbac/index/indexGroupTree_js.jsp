<%@ page language="java"%>
<script type="text/javascript">
			var tree;
			//左侧树初始化
			$(document).ready(function() {
				tree = new dhtmlXTreeObject('sidebar', "100%", "100%", 0);
				tree.enableCheckBoxes(1);
				//tree.enableThreeStateCheckboxes(true);   
				tree.setImagePath("${pageContext.request.contextPath}/images/component/dhtmlxtree/csh_bluebooks/");
				tree.setXMLAutoLoading("${pageContext.request.contextPath}/heaIndexAction.hea?action=indexGroupTreeNode&indexId=${indexId}");
				tree.loadXML("${pageContext.request.contextPath}/heaIndexAction.hea?action=initIndexGroupTree&indexId=${indexId}");//load root level from xml
			});
			
			function updateIGFormSubmit() {
				document.updateIGForm.selectedGroupIdsStr.value = tree.getAllChecked().replace('__Y__,','').replace('__Y__','');
				return true;
			}
			
			var bubs = new Array();
			var bubIndex = 0;
			function bubItemsId(){
				var bub =  tree.getAllChecked().replace('__Y__,','').replace('__Y__','').split(',');
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
		</script>