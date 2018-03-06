$(document).ready(function(){
	$(".main_nav li").mouseover(function(){
		$(".main_nav li").attr("class","");
		$(this).attr("class","navActived");
		var index = $(this).index();
		$(".second_nav ul").hide().eq(index).show();
	});
});