jQuery(function(){
	
	//点击编辑按钮，加载数据，打开dialog
	jQuery("a.edit").click(function(){
		//modify:xiaoqi
		editPortlet(jQuery(this));
	});
/**
 * 编辑portlet
 * modify:xiaoqi
 */	
function editPortlet(cur){
//	var cur = jQuery(this);
	jQuery.ajax({
		cahce:false,
		url:'heaPortletPropertyAction.hea',
		type:'get',
		data:{action:'findById',watchType:'json',portletId:cur.attr("portletId")},
		dataType:'json',
		success:function(port){
			
			jQuery.ajax({
				url:'modules/personal/newPortlet.jsp',
				cache:false,
				success:function(data){
					jQuery("#new_dialog").empty().append(data);
		

					var portletType = jQuery("#new_dialog").find("#portletType"), 
					portletName = jQuery("#new_dialog").find("#portletName"),
					width = jQuery("#new_dialog").find("#width"),
					height = jQuery("#new_dialog").find("#height"),
					editAction = jQuery("#new_dialog").find("#editAction"),
					titleUrl = jQuery("#new_dialog").find("#titleUrl"),
					htmlCode = jQuery("#new_dialog").find("#htmlCode"),
					portletDesc = jQuery("#new_dialog").find("#portletDesc"),
					funcAction = jQuery("#new_dialog").find("#funcAction"),
					allFields = jQuery([]).add(portletType).add(portletName).add(portletDesc)
								.add(width).add(height).add(funcAction)
								.add(editAction).add(titleUrl),
					tips = jQuery("#new_dialog").find(".validateTips");
					
					
					
					
					var targetValue = '' ;
					jQuery("#new_dialog").find("tr.CMSPortlet:eq(0)").remove();
					switch(port.portletType.substring(0,2)){
						case "01":{
							portletName.val(port.portletName);
							width.val(port.width);
							height.val(port.height);
							titleUrl.val(port.titleUrl);
							editAction.val(port.editAction);
							funcAction.val(port.funcAction);
							portletDesc.val(port.portletDesc);
							jQuery("#new_dialog").find("tr:not('.normal')").hide();
							jQuery("#new_dialog").find("tr.webPortlet").show();
							cnt='01';
							$("#new_dialog").find("#showterms").val(port.portletType);
							
							funcAction = jQuery("#new_dialog").find("#funcAction");
							
							allFields = jQuery([]).add($("#new_dialog").find("#showterms").val()).add(portletName).add(portletDesc)
							.add(width).add(height).add(funcAction)
							.add(editAction).add(titleUrl);
							break;
						}
						case "03":{

							jQuery("#new_dialog").find("tr.CMSPortlet:eq(0)").hide();
							jQuery("#new_dialog").find("tr:not('.normal')").hide();
							jQuery("#new_dialog").find("tr.CMSPortlet").show();
							jQuery("#new_dialog").find("#funcAction1").val(port.funcAction).attr("disabled","true");
							
							editAction.val(port.editAction).attr("disabled","true");
							portletName.val(port.portletName);
							portletDesc.val(port.portletDesc);
							cnt='03';
							

							funcAction = jQuery("#new_dialog").find("#funcAction1");
							allFields = jQuery([]).add(portletType).add(portletName).add(portletDesc)
							.add(funcAction).add(editAction);
							break;
						}
						case "02":{
							jQuery("#new_dialog").find("tr:not('.normal')").hide();
							jQuery("#new_dialog").find("tr.wsrp").show();
							portletName.val(port.portletName);
							jQuery("#htmlCode").val(port.htmlCode);
							portletDesc.val(port.portletDesc);
							cnt='02';

							allFields = jQuery([]).add(portletType).add(portletName).add(portletDesc)
							.add(htmlCode);
							break;
						}
					}
					//portletType.get(0).selectIndex = cnt;
					setSelectOption("portletType",cnt);
					
					
					
					jQuery("#new_dialog").dialog({
						title:'编辑portlet',
						width:'600',
						height:'500',
						autoOpen:false,
						model:true,
						resizable:false,
						buttons:{
							'取消':function(){
								allFields.val('').removeClass('ui-state-error');
								tips.empty().removeClass('ui-state-highlight');
								jQuery("#new_dialog").dialog("close");
							},
							'保存':function(){
								//验证数据
								var bValid = true;
								allFields.removeClass('ui-state-error');
	
								bValid = bValid && checkLength(portletName,"栏目名称",2,12);
	
								if(port.portletType.substring(0,2) == "01"){
									bValid = bValid && checkRegexp(width,/^[0-9]{2,3}$/,"宽度只能输入数字,范围在10-900之间");
									bValid = bValid && checkRegexp(height,/^[0-9]{2,3}$/,"高度只能输入数字,范围在10-900之间");
									
									bValid = bValid && checkLength(jQuery("#funcAction"),"功能地址",6,100);
									bValid = bValid && checkLength(editAction,"编辑地址",6,100);
									bValid = bValid && checkLength(titleUrl,"标题地址",6,100);
									
								}else if(port.portletType == "02"){
									bValid = bValid && checkLength(htmlCode,"html片段",20,4000); 
								}else if(port.portletType == "03"){
									bValid = bValid && checkLength(jQuery("#funcAction1"),"CmsPortlet地址",6,100);
									bValid = bValid && checkLength(editAction,"编辑地址",6,100);
								}
								bValid = bValid && checkLength(portletDesc,"栏目描述",6,4000);
								//    /^(http://).+/
	
								
								if (bValid) {
									if(port.portletType.substring(0,2) == "01"){
										portletType = jQuery("#new_dialog").find("#showterms");
										allFields = jQuery([]).add(portletType).add(portletName).add(portletDesc)
										.add(width).add(height).add(funcAction)
										.add(editAction).add(titleUrl);
									}
									
									//表单提交
									jQuery.ajax({
										cache:false,
										url:'/hea/heaPortletPropertyAction.hea?action=update&portletProperty.portletId='+cur.attr("portletId")+'&'+encodeURI(jQuery.param(allFields)),
										type:'get',
										success:function(msg){
											if(msg != "1"){
												showMsg({msg:msg,title:"系统错误，请联系管理员"});
											}else{
												showMsg({
													msg:'保存成功',
													buttons:{
														'确认':function(){
															location.href="heaPortletPropertyAction.hea?action=findAllPortlet&state=page";
															jQuery(this).dialog("close");
														}
													}
												});
											}
										}
										
									});
									
								}
							}
							
						},
						close:function(){
							allFields.val('').removeClass('ui-state-error');
						}
					});
					function updateTips(t) {
						tips
							.text(t)
							.addClass('ui-state-highlight');
						setTimeout(function() {
							tips.removeClass('ui-state-highlight', 1500);
						}, 500);
					}
	
					function checkLength(o,n,min,max) {
	
						if ( o.val().length > max || o.val().length < min ) {
							o.addClass('ui-state-error');
							updateTips(n + "的长度必须在"+min+"到"+max+"之间.");
							return false;
						} else {
							return true;
						}
	
					}
	
					function checkRegexp(o,regexp,n) {
	
						if ( !( regexp.test( o.val() ) ) ) {
							o.addClass('ui-state-error');
							updateTips(n);
							return false;
						} else {
							return true;
						}
	
					}

					portletType.attr("disabled","true");
					jQuery("#new_dialog").dialog("open");
						
				}
			});
			
		}
	});
}
});
