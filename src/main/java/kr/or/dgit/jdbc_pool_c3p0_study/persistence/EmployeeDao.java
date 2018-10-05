package kr.or.dgit.jdbc_pool_c3p0_study.persistence;

import java.util.List;

import kr.or.dgit.jdbc_pool_c3p0_study.domain.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();
	Employee selectEmployeeByCode(String empNo);
	int insertEmployee(Employee employee);
	int deleteEmployee(String empNo);
	int updateEmployee(Employee employee);

}
