package kr.or.dgit.jdbc_pool_c3p0_study;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mchange.v2.c3p0.PooledDataSource;

import kr.or.dgit.jdbc_pool_c3p0_study.jdbc.DataBaseFactory;

public class DBConnectionTest {
	static final Logger log = LogManager.getLogger();
	static DataSource ds;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.trace("setUpBeforeClass()");
		ds = DataBaseFactory.getInstance().getDataSource();
		printDriverStats();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.trace("tearDownAfterClass()");
		printDriverStats();
	}

	@Test
	public void testPool() throws SQLException {
		log.trace("testPool()");
		Connection[] connections = new Connection[10];
		for(int i=0; i<10; i++) {
			connections[i] = ds.getConnection();
			Assert.assertNotNull(connections[i]);
			printDriverStats();
		}
				
		for(int i=0; i<10; i++) {
			connections[i].close();
			printDriverStats();
		}
		
	}

	public static void printDriverStats() {
		PooledDataSource pds = (PooledDataSource) ds;
		try {
			log.trace(String.format("num_connections: %d", pds.getNumConnectionsDefaultUser()));
			log.trace(String.format("num_busy_connections: %d", pds.getNumBusyConnectionsDefaultUser()));
			log.trace(String.format("num_idle_connections: %d", pds.getNumIdleConnectionsDefaultUser()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
