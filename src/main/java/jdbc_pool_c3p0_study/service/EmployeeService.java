package jdbc_pool_c3p0_study.service;

import java.util.List;

import jdbc_pool_c3p0_study.dao.EmployeeDao;
import jdbc_pool_c3p0_study.dao.EmployeeDaoImpl;
import jdbc_pool_c3p0_study.dto.Employee;

public class EmployeeService {
	private EmployeeDao dao;
	
	public EmployeeService() {
		dao =new EmployeeDaoImpl();
	}
	
	public List<Employee> findEmployeeByAll() {
		return dao.selectEmployeeByAll();
	}
	
	public Employee findEmployeeByCode(String empNo) {
		return dao.selectEmployeeByCode(empNo);
	}
	
	public int registerEmployee(Employee employee) {
		return dao.insertEmployee(employee);
	}
	
	public int unRegisterEmployee(String empNo) {
		return dao.deleteEmployee(empNo);
	}
	
	public int updateEmployee(Employee employee) {
		return dao.updateEmployee(employee);
	}

}
