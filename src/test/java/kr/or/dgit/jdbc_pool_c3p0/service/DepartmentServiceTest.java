package kr.or.dgit.jdbc_pool_c3p0.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.jdbc_pool_c3p0.domain.Department;
import kr.or.dgit.jdbc_pool_c3p0.service.DepartmentService;

public class DepartmentServiceTest {
	static final Logger log = LogManager.getLogger();
	static DepartmentService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new DepartmentService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test
	public void testDepartmentByAll() throws SQLException {
		log.trace("testDepartmentByAll()");
		List<Department> list = service.findDepartmentByAll();
		Assert.assertNotNull(list);
		for (Department dept : list) {
			log.trace(dept);
		}
	}

	@Test
	public void testDepartmentByCode() throws SQLException {
		Department department = service.findDepartmentByCode("D001");
		log.trace(department);
		Assert.assertNotNull(department);
	}

	@Test
	public void testRegisterDepartment() throws SQLException {
		Department department = new Department("D005", "개발", 10);
		int res = service.registerDepartment(department);
		log.trace("result : " + res);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testUnRegisterDepartment() throws SQLException {
		int res = service.unRegisterDepartment("D005");
		log.trace("result : " + res);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testUpdateDepartment() throws SQLException {
		Department department = new Department("D004", "영업", 15);
		int res = service.updateDepartment(department);
		log.trace("result : " + res);
		Assert.assertEquals(1, res);
	}
}
