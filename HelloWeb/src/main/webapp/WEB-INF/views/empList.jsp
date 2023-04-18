<%@page import="com.yedam.domain.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.yedam.persistence.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empList.jsp</title>
</head>
<body>
 <% 
	/* EmpDAO dao = new EmpDAO();
	List<Employee> list = dao.getEmpList();*/
	List<Employee> list = (List<Employee>) request.getAttribute("listInfo");// 반환유형 : Object
 %>
 <table class = "table">
  <thead>
  	<tr><th>사원번호</th><th>이름</th><th>이메일</th></tr>
  </thead>
  <tbody>
  <% for(Employee emp : list) { %>
  	<tr><td><a href = "getMember.do?emp_id=<%=emp.getEmployeeId() %>"> <%=emp.getEmployeeId() %> </a></td>
  		<td><a href = "modifyMember.do?emp_id=<%=emp.getEmployeeId() %>"><%=emp.getFistName() %></a></td>
  		<td><%=emp.getEmail() %></td></tr>
  <% } %>
 </tbody>
 </table>
</body>
</html>