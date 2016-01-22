package io.egen.dao;

import io.egen.exception.AppException;
import io.egen.model.Employee;
import io.egen.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

public class EmployeeDAO {

	public List<Employee> findAll() throws AppException {
		List<Employee> employees = new ArrayList<Employee>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM employee");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getString(1));
				emp.setName(rs.getString(2));
				emp.setDateofjoin(rs.getString(3));
				emp.setSalary(rs.getInt(4));
				
				employees.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return employees;
	}

	public Employee findOne(int id) throws AppException, NotFoundException {
		Employee emp = null;
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM employee WHERE ID=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				emp = new Employee();
				emp.setId(rs.getString(1));
				emp.setName(rs.getString(2));
				emp.setDateofjoin(rs.getString(3));
				emp.setSalary(rs.getInt(4));
			}
			else {
				throw new NotFoundException("Employee with this id not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return emp;
	}

	public Employee create (Employee emp) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("INSERT INTO employee (id,name,dateofjoin,salary) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,emp.getId());
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getDateofjoin());
			ps.setInt(4, emp.getSalary());
			
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return emp;
	}

	public Employee update(int id, Employee emp) throws AppException {
		return null;
	}

	public void delete(int id) throws AppException {
		
	}

	
}
