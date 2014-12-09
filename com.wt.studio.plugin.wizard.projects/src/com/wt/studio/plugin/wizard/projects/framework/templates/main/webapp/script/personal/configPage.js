jQuery(function(){
	
	if(jQuery(".addcopyright").attr("copyrightUrl") != "该站点未设置版权信息"){
		jQuery.ajax({
			url:jQuery(".addcopyright").attr("copyrightUrl"),
			cache:false,
			success:function(data){
				jQuery(".addcopyright").html(data);
			},
			error:function(){
				jQuery(".addcopyright").html(jQuery(".addcopyright").attr("copyrightUrl"));
			}
		});
	}else{
		jQuery(".addcopyright").html(jQuery(".addcopyright").attr("copyrightUrl"));
	}
	
	//飘窗和侧帘
//	$(".curtain").load($(".curtain").html());
//	$(".floatwindow").load($(".floatwindow").html());
//	
	var curtainUrl = $(".curtain").attr("curtainUrl");
	if(curtainUrl != "" && curtainUrl != undefined){
		$(".curtain").load(curtainUrl);
	}
	var floatwinUrl = $(".floatwindow").attr("fwUrl");
	if(floatwinUrl != "" && floatwinUrl != undefined){
		$(".floatwindow").load(floatwinUrl);
	}
	var portletDialog = jQuery("#choosePortlet");
	//生成dialog
	portletDialog.dialog({
		autoOpen:false,
		title:'选择内容',
		height:600,
		width: 700,
		modal: true,
		resizable:false,
		buttons: {
			'关闭':function() {
				portletDialog.dialog('close');
			
			},
			'确定':function() {
				var curEdit = jQuery("div.curEditing");
				if(curEdit.attr("portletType") == "02"){
					refreshPortlet();
				}else{
					if( curEdit.attr("actionUrl") != undefined){
						refreshPortlet();
					}
				}
				curEdit.removeClass("curEditing");
				//	curEdit.append(configDiv);
				portletDialog.dialog('close');
			}
		}, 
		close:  close_handle//关闭事件
	});
	
	/**
	 * 注册编辑portlet地址
	 */
	regdialog();
	function regdialog(){
		jQuery("#editPortlet").dialog({
			position:['center'],
			autoOpen: false,
			title:'选择内容',
			height: 400,
			width: 500,
			modal: true,
			resizable:false,
			
			buttons:{
				'取消':function(){
					jQuery("#editPortlet").dialog("close");
					$("#editPortlet").dialog("destroy");
					//jQuery("div.curEditing").removeClass("curEditing");
				},
				'确定':function(){
					
					refreshPortlet();
					jQuery("#editPortlet").dialog("close");
					jQuery("#editPortlet").dialog("destroy");
					//jQuery("div.curEditing").removeClass("curEditing");
				}
			},
			close:close_handle
		});
	}
	var ie6style = "height:0px;overflow:hidden;font-size:0px";
	if($.browser.msie && $.browser.version != '6.0'){
		ie6style = "font-size:0px;height:0px";
	}
	//alert();
//	jQuery("div[prefix]").each(function(){
	jQuery("div[isEdit=1]").each(function(i,curEditing){
		var editTag = "<div class='configPortlet_' align='right' style='"+ ie6style +"'><img src='images/personal/edit_button.gif' style='position:relative;z-index:1;margin:0px 0px -16px;'></div>";
		jQuery(curEditing).append(editTag);
		//页面点击事件，每个模块点击后，弹出dialog选择内容
		jQuery(curEditing).children(".configPortlet_").unbind("click").click(function(){
			if(jQuery(curEditing).attr("portletType") != undefined){
				//alert(jQuery(curEditing).attr("tpId"));
				//添加portlet编辑页面
				regModifyPortlet(curEditing);
			}else{
				//注册添加portlet页面
				regConfigPortlet(curEditing);
			}
		});
	});
	/**
	 * 注册默认的配置portlet界面
	 */
	function regConfigPortlet(curEditing){
		jQuery(curEditing).addClass("curEditing");
		var type = jQuery(curEditing).attr("type"); 
			jQuery.ajax({
				url:"heaPortletPropertyAction.hea",
				data:{action:"findAllPortlet",state:"dialog"},
				cache:false,
				async:true,
				success:function(data){
					portletDialog.html("");
					portletDialog.append(data);
					
					pageClick_handle(portletDialog);
					addClick_handle(portletDialog);
					
					portletDialog.dialog("open");
				}
			});
	}
	/**
	 * 注册portlet修改界面
	 */
	function regModifyPortlet(curEditing){
		jQuery(curEditing).addClass("curEditing");
		jQuery("#editPortlet > table").show();
		jQuery("#editCmsPortlet").hide();
		if(jQuery(curEditing).attr("portletType") == "02"){
			jQuery(".webPortlet").hide();
			jQuery(".wsrp").show();
			jQuery("#htmlCode").text(curEditing.htmlCode);
			jQuery("#htmlCode").blur(function(){
				if(jQuery("#htmlCode").text() != ""){
					jQuery(curEditing).attr("htmlCode",jQuery("#htmlCode").text());
				}
			});
			regdialog();
			jQuery("#editPortlet").dialog("open");
		}
		if(jQuery(curEditing).attr("portletType") == "03"){
			//regdialog();
			jQuery("#editPortlet > table").hide();
			jQuery("#editCmsPortlet").show();
			var url = jQuery(curEditing).attr("actionUrl");
			regdialog();
			jQuery("#editCmsPortlet > iframe").attr("src",url.replace("view","edit"));
			jQuery("#editPortlet").dialog("open");
			jQuery("#editPortlet").height(1200);
			jQuery("#editPortlet").width(850);
			jQuery("#editPortlet").parent().width(850);
			jQuery("#editPortlet").dialog("option","position","center");
			//jQuery("#editPortlet").parent().height(800);
		}
		if(jQuery(curEditing).attr("portletType").substring(0,2) == "01"){
			jQuery(".webPortlet").show();
			jQuery("#editCmsPortlet").hide();
			jQuery(".wsrp").hide();
			jQuery("#funcAction").text(jQuery(curEditing).attr("actionUrl"));
			jQuery("#funcAction").blur(function(){
				if(jQuery("#funcAction").text() != ""){
					jQuery(curEditing).attr("actionUrl",jQuery("#funcAction").text());
				}
			});
			regdialog();
			jQuery("#editPortlet").dialog("open"); 
		}
	}
	//保存页面
	jQuery("#btn_savePage").click(function(){
		var json = {prefixes : []};
		//构建页面结构json，新加的tpid为-100，即save，修改tpid有值，即update
		jQuery("div[portletid]").each(function(i,o){
			var prefix ={};
			prefix.tpId = o.getAttribute("tpId") == null?"-100":o.getAttribute("tpId") ;
			prefix.portletId = o.getAttribute("portletId");
			prefix.prefix = o.getAttribute("prefix");
			prefix.dataurl = o.getAttribute("actionUrl");
			prefix.type = o.getAttribute("type");
			prefix.portletType = o.getAttribute("portletType");
			prefix.htmlCode = o.getAttribute("htmlCode");
			json.prefixes[i] = prefix;
		});
		jQuery.ajax({
			url:"heaTemplatePortletInfoAction.hea",
			type:'post',
			data:{
				action:'savePageInfo',
				contents:JSON.stringify(json),
				pageId:jQuery("body").attr("id"),
				tmplId:jQuery("#tmplId").val(),
				siteId:jQuery("#siteId").val(),
				pageName:jQuery("#pageName").val(),
				themeCode:jQuery("#themeCode").val(),
				pageType:jQuery("#pageType").val()},
				success:function(data){
					//页面返回数据:页面id-占位符:tpid，截取后处理对应模块
					var pageId = data.split("-")[0];
					var savedPrefix = data.split("-")[1];
					jQuery(savedPrefix.split(";")).each(function(i,o){
						$("div[prefix='"+o.split(":")[0]+"']").attr("tpId",o.split(":")[1]);
					});
					jQuery("body").attr("id",pageId);
					showMsg({
						height:200,
						width:250,
						title:'提示',
						msg:'保存成功，现在发布该页面吗？',
						buttons:{
							'取消':function(){
								jQuery(this).dialog("close");
							},
							'确定':function(){
								if(pageId != null){//新建
									publishPage(pageId);
								}else{//修改
									publishPage(jQuery("body").attr("id"));
								}
								jQuery(this).dialog("close");
							}						
						}
					});
			}
		});
	});
	
	
	//发布页面
//	jQuery("button:eq(1)").click(function(){
//		
//		if(jQuery("body").first().attr("id")==null || jQuery("body").first().attr("id") == ""){
//			//showMsg("请先保存该页面","系统提示");
//			showMsg({
//				title:'请先保存该页面',
//				msg:'请先保存该页面',
//				buttons:{
//					'确定':function(){
//						jQuery(this).dialog("close");
//					}
//				}
//					
//			});
//		}else{
//			var id=jQuery("body").attr("id");
//			publishPage(id);
//		}
//		
//	});
	
	//分页后，重新注册添加事件
	function addClick_handle(portletDialog){

		//取消查询
		portletDialog.find("div#top_query").next().andSelf().hide();
		portletDialog.find("a#addPorltet").hide();
		
		portletDialog.find("a.add").each(function(i,o){
			jQuery(this).click(function(){
				var id = jQuery(this).prev(":hidden").val();
				jQuery(".curEditing").attr("portletId",id);
				//给该占位符加上portlet类型
				jQuery(".curEditing").attr("portletType",jQuery(this).parent().prev().prev().prev().attr("portletType"));
				var portletType = jQuery(".curEditing").attr("portletType");
				if(portletType != "02"){
					var tempUrl = jQuery(this).parent().prev().text().Trim();
					jQuery(".curEditing").attr("actionUrl",tempUrl);
					if(portletType == '03'){
						if(tempUrl.indexOf("newCmsPortlet") >=0){//cmsportlet类型
							jQuery(".curEditing").attr("actionUrl",tempUrl.replace('newCmsPortlet',new UUID().id) + "&isshowstyle=true");
						}else{//cmsportlet实例
							jQuery(".curEditing").attr("actionUrl",tempUrl + "&isshowstyle=true");
						}
					}
				}else{
					jQuery(".curEditing").attr("htmlCode",jQuery(this).parent().prev().children("textarea").val());
				}
				//将为“已添加”的修改为“添加”
				portletDialog.find("a.add").each(function(){
					jQuery(this).text("添加");
				});
				jQuery(this).text("已添加");
				jQuery(this).addClass("curEditing");
			});
		});
	}
	function publishPage(pageId){
		jQuery.ajax({
			url:'heaTemplatePageAction.hea',
			type:'get',
			data:{action:'publish',pageId:pageId},
			success:function(status){
				if(status=="1"){
					//showMsg("发布成功","系统提示");

					showMsg({
						title:'提示',
						msg:'发布成功',
						buttons:{
							'确定':function(){
							var aa = $(document.createElement('a'));
								$(aa).attr("href","/hea/modules/flex/ViewTreeTable.html");
								$(aa).attr("target","mainContent");
								$(aa).click(function(){
									window.location = "/hea/modules/flex/ViewTreeTable.html";
									//window.open($(aa).attr("href"),$(aa).attr("target"));
								});
								$(aa).trigger("click");
								jQuery(this).dialog("close");
							}
						}
							
					});
				}else{
					//showMsg("出错了，请联系系统管理员","系统提示");

					showMsg({
						title:'系统提示',
						msg:'出错了，请联系系统管理员',
						buttons:{
							'确定':function(){
								jQuery(this).dialog("close");
							}
						}
							
					});
				}
			},
			error:function(){
				//showMsg("出错了，请联系系统管理员","系统提示");
				showMsg({
					title:'系统提示',
					msg:'出错了，请联系系统管理员',
					buttons:{
						'确定':function(){
							jQuery(this).dialog("close");
						}
					}
						
				});
			}
			
		});
	}
	function close_handle(){
		jQuery(".curEditing").removeClass("curEditing");
	}
	
	
	//将newPorltet中的分页功能该为ajax方式，否则dialog会跳转
	function pageClick_handle(portletDialog){
		if(portletDialog.find("#next").length>0){
			portletDialog.find("#next").click(function(){
				var href = portletDialog.find("#next").attr("title")+"&state=dialog";
				jQuery.ajax({
					url:href,
					cache:false,
					//async:true,
					success:function(data1){
						portletDialog.html(data1);
						addClick_handle(portletDialog);
						pageClick_handle(portletDialog);
						
					}
				});
				return false;
				//portletDialog.find("#next").unbind("click");
			});
		}
		if( portletDialog.find("#prev").length>0){
			portletDialog.find("#prev").click(function(){
				var href = portletDialog.find("#prev").attr("title")+"&state=dialog";
				
				jQuery.ajax({ 
					url:href,
					//async:true,
					cache:false,
					success:function(data1){
						portletDialog.html(data1);
						addClick_handle(portletDialog);
						pageClick_handle(portletDialog);
					}
				});
				return false;
				//portletDialog.find("#next").unbind("click");
			});
		}
	}
	
	jQuery("#selectother").click(function(){
		var curEdit = jQuery(".curEditing") ;
		jQuery("#editPortlet").dialog("close");
		jQuery("#editPortlet").dialog("destory");
		regConfigPortlet(curEdit);
	});
	jQuery(".menu").load(jQuery(".menu").attr("menuUrl"));
	//清空portlet的内容
	$("#deletePortletInfo").click(function(){
		var curr = jQuery("div.curEditing");
		//alert(curr.attr("tpId"));
		if(curr.attr("tpId") == undefined || curr.attr("tpId") == ""){
			curr.children("div:eq(1)").detach();
			curr.removeAttr("portletType");
			curr.removeAttr("actionUrl");
			jQuery("#funcAction").val("");
		}else{
		$.post("heaTemplatePortletInfoAction.hea",
				{action:"deletePortletInfo",
				tpId:curr.attr("tpId")
				},
				function(data){
					if(data == "1"){
						jQuery("#funcAction").val("");
						curr.children("div:eq(1)").detach();
						curr.removeAttr("tpId");
						curr.removeAttr("portletType");
						curr.removeAttr("actionUrl");
						curr.removeAttr("portletId");
					}
				});
	}
	});
});

/**
 * 刷新portlet数据
 */
function refreshPortlet(){
	var curEdit = jQuery("div.curEditing");
	var editTag = "<div class='configPortlet_' align='right'><img src='../../images/personal/edit_button.gif' style='position:relative;z-index:1;margin:0px 0px -16px;'></div>";
	curEdit.children("div:eq(1)").detach();
	//判断类型主要是区分wsrp和webportlet
	var portletType = curEdit.attr("portletType");
	if(portletType != undefined){
	if(portletType == "02"){
//		curEdit.html(editTag + unescape(curEdit.attr("htmlCode")));
		curEdit.append("<div>" + unescape(curEdit.attr("htmlCode")) + "</div>");
		//curEdit.removeClass("curEditing");
	}else{
		if(portletType  == '0102'){
			curEdit.append("<div><iframe frameborder='0px' width='100%' height='100%' scrolling='no' src='" + curEdit.attr("actionUrl")+ "'></iframe></div>");
		}else{
			$.ajax({
				url:curEdit.attr("actionUrl"),
				cache:false,
				async:true,
				success:function(data){
					curEdit.append("<div>"+data +"</div>");
					//curEdit.removeClass("curEditing");
				}
			});
		}
		//curEdit.append(editTag);
	}
	}
}