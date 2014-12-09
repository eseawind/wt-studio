
/**
 * 模板添加页面操作
 * @memberOf {TypeName} 
 */
function addTemplate(){
	
	/**
	 * 隐藏自动布局表单录入
	 */
	this.hideFix = function(){
		$("#fixlayoutfilepath").hide();
		//$("#fixlayoutfile").hide();
	}
	/**
	 * 隐藏固定表单录入
	 */
	this.showFix = function(){
		$("#fixlayoutfilepath").show();
		//$("#fixlayoutfile").show();
	}
	
}
$(function(){
	var at = new addTemplate();
	var dFilePath,dFile;
	var myParentNode ;
	$("#tmplTypeAuto").click(function(){
		if($("#tmplTypeAuto").attr("checked") == true){
			at.hideFix();
			myParentNode = $("#fixlayoutfilepath").prev();
			dFilePath = $("#fixlayoutfilepath").detach();
			//dFile = $("#fixlayoutfile").detach();
		}
	});
	$("#tmplTypeFix").click(function(){
		if($("#tmplTypeAuto").attr("checked") == false){
			if(dFilePath){
				dFilePath.appendTo("#tableform");
				myParentNode.after(dFilePath);
			//	dFile.appendTo("#tableform");
			}
			at.showFix();
		}
	});
	if($("#tmplTypeAuto").attr("checked") == true){
		at.hideFix();
		myParentNode = $("#fixlayoutfilepath").prev();
		dFilePath = $("#fixlayoutfilepath").detach();
	}
})