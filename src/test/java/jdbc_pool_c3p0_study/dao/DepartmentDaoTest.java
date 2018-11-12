package jdbc_pool_c3p0_study.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_pool_c3p0_study.dao.DepartmentDao;
import jdbc_pool_c3p0_study.dao.DepartmentDaoImpl;
import jdbc_pool_c3p0_study.dto.Department;
import jdbc_pool_c3p0_study.jdbc.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start DepartmentDaoTest");
		dao = new DepartmentDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End DepartmentDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectDepartmentByAll() throws SQLException {
		List<Department> lists = dao.selectDepartmentByAll();
		LogUtil.prnLog(lists.toString());
		Assert.assertNotNull(lists);
	}

	@Test
	public void test05selectDepartmentByNo() throws SQLException {
		Department department = dao.selectDepartmentByCode("D001");
		LogUtil.prnLog(String.format("%s - %s", department.getClass().getSimpleName(), department));
		Assert.assertNotNull(department);
	}

	@Test
	public void test02InsertDepartment(){
		Department newDept = new Department("D005", "개발", 10);
		int rowAffected = dao.insertDepartment(newDept);
		LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
		Assert.assertEquals(1, rowAffected);
	}

	@Test
	public void test03DeleteDepartment() throws SQLException {
		int rowAffected = dao.deleteDepartment("D005");
		LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
		Assert.assertEquals(1, rowAffected);
	}

	@Test
	public void test04UpdateDepartment() throws SQLException {
		Department updateDept = new Department("D004", "영업", 15);
		int rowAffected = dao.updateDepartment(updateDept);
		LogUtil.prnLog(String.format("rowAffected %d", rowAffected));
		Assert.assertEquals(1, rowAffected);
	}
}
