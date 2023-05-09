<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/preset.jsp" %>
<script type="text/javascript">
	$(() => {
		$('.toggle').load(e => $(e.target).attr('data-toggle', "false"));
		$('.toggle').on('click', e => {
			if (e.target.getAttribute('data-toggle') == "false") e.target.setAttribute('data-toggle', "true");
			else e.target.setAttribute('data-toggle', "false");
		});
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
		$('.admin-report-container').on('click', e => {
			let target = $(e.target).closest('.admin-report-container');
			let isToggled = target.attr('data-toggle') == 'true';
			let path = target.find('.admin-report-item').find('path');
			if (isToggled) {
				path.attr('d', 'M 32 224 L 256 32 480 224');
				target.find('.admin-report-info').css('height', 'max-content');
				//target.find('.admin-report-info').animate({'height': 'max-content'}, 250, $.bez([0.33, 1.0, 0.33, 1.0]));
				//target.find('.admin-report-info').css('height', '100px');
			} else {
				path.attr('d', 'M 32 32 L 256 224 480 32');
				//target.find('.admin-report-info').animate({'height': '0'}, 250, $.bez([0.33, 1.0, 0.33, 1.0]));
				target.find('.admin-report-info').css('height', '0');
			}
		});
		$('.admin-report-info').on('click', e => {
			e.stopPropagation();
			
		});
	});
</script>
<!-- <div class="admin-container" data-page="1"> -->
<div class="admin-container full-height">
	<div class="admin-main">
		<div class="admin-main-title-box">
			<h2 class="admin-main-title">신고 관리</h2>
		</div>
		<div class="admin-board-2-head display-flex justify-content-flex-start align-items-center">
			<span class="text-align-center width-50px">번호</span>
			<span class="color-subtheme font-weight-bolder text-align-center width-75px">항목</span>
			<span class="flex-grow-1">신고 내용</span>
			<span class="text-align-center width-100px" style="margin-right: 24px;">신고 시간</span>
		</div>
		<div class="admin-board-2">
			<c:forEach var="report" items="${reports }">
				<div class="admin-report-container toggle" data-id="${report.report_id }" data-toggle="false">
					<div class="admin-report-item">
						<span class="width-50px text-align-center">${report.report_id }</span>
						<span class="color-subtheme font-weight-bold width-75px text-align-center">
							<c:choose>
								<c:when test="${report.type == 'MEMBER' }">회원</c:when>
								<c:when test="${report.type == 'ARTICLE' }">게시글</c:when>
								<c:otherwise>댓글</c:otherwise>
							</c:choose>
						</span>
						<span class="flex-grow-1" style="width: calc(100% - 245px); overflow-x: hidden; text-overflow: ellipsis;">${report.report_content }</span>
						<span class="font-weight-bold font-size-10px width-100px text-align-center"><fmt:formatDate value="${report.report_date }" pattern="yyyy/MM/dd hh:mm:ss"/></span>
						<svg viewBox="0 0 512 256">
							<path d="M 32 32 L 256 224 480 32"/>
						</svg>
					</div>
					<div class="admin-report-info">
						<div style="height: 100px;"></div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>