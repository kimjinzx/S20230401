<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="preset.jsp" %>
<html>
<head></head>
<body>
	<c:forEach var="reply" items="${replies }">
		<div>
			<p>${reply.mem_id }</p>
			<p>${reply.rep_regdate }</p>
			<p>${reply.rep_content }</p>
		</div>
	</c:forEach>
</body>
</html>