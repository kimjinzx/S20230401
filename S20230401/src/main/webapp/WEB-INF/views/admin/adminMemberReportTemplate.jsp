<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/preset.jsp" %>
<table class="admin-board-table">
	<thead>
		<tr>
			
		</tr>
	</thead>
	<tbody style="border: 2.5px solid var(--subtheme);">
		<c:forEach var="member" items="${members }">
			<tr id="member-row-${member.mem_id }" class="member-data">
				<td>${member.mem_id }</td>
				<td>${member.mem_nickname }</td>
				<td>${member.mem_email }</td>
				<td>${member.mem_authority }</td>
				<td>${member.mem_regdate }</td>
				<td>0</td>
				<td>
					<input type="number" id="member-row-bandate" value="1">
					<span>일 </span>
					<button class="member-row-button-ban adv-hover" type="button">
						정지
					</button>
				</td>
				<td>
					<button class="member-row-button-delete adv-hover" type="button">
						삭제
					</button>
				</td>
			</tr>
			<tr id="member-row-${member.mem_id }-information" class="member-report">
				<!-- 회원번호, 닉네임, 이메일, 권한, 가입일, 신고 수 + 정지, 삭제 -->
				<!-- 글 번호, 게시판 이름, 제목, 작성자, 작성일, 신고 수 -->
				<!-- 슬라이드 -->
				<td colspan="8">
					<div class="member-report-list">
						
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			
		</tr>
	</tfoot>
</table>