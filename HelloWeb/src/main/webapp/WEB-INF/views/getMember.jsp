<%@page import="com.yedam.domain.Employee"%>
<%@page import="com.yedam.persistence.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getMember.jsp</title>
</head>
<body>
  <% 
  	Employee emp =(Employee) request.getAttribute("empInfo");
/*	EmpDAO dao = new EmpDAO();
	Employee result = dao.getEmp(Integer.parseInt(empId));	
*/
	%>	
  
  <table class = "table">
  	<tr>
  	  <th>사원번호</th>
  	  <td><%=emp.getEmployeeId() %>
  	  </td>
  	</tr>
  	<tr>
  	  <th>이름</th>
  	  <td><%=emp.getFistName() %></td>
  	</tr>
  	<tr>
  	  <th>이메일</th>
  	  <td><%=emp.getEmail() %></td>
  	</tr>
  </table>
</body>
</html>