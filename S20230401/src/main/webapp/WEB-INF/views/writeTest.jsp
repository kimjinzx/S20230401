<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 ▒ ShareGo</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/initializer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor/ckeditor.js"></script>
<link href="https://unpkg.com/sanitize.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/preference.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/presets.css">
<script type="text/javascript">
	$(() => {
		CKEDITOR.replace('article-editor', {
			uploadUrl : '${pageContext.request.contextPath}/uploads/article',
			filebrowserUploadUrl : '${pageContext.request.contextPath}/uploads/article',
			removePlugins : [ 'Title' ],
			language : "ko"
		});
	});
</script>
<style type="text/css">
	html {
		min-height: 100%;
		
	}
	body {
		
	}
	#article-editor {
		width: 100%;
		height: 400px;
	}
	.ck-editor__editable {
		height: 400px;
	}
	.ck-content {
		font-size: 12px;
	}
</style>
</head>
<body>
	<div class="container" style="height: 100%;">
		<form action="">
			<textarea id="article-editor" name="article-editor">
				
			</textarea>
		</form>
	</div>
</body>
</html>