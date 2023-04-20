<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table border="1">
  	<thead><tr><th>글번호</th><th>제목</th><th>작성자</th></tr></thead>
<!-- 달러대괄호는 주석을 해도 내부의 값이 발동됨. -->
  <c:forEach var="notice" items="${list }"> 
 	 <tr>
  		<td>${notice.noticeId }</td>
  		<td>${notice.noticeTitle }</td>
  		<td>${notice.noticeWriter }</td>
 	 </tr>
  </c:forEach>
  </table>
</body>
</html>