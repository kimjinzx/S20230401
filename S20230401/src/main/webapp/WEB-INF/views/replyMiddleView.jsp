<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="preset.jsp" %>
<c:forEach var="reply" items="${replies }">
	<div class="reply-box" style="display: flex; justify-content: flex-start; align-items: stretch;">
		<c:if test="${reply.rep_id != reply.rep_parent }">
			<div class="reply-indent" style="width: 20px;">
				
			</div>
		</c:if>
		<div class="reply-indented" style="display: flex; flex-direction: column; justify-content: flex-start; align-items: stretch; border: 1px solid var(--subtheme); border-radius: 5px; margin: 5px 0; flex-grow: 1;">
			<div class="reply-box-title" style="display: flex; justify-content: space-between; align-items: center; padding: 5px 10px; border-bottom: 1px solid var(--subtheme); background-color: rgba(var(--subtheme-rgb), 0.125);">
				<span style="font-size: 16px; font-weight: bold;">${reply.mem_nickname }</span>
				<span style="font-size: 14px; font-weight: bold;">
					<fmt:formatDate value="${reply.rep_regdate }" pattern="yyyy-MM-dd hh:mm:ss"/>
				</span>
			</div>
			<span style="margin: 10px;">${reply.rep_content }</span>
		</div>
	</div>
</c:forEach>