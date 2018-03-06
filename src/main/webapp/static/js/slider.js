$(document).ready(function(){
	var _c = _h = 0;
	var time = 5000;
	var count = $('#frame_links a').length -1;
	var change = function(i){
		$('#feature_images li').fadeOut('show').eq(i).fadeIn('slow');
		$('#frame_links a').attr('class','');
		$('#frame_links a:eq('+i+')').attr('class','activeSlide');
	}
	var auto = function(){
		var i = _c == count ? 0 : _c+1;
		_c = i;
		change(i);
	}
	var play = function(){
		_h = setInterval(auto,time);
	}
	play();
	$('#frame_links a').click(function(){
		var i = $(this).index();
		clearInterval(_h);
		_c = i;
		change(i);
		_h = setInterval(auto,time);
	});
	$('#feature_controls .prev').click(function(){
		var i = (_c == 0 ? count : _c - 1);
		_c = i;
		clearInterval(_h);
		change(i);
		_h = setInterval(auto,time);
		return;
	});
	$('#feature_controls .next').click(function(){
		var i = (_c == count ? 0 : _c + 1);
		_c = i;
		clearInterval(_h);
		change(i);
		_h = setInterval(auto,time);
	});
});