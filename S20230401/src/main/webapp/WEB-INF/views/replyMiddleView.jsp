<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="preset.jsp" %>
<c:forEach var="reply" items="${replies }">
	<div class="reply-box" style="display: flex; justify-content: flex-start; align-items: stretch;">
		<c:if test="${reply.rep_id != reply.rep_parent }">
			<div class="reply-indent" style="width: 30px; display: flex; justify-content: flex-end; align-items: stretch;">
				<div style="border-left: 2px solid var(--theme-font); width: 50%; opacity: 0.5;"></div>
			</div>
		</c:if>
		<!-- <div class="reply-indented" style="display: flex; flex-direction: column; justify-content: flex-start; align-items: stretch; border: 1px solid var(--subtheme); border-radius: 5px; margin-top: 10px; flex-grow: 1;"> -->
		<div class="reply-indented" style="display: flex; flex-direction: column; justify-content: flex-start; align-items: stretch; border: 0; border-radius: 5px; margin-top: 10px; flex-grow: 1; background-color: ${reply.rep_id == reply.rep_parent ? 'rgba(var(--subtheme-rgb), 0.25)' : 'rgba(var(--subtheme-rgb), 0.125)'};">
			<div class="reply-box-title" style="display: flex; justify-content: space-between; align-items: center; padding: 5px 10px;">
				<div style="display: flex; justify-content: flex-start; align-items: center;">
					<div style="width: 40px; height: 40px; border-radius: 50%; overflow: hidden; margin-right: 10px; box-shadow: 0 2.5px 2.5px var(--theme-font); display: flex; justify-content: center; align-items: center; background-color: white;">
						<img src="${pageContext.request.contextPath }/uploads/profile/${reply.mem_image }" onerror="this.onerror=null; this.src='${pageContext.request.contextPath }/image/abstract-user.svg';" style="width: 40px; height: 40px; object-fit: cover;">
					</div>
					<div style="display: flex; flex-direction: column; justify-content: center; align-items: flex-start;">
						<span style="font-size: 18px; font-weight: bold;">${reply.mem_nickname }</span>
						<c:set var="date"><fmt:formatDate value="${reply.rep_regdate }" pattern="MM.dd"/></c:set>
						<c:set var="time"><fmt:formatDate value="${reply.rep_regdate }" pattern="hh:mm"/></c:set>
						<span style="font-size: 14px; font-weight: bold;">${date } [${time }]</span>
					</div>
				</div>
				<div>
					
				</div>
			</div>
			<span style="margin: 10px;">${reply.rep_content }</span>
		</div>
	</div>
</c:forEach>