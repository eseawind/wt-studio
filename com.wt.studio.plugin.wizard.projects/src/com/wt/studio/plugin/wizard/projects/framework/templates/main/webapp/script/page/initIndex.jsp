<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/modules/common/taglib.jsp" %>
/**
 * @author pu
 * @copyright 1.0.1.20101223
 * @classDescription Using system parameter and custom's config to control page
 */

$.extend($.fn, {
	/**
	 * Using system parameter and constract parameter to initialize page layout
	 * full		12/24
	 * center	12/27r
	 * call the onComleted  1/11
	 * perfection function named 'createPage',accept custom's contentCss	1/18
	 * @param {Object} setting
	 */
    initPage: function(setting) {
        var opts = $.fn.extend({
			//page layout：center,full
            layout: 'full',
			//tabtitle's width 
			tabWidth:120,
			//page style，this position of default is full
            contentCss: {
				topHeight: 75,
	            leftWidth: $(".siderbar").width()  + $(".drawback").width(),
				margin:'0'
            },
			//leftmenu:menu,tree; default is menu;
			leftMenu:'menu',
			//tab title can be sort;
			tabSortable:true,
			//save the page when page been changed as change layout or added new tabs and so on
			save:true,
			//zhe callback function when after page render successfully
			onComleted: function(){}
        }, setting);
		
		function loadConfig(){
			var defaultConfig=[{
			    "codeId": "402880e82aac6608012aac6618260001",
			    "codeName": "isSwitchLayout",
			    "codeType": "overall", 
			    "codeValue": "0",
			    "content1": "个性化首页是否允许切换布局"
			}, {
			    "codeId": "402880102aa19b1e012aa1ec1a0e0012",
			    "codeName": "isPersonal",
			    "codeType": "overall",
			    "codeValue": "0",
			    "content1": "是否允许普通用户个性化"
			}, {
			    "codeId": "402881c02a74a0b8012a74a107100005",
			    "codeName": "isSwitchTheme",
			    "codeType": "overall",
			    "codeValue": "0",
			    "content1": "个性化首页是否允许更换主题"
			}, {
			    "codeId": "402880102aa20979012aa21182930003",
			    "codeName": "isTabPanel",
			    "codeType": "overall",
			    "codeValue": "1",
			    "content1": "首页是否有TabPanel 标签"
			}, {
			    "codeId": "402881c02a74a0b8012a74a107150006",
			    "codeName": "isLeftMenu",
			    "codeType": "overall",
			    "codeValue": "1",
			    "content1": "首页是否有菜单"
			}];
			
			//get config value from config name
			$.fn.getConfig = function (name){
				var value='0';
				$(defaultConfig).each(function(i,o){
					if(name==o.codeName)
						value= o.codeValue;
				});
				return value;
			}
			
		    $.ajax({
		        url: 'heaIndexPageAction.hea',
		        data: 'action=findIndexConfig&type=json',
		        cache: false,
		        async: false,
		        success: function(data){
					if(data != null || data != "" ){
						defaultConfig = $.fn.extend(defaultConfig,eval(data));
					}
					createPage();
					$.fn.createTab(opts);
					$.fn.editTitle(opts);
					if ($.fn.getConfig('isLeftMenu') == "1") {//有菜单的情况下，页面是由菜单添加的
						$.fn.createLeftMenu(opts);
					}else{//没有菜单的情况下，页面是由用户自己添加
						$.fn.personalPage(opts);
					}
					if ($.fn.getConfig('isSwitchTheme') == "1") {
						$.fn.createChangeTheme(opts);
					}
					if ($.fn.getConfig('isSwitchLayout') == "1") {
						$.fn.createChangeLayout(opts);
					}
					!!opts.onComleted ? opts.onComleted() : true;
		        }
		    });
			
			/**
			 * Setting the page layout with two parameter.
			 * 	1、The layout of full or center
			 * 	2、browser(IE、FF)
			 */
			function createPage(){
				//The ffset of content's width on different browsers
				var rightWidthOffset = 0;
				/**
				 * screenAvailWidth value is current screen availWidth
				 */
				var screenAvailWidth = $($(window).attr("screen")).attr("availWidth");
				
				if($.browser.msie){
					rightWidthOffset = 19;						
				}else{
					rightWidthOffset = 0;
					screenAvailWidth += 21;
				}
				if($.fn.getConfig('isLeftMenu') == "1"){//In menu case
					if (opts.layout == "full") {//In full layout case
						$('.content_1').css({
							width: $("body").width() - opts.contentCss.leftWidth - 40 + rightWidthOffset
							//left: opts.contentCss.leftWidth + ($.browser.msie?0:10)
						});
					}else{//In center layout case
						var lc = screenAvailWidth - $(".maincontent").width() ;
						
						$('.content_1').css({
//							width:$("body").width() - opts.contentCss.leftWidth - ($.browser.msie?239:242) + rightWidthOffset
							width:screenAvailWidth - lc - opts.contentCss.leftWidth - 5 - ($.browser.msie?0:17)
							//left: opts.contentCss.leftWidth + ($.browser.msie?0:10)
						});
						//$(".container").width($("body").width() - ($.browser.msie?239:242) + rightWidthOffset+21);//.css({position:'absolute',top:'0',left:100});
					}
					
//					$('.siderbar').css({
//							width: opts.contentCss.leftWidth,
//							height: $(document).height() - opts.contentCss.topHeight - ($.browser.msie?10:18),
//							position: 'absolute', 
//							top: opts.contentCss.topHeight + 5,
//							margin:opts.contentCss.margin,
//							display:'block'
//					});
//					
//					$('.content').css({
//							height: $(document).height() - opts.contentCss.topHeight - ($.browser.msie?10:18) ,
//							position:'absolute',
//							top: opts.contentCss.topHeight + 5,
//							margin:opts.contentCss.margin,
//							display:'block'
//					});
				}else{//In not menu case
					if (opts.layout == "full") {//In full layout case
						$('.content_1').css({
							width: $(".container").width(),
							left: 1
						});
					}else{//In center layout case
						$('.content_1').css({
							//width: $("body").width() - ($.browser.msie?220:232),
							width: $(".container").width(),
							left: 1
						});
						
						//$(".container").width($("body").width() - ($.browser.msie?239:242) + rightWidthOffset+21);//.css({position:'absolute',top:'0',left:100});
					}
					$(".siderbar").css({display:'none'});
					$(".drawback").css({display:'none'});
					$('.content_1').css({
							//height: $(document).height() - opts.contentCss.topHeight - ($.browser.msie?10:18) - ($.browser.msie?0:7),
							//position:'absolute',
							//top: opts.contentCss.topHeight + 5,
							margin:opts.contentCss.margin,
							display:'block'
					});					
				}
				
				
				//Save left and width of 'content',for resume content's position when leftMenu flex or expand
				$('.content_1').data("rightShowPosition",$('.content_1').css("left"));
				$('.content_1').data("rightShowWidth",$('.content_1').css("width"));
			}
		}
		loadConfig();

	},
	/**
	 * Create a button to change the theme
	 * 1.0	12/24		complettion function
	 * 1.1	12/26		给主题层添加边框(IE6下不识别red，必须用#0000FF....)
	 * @param {Object} opts
	 */
	createChangeTheme:function(opts){
		$("<div></div>",{
			'class':'topnav',
			'html':'<a href="#"><bean:message key="ui.changeTheme"/></a>',
			'click':function(){
				var path = $("#themePath").data("path")==null?"blue":$("#themePath").data("path");
				
				var dialog = $("<div/>",{
								html:'<form>'
									+'<table>'
									+'<tr>'
									+'	<td align="center">'
									+'		<div id="theme1" class="ui-widget-content ui-corner-all div"'
									+'				title="blue"'
									+'				style="width: 100px; height: 50px; background: #28609B;">'
									+'		</div>'
									+'</td>'
									+'<td align="center">'
									+'		<div id="theme2" class="ui-widget-content ui-corner-all div"'
									+'			title="red"'
									+'			style="width: 100px; height: 50px; background: #9F290B;"></div>'
									+'</td>'
//									+'<td align="center">'
//									+'		<div id="theme2" class="ui-widget-content ui-corner-all div"'
//									+'			title="red"'
//									+'			style="width: 100px; height: 50px; background: #BE2527;"></div>'
//									+'</td>'
									+'</tr>'
									+'</table>'
									+'</form>'
							});
							dialog.find("div")
							.click(function(){
								path = $(this).attr("title");
								dialog.find("div").css("border","");
								$(this).css("border","3px solid #ABABAB");
								$("#themePath").attr("href","css/theme/"+path+"/css.css");
								$("#tabthemepath").attr("href","css/component/jerichotab/" + path + "/jquery.jerichotab.css");
							});
				dialog.find("div[title='"+path+"']").css("border","3px solid #ABABAB");
				$.fn.createDialog({
					title:'<bean:message key="ui.changeTheme"/>',
					content:dialog,
					height:300,
					width:400,
					buttons:{
						'<bean:message key="ui.cancel"/>':function(){
							var _path =$("#themePath").data("path")==null?"blue":$("#themePath").data("path");
							$("#themePath").attr("href","css/theme/"+_path+"/css.css");
							$("#tabthemepath").attr("href","css/component/jerichotab/" + _path + "/jquery.jerichotab.css");
							$(this).dialog("close");
						},
						'<bean:message key="ui.ok"/>':function(){
							$("#themePath").data("path",path);
							$.ajax({
								url:'/hea/heaUserPageAction.hea',
								data:'action=saveTheme&theme=' + path,
								success:function(){
								},
								error:function(){
								}
							});
							$(this).dialog("close");
						}
					}
				});
			}
		}).appendTo(".banner");
	},
	/**
	 * Create a button to change the layout
	 * 1.0	12/24		只完成点击事件，具体功能有待讨论
	 * @param {Object} opts
	 */
	createChangeLayout:function(opts){
		$("<div></div>",{
			'class':'topnav',
			'html':'<a href="javascript:void(0);" ><bean:message key="ui.changeLayout"/></a>',
			'click':function(){
				$.fn.createDialog({
					title:'<bean:message key="ui.message"/>',
					content:'切换布局功能稍后添加',
					height:200,
					width:300,
					buttons:{
						'\u786e\u5b9a':function(){
							$(this).dialog("close");
						}
					}
				});
			}
		}).appendTo(".banner");
	},
	/**
	 * Create a button to add the user's page 
	 *12/27				未发布，整体架构有待讨论		
	 * @param {Object} opts
	 */
	personalPage:function(){
		
		$("<div></div>",{
			'class':'topnav',
			'html':'<a href="javascript:void(0);" alt="添加页面" >添加页面</a>',
			'click':function(){
				$.ajax({
					url:'heaIndexPageAction.hea?action=findAllPersonalPage',
					cache:false,
					success:function(data){
						$.fn.jerichoTab.addTab({
	                        title:'添加页面',
							closeable:true,
                       		iconImg: 'images/component/jerichotab/jerichotab.png',
							data: { dataType: 'iframe', dataLink:'heaIndexPageAction.hea?action=findAllPersonalPage' },
	                        type:'add'
	                    }).showLoader().loadData();
					}
				})
			}
		}).appendTo(".banner");
	},
	/**
	 * Create menu on the left of page and register slide animate for the menu  
	 * full		12/24 
	 * center	12/27      
	 * FF		12/24
	 * IE		12/26   12/27
	 * perfection function		1/18
	 * 		1、synchronization custom's contentCss	
	 * 		2、reset the width of right's iframe when leftmenu spread or shrink
	 * @param {Object} opts
	 */
	createLeftMenu:function(opts){
		
		var leftShowPosition = '0px',
			rightShowPosition = ($.browser.msie?'30px':'25px'),
			rightShowWidth = 0;
	
		if ($.browser.msie) {
			if (opts.layout == 'center') {
				rightShowWidth = ($.browser.msie?774:779);
			}else{
				rightShowWidth = ($.browser.msie?973:978);
			}
		}else{
			if (opts.layout == 'center') {
				rightShowWidth = ($.browser.msie?763:768);
			}else{
				rightShowWidth = ($.browser.msie?966:971);
			}
		}
		
		
//		$(".siderbar").find(".collapseRight").toggle(
		$(".drawback_icon").toggle(
			function(){
				var tag = $(this);
				//	$(".siderbar").hide();
				var screenAvailWidth = $($(window).attr("screen")).attr("availWidth");
				var lc = screenAvailWidth - $(".maincontent").width();
				$(".login").hide();
				$(".siderbar").animate({width: leftShowPosition}, 350);
				$(".content_1").animate({
//					width:rightShowWidth,
					width:screenAvailWidth - lc - 5,
					left:rightShowPosition
				},200,function(){
					$(".siderbar").hide();
					
					 $('#divMainLoader').css('display', 'block');
					//var tagIFrame = $("#jerichotabholder_"+$("li.tab_selected").attr("id")
					//		.substring($("li.tab_selected").attr("id").indexOf("_")+1))
					//		.find("iframe");
					//tagIFrame.width(rightShowWidth - 20 + 'px');
					$("iframe[id^=jerichotabiframe_]").width(rightShowWidth - 20 + 'px');
//					tagIFrame.get(0).contentWindow.location = tagIFrame.attr("src");
					 $('#divMainLoader').css('display', 'none');
				});
				$(this).toggleClass("drawgo_icon");
			},
			function(){
				var tag = $(this);
				$(".login").show();
				$(".siderbar").animate({width: opts.contentCss.leftWidth}, 200);
				$(this).toggleClass("drawgo_icon");
				$(".siderbar").show();
				$(".content_1").animate({
					width:$(".content_1").data("rightShowWidth"),
					left:0
				},350,function(){
					//$(".login").show();
					
					 $('#divMainLoader').css('display', 'block');
					//var tagIFrame = $("#jerichotabholder_"+$("li.tab_selected").attr("id")
					//		.substring($("li.tab_selected").attr("id").indexOf("_")+1))
					//		.find("iframe");
					//tagIFrame.width(tagIFrame.attr("width"));
					
					
					$("iframe[id^=jerichotabiframe_]")
						.width(
							$(".content_1").data("rightShowWidth")
								.substring(0,$(".content_1").data("rightShowWidth").indexOf("px"))
								-20 + 'px'
							);
					 $('#divMainLoader').css('display', 'none');
				});
			}
		);
		
		if (opts.leftMenu == "menu") {

			$.ajax({
				url:'heaIndexPageAction.hea?action=initTree&menuType=menu',
				cache:false,
				dataType:'xml',
				success:function(data){
					var html = '';
					$(data).find("menu").each(function(i,o){
						if($(o).attr("level")==2){
							html += '<div><span>'+$(o).attr("name")+'</span>';
							$(o).children().each(function(i,k){
								html+='<a class="function" href="#" link="'+$(k).text()+'">'+$(k).attr("name")+'</a>';
							});
							html+='</div>';
						}
					});
					
					var div = $("<div/>",{
						'id':'my_menu',
						'class':'sdmenu',
						'css':{'float':'left'},
						'html':html
					})
					.appendTo(".menuContent")
					.find(".function").click(function(){
						$.fn.jerichoTab.addTab({
		                    tabFirer:$(this),
		                    title:$(this).text(),
							closeable:true,
							data: { dataType: 'iframe', dataLink:$(this).attr("link") },
		                    type:'add'
		                }).showLoader().loadData();
					});
					var myMenu = new SDMenu("my_menu");
					myMenu.remember = false;
					myMenu.init();
					
					
					
					
				}				
			});		
		}else if(opts.leftMenu == "tree"){
			var tree=new dhtmlXTreeObject("siderbar_menu","100%","92%",0);
			tree.setImagePath("images/component/dhtmlxtree/csh_bluebooks/");
			tree.setXMLAutoLoading('heaIndexPageAction.hea?action=loadNextNodes');
			tree.loadXML('heaIndexPageAction.hea?action=initTree&menuType=tree');
			
			tree.setOnClickHandler(function(id){
		 		var url=this.getUserData(id,"url");
			 	if(url==null || url=="")return ;
				var name= this.getItemText(id);
				
				var t=$(".selectedTreeRow");
        			
                $.fn.jerichoTab.addTab({
                	uniqueId:id,
                    tabFirer:t,
                    title: name,
                    closeable: true,
                    loadOnce: true,
                    contentCss: { 'height': $('#rightContent').height() },
                    data: {
                        dataType: 'iframe', 
                        dataLink: url
                    },
					type:'add'
                }).showLoader().loadData();         
				$.fn.editTitle(opts);
			});
		}
	},
	/**
	 * Initialize tab page on the right of page
	 * 1.0		12/24	initialize one tabPage
	 * 1.1		12/27	initialize many tabPages
	 * @param {Object} opts
	 */
	createTab:function(opts){
		
					//[{"dispSn":1,"ifDefault":1,"isAdd":"0","layoutCode":"1","
					//pageAddr":"1","pageId":"1","pageName":"1","pageNo":"1",
					//"pageStatus":"1","pageTitle":"1","pubAddr":"1","pubTime":null,
					//"siteId":"1","siteManage":null,"siteName":"1","siteNo":"1",
					//"templateLayout":null,"templatePortletInfoList":[],
					//"themeCode":"1","tmplId":"1","type":"1","userNo":"1"}]
		var tabs =jQuery([]).add({
                        title: '首页',
                        closeable: false,
                        iconImg: 'images/component/jerichotab/jerichotab.png',
                        data: { dataType: 'formtag', dataLink: '#tbNews' },
						type:'init'
                    });
		$.ajax({
				url:'heaIndexPageAction.hea?action=findAllPersonalPage&type=json',
				type:'get',
				cache:false,
				async:false,
				success:function(data){
					data = eval(data);
					$(data).each(function(i,p){
						tabs = tabs.add({
							title:p.pageName,
							closeable:true,
                       		iconImg: 'images/component/jerichotab/jerichotab.png',
							data: { dataType: 'iframe', dataLink:'http://localhost:8080/hea/login.jsp' },
							type:'init'
						});
					});
					
					var jericho = {
			            showLoader: function() {
			                $('#divMainLoader').css('display', 'block');
			            },
			            removeLoader: function() {
			                $('#divMainLoader').css('display', 'none');
			            },
						buildTabpanel : function(){
							$.fn.initJerichoTab({
			                    renderTo: '.content_1',
			                    uniqueId: 'myJerichoTab',
			                    contentCss: { 'height': $('.content_1').height() - 33 },
			                    tabWidth: 120,
			                    tabs: tabs,
			                    loadOnce: true
			               });
						}
					 };
		
					
					$('.content_1').empty();
					jericho.showLoader();
					jericho.buildTabpanel();
					jericho.removeLoader();
					if(opts.tabSorter)
						$(".content_1").find(".tabs").children("ul").sortable({axis:'x'});	
				}
		});
	},
	/**
	 * Prompt user to save the page when page(theme...) modified,
	 *  Tt'll been saving when user click 'yes'
	 * 1.0 		1/1     release
	 * @param {Object} opts
	 */
	savePage:function(opts){
		if(opts.save==false)return;
		var overlay = $("<div/>",{
			'class':'ui-widget-overlay',
			css:{
					width:$("body").width(),
					height:1000,
					'z-index':1001
				}
		}).appendTo($("body"));
		
		
		
		var showMsg = $("<div/>",{
			id:'showMsg',
			css:{
					display:'none',
					'background-color':'#ABABAB',
					height:80,
					width:$("body").width(),
					position:'absolute',
					top:0,
					left:0,
					'z-index':1002,
					display:'none'
					},
			html:'<div style="font-size:20px;">页面已修改，要保存吗？<button>确定</button></div>'	
		}).prependTo($("body"));
		
		showMsg.show("blind",{},500,function(){
			//确认
			$(this).find("button:eq(0)").click(function(){
				$("#showMsg:visible").removeAttr('style').hide().fadeOut();
				var ttt = {
					theme:$("#mainlinks").attr("href"),
					//themeId:$("body").attr("themeId"),
					pages:[]
				};
				
				
				
				
//				$(".dragTable").each(function(i){
//					var page = {id:$(this).attr("id"),text:$.trim($("#content>ul>li>a[href='#"+$(this).attr('id')+"']>span").text()),columns:[]};
//					$(this).children("div.column").each(function(j){
//						var column = {width:$(this).css("width"),portlets:[]};
//						$(this).children("div.portlet").each(function(h){
//							var portlet = {id:$(this).attr("id"),title:$(this).children("div.portlet-header").text(),
//											url:'',type:'grid'};
//							column.portlets[h] = portlet;
//						});
//						page.columns[j] = column;
//					});
//					ttt.pages[i]=page;
//					ttt.pages[i].themeCode = $("body").attr("themeId");
//			        ttt.pages[i].themeContent = $("#mainlinks").attr("href");
//			        ttt.pages[i].layoutCode = $(this).attr("layout");
//				});
				
				
				overlay.remove();
				showMsg.remove();
			});
			//取消
//			$(this).find("button:eq(1)").click(function(){
//				$("#showMsg:visible").removeAttr('style').hide().fadeOut();
//			});
		}).animate({ backgroundColor: '#aa0000', color: '#fff' },200)
			.animate({ backgroundColor: '#ABABAB', color: '#000' },200)
			.animate({ backgroundColor: '#aa0000', color: '#fff' },200)
			.animate({ backgroundColor: '#ABABAB', color: '#000' },200);
	},
	/**
	 * Edit the title of tab page
	 * 1.0 		1/1     release
	 * @param {Object} opts
	 */
	editTitle:function(opts){
		//edit tabtitle when dblclick tabpage
		$("li.jericho_tabs").find(".tab_left").unbind("dblclick").dblclick(function(){
			var tab_text = $(this).find(".tab_text");
			var tempText = tab_text.attr("title");
			$(this).find(".tab_text").text("");
			
			var text = $("<input/>",{
				size:opts.tabWidth/15,
				val:tempText,
				maxlength:opts.tabWidth/20,
				css:{
					'margin-top':5
				},
				keydown:function(e){
					e = window.event || e;
					var code = e.keyCode;
					if(code == 13){//confirm
						tab_text.attr("title",text.val().Trim()==""?tempText:text.val().Trim())
								.text(text.val().Trim()==""?tempText:text.val().Trim());
						text.remove();
					}else if(code == 27){//esc
						tab_text.attr("title",tempText)
								.text(tempText);
						text.remove();
					}
				},
				blur:function(){
					tab_text.attr("title",text.val().Trim()==""?tempText:text.val().Trim())
								.text(text.val().Trim()==""?tempText:text.val().Trim());
					text.remove();
				}
			}).prependTo($(this).find(".tab_text")).select();
		});
	},
	/**
	 * Inside function,create a dialog box.
	 * 1.0		12/26		released
	 * @param {Object} setting
	 */
	createDialog:function(setting){
		
		var config = $.fn.extend({
			title:'系统提示',
			content:'系统提示',
			resizable:false,
			draggable:false,
			modal: true,
			height:200,
			width:300,
			buttons:{
			},
            close:function(){
				$(this).remove();
		    }
        }, setting);
		
		var dialog = $("<div/>",{
								html:config.content
							});
		$("#dialog").dialog("destroy");
		dialog.dialog(config);
	}
});
