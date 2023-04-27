<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지 ▒ ShareGo</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/initializer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/layout.js"></script>
<link href="https://unpkg.com/sanitize.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/preference.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/presets.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/layout.css">
<script type="text/javascript">
	$(() => {
		$('.board-toggle').click(e => {
			let parent = $(e.target).closest('.board-summary');
			let children = parent.find('.board-summary-part');
			children.toggle();
		});
	});
</script>
</head>
<body>
	<header>
		<div id="usernav">
			<a href="">이용 약관</a>
			<a href="">개인정보 취급 방침</a>
		</div>
		<div id="topbar">
			<div id="logo-div" class="full-height" style="display: flex; justify-content: flex-start; align-items: center; padding: 0 10px;">
				<a id="logo" class="full-height" href="/">
					<div style="width: auto; display: flex; flex-direction: column; justify-content: center; align-items: flex-end;">
						<img src="${pageContext.request.contextPath}/image/ShareGo_Img.png" style="height: 30px;">
						<span style="font-size: 24px; font-weight: 900; margin: -5px 0 0 0;">ShareGo</span>
					</div>
				</a>
			</div>
			<div id="topmenu">
				<a class="adv-hover menuitem" href="">함께해요</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="">같이사요</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="">나눔해요</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="">커뮤니티</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="">정보공유</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="">고객센터</a>
				<div id="dropdown">
					<div style="width: 200px;"></div>
					<div class="submenu-items">
						<div class="submenu">
							<a class="submenuitem adv-hover" href="">밥 / 카페</a>
							<a class="submenuitem adv-hover" href="">스포츠 / 운동</a>
							<a class="submenuitem adv-hover" href="">쇼핑</a>
							<a class="submenuitem adv-hover" href="">문화생활</a>
							<a class="submenuitem adv-hover" href="">취미생활</a>
							<a class="submenuitem adv-hover" href="">기타</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="">식료품</a>
							<a class="submenuitem adv-hover" href="">의류 / 잡화</a>
							<a class="submenuitem adv-hover" href="">생활용품</a>
							<a class="submenuitem adv-hover" href="">해외배송</a>
							<a class="submenuitem adv-hover" href="">기타</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="">식품</a>
							<a class="submenuitem adv-hover" href="">패션 / 잡화</a>
							<a class="submenuitem adv-hover" href="">가전 / 가구</a>
							<a class="submenuitem adv-hover" href="">기타</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="">일상수다</a>
							<a class="submenuitem adv-hover" href="">자랑하기</a>
							<a class="submenuitem adv-hover" href="">홍보하기</a>
							<a class="submenuitem adv-hover" href="">질문 / 요청</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="">동네정보</a>
							<a class="submenuitem adv-hover" href="">구매정보</a>
							<a class="submenuitem adv-hover" href="">신규점포</a>
							<a class="submenuitem adv-hover" href="">지역활동</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="">공지</a>
							<a class="submenuitem adv-hover" href="">Q&A</a>
							<a class="submenuitem adv-hover" href="">이벤트</a>
							<a class="submenuitem adv-hover" href="">문의 / 건의</a>
						</div>
					</div>
					<div style="width: 200px;"></div>
				</div>
			</div>
			<div id="top-right">
				<!-- <button id="viewMode">
					<div id="viewModeButton"></div>
				</button> -->
				<div></div>
				<div class="popup-group">
					<c:choose>
						<c:when test="${1 == 1 }">
							<button id="login" class="togglePopup init-button">
								<svg width="40" height="40" viewBox="0 0 512 512" style="margin: 5px;">
									<path d="M258.9 48C141.92 46.42 46.42 141.92 48 258.9c1.56 112.19 92.91 203.54 205.1 205.1 117 1.6 212.48-93.9 210.88-210.88C462.44 140.91 371.09 49.56 258.9 48zm126.42 327.25a4 4 0 01-6.14-.32 124.27 124.27 0 00-32.35-29.59C321.37 329 289.11 320 256 320s-65.37 9-90.83 25.34a124.24 124.24 0 00-32.35 29.58 4 4 0 01-6.14.32A175.32 175.32 0 0180 259c-1.63-97.31 78.22-178.76 175.57-179S432 158.81 432 256a175.32 175.32 0 01-46.68 119.25z"/>
									<path d="M256 144c-19.72 0-37.55 7.39-50.22 20.82s-19 32-17.57 51.93C191.11 256 221.52 288 256 288s64.83-32 67.79-71.24c1.48-19.74-4.8-38.14-17.68-51.82C293.39 151.44 275.59 144 256 144z"/>
								</svg>
							</button>
							<div id="login-popup" class="popup-window">
								<button id="login-button" class="subtheme-button adv-hover" click="location.href = '/login';">
									로그인
								</button>
							</div>
						</c:when>
						<c:otherwise>
							<button id="userInfo" class="togglePopup adv-hover">
								
							</button>
							<div id="userInfo-popup" class="popup-window">
								
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</header>
	<aside id="leftside">
		
	</aside>
	<main>
		
		<button id="scrollToTop" class="adv-hover">
			<svg style="fill: var(--subtheme); stroke: var(--subtheme); stroke-width: 2px; stroke-linecap: round; stroke-linejoin: round;" width="20" height="10" viewBox="0 0 32 16">
				<path d="M 15 1 L 1 15 31 15 Z"/>
			</svg>
		</button>
		<button id="scrollToBottom" class="adv-hover">
			<svg style="fill: var(--subtheme); stroke: var(--subtheme); stroke-width: 2px; stroke-linecap: round; stroke-linejoin: round;" width="20" height="10" viewBox="0 0 32 16">
				<path d="M 15 15 L 1 1 31 1 Z"/>
			</svg>
		</button>
	</main>
	<aside id="rightside">
		
	</aside>
	<footer>
		<div id="footer-info" class="full-height">
			<span style="font-size: 40px; font-weight: 900; margin: 0 10px; text-align: left; opacity: 0.5;">ShareGo</span>
			<div style="font-size: 12px; flex-grow: 1;">
				<p>서울특별시 마포구 신촌로 176</p>
				<p>02-313-1711</p>
				<p>choongang@naver.com</p>
			</div>
			<div>
				<a href="" style="margin: 10px;">
					<svg style="fill: var(--theme-font);" width="30" height="30" viewBox="0 0 512 512">
						<path d="M480 257.35c0-123.7-100.3-224-224-224s-224 100.3-224 224c0 111.8 81.9 204.47 189 221.29V322.12h-56.89v-64.77H221V208c0-56.13 33.45-87.16 84.61-87.16 24.51 0 50.15 4.38 50.15 4.38v55.13H327.5c-27.81 0-36.51 17.26-36.51 35v42h62.12l-9.92 64.77H291v156.54c107.1-16.81 189-109.48 189-221.31z" fill-rule="evenodd"/>
					</svg>
				</a>
				<a href="" style="margin: 10px;">
					<svg style="stroke: var(--theme-font);" width="30" height="30" viewBox="0 0 512 512">
						<path d="M349.33 69.33a93.62 93.62 0 0193.34 93.34v186.66a93.62 93.62 0 01-93.34 93.34H162.67a93.62 93.62 0 01-93.34-93.34V162.67a93.62 93.62 0 0193.34-93.34h186.66m0-37.33H162.67C90.8 32 32 90.8 32 162.67v186.66C32 421.2 90.8 480 162.67 480h186.66C421.2 480 480 421.2 480 349.33V162.67C480 90.8 421.2 32 349.33 32z"/>
						<path d="M377.33 162.67a28 28 0 1128-28 27.94 27.94 0 01-28 28zM256 181.33A74.67 74.67 0 11181.33 256 74.75 74.75 0 01256 181.33m0-37.33a112 112 0 10112 112 112 112 0 00-112-112z"/>
					</svg>
				</a>
				<a href="" style="margin: 10px;">
					<svg style="fill: var(--theme-font);" width="30" height="30" viewBox="0 0 512 512">
						<path d="M508.64 148.79c0-45-33.1-81.2-74-81.2C379.24 65 322.74 64 265 64h-18c-57.6 0-114.2 1-169.6 3.6C36.6 67.6 3.5 104 3.5 149 1 184.59-.06 220.19 0 255.79q-.15 53.4 3.4 106.9c0 45 33.1 81.5 73.9 81.5 58.2 2.7 117.9 3.9 178.6 3.8q91.2.3 178.6-3.8c40.9 0 74-36.5 74-81.5 2.4-35.7 3.5-71.3 3.4-107q.34-53.4-3.26-106.9zM207 353.89v-196.5l145 98.2z"/>
					</svg>
				</a>
				<a href="" style="margin: 10px;">
					<svg style="fill: var(--theme-font);" width="30" height="30" viewBox="0 0 512 512">
						<path d="M496 109.5a201.8 201.8 0 01-56.55 15.3 97.51 97.51 0 0043.33-53.6 197.74 197.74 0 01-62.56 23.5A99.14 99.14 0 00348.31 64c-54.42 0-98.46 43.4-98.46 96.9a93.21 93.21 0 002.54 22.1 280.7 280.7 0 01-203-101.3A95.69 95.69 0 0036 130.4c0 33.6 17.53 63.3 44 80.7A97.5 97.5 0 0135.22 199v1.2c0 47 34 86.1 79 95a100.76 100.76 0 01-25.94 3.4 94.38 94.38 0 01-18.51-1.8c12.51 38.5 48.92 66.5 92.05 67.3A199.59 199.59 0 0139.5 405.6a203 203 0 01-23.5-1.4A278.68 278.68 0 00166.74 448c181.36 0 280.44-147.7 280.44-275.8 0-4.2-.11-8.4-.31-12.5A198.48 198.48 0 00496 109.5z"/>
					</svg>
				</a>
			</div>
		</div>
	</footer>
</body>
</html>