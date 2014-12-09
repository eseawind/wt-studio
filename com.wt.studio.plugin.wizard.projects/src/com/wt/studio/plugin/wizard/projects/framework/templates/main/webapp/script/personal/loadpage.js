
$(function (){
	jQuery.ajax({
		url:jQuery(".menu").attr("menuUrl"),
		cache:false,
		success:function(data){
		jQuery(".menu").html(data);
		//给菜单加上事件
		jQuery(".menu").find("a").each(function(a,o){
			jQuery(o).attr("loadurl1",jQuery(o).attr("href"));
			jQuery(o).attr("href","javascript:void(0)");
			jQuery(o).attr("targets",jQuery(o).attr("target"));
			jQuery(o).removeAttr("target");
			jQuery(o).click(function(){
				var url = jQuery(o).attr("loadurl1");
				if(url != "" ){
					if(jQuery(o).attr("targets") == "mainFrame" ){//在当前页的框架中打开
						if(url.indexOf("#") <0){//不是首页
							jQuery(".replaceAll").hide();
							jQuery(".replacecontent").show();
							jQuery.ajax({
								url: url,
								cache:false,
								success:function(data){
								jQuery(".replacecontent").html(data);
							}
							});
						}else{
							
							jQuery(".replaceAll").show();
							jQuery(".replaceAll").find("div[dataUrl]").each(function(i,o){
								jQuery(o).html("<div class='left1_list' portletContent='content'></div>");
								jQuery(o).children("div[portletContent=content]").load(jQuery(o).attr("dataUrl"));
							});
							jQuery(".replacecontent").hide();
						}
					}else{
						window.open(url);
					}
				}	
			});
		});
		}
	});
	
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
//	$("floatwindow").load($("floatwindow").html());;
	var curtainUrl = $(".curtain").attr("curtainUrl");
	if(curtainUrl != "" && curtainUrl != undefined){
		$(".curtain").load(curtainUrl);
	}
	var floatwinUrl = $(".floatwindow").attr("fwUrl");
	if(floatwinUrl != "" && floatwinUrl != undefined){
		$(".floatwindow").load(floatwinUrl);
	}
	
	jQuery("div[dataUrl]").each(function(i,o){
		jQuery(o).html("<div class='left1_list' portletContent='content'></div>");
		jQuery(o).children("div[portletContent=content]").load(jQuery(o).attr("dataUrl"));
	});
});