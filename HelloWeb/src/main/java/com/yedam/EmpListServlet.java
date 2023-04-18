package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/empList")
public class EmpListServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		

		out.print("<table border = '1'>");
		out.print("<thead><tr><th>사원번호</th><th>FirstName</th><th>LastName</th><th>email</th><th>jobId</th><th>hiredate</th></tr></thead>");
		out.print("<tbody>");	
		//while (rs.next()) {
		for(Employee emp : list){
				//sysout("eid : " + rs.getInt("employee_id") + "fname : " + rs.getString("first_name"));
				// 사원번호, fname, lname, email. phone...
				out.print("<tr><td><a href='searchMember?emp_id=" + emp.getEmployeeId() +"'>" + emp.getEmployeeId()
				+ "</a></td><td><a href='modifyMemberServlet?first_name="+emp.getFistName()  +"'>" +emp.getFistName()
				+ "</td><td>"+emp.getLastName()
				+ "</td><td>" + emp.getEmail() 
				+ "</td><td>" + emp.getJobId() 
				+ "</td><td>" + emp.getHireDate() 
				+"</td></tr>");
			}
			out.print("</tbody>");
			out.print("</table>");
//	}
		
		//등록 : 사용자의 입력(addForm.html)
		//처리 : 서블릿(insert기능 : addMemberServlet)
		//결과 : 목록페이지출력.

//	public static void main(String[] args) {

	}
}
