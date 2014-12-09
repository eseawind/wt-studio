<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'index/chooseParentIndex.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/i_tree/jsframework.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/script/jquery-1.3.2.js"></script>
	</head>

	<body>


	<script type="text/javascript">
			function closeWindow(){
			   parent.window.document.getElementById('parentIndexTree').style.display="none";
			}
			 	function  returnRadioIndex(){
		   			var index=$("input[checked='true'][name='mzRadio']")[0].value.split('_');
		   			var parentindexuuid= parent.window.document.getElementById('parentindexuuid').value;
		   			if(parentindexuuid==index[0]){
						alert('不能将自己设置为父结点!');
						return ;	
			   		}
		   			   parent.window.document.getElementById('parentindexuuid').value=index[0];
		   			   parent.window.document.getElementById('parentindexName').value=index[1];
		   			   //alert(index[0]+"_"+index[1]);
		   	}
				 var radioParentindexuuid="${radioParentindexuuid}";
		    	 var hasindex="";
		   		 function hasIndexF(indexuuid){
		   		      hasindex="";
		   		      if(indexuuid==radioParentindexuuid){
		   		       hasindex='checked';
		   		      }
		   		 }
			 <bean:size id="dsize" name="indexs" />
			 var data={};
			 <c:if test="${dsize >0}">
			 	hasIndexF("${indexs[0].indexuuid}");
			 	data["-1_${indexs[0].indexuuid}"] = "text:${indexs[0].indexname};checked:"+hasindex+";value:${indexs[0].indexuuid};";
			 	var rootid="${indexs[0].indexuuid}";
			
		 	 <c:forEach begin="1" end="${dsize-1}" items="${indexs}" var="iitem">
		        hasIndexF("${iitem.indexuuid}");
			    data['${iitem.parentindexuuid}_${iitem.indexuuid}'] = "text: ${iitem.indexname };checked:"+hasindex+";value:${iitem.indexuuid};";
			</c:forEach>
		   </c:if>
	</script>
		<c:if test="${dsize >0}">
			<a style="font-size: 12px; text-decoration: none;"
				href="javascript:a.expandAll('${indexs[0].indexuuid}')">展开</a>
			<a style="font-size: 12px; text-decoration: none;"
				href="javascript:a.collapseAll('${indexs[0].indexuuid}')">收起</a>
		
		<a style="font-size: 12px; text-decoration: none; float: right;"
			href="javascript:closeWindow()">关闭</a>
		</c:if>
		<c:if test="${dsize >0}">
			<a
				style="font-size: 12px; text-decoration: none; float: right; margin-right: 10px"
				href="javascript:returnRadioIndex()">确认</a>
		</c:if>
     <script type="text/javascript">
        Using("System.Web.UI.WebControls.MzTreeView");
        var a = new MzTreeView();
        a.dataSource = data; 
       // alert(rootid);
       // a.autoSort=true;
        a.rootId=rootid;
        a.useRadio=true;
        //a.useCheckbox=true;
        a.canOperate=true;
        document.write(a.render());
        a.expandLevel(1);
    </script>
	</body>

</html>
