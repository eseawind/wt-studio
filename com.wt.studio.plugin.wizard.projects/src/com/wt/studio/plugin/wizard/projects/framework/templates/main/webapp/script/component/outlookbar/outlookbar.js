/**
 * Author: Aaron
 * Email: yunlong.ye@longshine.com
 * Date: 2006-04-17
 */
function OutlookBar(id)
{
	this.titlelist=new Array();
	this.itemlist=new Array();
	
	this.tempinnertext1 = "";
	this.tempinnertext2 = "";
	this.outlooksmoothstat = 0;
	this.id = id;
	OutlookBar.allbar[id] = this;
	
	this.otherclass="border=0 cellspacing='0' cellpadding='0' style='height:100%;width:100%'valign=middle align=center ";
	this.starttitle=-1;
	this.opentitle=this.starttitle;
	this.selectedItem = -1;
	this.timedelay=50;
	this.sliding = false;
	this.inc=15;
}

OutlookBar.allbar ={};
OutlookBar.prototype.switchoutlookBar = function (sTitleId)
{
	var openId = this.opentitle;
	this.opentitle=sTitleId;
	//alert(openId+" "+sTitleId);
	var id1,id2,id1b,id2b;
	if (sTitleId!=openId && this.outlooksmoothstat==0){
		if (sTitleId!=-1)
		{
			if (openId==-1){
				id2=this.id+"_blankdiv";
				id2b=this.id+"_blankdiv";
			}
			else{
				id2=openId+"-parent";
				id2b=openId+"-cont";
				//alert(document.getElementById(this.id+"_outlooktitle"+i).style);
				document.all(openId).className = "outlookbar-folder-unselected"
			}
			id1=sTitleId+"-parent";
			id1b=sTitleId+"-cont";
			document.all(sTitleId).className = "outlookbar-folder-selected"
			
			document.all(openId+"-openicon").style.display="none";
			document.all(openId+"-closeicon").style.display="";
			document.all(sTitleId+"-openicon").style.display="";
			document.all(sTitleId+"-closeicon").style.display="none";
			
			this.sliding = true;
			this.smoothout(id1,id2,id1b,id2b,0);
			this.sliding = false;
		}
		else
		{
			document.all(this.id+"_blankdiv").style.display="";
			document.all(this.id+"_blankdiv").sryle.height="100%";
			document.all(openId+"-parent").style.display="none";
			document.all(openId+"-parent").style.height="0%";
			document.all(openId).className = "outlookbar-folder-selected";
		}
	}
}
OutlookBar.prototype.smoothout = function (id1,id2,id1b,id2b,stat)
{
	if(stat==0){
		this.tempinnertext1=document.all(id1b).innerHTML;
		this.tempinnertext2=document.all(id2b).innerHTML;
		document.all(id1b).innerHTML="";
		document.all(id2b).innerHTML="";
		this.outlooksmoothstat=1;
		document.all(id1b).style.overflow="hidden";
		document.all(id2b).style.overflow="hidden";
		document.all(id1).style.height="0%";
		document.all(id1).style.display="";
		setTimeout(this.id+".smoothout('"+id1+"','"+id2+"','"+id1b+"','"+id2b+"',"+this.inc+")",this.timedalay);
	}
	else
	{
		stat+=this.inc;
		if (stat>100)
			stat=100;
		document.all(id1).style.height=stat+"%";
		document.all(id2).style.height=(100-stat)+"%";
		if (stat<100) 
			setTimeout(this.id+".smoothout('"+id1+"','"+id2+"','"+id1b+"','"+id2b+"',"+stat+")",this.timedalay);
		else
		{
			document.all(id1b).innerHTML=this.tempinnertext1;
			document.all(id2b).innerHTML=this.tempinnertext2;
			this.outlooksmoothstat=0;
			document.all(id1b).style.overflow="auto";
			document.all(id2).style.display="none";
		}
	}
}
OutlookBar.prototype.getOutLine = function ()
{
	var outline = [];
	outline.push("<table ");
	outline.push(this.otherclass);
	outline.push(" class='outlookbar-table' >");
	for (var i=0;i<(this.titlelist.length);i++)
	{
		var titleId = this.titlelist[i].identification;
		outline.push("<tr><td name=\"");
		outline.push(titleId);
		outline.push("\" id=\"");
		outline.push(titleId);
		outline.push("\"");
		if (this.titlelist[i].identification!=this.opentitle) 
			outline.push(" nowrap align=center class='outlookbar-folder-unselected'");
		else
			outline.push(" nowrap align=center class='outlookbar-folder-selected' ");
		outline.push(" onclick=\"javacript:");
		outline.push(this.id);
		outline.push(".switchoutlookBar('");
		outline.push(titleId);
		outline.push("');");
		outline.push(this.titlelist[i].action);
		outline.push("\"><span class=\"smallFont\">");
		var openDisp = (this.titlelist[i].identification==this.opentitle)?"":"none";
		var iconDisp = (this.titlelist[i].identification==this.opentitle)?"none":"";
		outline.push("<img id=\"");
		outline.push(titleId);
		outline.push("-openicon\" class=\"folder-open-icon\" src=\"");
		outline.push(this.titlelist[i].openIcon);
		outline.push("\" style=\"display:");
		outline.push(openDisp);
		outline.push("\"/><img id=\"");
		outline.push(titleId);
		outline.push("-closeicon\" class=\"folder-close-icon\" src=\"");
		outline.push(this.titlelist[i].icon);
		outline.push("\" style=\"display:");
		outline.push(iconDisp);
		outline.push("\"/>");
		outline.push(this.titlelist[i].text);
		outline.push("</span></td></tr><tr><td name=\"");
		outline.push(titleId);
		outline.push("-parent\" valign=\"top\" align=\"left\" id=\"");
		outline.push(titleId);
		outline.push("-parent\" style=\"width:100%");
		if (this.titlelist[i].identification!=this.opentitle) 
			outline.push(";display:none;height:0%;");
		else
			outline.push(";display:;height:100%;");
		var titleContId = this.titlelist[i].identification + "-cont";
		outline.push("\"><div class=\"folder-cont\" name=\"");
		outline.push(titleContId);
		outline.push("\" id=\"");
		outline.push(titleContId);
		outline.push("\" >");
		for (j=0;j<this.itemlist[i].length;j++)
		{
			outline.push(this.itemlist[i][j].showItem(this.id,i+"_"+j));
		}
		outline.push("</div></td></tr>");
	}
	outline.push("</table>");
	return outline.join("");
}
OutlookBar.prototype.show = function ()
{
	var outline = [];
	outline.push("<div id=\"");
	outline.push(this.id);
	outline.push("_outLookBarDiv\" name=\"");
	outline.push(this.id);
	outline.push("_outLookBarDiv\" style='width=100%;height:100%'>");
	outline.push(this.getOutLine());
	outline.push("</div>");
	document.getElementById(this.id).innerHTML = outline.join("");
}

function OutlookItem (sText,sAction,sIdentification,sIcon,sPopupMenu,sHint,sOpenIcon){
	this.text = sText;
	this.action = sAction;
	this.identification = sIdentification;
	this.icon = sIcon;
	this.openIcon = sOpenIcon || sIcon;
	this.popupMenu = sPopupMenu;
	this.hint = sHint;
}

OutlookItem.prototype.showItem = function (sPrefix,sId){
	var str = [];
	str.push("<span><div class='item' id=\"");
	str.push(sPrefix+"item"+sId);
	str.push("\"><div onclick=\"");
	str.push(this.action);
	str.push("\" class='itembg' id='");
	str.push(sPrefix+"itembg"+sId);
	str.push("' ><div class='itemmask' style='' id='");
	str.push(sPrefix+"itembgmask"+sId);
	str.push("'><div class='itemborder' id='");
	str.push(sPrefix+"itemborder"+sId);
	str.push("'><div class='itemicon'");
	if(this.popupMenu != null && this.popupMenu.length > 0){
		str.push(" popupmenu='");
		str.push(this.popupMenu);
		str.push("'");
	}
	str.push(" id='");
	str.push(sPrefix+"itemicon"+sId);
	str.push("' title='");
	str.push(this.hint);
	str.push("' onMouseOut=\"OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemout('");
	str.push(sId);
	str.push("')\" onMouseDown=\"OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemdown('");
	str.push(sId);
	str.push("')\" onMouseOver=\"OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemover('");
	str.push(sId);
	str.push("')\" onMouseUp=\"OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemover('");
	str.push(sId);
	str.push("');OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemSelected('");
	str.push(sId);
	str.push("')\"><img src='");
	str.push(this.icon);
	str.push("'/></div><div class='itemicon' style='display:none;' ");
	if(this.popupMenu != null && this.popupMenu.length > 0){
		str.push("popupmenu='");
		str.push(this.popupMenu);
		str.push("' ");
	}
	str.push(" id='");
	str.push(sPrefix+"itemopenicon"+sId);
	str.push("' title='");
	str.push(this.hint);
	str.push("' onMouseOut=\"OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemout('");
	str.push(sId);
	str.push("')\" onMouseDown=\"OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemdown('");
	str.push(sId);
	str.push("')\" onMouseOver=\"OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemover('");
	str.push(sId);
	str.push("')\" onMouseUp=\"OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemover('");
	str.push(sId);
	str.push("');OutlookBar.allbar['");
	str.push(sPrefix);
	str.push("'].itemSelected('");
	str.push(sId);
	str.push("')\"><img src='");
	str.push(this.openIcon);
	str.push("'/></div></div></div></div><div class='itemtext' id='");
	str.push(sPrefix+"itemtext"+sId);
	str.push("'>");
	str.push(this.text);
	str.push("</div></div></span>");
	return str.join("");
}
OutlookBar.prototype.itemSelected = function(item){
	if(this.sliding)
		return;
	if(this.selectedItem == item)
		return;
	document.all(this.id+"itemicon"+item).style.display = "none";
	document.all(this.id+"itemopenicon"+item).style.display = "";
	document.all(this.id+"itemopenicon"+item).className = "itemicon-selected";
	eval(this.id+"item"+item).className = "item-selected";
	if(this.selectedItem != -1){
		document.all(this.id+"itemopenicon"+this.selectedItem).className = "itemicon";
		document.all(this.id+"itemopenicon"+this.selectedItem).style.display = "none";
		document.all(this.id+"itemicon"+this.selectedItem).style.display = "";
		eval(this.id+"item"+ this.selectedItem).className = "item";
	}
	this.selectedItem = item;
		
}

OutlookBar.prototype.itemover = function(item){
	if(this.sliding)
		return;
	document.all(this.id+"itemicon"+item).style.display = "none";
	document.all(this.id+"itemopenicon"+item).style.display = "";		
	eval(this.id+"itemborder"+item).className="itemborder-hover";
	eval(this.id+"itembg"+item).className="itembg-hover";
}

OutlookBar.prototype.itemdown = function(item){
	if(this.sliding)
		return;
	eval(this.id+"itemborder"+item).className="itemborder-down";
	eval(this.id+"itembg"+item).className="itembg-down";
}

OutlookBar.prototype.itemout = function(item){
	if(this.sliding)
		return;
	if(this.selectedItem != item){
		document.all(this.id+"itemopenicon"+item).style.display = "none";
		document.all(this.id+"itemicon"+item).style.display = "";
	}	
	eval(this.id+"itemborder"+item).className="itemborder";
	eval(this.id+"itembg"+item).className="itembg";
}

OutlookBar.prototype.addtitle = function (sText,sAction,sIdentification,sIcon,sPopupMenu,sHint,sOpenIcon)
{
	this.itemlist[this.titlelist.length]=new Array();
	this.titlelist[this.titlelist.length]=new OutlookItem(sText,sAction,sIdentification,sIcon,sPopupMenu,sHint,sOpenIcon);
	return(this.titlelist.length-1);
}
OutlookBar.prototype.additem = function (sText,sAction,sIdentification,sIcon,sPopupMenu,sHint,sParentId,sOpenIcon)
{
	if (sParentId >= 0 && sParentId <= this.titlelist.length)
	{
		this.itemlist[sParentId][this.itemlist[sParentId].length]=new OutlookItem(sText,sAction,sIdentification,sIcon,sPopupMenu,sHint,sOpenIcon);
		this.itemlist[sParentId][this.itemlist[sParentId].length-1].otherclass=" nowrap align=left style='height:5' ";
		return(this.itemlist[sParentId].length-1);
	}
	else
		additem = -1;
}

OutlookBar.prototype.outreflesh = function ()
{
	document.all(this.id+"_outLookBarDiv").innerHTML=this.getOutLine();
}


OutlookBar.prototype.locatefold = function(foldname)
{
	if (foldname==""){
		foldname = this.titlelist[0].text;
	}
	for (var i=0;i<this.titlelist.length;i++)
	{
		if(foldname==this.titlelist[i].text)
		{
			this.starttitle=this.titlelist[i].id;
			this.opentitle=this.titlelist[i].id;
			break;
		}
	}
}
OutlookBar.prototype.locatefoldIndex = function(index)
{
	if(index < 0 || index >= this.titlelist.length){
		index = 0;
	}
	this.starttitle = this.titlelist[index].identification;
	this.opentitle = this.titlelist[index].identification;
}

OutlookBar.prototype.getSelected = function(){
	if(this.selectedItem != -1){
		var nums = this.selectedItem.split("_");
		var titleIndex = nums[0];
		var itemIndex = nums[1];
		return this.itemlist[titleIndex][itemIndex];
	}
	else{
		return null;
	}
}

OutlookBar.prototype.setSelected = function(oItem){
	for(var i = 0; i < this.titlelist.length; i++){
		for(var j = 0; j < this.itemlist[i].length; j++){
			if(this.itemlist[i][j].identification == oItem.identification){
				this.itemSelected(i+"_"+j);
				if(this.opentitle != this.titlelist[i].identification){
					this.switchoutlookBar(this.titlelist[i].identification);
				}
				return true;
			}
		}
	}
	return false;
	
}