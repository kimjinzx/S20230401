<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 ▒ ShareGo</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/initializer.js"></script>
<link href="https://unpkg.com/sanitize.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/preference.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/presets.css">
<style type="text/css">
	html {
		width: 100%;
		height: 100%;
	}
	body {
		width: 100%;
		height: 100%;
		background-color: var(--theme);
	}
	div.container {
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	div#loginBox {
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
	}
	.input-box > label {
		position: absolute;
		top: 50%;
		left: 5px;
		transform: translateY(-50%);
		color: var(--theme-font);
		font-size: 1em;
		font-weight: bold;
		pointer-events: none;
		transition: top .5s;
	}
	.input-box > input {
		width: 100%;
		height: 50px;
		background-color: transparent;
		border: none;
		outline: none;
		font-size: 1em;
		padding: 0 5px;
	}
	.input-box > input:focus ~ label, .input-box > input:valid ~ label {
		top: -5px;
	}
	span#loginErrorMessage {
		position: absolute;
		width: 100%;
		top: 55px;
		color: var(--warning);
		font-size: 14px;
		padding: 2.5px 5px;
	}
	button#login-button {
		width: 100%;
		height: 40px;
		line-height: 40px;
		text-align: center;
		font-size: 20px;
		font-weight: bold;
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
	div#login-forgot {
		font-size: 12px;
		margin-top: 15px;
	}
	div#login-forgot > a {
		color: var(--subtheme);
		font-weight: bold;
	}
</style>
</head>
<body>
	<div class="container">
		<div id="loginBox">
			<form action="/loginProc" method="post">
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
						<input type="text" id="username" name="username" required>
						<label for="username">아이디</label>
					</div>
					<div class="input-box">
						<input type="password" id="password" name="password" required>
						<label for="password">비밀번호</label>
						<br>
						<span id="loginErrorMessage">${message }</span>
					</div>
					<button id="login-button" class="subtheme-button adv-hover" type="submit">로그인</button>
					<button id="join-button" class="theme-button adv-hover" type="button" onclick="location.href = '${pageContext.request.contextPath}/join';">회원가입</button>
					<div id="login-forgot">
						로그인 정보를 잊어버렸나요? <a href="">아이디 찾기</a> / <a href="">비밀번호 재설정</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>