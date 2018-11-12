package jdbc_pool_c3p0.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_pool_c3p0.dto.Department;
import jdbc_pool_c3p0.jdbc.ConnectionProvider;
import jdbc_pool_c3p0.jdbc.LogUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public List<Department> selectDepartmentByAll() {
		LogUtil.prnLog("selectDepartmentByAll()");
		String sql = "select deptno, deptname, floor from department";
		List<Department> list = new ArrayList<>();

		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		return list;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getString(1), rs.getString(2), rs.getInt(3));
	}

	@Override
	public Department selectDepartmentByCode(String deptCode) {
		LogUtil.prnLog("selectDepartmentByNo()");
		String sql = "select deptno, deptname, floor from department where deptno = ?";
		Department dept = null;

		try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, deptCode);
			LogUtil.prnLog(pstmt);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					dept = getDepartment(rs);
				}
			}
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}

		return dept;
	}

	@Override
	public int insertDepartment(Department department) {
		LogUtil.prnLog("insertDepartment()");
		String sql = "insert into Department values(?, ?, ?)";
		int res = -1;
		try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		return res;
	}

	@Override
	public int deleteDepartment(String deptCode) {
		LogUtil.prnLog("deleteDepartment()");
		String sql = "delete from department where deptno = ?";
		int res = -1;
		try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, deptCode);
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		return res;
	}

	@Override
	public int updateDepartment(Department department) {
		LogUtil.prnLog("updateDepartment()");
		String sql = "update department set deptname=?, floor=? where deptno = ?";
		int res = -1;
		try (Connection connection = ConnectionProvider.getConnection(); 
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getFloor());
			pstmt.setString(3, department.getDeptNo());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		return res;
	}

}
