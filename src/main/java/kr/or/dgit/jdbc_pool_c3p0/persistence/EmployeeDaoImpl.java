package kr.or.dgit.jdbc_pool_c3p0.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.jdbc_pool_c3p0.domain.Department;
import kr.or.dgit.jdbc_pool_c3p0.domain.Employee;
import kr.or.dgit.jdbc_pool_c3p0.domain.Title;
import kr.or.dgit.jdbc_pool_c3p0.jdbc.ConnectionProvider;

public class EmployeeDaoImpl implements EmployeeDao {
/*	private DataSource ds;

	public EmployeeDaoImpl() {
		ds = DataSourceProvider.getDataSource();
	}*/

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select empno, empname, title, salary, gender, hobby, dept, joinDate from Employee";
		List<Employee> list = new ArrayList<>();

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				list.add(getEmployee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Employee selectEmployeeByCode(String empNo) {
		String sql = "select empno, empname, title, salary, gender, hobby, dept, joinDate from Employee where empno = ?";
		Employee employee = null;

		try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, empNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					employee = getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getString("empNo"), rs.getString("empName"), new Title(rs.getString("title")),
				rs.getInt("salary"), rs.getBoolean("gender"), rs.getString("hobby"),
				new Department(rs.getString("dept")), rs.getDate("joinDate"));
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		int res = -1;
		try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getTitle().getCode());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setBoolean(5, emp.isGender());
			pstmt.setString(6, emp.getHobby());
			pstmt.setString(7, emp.getDept().getDeptNo());
			pstmt.setDate(8, new java.sql.Date(emp.getJoinDate().getTime()));
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteEmployee(String empNo) {
		String sql = "delete from employee where empno =?";
		int res = -1;
		try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, empNo);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql = "UPDATE employee " + "SET empname=?, title=?, salary=?, gender=?, hobby=?, dept=?, joinDate=? "
				+ "WHERE empno=?";
		int res = -1;
		try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getTitle().getCode());
			pstmt.setInt(3, emp.getSalary());
			pstmt.setBoolean(4, emp.isGender());
			pstmt.setString(5, emp.getHobby());
			pstmt.setString(6, emp.getDept().getDeptNo());
			pstmt.setDate(7, new java.sql.Date(emp.getJoinDate().getTime()));
			pstmt.setString(8, emp.getEmpNo());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
