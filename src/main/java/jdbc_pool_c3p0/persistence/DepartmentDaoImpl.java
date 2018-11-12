package jdbc_pool_c3p0.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import jdbc_pool_c3p0.domain.Department;
import jdbc_pool_c3p0.jdbc.ConnectionProvider;
import jdbc_pool_c3p0.jdbc.DataSourceProvider;

public class DepartmentDaoImpl implements DepartmentDao {
	private DataSource ds;

	public DepartmentDaoImpl() {
		ds = DataSourceProvider.getDataSource();
	}

	@Override
	public List<Department> selectDepartmentByAll() {
		String sql = "select deptno, deptname, floor from department";
		List<Department> list = new ArrayList<>();

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getString(1), rs.getString(2), rs.getInt(3));
	}

	@Override
	public Department selectDepartmentByCode(String deptNo) {
		String sql = "select deptno, deptname, floor from department where deptno = ?";
		Department dept = null;

		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, deptNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					dept = getDepartment(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dept;
	}

	@Override
	public int insertDepartment(Department department) {
		String sql = "insert into Department values(?, ?, ?)";
		int res = -1;
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteDepartment(String deptNo) {
		String sql = "delete from department where deptno = ?";
		int res = -1;
		try (Connection connection = ds.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, deptNo);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateDepartment(Department department) {
		String sql = "update department set deptname=?, floor=? where deptno = ?";
		int res = -1;
		try (Connection connection = ds.getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getFloor());
			pstmt.setString(3, department.getDeptNo());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
