<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>회원정보 수정페이지.</h3>

<form action="modifyMember.do" method="POST">
	<table class="table">
		<tr>
			<th>이메일</th>
			<td><input type="text" name="nid"
				value="${session.Id }" readonly></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="title"
				value="${noticeInfo.noticeTitle }"></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><textarea rows="3" cols="20" name="subject">${noticeInfo.noticeSubject }</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer"
				value="${noticeInfo.noticeWriter }" readonly></td>
		</tr>
		<tr>
			<th>첨부파일: ${fileType }</th>
			<td><c:if test="${noticeInfo.attachFile != null} ">
					<c:choose>
						<c:when test="${fileType == 'image' }">
							<img width="200px" src="images/${noticeInfo.attachFile }">
						</c:when>
						<c:otherwise>
							<a href="images/${noticeInfo.attachFile }">${noticeInfo.attachFile }</a>
						</c:otherwise>
					</c:choose>
				</c:if></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">저장</button>
				<button type="button">삭제</button>
			</td>
		</tr>
	</table>
</form>