<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/modules/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title> 
<link href="${pageContext.request.contextPath }/modules/eqmt/css/common.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/script/common/jquery-1.4.2.min.js"></script>

<script language="javascript">
$(document).ready(function(){
	$('#userid').focus();
	$(".login").css("top",($(window).height()/2));
	$("body").css("height",$(window).height());
	$(window).resize(function(){
		$("body").css("height",$(window).height());
		$(".login").css("top",($(window).height()/2));
	});
});

</script>
<script type="text/javascript">
function formSubmit(){
	var idValue=$('#userid').val();
	var passwordValue=$('.txt pwd').val();
	if(!idValue){
		return ;
	}
	if(!passwordValue){
		return ;
	}
	var targetW = new Date();
	
	window.opener = "aaa";
	window.open('','_parent','');  
	form1.target = "newWindow" + targetW.getSeconds();
	var win = window.open("about:blank","newWindow" + targetW.getSeconds(),"height=screen.height,width=screen.width,scrollbars=yes,status=yes,toolbar=no,menubar=no,location=yes,fullscreen=0");
	win.moveTo(0,0);
	win.resizeTo(screen.width,screen.height); 
	win.focus();
	document.form1.submit();
	window.close();
}

function init() {
	document.onkeydown = function() { //回车键提交
		if (window.event.keyCode == 13) {
			formSubmit();
		}
		if (window.event.keyCode == 116) { ////禁用F5键刷新
			window.event.keyCode = 0;
			window.event.returnValue = false;
		}

		var k = event.keyCode;
		if ((event.ctrlKey == true && k == 82) || (k == 116)
				|| (event.ctrlKey == true && k == 116)) {
			event.keyCode = 0;
			event.returnValue = false;
			event.cancelBubble = true;
		}
	}
	document.oncontextmenu = new Function("event.returnValue=false;"); //禁用右键刷新
}
</script>
<style type="text/css">
body{
	background:#0036a5 url(${pageContext.request.contextPath }/modules/eqmt/images/bg.gif) no-repeat center;
}
body .login{
	background:url(${pageContext.request.contextPath }/modules/eqmt/images/spanBg.png) no-repeat center;
	height:310px;
	width:540px;
	margin:auto;
	margin-top:-155px;
	position:relative;
}
body .login .txt{
	padding:0 5px;
	font-size:14px;
	color:#003F73;
	margin-left:180px;
	height:30px;
	line-height:30px;
	width:250px;
	background:url(${pageContext.request.contextPath }/modules/eqmt/images/inputBg.gif) repeat-x;
	border:solid #96B0D1 1px;
}
body .login .name{
	margin-top:93px;
	
}
body .login .pwd{
	margin-top:20px;
}
body .login .btn{
	margin-top:30px;
	float:left;
	width:112px;
	height:38px;
	border:none;
	cursor:pointer;
}
body .login .btnlogin{
	background:url(${pageContext.request.contextPath }/modules/eqmt/images/btnLogin.gif) no-repeat;
	margin-left:205px;
	margin-right:5px;
}
body .login .btnreset{
	background:url(${pageContext.request.contextPath }/modules/eqmt/images/btnReset.gif) no-repeat;
}

body .login .sp{
	margin:15px 20px;
	display:block;
	position:absolute;
	right:10px;
	bottom:0;
	color:#666;
}
body .login .message{
	margin:110px 10px;
	display:block;
	position:absolute;
	right:140px;
	bottom:0;
	color:#666;
}
</style>
</head>
<body>
	<div class="login" >
        <form name="form1" method="post" action="<html:rewrite action='heaUserAction' />">
        	<input type="hidden" value="validateUser" name="action"/>
            <input class="txt name" type="text" name="id" id="userid"/>
            <input class="txt pwd" type="password" name="password"/>
            <input class="btn btnlogin" type="submit" value=""/>
            <input class="btn btnreset" type="reset" value=""/>
        </form>
        <span class="message"> 
        	<font color="red">${message}</font>
        </span>
        <span class="sp"> 
        	建议使用浏览器IE7+...
        </span>
    </div>
</body>
</html>
