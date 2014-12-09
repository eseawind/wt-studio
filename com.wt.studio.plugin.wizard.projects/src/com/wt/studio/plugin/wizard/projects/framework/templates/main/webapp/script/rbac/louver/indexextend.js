function changeMainNavState()
	{
		var mainNavState = document.getElementById("mainNav_draw").className;
		if(mainNavState == "mainnav_menu_drawback")
			{
				document.getElementById("mainNav_draw").className = 'mainnav_menu_drawgo';
			}else
				{
					document.getElementById("mainNav_draw").className = 'mainnav_menu_drawback';
				}
		$("#header").slideToggle("fast", function autoChangeHeight(){
					var mainNavState = document.getElementById("mainNav_draw").className;
					var headerHeight =0;
					if(mainNavState == "mainnav_menu_drawback")
						{
							headerHeight =document.getElementById('header').offsetHeight;
						}
			     	var mainnavHeight = document.getElementById('mainnav').offsetHeight;
			     	var footerHeight = document.getElementById('footer').offsetHeight;
			     	var loginHeight = document.getElementById('login').offsetHeight;
			     	var clientHeight = document.body.clientHeight;
			     	var maincontentHeight = clientHeight - headerHeight - mainnavHeight - footerHeight;
			     	document.getElementById('maincontent').style.height = maincontentHeight;
			     	document.getElementById('siderbar_menu').style.height = maincontentHeight - loginHeight;
			     });
	}
	function changeFrameStyle(stylePath)
	{
		var myframes = window.frames;
		for (var i = 0; i < myframes.length; i++) {
			var linkObj = myframes[i].document.getElementById("themePath");
			if(linkObj)
				{
					linkObj.href ="${pageContext.request.contextPath}/"+ stylePath;
				}
		}
	}
	
	/**
	 * 
	 * @param {Object} id
	 */
	function loadChildNode(aLink,id,level,frmindex){
		var pad = (Number(level)+2) * 10;
		var linkp = $(aLink).parent();
		var labels = linkp.children("h4");
		if(labels.length == 0){
			$.ajax({
				url:'${pageContext.request.contextPath}/heaIndexAction.hea?action=ajaxLoadIndex&id=' + id,
				async:false,
				success:function(data){
					var j = data;
					$(j).each(function(i,o){
						var ur = o.indexurl;
						var atarget = "";
						if(ur != ''){
						if(ur.indexOf("&") >=0){//给url添加部门id
							ur = ur + "&p_zgs=${dept.uuid}";
						}else{
							ur = ur + "?p_zgs=${dept.uuid}";
						}
						atarget = o.target + "_" + frmindex;
						}else{
							ur = "###";
							atarget = "";
						}
						var ht = "<h4 class='siderbar_menu_1_1'>"
									+ "<h4 class='siderbar_menu_1_1'>"
									+ "<a class='changestyle' style='padding-left:"+ pad +"px;' href='" + ur +"' target='" +atarget + "'"
	 								+ "onclick=loadChildNode(this,'" + o.indexuuid+ "'," + (Number(level)+ 1) + "," + frmindex + ");changestyle(this)>" + o.indexname +"</a>"
									+ "</h4>"
								+"</h4>";
						linkp.append(ht);
					})
				}
			});
			
		}else{//再次点击删除已添加的数据
			$(labels).each(function(i,o){
				$(o).remove();
			})
		}
		
		return false;
	}
	 
	 function changestyle(atag){
		 $(".changestyle").each(function(ii,oo){
					$(oo).attr("id","");
				});
				$(atag).attr("id","siderbaractive");
	 }
	
	$(function(){
		autoWindowHeight();
		$(".changestyle").each(function(i,o){
			$(o).click(function(){
				$(".changestyle").each(function(ii,oo){
					$(oo).attr("id","");
				});
				$(this).attr("id","siderbaractive");
			});
		});
		showAhref(0);
	})
	
	
	function buildMenu() {
	  		<c:forEach items="${topMenus}" var="i" varStatus="status">
						var myMenu_${i.indexuuid} = new SDMenu("my_menu_${i.indexuuid}");
						myMenu_${i.indexuuid}.init();
						myMenu_${i.indexuuid}.speed=5;
			</c:forEach>
		}
		window.onload =buildMenu;
		var firstIndexUuid="${firstNode.indexuuid}";
		function showMenu(indexuuid){
			
			var arr=document.getElementsByTagName("div");
			//全部隐藏
			for(i=0;i<arr.length;i++){
				if(arr[i].id.indexOf("my_menu_") != -1){
					arr[i].className="sdmenu";
					arr[i].style.display="none";
				}
			}
			//显示点击的层
			for(i=0;i<arr.length;i++){
				if(arr[i].id.indexOf("my_menu_") != -1){
					var myDiv=document.getElementById("my_menu_"+indexuuid);
					myDiv.style.display="block";
					myDiv.className="sdmenu";
					break;
				}
			}
			
		}
		
		function changeBackground(myli){
			$(".nav > li > a").each(function(i,o){
				//$(o).removeClass("navactive");
				$(o).get(0).style.backgroundImage = '';
			})	
			//$(myli).addClass("navactive");
			$(myli).children("a").get(0).style.backgroundImage = 'url(${pageContext.request.contextPath }/images/theme/blue/xznew/nav11.gif)';
		}
		
		function showAhref (frameIndex){
			//展示该层级下的第三级的第一个a标签地址
			var atag0 = $(".sdmenu:visible a").get(0);
			if(atag0){
				var ahref = $(".sdmenu:visible a").get(0).href;
				if(ahref){
					$("#mainContent_" + frameIndex).attr("src",ahref);
					//alert(ahref);
				}
			}
		}