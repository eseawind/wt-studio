<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>展现平台</title>
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/view_ref/page/css/jerichotabcommon.css" />
    <link rel="Stylesheet" href="${pageContext.request.contextPath}/view_ref/page/css/jquery.jerichotab.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/view_ref/common/component/sdmenu/sdmenu.css" type="text/css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.bgiframe-2.1.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.ui.button.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.ui.resizable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.effects.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/ui/jquery-ui-1.8.2.custom/development-bundle/ui/jquery.ui.sortable.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/view_ref/page/js/jquery.jerichotab.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/view_ref/common/component/sdmenu/sdmenu.js"></script>
	
    <script type="text/javascript">
    
        var jericho = {
            showLoader: function() {
                $('#divMainLoader').css('display', 'block');
            },
            removeLoader: function() {
                $('#divMainLoader').css('display', 'none');
            },
            buildTree:function(){
            	$("a.func").click(function(){
            		  if($(this).attr("href")=="")return;
            		  $.fn.jerichoTab.addTab({
		                tabFirer: $(this),
		                title: $(this).text(),
		                closeable: true,
		                data: {
		                    dataType: 'iframe',
		                    dataLink: $(this).attr('href')
		                },
						onLoadCompleted:function(panel){}
		            }).showLoader().loadData();
            	});
            },
            buildTabpanel: function() {
                $.fn.initJerichoTab({
                    renderTo: '.divRight',
                    uniqueId: 'myJerichoTab',
                    contentCss: { 'height': $('.divRight').height() - 33 },
                    tabWidth:120,
                    tabs: [{
                        title: '首页',
                        closeable: false,
                        iconImg: 'images/jerichotab.png',
                        data: { dataType: 'formtag', dataLink: '#tbNews' },
                        onLoadCompleted: function(h) {
                            
                        }
                    }],
                    activeTabIndex: 0,
                    loadOnce: true
                });
            }
        }
        
        
        $().ready(function() {
           renderPage();
           //window.onresize=renderPage;
        });
        
        
        function renderPage(){
        		$('.divRight').empty();
        		jericho.showLoader();
	            var w = $(document).width();
	            var h = $(document).height();
	            
	            
				var topWidth = 80;
				var leftWidth = 150;
				var v_height = 88;
				$('.divLeft').css({ width: 150, height: h - v_height, 'position': 'absolute', 'top': topWidth + 2,
									'left': 0,'display': 'block' ,
									'margin-left': 0,
									'margin-right': 0});
	
	            $('.divRight').css({ width:$("body").width()-180, height: h - v_height, 
				                    'position': 'absolute',
									'top': topWidth + 2,
				 					'left': leftWidth + 10,
				                    'display': 'block', 
				 					'margin-left': 0,
				 					'margin-right': 0 });
	            
	            
	           // $('.divLeft').css({ width: 150, height: h - 100, 'display': 'block' ,'margin-left': 10});
	            //$('.divRight').css({ width: window.screen.width-250,height: h - 100, 'display': 'block', 'margin-left': 7 });
	            jericho.buildTree();
	            jericho.buildTabpanel();
	            jericho.removeLoader();
	            
	            $(".sdmenu").hide();
	            $("#my_menu"+$(".topmenu>span:eq(0)").attr("id")).show();
	            $(".sdmenu").each(function(i,o){
		            var myMenu = new SDMenu(o.id);
		            myMenu.speed=5;
					myMenu.init();
	            });
	            
				$(".topmenu>span").addClass("menu").click(function(){
					$(".topmenu>span").removeClass("menuheilight").addClass("menu");
					$(".sdmenu").hide();
					$("#my_menu"+$(this).attr("id")).show();
					$(this).removeClass("menu").addClass("menuheilight");
				}).eq(0).removeClass("menu").addClass("menuheilight");
				
				//tab页可拖动
				$(".divRight").find(".tabs").children("ul").sortable({axis:'x'});
			
        }
    </script>
</head>
<body>
<!-- top begin -->
<div class="divTop">
	
	<div class="topmenu">
		<c:forEach items="${rootMenus}" var="i">
			<span id="${i.indexuuid}">${i.indexname}</span>
		</c:forEach>
	</div>
	<div class="opts" style="float:right">
		<a class="nav-font"  href="${pageContext.request.contextPath}/heaUserAction.hea?action=logout">
	   		<img style="border:0px;" src="${pageContext.request.contextPath }/view_ref/page/images/tc.gif" alt="注销" />
	   		注销
	   	</a>
	   	<a class="nav-font" href='${pageContext.request.contextPath}/adminlogin.jsp'>
	   		<img style="border:0px;" src="${pageContext.request.contextPath }/view_ref/page/images/icon2.gif" alt="注销" />
	   		系统管理
	   	</a>
	</div>
    		
    
</div>
<!-- top end -->
<!-- main begin -->
<div class="divMain">
    <div class="divLeft"><!-- left begin -->
       <!-- <div style="float: left" id="my_menu" class="sdmenu">
  			 	<c:forEach items="${rootMenus}" var="i">
  			 		<div>
  			 			<span>${i.indexname}</span>
  			 			<c:forEach items="${indexs}" var="subindex">
  			 				<c:if test="${subindex.parentIndex.indexuuid eq i.indexuuid}">
   			 				<a class="func" href="${subindex.indexurl}" onclick="return false;">${subindex.indexname}</a>
   			 			</c:if>
  			 			</c:forEach>
  			 		</div>
  			 	</c:forEach>
	    </div>-->
	    <c:forEach items="${leftMenu}" var="map" varStatus="i">
		    <div style="float:left" id="my_menu${map.key}" class="sdmenu">
		    	<c:forEach items="${map.value}" var="index">
		    		<div>
		    			<span>${index.indexname}</span>
		    			<c:forEach items="${index.subIndexes}" var="sub">
		    				<a class="func" href="${sub.indexurl}" onclick="return false;">${sub.indexname}</a>
		    			</c:forEach>
		    		</div>
		    	</c:forEach>
		    </div>
	    </c:forEach>
    </div><!-- left end -->
    <div class="divRight"></div>
</div>

<!-- main end -->
<div id="divMainLoader">Loading...</div>

<div id="tbNews">
    <h1>欢迎使用山西领导手册V1.0</h1>
</div>
</body>
</html>