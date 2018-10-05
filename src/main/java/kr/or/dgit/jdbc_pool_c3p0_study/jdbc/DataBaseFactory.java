package kr.or.dgit.jdbc_pool_c3p0_study.jdbc;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

public class DataBaseFactory {
	private static final DataBaseFactory instance = new DataBaseFactory();
	
	public static DataBaseFactory getInstance() {
		return instance;
	}

	private DataBase db;
	private DataSource ds;
	
	private DataBaseFactory() {
		db = new DataBase();
		ds = db.getDataSource();
	}

	public DataSource getDataSource() {
		return ds;
	}
	
	public void close() {
		try {
			DataSources.destroy(ds);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
