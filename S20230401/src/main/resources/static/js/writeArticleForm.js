/**
 * 
 */
function writeAction () {
	$('#art_content').val(CKEDITOR.instances['articleEditor'].getData());
	if ($('#art_title').val() == '' || $('#art_title').val() == null) {
		return false;
	}
	if ($('#art_content').val() == '' || $('#art_content').val() == null) {
		return false;
	}
	let tagIndex = 1;
	let tagValues = $('#tag-box').find('.tag-box-tag-value');
	for (let tagPart of tagValues) {
		let tag = tagPart.html();
		$('#art_tag' + tagIndex++).val(tag);
	}
	return true;
}
$(() => {
	$('form input').keydown(e => {
		if (e.keyCode == 13) e.preventDefault();
	});
	
	// Tag input Effects
	$('#tag-input').keydown(e => {
		if ($('#tag-box').find('.tag-box-tag').length >= 5 && e.keyCode != 8) {
			e.preventDefault();
			e.target.value = null;
			return;
		}
		if (e.keyCode == 32) {
			e.preventDefault();
			if (e.target.value == '' || !e.target.value || e.target.value == null) return;
			$(e.target).blur();
			/*let elem = '<div class="tag-box-tag"><span class="tag-box-tag-value">' + e.target.value + '</span><button class="tag-box-tag-remove adv-hover" type="button"><svg class="tag-box-tag-remove-svg" width="10" height="10" viewBox="0 0 12 12" stroke-width="4" stroke-linecap="round" stroke-linejoin="round"><path d="M 2 2 L 10 10 M 10 2 L 2 10"/></svg></button></div>';
			$('#tag-box').append(elem);
			$('#tag-box').find('div.tag-box-tag:last-child > button.tag-box-tag-remove').click(e => {
				$(e.target).parent().remove();
			});*/
			e.target.value = null;
			$(e.target).focus();
		} else if (e.keyCode == 13) e.preventDefault();
		else if (e.keyCode == 8) {
			if (e.target.selectionStart == 0 && e.target.selectionEnd == 0) {
				$('#tag-box').find('div.tag-box-tag:last-child').remove();
				e.preventDefault();
			}
		}
	});
	$('#tag-input').blur(e => {
		if ($('#tag-box').find('.tag-box-tag').length >= 5) {
			e.target.value = null;
			return;
		}
		if (e.target.value == '' || !e.target.value || e.target.value == null) return;
		let elem = '<div class="tag-box-tag"><span class="tag-box-tag-value">' + e.target.value + '</span><button class="tag-box-tag-remove adv-hover" type="button"><svg class="tag-box-tag-remove-svg" width="10" height="10" viewBox="0 0 12 12" stroke-width="4" stroke-linecap="round" stroke-linejoin="round"><path d="M 2 2 L 10 10 M 10 2 L 2 10"/></svg></button></div>';
		$('#tag-box').append(elem);
		$('#tag-box').find('div.tag-box-tag:last-child > button.tag-box-tag-remove').click(e => {
			$(e.target).parent().remove();
		});
		e.target.value = null;
	});
});