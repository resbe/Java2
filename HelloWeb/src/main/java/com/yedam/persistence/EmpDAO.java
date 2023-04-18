package com.yedam.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.domain.Employee;

public class EmpDAO {
	Connection conn = DAO.getConnect();
	PreparedStatement psmt;
	ResultSet rs;

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// 단건조회.
	public Employee getEmp(int empId) {
		// 사원번호의 조회결과로 값이 있으면 Employee 반환.
		// 값이 없으면 null이 반환.
		Employee emp = null;
		conn = DAO.getConnect();
		String sql = "select * from employees where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empId);

			rs = psmt.executeQuery();
			if (rs.next()) {
				emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setLastName(rs.getString("last_name"));
				emp.setFistName(rs.getString("first_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return emp;
	}

	// 사원 삭제
	public boolean deleteEmployee(int empId) {
		conn = DAO.getConnect();
		String sql = "delete employees where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empId);

			int r = psmt.executeUpdate();

			System.out.println("처리된 건수 : " + r);
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// 사원등록
	public boolean insertEmployee(Employee emp) {
		conn = DAO.getConnect();

		String sql = "insert into employees (employee_id, last_name,email, hire_date, job_id, first_name)"
				+ " values(employees_seq.nextval,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getLastName());
			psmt.setString(2, emp.getEmail());
			psmt.setString(3, emp.getHireDate());
			psmt.setString(4, emp.getJobId());
			psmt.setString(5, emp.getFistName());

			int r = psmt.executeUpdate();

			System.out.println("처리된 건수 : " + r);
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// 사원목록.
	public List<Employee> getEmpList() {
		List<Employee> list = new ArrayList<>();
		String sql = "select * from employees order by 1 desc";
		conn = DAO.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setLastName(rs.getString("last_name"));
				;
				emp.setFistName(rs.getString("first_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));

				list.add(emp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	// 사원 수정
	public boolean updateMember(Employee emp) {
		conn = DAO.getConnect();
		String sql = "update employees set first_name=?,last_name=?,email=? where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getFistName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setInt(4, emp.getEmployeeId());

			int r = psmt.executeUpdate();

			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;

	}
}
