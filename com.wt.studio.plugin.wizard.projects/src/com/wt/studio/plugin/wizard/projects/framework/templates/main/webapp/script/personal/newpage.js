	$(function(){
		/*
		 * 初始化模板预览图
		 */
		if($("#slider > ul").html() != ""){
		$("#slider").easySlider({
			auto: false, 
			continuous: true,
			isSync:true,
			regElementId:'#tl',
			regEvent:'onchange'
		});
		}else{
			$("#container").parent().parent().hide();
		}
		//初始化选择主题和布局的第一个
		if($("div[theme=theme]").get(0) != undefined){
			$("div[theme=theme]").get(0).style.border="3px solid #AAAAAA";
			$("#themeCode").val($("div[theme=theme]").first().attr("id"));
		}
		$("div[layout=layout] > img").get(0).style.border="3px solid #AAAAAA";
		
		$("#layoutCode").val($("div[layout=layout1]").first().attr("id"));
		
		//如果不是自动布局则把布局选项隐藏起来
		if($("#pt").val() != "02"){
			$("div[layout=layout1]").hide();
			$("#autoLayout").hide();
			$(".autotemplatechart").hide();
		}else{
			$(".testContainerfix").hide();
			$("#fixeLayout").hide();
			
		}
		/*
		 * 页面修改时做出判断并显示提示信息
		 */
		$("#pageName").change(function(){
			$.post(
					"heaTemplatePageAction.hea",
					{action:"validatePageExist",
						pageName:$("#pageName").val(),
						siteId:$("#siteId").val()
					},
					function(data){
						var temp = data.split("_");
						if(temp[0] == "1"){
							$("#pageId").val(temp[1]);
							$(".warnInfo").text("此页面已经存在，继续操作将会进行修改操作！！");
							if(temp[2] != "02"){//此页面不是自动布局的页面
								cnt = temp[2];
								setSelectOption("pt",cnt);
								$("#pt").attr("disabled","disabled");
							}else{
								cnt = "02";
								setSelectOption("pt",cnt);
								$("#pt").attr("disabled","disabled");
								alert($("#pt").attr("disabled"));
							}
						}else{
							$(".warnInfo").text("");
						}
						
						$("#pt").change();
					});
		});

		$("#pt").change(function(){
			if($("#pt").val() == "02"){//自动
				$("div[layout]").show();
				$(".autotemplatechart").show();
				$("#autoLayout").show();
				$(".testContainerfix").hide();
				$("#fixeLayout").hide();
			}else{
				$("div[layout]").hide();
				$("#autoLayout").hide();
				$(".autotemplatechart").hide();
				$(".testContainerfix").show();
				$("#fixeLayout").show();	
			}
		});
		/**
		 * 注册提交地址
		 * @return
		 */
		$("a#submit").click(function(){
			var flag = true;
			$.ajax({
				url:"heaTemplateLayoutAction.hea?action=templateExists&tmplId=" + $("#tl").val(),
				async:false,
				type:"POST",
				success:function(data){
				if(data == "0"){
					flag = false;
					alert("您选择的模板不存在，这可能是模板添加时路径中有空格！");
					return false;
				}
			}
			});
			if(flag){
			$.ajax({
				url:"heaThemeDefinitionAction.hea?action=themeIsExists&themeCode=" + $("#themeCode").val(),
				async:false,
				type:"POST",
				success:function(data){
				if(data == "0"){
					flag = false;
					alert("您选择的主题不存在，这可能是主题添加时路径中有空格！");
					return false;
				}
			}
			});
			}
			if(flag){
			var param = "tmplId="
				+ $("#tl").val() + "&siteId=" + $("#siteId").val() + "&pageName=" + $("#pageName").val()
				+ "&themeCode=" + $("#themeCode").val() + "&pageType="+$("#pt").val() 
				+ "&layout=" + $("#layoutCode").val();
			if($("#themeCode").val() == undefined || $("#tl").val() == undefined){
				alert("请先添加模板和主题");
			}
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
			}
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