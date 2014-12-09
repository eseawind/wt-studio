$(document).ready(function(){

	//Toogle Information section

	$("#header a").click(function(){
		$("#information").slideToggle({
			duration: "slow",
			easing: "easeinout"
		});
		$("#header a").toggleClass("active");
			return false;
	});
	$('#news').tabs({ fxSlide: true, fxFade: true, fxSpeed: 'fast' });
	$('#people').tabs(Math.floor(Math.random()*$("#people li").length+1), { fxSlide: true, fxFade: true, fxSpeed: 'fast' });

	//Settings for Projects
	$(".projectList").jCarouselLite({
		btnNext: ".next",
		btnPrev: ".prev",
		visible: 1, //控制显示的图片数
		speed: "slow",
		easing: "easeinout",
		circular: true
	});

	$("//a[@rel='external']").attr("target","_blank");
	
	//更改默认的样试
	$("#projects").css("width","250px");
	$("#myDiv").css("height","150px");
	$("#myBody").css("width","600px").css("margin","0px");
	
	
});