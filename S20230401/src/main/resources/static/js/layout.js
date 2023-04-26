/**
 * 
 */
 $(window).scroll(() => {
	let scrollTop = $(window).scrollTop();
	let header = $('header');
	let footer = $('footer');
	if (header != null) {
		if (scrollTop > 21 && !header.hasClass('fix-header')) {
			header.addClass('fix-header');
		}
		else if (scrollTop <= 21 && header.hasClass('fix-header')) {
			header.removeClass('fix-header');
		}
	}
	if (footer != null) {
		if (scrollTop < $('body').height() - $(window).height() - footer.height()) {
			$('#scrollToTop').css('bottom', '60px');
			$('#scrollToBottom').css('bottom', '10px');
		} else {
			let interval = scrollTop - ($('body').height() - $(window).height() - footer.height());
			$('#scrollToTop').css('bottom', (interval + 60) + 'px');
			$('#scrollToBottom').css('bottom', (interval + 10) + 'px');
		}
	}
});
$(() => {
	$('#scrollToTop').click(e => $(window).scrollTop(0));
	$('#scrollToBottom').click(e => $(window).scrollTop($('body').height() - $(window).height()));
});