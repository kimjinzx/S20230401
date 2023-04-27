<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지 ▒ ShareGo</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/initializer.js"></script>
<link href="https://unpkg.com/sanitize.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/preference.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/presets.css">
<script type="text/javascript">
	$(() => {
		$('button.selector').click(e => {
			console.log($(e.target).attr('data-group'));
			let groupName = $(e.target).attr('data-group');
			$('button[data-group="' + groupName + '"]').attr('data-toggle', 'false');
			$(e.target).attr('data-toggle', 'true');
		});
	});
</script>
<!-- ignoring Style -->
<style type="text/css">
	/* Buttons */
	button.theme-button {
		background-color: var(--theme);
		color: var(--theme-font);
		font-weight: bold;
		padding: 0;
		border-radius: 5px;
		outline: none;
		border: 2px solid var(--subtheme);
		cursor: pointer;
	}
	button.subtheme-button {
		background-color: var(--subtheme);
		color: var(--subtheme-font);
		font-weight: bold;
		padding: 0;
		border-radius: 5px;
		outline: none;
		border: 0;
		cursor: pointer;
	}
</style>
<style type="text/css">
	html {
		min-height: 100%;
	}
	div#user-background {
		width: 100%;
		padding: 20px 0;
		border-bottom: 5px solid var(--subtheme);
	}
	div#user-profile-box {
		margin: 0 auto;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		align-items: center;
	}
	div#user-image-container {
		width: max-content;
		height: max-content;
		border-radius: 50%;
		overflow: hidden;
		box-shadow: 0 2.5px 2.5px var(--theme-font);
		background-color: white;
	}
	img#user-image {
		object-fit: cover;
		width: 160px;
		height: 160px;
	}
	div#user-nickname-box {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 20px;
	}
	h2#user-nickname {
		margin: 0;
		margin-left: 40px;
	}
	button#user-profile-modify {
		width: 20px;
		height: 20px;
		padding: 0;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-left: 20px;
		background-color: var(--theme);
		outline: none;
		border-radius: 2px;
		border: 0;
		cursor: pointer;
	}
	button#user-profile-modify > * {
		pointer-events: none;
	}
	span#user-hierachy {
		font-size: 14px;
		font-weight: bold;
		color: #888888;
		margin: 10px 0 0 0;
	}
	div#user-info-selector {
		display: flex;
		justify-content: center;
		align-items: stretch;
	}
	div#user-info-selector > * {
		flex-grow: 1;
	}
	button.rect-button {
		border-radius: 0;
		font-size: 20px;
		font-weight: bold;
		padding: 20px 0;
	}
	button.selector[data-toggle="true"] {
		background-color: var(--theme);
		color: var(--theme-font);
	}
</style>
</head>
<body>
	<div class="container">
		<div id="user-background">
			<div id="user-profile-box">
				<div id="user-image-container">
					<img id="user-image" src="${pageContext.request.contextPath }/uploads/profile/${article.mem_image }" onerror="this.onerror=null; this.src='${pageContext.request.contextPath }/image/anonymous.png';">
				</div>
				<div id="user-nickname-box">
					<h2 id="user-nickname">${memberInfo.mem_nickname }</h2>
					<button id="user-profile-modify" class="adv-hover" type="button">
						<!-- https://ionic.io/ionicons -->
						<svg style="width: 16px; height: 16px; fill: var(--theme-font); stroke: var(--theme-font); stroke-linecap: round; stroke-linejoin: round; stroke-width: 32px;" viewBox="0 0 512 512">
							<path d="M384 224v184a40 40 0 01-40 40H104a40 40 0 01-40-40V168a40 40 0 0140-40h167.48" fill="none"/>
							<path d="M459.94 53.25a16.06 16.06 0 00-23.22-.56L424.35 65a8 8 0 000 11.31l11.34 11.32a8 8 0 0011.34 0l12.06-12c6.1-6.09 6.67-16.01.85-22.38zM399.34 90L218.82 270.2a9 9 0 00-2.31 3.93L208.16 299a3.91 3.91 0 004.86 4.86l24.85-8.35a9 9 0 003.93-2.31L422 112.66a9 9 0 000-12.66l-9.95-10a9 9 0 00-12.71 0z"/>
						</svg>
					</button>
				</div>
				<span id="user-hierachy">
					<c:choose>
						<c:when test="${memberInfo.mem_authority == 109 }">관리자</c:when>
						<c:when test="${memberInfo.mem_authority == 108 }">매니저</c:when>
						<c:when test="${memberInfo.mem_authority == 103 }">일반 사용자</c:when>
						<c:when test="${memberInfo.mem_authority == 102 }">차단된 사용자</c:when>
						<c:otherwise>인증되지 않은 사용자</c:otherwise>
					</c:choose>
				</span>
			</div>
		</div>
		<div id="user-info-selector">
			<button data-group="info-menu" data-toggle="true" class="selector rect-button subtheme-button adv-hover" type="button">게시글</button>
			<button data-group="info-menu" data-toggle="false" class="selector rect-button subtheme-button adv-hover" type="button">댓글</button>
			<button data-group="info-menu" data-toggle="false" class="selector rect-button subtheme-button adv-hover" type="button">거래내역</button>
		</div>
		<div id="user-info-container">
			
		</div>
	</div>
</body>
</html>