<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String indexType = request.getParameter("indexType");
	request.setAttribute("indexType",indexType);
 %>
<script type="text/javascript">
		//注册左侧树事件响应到tab页
	function loadConsoleTree(){
			
	var tree=new dhtmlXTreeObject('sidebar',"100%","100%",0);
	tree.setImagePath("${pageContext.request.contextPath}/images/component/dhtmlxtree/csh_bluebooks/");
	tree.setXMLAutoLoading("${pageContext.request.contextPath}/heaIndexAction.hea?action=loadNextNodes&indexType=${indexType}");
	tree.loadXML("${pageContext.request.contextPath}/heaIndexAction.hea?action=initTree&indexType=${indexType}");//load root level from xml
	
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
	                        iconImg:  '${pageContext.request.contextPath }/view_ref/common/ui/theme/common/images/jerichotab.png',
	                        loadOnce: true,
	                        contentCss: { 'height': $('#rightContent').height() },
	                        data: {
	                            dataType: 'iframe', //formtag,html,iframe,ajax
	                            dataLink: url
	                        },
	                        type:'add'
	                    }).showLoader().loadData();         
				
			});		
			//初使化tab页
			 var jericho = {
           
            buildTree: function() {
            		$("span.standartTreeRow").mousedown(function() {
            			var t = $(".selectedTreeRow")[0] ;
            			alert(t);
            			tree.setOnClickHandler(function(){
            				$.fn.jerichoTab.addTab({
	                        tabFirer: t,
	                        title: $(this).text(),
	                        closeable: true,
	                        iconImg:  '${pageContext.request.contextPath }/view_ref/common/ui/theme/common/images/jerichotab.png',
	                        loadOnce: true,
	                        contentCss: { 'height': $('#rightContent').height() },
	                        data: {
	                            dataType: 'iframe', //formtag,html,iframe,ajax
	                            dataLink: url
	                        },
	                        type:'add'
	                    }).showLoader().loadData();
            			});
	                    
            	}); 
            },
            
            
            //初使第一个默认tab页
            buildTabpanel: function() {
            	
                $.fn.initJerichoTab({
                    renderTo: '#rightContent',
                    uniqueId: 'tab_index',
                    tabWidth:120,
                    contentCss: { 'height': $('#rightContent').height() },
                    tabs: [{
                        title: '首页',
                        closeable: false,
                        iconImg: '${pageContext.request.contextPath }/view_ref/common/ui/theme/common/images/jerichotab.png',
                        data: { dataType: 'iframe', dataLink: '${pageContext.request.contextPath }/runtime.jsp' },
                        onLoadCompleted: function(h) {
                            $('<b style="color:red" />').html('The JerichoTab processed in ').appendTo(h);
                        }
                    }],
                    activeTabIndex: 0,
                    loadOnce: true,
                    type:'init'
                    });
                }
            }
			jericho.buildTree();
	       	jericho. buildTabpanel();
	       	
	       	
	       	
	       	//树构事件响应，每点击结点动态添加tab页
	       	var clickedNode=new Array();
	       	var temp=null;
	       	$("span.standartTreeRow").mousedown(function(){
				        	temp=$(this);
				        });
	       	
	       	
	       //	var t=null;
			
			
	       // $(function() {
	        	//页面加载后去掉图片边框样试
				$("#home").css({"border":0,"width":25,"height": 25,"cursor":"pointer"});
	        	
	        	//div+css布局样式控制
	        	var h=$('#sidebar').height()+32;
	           $('#sidebar').css('height',h);
	        //});
		}
 </script>