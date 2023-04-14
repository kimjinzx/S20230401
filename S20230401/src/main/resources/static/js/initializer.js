/**
 * 
 */
 
const getToggle = () => document.querySelector('.modeToggle');
const isUserColorTheme = window.localStorage.getItem('color-theme');
const isOsColorTheme = window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
const getUserTheme = () => isUserColorTheme ? isUserColorTheme : isOsColorTheme;

$(function() {
	const getStyle = (el,styleProp) => {
	    if (el.currentStyle) return el.currentStyle[styleProp];
	    return document.defaultView.getComputedStyle(el,null)[styleProp];
	};
	
	const getLuminance = (r, g, b) => 0.2126 * r + 0.7152 * g + 0.0722 * b;
	
	const hoverEffect = (target, ratio) => {
		let bgColor = getStyle(target, 'background-color');
		let [r, g, b] = bgColor.match(/\d+/g).map(x => parseInt(x));
		//console.log('red: ' + r + ', green: ' + g + ', blue: ' + b);
		
		let luminance = getLuminance(r, g, b);
		if (luminance > 127.5) {
			r = Math.round(r * (1 - ratio));
			g = Math.round(g * (1 - ratio));
			b = Math.round(b * (1 - ratio));
		} else {
			r += Math.round((255 - r) * ratio);
			g += Math.round((255 - g) * ratio);
			b += Math.round((255 - b) * ratio);
		}
		target.style.backgroundColor = 'rgb(' + r + ', ' + g + ', ' + b + ')';
	};
	
	/* Advanced Hover Effect */
	$('.adv-hover').mouseenter(e => {
		hoverEffect(e.target, 0.2);
	});
	$('.adv-hover').mouseleave(e => {
		e.target.style.backgroundColor = '';
	});
	
	
	/* Interruptable Popup Toggle */
	$('.togglePopup').each((index, item) => {
		$('#' + item.getAttribute('id') + '-popup').hide();
		$('#' + item.getAttribute('id') + '-popup').click(e => {
			e.stopPropagation();
		});
	});
	$('.togglePopup').on('click', e => {
		e.stopPropagation();
		let thisID = e.target.getAttribute('id');
		if (typeof thisID != "undefined" && thisID != null) {
			$('#' + thisID + '-popup').toggle();
		}
		$('.togglePopup').each((index, item) => {
			if (item.getAttribute('id') != thisID) $('#' + item.getAttribute('id') + '-popup').hide();
		});
	});
	$(document).click(e => {
		$('.togglePopup').each((index, item) => {
			$('#' + item.getAttribute('id') + '-popup').hide();
		});
	});
	
	
	/* Toggle And Fix Button */
	$('.toggle').attr('data-toggle', "false");
	$('.toggle').click(e => {
		if (e.target.getAttribute('data-toggle') == "false") e.target.setAttribute('data-toggle', "true");
		else e.target.setAttribute('data-toggle', "false");
	});
	
	
	/* View Mode Switcher */
	$('#viewMode').click(e => {
		if ($('#viewMode').attr('data-toggle') == "false") {
			$('#viewMode').attr('data-toggle', "true");
			document.documentElement.setAttribute('color-theme', 'dark');
		}
		else {
			$('#viewMode').attr('data-toggle', "false")
			document.documentElement.setAttribute('color-theme', 'light');
		};
	});
	
	
	/* View Mode Detect */
	if (getUserTheme === 'dark') {
		window.localStorage.setItem('color-theme', 'dark');
		document.documentElement.setAttribute('color-theme', 'dark');
		if ($('#viewMode') != null) $('#viewMode').attr('data-toggle', "true");
	} else {
		window.localStorage.setItem('color-theme', 'light');
		document.documentElement.setAttribute('color-theme', 'light');
		if ($('#viewMode') != null) $('#viewMode').attr('data-toggle', "false");
	}
});