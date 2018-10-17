package kr.or.dgit.jdbc_pool_c3p0.persistence;

import java.util.List;

import kr.or.dgit.jdbc_pool_c3p0.domain.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	Department selectDepartmentByCode(String deptNo);
	int insertDepartment(Department department);
	int deleteDepartment(String deptNo);
	int updateDepartment(Department department);
}
