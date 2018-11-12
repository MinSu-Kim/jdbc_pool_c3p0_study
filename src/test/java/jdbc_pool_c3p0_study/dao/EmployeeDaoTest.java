package jdbc_pool_c3p0_study.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_pool_c3p0_study.dao.EmployeeDao;
import jdbc_pool_c3p0_study.dao.EmployeeDaoImpl;
import jdbc_pool_c3p0_study.dto.Department;
import jdbc_pool_c3p0_study.dto.Employee;
import jdbc_pool_c3p0_study.dto.Title;
import jdbc_pool_c3p0_study.jdbc.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static EmployeeDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start EmployeeDaoTest");
		dao = new EmployeeDaoImpl();	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End EmployeeDaoTest");
		dao = null;
	}
	
	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectEmployeeByAll() throws SQLException {
		List<Employee> list = dao.selectEmployeeByAll();
		Assert.assertNotNull(list);
		for (Employee e : list) {
			LogUtil.prnLog(e.toString());
		}
	}

	@Test
	public void test05SelectEmployeeByNo() throws SQLException {
		Employee employee = dao.selectEmployeeByCode("E001");
		LogUtil.prnLog(employee.toString());
		Assert.assertNotNull(employee);
	}

	@Test
	public void test02InsertEmployee() throws SQLException {
/*		Random rnd = new Random(1010);
		
		for(int i=123; i<1000;i++) {
			Employee e = new Employee(
					String.format("E%03d", i+1), 
					String.format("천사%d", i+1), 
					new Title(String.format("T%03d", (i%5)+1)), 
					rnd.nextInt(50) * 100000, 
					i%2==0?true:false, 
					i%2==0?"축구":"볼링", 
					new Department(String.format("D%03d", (i%5)+1)), 
					new Date());
			LogUtil.prnLog(e);
			service.registerEmployee(e);
			
		}*/
		Employee newEmp = new Employee("E002", "천사", new Title("T003"), 3500000, true, "축구", new Department("D002"), new Date());
		int res = dao.insertEmployee(newEmp);
		LogUtil.prnLog("result : " + res);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test04DeleteEmployee() throws SQLException {
		int res = dao.deleteEmployee("E002");
		LogUtil.prnLog("result : " + res);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test03UpdateEmployee() throws SQLException {
		Calendar d = Calendar.getInstance();
		d.set(2015, 9, 03);
		
		Employee emp = new Employee("E001", "사장", new Title("T001"), 5500000, true, "축구", new Department("D001"), d.getTime());
		int res = dao.updateEmployee(emp);
		LogUtil.prnLog("result : " + res);
		Assert.assertEquals(1, res);
	}

}
