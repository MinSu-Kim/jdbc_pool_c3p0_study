package jdbc_pool_c3p0_study.dao;

import java.util.List;

import jdbc_pool_c3p0_study.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	Department selectDepartmentByCode(String deptCode);
	int insertDepartment(Department department);
	int deleteDepartment(String deptCode);
	int updateDepartment(Department department);
}
