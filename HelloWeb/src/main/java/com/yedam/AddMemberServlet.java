package com.yedam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/addMemberServlet")
public class AddMemberServlet extends HttpServlet {
	 
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
	
		String fname = req.getParameter("fname");//input name="fname"
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String job = req.getParameter("job");
		String hdate =  req.getParameter("hdate");
		
		Employee emp = new Employee();
		emp.setFistName(fname);
		emp.setLastName(lname);
		emp.setEmail(email);
		emp.setJobId(job);
		emp.setHireDate(hdate);
		
		EmpDAO dao = new EmpDAO();
		boolean result = dao.insertEmployee(emp);
	
		if(result) {
			resp.sendRedirect("empList");
		}else {
			resp.sendRedirect("employee/addForm.html");
		}
	}
}
