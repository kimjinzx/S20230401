<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/preset.jsp" %>
<script type="text/javascript">
	const viewNotice = (art_id) => {
		let currentPage = $('.admin-board-1').attr('page');
		$.ajax({
			url: '${pageContext.request.contextPath}/admin/notice/view',
			type: 'post',
			data: JSON.stringify({ 'art_id' : art_id, 'page' : currentPage }),
			dataType: 'html',
			contentType: 'application/json',
			success: data => {
				$('.admin-side-panel > .admin-side-panel-content').html(data);
			}
		});
	};
	$(() => {
		$('.admin-board-1 .admin-article-item').click(e => {
			viewNotice(parseInt($(e.target).attr('data-id')));
			//$('.admin-container admin-side-panel').css('display', 'flex').css('width', '100%');
			if ($('.admin-side-panel').css('display') != 'none') return;
			$('.admin-side-panel').css({ 'display' : 'flex' });
			$('.admin-side-panel').animate({ 'width' : '75%' }, 250);
		});
	});
</script>
<div class="admin-container" data-page="1">
	<div class="admin-main">
		<h2>공지사항 관리</h2>
		<div class="admin-board-1-head">
			
		</div>
		<div class="admin-board-1">
			<c:forEach var="article" items="${articles }">
				<div class="admin-article-item" data-id="${article.art_id }">
					<span class="admin-article-title">${article.art_title }</span>
					<span class="admin-article-regdate">
						<fmt:formatDate value="${article.art_regdate }" pattern="yyyy-MM-dd hh:mm:ss"/>
					</span>
				</div>
			</c:forEach>
		</div>
		<div class="admin-board-1-page">
			<button class="paging-button paging-prev" type="button" ${startPage != 1 ? '' : 'disabled'}>
				<svg>
					<path d="M 11.5 1 L 4.5 8 11.5 14"/>
				</svg>
			</button>
			<c:forEach var="pg" begin="${startPage }" end="${endPage }">
				<button class="paging-button paging-page" type="button" data-page="${pg }" ${pg == page ? 'disabled' : '' }>
					<span>${pg }</span>
				</button>
			</c:forEach>
			<button class="paging-button paging-next" type="button" ${endPage != totalPage ? '' : 'disabled'}>
				<svg>
					<path d="M 4.5 1 L 11.5 8 4.5 14"/>
				</svg>
			</button>
		</div>
		<div class="admin-board-1-foot">
			
		</div>
	</div>
	<div class="admin-side-panel">
		<div class="admin-side-panel-header" style="height: 32px; background-color: var(--subtheme);">
			<button type="button">
				
			</button>
		</div>
		<div class="admin-side-panel-content">
			
		</div>
	</div>
</div>