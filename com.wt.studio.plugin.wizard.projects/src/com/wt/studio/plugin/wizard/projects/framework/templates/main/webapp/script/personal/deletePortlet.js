/**
 * @author Administrator
 */
jQuery(function(){
	jQuery("a.delete").click(function(){
		var cur = jQuery(this);
		showMsg({
			msg:'该资源删除后无法回复，您确定删除吗？',
			buttons:{
				'取消':function(){
					jQuery(this).dialog("close");
				},
				'确定':function(){
					var portletId = cur.attr("portletId");
					jQuery.ajax({
						url:'heaPortletPropertyAction.hea',
						data:{action:'delete',portletId:portletId},
						type:'post',
						success:function(flag){
							if(flag == "1"){
								location.href="heaPortletPropertyAction.hea?action=findAllPortlet&state=page";
							}else{
								
								showMsg({
									msg:flag
								});								
							}
							
						}
						
					});
					jQuery(this).dialog("close");
				}
			}
		});
		
	});
});
