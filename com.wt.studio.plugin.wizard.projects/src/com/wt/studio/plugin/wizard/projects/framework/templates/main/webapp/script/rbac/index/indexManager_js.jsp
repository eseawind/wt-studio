<%@ page language="java"%>
<script type="text/javascript">
		function updateIndex(indexuuid){
			document.getElementById('iframeIndex').src = '${pageContext.request.contextPath}/heaIndexAction.hea?action=toUpdateIndex&indexuuid='+indexuuid;
		}
		
		function addIndex(indexuuid){
			document.getElementById('iframeIndex').src = '${pageContext.request.contextPath}/heaIndexAction.hea?action=toAddIndex&parentUuid=' + indexuuid;
		}
		
		function deleteIndex(indexuuid){
			if(confirm('删除指标将删除该指标的下级指标！')){
				document.getElementById('iframeIndex').src = '${pageContext.request.contextPath}/heaIndexAction.hea?action=deleteIndex&indexuuid=' + indexuuid;
			}
		}
		
		function updateIndexParent(indexuuid){
			document.getElementById('iframeIndex').src = '${pageContext.request.contextPath}/modules/rbac/index/updateIndexParent.jsp?indexuuid=' + indexuuid;
		}
		function updateIGTree(indexuuid){
			document.getElementById('iframeIndex').src = '${pageContext.request.contextPath}/heaIndexAction.hea?action=toIndexGroupTree&indexId=' + indexuuid;
		}
	</script>