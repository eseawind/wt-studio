	$(function(){
		//初始化选择主题和布局的第一个
		$("div[theme=theme]").each(function(i,t){
			if($("#themeCode").val() == $(t).attr("id")){
				var style1 = $(t).attr("style");
				$(t).attr("style","border:3px solid #AAAAAA;"+style1);
			}
		});
		$("div[layout=layout] > img").each(function(i,l){
			if($("#layoutCode").val() == $(l).attr("id")){
				$(l).attr("style","border:3px solid #AAAAAA");
			}
		});
		
		$("#slider").easySlider({
			auto: false, 
			continuous: true,
			isSync:true,
			regElementId:'#tl',
			regEvent:'onchange'
		});
//		$("#slider").animate("2",true);
//		$("#tl").trigger("change");
		//添加默认的转向地址
			if($("#pageType").val() == "02"){
				$("div[layout=layout]").show();
				$(".autotmplChart").show();
				$("#autoLayout").show();
				$(".fixetmplChart").hide();
				$("#fixeLayout").hide();
			}else{
				$("div[layout=layout]").hide();
				$("#autoLayout").hide();
				$(".autotmplChart").hide();
				$(".fixetmplChart").show();
				$("#fixeLayout").show();	
			}
			/**
			 * 注册提交地址
			 * @return
			 */
			$("a#submit").click(function(){
					var param = "tmplId="
						+ $("#tl").val() + "&siteId=" + $("#siteId").val() + "&pageName=" + $("#pageName").val()
						+ "&themeCode=" + $("#themeCode").val() + "&pageType="+$("#pageType").val() 
						+ "&layout=" + $("#layoutCode").val();
					
					if($("#pageName").val() != null && $("#pageName").val().length >0){
						if($("#pt").val() != "02"){//固定模板布局提交方式
							if($("#pageId").val() == "newPage"){//新建页面
								location.href=encodeURI(encodeURI("/hea/heaTemplatePageAction.hea?action=toSecondStep&" + param));
							}else{//修改页面
								location.href=encodeURI(encodeURI("/hea/heaTemplatePageAction.hea?action=modifyPage&pageId=" + $("#pageId").val() + "&" + param));
							}
						}else{
							alert("自动模板开发中");
						}
					}else{
						alert("请填写页面名称");
					}
					return false;	
				});
		});

	/*
	* 点选模板时出发列表框联动
	*/
	function changeTmpl(tmplId){
		
			$("#tl > option").each(function(i,o){
				if(tmplId == o.value){
				$(o).attr("selected","selected");
				}
			});
	}

	/*
	* 点选主题
	*/
	function changeTheme(theme){
		$("#themeCode").val(theme.id);
		$("div[theme=theme]").each(function(i,t){
			if(theme.id == t.id){
				t.style.border="3px solid #AAAAAA";
			}else{
				t.style.border="";				
			}
		});
	}
	/*
	*点选布局
	*/
	function changeLayout(layout){
		$("#layoutCode").val(layout.id);
		$("div[layout=layout] > img").each(function(i,t){
			if(layout.id == t.parentNode.id){
				t.style.border="3px solid #AAAAAA";
			}else{
				t.style.border="";				
			}
		});

	}
	
//	function registeUrl(){
//		
//	}