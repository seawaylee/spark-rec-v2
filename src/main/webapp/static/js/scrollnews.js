function AutoScroll(obj) {
	$(obj).find("ul:first").animate({
				marginTop : "-25px"
			}, 500, function() {
				$(this).css({
							marginTop : "0px"
						}).find("li:first").appendTo(this);
			});
}
$(document).ready(function() {
	setInterval('AutoScroll("#scroll")', 2500);
});