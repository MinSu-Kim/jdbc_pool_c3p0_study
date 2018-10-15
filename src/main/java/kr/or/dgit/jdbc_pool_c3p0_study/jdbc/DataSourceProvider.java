package kr.or.dgit.jdbc_pool_c3p0_study.jdbc;

import javax.sql.DataSource;

public class DataSourceProvider{
	
	public static DataSource getDataSource() {
		return MyDataSource.getInstance().getDataSource();
	}
	
}
