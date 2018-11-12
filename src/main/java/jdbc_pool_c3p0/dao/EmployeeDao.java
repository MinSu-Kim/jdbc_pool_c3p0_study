package jdbc_pool_c3p0.dao;

import java.util.List;

import jdbc_pool_c3p0.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();
	Employee selectEmployeeByCode(String empNo);
	int insertEmployee(Employee employee);
	int deleteEmployee(String empNo);
	int updateEmployee(Employee employee);

}
