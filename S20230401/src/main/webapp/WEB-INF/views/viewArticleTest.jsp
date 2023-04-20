<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/initializer.js"></script>
<link href="https://unpkg.com/sanitize.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/preference.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/presets.css">
<!-- <script type="text/javascript">
	$(() => {
		$('img').load(() => {
			let reader = new FileReader();
			reader.readAsDataURL(this.src);
			let img = this;
			reader.onload = function() {
				img.attr('src', reader.result);
			};
		});
	});
</script> -->
</head>
<body>
	<p>${article.art_title }</p>
	<p>${article.mem_id }</p>
	<p>${article.art_regdate }</p>
	<p>${article.art_tag1 }</p>
	<p>${article.art_tag2 }</p>
	<p>${article.art_tag3 }</p>
	<p>${article.art_tag4 }</p>
	<p>${article.art_tag5 }</p>
	<p>${article.art_content }</p>
</body>
</html>