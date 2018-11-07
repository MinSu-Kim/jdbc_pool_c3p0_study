package kr.or.dgit.jdbc_pool_c3p0.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
	
	public static Connection getConnection() throws SQLException {
		return MyDataSource.getInstance().getDataSource().getConnection();
	}
}
