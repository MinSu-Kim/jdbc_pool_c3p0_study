package jdbc_pool_c3p0.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import jdbc_pool_c3p0.domain.Title;
import jdbc_pool_c3p0.jdbc.DataSourceProvider;
import jdbc_pool_c3p0.jdbc.JdbcUtil;

public class TitleDaoImpl implements TitleDao {
	private DataSource ds;

	public TitleDaoImpl() {
		ds = DataSourceProvider.getDataSource();
	}

	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select code, name from title";
		List<Title> list = new ArrayList<>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				list.add(getTitle(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Title selectTitleByCode(Title title) {
		String sql = "select code, name from title where code=?";
		Title res = null;
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title.getCode());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					res = getTitle(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		return new Title(rs.getString(1), rs.getString(2));
	}

	@Override
	public int insertTitle(Title title) {
		String sql = "insert into title values(?, ?)";
		int res = -1;
		try (Connection con = ds.getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				con.setAutoCommit(false);
				pstmt.setString(1, title.getCode());
				pstmt.setString(2, title.getName());
				res = pstmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				JdbcUtil.rollback(con);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return res;
	}

	@Override
	public int deleteTitle(String code) {
		String sql = "delete from title where code = ?";
		int res = -1;
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, code);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateTitle(Title title) {
		//code, name
		String sql = "update title set name=? where code = ?";
		int res = -1;
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title.getName());
			pstmt.setString(2, title.getCode());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
