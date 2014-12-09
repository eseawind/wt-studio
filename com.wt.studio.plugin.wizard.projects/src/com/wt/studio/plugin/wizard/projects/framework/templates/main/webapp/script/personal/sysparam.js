$(function(){

	$("#dialog-message").dialog({
		autoOpen:false,
		modal: true,
		resizable:false,
		width:300,
		height:200,
		buttons: {
			确定: function() {
				$(this).dialog('close');
			}
		}
	});
	
	if($(":checkbox[name='isPersonal']").attr("checked")){
		$("#personal").show();
		$("#personal").next().show();
	}else{
		
		$("#personal").hide();
		$("#personal").next().hide();
		//cancelCheck();
	}
		$("tr:gt(0):even").addClass("col-out");
		$("tr:gt(0):odd").addClass("col-on");
});
function mysubmit(){
		var ids = "";
		$(":checkbox[checked]").each(function(i,p){
			ids += p.id+ ",";
			});
		$.post("heaPersonalSysParamAction.hea",{action:"update",pspIds:ids},function(data){
			if(data == "1"){
				//alert("保存成功");
				$(".ui-icon-alert").next().text("修改成功");
				$("#dialog-message").dialog("open");
			}else{
				$(".ui-icon-alert").next().text("修改失败");
				$("#dialog-message").dialog("open");
			}
		});
	}

	function addEve(ch){
		if(ch.checked){
			$("#personal").show();
			$("#personal").next().show();
		}else{
			cancelCheck();
			$("#personal").next().hide();
			$("#personal").hide();
		}
	}
  	//给表格隔行换色，如果允许个性化则显示下面的配置
	
	//当不允许用户个性化时取消个性化设置
	function cancelCheck(){
		//alert($(":checkbox[checked]"));
		$(":checkbox[checked]").each(function(i,p){
//			alert($(p.parentNode.parentNode).attr("id"));
			if($(p.parentNode.parentNode).attr("id") == "tr1"){
				p.checked = false ;
				///alert(p.checked);
			}
//			alert(p.checked);
		});
	}
	
	
