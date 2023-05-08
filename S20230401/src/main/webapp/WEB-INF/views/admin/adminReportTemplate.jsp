<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/preset.jsp" %>
<script type="text/javascript">
	$(() => {
		
		
		$('.radio-button').on('click', e => {
			let group = $(e.target).attr('data-group');
			$('.radio-button[data-group="' + group + '"]').not($(e.target)).css('background-color', '').attr({
				'data-selected' : 'false',
				'disabled' : false
			});
			$(e.target).css('background-color', '').attr({
				'data-selected' : 'true',
				'disabled' : true
			});
		});
		$('.report-tab').on('click', e => {
			$('.admin-container').attr('data-page', '1');
		});
	});
</script>
<div class="admin-container" data-page="1">
	<div class="admin-main">
		<div class="admin-main-title-box">
			<h2 class="admin-main-title">신고 관리</h2>
		</div>
		<div class="admin-board-2-tabgroup">
			<button class="report-tab radio-button" data-type="member" data-group="report-type" data-selected="true" type="button" disabled>
				<span>회원 신고</span>
			</button>
			<button class="report-tab radio-button" data-type="article" data-group="report-type" data-selected="false" type="button">
				<span>게시글 신고</span>
			</button>
			<button class="report-tab radio-button" data-type="reply" data-group="report-type" data-selected="false" type="button">
				<span>댓글 신고</span>
			</button>
		</div>
		<div class="admin-board-2" >
			
		</div>
	</div>
</div>