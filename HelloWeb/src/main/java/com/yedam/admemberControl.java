package com.yedam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class admemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
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
		try{
		if(dao.insertEmployee(emp)) {
			resp.sendRedirect("main.do");
		}else {
			resp.sendRedirect("addMemberForm.do");
		}
		}catch(IOException e){
			e.printStackTrace();	
		}
	
		
}
}
