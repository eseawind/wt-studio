<%@ page language="java"%>
<%
	/*
		遮罩层窗口
	*/
 %>
<style>
#divDialogWindow {
	opacity: 0.6;
	-moz-opacity: 0.6;
	filter: alpha(opacity = 40);
	position: absolute;
	left: 0px;
	top: 0px;
	z-index: 100;
	height: 100%;
	width: 100%;
	background-color: black;
	display: none;
	overflow: hidden;
}

#dialogContent {
	position: absolute;
	z-index: 101;
	left: 0px;
	top: 0px;
	display: none;
	background-color: white;
}

#divDialogTitle {
	height: 25px;
	background-color: #404F8D;
	color: white;
	font-weight: bold;
}

#spanDialogTitleValue {
	float: left;
}
</style>
<script type="text/javascript">
function changDialogURL(url){
	document.getElementById('iframeDialog').src = url;
}

function showDialogWindow2(title, width, height) {
	document.getElementById('divDialogTitle').style.width = width;
	document.getElementById('iframeDialog').style.width = width;
	document.getElementById('iframeDialog').style.height = height;
	document.getElementById('spanDialogTitleValue').innerHTML = title;
	var winWidth =document.body.clientWidth;
	var winHeight =document.body.clientHeight;
	document.getElementById('dialogContent').style.left = (winWidth-width)/2;
	document.getElementById('dialogContent').style.top = (winHeight-height)/2;
	document.getElementById('divDialogWindow').style.display = 'block';
	document.getElementById('dialogContent').style.display = 'block';
}

function showDialogWindow(url, title, width, height) {
	document.getElementById('divDialogTitle').style.width = width;
	document.getElementById('iframeDialog').style.width = width;
	document.getElementById('iframeDialog').style.height = height;
	document.getElementById('iframeDialog').src = url;
	document.getElementById('spanDialogTitleValue').innerHTML = title;
	var winWidth =document.body.clientWidth;
	var winHeight =document.body.clientHeight;
	document.getElementById('dialogContent').style.left = (winWidth-width)/2;
	document.getElementById('dialogContent').style.top = (winHeight-height)/2;
	document.getElementById('divDialogWindow').style.display = 'block';
	document.getElementById('dialogContent').style.display = 'block';
}

function closeDialogWindow(){
	document.getElementById('dialogContent').style.display = 'none';
	document.getElementById('divDialogWindow').style.display = 'none';
}
</script>
<div id="divDialogWindow">
	<table width="100%" height="100%">
		<tr>
			<td align="center" valign="middle">
			</td>
		</tr>
	</table>
</div>
<div id="dialogContent">
	<div id="divDialogTitle">
		<span id="spanDialogTitleValue"></span>
		</div>
	<iframe id="iframeDialog" frameborder="0"></iframe>
</div>
