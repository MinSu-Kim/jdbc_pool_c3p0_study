package jdbc_pool_c3p0_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc_pool_c3p0_study.dto.Title;
import jdbc_pool_c3p0_study.jdbc.ConnectionProvider;
import jdbc_pool_c3p0_study.jdbc.JdbcUtil;
import jdbc_pool_c3p0_study.jdbc.LogUtil;

public class TitleDaoImpl implements TitleDao {

	@Override
	public List<Title> selectTitleByAll() {
		LogUtil.prnLog("selectTitleByAll()");
		String sql = "select code, name from title";
		List<Title> list = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getTitle(rs));
			}
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		return list;
	}

	@Override
	public Title selectTitleByCode(Title title) {
		LogUtil.prnLog("selectTitleByCode()");
		String sql = "select code, name from title where code=?";
		Title res = null;
		try (Connection con = ConnectionProvider.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title.getCode());
			LogUtil.prnLog(pstmt);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					res = getTitle(rs);
				}
			}
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		return res;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		return new Title(rs.getString(1), rs.getString(2));
	}

	@Override
	public int insertTitle(Title title) {
		LogUtil.prnLog("insertTitle()");
		String sql = "insert into title values(?, ?)";
		int res = -1;
		try (Connection con = ConnectionProvider.getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				con.setAutoCommit(false);
				pstmt.setString(1, title.getCode());
				pstmt.setString(2, title.getName());
				LogUtil.prnLog(pstmt);
				res = pstmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				JdbcUtil.rollback(con);
			}
		} catch (SQLException e1) {
			LogUtil.prnLog(e1);
		}

		return res;
	}

	@Override
	public int deleteTitle(String code) {
		LogUtil.prnLog("deleteTitle()");
		String sql = "delete from title where code = ?";
		int res = -1;
		try (Connection con = ConnectionProvider.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, code);
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		return res;
	}

	@Override
	public int updateTitle(Title title) {
		LogUtil.prnLog("updateTitle()");
		//code, name
		String sql = "update title set name=? where code = ?";
		int res = -1;
		try (Connection con = ConnectionProvider.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title.getName());
			pstmt.setString(2, title.getCode());
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		return res;
	}

}
