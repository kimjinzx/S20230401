<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 ▒ ShareGo</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/initializer.js"></script>
<link href="https://unpkg.com/sanitize.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/preference.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/presets.css">
<script type="text/javascript">
	const readImage = input => {
		if (input.files && input.files[0]) {
			const reader = new FileReader();
			reader.onload = e => {
				const img = document.getElementById('user-profile');
				img.src = e.target.result;
			};
			reader.readAsDataURL(input.files[0]);
		}
	};
	$(() => {
		$('#image-file').change(e => {
			if (e.target.files[0].size / 1024 / 1024 > 5) return;
			readImage(e.target);
		});
	});
</script>
<style type="text/css">
	html {
		width: 100%;
	}
	body {
		width: 100%;
		padding: 50px 0;
		background-color: var(--theme);
	}
	div.container {
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	div#joinBox {
		padding: 50px 25px;
		border: 5px solid var(--subtheme);
		border-radius: 10px;
		background-color: var(--theme);
	}
	.input-box {
		position: relative;
		margin: 30px auto;
		width: 100%;
		border-bottom: 2px solid var(--subtheme);
		
		display: flex;
		justify-content: flex-start;
		align-items: center;
	}
	.input-box > label {
		color: var(--theme-font);
		font-size: 1em;
		font-weight: bold;
		pointer-events: none;
	}
	.input-box input {
		flex-grow: 1;
		height: 50px;
		background-color: transparent;
		border: none;
		outline: none;
		font-size: 1em;
		padding: 0 5px;
	}
	span.joinErrorMessage {
		position: absolute;
		width: 100%;
		top: 55px;
		color: var(--warning);
		font-size: 14px;
		padding: 2.5px 5px;
	}
	button#join-button {
		width: 100%;
		height: 40px;
		line-height: 36px;
		text-align: center;
		font-size: 20px;
		font-weight: bold;
	}
	button.theme-button, button.subtheme-button {
		outline: none;
		border-radius: 5px;
		cursor: pointer;
		margin-top: 15px;
	}
	button.theme-button {
		border: 2px solid var(--subtheme);
		background-color: var(--theme);
		color: var(--theme-font);
	}
	button.subtheme-button {
		border: none;
		background-color: var(--subtheme);
		color: var(--subtheme-font);
	}
	
	div.popup-group {
		position: relative;
		height: 32px;
		margin-bottom: 10px;
		display: flex;
		justify-content: flex-start;
		align-items: center;
	}
	div.popup-group > button.togglePopup {
		width: 100px;
		height: 32px;
		overflow: hidden;
		margin: 0;
		margin-left: 10px;
	}
	div.popup-group > button.togglePopup > * {
		pointer-events: none;
	}
	div.popup-group > div.popup-window {
		position: absolute;
		border-radius: 2.5px;
		background-color: var(--theme);
		border: 2px solid #CCCCCC;
		top: 24px;
		left: 0px;
	}
</style>
</head>
<body>
	<div class="container">
		<div id="joinBox">
			<form action="/joinProc" method="post" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
				<input type="hidden" id="redirectLink" name="redirectLink" value="${redirect }">
				<div class="form-group">
					<div id="logo-div" class="full-height" style="display: flex; justify-content: center; align-items: center; padding: 0 10px;">
						<a id="logo" class="full-height" href="${pageContext.request.contextPath}/">
							<div style="width: auto; display: flex; flex-direction: column; justify-content: center; align-items: flex-end;">
								<img src="${pageContext.request.contextPath}/image/ShareGo_Img.png" style="height: 30px;">
								<span style="font-size: 24px; font-weight: 900; margin: -5px 0 0 0;">ShareGo</span>
							</div>
						</a>
					</div>
					<div class="input-box">
						<label for="username">아이디</label>
						<input type="text" id="username" name="username" required>
						<button type="button" class="theme-button adv-hover" style="margin: 0; font-size: 16px; font-weight: bold; padding: 2.5px 5px;">중복 확인</button>
						<br>
						<span class="joinErrorMessage"></span>
					</div>
					<div class="input-box">
						<label for="password">비밀번호</label>
						<input type="password" id="password" name="password" required>
						<br>
						<span class="joinErrorMessage"></span>
					</div>
					<div class="input-box">
						<label for="passwordConfirm">비밀번호 확인</label>
						<input type="password" id="passwordConfirm" name="passwordConfirm" required>
						<br>
						<span class="joinErrorMessage"></span>
					</div>
					<div class="input-box">
						<label for="nickname">닉네임</label>
						<input type="text" id="nickname" name="nickname" required>
						<br>
						<span class="joinErrorMessage"></span>
					</div>
					<div class="input-box">
						<label for="email">이메일</label>
						<input type="email" id="email" name="email" required>
						<br>
						<span class="joinErrorMessage"></span>
					</div>
					<div class="input-box">
						<label for="tel1">전화번호</label>
						<input style="flex-grow: 1; width: 100px; text-align: center;" type="text" id="tel1" name="tel1" pattern="^[0-9]{2,3}" maxlength="3" required>
						-
						<input style="flex-grow: 1; width: 100px; text-align: center;" type="text" id="tel2" name="tel2" pattern="^[0-9]{3,4}" maxlength="4" required>
						-
						<input style="flex-grow: 1; width: 100px; text-align: center;" type="text" id="tel3" name="tel3" pattern="^[0-9]{4}" maxlength="4" required>
						<br>
						<span class="joinErrorMessage"></span>
					</div>
					<div class="input-box">
						<label for="year">생년월일</label>
						<select style="height: 50px; background-color: transparent; text-align: center; margin-left: 10px; outline: none; border: none; font-size: 16px; flex-grow: 1;" id="year" name="year" required>
							<c:set var="yearMax">
								<fmt:formatDate value="${now }" pattern="yyyy"/>
							</c:set>
							<c:forEach var="yyyy" begin="1901" end="${yearMax }">
								<option style="background-color: var(--theme); text-align: center; font-size: 16px;" value="${yearMax + 1901 - yyyy }">${yearMax + 1901 - yyyy }</option>
							</c:forEach>
						</select>
						<select style="height: 50px; background-color: transparent; text-align: center; margin-left: 10px; outline: none; border: none; font-size: 16px; flex-grow: 1;" id="month" name="month" required>
							<c:forEach var="mm" begin="1" end="12">
								<option style="background-color: var(--theme); text-align: center; font-size: 16px;" value="${mm }">${mm }</option>
							</c:forEach>
						</select>
						<select style="height: 50px; background-color: transparent; text-align: center; margin-left: 10px; outline: none; border: none; font-size: 16px; flex-grow: 1;" id="day" name="day" required>
							<c:forEach var="dd" begin="1" end="31">
								<option style="background-color: var(--theme); text-align: center; font-size: 16px;" value="${dd }">${dd }</option>
							</c:forEach>
						</select>
					</div>
					<div class="input-box">
						<label>성별</label>
						<div style="margin-left: 10px; flex-grow: 1; display: flex; justify-content: space-evenly; align-items: center;">
							<div style="height: 50px; display: flex; justify-content: center; align-items: center;">
								<input style="width: 16px; height: 16px;" type="radio" id="gender-male" name="gender" value="MALE" checked>
								<label style="font-size: 20px; margin-left: 5px" for="gender-male">남</label>
							</div>
							<div style="height: 50px; display: flex; justify-content: center; align-items: center;">
								<input style="width: 16px; height: 16px;" type="radio" id="gender-female" name="gender" value="FEMALE" checked>
								<label style="font-size: 20px; margin-left: 5px" for="gender-female">여</label>
							</div>
						</div>
					</div>
					<div class="input-box">
						<label for="user-profile-container">프로필 사진</label>
						<div id="user-profile-container" style="width: 75px; height: 75px; border-radius: 50%; overflow: hidden; box-shadow: 0 2.5px 2.5px var(--theme-font); margin: 5px 10px; background-color: white;">
							<img id="user-profile" style="width: 75px; height: 75px; object-fit: cover;" src="${pageContext.request.contextPath }/image/abstract-user.svg" onerror="this.onerror = null; this.src = '${pageContext.request.contextPath }/image/abstract-user.svg';">
						</div>
						<div style="flex-grow: 1; flex-direction: column; justify-content: space-evenly; align-items: flex-start;">
							<input type="file" id="image-file" name="image-file" style="height: auto; display: none;">
							<span style="opacity: 0.5; font-size: 12px;">5 MB 이하의 파일만 업로드 할 수 있습니다</span>
							<div style="display: flex; justify-content: flex-start; align-items: stretch;">
								<button type="button" style="font-size: 14px;" class="subtheme-button" onclick="document.getElementById('image-file').click();">업로드</button>
								<button type="button" style="margin-left: 10px; font-size: 14px;" class="theme-button" onclick="$('#image-file').val(''); $('#user-profile').attr('src', '');">초기화</button>
							</div>
						</div>
					</div>
					<div class="input-box">
						<label for="region-box">관심지역</label>
						<div id="region-box" style="margin-left: 10px;">
							<div class="popup-group">
								<span style="font-size: 14px; font-weight: bold;">1순위 </span>
								<button type="button" id="region1" class="togglePopup theme-button"></button>
								<div id="region1-popup" class="popup-window">
									
								</div>
							</div>
							<div class="popup-group">
								<span style="font-size: 14px; font-weight: bold;">2순위 </span>
								<button type="button" id="region2" class="togglePopup theme-button"></button>
								<div id="region2-popup" class="popup-window">
									
								</div>
							</div>
						</div>
					</div>
					<button id="join-button" class="subtheme-button adv-hover" type="submit">회원가입</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>