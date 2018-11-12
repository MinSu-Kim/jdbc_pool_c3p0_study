package jdbc_pool_c3p0.jdbc;

import javax.sql.DataSource;

/**
 * @author mskim
 * DataSource 테스트를 위해서 생성 배포 시 삭제
 */
public class DataSourceProvider{
	
	public static DataSource getDataSource() {
		return MyDataSource.getInstance().getDataSource();
	}
	
}
