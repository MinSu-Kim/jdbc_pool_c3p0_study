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

	private DataBaseFactory() {
		db = new DataBase();
	}

	public DataSource getDataSource() {
		return db.getDataSource();
	}

	public void close() {
		db.close();
	}

}
