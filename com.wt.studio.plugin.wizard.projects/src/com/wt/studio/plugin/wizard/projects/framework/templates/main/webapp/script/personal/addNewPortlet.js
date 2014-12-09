
var jQuery=$;

jQuery(function(){
	//让portletList页面中的分页生效
	var pageD = document.getElementById("page");
	var links = pageD.getElementsByTagName("a");

	for(var i = 0;i < links.length;i++){
		links[i].setAttribute("href",links[i].getAttribute("title")+"&state=page");
		links[i].onclick=null;
	}
	jQuery("#addPorltet").click(function(){
		//jQuery("#new_dialog").dialog("open");
				
		jQuery("#loading").ajaxStart(function(){
			jQuery(this).show();
		 });
		jQuery("#loading").ajaxComplete(function(){
			jQuery(this).hide();
		});
		jQuery("#loading").ajaxError(function(){
			jQuery(this).hide();
		});

		

		
		//打开新建dialog
		jQuery.ajax({
			url:'modules/personal/newPortlet.jsp',
			cache:false,
			success:function(data){
				jQuery("#new_dialog").empty().append(data);
				var portletType = jQuery("#new_dialog").find("#showterms"),  
				portletName = jQuery("#new_dialog").find("#portletName"),
				portletNo = jQuery("#new_dialog").find("#portletNo"),
				width = jQuery("#new_dialog").find("#width"),
				height = jQuery("#new_dialog").find("#height"),
				editAction = jQuery("#new_dialog").find("#editAction"),
				titleUrl = jQuery("#new_dialog").find("#titleUrl"),
				htmlCode = jQuery("#new_dialog").find("#htmlCode"),
				portletDesc = jQuery("#new_dialog").find("#portletDesc"),
				tips = jQuery("#new_dialog").find(".validateTips"),
				funcAction = jQuery("#new_dialog").find("#funcAction"),
				allFields = jQuery([]).add(portletType).add(portletName)
							.add(portletDesc).add(width).add(height).add(funcAction)
							.add(editAction).add(titleUrl);
				
				var checked="WebPortlet";
				
				jQuery("#new_dialog").find("tr:not('.normal')").hide();
				jQuery("#new_dialog").find("tr.webPortlet").show();


				encodeURI(allFields.eq(1));
				encodeURI(allFields.eq(2));
				
				//combobox切换处理
				jQuery("#new_dialog").find("#portletType").change(function(){
						if(jQuery(this).val()=="02"){
							jQuery("#new_dialog").find("tr.CMSPortlet:eq(0)").hide();
							jQuery("#new_dialog").find("tr:not('.normal')").hide();
							jQuery("#new_dialog").find("tr.wsrp").show();
							checked="02";
							portletType = jQuery("#new_dialog").find("#portletType");
							allFields = jQuery([]).add(portletType).add(portletName).add(portletDesc)
							.add(htmlCode);
							
						}else if(jQuery(this).val()=="03"){
							jQuery("#new_dialog").find("tr:not('.normal')").hide();
							jQuery("#new_dialog").find("tr.CMSPortlet").show();
							checked="03";
							portletType = jQuery("#new_dialog").find("#portletType");
							funcAction = jQuery("#new_dialog").find("#funcAction1");
							//funcAction.val("/CmsPortlet/setting/view.jsp?portletId=");
							//.attr("disabled","true");
							//editAction.val("/CmsPortlet/setting/edit.jsp?portletId=");
							//.attr("disabled","true");
							//funcAction.val(funcAction.val()+"?portletId=");
							//editAction.val(editAction.val()+"?portletId=");
							portletNo.val("").keydown(function(event){
								var e = event || window.event;
							if(!((event.keyCode>=65&&event.keyCode<=90)||
									(event.keyCode>=97&&event.keyCode<=122)||
									(event.keyCode>=48&&event.keyCode<=65))){
									event.keyCode=0;
									return false;
								}
								var char1 = convertKeyCode(e.keyCode);
								funcAction.val(funcAction.val()+char1);
								editAction.val(editAction.val()+char1);
							});
							allFields = jQuery([]).add(portletType).add(portletName).add(portletDesc)
							.add(funcAction).add(editAction);

						}else if(jQuery(this).val()=="01"){
							jQuery("#new_dialog").find("tr.CMSPortlet:eq(0)").hide();
							jQuery("#new_dialog").find("tr:not('.normal')").hide();
							jQuery("#new_dialog").find("tr.webPortlet").show();
							checked="01";
							portletType = $("#new_dialog").find("#showterms");
							funcAction = jQuery("#new_dialog").find("#funcAction");
							editAction.val("").removeAttr("disabled");
							
							allFields = jQuery([]).add(portletType).add(portletName).add(portletDesc)
							.add(width).add(height).add(funcAction)
							.add(editAction).add(titleUrl);

						}
					});
				
				jQuery("#dialog").dialog("destroy");

					

				
				jQuery("#new_dialog").dialog({
					title:'新建portlet',
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
						'重置':function(){
							allFields.val('').removeClass('ui-state-error');
							tips.empty().removeClass('ui-state-highlight');
						},
						'确定':function(){
							//验证数据
							var bValid = true;
							allFields.removeClass('ui-state-error');

							bValid = bValid && checkLength(portletName,"栏目名称",2,12);
							if(checked == "01"){
								bValid = bValid && checkRegexp(width,/^[0-9]{2,3}$/,"宽度只能输入数字,范围在10-900之间");
								bValid = bValid && checkRegexp(height,/^[0-9]{2,3}$/,"高度只能输入数字,范围在10-900之间");
								
								bValid = bValid && checkLength(jQuery("#funcAction"),"功能地址",6,100);
								bValid = bValid && checkLength(editAction,"编辑地址",6,100);
								bValid = bValid && checkLength(titleUrl,"标题地址",6,100);
								
							}else if(checked == "02"){
								bValid = bValid && checkLength(htmlCode,"html片段",6,4000);
							}else if(checked == "03"){
								bValid = bValid && checkLength(jQuery("#funcAction1"),"CmsPortlet地址",6,100);
								bValid = bValid && checkLength(editAction,"编辑地址",6,100);
							}
							bValid = bValid && checkLength(portletDesc,"栏目描述",6,4000);
							
							
							//    /^(http://).+/
//&lt &gt <>  " &quet
							
							

							if (bValid) {
								//表单提交
								if(checked == "01"){
									allFields = jQuery([]).add(portletType).add(portletName).add(portletDesc)
									.add(width).add(height).add(funcAction)
									.add(editAction).add(titleUrl);
									//jQuery.param(allFields).find(jQuery("#new_dialog").find("#portletType")) = $("#new_dialog").find("#showterms");
								}
								//allFields = jQuery([]).add(portletType = $("#new_dialog").find("#showterms"));
								jQuery.ajax({
									url:'heaPortletPropertyAction.hea?action=save&'+encodeURI(jQuery.param(allFields)),
									cache:false,
									type:'post',
									success:function(msg){
										if(msg != "1"){
											showMsg({msg:msg,title:"系统提示"});
										}else{
											showMsg({
												msg:'保存成功，确定继续添加，取消返回列表',
												buttons:{
													'取消':function(){
														location.href="heaPortletPropertyAction.hea?action=findAllPortlet&state=page";
														jQuery(this).dialog("close");
													},
													'确定':function(){
														allFields.val('').removeClass('ui-state-error');
														tips.empty().removeClass('ui-state-highlight');
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

				jQuery("#new_dialog").dialog("open");



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


				
			}
		});
		
		
	});
		
});