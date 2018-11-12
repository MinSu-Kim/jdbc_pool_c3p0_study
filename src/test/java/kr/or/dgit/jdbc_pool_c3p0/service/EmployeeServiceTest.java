package kr.or.dgit.jdbc_pool_c3p0.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc_pool_c3p0.domain.Department;
import jdbc_pool_c3p0.domain.Employee;
import jdbc_pool_c3p0.domain.Title;
import jdbc_pool_c3p0.service.EmployeeService;

public class EmployeeServiceTest {
	static final Logger log = LogManager.getLogger();
	static EmployeeService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new EmployeeService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test
	public void testEmployeeByAll() throws SQLException {
		log.trace("testDepartmentByAll()");
		List<Employee> list = service.findEmployeeByAll();
		Assert.assertNotNull(list);
		for (Employee e : list) {
			log.trace(e);
		}
	}

	@Test
	public void testEmployeeByCode() throws SQLException {
		Employee employee = service.findEmployeeByCode("E001");
		log.trace(employee);
		Assert.assertNotNull(employee);
	}

	@Test
	public void testRegisterEmployee() throws SQLException {
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
			log.trace(e);
			service.registerEmployee(e);
			
		}*/
/*		Employee employee = new Employee("E002", "천사", new Title("T003"), 3500000, true, "축구", new Department("D002"), new Date());
		int res = service.registerEmployee(employee);
		log.trace("result : " + res);
		Assert.assertEquals(1, res);*/
	}

/*	@Test
	public void testUnRegisterEmployee() throws SQLException {
		int res = service.unRegisterEmployee("E002");
		log.trace("result : " + res);
		Assert.assertEquals(1, res);
	}*/

	@Test
	public void testUpdateEmployeet() throws SQLException {
		LocalDate joinDate = LocalDate.of(2015, 10, 03);
		Employee emp = new Employee("E001", "사장", new Title("T001"), 5500000, true, "축구", new Department("D001"), java.sql.Date.valueOf(joinDate));
		int res = service.updateEmployee(emp);
		log.trace("result : " + res);
		Assert.assertEquals(1, res);
	}

}
