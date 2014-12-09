<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
$(function(){
		tree=new dhtmlXTreeObject("groups1","100%","100%",0);
       tree.setImagePath("images/component/dhtmlxtreepro/");
       tree.setXMLAutoLoading("heaTemplateLayoutAction.hea?action=loadTree");
      // tree.enableCheckBoxes(true);
       tree.enableRadioButtons(true);
       tree.loadXML("heaTemplateLayoutAction.hea?action=initTree");
       
		$("#groups").dialog({
			autoOpen:false,
			modal: true,
			resizable:false,
			width:400,
			height:500,
			title:'<bean:message key="ui.template.selectgroup"/>',
			buttons: {
				'<bean:message key="ui.cancel"/>':function(){
					tree.closeAllItems(0);
					$(this).dialog('close');
				},
				'<bean:message key="ui.ok"/>': function() {
					if(tree.getAllChecked() != undefined ){
						
					//var group=$("input[checked='true'][name='mzRadio']")[0].value.split('_');
					var group= tree.getAllChecked().split(',');
					$("#group").val(group[0]);
					$("#group").attr("groupid",group[0]);
					$.post("heaTemplateLayoutAction.hea",{action:"findById",tmplId:$("#tmplId").val(),groupId:group[0]},function(data){
						var template = data.split("__")[0];
						var style = data.split("__")[1];
						//å¨æä¿®æ¹å½åæ¨¡æ¿çå¸å±
						if ($.browser.msie) {
							$("#templateStyle").remove();
							var head = document.getElementsByTagName('head').item(0);
						    css = document.createElement('link');
					    	css.href = style; 
						    css.rel = 'stylesheet';
						    css.type = 'text/css';
						    css.id="templateStyle";
						    head.appendChild(css);
						}else if($.browser.mozilla){ 
								$("#templateStyle").attr("href",style);	
						}
						
						$("#placeHolders").html(template);
						$("[prefix]").each(function(i,p){
							//$(p).html("<div style='width:" + ($(p).width() - 4) + ";height: " + ($(p).height() - 4) + ";border:1px dashed #6ABCDE;'><input type=\"checkbox\" ></div>");
							$(p).html("<div style='border:1px dashed #6ABCDE;'><input type=\"checkbox\" ></div>");
						});
						$("[isEdit=1]").each(function(i,d){
							$(d).html("<div style='border:1px dashed #6ABCDE;'><input type=\"checkbox\" checked=\"checked\" ></div>");
						});
					});
					}
					$(this).dialog('close');
					tree.closeAllItems(0);
				}
			}
		});
		
		//$("#groups").load("heaGroupAction.hea?action=showGrouptree");
		//éä¸­çåæ°æ®åºä¸­åæç
		$("#selectGroup").click(function(){
			$("#groups").dialog("open");
		});
		//ä¿®æ¹æ¨¡æ¿æé
		$("#modify").click(function(){
			var noEdit = "";//è¦å é¤æéçå ä½ç¬¦id
			$("[isEdit=1]").each(function(i,e){
				if(!$(e).children().children(":checkbox").attr("checked")){
						noEdit = noEdit + $(e).attr("placeHolderId") + ",";
					}
				});
			//alert(noEdit);
			var editing = "";
			$("#placeHolders").find(":checked").each(function(i,p){
				if(p.parentNode.parentNode.getAttribute("isEdit") == undefined ){
				editing = editing + p.parentNode.parentNode.getAttribute("prefix") + ",";
				}
			});
			//alert(editing);
			$.post("heaTemplateLayoutAction.hea",
					{action:"editPlaceHolder",
					tmplId:$("#tmplId").val(),
					groupId:$("#group").attr("groupid"),
					noEdit:noEdit,
					editing:editing},function(data){
					if(data == '1'){
						alert("<bean:message key='ui.template.placeholder.update.success'/>");
					}
				});
		});
	});