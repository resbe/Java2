<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>

<%
String lname = (String) session.getAttribute("sesInfo");
%>
<form action="addMember.do" method = "post">
	firstName : <input name ="fname"><br> <!-- parameter의 이름 : -->
	lastName : <input name ="lname"><br>
	email : <input name = "email"><br>
	job: <input name ="job"><br>
	hire <input name ="hdate"><br>
	<input type ='submit' value='저장'>
	</form>
<jsp:include page="footer.jsp"></jsp:include>
	