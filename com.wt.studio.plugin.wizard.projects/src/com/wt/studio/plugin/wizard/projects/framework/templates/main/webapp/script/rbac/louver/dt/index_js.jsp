<%@ page language="java" %>
<script type="text/javascript">
	var weekCN = new Array();
	weekCN[0] = '星期一';
	weekCN[1] = '星期二';
	weekCN[2] = '星期三';
	weekCN[3] = '星期四';
	weekCN[4] = '星期五';
	weekCN[5] = '星期六';
	weekCN[6] = '星期日';
	function writeCurrentTime(){
		
		var cdate = new Date();
		var MM = cdate.getMonth() + 1;
		var dd = cdate.getDate();
		var ww = cdate.getDay();
		var hh = cdate.getHours();
		var mm = cdate.getMinutes();
		var ss = cdate.getSeconds()
		document.getElementById('time').innerHTML = '时间:' + weekCN[ww-1] + " " + (hh<10?'0'+hh:hh) + ":" + (mm<10?'0'+mm:mm)+":"+(ss<10?'0'+ss:ss);
		//document.getElementById('time').innerHTML = '时间:' + (MM<10?'0'+MM:MM) + '月' + (dd<10?'0'+dd:dd) + '日 ' + weekCN[ww-1] + " " + (hh<10?'0'+hh:hh) + ":" + (mm<10?'0'+mm:mm)+":"+(ss<10?'0'+ss:ss);
		setTimeout('writeCurrentTime();',500); 
	}

	//登出
	function loginOut(){
		window.location.href='${pageContext.request.contextPath}/heaUserAction.hea?action=logoutLouver';
	}

	//更改iframe内嵌页面的背景与主页面一致
    function changeIframeBG(iframeId){
    	if(document.getElementById(iframeId) == null)
    		return;
		var dcm = document.getElementById(iframeId).contentWindow.document;
		if(dcm.body == null){
			setTimeout("changeBGByName(iframeId)",1000);
		}
		else{
			dcm.body.style.background = 'transparent';
		}
    }
    
    //选中一级菜单ID标识
    var selectId = '';
    //一级菜单点击事件处理
    //indexId 指标ID
    //divNum 索引号
    function clickTitleBtn(indexId,divNum){
    	showAhref(divNum);
    	if(indexId == selectId)
    		return;
    	showHideMenu(1);
    	for(var i = 0;i < 30;i++){
    		var divMenu = document.getElementById('divMenu_' + i);
    		if(divMenu == null){
    			break;
    		}else{
	    		divMenu.className = '';
    		}
    		var mainContent = document.getElementById('mainContent_' + i);
    		if(mainContent == null){
    			break;
    		}else{
	    		mainContent.className = 'iframeContent_2';
    		}
    	}
    	document.getElementById('divMenu_' + divNum).className = 'navactive';
    	document.getElementById('mainContent_' + divNum).className = 'iframeContent_1';
    	
    	selectId = indexId;
    	//for(var i = 0;i < 30;i++){
    	//	var divObj = document.getElementById('divSiderbar_' + i);
    	//	if(divObj == null){
    	//		break;
    	//	}else{
    	//		divObj.className = 'divSiderbar_2';
    	//	}
    	//}
    	//document.getElementById('divSiderbar_' + divNum).className = 'divSiderbar_1';
    	//buildTree(indexId,divNum);
    	changeIframeBG('mainContent_' + divNum);
     }
     
     var loadTreeFlag = new Array();
     function buildTree(rootId,divNum){
     	for(var i =0;i < loadTreeFlag.length;i++){
     		if(divNum == loadTreeFlag[i])
     			return;	
     	}
     	loadTreeFlag[divNum] = divNum;
     	var tree = new dhtmlXTreeObject('divSiderbar_' + divNum, "100%", "100%", 0);
		tree.setImagePath("${pageContext.request.contextPath}/images/component/dhtmlxtree/csh_bluebooks/");
		tree.setXMLAutoLoading("${pageContext.request.contextPath}/heaIndexAction.hea?action=loadLouverTreeNode");
		tree.loadXML("${pageContext.request.contextPath}/heaIndexAction.hea?action=initLouver&rootId=" + rootId);//load root level from xml
		tree.setOnClickHandler(function(id){
		 	var url=this.getUserData(id,"url");
		 	if(url==null || url=="")return ;
		 	document.getElementById('mainContent_' + divNum).src = null;
		 	document.getElementById('mainContent_' + divNum).src = url;
		});	
     }
     
     //margin-left:185px !important;
	 //margin-left:182px;
     var isShowMenu = 1;
     var showHideMenuInit = 1;
     var siderbarWidth;
     function showHideMenu(show_Int){
         if(showHideMenuInit == 1){
         	showHideMenuInit = 0;
         	siderbarWidth = $(".siderbar").width() + $(".drawback").width() + 10;
         }
     	if(show_Int != null)
     		isShowMenu = show_Int;
     	else
	     	isShowMenu = isShowMenu == 1?0:1;
     	if(isShowMenu == 1){
     		$(".siderbar").show(600);
     		$(".drawgo_icon").addClass("drawback_icon");
     		$(".drawgo_icon").removeClass("drawgo_icon");
     		for(var i = 1; i <= siderbarWidth; i++){
     			//document.getElementById('content_1').style.marginLeft = i + 'px';
     		}
     	}else if(isShowMenu == 0){
     		$(".siderbar").hide(600);
     		$(".drawback_icon").addClass("drawgo_icon");
     		$(".drawback_icon").removeClass("drawback_icon");
     		for(var i = siderbarWidth; i >= 0; i--){
     			//document.getElementById('content_1').style.marginLeft = i + 'px';
     		}
     	}
     }
     
     function autoWindowHeight(){
     	var headerHeight = document.getElementById('header').offsetHeight;
     	var mainnavHeight = document.getElementById('mainnav').offsetHeight;
     	var footerHeight = document.getElementById('footer').offsetHeight;
     	var sidebarbgHeight = document.getElementById("sidebarbg").offsetHeight;
     	var loginHeight = 0;//document.getElementById('login').offsetHeight;
     	var clientHeight1 = document.body.clientHeight;
     	var maincontentHeight = clientHeight1 - headerHeight - mainnavHeight - footerHeight;// - sidebarbgHeight;
     	var sidebarcontentHeight = maincontentHeight - sidebarbgHeight;
     	document.getElementById('sidebarcontent').style.height = sidebarcontentHeight ;
     	document.getElementById('maincontent').style.height = maincontentHeight;
     	document.getElementById('siderbar_menu').style.height = maincontentHeight - sidebarbgHeight;
     }
     window.onresize = autoWindowHeight;
</script>