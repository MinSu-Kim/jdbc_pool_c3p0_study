package jdbc_pool_c3p0.dao;

import java.util.List;

import jdbc_pool_c3p0.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	Department selectDepartmentByCode(String deptCode);
	int insertDepartment(Department department);
	int deleteDepartment(String deptCode);
	int updateDepartment(Department department);
}
