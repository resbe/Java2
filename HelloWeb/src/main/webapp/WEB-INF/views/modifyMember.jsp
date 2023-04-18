<%@page import="com.yedam.persistence.EmpDAO"%>
<%@page import="com.yedam.domain.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>
	<% 
		Employee result =(Employee) request.getAttribute("empInfo");
%>
	<form action="modifyMember.do" method = "post">
	<table border = "1">
	<tr>
  	  <th>ID</th>							<!-- result.getEmployeeId() -->		
  	  <td><input type="text" name ="emp_id" value="<%=result.getEmployeeId() %>" readonly>
  	  </td>
  	</tr>
  	<tr>
  	  <th>FirstName</th>
  	  <td><input type="text" name="first_name" value="<%=result.getFistName() %>">
  	  </td>
  	</tr>
  	<tr>
  	  <th>LastName</th>
  	  <td><input type="text" name="last_name" value="<%=result.getLastName() %>"></td>
  	</tr>
  	<tr>
  	  <th>Email</th>
  	  <td><input type="text" name ="email" value="<%=result.getEmail() %>"></td>
  	</tr>
  	<input type ="submit" value='수정'>
  	<input type ="button" value='삭제'>
</table>
</form>

<script>
	//Document Object Model.
	
 	let btn = document.querySelector('input[type = "button"]'';)
 	console.log(btn);
 	btn.onclick = function(){
 		//alert("클릭됨.");
 		let frm = document.querySelector('form');
 		frm.action() = "deleteMember.do";
 		frm.submit(); //
 	}
</script>
<jsp:include page="footer.jsp"></jsp:include>